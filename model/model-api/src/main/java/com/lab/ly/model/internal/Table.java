package com.lab.ly.model.internal;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by haswell on 1/27/15.
 */
@XmlRootElement
public class Table {

    @XmlAttribute
    private Integer draw;

    @XmlAttribute
    private Integer recordsTotal;

    @XmlAttribute
    private Integer recordsFiltered;

    @XmlElement
    private MappedList data;


    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public MappedList getData() {
        return data;
    }

    public void setData(MappedList data) {
        this.data = data;
    }
}
