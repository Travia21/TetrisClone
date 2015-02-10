package com.donnellpetersonexperience.input;

import com.donnellpetersonexperience.Tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

/**
 * Created by Steven on 2/1/2015.
 */
public class Keyboard implements KeyListener{
    private boolean keys[] = new boolean[524];
    public boolean up, down, left, right, rotateLeft, rotateRight, hold, pause;

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public void update(){
        up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];

        rotateLeft = keys[KeyEvent.VK_Q];
        rotateRight = keys[KeyEvent.VK_E];

        hold = keys[KeyEvent.VK_SPACE];
        pause = keys[KeyEvent.VK_ESCAPE];
    }
}
