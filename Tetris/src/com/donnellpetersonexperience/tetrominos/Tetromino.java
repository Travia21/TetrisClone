package com.donnellpetersonexperience.tetrominos;

/**
 * Created by Steven on 2/1/2015.
 *
 * O - 0
 * I - 1
 * T - 2
 *
 * L - 3
 * J - 4
 *
 * S - 5
 * Z - 6
 */
public abstract class Tetromino {
    protected int type;

    private Block[] blocks = new Block[4];

    public void fall(){
        for(Block block : blocks)
            block.changeY(1);
    }

    public void drop(){

    }

    public void moveLeft(){
        for(Block block : blocks)
            block.changeX(-1);
    }

    public void moveRight(){
        for(Block block : blocks)
            block.changeX(1);
    }

    public void rotateLeft(){}
    public void rotateRight(){}
    public void rotateFlip(){}
}
