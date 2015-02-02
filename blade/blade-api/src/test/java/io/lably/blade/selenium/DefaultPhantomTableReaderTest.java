package io.lably.blade.selenium;

import com.lab.ly.model.Column;
import com.lab.ly.model.DataSet;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DefaultPhantomTableReaderTest {

    static final List<DataSet> dataSets = new ArrayList<>();

    @BeforeClass
    public static void setUp() {
        dataSets.addAll(new
                DefaultPhantomTableReader("http://stats.nba.com/tracking/#!/player/catchshoot/").resolveDataSets());
    }



    @Test
    public void ensureReaderCanReadFromNBA() {
        assertThat(dataSets.size(), is(1));
    }

    @Test
    public void ensureReaderReadsCorrectNumberOfColumns() {
        final DataSet fst = dataSets.get(0);
        assertThat(fst.getColumnCount(), is(13));
    }

    @Test
    public void ensureReaderReadsHeadersCorrectly() {
        final DataSet fst = dataSets.get(0);
        assertThat(fst.getHeaders().size(), is(13));
    }


    @Test
    public void ensureReaderReadsColumnCountsCorrectly() {
        final DataSet fst = dataSets.get(0);
        fst.getHeaders().forEach(header -> {
            Column<String> column = fst.getColumn(header);
            assertThat(column.size(), is(518));

        }
        );


    }




}