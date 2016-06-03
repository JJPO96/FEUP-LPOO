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
 * Created by Manuel Gomes on 12/05/2016.
 */
public class LevelScreen implements Screen {

    BlockBoy game;

    private Stage stage;
    private Skin skin;
    private Viewport viewport;

    private TextureAtlas lvlMenuAtlas;

    private ImageButton lvl1Btn;
    private ImageButton lvl2Btn;
    private ImageButton lvl3Btn;
    private ImageButton lvl4Btn;
    private ImageButton lvl5Btn;
    private ImageButton lvl6Btn;
    private ImageButton lvl7Btn;
    private ImageButton lvl8Btn;
    private ImageButton lvl9Btn;
    private ImageButton lvl10Btn;
    private ImageButton lvlLockedBtn;

    private Texture menu_bg;

    public LevelScreen(BlockBoy game){
        this.game = game;

        this.viewport = new FitViewport(BlockBoy.VWIDTH, BlockBoy.VHEIGHT, new OrthographicCamera());
        initStage(game.batch);

        menu_bg = new Texture("menu/menu_bg.png");
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

        lvlMenuAtlas = new TextureAtlas("menu/lvlMenu.pack");
        skin = new Skin();
        skin.addRegions(lvlMenuAtlas);
        stage.clear();

        lvl1Btn = new ImageButton(skin.getDrawable("lvl1"),skin.getDrawable("lvl1p"));
        lvl2Btn = new ImageButton(skin.getDrawable("lvl2"),skin.getDrawable("lvl2p"));
        lvl3Btn = new ImageButton(skin.getDrawable("lvl3"),skin.getDrawable("lvl3p"));
        lvl4Btn = new ImageButton(skin.getDrawable("lvl4"),skin.getDrawable("lvl4p"));
        lvl5Btn = new ImageButton(skin.getDrawable("lvl5"),skin.getDrawable("lvl5p"));
        lvl6Btn = new ImageButton(skin.getDrawable("lvl6"),skin.getDrawable("lvl6p"));
        lvl7Btn = new ImageButton(skin.getDrawable("lvl7"),skin.getDrawable("lvl7p"));
        lvl8Btn = new ImageButton(skin.getDrawable("lvl8"),skin.getDrawable("lvl8p"));
        lvl9Btn = new ImageButton(skin.getDrawable("lvl9"),skin.getDrawable("lvl9p"));
        lvl10Btn = new ImageButton(skin.getDrawable("lvl10"),skin.getDrawable("lvl10p"));
        lvlLockedBtn = new ImageButton(skin.getDrawable("lockedLvl"));


        lvl1Btn.setSize(3*lvl1Btn.getWidth()/7,3*lvl1Btn.getHeight()/7);
        lvl1Btn.setPosition(3*BlockBoy.VWIDTH/14-lvl1Btn.getWidth()/2,6*BlockBoy.VHEIGHT/10-lvl1Btn.getHeight()/2);
        lvl2Btn.setSize(3*lvl2Btn.getWidth()/7,3*lvl2Btn.getHeight()/7);
        lvl2Btn.setPosition(5*BlockBoy.VWIDTH/14-lvl2Btn.getWidth()/2,6*BlockBoy.VHEIGHT/10-lvl2Btn.getHeight()/2);
        lvl3Btn.setSize(3*lvl3Btn.getWidth()/7,3*lvl3Btn.getHeight()/7);
        lvl3Btn.setPosition(7*BlockBoy.VWIDTH/14-lvl3Btn.getWidth()/2,6*BlockBoy.VHEIGHT/10-lvl3Btn.getHeight()/2);
        lvl4Btn.setSize(3*lvl4Btn.getWidth()/7,3*lvl4Btn.getHeight()/7);
        lvl4Btn.setPosition(9*BlockBoy.VWIDTH/14-lvl4Btn.getWidth()/2,6*BlockBoy.VHEIGHT/10-lvl4Btn.getHeight()/2);
        lvl5Btn.setSize(3*lvl5Btn.getWidth()/7,3*lvl5Btn.getHeight()/7);
        lvl5Btn.setPosition(11*BlockBoy.VWIDTH/14-lvl5Btn.getWidth()/2,6*BlockBoy.VHEIGHT/10-lvl5Btn.getHeight()/2);

        lvl6Btn.setSize(3*lvl6Btn.getWidth()/7,3*lvl6Btn.getHeight()/7);
        lvl6Btn.setPosition(3*BlockBoy.VWIDTH/14-lvl6Btn.getWidth()/2,4*BlockBoy.VHEIGHT/10-lvl6Btn.getHeight()/2);
        lvl7Btn.setSize(3*lvl7Btn.getWidth()/7,3*lvl7Btn.getHeight()/7);
        lvl7Btn.setPosition(5*BlockBoy.VWIDTH/14-lvl7Btn.getWidth()/2,4*BlockBoy.VHEIGHT/10-lvl7Btn.getHeight()/2);
        lvl8Btn.setSize(3*lvl8Btn.getWidth()/7,3*lvl8Btn.getHeight()/7);
        lvl8Btn.setPosition(7*BlockBoy.VWIDTH/14-lvl8Btn.getWidth()/2,4*BlockBoy.VHEIGHT/10-lvl8Btn.getHeight()/2);
        lvl9Btn.setSize(3*lvl9Btn.getWidth()/7,3*lvl9Btn.getHeight()/7);
        lvl9Btn.setPosition(9*BlockBoy.VWIDTH/14-lvl9Btn.getWidth()/2,4*BlockBoy.VHEIGHT/10-lvl9Btn.getHeight()/2);
        lvl10Btn.setSize(3*lvl10Btn.getWidth()/7,3*lvl10Btn.getHeight()/7);
        lvl10Btn.setPosition(11*BlockBoy.VWIDTH/14-lvl10Btn.getWidth()/2,4*BlockBoy.VHEIGHT/10-lvl10Btn.getHeight()/2);

        lvl1Btn.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });

        lvl2Btn.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
               /* game.setScreen(new GameScreen(game));
                dispose();*/
            }
        });

        lvl3Btn.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                /*game.setScreen(new GameScreen(game));
                dispose();*/
            }
        });

        lvl4Btn.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                /*game.setScreen(new GameScreen(game));
                dispose();*/
            }
        });

        lvl5Btn.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                /*game.setScreen(new GameScreen(game));
                dispose();*/
            }
        });

        lvl6Btn.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                /*game.setScreen(new GameScreen(game));
                dispose();*/
            }
        });

        lvl7Btn.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                /*game.setScreen(new GameScreen(game));
                dispose();*/
            }
        });

        lvl8Btn.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                /*game.setScreen(new GameScreen(game));
                dispose();*/
            }
        });

        lvl9Btn.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                /*game.setScreen(new GameScreen(game));
                dispose();*/
            }
        });

        lvl10Btn.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                /*game.setScreen(new GameScreen(game));
                dispose();*/
            }
        });

        stage.addActor(lvl1Btn);
        stage.addActor(lvl2Btn);
        stage.addActor(lvl3Btn);
        stage.addActor(lvl4Btn);
        stage.addActor(lvl5Btn);
        stage.addActor(lvl6Btn);
        stage.addActor(lvl7Btn);
        stage.addActor(lvl8Btn);
        stage.addActor(lvl9Btn);
        stage.addActor(lvl10Btn);
        //stage.addActor(lvlLockedBtn);

        Gdx.input.setInputProcessor(stage);
    }
}
