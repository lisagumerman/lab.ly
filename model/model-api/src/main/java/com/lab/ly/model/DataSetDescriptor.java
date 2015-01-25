package com.lab.ly.model;

import javax.persistence.*;
import javax.persistence.Column;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;
import java.util.Set;

/**
 * Created by haswell on 1/24/15.
 */

@XmlRootElement
@javax.persistence.Entity
@Table(name = "lably_datasets")
public class DataSetDescriptor extends AbstractEntity<Long, String> {



    @ManyToOne
    private Account owner;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<ColumnDefinition> columns;

    public DataSetDescriptor(Long aLong, String s) {
        super(aLong, s);
    }

    public DataSetDescriptor() {

    }

    public void addColumn(ColumnDefinition columnDefinition) {
        this.columns.add(columnDefinition);
    }

    public Set<ColumnDefinition> getColumnDefinitions() {
        return columns;
    }



    public void setOwner(Account owner) {
        this.owner = owner;
        if(owner != null) {
            owner.addDataSet(this);
        }
    }
}
