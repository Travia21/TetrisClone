package com.donnellpetersonexperience.board;

import com.donnellpetersonexperience.input.Keyboard;
import com.donnellpetersonexperience.input.Mouse;
import com.donnellpetersonexperience.tetrominos.*;

import java.awt.*;
import java.util.Arrays;

/**
 * This is the main game state class.  It keeps track of the board state,
 * as well as altering the board/pieces on input.
 *
 * @author Zach & Steven
 *
 */
public class Board {
    private static final int X = Tetromino.X, Y = Tetromino.Y;

    private final int COLUMNS = 10;
    private final int ROWS = 22;
    private final int VISIBLE_ROWS = 20;
    private final int BLOCK_SIZE = 20;

    public Tetromino currentTetromino = null;

    private Keyboard kb;
    private Mouse mouse;

    public char[][] board = new char[COLUMNS][ROWS];

    public Board(Keyboard key, Mouse mse) {
        kb = key;
        mouse = mse;

        // fill the board with empty spots
        for(int y = 0; y < COLUMNS; y++){
            Arrays.fill(board[y], ' ');
        }

        currentTetromino = spawnPiece();
    }

    public char[][] getBoard() {
        return board;
    }

    public int getColumns(){
        return COLUMNS;
    }

    public int getRows(){
        return ROWS;
    }

    public int getVisibleRows(){
        return VISIBLE_ROWS;
    }

    public int getBlockSize() {
        return BLOCK_SIZE;
    }

    private long lastMoveTime = 0, moveTimer = 0;
    /**
     * Update handles changes to the board's state, including gravity, spawning next tetromino, and initiating line
     * clear checks.
     *
     * @param callTime Determines whether the tetromino should moveDown another row based on gravity setting.
     */
    public void update(long callTime) {
        if(currentTetromino == null)
            currentTetromino = spawnPiece();

        kb.update();

        if(callTime - moveTimer > 100){
            moveTimer = callTime;
            if(kb.up) move(Tetromino.UP);
            else if(kb.down) moveDown();

            if(kb.left){
                move(Tetromino.LEFT);
                kb.left = false;
            } else if(kb.right){
                move(Tetromino.RIGHT);
                kb.right = false;
            }

            if(kb.rotateLeft)
                rotate(0);
            else if(kb.rotateRight)
                rotate(1);
        }

        if(kb.pause || kb.hold) System.exit(2);

        if(callTime - lastMoveTime > 800) {
            lastMoveTime = callTime;
            moveDown();
        }

        placeCurrentTetromino();
    }

    public void checkLines(){
        boolean full = false;
        int rowNum = 0;
        for(char[] i : board){
            for(char block : i){
                full = true;
                if(block == ' '){
                    full = false;
                    break;
                }
            }
            if(full) clearLine(rowNum);
            rowNum++;
        }
    }

    public char getCurrentTetromino(){
        return currentTetromino.getCode();
    }

    public void clearLine(int rowNum){
        for(int i = 0; i < COLUMNS; i++){
            board[i][rowNum] = ' ';
        }

        for(int c = 0; c < COLUMNS; c++){
            for(int r = rowNum; r < ROWS; r++){
                board[c][r-1] = board[c][r];
            }
            board[c][ROWS] = ' ';
        }
    }

    /**
     * Generates the next random Tetromino at the top of the board.
     *
     * @return The type of tetromino to be spawned.
     */
    private Tetromino spawnPiece() {
        return new T_Piece();
        /*int random = (int) (Math.random() * 7);

		switch (random) {
		case 0:
			return new I_Piece();
		case 1:
			return new O_Piece();
		case 2:
			return new T_Piece();
		case 3:
			return new S_Piece();
		case 4:
			return new Z_Piece();
		case 5:
			return new J_Piece();
		case 6:
			return new L_Piece();
		default:
			return new I_Piece();
		}*/
    }

    /**
     * Generates a specific Tetromino at the top of the board.
     *
     * @return The type of tetromino to be spawned.
     */
    public Tetromino spawnPiece(char c) {
		switch (c) {
		/*case 'i':
			return new I_Piece();
		case 'o':
			return new O_Piece();*/
		case 't':
			return new T_Piece();
		/*case 's':
			return new S_Piece();
		case 'z':
			return new Z_Piece();
		case 'j':
			return new J_Piece();
		case 'l':
			return new L_Piece();*/
		default:
			return new T_Piece();
		}
    }

    public Color getBlockColor(char c){
        switch(c){
            case 'i':
                return Color.CYAN;
            case 'o':
                return Color.YELLOW;
            case 't':
                return Color.MAGENTA;
            case 's':
                return Color.GREEN;
            case 'z':
                return Color.RED;
            case 'j':
                return Color.ORANGE;
            case 'l':
                return Color.BLUE;
            default:
                return Color.MAGENTA;
        }
    }

    public void placeCurrentTetromino(){
        int[][] segments = currentTetromino.getSegments();
        for(int[] segment : segments){
            board[segment[0]][segment[1]] = currentTetromino.getCode();
        }
    }

    /**
     * Moves the piece down, depending on lock conditions.
     */
    public void move(int direction){
        int[][] segments = currentTetromino.getSegments();
        switch(direction){
            case Tetromino.LEFT:
                for(int[] segment : segments){
                    if(segment[X] <= 0){
                        return;
                    }
                }
                clearPreviousPos(segments);
                currentTetromino.move(Tetromino.LEFT);
                break;
            case Tetromino.RIGHT:
                for(int[] segment : segments){
                    if(segment[X] >= 9){
                        return;
                    }
                }
                clearPreviousPos(segments);
                currentTetromino.move(Tetromino.RIGHT);
                break;
            case Tetromino.DOWN:
                clearPreviousPos(segments);
                moveDown();
                break;
            case Tetromino.UP:
                clearPreviousPos(segments);
                currentTetromino.move(Tetromino.UP);
                break;
            default:
                System.out.println("Error: move() specified without direction.");
        }
    }

    /**
     * Moves the piece down, depending on lock conditions.
     */
    public void moveDown() {
		/*
		 * Loops through each second dimension part of the segments[][] array.
		 * Then checks to see if the tetromino should be placed and spawn a new one.
		 * If not, moves the tetromino into the new position.
		 *
		 * i.e. segments[Loop Here][When this is the specified value] and increment or decrement.
		 */

        int[][] segments = currentTetromino.getSegments();
        int[][] downSegments = currentTetromino.getDownSegments();
        boolean lockCondtion = false;

        //Check for lock condition
        for (int[] segment : downSegments) {
            if(segment[Y] == ROWS-1 || board[segment[X]][segment[Y] + 1] != ' ')
                lockCondtion = true;
        }

        if(!lockCondtion) {
            clearPreviousPos(segments);
            currentTetromino.move(Tetromino.DOWN);
        }

        //Locks the piece in place by spawning a new tetromino
        if(lockCondtion)
            currentTetromino = spawnPiece();
    }

    private void rotate(int direction){
        int[][] segments = currentTetromino.getSegments();
        if(direction == 0){
            clearPreviousPos(segments);
            currentTetromino.rotateLeft();
        }
        else if(direction == 1){
            clearPreviousPos(segments);
            currentTetromino.rotateRight();
        }
    }

    /**
     * Clears the board of the current Tetromino's segments, allowing proper movement.
     *
     * @param segments the positions of the current tetromino.
     */
    private void clearPreviousPos(int[][] segments){
        for (int[] segment : segments)
            board[segment[X]][segment[Y]] = ' ';
    }
}
