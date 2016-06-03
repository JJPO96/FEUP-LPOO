package com.lpoo.blockboy.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.lpoo.blockboy.BlockBoy;

/**
 * Created by José Oliveira on 12/05/2016.
 */
public class LevelScreen implements Screen {

    BlockBoy game;
    OrthographicCamera gameCam;

    private Stage stage;

    private Texture menu_bg;

    private boolean[] lvlsLock;

    private TextureAtlas lvlAtlas;
    Skin skin;

    private ImageButton lvl1;
    private ImageButton lvl2;
    private ImageButton lvl3;
    private ImageButton lvl4;
    private ImageButton lvl5;
    private ImageButton lvl6;
    private ImageButton lvl7;
    private ImageButton lvl8;
    private ImageButton lvl9;
    private ImageButton lvl10;
    private ImageButton lvlLocked;

    float widthRatio;
    float heightRatio;

    public LevelScreen(BlockBoy game){
        this.game = game;
        this.gameCam = new OrthographicCamera();
        gameCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage();
        lvlAtlas = new TextureAtlas("menu/lvlMenu.pack");
        skin = new Skin();
        skin.addRegions(lvlAtlas);
        stage.clear();

        menu_bg = new Texture("menu/menu_bg.png");

        lvl1 = new ImageButton(skin.getDrawable("lvl1"),skin.getDrawable("lvl1p"));
        lvl2 = new ImageButton(skin.getDrawable("lvl2"),skin.getDrawable("lvl2p"));
        lvl3 = new ImageButton(skin.getDrawable("lvl3"),skin.getDrawable("lvl3p"));
        lvl4 = new ImageButton(skin.getDrawable("lvl4"),skin.getDrawable("lvl4p"));
        lvl5 = new ImageButton(skin.getDrawable("lvl5"),skin.getDrawable("lvl5p"));
        lvl6 = new ImageButton(skin.getDrawable("lvl6"),skin.getDrawable("lvl6p"));
        lvl7 = new ImageButton(skin.getDrawable("lvl7"),skin.getDrawable("lvl7p"));
        lvl8 = new ImageButton(skin.getDrawable("lvl8"),skin.getDrawable("lvl8p"));
        lvl9 = new ImageButton(skin.getDrawable("lvl9"),skin.getDrawable("lvl9p"));
        lvl10 = new ImageButton(skin.getDrawable("lvl10"),skin.getDrawable("lvl10p"));
        lvlLocked = new ImageButton(skin.getDrawable("lockedLvl"));


        lvlsLock = new boolean[10];
        for(int i = 0; i < 10 ; i++){
            lvlsLock[i] = true;
        }

        widthRatio = Gdx.graphics.getWidth() * 178 / 2560;
        heightRatio = Gdx.graphics.getHeight() * 178 / 1440;

        lvl1.setPosition(Gdx.graphics.getWidth()/2 - lvl1.getWidth(),Gdx.graphics.getHeight()/2);
        //lvl1.setPosition(600, 440);

        stage.addActor(lvl1);
        Gdx.input.setInputProcessor(stage);




    }

    public void checkInput(float delta){
        if (Gdx.input.justTouched()) {
           /* if (Gdx.input.getX() > Gdx.graphics.getWidth() / 2 - 2 * widthRatio
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
            }*/


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
        lvlAtlas.dispose();
    }
}
