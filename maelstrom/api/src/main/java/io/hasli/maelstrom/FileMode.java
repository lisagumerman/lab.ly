package io.hasli.maelstrom;

/**
 * Created by haswell on 2/21/16.
 */
public enum FileMode {



    /**
     * Open file for reading only
     */
    R(0),

    /**
     * Open file for reading binary
     */

    RB(1),

    /**
     * Open file for reading and writing
     */

    RW(2),

    /**
     * Open file for reading and writing binary
     */

    RWB(3),

    /**
     * Open file for writing only
     */

    W(4),

    /**
     * Open file for writing binary only
     */

    WB(5),

    /**
     * Open file in read-write mode
     */

    WR(6),

    /**
     * Open file in write-read-binary mode
     */

    WRB(7),

    /**
     * Open file in append mode
     */

    A(8),

    /**
     * Open file in append binary mode
     */

    AB(9),

    /**
     * Open file in append-read mode
     */

    AR(10),

    /**
     * Open file in append-read-binary mode
     */
    ARB(11);


    final int value;

    FileMode(int value) {
        this.value = value;
    }


    public static FileMode getValue(int value) {
        switch(value) {
            case 0 : return FileMode.R;
            case 1 : return FileMode.RB;
            case 2 : return FileMode.RW;
            case 3 : return FileMode.RWB;
            case 4 : return FileMode.W;
            case 5 : return FileMode.WB;
            case 6 : return FileMode.WR;
            case 7 : return FileMode.WRB;
            case 8 : return FileMode.A;
            case 9 : return FileMode.AB;
            case 10: return FileMode.AR;
            case 11: return FileMode.ARB;
        }
        throw new InvalidFrameException("Invalid file mode: " + value);
    }

}
