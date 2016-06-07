package com.lpoo.blockboy.logic;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.lpoo.blockboy.BlockBoy;
import com.lpoo.blockboy.gui.GameScreen;

/**
 * Class that represents a coin
 */
public class Coin extends GameElement {
    private boolean pick = false;
    private boolean picked = false;
    private boolean scored = false;
    private MapObject object;
    private TiledMap map;
    private Rectangle bounds;
    private PolygonShape shape;
    private Fixture fixture;
    private Filter filter;
    private Animation coinAnim;
    private TextureRegion region;
    private float stateTimer;

    /**
     * Coin's constructor
     *
     * @param screen where the key will be displayed
     */
    public Coin(GameScreen screen, MapObject object) {
        super(screen, "coins");
        this.object = object;
        this.map = screen.getMap();
        this.bounds = ((RectangleMapObject) object).getRectangle();
        this.stateTimer = 0;
        init();
        setCategoryFilter(BlockBoy.COIN_BIT);
    }

    /**
     * Initiates the coin's variables
     */
    @Override
    public void init() {
        // Creating the body
        bodyDef = new BodyDef();
        shape = new PolygonShape();
        fixtureDef = new FixtureDef();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2) / BlockBoy.PPM, (bounds.getY() + bounds.getHeight() / 2) / BlockBoy.PPM);
        body = world.createBody(bodyDef);
        shape.setAsBox(bounds.getWidth() / 2 / BlockBoy.PPM, bounds.getHeight() / 2 / BlockBoy.PPM);
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);

        loadTextures();
    }

    /**
     * Loads coin's textures
     */
    @Override
    public void loadTextures() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        // In game sprite size
        setBounds(0, 0, 40 / BlockBoy.PPM, 40 / BlockBoy.PPM);

        // Creates coin animation
        for (int i = 0; i < 6; i++) {
            frames.add(new TextureRegion(getTexture(), 2459 + i * 130, 299, 130, 130));
        }

        // Takes frames and the frame rate
        coinAnim = new Animation(0.16f, frames);
    }

    /**
     * Sets the collision state of the coin
     *
     * @param collision
     */
    public void setCollision(boolean collision) {
        pick = collision;
    }

    /**
     * Sets a new category filter of the coin used for detecting collisions with the coin
     *
     * @param filterBit
     */
    public void setCategoryFilter(short filterBit) {
        filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    /**
     * Updates the coin's state
     *
     * @param delta time
     */
    @Override
    public void update(float delta) {
        if (pick && !picked) {
            world.destroyBody(this.body);
            picked = true;
        } else {
            setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
            setRegion(getFrame(delta));
        }
    }

    /**
     * Returns the TextureRegion corresponding to the current frame
     *
     * @param delta
     * @return TextureRegion of the current frame
     */
    public TextureRegion getFrame(float delta) {
        region = coinAnim.getKeyFrame(stateTimer, true);
        stateTimer = stateTimer + delta;

        return region;
    }

    /**
     * Verifies if the key is picked by the Hero
     *
     * @return true if the key is picked
     */
    public boolean isPicked() {
        return this.picked;
    }

    /**
     * Sets a new coin score state
     */
    public void setScored() {
        this.scored = true;
    }

    /**
     * Verifies if the coin is already scored
     *
     * @return true if the coin is scored
     */
    public boolean isScored() {
        return this.scored;
    }
}
