package com.lpoo.blockboy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.lpoo.blockboy.gui.*;

public class BlockBoy extends Game {
    // Variables used to keep aspect ratio for any screen size (screen resolution of the game)
    public static final int VWIDTH = 800;
    public static final int VHEIGHT = 520;
    public static final float PPM = 100;

    // TODO - PASSAR PARA OUTRO LOCAL // APAGAR AS VARIAVEIS QUE NAO INTERESSEM
    public static final short DEFAULT_BIT = 1;
    public static final short HERO_BIT = 2;
    public static final short BLOCK_BIT = 4;
    public static final short BLOCK_PICKED_BIT = 8;
    public static final short COIN_BIT = 16;

    public SpriteBatch batch;
    public BitmapFont font;
    public AssetManager manager;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // TODO - SOUND NOT WORKING
        manager = new AssetManager();
        manager.load("sounds/coin.wav", Sound.class);
        manager.load("sounds/jump.mp3", Sound.class);
        manager.finishLoading();

        setScreen(new MainMenuScreen(this));
    }


    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        manager.dispose();
    }

    @Override
    public void render() {
        // Renders the screen active at the time
        super.render();
    }
}
