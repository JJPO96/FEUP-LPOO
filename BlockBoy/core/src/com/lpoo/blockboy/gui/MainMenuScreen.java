package com.lpoo.blockboy.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo.blockboy.BlockBoy;
import com.lpoo.blockboy.logic.Block;

/**
 * Created by Manuel Gomes on 12/05/2016.
 */
public class MainMenuScreen implements Screen {

    BlockBoy game;
    OrthographicCamera gameCam;

    private Stage stage;
    private Skin skin;
    private Viewport viewport;

    private TextureAtlas startMenuAtlas;

    private ImageButton newGameBtn;
    private ImageButton optionsBtn;
    private ImageButton exitBtn;

    private Texture menu_bg;

    float widthRatio;
    float heightRatio;

    public MainMenuScreen(BlockBoy game){
        this.game = game;

        this.viewport = new FitViewport(BlockBoy.VWIDTH, BlockBoy.VHEIGHT, new OrthographicCamera());
        initStage(game.batch);

        menu_bg = new Texture("menu/menu_bg.png");


        Gdx.app.log("" + widthRatio,"" + heightRatio);
    }

    public void checkInput(float delta){
        if (Gdx.input.isTouched()) {
            if (newGameBtn.isPressed()){ // new game button
                game.setScreen(new GameScreen(game));
                dispose();
            }else if (optionsBtn.isPressed()) {
              /*game.setScreen(new LevelScreen(game));
                dispose();*/
            }else if (exitBtn.isPressed()) { // exit button
                dispose();
                Gdx.app.exit();
            }


           // dispose();
        }

    }

    public void update(float delta){
        checkInput(delta);
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
        game.batch.draw(menu_bg,0,0, BlockBoy.VWIDTH,BlockBoy.VHEIGHT);
        game.batch.end();

        stage.draw();
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
        menu_bg.dispose();
        stage.dispose();
    }

    public void initStage(SpriteBatch batch){
        this.stage = new Stage(viewport, batch);

        startMenuAtlas = new TextureAtlas("menu/startMenu.pack");
        skin = new Skin();
        skin.addRegions(startMenuAtlas);
        stage.clear();

        newGameBtn = new ImageButton(skin.getDrawable("newGameBtn"),skin.getDrawable("newGamePressed"));
        optionsBtn = new ImageButton(skin.getDrawable("optionsBtn"),skin.getDrawable("optionsPressed"));
        exitBtn = new ImageButton(skin.getDrawable("exitBtn"),skin.getDrawable("exitPressed"));

        /*widthRatio = Gdx.graphics.getWidth() * newGameBtn.getWidth()/1280;
        heightRatio = BlockBoy.VHEIGHT * newGameBtn.getHeight()/770;*/

        newGameBtn.setSize(newGameBtn.getWidth()/2,newGameBtn.getHeight()/2);
        newGameBtn.setPosition(BlockBoy.VWIDTH/2-newGameBtn.getWidth()/2,7*BlockBoy.VHEIGHT/10-newGameBtn.getHeight()/2);
        optionsBtn.setSize(optionsBtn.getWidth()/2,optionsBtn.getHeight()/2);
        optionsBtn.setPosition(BlockBoy.VWIDTH/2-optionsBtn.getWidth()/2,5*BlockBoy.VHEIGHT/10-optionsBtn.getHeight()/2);
        exitBtn.setSize(exitBtn.getWidth()/2,exitBtn.getHeight()/2);
        exitBtn.setPosition(BlockBoy.VWIDTH/2-exitBtn.getWidth()/2,3*BlockBoy.VHEIGHT/10-exitBtn.getHeight()/2);

        stage.addActor(newGameBtn);
        stage.addActor(optionsBtn);
        stage.addActor(exitBtn);

        Gdx.input.setInputProcessor(stage);
    }
}
