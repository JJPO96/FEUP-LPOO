package com.lpoo.blockboy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoo.blockboy.gui.*;

public class BlockBoy extends Game {

	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		// TODO - MUDAR PARA O MENU SCREEN NA VERSAO FINAL PARA MOSTRAR MENU
		// CRIAR AQUI UM GAMELOOP - WHILE GAME IS NOT OVER - UPDATE SCREEN IN USE
        setScreen(new GameScreen(this));
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
