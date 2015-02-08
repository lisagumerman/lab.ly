package com.lab.ly.model;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import javax.persistence.*;
import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by haswell on 1/24/15.
 */

@XmlRootElement
@javax.persistence.Entity
@Table(name = "lably_datasets")
public class DataSetDescriptor implements Entity<Long, String> {


    @Id
    @XmlAttribute
    @GeneratedValue
    private Long id;

    @Basic
    @Column(name = "entity_key")
    private String key;

    @Basic
    @Column(name = "name")
    private String name;


    @ManyToOne
    @XmlInverseReference(mappedBy = "columns")
    private Account owner;

    @ElementCollection(
            fetch = FetchType.EAGER)
    @CollectionTable(
            name = "lably_column_definition",
            joinColumns = @JoinColumn(name = "owner_id")
    )
    @XmlElement(name = "column")
    @XmlElementWrapper(name = "columns")
    private Set<ColumnDefinition> columns = new HashSet<>();

    public DataSetDescriptor(Long aLong, String s) {
        this.key = s;
    }

    public DataSetDescriptor() {
        this(null, UUID.randomUUID().toString());

    }

    public void addColumn(ColumnDefinition columnDefinition) {
        this.columns.add(columnDefinition);
    }

    public Set<ColumnDefinition> getColumnDefinitions() {
        return columns;
    }



    public void setOwner(Account owner) {
        this.owner = owner;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isNew() {
        return getId() != null;
    }

    @Override
    public EntityCoordinate<Long, String> getCoordinate() {
        return new EntityCoordinate<>(id, name);
    }
}
