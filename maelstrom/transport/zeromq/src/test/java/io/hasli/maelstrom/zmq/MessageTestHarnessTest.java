package io.hasli.maelstrom.zmq;

import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by haswell on 2/20/16.
 */
public class MessageTestHarnessTest extends MessageTestHarness {

    public MessageTestHarnessTest() {
        super(55555, 1);
    }



    @Test
    public void ensureSendingWorks() {
        setUp();
        byte[][] bytes = send("hello".getBytes(), "world".getBytes(), "How".getBytes());
        assertThat(bytes.length, is(3));
        tearDown();
    }

}