package com.lab.ly.common;

import com.lab.ly.Type;

import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;

/**
 * User: haswell
 * Date: 11/28/14
 * Time: 4:16 PM
 */
@XmlTransient
public interface DataSet {


    Collection<String> getColumns();

    Table getContents();



}
