package com.lab.ly.formats.ds

import spock.lang.Shared
import spock.lang.Specification

/**
 * User: haswell
 * Date: 11/28/14
 * Time: 4:36 PM
 */
class InMemoryDelimitedFileParserTest extends Specification {

    @Shared
    def parser = new InMemoryDelimitedFileParser()

    def "a parser should return the correct headers for a sample input string"() {
        expect:
            headers == parsedHeaders
        where:
            headers << [["hello", "world", "how are you?"]]
            parsedHeaders << [parser.parse("hello,world,how are you?\nI am fine, thank you, very much\n").columns]
    }
}
