package io.hasli.maelstrom.transport.framing;
import io.hasli.maelstrom.InvalidFrameException;
import io.hasli.maelstrom.transport.Encoding;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
/**
 * Created by haswell on 2/20/16.
 */
public class FrameTest {


    @Test
    public void ensureFrameStreamValueIsCorrect() {
        assertThat(Frame.Type.Stream.getValue(), is(0));
    }

    @Test
    public void ensureFrameInvocationValueIsCorrect() {
        assertThat(Frame.Type.Invocation.getValue(), is(1));
    }

}
