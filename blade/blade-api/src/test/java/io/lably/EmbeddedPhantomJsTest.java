package io.lably;

import com.github.jarlakxen.embedphantomjs.ExecutionTimeout;
import com.github.jarlakxen.embedphantomjs.PhantomJSReference;
import com.github.jarlakxen.embedphantomjs.executor.PhantomJSFileExecutor;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by haswell on 1/31/15.
 */
public class EmbeddedPhantomJsTest {

    @Test
    public void ensureWhatever() throws ExecutionException, InterruptedException {
        PhantomJSDriver driver = new PhantomJSDriver();
        driver.manage().timeouts().setScriptTimeout(1000l, TimeUnit.SECONDS);
        driver.get("http://stats.nba.com/tracking/#!/player/catchshoot/");

    }
}
