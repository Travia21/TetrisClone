package com.donnellpetersonexperience.tetrominos;

import java.awt.*;

/**
 * Created by Steven on 2/1/2015.
 */
public class Block {
    private Color blockColor;
    private int blockType;
    private int xPos;
    private int yPos;

    public Block(int bType) {
        blockType = bType;
        switch(blockType) {
            case 0:
                blockColor = Color.YELLOW;
                break;
            case 1:
                blockColor = Color.CYAN;
                break;
            case 2:
                blockColor = new Color(103, 5, 255);
                break;
            case 3:
                blockColor = Color.BLUE;
                break;
            case 4:
                blockColor = Color.ORANGE;
                break;
            case 5:
                blockColor = Color.GREEN;
                break;
            case 6:
                blockColor = Color.RED;
                break;
                default:
                    blockColor = Color.GRAY;
                    break;
        }
    }

    public void setXPos(int pos){
        xPos = pos;
    }

    public void setYPos(int pos){
        yPos = pos;
    }

    public int getXPos(){ return xPos; }

    public int getYPos(){ return yPos; }

    public Color getBlockColor(){ return blockColor; }

    public int getBlockType(){ return blockType; }
}
