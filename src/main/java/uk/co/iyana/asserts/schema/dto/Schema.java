/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.schema.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author fgyara
 */
public class Schema {

    private final List<Table> tables = new ArrayList<>();
    private final List<String> sequences = new ArrayList<>();
    private String entityName;
    
    public Schema forEntity(String entityName) {
        this.entityName = entityName;
        return this;
    }
    
    public Schema hasTable(Table t) {
        this.getTables().add(t);
        return this;
    }
    
    public Schema hasSequence(String sequenceName) {
        this.getSequences().add(sequenceName);
        return this;
    }

    private void dumpRS(ResultSet rs) throws SQLException {
        while (rs.next()) {
            int iCols = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= iCols; i++) {
                System.out.println( rs.getMetaData().getColumnName(i) + " > " +  rs.getString(i));
            }
            System.out.println("");
        }
    }

    /**
     * @return the tables
     */
    public List<Table> getTables() {
        return tables;
    }

    /**
     * @return the sequences
     */
    public List<String> getSequences() {
        return sequences;
    }

    /**
     * @return the entityName
     */
    public String getEntityName() {
        return entityName;
    }
}
