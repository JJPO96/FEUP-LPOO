/*******************************************************************************
 * Copyright 2015 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package src.de.tomgrill.gdxtesting.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;
import com.lpoo.blockboy.BlockBoy;
import com.lpoo.blockboy.gui.GameScreen;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.lpoo.blockboy.logic.GameLogic;

import src.de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class AssetExistsExampleTest {

	// Verificar se heroi consegue andar/saltar
	// Verificar se o heroi nao consegue passar pelos obstáculos
	// Verficar se o heroi consegue colocar blocos uns por cima dos outros
	// Verificar se o heroi consegue saltar para cima de pequenos obstaculos
	// Verificar se o heroi consegue apanhar um block
	// Verificar se o heroi consegue pousar um block
	// Verificar se o heroi consegue apanhar as moedas

	@Test
	public void badlogicLogoFileExists() {
		//assertTrue("This test will only pass when the badlogic.jpg file coming with a new project setup has not been deleted.", Gdx.files
				//.internal("../android/assets/menu/lvlMenu.png").exists());
	}

	@Test
	public void testA(){
		BlockBoy game = new BlockBoy();
		assertEquals(game.levelInd, 0);
	}

	@Test
	public void testB(){
		BlockBoy game = new BlockBoy();
		GameScreen gameScreen = new GameScreen(game);
		GameLogic gameLogic = new GameLogic(gameScreen);
		gameLogic.update(0.1f);
	}
}
