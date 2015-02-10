package com.donnellpetersonexperience;

import com.donnellpetersonexperience.board.Board;
import com.donnellpetersonexperience.input.Keyboard;
import com.donnellpetersonexperience.input.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Steven on 2/1/2015.
 */
public class Tetris extends Canvas{
    private static int width = 400;
    private static int height = (width / 16) * 9;
    private static int scale = 2;

    private final static Color REAL_ORANGE = new Color(255, 120, 0);

    public static String title = "TetrisClone";
    private boolean running;

    private JFrame frame;

    private Keyboard kb;
    private Mouse mouse;

    private int xPos = 100, yPos = 100;

    private Board board;

    public Tetris() {
        setBackground(Color.BLACK);

        frame = new JFrame();
        kb = new Keyboard();
        mouse = new Mouse();
        board = new Board();

        addKeyListener(kb);
        addMouseListener(mouse);
    }

    public static void main(String[] args){
        Tetris tetris = new Tetris();

        tetris.frame.setSize(300, 300);
        tetris.frame.add(tetris);
        tetris.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tetris.frame.setVisible(true);

        tetris.begin();

        System.exit(1);
    }

    public synchronized void begin() {
        running = true;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        int avgTotal = 0, avgI = 0, average = 0;

        requestFocus();
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
            //Do stuff here
                update();
                render();

                updates++;
                delta--;
            }

            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                avgI++;
                timer += 1000;
                avgTotal += frames;
                average = avgTotal/avgI;
                System.out.println(updates +  " UPS, " + frames + " FPS, " + average + " average FPS.");
                frame.setTitle(title + "  |  " + Integer.toString(frames));
                updates = 0;
                frames = 0;
            }
        }
    }

    public void update(){
        kb.update();
        if(kb.up) yPos = 50;
        if(kb.down) yPos = 150;
        if(!kb.up && !kb.down) yPos = 100;

        if(kb.left) xPos = 50;
        if(kb.right) xPos = 150;
        if(!kb.left && !kb.right) xPos = 100;

        if(kb.rotateLeft){
            running = false;
        }
    }

    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        {
            //Do rendering stuff here.
            g.clearRect(0,0,getWidth(),getHeight());
            g.setColor(REAL_ORANGE);
            board.render(g, xPos, yPos);
        }
        g.dispose();
        bs.show();
    }
}
