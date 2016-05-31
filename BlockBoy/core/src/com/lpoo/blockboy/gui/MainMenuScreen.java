package com.lpoo.blockboy.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.lpoo.blockboy.BlockBoy;

/**
 * Created by Manuel Gomes on 12/05/2016.
 */
public class MainMenuScreen implements Screen {

    BlockBoy game;
    OrthographicCamera gameCam;

    public MainMenuScreen(BlockBoy game){
        this.game = game;
        this.gameCam = new OrthographicCamera();
        gameCam.setToOrtho(false, BlockBoy.VHEIGHT, BlockBoy.VHEIGHT);
    }

    public void checkInput(float delta){
        if (Gdx.input.isTouched()){
            game.setScreen(new GameScreen(game));
            dispose();
        }

    }

    public void update(float delta){
        checkInput(delta);
        gameCam.update();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        // Clears game screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        game.font.draw(game.batch, "Welcome to BlockBoy! ", 200, 300);
        game.font.draw(game.batch, "Tap anywhere to begin!", 200, 200);
        game.batch.end();

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
