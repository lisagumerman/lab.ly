package com.lab.ly.formats.ds;

/**
 * User: haswell
 * Date: 11/28/14
 * Time: 4:30 PM
 */
public interface Parser<T, U> {

    T parse(U u);
}
