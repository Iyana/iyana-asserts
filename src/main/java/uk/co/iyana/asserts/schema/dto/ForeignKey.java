/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.schema.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fgyara
 */
public class ForeignKey {
    
    private String name;
    private boolean cascadeDelete;
    private List<String> keyColumns = new ArrayList<>();
    private List<String> referenceColumns = new ArrayList<>();
    private String references;
    
    private ForeignKey(String name) {
        this.name = name;
    }
    
    public static ForeignKey name(String name) {
        ForeignKey fk = new ForeignKey(name);
        return fk;
    }
    
    public ForeignKey cascadeDelete(boolean cascadeDelete) {
        this.setCascadeDelete(cascadeDelete);
        return this;
    }
    
    public ForeignKey keyColumn(KeyColumn keyColumn) {
        setKeyColumns(keyColumn.getKeyColumns());
        return this;
    }
    
    public ForeignKey referenceColumn(KeyColumn referenceColumn) {
        setReferenceColumns(referenceColumn.getKeyColumns());
        return this;
    }
    
    public ForeignKey references(String references) {
        this.setReferences(references);
        return this;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the cascadeDelete
     */
    public boolean isCascadeDelete() {
        return cascadeDelete;
    }

    /**
     * @param cascadeDelete the cascadeDelete to set
     */
    public void setCascadeDelete(boolean cascadeDelete) {
        this.cascadeDelete = cascadeDelete;
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

    /**
     * @return the referenceColumns
     */
    public List<String> getReferenceColumns() {
        return referenceColumns;
    }

    /**
     * @param referenceColumns the referenceColumns to set
     */
    public void setReferenceColumns(List<String> referenceColumns) {
        this.referenceColumns = referenceColumns;
    }

    /**
     * @return the references
     */
    public String getReferences() {
        return references;
    }

    /**
     * @param references the references to set
     */
    public void setReferences(String references) {
        this.references = references;
    }
}
