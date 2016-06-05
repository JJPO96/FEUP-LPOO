package com.lpoo.blockboy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.lpoo.blockboy.gui.*;

import java.util.Arrays;

public class BlockBoy extends Game {
    // Variables used to keep aspect ratio for any screen size (screen resolution of the game)
    public static final int VWIDTH = 800;
    public static final int VHEIGHT = 520;
    public static final float PPM = 100;

    public static float volume = 0;
    public static boolean mute = false;

    public static Preferences prefs;

    public static int levelInd = 0;

    public static boolean[] lockLevels;
    public static boolean[] lockSkins;

    // TODO - PASSAR PARA OUTRO LOCAL // APAGAR AS VARIAVEIS QUE NAO INTERESSEM
    public static final short DEFAULT_BIT = 1;
    public static final short HERO_BIT = 2;
    public static final short BLOCK_BIT = 4;
    public static final short BLOCK_PICKED_BIT = 8;
    public static final short COIN_BIT = 16;
    public static final short AIRGROUND_BIT = 32;
    public static final short BRICK_BIT = 64;
    public static final short EXIT_BIT = 128;

    public SpriteBatch batch;
    public AssetManager manager;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // TODO - SOUND NOT WORKING
        manager = new AssetManager();
        manager.load("sounds/coin.wav", Sound.class);
        manager.load("sounds/jump.mp3", Sound.class);
        manager.finishLoading();

        lockLevels = new boolean[10];
        lockSkins = new boolean[3];

        prefs = Gdx.app.getPreferences("BlockBoyPrefs");
        String name = prefs.getString("name", "No name stored");
        if (name == "No name stored"){
            predefinedData();
        }else {
            loadData();
        }

        loadData();
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

    public static void saveData(){
        prefs.putString("name", "BlockBoy");
        prefs.flush();
        prefs.putInteger("volume",(int)volume);
        prefs.flush();
        prefs.putBoolean("mute", mute);
        prefs.flush();

        for (int i = 0; i < lockLevels.length;i++){
            prefs.putBoolean("level " + i, lockLevels[i]);
            prefs.flush();
        }

        for (int i = 0; i < lockSkins.length;i++){
            prefs.putBoolean("skin " + i, lockSkins[i]);
            prefs.flush();
        }
    }

    public static void loadData(){
        mute = prefs.getBoolean("mute");
        volume = prefs.getInteger("volume");

        for (int i = 0; i < lockLevels.length;i++){
            lockLevels[i] = prefs.getBoolean("level " + i);
        }

        for (int i = 0; i < lockSkins.length;i++){
            lockSkins[i] = prefs.getBoolean("skin " + i);
        }
    }

    public static void predefinedData(){
        volume = 0;
        prefs.putInteger("volume",(int)volume);
        prefs.putBoolean("mute",mute);

        for (int i = 0; i < lockLevels.length;i++){
            lockLevels[i] = true;
            prefs.putBoolean("level " + i, lockLevels[i]);
            prefs.flush();
        }

        lockLevels[0] = false;
        prefs.putBoolean("level " + 0, lockLevels[0]);
        prefs.flush();

        for (int i = 0; i < lockSkins.length;i++){
            lockSkins[i] = true;
            prefs.putBoolean("skin " + i, lockSkins[i]);
            prefs.flush();
        }

        lockSkins[0] = false;
        prefs.putBoolean("skin " + 0, lockSkins[0]);
        prefs.flush();
    }
}
