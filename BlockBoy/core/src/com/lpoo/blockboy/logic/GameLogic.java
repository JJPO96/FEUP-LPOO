package com.lpoo.blockboy.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoo.blockboy.BlockBoy;
import com.lpoo.blockboy.gui.GameScreen;

import java.util.ArrayList;

/**
 * Created by Manuel Gomes on 12/05/2016.
 */
public class GameLogic {

    public enum State {RUNNING, WIN, LOOSE}
    private Hero hero;
    private ArrayList<Coin> coins;
    private ArrayList<Block> blocks;
    private int coinScore;
    private boolean moveBlock = false;
    private State state;

    private GameScreen screen;
    private World world;

    /**
     * GameLogic constructor
     *
     * @param screen of the game
     */
    public GameLogic(GameScreen screen) {
        this.screen = screen;
        this.world = screen.getWorld();
        this.coinScore = 0;
        init();
        this.state = State.RUNNING;
    }

    public void init() {
        coins = new ArrayList<Coin>();
        blocks = new ArrayList<Block>();

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //  Create ground
        for (MapObject object : screen.getMap().getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.linearDamping = 1.0f;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / BlockBoy.PPM, (rect.getY() + rect.getHeight() / 2) / BlockBoy.PPM);
            body = world.createBody(bodyDef);
            shape.setAsBox(rect.getWidth() / 2 / BlockBoy.PPM, rect.getHeight() / 2 / BlockBoy.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = BlockBoy.DEFAULT_BIT;
            body.createFixture(fdef);
        }

        // Create coins
        for (MapObject object : screen.getMap().getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            coins.add(new Coin(screen, object));
        }

        // Create exit
        for (MapObject object : screen.getMap().getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / BlockBoy.PPM, (rect.getY() + rect.getHeight() / 2) / BlockBoy.PPM);
            body = world.createBody(bodyDef);
            shape.setAsBox(rect.getWidth() / 2 / BlockBoy.PPM, rect.getHeight() / 2 / BlockBoy.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = BlockBoy.EXIT_BIT;
            body.createFixture(fdef);
        }

        // Create blocks
        for (MapObject object : screen.getMap().getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            blocks.add(new Block(screen, object));
        }

        // Create bricks
        for (MapObject object : screen.getMap().getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / BlockBoy.PPM, (rect.getY() + rect.getHeight() / 2) / BlockBoy.PPM);
            body = world.createBody(bodyDef);
            shape.setAsBox(rect.getWidth() / 2 / BlockBoy.PPM, rect.getHeight() / 2 / BlockBoy.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = BlockBoy.BRICK_BIT;
            body.createFixture(fdef);
        }
        // Create Hero
        for (MapObject object : screen.getMap().getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
            hero = new Hero(screen, object);
        }

        //  Create air ground
        for (MapObject object : screen.getMap().getLayers().get(9).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.linearDamping = 1.0f;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / BlockBoy.PPM, (rect.getY() + rect.getHeight() / 2) / BlockBoy.PPM);
            body = world.createBody(bodyDef);
            shape.setAsBox(rect.getWidth() / 2 / BlockBoy.PPM, rect.getHeight() / 2 / BlockBoy.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = BlockBoy.AIRGROUND_BIT;
            body.createFixture(fdef);
        }
    }

    /**
     * Calculates hero overlapping for each coin still not picked
     */
    void checkCoinPicking() {
        for (Coin coin : coins) {
            if (!coin.isPicked())
                if (hero.bodysOverlapping(coin)) {
                    coin.setCollision(true);
                }
        }
    }

    /**
     * Hero attempts to pick the block
     */
    void heroPickBlock() {
        for (Block block : blocks) {
            if (block.hasHeroCollision() && !hero.hasBlock()) {
                if (hero.isFacingRight()) {
                    if (hero.getX() < block.getX()) {
                        block.setBodyPosition(hero.getBody().getPosition().x, hero.getBody().getPosition().y + hero.getHeight());
                        hero.setCarryBlock(true);
                        block.setDynamic();
                        block.setPicked(true);
                    }
                } else if (hero.getX() > block.getX()) {
                    block.setBodyPosition(hero.getBody().getPosition().x, hero.getBody().getPosition().y + hero.getHeight());
                    hero.setCarryBlock(true);
                    block.setDynamic();
                    block.setPicked(true);
                }
            }
        }

        moveBlock = false;
    }

    /**
     * Hero attempts to drop the block
     */
    void heroDropBlock() {
        for (Block block : blocks) {
            if (block.isPicked()) {
                if (hero.isFacingRight()) {
                    block.setBodyPosition(hero.getBody().getPosition().x + hero.getHeight() + 2 / 10,
                            hero.getBody().getPosition().y + hero.getHeight() + 2 / 10);
                    hero.setCarryBlock(false);
                    block.setPicked(false);
                } else {
                    block.setBodyPosition(hero.getBody().getPosition().x - hero.getHeight() - 2 / 10,
                            hero.getBody().getPosition().y + hero.getHeight() + 2 / 10);
                    hero.setCarryBlock(false);
                    block.setPicked(false);
                }

                break;
            }
        }
    }

    /**
     * Verifies if the Hero is currently above a block
     *
     * @param block to be checked
     * @return true if the Hero is above of the block; false otherwise
     */
    public boolean checkHeroAboveBlock(Block block) {
        if (hero.getBody().getPosition().y > block.getBody().getPosition().y)
            return true;

        return false;
    }

    /**
     * Updates the game
     */
    public void update(float delta) {
        // Updates Hero
        hero.update(delta);

        if(hero.getState() == Hero.State.WIN)
            this.state = State.WIN;
        else if(hero.getState() == Hero.State.DEAD)
            this.state = State.LOOSE;

        // Updates coins
        for (Coin coin : coins) {
            coin.update(delta);
            if (coin.isPicked() && !coin.isScored()) {
                coinScore++;
                coin.setScored();
            }
        }

        // Verifies if was received user's input to move a block
        if (moveBlock) {
            moveBlock = false;

            if (hero.hasBlock())
                heroDropBlock();
            else
                heroPickBlock();
        }

        // Updates blocks
        for (Block block : blocks) {
            if (checkHeroAboveBlock(block))
                block.setStatic();
            else
                block.setDynamic();

            if (block.isPicked())
                block.setBodyPosition(hero.getBody().getPosition().x, hero.getBody().getPosition().y + hero.getHeight());

            block.update(delta);
        }

        // Picks coins if possible
        checkCoinPicking();
    }

    public Hero getHero() {
        return hero;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public int getCoinScore() {
        return coinScore;
    }

    public void setMoveBlock(boolean move) {
        this.moveBlock = move;
    }

    /**
     * Returns the state of the game
     *
     * @return the state of the game
     */
    public State getState(){
        return  state;
    }
}
