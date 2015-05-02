package com.donnellpetersonexperience.board;

import com.donnellpetersonexperience.tetrominos.Block;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Steven on 2/1/2015.
 */
public class Board extends Canvas{
    private final int GAME_WIDTH = 10;
    private final int GAME_HEIGHT = 22;
    private final int SCREEN_HEIGHT = 20;
    private final int BLOCK_SIZE = 20;
    private final int BOARD_WIDTH = GAME_WIDTH * BLOCK_SIZE;
    private final int BOARD_HEIGHT = SCREEN_HEIGHT * BLOCK_SIZE;


    public Block[][] board = new Block[GAME_WIDTH][GAME_HEIGHT];

    public void update(){
        checkLines();
    }

    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.clearRect(0,0,getWidth(),getHeight());

        {//Do rendering stuff here.
            g.setColor(new Color(0x6D6D6D));
            for(int x = 0; x < BOARD_WIDTH; x+=BLOCK_SIZE)
                for(int y = 0; y < BOARD_HEIGHT; y+=BLOCK_SIZE)
                    g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);

            for(Block[] blocks : board){
                for(Block block : blocks){
                    if(block != null) {
                        g.setColor(block.getBlockColor());
                        g.fillRect(
                                block.getXPos() * BLOCK_SIZE,
                                block.getYPos() * BLOCK_SIZE,
                                BLOCK_SIZE, BLOCK_SIZE);

                        g.setColor(Color.WHITE);
                        g.drawRect(
                                block.getXPos() * BLOCK_SIZE,
                                block.getYPos() * BLOCK_SIZE,
                                BLOCK_SIZE, BLOCK_SIZE);
                    }
                }
            }
        }

        g.dispose();
        bs.show();
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
