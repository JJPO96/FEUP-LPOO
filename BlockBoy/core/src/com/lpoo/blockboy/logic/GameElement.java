package com.lpoo.blockboy.logic;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoo.blockboy.gui.GameScreen;

/**
 * Class parent from which the game elements inherit
 */
public abstract class GameElement extends Sprite {

    protected World world;
    protected Body body;
    protected GameScreen screen;
    Map map;

    /**
     * GameElement's Constructor
     *
     * @param screen where the element will be displayed
     */
    GameElement(GameScreen screen){
        this.screen = screen;
        this.world = screen.getWorld();
    }

    public abstract void initElement();

    public abstract void update();

    public World getWorld(){ return world;}

    public Screen getScreen() { return screen;}

    public Body getBody() { return body;}
}
