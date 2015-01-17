package com.lab.ly.formats.ds

import com.lab.ly.io.parsers.DelimitedFileParser
import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by haswell on 1/14/15.
 */
class DataSetSpecification extends Specification {

    @Shared
    DelimitedFileParser parser = new DelimitedFileParser(",");

    def parse(String input) {
        return parser.parse(input);
    }


    def "a parser should correctly parse a well-formed file with headers"() {
        expect:
            ds.get(0) == ["Hello", "World"]
            ds.get(1) == ["How", "Are"]
        where:
            ds << [parse("""Hello,World\nHow,Are""")]
    }

}
