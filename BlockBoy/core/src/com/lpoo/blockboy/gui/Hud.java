package com.lpoo.blockboy.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo.blockboy.BlockBoy;
import com.lpoo.blockboy.logic.Block;

/**
 * Created by Manuel Gomes on 31/05/2016.
 */
public class Hud implements Disposable {
    private GameScreen gameScreen;
    private Stage stage;
    private Skin skin;
    private Viewport viewport;
    private Integer coinScore;
    private Integer worldTimer;
    private float timeCount;

    private TextureAtlas hudAtlas;
    private ImageButton coin;
    private ImageButton cross;
    private ImageButton coinNumber;
    private ImageButton heart;
    private ImageButton sandclock;
    private ImageButton timeOne;
    private ImageButton timeTwo;
    private ImageButton timeThree;
    private ImageButton back;
    private ImageButton pickBox;
    private ImageButton jump;

    /**
     * Hud constructor
     *
     * @param gameScreen
     */
    public Hud(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.coinScore = 0;
        this.worldTimer = 0;
        this.timeCount = 0;
        this.viewport = new FitViewport(BlockBoy.VWIDTH, BlockBoy.VHEIGHT, new OrthographicCamera());
        initStage(gameScreen.getGame().batch);
    }

    /**
     * Creates a stage
     *
     * @param batch
     */
    public void initStage(SpriteBatch batch) {
        this.stage = new Stage(viewport, batch);
        hudAtlas = new TextureAtlas("hud/hudspritesheet.pack");
        skin = new Skin();
        skin.addRegions(hudAtlas);
        stage.clear();

        // Creates buttons
        coin = new ImageButton(skin.getDrawable("coin"));
        cross = new ImageButton(skin.getDrawable("x"));
        coinNumber = new ImageButton(skin.getDrawable("zero"));
        heart = new ImageButton(skin.getDrawable("heart"));
        sandclock = new ImageButton(skin.getDrawable("sandclock"));
        timeOne = new ImageButton(skin.getDrawable("zero"));
        timeTwo = new ImageButton(skin.getDrawable("zero"));
        timeThree = new ImageButton(skin.getDrawable("zero"));
        back = new ImageButton(skin.getDrawable("homeUp"), skin.getDrawable("homePressed"));
        pickBox = new ImageButton(skin.getDrawable("boxUp"), skin.getDrawable("boxPressed"));
        jump = new ImageButton(skin.getDrawable("jumpUp"), skin.getDrawable("jumpPressed"));

        coin.setWidth(coin.getWidth() / 3);
        coin.setHeight(coin.getHeight() / 3);
        coin.setPosition(BlockBoy.VWIDTH/8, BlockBoy.VHEIGHT - coin.getHeight());
        cross.setWidth(cross.getWidth() / 3);
        cross.setHeight(cross.getHeight() / 3);
        cross.setPosition(40 + BlockBoy.VWIDTH/8, BlockBoy.VHEIGHT - cross.getHeight());
        coinNumber.setWidth(coinNumber.getWidth() / 3);
        coinNumber.setHeight(coinNumber.getHeight() / 3);
        coinNumber.setPosition(70 + BlockBoy.VWIDTH/8, BlockBoy.VHEIGHT - coinNumber.getHeight());
        heart.setWidth(heart.getWidth() / 3);
        heart.setHeight(heart.getHeight() / 3);
        heart.setPosition(BlockBoy.VWIDTH/2 - heart.getWidth()/2, BlockBoy.VHEIGHT - heart.getHeight());
        sandclock.setWidth(sandclock.getWidth() / 3);
        sandclock.setHeight(sandclock.getHeight() / 4);
        sandclock.setPosition(3*BlockBoy.VWIDTH/4 - sandclock.getWidth()/2, BlockBoy.VHEIGHT - sandclock.getHeight());
        timeOne.setWidth(timeOne.getWidth() / 3);
        timeOne.setHeight(timeOne.getHeight() / 3);
        timeOne.setPosition(40+3*BlockBoy.VWIDTH/4, BlockBoy.VHEIGHT - timeOne.getHeight());
        timeTwo.setWidth(timeTwo.getWidth() / 3);
        timeTwo.setHeight(timeTwo.getHeight() / 3);
        timeTwo.setPosition(60+3*BlockBoy.VWIDTH/4, BlockBoy.VHEIGHT - timeTwo.getHeight());
        timeThree.setWidth(timeThree.getWidth() / 3);
        timeThree.setHeight(timeThree.getHeight() / 3);
        timeThree.setPosition(80+3*BlockBoy.VWIDTH/4, BlockBoy.VHEIGHT - timeThree.getHeight());

        back.setWidth(90);
        back.setHeight(90);
        back.setPosition(BlockBoy.VWIDTH/8, 50);
        pickBox.setWidth(90);
        pickBox.setHeight(90);
        pickBox.setPosition(7*BlockBoy.VWIDTH/8-90, 50);
        jump.setWidth(90);
        jump.setHeight(90);
        jump.setPosition(BlockBoy.VWIDTH/2-45, 50);

        addButtonListeners();

        // Adding actors
        stage.addActor(coin);
        stage.addActor(cross);
        stage.addActor(coinNumber);
        stage.addActor(heart);
        stage.addActor(sandclock);
        stage.addActor(timeOne);
        stage.addActor(timeTwo);
        stage.addActor(timeThree);
        stage.addActor(back);
        stage.addActor(pickBox);
        stage.addActor(jump);

        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Add event listeners to the buttons
     */
    void addButtonListeners(){
        //Back button
        back.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameScreen.getGame().setScreen(new MainMenuScreen(gameScreen.getGame()));
            }
        });

        // Pick box button
        pickBox.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameScreen.getGameLogic().setMoveBlock(true);
            }
        });

        // Jump button
        jump.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gameScreen.getGameLogic().getHero().jump();
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
        });
    }

    public Stage getStage() {
        return stage;
    }

    /**
     * Updates the time and score
     *
     * @param delta
     * @param score
     */
    public void update(float delta, int score) {
        timeCount+= delta;
        if (timeCount >= 1){
            worldTimer++;
            if (worldTimer > 999)
                worldTimer = 0;
            setTime();
            timeCount = 0;
        }

        this.coinScore = score;

        coinNumber.remove();
        coinNumber = new ImageButton(skin.getDrawable(getNumber(score)));
        coinNumber.setWidth(coinNumber.getWidth() / 3);
        coinNumber.setHeight(coinNumber.getHeight() / 3);
        coinNumber.setPosition(70 + BlockBoy.VWIDTH/8, BlockBoy.VHEIGHT - coinNumber.getHeight());
        stage.addActor(coinNumber);
    }

    /**
     * Sets new time
     */
    public void setTime(){
        int division;
        timeOne.remove();
        timeTwo.remove();
        timeThree.remove();

        timeThree = new ImageButton(skin.getDrawable(getNumber(worldTimer%10)));
        division = worldTimer/10;
        timeTwo = new ImageButton(skin.getDrawable(getNumber(division%10)));
        division = division/10;
        timeOne = new ImageButton(skin.getDrawable(getNumber(division%10)));

        timeOne.setWidth(timeOne.getWidth() / 3);
        timeOne.setHeight(timeOne.getHeight() / 3);
        timeOne.setPosition(40+3*BlockBoy.VWIDTH/4, BlockBoy.VHEIGHT - timeOne.getHeight());
        timeTwo.setWidth(timeTwo.getWidth() / 3);
        timeTwo.setHeight(timeTwo.getHeight() / 3);
        timeTwo.setPosition(60+3*BlockBoy.VWIDTH/4, BlockBoy.VHEIGHT - timeTwo.getHeight());
        timeThree.setWidth(timeThree.getWidth() / 3);
        timeThree.setHeight(timeThree.getHeight() / 3);
        timeThree.setPosition(80+3*BlockBoy.VWIDTH/4, BlockBoy.VHEIGHT - timeThree.getHeight());

        stage.addActor(timeOne);
        stage.addActor(timeTwo);
        stage.addActor(timeThree);
    }

    /**
     * Converts integer to string
     *
     * @param integer
     * @return string
     */
    public String getNumber(Integer integer) {

        switch (integer) {
            case 0:
                return "zero";
            case 1:
                return "one";
            case 2:
                return "two";
            case 3:
                return "three";
            case 4:
                return "four";
            case 5:
                return "five";
            case 6:
                return "six";
            case 7:
                return "seven";
            case 8:
                return "eight";
            case 9:
                return "nine";
            default:
                break;
        }

        return null;
    }

    @Override
    public void dispose() {
        stage.dispose();
        hudAtlas.dispose();
        skin.dispose();
    }
}
