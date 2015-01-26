package com.lab.ly.model;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by haswell on 1/24/15.
 */
@Embeddable
@XmlRootElement
public class ColumnDefinition {

    @Enumerated
    @Column(name = "column_type")
    private ColumnType type;

    @Basic
    @Column(name = "col_name")
    private String name;

    @Basic
    @Column(name = "column_index")
    private Integer index;

    public ColumnDefinition(ColumnType type, String name, Integer index) {
        this.type = type;
        this.name = name;
        this.index = index;
    }

    public ColumnDefinition() {
    }

    public ColumnType getType() {
        return type;
    }

    public void setType(ColumnType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
