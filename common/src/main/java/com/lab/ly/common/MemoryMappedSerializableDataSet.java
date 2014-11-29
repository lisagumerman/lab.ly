package com.lab.ly.common;

import com.lab.ly.Type;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User: haswell
 * Date: 11/28/14
 * Time: 4:27 PM
 */
@XmlRootElement
public class MemoryMappedSerializableDataSet implements DataSet {

    @XmlElement
    private Table table;

    @XmlElement
    private List<String> columns;

    public MemoryMappedSerializableDataSet() {
        columns = new ArrayList<String>();
        table = new MemoryMappedSerializableTable();
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    @Override
    public Collection<String> getColumns() {
        return columns;
    }

    @Override
    public Table getContents() {
        return table;
    }

}
