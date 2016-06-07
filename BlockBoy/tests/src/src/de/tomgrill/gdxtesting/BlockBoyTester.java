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

package src.de.tomgrill.gdxtesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.lpoo.blockboy.BlockBoy;
import com.lpoo.blockboy.gui.GameScreen;
import com.lpoo.blockboy.logic.GameLogic;
import com.lpoo.blockboy.logic.Hero;

@RunWith(GdxTestRunner.class)
public class BlockBoyTester {

    private final BlockBoy game = new BlockBoy();
    private final String levelPath = "levels/level1.tmx";
    private final GameScreen gameScreen = new GameScreen(game, levelPath);
    private final GameLogic gameLogic = new GameLogic(gameScreen);

    // Verificar se heroi consegue andar/saltar
    // Verificar se o heroi nao consegue passar pelos obstáculos
    // Verficar se o heroi consegue colocar blocos uns por cima dos outros
    // Verificar se o heroi consegue saltar para cima de pequenos obstaculos
    // Verificar se o heroi consegue apanhar um block
    // Verificar se o heroi consegue pousar um block
    // Verificar se o heroi consegue apanhar as moedas


    @Test
    public void testStartingWorld(){
        assertEquals(0, gameLogic.getCoinScore());
        assertEquals(2, gameLogic.getCoins().size());
        assertEquals(2, gameLogic.getBlocks().size());
        assertEquals(GameLogic.State.RUNNING, gameLogic.getState());
    }

    @Test
    public void testStartingHero(){
        assertEquals(Hero.State.STANDING, gameLogic.getHero().getState());
        assertTrue(!gameLogic.getHero().hasBlock());
    }

    @Test
    public void testWinGame(){
        gameLogic.getHero().setArriveExit(true);
        assertEquals(Hero.State.WIN, gameLogic.getHero().getState());
        gameLogic.update(0.1f);
        assertEquals(GameLogic.State.WIN, gameLogic.getState());
    }
}