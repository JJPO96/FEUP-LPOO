package com.lpoo.blockboy.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo.blockboy.BlockBoy;

/**
 * Created by Manuel Gomes on 31/05/2016.
 */
public class Hud implements Disposable{
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
    private ImageButton back;
    private ImageButton pickBox;
    private ImageButton jump;


    ///// TODO - APAGAR??
    private Label coinsCount;
    private Label coinsLabel;
    private Label countdownLabel;
    private Label timeLabel;
    private Table tableTop;
    private Table tableBottom;

    // TODO - FALTA TERMINAR
    public Hud(SpriteBatch batch, BitmapFont font){
        this.coinScore = 0;
        this.worldTimer = 0;
        this.timeCount = 0;
        this.viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        initStage(batch);




        /*this.tableTop = new Table();
        this.tableTop.top();
        this.tableTop.setFillParent(true);
        this.tableBottom = new Table();
        this.countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(font, Color.WHITE));
        this.timeLabel = new Label("TIME", new Label.LabelStyle(font, Color.WHITE));
        this.coinsCount = new Label(String.format("%01d", coinScore), new Label.LabelStyle(font, Color.WHITE));
        this.coinsLabel = new Label("COINS", new Label.LabelStyle(font, Color.WHITE));

        tableTop.add(coinsLabel).expandX();
        tableTop.add(timeLabel).expandX();
        tableTop.row();
        tableTop.add(coinsCount).expandX();
        tableTop.add(countdownLabel).expandX();
        stage.addActor(tableTop);
        stage.addActor(tableBottom);*/
    }

    public void initStage(SpriteBatch batch){
        this.stage = new Stage(viewport, batch);
        hudAtlas = new TextureAtlas("hud/hudspritesheet.pack");
        skin = new Skin();
        skin.addRegions(hudAtlas);
        stage.clear();

        coin = new ImageButton(skin.getDrawable("coin"));
        cross = new ImageButton(skin.getDrawable("x"));
        coinNumber = new ImageButton(skin.getDrawable("one"));


        coin.setWidth(coin.getWidth()/3);
        coin.setHeight(coin.getHeight()/3);
        coin.setPosition(20, Gdx.graphics.getHeight()-coin.getHeight());
        cross.setWidth(cross.getWidth()/3);
        cross.setHeight(cross.getHeight()/3);
        cross.setPosition(40+coin.getWidth()/3, Gdx.graphics.getHeight()-cross.getHeight());
        coinNumber.setWidth(coinNumber.getWidth()/3);
        coinNumber.setHeight(coinNumber.getHeight()/3);
        coinNumber.setPosition(70+cross.getWidth()/3, Gdx.graphics.getHeight()-coin.getHeight());

        stage.addActor(coin);
        stage.addActor(cross);
        stage.addActor(coinNumber);

        Gdx.input.setInputProcessor(stage);
    }

    public Stage getStage() {
        return stage;
    }

    // TODO - COMPLETAR
    public void update(float delta, int score){
        /*timeCount+= delta;
        if (timeCount >= 1){
            worldTimer++;
            if (worldTimer > 999)
                worldTimer = 0;
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }*/


        //stage.removeac
        this.coinScore = score;
        this.coinsCount.setText(String.format("%01d", coinScore));
    }

    @Override
    public void dispose() { stage.dispose(); }
}
