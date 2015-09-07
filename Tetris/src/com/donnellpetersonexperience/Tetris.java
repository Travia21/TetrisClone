package com.donnellpetersonexperience;

import com.donnellpetersonexperience.board.Board;
import com.donnellpetersonexperience.graphics.GameScreen;
import com.donnellpetersonexperience.input.Keyboard;
import com.donnellpetersonexperience.input.Mouse;

import javax.swing.*;
import java.awt.*;

/**
 * The main class primarily handles the switching between game states (i.e. main menu, the game, options...)
 *
 * Created by Steven on 2/1/2015.
 */
public class Tetris{
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    /**
     * TODO Documentation
     */
    private static void createAndShowGUI() {
        final JFrame frame = new JFrame();
        final Keyboard kb = new Keyboard();
        final Mouse mouse = new Mouse();
        final Board board = new Board(kb, mouse);
        final GameScreen gameScreen = new GameScreen(board);

        frame.setLayout(new BorderLayout());
        frame.addKeyListener(kb);

        final Runnable r = new GameTimer(gameScreen, board);
        final Thread t = new Thread(r);
        t.start();

        frame.getContentPane().add(gameScreen);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
