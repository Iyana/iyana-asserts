/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.schema.dto;

/**
 *
 * @author fgyara
 */
public class Column {
    
    private final String columnName;
    private final String columnType;
    private final boolean isMandatory;
    
    public Column(String columnName, String columnType, boolean isMandatory) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.isMandatory = isMandatory;
    }

    /**
     * @return the columnName
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @return the columnType
     */
    public String getColumnType() {
        return columnType;
    }

    /**
     * @return the isMandatory
     */
    public boolean isIsMandatory() {
        return isMandatory;
    }
}
