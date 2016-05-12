package com.lpoo.blockboy.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoo.blockboy.BlockBoy;

/**
 * Created by Manuel Gomes on 12/05/2016.
 */
public class GameScreen implements Screen {

    // Variables to be used to keep aspect ratio for any screen size
    private final int VWIDTH = 400;
    private final int VHEIGHT = 200;

    private BlockBoy game;
    private World world;

    public GameScreen(BlockBoy game){
        this.game = game;

        // Create a Box2D world, setting no gravity in X axis, -9.8 gravity in Y axis
        world = new World(new Vector2(0, -9.8f), true);
    }

    public World getWorld(){
        return world;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Clears the game screen
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
