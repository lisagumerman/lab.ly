package com.lab.ly;


import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DelimitedFileTest {

    @Test public void ensureDelimitedFileExtractsHeaders() {
        final String header = "hello,world,how,are,you";
        final DelimitedFile file = new DelimitedFile(header);
        assertTrue(file.headers.equals(Arrays.asList("hello", "world", "how", "are", "you")));
    }

    @Test
    public void ensureBodyIsExpected() {
        final String file = "hello,world\nhow,are\nyou,i'm";
        final DelimitedFile f = new DelimitedFile(file);
        System.out.println(f.contents);

    }



}