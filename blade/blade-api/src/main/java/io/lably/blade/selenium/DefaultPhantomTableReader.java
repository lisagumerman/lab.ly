package io.lably.blade.selenium;

import com.lab.ly.model.Column;
import com.lab.ly.model.DataSet;
import io.lably.blade.TableReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by haswell on 1/31/15.
 */
public class DefaultPhantomTableReader implements TableReader {

    final String url;
    final WebDriver driver;

    public DefaultPhantomTableReader(final String url) {
        this.url = url;
        this.driver = new PhantomJSDriver();
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public List<DataSet> resolveDataSets() {
        final Document document = resolveDocument(url);
        return resolveDataSets(document);
    }

    private List<DataSet> resolveDataSets(Document document) {
        final List<Element> tables = document.getElementsByTag("table");
        return tables.parallelStream()
                .map(this::parseDataSet)
                .collect(Collectors.toList());
    }

    private DataSet parseDataSet(Element element) {
        final List<String> headers = extractHeaders(element);
        final DataSet dataSet = new DataSet();
        return extractDataSetFrom(headers, dataSet, element);

    }

    private DataSet extractDataSetFrom(
            List<String> headers,
            DataSet dataSet,
            Element element
    ) {
        dataSet.setHeaders(headers);
        final List<Element> rows = element.select("tbody > tr");
        for(Element row : rows) {
            mapHeaderToRow(row, headers, dataSet);
        }
        return dataSet;
    }

    private void mapHeaderToRow(
            Element row,
            List<String> headers,
            DataSet dataSet) {
        final Iterator<String> headerIterator = headers.iterator();
        final Iterator<Element> rowIterator = row.getElementsByTag("td").iterator();
        while(headerIterator.hasNext()) {
            final String header = headerIterator.next();
            if(rowIterator.hasNext()) {
                final Element col = rowIterator.next();
                final String text = col.text();
                dataSet.getColumn(header).add(text);
            }
        }
    }

    private List<String> extractHeaders(Element element) {
        return element.select("thead > tr > th").stream().
                map(Element::text).
                collect(Collectors.toList());


    }

    private Document resolveDocument(String url) {
        driver.get(url);
        return Jsoup.parse(driver.getPageSource());
    }


}
