/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.schema.test;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import uk.co.iyana.asserts.schema.dto.Column;
import uk.co.iyana.asserts.schema.dto.DriverType;
import uk.co.iyana.asserts.schema.dto.ForeignKey;
import uk.co.iyana.asserts.schema.dto.Key;
import uk.co.iyana.asserts.schema.dto.Schema;
import uk.co.iyana.asserts.schema.dto.Table;
import uk.co.iyana.asserts.test.TestBase;

/**
 *
 * @author fgyara
 */
public class SchemaTestBase extends TestBase {
    
    protected Connection connection;
    protected DatabaseMetaData metadata;
    protected final List<Schema> schemas = new ArrayList<>();
    
    public SchemaTestBase() {
        super("Schema");
    }
    
    @DataProvider(name = "tablesParamsProvider")
    public Object[][] tablesParamsProvider() {

        ArrayList<Object[]> toRet = new ArrayList<>();
        Object[][] aToRet = {{}};
        Object[][] emptyArray = {};
        
        for (Schema schema : schemas) {
            for (Table table : schema.getTables()) {
                String errorLocation = "." + schema.getEntityName() + ".Table - " + table.getTableName();
                Object[] row = { errorLocation, table }; 
                toRet.add(row);
            }
        }
        
        if (toRet.isEmpty()) {
            return emptyArray;
        }
        
        return toRet.toArray(aToRet);
    }   
    
    @Test(dataProvider = "tablesParamsProvider") 
    public void testTables(String errorLocation, Table table) {
        this.checkTableExists(errorLocation, table.getTableName());
        this.checkColumnsExistForTable(errorLocation, table);
    }

    
    @DataProvider(name = "sequenceParamsProvider")
    public Object[][] sequenceParamsProvider() {

        ArrayList<Object[]> toRet = new ArrayList<>();
        Object[][] aToRet = {{}};
        Object[][] emptyArray = {};
        
        for (Schema schema : schemas) {
            for (String sequence : schema.getSequences()) {
                String errorLocation = "." + schema.getEntityName() + ".Sequence - " + sequence;
                Object[] row = { errorLocation, sequence}; 
                toRet.add(row);
            }
        }
        
        if (toRet.isEmpty()) {
            return emptyArray;
        }
        
        return toRet.toArray(aToRet);
    }    
    
    @Test(dataProvider = "sequenceParamsProvider") 
    public void testSequences(String errorLocation, String sequenceName) {
        this.checkSequenceExists(errorLocation, sequenceName);
    }
    
    @DataProvider(name = "keyParamsProvider")
    public Object[][] keyParamsProvider() {
        ArrayList<Object[]> toRet = new ArrayList<>();
        Object[][] aToRet = {{}};
        Object[][] emptyArray = {};

        for (Schema schema : schemas) {
            for (Table table : schema.getTables()) {
                for (Key key : table.getKeys()) {
                    String errorLocation = "." + schema.getEntityName() + ".Key - " + table.getTableName() + "." + key.getKeyName();
                    Object[] row = { errorLocation, key, table.getTableName() }; 
                    toRet.add(row);
                }
            }
        }
        
        if (toRet.isEmpty()) {
            return emptyArray;
        }
        return toRet.toArray(aToRet);
    }    
    
    @Test(dataProvider = "keyParamsProvider") 
    public void testKeys(String errorLocation, Key key, String tableName) {
        this.checkKeyExists(errorLocation, key, tableName);
    }
    
    @DataProvider(name = "foreignKeyParamsProvider")
    public Object[][] foreignKeyParamsProvider() {
        ArrayList<Object[]> toRet = new ArrayList<>();
        Object[][] aToRet = {{}};
        Object[][] emptyArray = {};
        
        for (Schema schema : schemas) {
            for (Table table : schema.getTables()) {
                for (ForeignKey fk : table.getForeignKeys()) {
                    String errorLocation = "." +  schema.getEntityName() + ".FK - " + table.getTableName() + "." + fk.getName();
                    Object[] row = { errorLocation, fk, table.getTableName() }; 
                    toRet.add(row);
                }
            }
        }
        
        if (toRet.isEmpty()) {
            return emptyArray;
        }
        
        return toRet.toArray(aToRet);
    }    
    
    @Test(dataProvider = "foreignKeyParamsProvider") 
    public void testForeignKeys(String errorLocation, ForeignKey fk, String tableName) {
        this.checkForeignKeyExists(errorLocation, fk, tableName);
    }    
    
    protected void connect(DriverType driverType, String url, String usr, String pwd) throws ClassNotFoundException, SQLException {

        if (connection != null) {
            return;
        }
                   
        switch (driverType) {
            case ORACLE:
                Class.forName("oracle.jdbc.OracleDriver");
                break;
        }
        
        connection = DriverManager.getConnection(url, usr, pwd);
        metadata = connection.getMetaData();
    }
    
    
    protected void checkTableExists(String errorLocation, String tableName) {
        try (ResultSet rs = metadata.getTables(connection.getCatalog(), connection.getSchema(), tableName, null)) {
            if (!rs.next()) {
                Assert.fail(errorLocation + " Table not found");
            }
        } catch (SQLException ex) {
            Assert.fail(errorLocation + " Table not found (Exception)", ex);
        }
    }
    
    protected void checkColumnsExistForTable(String errorLocation, Table table) {
        for (Column column : table.getColumns()) {
            checkColumnExistsForTable(errorLocation, column, table.getTableName());
        }
    }
        
    protected void checkColumnExistsForTable(String errorLocation, Column column, String tableName) {
        errorLocation = errorLocation + "." + column.getColumnName();
        try (ResultSet rs = metadata.getColumns(connection.getCatalog(), connection.getSchema(), tableName, column.getColumnName())) {
            if (!rs.next()) {
                Assert.fail(errorLocation + " Column not found");
            }
            
            // ensure the mandatory flag is correct
            int nullable = rs.getInt(11);
            if (column.isIsMandatory()) {
                Assert.assertEquals(nullable, DatabaseMetaData.columnNoNulls, "Expected mandatory column but found nullable - " + column.getColumnName());
            } else {
                Assert.assertEquals(nullable, DatabaseMetaData.columnNullable, "Expected nullable column but found mandatory - " + column.getColumnName());
            }
        } catch (SQLException ex) {
            Assert.fail(errorLocation + " Column not found (Exception)", ex);
        }
    }
    
    
    protected void checkSequenceExists(String errorLocation, String sequenceName) {
        try (ResultSet rs = metadata.getTables(connection.getCatalog(), connection.getSchema(), sequenceName, new String[] { "SEQUENCE" })) {
            if (!rs.next()) {
                Assert.fail(errorLocation + " Sequence not found");
            }
        } catch (SQLException ex) {
            Assert.fail(errorLocation + " Sequence not found (Exception)", ex);
        }
    }
    
    protected void checkKeyExists(String errorLocation, Key key, String tableName) {
        switch (key.getKeyType()) {
            case NON_UNIQUE:
                checkKeyExistsForTable(errorLocation, key, false, tableName);
                break;
            case PRIMARY:
                checkPrimaryKeyExistsForTable(errorLocation, key, tableName);
                break;
            case UNIQUE:
                checkKeyExistsForTable(errorLocation, key, true, tableName);
                break;
        }
    }

    protected void checkPrimaryKeyExistsForTable(String errorLocation, Key key, String tableName) {
        try (ResultSet rs = metadata.getPrimaryKeys(connection.getCatalog(), connection.getSchema(), tableName)) {
            
            ArrayList<String> keyColumns = new ArrayList<>();
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                keyColumns.add(columnName);
                String pkName = rs.getString("PK_NAME");
                
                // ensure PK name matches
                Assert.assertEquals(pkName, key.getKeyName(), errorLocation + "Primary key name incorrect for table" );
            }
            
            // check that column names match
            Assert.assertEquals(keyColumns, key.getKeyColumns(), errorLocation + "Primary key columns incorrect");
        } catch (SQLException ex) {
            Assert.fail (errorLocation + "Primary key name incorrect (Exception)", ex);
        }        
    }
    
    protected void checkKeyExistsForTable(String errorLocation, Key key, boolean unique, String tableName) {
        try (ResultSet rs = metadata.getIndexInfo(connection.getCatalog(), connection.getSchema(), tableName, false, false)) {
            
            ArrayList<String> keyColumns = new ArrayList<>();
            while (rs.next()) {
                if (rs.getShort("TYPE") == (DatabaseMetaData.tableIndexStatistic )) {
                    continue;
                }
                String indexName = rs.getString("INDEX_NAME");
                if (indexName.equals(key.getKeyName())) {
                    
                    boolean isUnique = !(rs.getBoolean("NON_UNIQUE"));
                    Assert.assertEquals(isUnique, unique, errorLocation + " Unexpected index type");
                    
                    String columnName = rs.getString("COLUMN_NAME");
                    keyColumns.add(columnName);
                }
            }
            
            // check that there is at least one column
            Assert.assertFalse(keyColumns.isEmpty(), errorLocation + " Key does not have key columns");

            // check that column names match
            Assert.assertEquals(keyColumns, key.getKeyColumns(), errorLocation + " Unexpected key columns");
        } catch (SQLException ex) {
            Assert.fail (errorLocation + "Unknown error (Exception)", ex);
        }        
    }
    
    protected void checkForeignKeyExists(String errorLocation, ForeignKey fk, String tableName) {
        try (            
            ResultSet rs = metadata.getImportedKeys(connection.getCatalog(), connection.getSchema(), tableName)
            ) {
            String expectedCatalog = connection.getCatalog();
            String expectedSchema = connection.getSchema();
            
            ArrayList<String> keyColumns = new ArrayList<>();
            ArrayList<String> refColumns = new ArrayList<>();
            while (rs.next()) {
                String fkName = rs.getString("FK_NAME");
                if (!(fkName.equals(fk.getName()))) {
                    continue;
                }
                
                refColumns.add( rs.getString("PKCOLUMN_NAME"));
                
                // check that the FK table is correct
                String fkTableCatalog = rs.getString("PKTABLE_CAT");
                String fkTableSchema = rs.getString("PKTABLE_SCHEM");
                String fkTableName = rs.getString("PKTABLE_NAME");
                Assert.assertEquals(fkTableCatalog, expectedCatalog, errorLocation + " FK in incorrect catalogue");
                Assert.assertEquals(fkTableSchema, expectedSchema, errorLocation + " FK in incorrect schema");
                Assert.assertEquals(fkTableName, fk.getReferences(), errorLocation + " FK refers to incorrect table");
                
                keyColumns.add( rs.getString("FKCOLUMN_NAME"));

                // check for cascade delete
                short deleteRule = rs.getShort("DELETE_RULE");
                int expectedDeleteRule = (fk.isCascadeDelete() ? DatabaseMetaData.importedKeyCascade :DatabaseMetaData.importedKeyRestrict);
                Assert.assertEquals(deleteRule, expectedDeleteRule, errorLocation + " Cascade rule is incorrect.");
                
            }
            rs.close();
            
            // check that there is at least one column
            Assert.assertFalse( keyColumns.isEmpty(), errorLocation + " No key columns found for FK");

            // check that column names match
            Assert.assertEquals( keyColumns, fk.getKeyColumns(), errorLocation + " Key columns incorrect for FK");
            Assert.assertEquals( refColumns, fk.getReferenceColumns(), errorLocation + " Reference columns incorrect for FK");
        } catch (SQLException ex) {
            Assert.fail ( errorLocation + " General error (Exception)", ex);
        }        
    }    
    
}
