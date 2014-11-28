package com.lab.ly;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * User: haswell
 * Date: 11/23/14
 * Time: 5:51 PM
 */
@XmlRootElement(name = "file")
public class DelimitedFile {

    @XmlElement
    final List<String> headers;

    @XmlElement
    final Map<String, List<String>> contents;

    public DelimitedFile(String input) {
        final String[] lines = input.split("\\n");
        headers = extractFirst(lines);
        if(lines.length > 0) {
            contents = extractAll(lines);
        } else {
            contents = new HashMap<String, List<String>>();
        }
    }

    final List<String> extractFirst(String[] fst) {
        String[] headerLine = fst[0].split(",");
        return Arrays.asList(headerLine);
    }

    final Map<String, List<String>> extractAll(String[] lines) {
        final Map<String, List<String>> contents = new HashMap<String, List<String>>();
        for(int i = 1; i < lines.length; ++i) {
            final String[] line = lines[i].split(",");
            for(int j = 0; j < line.length; ++j) {
                final String cell = line[j];
                List<String> column = contents.get(headers.get(j));
                if(column == null) {
                    column = new ArrayList<String>();
                    contents.put(headers.get(j), column);
                }
                column.add(cell);
            }
        }
        return contents;
    }

}
