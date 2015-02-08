package com.lab.ly.model.io;

import com.lab.ly.model.DataSource;

/**
 * Created by haswell on 1/31/15.
 */
public interface DataSetResolver<T>{

    DataSource resolve(T source);

}
