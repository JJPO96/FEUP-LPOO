package com.lpoo.blockboy.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.lpoo.blockboy.BlockBoy;

/**
 * Created by Manuel Gomes on 12/05/2016.
 */
public class MainMenuScreen implements Screen {

    BlockBoy game;
    OrthographicCamera gameCam;

    private Texture background;
    private Texture menu_bg;
    private Texture newGameBtn;
    private Texture optionsBtn;
    private Texture exitBtn;

    float widthRatio;
    float heightRatio;

    public MainMenuScreen(BlockBoy game){
        this.game = game;
        this.gameCam = new OrthographicCamera();
        gameCam.setToOrtho(false, BlockBoy.VHEIGHT, BlockBoy.VHEIGHT);

        background = new Texture("menu/background.png");
        menu_bg = new Texture("menu/menu_bg.png");
        newGameBtn = new Texture("menu/newGameBtn.png");
        optionsBtn = new Texture("menu/optionsBtn.png");
        exitBtn = new Texture("menu/exitBtn.png");

        widthRatio = Gdx.graphics.getWidth() * 195/2560;
        heightRatio = Gdx.graphics.getHeight() * 97 /1440;
    }

    public void checkInput(float delta){
        if (Gdx.input.isTouched()) {
            if (Gdx.input.getX() > Gdx.graphics.getWidth() / 2 - 2 * widthRatio
             && Gdx.input.getX() < Gdx.graphics.getWidth() / 2 - 2 * widthRatio + 4 * widthRatio
             && Gdx.input.getY() < (Gdx.graphics.getHeight() - (7 * Gdx.graphics.getHeight() / 8 - 4 * heightRatio))
             && Gdx.input.getY() > (Gdx.graphics.getHeight() - (7 * Gdx.graphics.getHeight() / 8 - 4 * heightRatio + 3 * heightRatio))){ // new game button
                game.setScreen(new GameScreen(game));
                dispose();
            }else if (Gdx.input.getX() > Gdx.graphics.getWidth()/2 - 2*widthRatio
                   && Gdx.input.getX() < Gdx.graphics.getWidth()/2 - 2*widthRatio + 4*widthRatio
                   && Gdx.input.getY() < (Gdx.graphics.getHeight() - (4*Gdx.graphics.getHeight()/8 - 8*heightRatio/5))
                   && Gdx.input.getY() > (Gdx.graphics.getHeight() - (4*Gdx.graphics.getHeight()/8 - 8*heightRatio/5 + 3*heightRatio))) {
                      dispose();
            }else if (Gdx.input.getX() > Gdx.graphics.getWidth()/2 - 2*widthRatio
             && Gdx.input.getX() < Gdx.graphics.getWidth()/2 - 2*widthRatio + 4*widthRatio
             && Gdx.input.getY() < (Gdx.graphics.getHeight() - (2*Gdx.graphics.getHeight()/8 - heightRatio))
             && Gdx.input.getY() > (Gdx.graphics.getHeight() - (2*Gdx.graphics.getHeight()/8 - heightRatio + 3*heightRatio))) { // exit button
                dispose();
                Gdx.app.exit();
            }


           // dispose();
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
    public void render(float delta)  {
        update(delta);
        // Clears game screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(menu_bg,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        game.batch.draw(newGameBtn,Gdx.graphics.getWidth()/2 - 2*widthRatio,7*Gdx.graphics.getHeight()/8 - 4*heightRatio,4*widthRatio,3*heightRatio);
        game.batch.draw(optionsBtn,Gdx.graphics.getWidth()/2 - 2*widthRatio,4*Gdx.graphics.getHeight()/8 - 8*heightRatio/5,4*widthRatio,3*heightRatio);
        game.batch.draw(exitBtn,Gdx.graphics.getWidth()/2 - 2*widthRatio,2*Gdx.graphics.getHeight()/8 - 1*heightRatio,4*widthRatio,3*heightRatio);

        /*game.font.draw(game.batch "Welcome to BlockBoy! ", 200, 300);1
        game.font.draw(game.batch, "Tap anywhere to begin!", 200, 200);*/
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
        background.dispose();
        menu_bg.dispose();
        newGameBtn.dispose();
        optionsBtn.dispose();
        exitBtn.dispose();
    }
}
