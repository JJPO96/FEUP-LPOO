package com.lpoo.blockboy.logic;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoo.blockboy.gui.GameScreen;

/**
 * Class parent from which the game elements inherit
 */
public abstract class GameElement extends Sprite {

    // Screen
    protected GameScreen screen;

    // Box2d variables
    protected Body body;
    protected BodyDef bodyDef;
    protected FixtureDef fixtureDef;
    protected World world;

    // TODO - CORRIGIR ESTE CONSTRUTOR
    /**
     * GameElement's Constructor
     *
     * @param screen where the element will be displayed
     */
    public GameElement(GameScreen screen, String region){
        super(screen.getAtlas().findRegion(region));
        this.screen = screen;
        this.world = screen.getWorld();
    }

    /**
     * Initializes an element of the game and creates its body
     */
    public abstract void init();

    public abstract void loadTextures();

    public abstract void update(float delta);

    public World getWorld(){ return world;}

    public Screen getScreen() { return screen;}

    public Body getBody() { return body;}
}
