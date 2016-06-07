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
import static org.junit.Assert.assertNotEquals;
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

    private final BlockBoy GAME = new BlockBoy();
    private final String LEVELPATH= "levels/testinglevel.tmx";
    private GameScreen gameScreen = new GameScreen(GAME, LEVELPATH);
    private GameLogic gameLogic = new GameLogic(gameScreen);

    @Test // Confirms the starting world's objects
    public void testStartingWorld(){
        assertEquals(0, gameLogic.getCoinScore());
        assertEquals(1, gameLogic.getCoins().size());
        assertEquals(1, gameLogic.getBlocks().size());
        assertEquals(GameLogic.State.RUNNING, gameLogic.getState());
    }

    @Test // Confirms hero's starting state
    public void testStartingHero(){
        assertEquals(Hero.State.STANDING, gameLogic.getHero().getState());
        assertTrue(!gameLogic.getHero().hasBlock());
    }

    @Test // Confirms block's starting state
    public void testStartingBlocks(){
        for (Block block: gameLogic.getBlocks()){
            assertTrue(!block.hasHeroCollision());
            assertTrue(!block.isPicked());
        }
    }

    @Test // Confirms block's starting state
    public void testStartingCoins() {
        // Confirms block's starting state
        for (Coin coin: gameLogic.getCoins()){
            assertTrue(!coin.isScored());
            assertTrue(!coin.isPicked());
        }
    }

    @Test // Confirms if the hero can run
    public void testRunHero() {
        float startingPosX = gameLogic.getHero().getBody().getPosition().x;
        gameLogic.getHero().run(0.5f);
        gameScreen.updateTesting(0.1f);
        assertNotEquals(gameLogic.getHero().getBody().getPosition().x, startingPosX);
    }

    @Test // Confirms if the hero can jump
    public void testJumpHero() {
        BlockBoy.testingMode = true;
        // The hero starts standing
        assertEquals(Hero.State.STANDING, gameLogic.getHero().getState());
        gameLogic.getHero().jump();
        gameScreen.updateTesting(0.1f);
        // The hero is now jumping
        assertEquals(Hero.State.JUMPING, gameLogic.getHero().getState());
    }


    @Test // Confirms if the hero can pick a block
    public void testPickBlock() {
        gameLogic = new GameLogic(gameScreen);

        // Confirms the hero starts the game without a block
        assertTrue(!gameLogic.getHero().hasBlock());

        // Confirms the hero is not currently colliding with a block
        for (Block block: gameLogic.getBlocks()){
            assertTrue(!block.hasHeroCollision());
        }

        // Confirms the hero is facing the right side (the same as the block)
        assertTrue(gameLogic.getHero().isFacingRight());

        // Hero moves to the right to close to the block
        gameLogic.getHero().run(0.5f);
        gameScreen.updateTesting(0.1f);

        // Confirms the hero is now colliding with the block
        for (Block block: gameLogic.getBlocks()){
            assertTrue(block.hasHeroCollision());
        }

        gameLogic.setMoveBlock(true);
        gameLogic.update(0.1f);
        //gameScreen.updateTesting(0.1f);

        /*for (Block block: gameLogic.getBlocks()){
            assertTrue(block.isPicked());
        }*/

        // Confirms the hero starts the game without a block
        //assertTrue(gameLogic.getHero().hasBlock());
    }

    @Test
    public void testDropBlock() {

    }

    @Test
    public void testPickCoin() {
        BlockBoy.testingMode = true;
        gameLogic = new GameLogic(gameScreen);

        // Confirms the hero starts the game without a block
        assertTrue(!gameLogic.getHero().hasBlock());

        // Confirms the coins are not picked yet
        for (Coin coin: gameLogic.getCoins()){
            assertTrue(!coin.isScored());
            assertTrue(!coin.isPicked());
        }

        // Hero moves to the left side (same as the coin)
        gameLogic.getHero().run(-13f);
        gameScreen.updateTesting(0.1f);
        gameScreen.updateTesting(0.1f);
        gameScreen.updateTesting(0.1f);

        Gdx.app.log(""+gameLogic.getHero().getBody().getPosition().x, "");
        Gdx.app.log(""+gameLogic.getHero().getWidth(), "");
        Gdx.app.log(""+gameLogic.getCoins().get(0).getBody().getPosition().x, "");
        Gdx.app.log(""+gameLogic.getCoins().get(0).getWidth(), "");

        // Confirms the coins are now picked yet
        for (Coin coin: gameLogic.getCoins()){
            assertTrue(coin.isScored());
            assertTrue(coin.isPicked());
        }

        //gameLogic.setMoveBlock(true);
        //gameLogic.update(0.1f);
    }

    @Test // Confirms the hero can win the game
    public void testWinGame(){
        gameLogic.getHero().setArriveExit(true);
        assertEquals(Hero.State.WIN, gameLogic.getHero().getState());
        gameLogic.update(0.1f);
        assertEquals(GameLogic.State.WIN, gameLogic.getState());
    }
}