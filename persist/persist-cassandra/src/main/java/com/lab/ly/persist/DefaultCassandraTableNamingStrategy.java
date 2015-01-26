package com.lab.ly.persist;

/**
 * Created by haswell on 1/24/15.
 */
public class DefaultCassandraTableNamingStrategy implements NamingStrategy {
    @Override
    public String getName(String name) {
        return "c" + name.replaceAll("-", "");
    }
}
