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
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo.blockboy.BlockBoy;

/**
 * Created by José Oliveira on 12/05/2016.
 */
public class OptionsScreen implements Screen {

    BlockBoy game;

    private Stage stage;
    private Skin skin;
    private Viewport viewport;

    private TextureAtlas optMenuAtlas;

    private ImageButton homeBtn;
    private ImageButton volBtn;
    private ImageButton plusBtn;
    private ImageButton minusBtn;

    private Slider volSlider;

    private Texture menu_bg;

    public OptionsScreen(BlockBoy game){
        this.game = game;
        this.viewport = new FitViewport(BlockBoy.VWIDTH, BlockBoy.VHEIGHT, new OrthographicCamera());
        initStage(game.batch);

        menu_bg = new Texture("menu/opt_bg.png");
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
    }

    public void initStage(SpriteBatch batch){
        this.stage = new Stage(viewport, batch);

        optMenuAtlas = new TextureAtlas("menu/optMenu.pack");
        skin = new Skin();
        skin.addRegions(optMenuAtlas);
        stage.clear();

        homeBtn = new ImageButton(skin.getDrawable("homeBtn"),skin.getDrawable("homePressed"));
        volBtn = new ImageButton(skin.getDrawable("volBtn"),skin.getDrawable("volPressed"),skin.getDrawable("volCheck"));
        plusBtn = new ImageButton(skin.getDrawable("plusBtn"),skin.getDrawable("plusPressed"));
        minusBtn = new ImageButton(skin.getDrawable("minusBtn"),skin.getDrawable("minusPressed"));

        /*Slider.SliderStyle volSliStyle = new Slider.SliderStyle(skin.getDrawable("knobBtn"),skin.getDrawable("slider_bg"));
        volSliStyle.knobDown = skin.getDrawable("knobPressed");

        volSlider = new Slider(0,100,5,false,volSliStyle);
        volSlider.setSize(100,20);
        volSlider.setPosition(5*BlockBoy.VWIDTH/12,2*BlockBoy.VWIDTH/5);*/

        homeBtn.setSize(2*homeBtn.getWidth()/5,2*homeBtn.getHeight()/5);
        homeBtn.setPosition(10,BlockBoy.VHEIGHT - homeBtn.getHeight() - 10);

        volBtn.setSize(2*volBtn.getWidth()/5,2*volBtn.getHeight()/5);
        volBtn.setPosition(3*BlockBoy.VWIDTH/12 - volBtn.getWidth()/2,2*BlockBoy.VWIDTH/5 - volBtn.getHeight()/2);

        plusBtn.setSize(2*plusBtn.getWidth()/5,2*plusBtn.getHeight()/5);
        plusBtn.setPosition(9*BlockBoy.VWIDTH/12 - volBtn.getWidth()/2,2*BlockBoy.VWIDTH/5 - volBtn.getHeight()/2);

        minusBtn.setSize(2*minusBtn.getWidth()/5,2*minusBtn.getHeight()/5);
        minusBtn.setPosition(4*BlockBoy.VWIDTH/12 - volBtn.getWidth()/2 + 5,2*BlockBoy.VWIDTH/5 - volBtn.getHeight()/2);

        homeBtn.addListener(new InputListener(){

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
               return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });

        stage.addActor(homeBtn);
        stage.addActor(volBtn);
        stage.addActor(plusBtn);
        stage.addActor(minusBtn);

        //stage.addActor(volSlider);

        Gdx.input.setInputProcessor(stage);
    }
}
