package com.lpoo.blockboy.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.lpoo.blockboy.BlockBoy;
import com.lpoo.blockboy.gui.GameScreen;

/**
 * Class that represents a block
 */
public class Block extends GameElement {
    private boolean picked = false;
    private MapObject object;
    private TiledMap map;
    private Rectangle bounds;
    private PolygonShape shape;
    private Fixture fixture;
    private Filter filter;

    private TextureRegion region;

    // TODO - CORRIGIR TODO A CLASSE
    // TODO - CORRIGIR CONSTRUTOR
    /**
     * Coin's constructor
     *
     * @param screen where the block will be displayed
     */
    public Block (GameScreen screen, MapObject object){
        super(screen, "boxCrate_double");
        this.object = object;
        this.map = screen.getMap();
        this.bounds = ((RectangleMapObject) object).getRectangle();
        init();
        setCategoryFilter(BlockBoy.BLOCK_BIT);
    }

    @Override
    public void init() {
        bodyDef = new BodyDef();
        shape = new PolygonShape();
        fixtureDef = new FixtureDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2) / BlockBoy.PPM, (bounds.getY() + bounds.getHeight() / 2) / BlockBoy.PPM);
        body = world.createBody(bodyDef);
        shape.setAsBox(bounds.getWidth() / 2 / BlockBoy.PPM, bounds.getHeight() / 2 / BlockBoy.PPM);
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);
        // TODO - APAGAR SE NAO USADO (SENSOR)
        fixture.setUserData(this);


        loadTextures();
    }

    @Override
    public void loadTextures() {
        // In game sprite size
        setBounds(0, 0, 64 / BlockBoy.PPM, 64 / BlockBoy.PPM);

        region = new TextureRegion(getTexture(), 2459, 195, 64, 64);
    }

    public void detectCollition(){
        Gdx.app.log("Block", "Collision");
    }

    public void setCategoryFilter(short filterBit){
        filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
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
