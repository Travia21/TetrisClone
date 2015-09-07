package com.donnellpetersonexperience.input;

/**
 * Settings maintains all the parameters of the game, namely the gravity.
 *
 * In the future, this class will hold all the various settings for the different Tetris versions (gravity, wall kick
 * rules, fast/hard drop rules, etc.
 *
 * Created by Steven on 3/21/2015.
 */
public final class Settings {
    public final static Settings INSTANCE = new Settings();

    private int GAME_SPEED;

    private Settings(){ }

    public boolean setGameSpeed(int GameSpeed){
        if(GameSpeed > 0 && GameSpeed <= 20){
            GAME_SPEED = GameSpeed;
            return true;
        }
        return false;
    }

    public int getGameSpeed(){
        return GAME_SPEED;
    }
}
