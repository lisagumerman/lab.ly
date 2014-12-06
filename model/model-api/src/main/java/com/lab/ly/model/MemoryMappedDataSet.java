package com.lab.ly.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

/**
 * User: haswell
 * Date: 11/28/14
 * Time: 6:34 PM
 */
@XmlRootElement
public class MemoryMappedDataSet {

    @XmlElement
    private HeaderSet headers;

    @XmlElement
    private Table data;


    public MemoryMappedDataSet() {
        headers = new HeaderSet();
        data = new Table();
    }

    public void addColumn(String name, Collection<String> data) {
        headers.add(name);
        this.data.addColumn(name, data);
    }


}
