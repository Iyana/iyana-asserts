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
public class KeyColumn {
    private final List<String> keyColumns = new ArrayList<>();

    public static KeyColumn with(String keyColumnName) {
        KeyColumn akc = new KeyColumn();
        akc.keyColumns.add(keyColumnName);
        return akc;
    }
    
    public KeyColumn and(String keyColumnName) {
        this.keyColumns.add(keyColumnName);
        return this;
    }

    public List<String> getKeyColumns() {
        return this.keyColumns;
    }
}
