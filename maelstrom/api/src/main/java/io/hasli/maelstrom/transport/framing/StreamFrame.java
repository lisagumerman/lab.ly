package io.hasli.maelstrom.transport.framing;


/**
 * Created by haswell on 2/20/16.
 */
public interface StreamFrame extends Frame {

    /**
     * Where
     */

    enum Lifecycle {
        /**
         * Open a stream
         */
        Initialize,

        /**
         * Progress a stream
         */

        Progress,

        /**
         * Close a stream
         */

        Finalize
    }

}
