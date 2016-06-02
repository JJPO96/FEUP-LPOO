package com.lpoo.blockboy.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
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

    private Hero hero;
    private boolean pickBlock = false;
    private ArrayList<Coin> coins;
    private ArrayList<Block> blocks;
    private int coinScore;
    private Boolean running = true;

    private GameScreen screen;
    private World world;

    public GameLogic(GameScreen screen) {
        // TODO - implementar aqui a logica do jogo
        this.screen = screen;
        this.world = screen.getWorld();
        this.coinScore = 0;
        init();
    }

    // TODO - CRIAR TODOS OS ELEMENTOS DE JOGO AQUI
    public void init() {
        hero = new Hero(screen);
        coins = new ArrayList<Coin>();
        blocks = new ArrayList<Block>();

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //  Create ground
        for (MapObject object : screen.getMap().getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.linearDamping = 1.0f;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / BlockBoy.PPM, (rect.getY() + rect.getHeight() / 2) / BlockBoy.PPM);
            body = world.createBody(bodyDef);
            shape.setAsBox(rect.getWidth() / 2 / BlockBoy.PPM, rect.getHeight() / 2 / BlockBoy.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        // Create coins
        for (MapObject object : screen.getMap().getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            coins.add(new Coin(screen, object));
        }

        // Create exit
        for (MapObject object : screen.getMap().getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / BlockBoy.PPM, (rect.getY() + rect.getHeight() / 2) / BlockBoy.PPM);
            body = world.createBody(bodyDef);
            shape.setAsBox(rect.getWidth() / 2 / BlockBoy.PPM, rect.getHeight() / 2 / BlockBoy.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        // Create blocks
        for (MapObject object : screen.getMap().getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            blocks.add(new Block(screen, object));
        }

        // Create bricks
        for (MapObject object : screen.getMap().getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / BlockBoy.PPM, (rect.getY() + rect.getHeight() / 2) / BlockBoy.PPM);
            body = world.createBody(bodyDef);
            shape.setAsBox(rect.getWidth() / 2 / BlockBoy.PPM, rect.getHeight() / 2 / BlockBoy.PPM);
            fdef.shape = shape;
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

    void heroPickBlock() {
        for (Block block : blocks) {
            if (block.hasCollision()) {
                if (hero.isFacingRight()) {
                    if (hero.getX() < block.getX()) {
                        block.setBodyPosition(hero.getBody().getPosition().x, hero.getBody().getPosition().y + hero.getHeight());
                        pickBlock = false;
                        hero.setCarryBlock(true);
                        block.setPicked(true);
                    }
                } else if (hero.getX() > block.getX()) {
                    block.setBodyPosition(hero.getBody().getPosition().x, hero.getBody().getPosition().y + hero.getHeight());
                    pickBlock = false;
                    hero.setCarryBlock(true);
                    block.setPicked(true);
                }
            }
        }
    }

    /**
     * Updates the game
     */
    public void update(float delta) {
        // Updates Hero
        hero.update(delta);

        // Updates coins
        for (Coin coin : coins) {
            coin.update(delta);
            if (coin.isPicked() && !coin.isScored()) {
                coinScore++;
                coin.setScored();
            }
        }

        // Verifies if was received user's input to pick a block
        if (pickBlock)
            heroPickBlock();

        // Updates blocks
        for (Block block : blocks) {
            if (block.isPicked())
                block.setBodyPosition(hero.getBody().getPosition().x, hero.getBody().getPosition().y + hero.getHeight());

            block.update(delta);
        }

        // Picks coins if possible
        checkCoinPicking();
    }

    public boolean isGameRunning() {
        return running;
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

    public void setPickBlock(boolean pick) {
        this.pickBlock = pick;
    }
}
