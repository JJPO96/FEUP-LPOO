package com.lpoo.blockboy.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo.blockboy.BlockBoy;

/**
 * Created by José Oliveira on 12/05/2016.
 */
public class GameOverScreen implements Screen {

    BlockBoy game;
    private int level;

    private Stage stage;
    private Skin skin;
    private Viewport viewport;

    private TextureAtlas pauseMenuAtlas;

    private ImageButton homeBtn;
    private ImageButton rstBtn;

    private Texture menu_bg;

    private GameScreen gamePlayed;

    public GameOverScreen(BlockBoy game, GameScreen gamePlayed, int level){
        this.game = game;
        this.level = level;
        this.gamePlayed = gamePlayed;
        this.viewport = new FitViewport(BlockBoy.VWIDTH, BlockBoy.VHEIGHT, new OrthographicCamera());
        initStage(game.batch);

        menu_bg = new Texture("menu/pause_bg.png");
    }

    public void checkInput(float delta){

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
        gamePlayed.dispose();
        skin.dispose();
        game.dispose();
    }

    public void initStage(SpriteBatch batch){
        this.stage = new Stage(viewport, batch);

        pauseMenuAtlas = new TextureAtlas("menu/pauseMenu.pack");
        skin = new Skin();
        skin.addRegions(pauseMenuAtlas);
        stage.clear();

        homeBtn = new ImageButton(skin.getDrawable("homeBtn"),skin.getDrawable("homePressed"));
        rstBtn = new ImageButton(skin.getDrawable("rstBtn"),skin.getDrawable("rstPressed"));

        homeBtn.setSize(2*homeBtn.getWidth()/3,2*homeBtn.getHeight()/3);
        homeBtn.setPosition(4*BlockBoy.VWIDTH/10-homeBtn.getWidth()/2,BlockBoy.VHEIGHT/2-homeBtn.getHeight()/2);
        rstBtn.setSize(2*rstBtn.getWidth()/3,2*rstBtn.getHeight()/3);
        rstBtn.setPosition(6*BlockBoy.VWIDTH/10-rstBtn.getWidth()/2,BlockBoy.VHEIGHT/2-rstBtn.getHeight()/2);

        homeBtn.addListener(new InputListener(){

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                Gdx.input.vibrate(40);
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                gamePlayed.dispose();
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });

        rstBtn.addListener(new InputListener(){

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                Gdx.input.vibrate(40);
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                gamePlayed.dispose();
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });

        stage.addActor(homeBtn);
        stage.addActor(rstBtn);

        Gdx.input.setInputProcessor(stage);
    }
}
