package io.lably.blade.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.lab.ly.model.DataSet;
import org.eclipse.jetty.util.ajax.JSON;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by haswell on 1/31/15.
 */
public class NbaSeasonReader {

    static class Statistics {
        private Parameters parameters;
        private String resource;
        private List<ResultSet> resultSets;
    }

    static class Parameters {
        String LeagueID;
        String Season;
        String SeasonType;
    }

    static class ResultSet {
        String name;
        List<String> headers;
        List<List<String>> rowSet;
    }


    public List<DataSet> readDataSet(InputStream inputStream) {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Statistics stats
                = new Gson().fromJson(reader, Statistics.class);
        return stats.resultSets.stream().map(e -> toDataSet(e)).collect(Collectors.toList());
    }

    private DataSet toDataSet(ResultSet stats) {
        final DataSet dataSet = new DataSet();
        dataSet.setHeaders(stats.headers);
        for(List<String> row : stats.rowSet) {
            dataSet.addRow(row);
        }
        return dataSet;

    }

}
