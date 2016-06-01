package com.lpoo.blockboy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
    public static final short COIN_BIT = 8;
    public static final short COIN_PICKED_BIT = 8;

    public SpriteBatch batch;
    public BitmapFont font;
    private FreeTypeFontGenerator generator;

    @Override
    public void create() {
        batch = new SpriteBatch();
        //TODO - APAGAR SE NAO FOR USADO
        initFonts();


        // TODO - MUDAR PARA O MENU SCREEN NA VERSAO FINAL PARA MOSTRAR MENU
        // USAR PATTERN VISITOR PARA DETEÇÃO DE COLISÕES ENTRE SPRITES?
        // CRIAR AQUI UM GAMELOOP - WHILE GAME IS NOT OVER - UPDATE SCREEN IN USE
        setScreen(new MainMenuScreen(this));
    }

    public void initFonts() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Akashi.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = 20;
        params.color = Color.WHITE;
        params.borderColor = Color.GOLDENROD;
        params.borderWidth = 2;
        font = generator.generateFont(params);
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {

        // Renders the screen active at the time
        super.render();
    }
}
