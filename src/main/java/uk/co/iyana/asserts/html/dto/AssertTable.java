/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.html.dto;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fgyara
 */
public class AssertTable {
    private String xpath;
    private String name;
    
    private final List<String> titles = new ArrayList<>();

    /**
     * @return the xpath
     */
    public String getXpath() {
        return xpath;
    }

    /**
     * @param xpath the xpath to set
     */
    public void setXpath(String xpath) {
        this.xpath = xpath;
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
     * @return the titles
     */
    public List<String> getTitles() {
        return titles;
    }
    

}
