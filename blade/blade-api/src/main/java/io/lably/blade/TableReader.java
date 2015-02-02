package io.lably.blade;

import com.lab.ly.model.DataSet;
import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * Created by haswell on 1/31/15.
 */
public interface TableReader {
    WebDriver getDriver();

    String getUrl();

    List<DataSet> resolveDataSets();
}
