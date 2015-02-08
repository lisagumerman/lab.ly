package com.lab.ly.service;

import com.lab.ly.DataSourceService;
import com.lab.ly.model.DataSet;
import io.lably.blade.json.NbaSeasonReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by haswell on 1/31/15.
 */
public class DefaultDataSourceService implements DataSourceService {


    @Override
    public DataSet get(String dsName, String dataSetName) {
        try (InputStream inputStream =
                     new URL("http://stats.nba.com/js/data/sportvu/2014/catchShootData.json").openStream()) {
            return new NbaSeasonReader().readDataSet(inputStream).get(0);
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
