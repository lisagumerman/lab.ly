package io.hasli.maelstrom.transport.framing;

import io.hasli.maelstrom.FileMode;
import io.hasli.maelstrom.transport.Encoding;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by haswell on 2/21/16.
 */
public class FramesTest {


    @Test
    public void ensureEncodingWithValueOfZeroReturnsRaw() {
        byte encoding = 0b0000001;
        Encoding e = Frames.parseEncoding(new byte[]{encoding});
        assertThat(e, is(Encoding.RAW));
    }

    @Test
    public void ensureEncodingWithValueOfOneReturnsUTF8() {
        byte encoding = 0b0000011;
        Encoding e = Frames.parseEncoding(new byte[]{encoding});
        assertThat(e, is(Encoding.UTF8));
    }

    @Test
    public void ensureEncodingWithvalueOfTwoReturnsUTF16() {
        byte encoding = 0b0000101;
        Encoding e = Frames.parseEncoding(new byte[]{encoding});
        assertThat(e, is(Encoding.UTF16));
    }



    @Test
    public void ensureEncodingWithValueOfZeroReturnsRawIgnoringTrailingBit() {
        byte encoding = 0b0000000;
        Encoding e = Frames.parseEncoding(new byte[]{encoding});
        assertThat(e, is(Encoding.RAW));
    }

    @Test
    public void ensureEncodingWithValueOfOneReturnsUTF8IgnoringTrailingBit() {
        byte encoding = 0b0000010;
        Encoding e = Frames.parseEncoding(new byte[]{encoding});
        assertThat(e, is(Encoding.UTF8));
    }

    @Test
    public void ensureEncodingWithvalueOfTwoReturnsUTF16IgnoringTrailingBit() {
        byte encoding = 0b0000100;
        Encoding e = Frames.parseEncoding(new byte[]{encoding});
        assertThat(e, is(Encoding.UTF16));
    }


    @Test
    public void ensureParseTypeOnValueWithTralingBitOfZeroReturnsStream() {
        byte encoding = 0b000000000;
        Frame.Type t = Frames.parseType(new byte[]{encoding});
        assertThat(t, is(Frame.Type.Stream));
    }

    @Test
    public void ensureParseTypeOnValueWithTrailingBitOfOneReturnsInvocation() {
        byte encoding = 0b01010101;
        Frame.Type t = Frames.parseType(new byte[]{encoding});
        assertThat(t, is(Frame.Type.Invocation));
    }

    @Test
    public void ensureParseTypeOnValueWithTrailingBitOfOneReturnsInvocationIgnoringLeading() {
        byte encoding = 0b1010001;
        Frame.Type t = Frames.parseType(new byte[]{encoding});
        assertThat(t, is(Frame.Type.Invocation));
    }

    @Test
    public void ensureParseTypeOnValueWithTrailingBitOfZeroReturnsInvocationIgnoringLeading() {
        byte encoding = 0b1010010;
        Frame.Type t = Frames.parseType(new byte[]{encoding});
        assertThat(t, is(Frame.Type.Stream));
    }


    @Test
    public void ensureParseTypeOnValueWithTrailingBitOfOneReturnsInvocation_1() {
        byte encoding = 0b1010011;
        Frame.Type t = Frames.parseType(new byte[]{encoding});
        assertThat(t, is(Frame.Type.Invocation));
    }

    @Test
    public void ensureParseTypeOnValueWithTrailingBitOfOneReturnsStream() {
        byte encoding = 0b1011010;
        Frame.Type t = Frames.parseType(new byte[]{encoding});
        assertThat(t, is(Frame.Type.Stream));
    }


    @Test
    public void ensureParseVersionOnTwoPositiveValuesProducesExpectedResults() {
        byte[] frame = {1, 1, 1};
        assertThat(Frames.parseVersion(frame), is(2));
    }

    @Test
    public void ensureParseVersionOnTwoPositiveDifferentValuesProducesExpectedResults() {
        byte[] frame = {1, 1, 4};
        assertThat(Frames.parseVersion(frame), is(5));
    }

    @Test
    public void ensureOperationTypeIsExtractableFromFrame() {
        byte[] frame = {1, 2, 3, 0b1000000}; FileMode mode = Frames.parseFileMode(frame);
        assertThat(mode, is(FileMode.R));
    }

    @Test
    public void ensureOperationTypeIsExtractableFromFrame_RB() {
        byte[] frame = {1, 2, 3, 0b1};
        FileMode mode = Frames.parseFileMode(frame);
        assertThat(mode, is(FileMode.RB));
    }

    @Test
    public void ensureOperationTypeIsExtractableFromFrame_RW() {
        byte[] frame = {1, 2, 3, 0b1000010};
        FileMode mode = Frames.parseFileMode(frame);
        assertThat(mode, is(FileMode.RW));
    }



    @Test
    public void ensureOperationTypeIsExtractableFromFrame_AB() {
        byte[] frame = {1, 2, 3, 0b1001};
        FileMode mode = Frames.parseFileMode(frame);
        assertThat(mode, is(FileMode.AB));
    }

    @Test
    public void ensureOperationTypeIsExtractableFromFrame_ARB() {
        byte[] frame = {1, 2, 3, 0b1001011};
        FileMode mode = Frames.parseFileMode(frame);
        assertThat(mode, is(FileMode.ARB));
    }

    @Test
    public void ensureOffsetIsPresentWhenLeadingDigitIsOne() {
        byte[] frame = {1,2,3, 0b1000000};
        assertThat(Frames.isOffsetPresent(frame), is(true));
    }

    @Test
    public void ensureOffsetIsNotPresentWhenLeadingDigitIsZero() {
        byte[] frame = {1,2,3, 0b100000};
        assertThat(Frames.isOffsetPresent(frame), is(false));
    }


}