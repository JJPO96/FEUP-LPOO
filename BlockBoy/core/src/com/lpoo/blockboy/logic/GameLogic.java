package com.lpoo.blockboy.logic;

import java.util.ArrayList;

/**
 * Created by Manuel Gomes on 12/05/2016.
 */
public class GameLogic {

    private Hero hero;
    private Key key;
    private ArrayList<Block> blocks;
    private Boolean running = true;

    public GameLogic(){}

    public void update(){}

    public boolean isGameRunning(){
        return running;
    }
}
