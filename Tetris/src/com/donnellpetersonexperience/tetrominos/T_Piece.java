package com.donnellpetersonexperience.tetrominos;

/**
 * Piece that looks like:
 *
 *      		* * * * *               * * * * * * * * * * * * *               * * * * *       * * * * *
 *				*		*               *		*		*		*               *		*       *		*
 *      		*	1	*               *	3	*	0	*	2	*               *	3	*       *	2	*
 *      		*		*               *		*		*		*               *		*       *		*
 *      * * * * * * * * * * * * *       * * * * * * * * * * * * *       * * * * * * * * *       * * * * * * * * *
 *		*		*		*		*               *		*               *		*		*       *		*		*
 *      *	2	*	0	*	3	*               *	1	*               *	1	*	0	*       *	0	*	1	*
 *      *		*		*		*               *		*               *		*		*       *		*		*
 *      * * * * * * * * * * * * *               * * * * *               * * * * * * * * *       * * * * * * * * *
 *                                                                              *		*       *		*
 *                                                                              *	2	*       *	3	*
 *                                                                              *		*       *		*
 *                                                                              * * * * *       * * * * *
 *                SOUTH                         NORTH                           EAST            WEST
 *
 * I'm really hoping I can get away without having to explain all the Tetrominos...
 *
 * @author Steven
 */
public class T_Piece extends Tetromino {
    /**
     * The extension's constructor determines the initial location of each of the tetromino's segments.
     */
    public T_Piece() {
        super(new int[4][2], 't');

        segments[BLOCK_0][X] = 4;
        segments[BLOCK_1][X] = 4;
        segments[BLOCK_2][X] = 3;
        segments[BLOCK_3][X] = 5;

        segments[BLOCK_0][Y] = 2;
        segments[BLOCK_1][Y] = 1;
        segments[BLOCK_2][Y] = 2;
        segments[BLOCK_3][Y] = 2;

        state = State.SOUTH;
    }

    /**
     * Changes each segment's X and Y positions in a left rotation.
     *
     * TODO Determine if rotation is possible
     * TODO Add special rotation
     * TODO Add advanced rotation rules (T-Spin triples)
     */
    @Override
    public void rotateLeft() {
        switch (state) {
            case NORTH:
                state = state.WEST;
                break;
            case EAST:
                state = state.NORTH;
                break;
            case SOUTH:
                state = state.EAST;
                break;
            case WEST:
                state = state.SOUTH;
                break;
            default:
                System.out.println("Rotation error in T_Piece, state not specified.");
        }

        //Regular
        segments[BLOCK_1][X] -= 1;
        segments[BLOCK_2][X] += 1;
        segments[BLOCK_3][X] -= 1;

        segments[BLOCK_1][Y] += 1;
        segments[BLOCK_2][Y] += 1;
        segments[BLOCK_3][Y] -= 1;
    }

    /**
     * Changes each segment's X and Y positions in a right rotation.
     *
     * TODO Determine if rotation is possible
     * TODO Add special rotation
     * TODO Add advanced rotation rules (T-Spin triples)
     */
    @Override
    public void rotateRight() {
        switch(state){
        case NORTH:
            state = state.EAST;
            break;
        case EAST:
            state = state.SOUTH;
            break;
        case SOUTH:
            state = state.WEST;
            break;
        case WEST:
            state = state.NORTH;
            break;
        default:
            System.out.println("Rotation error in T_Piece, state not specified.");
        }

        //Regular
        segments[BLOCK_1][X] += 1;
        segments[BLOCK_2][X] += 1;
        segments[BLOCK_3][X] -= 1;

        segments[BLOCK_1][Y] += 1;
        segments[BLOCK_2][Y] -= 1;
        segments[BLOCK_3][Y] += 1;
    }

    public void move(int direction) {
		/*
		 * Loops through each second dimension part of the blocks[][] array.
		 *
		 * i.e. blocks[Loop Here][When this is the specified value] and increment or decrement.
		 */
        for(int[] seg : segments) {
            switch (direction) {
                case UP:
                    seg[Y] -= 1;
                    break;
                case DOWN:
                    seg[Y] += 1;
                    break;
                case LEFT:
                    seg[X] -= 1;
                    break;
                case RIGHT:
                    seg[X] += 1;
                    break;
            }
        }
    }

    public int[][] getDownSegments(){
        switch (state){
            case NORTH:
                downSegments = new int[3][2];
                downSegments[BLOCK_0][X] = segments[BLOCK_1][X];
                downSegments[BLOCK_1][X] = segments[BLOCK_2][X];
                downSegments[BLOCK_2][X] = segments[BLOCK_3][X];

                downSegments[BLOCK_0][Y] = segments[BLOCK_1][Y];
                downSegments[BLOCK_1][Y] = segments[BLOCK_2][Y];
                downSegments[BLOCK_2][Y] = segments[BLOCK_3][Y];
                break;
            case EAST:
                downSegments = new int[2][2];
                downSegments[BLOCK_0][X] = segments[BLOCK_1][X];
                downSegments[BLOCK_1][X] = segments[BLOCK_2][X];

                downSegments[BLOCK_0][Y] = segments[BLOCK_1][Y];
                downSegments[BLOCK_1][Y] = segments[BLOCK_2][Y];
                break;
            case SOUTH:
                downSegments = new int[3][2];
                downSegments[BLOCK_0][X] = segments[BLOCK_0][X];
                downSegments[BLOCK_1][X] = segments[BLOCK_2][X];
                downSegments[BLOCK_2][X] = segments[BLOCK_3][X];

                downSegments[BLOCK_0][Y] = segments[BLOCK_0][Y];
                downSegments[BLOCK_1][Y] = segments[BLOCK_2][Y];
                downSegments[BLOCK_2][Y] = segments[BLOCK_3][Y];
                break;
            case WEST:
                downSegments = new int[2][2];
                downSegments[BLOCK_0][X] = segments[BLOCK_1][X];
                downSegments[BLOCK_1][X] = segments[BLOCK_3][X];

                downSegments[BLOCK_0][Y] = segments[BLOCK_1][Y];
                downSegments[BLOCK_1][Y] = segments[BLOCK_3][Y];
                break;
        }

        return downSegments;
    }

    /*
    public boolean[] getDownSegments(){
        boolean[] downSegments = new boolean[4];
        switch (state){
            case NORTH:
                downSegments[0] = false;
                downSegments[1] = true;
                downSegments[2] = true;
                downSegments[3] = true;
            case EAST:
                downSegments[0] = false;
                downSegments[1] = true;
                downSegments[2] = true;
                downSegments[3] = false;
            case SOUTH:
                downSegments[0] = true;
                downSegments[1] = false;
                downSegments[2] = true;
                downSegments[3] = true;
            case WEST:
                downSegments[0] = false;
                downSegments[1] = true;
                downSegments[2] = false;
                downSegments[3] = true;
        }
        return downSegments;
    }*/
}
