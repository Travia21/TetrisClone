package com.donnellpetersonexperience.tetrominos;

/**
 * Base representation of a Tetromino.  Each specific tetromino must extend this
 * abstract class.
 *
 * @author Zach & Steven
 *
 *		* * * * *
 *		*		*
 *      *   1	*
 *      *		*
 *      * * * * *
 *		*		*
 *      *   2	*
 *      *		*
 *      * * * * *				* * * * *				* * * * * * * * *
 *		*		*				*		*				*		*		*
 *      *   0	*				*	1	*				*	0	*	1	*
 *      *		*				*		*				*		*		*
 *      * * * * *		* * * * * * * * * * * * *		* * * * * * * * *
 *		*		*		*		*		*		*		*		*		*
 *      *   3	*		*	2	*	0	*	3	*		*	2	*	3	*
 *      *		*		*		*		*		*		*		*		*
 *      * * * * *		* * * * * * * * * * * * *		* * * * * * * * *
 *
 *
 *
 *      * * * * *										* * * * *
 *      *		*										*		*
 *      *	1	*										*	1	*
 *      *		*										*		*
 *      * * * * * * * * * * * * *		* * * * * * * * * * * * *
 *		*		*		*		*		*		*		*		*
 *      *	0	*	2	*	3	*		*	3	*	2	*	0	*
 *      *		*		*		*		*		*		*		*
 *      * * * * * * * * * * * * *		* * * * * * * * * * * * *
 *
 *
 *
 *      * * * * * * * * *						* * * * * * * * *
 *      *		*		*						*		*		*
 *      *	1	*	0	*						*	1	*	2	*
 *      *		*		*						*		*		*
 *      * * * * * * * * * * * * * 		* * * * * * * * * * * * *
 *      		*		*		*		*		*		*
 *      		*	2	*	3	*		*	3	*	0	*
 *      		*		*		*		*		*		*
 *      		* * * * * * * * *		* * * * * * * * *
 */

public abstract class Tetromino {
    //NESW tells you which direction the tetromino is facing.
    protected enum State { NORTH, EAST, SOUTH, WEST }

    public static final int SEGMENT_COUNT = 4;

    public static final int UP = 0,
                            DOWN = 1,
                            LEFT = 2,
                            RIGHT = 3;

    /*
     * Defines the indices for int[][] blocks
     */
    public static final int 	X = 0,
                                Y = 1;

    protected static final int	BLOCK_0 = 0,
                                BLOCK_1 = 1,
                                BLOCK_2 = 2,
                                BLOCK_3 = 3;

    private char code;

    /**
     * segments[][] makes up the tetromino's individual blocks.
     *
     * downSegments is a flag that determines which segments are facing down.
     * i.e. for a T_Piece that just spawned, blocks 2, 0, and 3 are facing down.
     * So downSegments bits should read 00001011, or 11.
     *
     *
     * The first index represents the tetromino block piece, while
     * the second index specifies the x or y coordinate
     */
    protected final int[][] segments;
    protected int[][] downSegments;
    protected State state;

    /**
     * Constructor
     *
     * @param initSections Tetromino section locations, provided by the extending subclass
     */
    public Tetromino(int[][] initSections, char code) {
        segments = initSections;
        this.code = code;
    }

    /**
     * Attempts to rotate the tetromino left.
     */
    public abstract void rotateLeft();

    /**
     * Attempts to rotate the tetromino right.
     */
    public abstract void rotateRight();

    public abstract void move(int direction);

    public int[][] getSegments() {
        return segments;
    }

    public abstract int[][] getDownSegments();

    public char getCode(){
        return code;
    }

    public String toString(){
        return String.format("Block 0 - X: " + segments[0][0] + " Y: " + segments[0][1]);
    }
}
