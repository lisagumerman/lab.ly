package com.lab.ly.spark;

import com.netflix.astyanax.test.EmbeddedCassandra;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.junit.Test;

/**
 * Created by haswell on 1/9/15.
 */
public class SparkTestCase {

    @Test
    public void ensure() throws Exception {
        EmbeddedCassandra cassandra = new EmbeddedCassandra();
        cassandra.start();
        SparkConf conf = new SparkConf()
                .setAppName("test")
                .setMaster("local")
                .set("spark.cassandra.connection.host", "127.0.0.1:9160");

        SparkContext context = new SparkContext(conf);






    }
}
