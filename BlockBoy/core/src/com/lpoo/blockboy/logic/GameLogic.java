package com.lpoo.blockboy.logic;

import com.badlogic.gdx.physics.box2d.World;
import com.lpoo.blockboy.gui.GameScreen;

import java.util.ArrayList;

/**
 * Created by Manuel Gomes on 12/05/2016.
 */
public class GameLogic {

    private Hero hero;
    private Key key;
    private ArrayList<Block> blocks;
    private Boolean running = true;

    private GameScreen screen;
    private World world;

    public GameLogic(GameScreen screen){
        // TODO - implementar aqui a logica do jogo
        this.screen = screen;
        this.world = screen.getWorld();
    }

    public void update(){}

    public boolean isGameRunning(){
        return running;
    }
}
