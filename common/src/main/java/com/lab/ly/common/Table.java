package com.lab.ly.common;

import java.util.Collection;
import java.util.Map;

/**
 * User: haswell
 * Date: 11/28/14
 * Time: 4:18 PM
 */
public interface Table {

    Table addColumn(Class<?> type, String name);

    Map<String, Collection<?>> getRawValues();

    <T> Collection<?> getColumn(Class<T> type, String name);

    <T> boolean hasColumn(Class<T> type, String name);

    <T> Collection<T> getColumn(String name);

    <T> boolean hasColumn(String name);

}
