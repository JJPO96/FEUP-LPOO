package com.lpoo.blockboy.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo.blockboy.BlockBoy;

/**
 * Created by Manuel Gomes on 31/05/2016.
 */
public class Hud implements Disposable{
    private Stage stage;
    private Viewport viewport;
    private Integer numCoins;
    private Integer worldTimer;
    private float timeCount;
    private Label coinsCount;
    private Label coinsLabel;
    private Label countdownLabel;
    private Label timeLabel;
    private Label spacing;
    private Table table;

    // TODO - FALTA TERMINAR
    public Hud(SpriteBatch batch, BitmapFont font){
        this.numCoins = 0;
        this.worldTimer = 0;
        this.timeCount = 0;
        this.viewport = new FitViewport(BlockBoy.VHEIGHT, BlockBoy.VHEIGHT, new OrthographicCamera());
        this.stage = new Stage(viewport, batch);
        this.table = new Table();
        this.table.top();
        this.table.setFillParent(true);
        this.countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(font, Color.WHITE));
        this.timeLabel = new Label("TIME", new Label.LabelStyle(font, Color.WHITE));
        this.coinsCount = new Label(String.format("%01d", numCoins), new Label.LabelStyle(font, Color.WHITE));
        this.coinsLabel = new Label("COINS", new Label.LabelStyle(font, Color.WHITE));
        this.spacing = new Label("", new Label.LabelStyle(font, Color.WHITE));

        table.add(coinsLabel).expandX();
        table.add(coinsCount).expandX();
        table.add(spacing).expandX();
        table.add(spacing).expandX();
        table.add(spacing).expandX();
        table.add(spacing).expandX();
        table.add(timeLabel).expandX();
        table.add(countdownLabel).expandX();
        stage.addActor(table);
    }

    public Stage getStage() {
        return stage;
    }

    public void update(float delta){
        timeCount+= delta;
        if (timeCount >= 1){
            worldTimer++;
            if (worldTimer > 999)
                worldTimer = 0;
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }

    @Override
    public void dispose() { stage.dispose(); }
}
