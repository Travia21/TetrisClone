package com.donnellpetersonexperience;

import com.donnellpetersonexperience.board.Board;
import com.donnellpetersonexperience.input.Keyboard;
import com.donnellpetersonexperience.input.Mouse;
import com.donnellpetersonexperience.tetrominos.Block;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Steven on 2/1/2015.
 */
public class Tetris{
    private static int width = 800;
    private static int height = 600;
    private static int scale = 2;

    private final static Color REAL_ORANGE = new Color(255, 120, 0);
    private final static String title = "TetrisClone";

    private JPanel startScreen, gameScreen;
    final static String START = "Start Screen", GAME = "Game Screen";

    private JPanel screen;

    private static Keyboard kb;
    private static Mouse mouse;

    private int xPos = 0, yPos = 0;
    private boolean running = false;

    /**
     * Starts by setting the position of the first Block. Then handles timing and screen refreshing.
     *
     * @param gameScreen the frame the game runs in.
     */
    private void begin(JPanel gameScreen) {
        Board board = new Board();
        gameScreen.add(board);
        board.board[0][0] = new Block(2);
        board.board[0][0].setXPos(5);
        board.board[0][0].setYPos(10);
        board.setBackground(Color.BLACK);

        running = true;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        /*
        nsPerFrame is 1 billion nanoSeconds divided into 60.
        This represents the amount of nanoSeconds that should pass between frames. (16,666,666 ns)
         */
        final double nsPerFrame = 1000000000.0 / 60.0;
        double frameDifference = 0;

        int updates = 0;
        int frames = 0;
        int avgTotal = 0, avgI = 0, average = 0;
        int bxp = board.board[0][0].getXPos(), byp = board.board[0][0].getYPos();

        while(running){
            long now = System.nanoTime();
            /*
            delta adds the ns difference between frames, then determines if the difference is greater than
            nsPerFrame.
             */
            frameDifference += (now - lastTime) / nsPerFrame;
            lastTime = now;

            while (frameDifference >= 1) {
                //Do stuff here
                update();

                bxp += xPos;
                byp += yPos;

                board.board[0][0].setXPos(bxp);
                board.board[0][0].setYPos(byp);

                board.render();

                frames++;
                frameDifference--;
            }

            updates++;

            if(System.currentTimeMillis() - timer > 1000) {
                avgI++;
                timer += 1000;
                avgTotal += updates;
                average = avgTotal/avgI;
                System.out.println(frames +  " UPS, " + updates + " FPS, " + average + " average FPS.");
                frames = 0;
                updates = 0;
            }
        }
    }

    /**
     * Handles the positioning of the active Block.
     */
    private void update(){
        kb.update();
        if(kb.up) yPos = -1;
        if(kb.down) yPos = 1;
        if(!kb.up && !kb.down) yPos = 0;

        if(kb.left) xPos = -1;
        if(kb.right) xPos = 1;
        if(!kb.left && !kb.right) xPos = 0;

        if(kb.rotateLeft){
            running = false;
        }
    }

    private void addComponents(Container pane){
        screen = new JPanel(new CardLayout());
        initiateGameScreen();
        initiateStartScreen();

        screen.add(startScreen, START);
        screen.add(gameScreen, GAME);

        pane.add(screen, BorderLayout.CENTER);
    }

    /**
     * Handles the screen's display on the frame and registers the mouse and keyboard listeners.
     */
    private static void createAndShowGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setTitle("Tetris Clone");
        frame.setVisible(true);

        try{
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch(Exception ex) {
            ex.printStackTrace();
        }

        kb = new Keyboard();
        frame.addKeyListener(kb);
        mouse = new Mouse();
        frame.addMouseListener(mouse);

        Tetris tetris = new Tetris();
        tetris.addComponents(frame.getContentPane());

        frame.pack();
    }

    private void initiateStartScreen(){
        startScreen = new JPanel();
        startScreen.setName(START);
        startScreen.setBackground(Color.BLACK);

        JTextArea startText = new JTextArea("Hello Zachary.");
        JButton button = new JButton("Button");
        button.addActionListener(e -> swapScreens(gameScreen));

        startScreen.add(startText, BorderLayout.NORTH);
        startScreen.add(button, BorderLayout.SOUTH);
    }

    private void initiateGameScreen(){
        gameScreen = new JPanel();
        gameScreen.setName(GAME);

        begin(gameScreen);
    }

    private void swapScreens(JPanel nextScreen){
        String name;

        if(nextScreen.getName().equals(GAME)){
            name = GAME;
        }else{
            name = START;
        }

        CardLayout cardLayout = (CardLayout)screen.getLayout();
        cardLayout.show(screen, name);
    }

    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
