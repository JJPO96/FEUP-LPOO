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

import com.badlogic.gdx.Gdx;
import com.lpoo.blockboy.BlockBoy;
import com.lpoo.blockboy.gui.GameScreen;
import com.lpoo.blockboy.logic.Block;
import com.lpoo.blockboy.logic.Coin;
import com.lpoo.blockboy.logic.GameLogic;
import com.lpoo.blockboy.logic.Hero;

@RunWith(GdxTestRunner.class)
public class BlockBoyTester {

    private final BlockBoy game = new BlockBoy();
    private final String levelPath = "levels/testinglevel.tmx";
    private final GameScreen gameScreen = new GameScreen(game, levelPath);
    private final GameLogic gameLogic = new GameLogic(gameScreen);

    @Test
    public void testStartingWorld(){
        assertEquals(0, gameLogic.getCoinScore());
        assertEquals(1, gameLogic.getCoins().size());
        assertEquals(1, gameLogic.getBlocks().size());
        assertEquals(GameLogic.State.RUNNING, gameLogic.getState());
    }

    @Test
    public void testStartingHero(){
        assertEquals(Hero.State.STANDING, gameLogic.getHero().getState());
        assertTrue(!gameLogic.getHero().hasBlock());
    }

    @Test
    public void testStartingBlocks(){
        for (Block block: gameLogic.getBlocks()){
            assertTrue(!block.hasHeroCollision());
            assertTrue(!block.isPicked());
        }
    }

    @Test
    public void testStartingCoins() {
        for (Coin coin: gameLogic.getCoins()){
            assertTrue(!coin.isScored());
            assertTrue(!coin.isPicked());
        }
    }

    @Test
    public void testPickBlock() {

    }

    @Test
    public void testDropBlock() {

    }

    @Test
    public void testPickCoin() {
        Gdx.app.log(""+gameLogic.getHero().getBody().getPosition().x, "");
        gameLogic.getHero().run(0.5f);
        gameScreen.updateTesting(1);
        Gdx.app.log(""+gameLogic.getHero().getBody().getPosition().x, "");
    }



    @Test
    public void testWinGame(){
        gameLogic.getHero().setArriveExit(true);
        assertEquals(Hero.State.WIN, gameLogic.getHero().getState());
        gameLogic.update(0.1f);
        assertEquals(GameLogic.State.WIN, gameLogic.getState());
    }
}