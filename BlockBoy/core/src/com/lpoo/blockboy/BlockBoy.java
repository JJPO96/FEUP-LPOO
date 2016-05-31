package com.lpoo.blockboy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoo.blockboy.gui.*;

public class BlockBoy extends Game {
	// Variables used to keep aspect ratio for any screen size (screen resolution of the game)
	public static final int VWIDTH = 800;
	public static final int VHEIGHT = 520;
	public static final float PPM = 100;

	public SpriteBatch batch;
	public BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont(); //TODO - APGAR SE NAO FOR USADO - LibGDX's default Arial font.

		// TODO - MUDAR PARA O MENU SCREEN NA VERSAO FINAL PARA MOSTRAR MENU
		// USAR PATTERN VISITOR PARA DETEÇÃO DE COLISÕES ENTRE SPRITES?
		// CRIAR AQUI UM GAMELOOP - WHILE GAME IS NOT OVER - UPDATE SCREEN IN USE
        setScreen(new MainMenuScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
	}

	@Override
	public void render () {

		// Renders the screen active at the time
        super.render();
	}
}
