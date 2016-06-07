package com.lpoo.blockboy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoo.blockboy.gui.*;

public class BlockBoy extends Game {
    // Variables used to keep aspect ratio for any screen size (screen resolution of the game)
    public static final int VWIDTH = 800;
    public static final int VHEIGHT = 520;
    public static final float PPM = 100;

    public static float volume = 0;
    public static boolean mute = false;

    public static Preferences prefs;

    public static int levelInd = 0;
    public static int skinInd;
    public static int coinScore = 0;

    public static boolean[] lockLevels;
    public static boolean[] lockSkins;
    // Sounds
    public static Music bg_music;
    public static Sound btnClick;
    public static Sound jumpSound;
    public static Sound coinSound;
    public static Sound winSound;
    public static Sound gameOverSound;
    // Mask bits to detect collisions
    public static final short DEFAULT_BIT = 1;
    public static final short HERO_BIT = 2;
    public static final short BLOCK_BIT = 4;
    public static final short BLOCK_PICKED_BIT = 8;
    public static final short COIN_BIT = 16;
    public static final short AIRGROUND_BIT = 32;
    public static final short BRICK_BIT = 64;
    public static final short EXIT_BIT = 128;

    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        init();
        setScreen(new MainMenuScreen(this));
    }

    public void init(){
        lockLevels = new boolean[10];
        lockSkins = new boolean[3];
        bg_music = Gdx.audio.newMusic(Gdx.files.internal("sounds/bg.mp3"));
        btnClick = Gdx.audio.newSound(Gdx.files.internal("sounds/button.mp3"));
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("sounds/jump.mp3"));
        coinSound = Gdx.audio.newSound(Gdx.files.internal("sounds/coin.wav"));
        winSound = Gdx.audio.newSound(Gdx.files.internal("sounds/win.mp3"));
        gameOverSound = Gdx.audio.newSound(Gdx.files.internal("sounds/gameOver.mp3"));

        prefs = Gdx.app.getPreferences("BlockBoyPrefs");
        String name = prefs.getString("name", "No name stored");
        if (name.equals("No name stored")){
            predefinedData();
        }else {
            loadData();
        }

        if (!mute) {
            bg_music.play();
            bg_music.setVolume(BlockBoy.volume / 100);
            bg_music.setLooping(true);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }

    @Override
    public void render() {
        // Renders the screen active at the time
        super.render();
    }

    public static void saveData(){
        prefs.putString("name", "BlockBoy");
        prefs.putInteger("volume",(int)volume);
        prefs.putInteger("coins",coinScore);
        prefs.putBoolean("mute", mute);
        prefs.putInteger("skinInd", skinInd);

        for (int i = 0; i < lockLevels.length;i++){
            prefs.putBoolean("level " + i, lockLevels[i]);
        }

        for (int i = 0; i < lockSkins.length;i++){
            prefs.putBoolean("skin " + i, lockSkins[i]);
        }
        prefs.flush();
    }

    public static void loadData(){
        mute = prefs.getBoolean("mute");
        volume = prefs.getInteger("volume");
        skinInd = prefs.getInteger("skinInd");
        coinScore = prefs.getInteger("coins");

        for (int i = 0; i < lockLevels.length;i++){
            lockLevels[i] = prefs.getBoolean("level " + i);
        }

        for (int i = 0; i < lockSkins.length;i++){
            lockSkins[i] = prefs.getBoolean("skin " + i);
        }
    }

    public static void predefinedData(){
        volume = 50;
        mute = false;
        skinInd = 0;
        coinScore = 0;
        
        prefs.putInteger("volume",(int)volume);
        prefs.putInteger("coins",coinScore);
        prefs.putInteger("skinInd", skinInd);
        prefs.putBoolean("mute",mute);
        prefs.putString("name","BlockBoy");

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
