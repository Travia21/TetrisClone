package com.donnellpetersonexperience.board;

import com.donnellpetersonexperience.tetrominos.Block;

import java.awt.*;

/**
 * Created by Steven on 2/1/2015.
 */
public class Board {
    private final int WIDTH = 10;
    private final int HEIGHT = 22;
    private final int SCREEN_HEIGHT = 20;
    private Block board[][] = new Block[WIDTH][HEIGHT];

    public void update(){
        checkLines();
    }

    public void render(Graphics g, int xPos, int yPos){
        g.fillRect(xPos, yPos, 100, 100);
    }

    public void checkLines(){
        boolean full = false;
        for(Block[] i : board){
            for(Block block : i){
                full = true;
                if(block == null){
                    full = false;
                    break;
                }
            }
            if(full) clearLine();
        }
    }

    public void clearLine(){

    }
}
