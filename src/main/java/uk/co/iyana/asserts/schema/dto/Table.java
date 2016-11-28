/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.schema.dto;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author fgyara
 */
public class Table {
    private final String tableName;
    private final List<Column> columns = new ArrayList<>();
    private final List<Key> keys = new ArrayList<>();
    private final List<ForeignKey> foreignKeys = new ArrayList<>();
    
    private Table(String tableName) {
        this.tableName = tableName;
    }
    
    public static Table name(String tableName) {
        Table t = new Table(tableName);
        return t;
    }
    
    public Table withColumn(String columnName, String columnType, boolean isMandatory) {
        this.getColumns().add(new Column(columnName, columnType, isMandatory));
        return this;
    }
    
    public Table withKey(Key key) {
        this.getKeys().add(key);
        return this;
    }
    
    public Table withForeignKey(ForeignKey fk) {
        this.getForeignKeys().add(fk);
        return this;
    }

    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @return the columns
     */
    public List<Column> getColumns() {
        return columns;
    }

    /**
     * @return the keys
     */
    public List<Key> getKeys() {
        return keys;
    }

    /**
     * @return the foreignKeys
     */
    public List<ForeignKey> getForeignKeys() {
        return foreignKeys;
    }
    
}
