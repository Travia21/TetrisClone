package com.donnellpetersonexperience.input;

/**
 * Created by Steven on 3/21/2015.
 */
public class Settings {
    private static int GAME_SPEED;

    public boolean setGameSpeed(int GameSpeed){
        if(GameSpeed > 0 && GameSpeed <= 10){
            GAME_SPEED = GameSpeed;
            return true;
        }
        return false;
    }

    public int getGameSpeed(){
        return GAME_SPEED;
    }
}
