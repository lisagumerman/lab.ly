package io.hasli.maelstrom.transport.framing;


/**
 * Created by haswell on 2/15/16.
 *
 * Interface describing how to map a Maelstrom operation
 */
public interface Frame {

    /**
     * Does the frame contain an invocation payload or a stream payload?
     */
    enum Type {

        /**
         * Is it a stream request?
         */
        Stream(0),
        /**
         * Is it an operation invocation?
         */
        Invocation(1);


        public static final int location = 1;

        final int value;


        Type(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }



    /**
     * The type of this frame
     * @return
     */

    Type getType();




}
