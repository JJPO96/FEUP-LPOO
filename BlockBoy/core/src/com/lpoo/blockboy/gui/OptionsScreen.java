package com.lpoo.blockboy.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo.blockboy.BlockBoy;
import com.lpoo.blockboy.logic.Block;

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

    private Slider volCtrl;

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
        BlockBoy.volume = volCtrl.getValue();
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

        ImageButton.ImageButtonStyle plSty = plusBtn.getStyle();
        ImageButton.ImageButtonStyle minSty = minusBtn.getStyle();
        plSty.imageDisabled = skin.getDrawable("plusCheck");
        minSty.imageDisabled = skin.getDrawable("minusCheck");
        plusBtn.setStyle(plSty);
        minusBtn.setStyle(minSty);

        if (BlockBoy.mute) {
            Slider.SliderStyle sliderStyle = new Slider.SliderStyle(skin.getDrawable("slider_bg"), skin.getDrawable("knobCheck"));
            sliderStyle.knobBefore = skin.newDrawable("slider_bg", Color.LIGHT_GRAY);
            sliderStyle.knob.setMinHeight(60);
            sliderStyle.knob.setMinWidth(60);
            sliderStyle.background.setMinWidth(5 * BlockBoy.VWIDTH / 12 - volBtn.getWidth() / 4);
            volCtrl = new Slider(0,100,5,false,sliderStyle);
        }else{
            Slider.SliderStyle sliderStyle = new Slider.SliderStyle(skin.getDrawable("slider_bg"), skin.getDrawable("knobBtn"));
            sliderStyle.knobDown = skin.getDrawable("knobPressed");
            sliderStyle.knobBefore = skin.newDrawable("slider_bg", Color.FOREST);
            sliderStyle.knob.setMinHeight(60);
            sliderStyle.knob.setMinWidth(60);
            sliderStyle.knobDown.setMinHeight(65);
            sliderStyle.knobDown.setMinWidth(65);
            sliderStyle.background.setMinWidth(5 * BlockBoy.VWIDTH / 12 - volBtn.getWidth() / 4);
            volCtrl = new Slider(0,100,5,false,sliderStyle);
        }
        volCtrl.setSize(4*BlockBoy.VWIDTH/12-30, 10);
        volCtrl.setPosition(6.5f*BlockBoy.VWIDTH/12-volCtrl.getWidth()/2+5, 3*BlockBoy.VHEIGHT/5-volCtrl.getHeight()/2);
        volCtrl.setValue(BlockBoy.volume);
        volCtrl.setVisible(true);

        homeBtn.setSize(2*homeBtn.getWidth()/5,2*homeBtn.getHeight()/5);
        homeBtn.setPosition(10,BlockBoy.VHEIGHT - homeBtn.getHeight() - 10);

        volBtn.setSize(2*volBtn.getWidth()/5,2*volBtn.getHeight()/5);
        volBtn.setPosition(3*BlockBoy.VWIDTH/12 - volBtn.getWidth()/2,3*BlockBoy.VHEIGHT/5 - volBtn.getHeight()/2);

        plusBtn.setSize(2*plusBtn.getWidth()/5,2*plusBtn.getHeight()/5);
        plusBtn.setPosition(9*BlockBoy.VWIDTH/12 - volBtn.getWidth()/2,3*BlockBoy.VHEIGHT/5 - volBtn.getHeight()/2);

        minusBtn.setSize(2*minusBtn.getWidth()/5,2*minusBtn.getHeight()/5);
        minusBtn.setPosition(4*BlockBoy.VWIDTH/12 - volBtn.getWidth()/2 + 10,3*BlockBoy.VHEIGHT/5 - volBtn.getHeight()/2);

        volCtrl.setDisabled(BlockBoy.mute);
        volBtn.setChecked(BlockBoy.mute);
        plusBtn.setDisabled(BlockBoy.mute);
        minusBtn.setDisabled(BlockBoy.mute);

        homeBtn.addListener(new InputListener(){

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                Gdx.input.vibrate(40);
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(new MainMenuScreen(game));
                BlockBoy.saveData();
                dispose();
            }
        });

        plusBtn.addListener(new InputListener(){

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                Gdx.input.vibrate(40);
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if (BlockBoy.volume < 100 && !BlockBoy.mute) {
                    BlockBoy.volume += 5;
                    volCtrl.setValue(BlockBoy.volume);
                }
            }
        });

        minusBtn.addListener(new InputListener(){

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                Gdx.input.vibrate(40);
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if (BlockBoy.volume > 0 && !BlockBoy.mute) {
                    BlockBoy.volume -= 5;
                    volCtrl.setValue(BlockBoy.volume);
                }
            }
        });

        volBtn.addListener(new InputListener(){

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                Gdx.input.vibrate(40);
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                BlockBoy.mute = !BlockBoy.mute;
                volCtrl.setDisabled(BlockBoy.mute);
                plusBtn.setDisabled(BlockBoy.mute);
                minusBtn.setDisabled(BlockBoy.mute);

                if (BlockBoy.mute) {
                    Slider.SliderStyle sliderStyle = new Slider.SliderStyle(skin.getDrawable("slider_bg"), skin.getDrawable("knobCheck"));
                    sliderStyle.knobBefore = skin.newDrawable("slider_bg", Color.LIGHT_GRAY);
                    sliderStyle.knob.setMinHeight(60);
                    sliderStyle.knob.setMinWidth(60);
                    sliderStyle.background.setMinWidth(5 * BlockBoy.VWIDTH / 12 - volBtn.getWidth() / 4);
                    volCtrl.setStyle(sliderStyle);
                }else{
                    Slider.SliderStyle sliderStyle = new Slider.SliderStyle(skin.getDrawable("slider_bg"), skin.getDrawable("knobBtn"));
                    sliderStyle.knobDown = skin.getDrawable("knobPressed");
                    sliderStyle.knobBefore = skin.newDrawable("slider_bg", Color.FOREST);
                    sliderStyle.knob.setMinHeight(60);
                    sliderStyle.knob.setMinWidth(60);
                    sliderStyle.knobDown.setMinHeight(65);
                    sliderStyle.knobDown.setMinWidth(65);
                    sliderStyle.background.setMinWidth(5 * BlockBoy.VWIDTH / 12 - volBtn.getWidth() / 4);
                    volCtrl.setStyle(sliderStyle);
                }
            }
        });

        stage.addActor(homeBtn);
        stage.addActor(volBtn);
        stage.addActor(plusBtn);
        stage.addActor(minusBtn);
        stage.addActor(volCtrl);

        Gdx.input.setInputProcessor(stage);
    }
}
