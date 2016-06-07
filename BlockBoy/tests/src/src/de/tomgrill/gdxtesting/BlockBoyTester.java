/*******************************************************************************
 * Copyright 2015 See AUTHORS file.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
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

import com.lpoo.blockboy.BlockBoy;
import com.lpoo.blockboy.gui.GameScreen;
import com.lpoo.blockboy.logic.Block;
import com.lpoo.blockboy.logic.Coin;
import com.lpoo.blockboy.logic.GameLogic;
import com.lpoo.blockboy.logic.Hero;

@RunWith(GdxTestRunner.class)
public class BlockBoyTester {

    private final BlockBoy GAME = new BlockBoy();
    private final String LEVELPATH = "levels/testinglevel.tmx";
    private GameScreen gameScreen = new GameScreen(GAME, LEVELPATH);

    @Test // Confirms the starting world's objects
    public void testStartingWorld() {
        assertEquals(0, gameScreen.getGameLogic().getCoinScore());
        assertEquals(1, gameScreen.getGameLogic().getCoins().size());
        assertEquals(2, gameScreen.getGameLogic().getBlocks().size());
        assertEquals(GameLogic.State.RUNNING, gameScreen.getGameLogic().getState());
    }

    @Test // Confirms hero's starting state
    public void testStartingHero() {
        assertEquals(Hero.State.STANDING, gameScreen.getGameLogic().getHero().getState());
        assertTrue(!gameScreen.getGameLogic().getHero().hasBlock());
    }

    @Test // Confirms block's starting state
    public void testStartingCoins() {
        // Confirms block's starting state
        for (Coin coin : gameScreen.getGameLogic().getCoins()) {
            assertTrue(!coin.isScored());
            assertTrue(!coin.isPicked());
        }
    }

    @Test // Confirms block's starting state
    public void testStartingBlocks() {
        for (Block block : gameScreen.getGameLogic().getBlocks()) {
            assertTrue(!block.hasHeroCollision());
            assertTrue(!block.isPicked());
        }
    }

    @Test // Confirms if the hero can run
    public void testRunHero() {
        float startingPosX = gameScreen.getGameLogic().getHero().getBody().getPosition().x;
        gameScreen.getGameLogic().getHero().run(0.5f);
        gameScreen.updateTesting(0.1f);
        assertNotEquals(gameScreen.getGameLogic().getHero().getBody().getPosition().x, startingPosX);
    }

    @Test // Confirms if the hero can jump
    public void testJumpHero() {
        // The hero starts standing
        assertEquals(Hero.State.STANDING, gameScreen.getGameLogic().getHero().getState());
        gameScreen.getGameLogic().getHero().jump();
        gameScreen.updateTesting(0.1f);
        // The hero is now jumping
        assertEquals(Hero.State.JUMPING, gameScreen.getGameLogic().getHero().getState());
    }

    @Test
    public void testPickCoin() {
        // Confirms the coins are not picked yet
        assertEquals(0, gameScreen.getGameLogic().getCoinScore());

        for (Coin coin : gameScreen.getGameLogic().getCoins()) {
            assertTrue(!coin.isScored());
            assertTrue(!coin.isPicked());
        }

        // Hero moves to the left side (same as the coin)
        gameScreen.getGameLogic().getHero().run(-20f);
        gameScreen.updateTesting(0.1f);
        gameScreen.updateTesting(0.1f);
        gameScreen.updateTesting(0.1f);

        assertEquals(1, gameScreen.getGameLogic().getCoinScore());

        // Confirms that the coin is now picked
        for (Coin coin : gameScreen.getGameLogic().getCoins()) {
            assertTrue(coin.isScored());
            assertTrue(coin.isPicked());
        }
    }

    @Test // Confirms if the hero can pick a block at left side
    public void testPickBlockLeft() {
        // Confirms the hero starts the game without a block
        assertTrue(!gameScreen.getGameLogic().getHero().hasBlock());

        // Confirms the hero is not currently colliding with a block
        for (Block block : gameScreen.getGameLogic().getBlocks()) {
            assertTrue(!block.hasHeroCollision());
        }

        // Hero moves to the left to close to the block
        gameScreen.getGameLogic().getHero().run(-40f);
        gameScreen.updateTesting(0.1f);
        gameScreen.updateTesting(0.1f);

        // Confirms the hero is facing the left side (the same as the block)
        assertTrue(!gameScreen.getGameLogic().getHero().isFacingRight());

        // Confirms the hero is now colliding with the block
        assertTrue(gameScreen.getGameLogic().getBlocks().get(1).hasHeroCollision());

        // Sets the hero to try to move a block
        gameScreen.getGameLogic().setMoveBlock(true);
        gameScreen.updateTesting(0.1f);

        assertTrue(gameScreen.getGameLogic().getBlocks().get(1).isPicked());

        // Confirms that the hero picked a block
        assertTrue(gameScreen.getGameLogic().getHero().hasBlock());
    }

    @Test // Confirms if the hero can pick a block at right side
    public void testPickBlockRight() {
        // Confirms the hero starts the game without a block
        assertTrue(!gameScreen.getGameLogic().getHero().hasBlock());

        // Confirms the hero is not currently colliding with a block
        for (Block block : gameScreen.getGameLogic().getBlocks()) {
            assertTrue(!block.hasHeroCollision());
        }

        // Confirms the hero is facing the right side (the same as the block)
        assertTrue(gameScreen.getGameLogic().getHero().isFacingRight());

        // Hero moves to the right to close to the block
        gameScreen.getGameLogic().getHero().run(0.5f);
        gameScreen.updateTesting(0.1f);

        // Confirms the hero is now colliding with the block
        assertTrue(gameScreen.getGameLogic().getBlocks().get(0).hasHeroCollision());

        // Sets the hero to try to move a block
        gameScreen.getGameLogic().setMoveBlock(true);
        gameScreen.updateTesting(0.1f);

        assertTrue(gameScreen.getGameLogic().getHero().isFacingRight());
        assertTrue(gameScreen.getGameLogic().getBlocks().get(0).isPicked());

        // Confirms that the hero picked a block
        assertTrue(gameScreen.getGameLogic().getHero().hasBlock());
    }

    @Test // Confirms the hero can drop a block
    public void testDropBlock() {
        /* First the hero will have to pick the block */

        // Confirms the hero starts the game without a block
        assertTrue(!gameScreen.getGameLogic().getHero().hasBlock());

        // Hero moves to the right to close to the block
        gameScreen.getGameLogic().getHero().run(0.5f);
        gameScreen.updateTesting(0.1f);

        // Sets the hero to try to move a block
        gameScreen.getGameLogic().setMoveBlock(true);
        gameScreen.updateTesting(0.1f);

        // Confirms that the hero picked a block
        assertTrue(gameScreen.getGameLogic().getHero().hasBlock());
        assertTrue(gameScreen.getGameLogic().getBlocks().get(0).isPicked());

        /* Now that the hero has a block, will try to drop it  */

        // Sets the hero to move the block
        gameScreen.getGameLogic().getHero().run(-0.1f);
        gameScreen.getGameLogic().setMoveBlock(true);
        gameScreen.updateTesting(0.1f);

        // Confirms that the hero picked a block
        assertTrue(!gameScreen.getGameLogic().getHero().hasBlock());
        assertTrue(!gameScreen.getGameLogic().getBlocks().get(0).isPicked());
    }

    @Test // Confirms the hero can win the game
    public void testWinGame() {
        gameScreen.getGameLogic().getHero().setArriveExit(true);
        assertEquals(Hero.State.WIN, gameScreen.getGameLogic().getHero().getState());
        gameScreen.getGameLogic().update(0.1f);
        assertEquals(GameLogic.State.WIN, gameScreen.getGameLogic().getState());
    }
}