package com.lab.ly.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * User: haswell
 * Date: 11/28/14
 * Time: 7:11 PM
 */
@XmlRootElement(name = "data")
public class Table {

    @XmlElement
    private Map<String, Collection<String>> data;

    public Table() {
        data = new HashMap<>();
    }

    public Map<String, Collection<String>> getData() {
        return data;
    }

    public void addColumn(String name, Collection<String> data) {
        this.data.put(name, data);
    }
}


