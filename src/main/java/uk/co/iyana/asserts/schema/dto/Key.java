/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.schema.dto;

import java.util.List;

/**
 *
 * @author fgyara
 */
public class Key {
    private String keyName;
    private KeyType keyType;
    private List<String> keyColumns;
    
    private Key(String keyName) {
        this.keyName = keyName;
    }
    
    public static Key name(String keyName) {
        Key key = new Key(keyName);
        return key;
    }
    
    public Key type(KeyType keyType) {
        this.setKeyType(keyType);
        return this;
    }
    
    public Key keyColumn(KeyColumn keyColumn) {
        setKeyColumns(keyColumn.getKeyColumns());
        return this;
    }

    /**
     * @return the keyName
     */
    public String getKeyName() {
        return keyName;
    }

    /**
     * @param keyName the keyName to set
     */
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    /**
     * @return the keyType
     */
    public KeyType getKeyType() {
        return keyType;
    }

    /**
     * @param keyType the keyType to set
     */
    public void setKeyType(KeyType keyType) {
        this.keyType = keyType;
    }

    /**
     * @return the keyColumns
     */
    public List<String> getKeyColumns() {
        return keyColumns;
    }

    /**
     * @param keyColumns the keyColumns to set
     */
    public void setKeyColumns(List<String> keyColumns) {
        this.keyColumns = keyColumns;
    }
}
