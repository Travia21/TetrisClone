package com.donnellpetersonexperience.graphics;

import com.donnellpetersonexperience.board.Board;
import com.donnellpetersonexperience.tetrominos.Tetromino;

import javax.swing.*;
import java.awt.*;

/**
 * GameScreen deals with the display of the game.
 *
 * Created by Steven on 6/27/2015.
 */
public class GameScreen extends JPanel{
    private Board board;
    private final int BOARD_OFFSET = 20;

    char[][] blocks;

    int columns, rows, visibleRows, blockOffset, blockSize, xPos, yPos;

    public GameScreen(Board b){
        this.board = b;
        columns = board.getColumns();
        rows = board.getRows();
        visibleRows = board.getVisibleRows();
        blockOffset = rows-visibleRows;
        blockSize = board.getBlockSize();
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(800, 600);
    }

    public void update() {
        revalidate();
        repaint();
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        blocks = board.getBoard();

        //Paint game board's grey background
        g.setColor(new Color(100,100,100));
        g.fillRect(BOARD_OFFSET, BOARD_OFFSET, columns * blockSize, visibleRows * blockSize);

        //Paint game board's vertical orange stripes
        g.setColor(new Color(155, 90, 50));
        for(int verticalLine = 0; verticalLine <= columns; verticalLine++){
            g.drawLine(verticalLine * blockSize + BOARD_OFFSET, BOARD_OFFSET,
                    verticalLine * blockSize + BOARD_OFFSET, visibleRows * blockSize + BOARD_OFFSET);
        }

        //Paint game board's horizontal orange stripes
        for(int horizontalLine = 0; horizontalLine <= visibleRows; horizontalLine++){
            g.drawLine(BOARD_OFFSET, horizontalLine * blockSize + BOARD_OFFSET,
                    columns * blockSize + BOARD_OFFSET, horizontalLine*blockSize + BOARD_OFFSET);
        }

        //Paint all the blocks
        for(int x = 0; x < columns; x++)
            for (int y = 2; y < rows; y++) {
                char block = blocks[x][y];

                xPos = x * blockSize + BOARD_OFFSET;
                yPos = (y - blockOffset) * blockSize + BOARD_OFFSET;

                //Paints any non-empty blocks
                if (block != ' ') {
                    g.setColor(board.getBlockColor(block));
                    g.fillRect( xPos,
                                yPos,
                                blockSize,
                                blockSize);

                    g.setColor(new Color(0xFFFFFF));
                    g.drawRect( xPos,
                                yPos,
                                blockSize,
                                blockSize);
                }
            }

        g.dispose();
    }
}
