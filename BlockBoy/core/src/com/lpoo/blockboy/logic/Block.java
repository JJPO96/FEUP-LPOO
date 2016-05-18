package com.lpoo.blockboy.logic;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.lpoo.blockboy.gui.GameScreen;

/**
 * Class that represents a block
 */
public class Block extends GameElement {

    // TODO - CREATE HUD.ADDSCORE

    private boolean picked = false;
    private MapObject object;
    private TiledMap map;
    private Rectangle bounds;
    private PolygonShape shape;
    private Fixture fixture;

    private TextureRegion region;

    // TODO - CORRIGIR TODO A CLASSE
    // TODO - CORRIGIR CONSTRUTOR
    /**
     * Coin's constructor
     *
     * @param screen where the block will be displayed
     */
    public Block (GameScreen screen, MapObject object){
        super(screen, "box");
        this.object = object;
        this.map = screen.getMap();
        this.bounds = ((RectangleMapObject) object).getRectangle();

        init();
    }

    @Override
    public void init() {
        bodyDef = new BodyDef();
        shape = new PolygonShape();
        fixtureDef = new FixtureDef();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2) / GameScreen.PPM, (bounds.getY() + bounds.getHeight() / 2) / GameScreen.PPM);
        body = world.createBody(bodyDef);
        shape.setAsBox(bounds.getWidth() / 2 / GameScreen.PPM, bounds.getHeight() / 2 / GameScreen.PPM);
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);

        loadTextures();
    }

    @Override
    public void loadTextures() {
        // In game sprite size
        setBounds(0, 0, 64 / GameScreen.PPM, 64 / GameScreen.PPM);

        region = new TextureRegion(getTexture(), 4991, 966, 64, 64);
    }

    @Override
    public void update(float delta) {
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(region);
    }

    /**
     * Sets the block as picked by the Hero
     */
    public void setPicked(){
        picked = true;
    }

    /**
     * Verifies if the key is picked by the Hero
     *
     * @return true if the block is picked
     */
    public boolean isPicked(){
        return picked;
    }
}
