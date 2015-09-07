package com.donnellpetersonexperience;

import com.donnellpetersonexperience.board.Board;
import com.donnellpetersonexperience.graphics.GameScreen;
import com.donnellpetersonexperience.input.*;

/**
 * Created by Steven on 7/27/2015.
 */
public class GameTimer implements Runnable {
    private GameScreen gameScreen;
    private Board board;

    public GameTimer(GameScreen gs, Board b){
        gameScreen = gs;
        board = b;
    }

    public void run(){
        // Timing
        boolean running = true;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        //nsPerFrame is 1 billion nanoSeconds divided into 60.
        //This represents the amount of nanoSeconds that should pass between frames. (16,666,666 ns)
        final double nsPerFrame = 1000000000.0 / 60.0;
        //frameDifference is the amount of frames that need to be displayed.
        double frameDifference = 0;
        int updates = 0;
        int frames = 0;
        int avgTotal = 0, avgI = 0, average = 0;

        while(running){
            long now = System.nanoTime();
            int delta = (int) (now - lastTime);
            //The number of frames to display is calculated by dividing the frame time difference by nsPerFrame.
            frameDifference += delta / nsPerFrame;
            lastTime = now;

            while (frameDifference >= 1) {
                //Do stuff here
                {
                    board.update(System.currentTimeMillis());
                    gameScreen.update();
                }
                //End stuff

                frames++;
                frameDifference--;
            }

            updates++;

            if(System.currentTimeMillis() - timer > 1000) {
                avgI++;
                timer += 1000;
                avgTotal += updates;
                average = avgTotal/avgI;
                System.out.println(frames +  " FPS, " + updates + " UPS, " + average + " average UPS.");
                frames = 0;
                updates = 0;
            }
        }
    }
}
