package com.donnellpetersonexperience.tetrominos;

import java.awt.*;

/**
 * Created by Steven on 2/1/2015.
 */
public class Block {
    private Color blockColor;
    private int xPos;
    private int yPos;

    public Block(int blockType) {
        switch(blockType) {
            case 0: blockColor = Color.YELLOW;
            case 1: blockColor = Color.CYAN;
            case 2: blockColor = Color.YELLOW;
            case 3: blockColor = Color.CYAN;
            case 4: blockColor = Color.YELLOW;
            case 5: blockColor = Color.CYAN;
            case 6: blockColor = Color.YELLOW;
                default: blockColor = Color.GRAY;
        }
    }

    public void changeX(int pos){
        xPos += pos;
    }

    public void changeY(int pos){
        yPos += pos;
    }
}
