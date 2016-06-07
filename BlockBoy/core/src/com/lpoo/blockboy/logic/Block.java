package com.lpoo.blockboy.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.lpoo.blockboy.BlockBoy;
import com.lpoo.blockboy.gui.GameScreen;

/**
 * Class that represents a block
 */
public class Block extends GameElement {
    private boolean picked = false;
    private boolean heroCollision = false;
    private boolean blockTopCollision = false;
    private boolean changeToStatic = false;
    private boolean isStatic = false;
    private boolean changeToDynamic= false;
    private boolean isDynamic = false;
    private MapObject object;
    private Rectangle bounds;
    private PolygonShape shape;
    private Fixture fixture;
    private Filter filter;
    private TextureRegion region;

    /**
     * Coin's constructor
     *
     * @param screen where the block will be displayed
     */
    public Block(GameScreen screen, MapObject object) {
        super(screen, "boxCrate_double");
        this.object = object;
        this.bounds = ((RectangleMapObject) object).getRectangle();
        init();
        setCategoryFilter(BlockBoy.BLOCK_BIT);
    }

    @Override
    public void init() {
        // Creating the body
        bodyDef = new BodyDef();
        shape = new PolygonShape();
        fixtureDef = new FixtureDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.linearDamping = 3.0f;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2) / BlockBoy.PPM, (bounds.getY() + bounds.getHeight() / 2) / BlockBoy.PPM);
        body = world.createBody(bodyDef);
        shape.setAsBox(bounds.getWidth() / 2 / BlockBoy.PPM, bounds.getHeight() / 2 / BlockBoy.PPM);
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);

        loadTextures();
    }

    @Override
    public void loadTextures() {
        // In game sprite size
        setBounds(0, 0, 64 / BlockBoy.PPM, 64 / BlockBoy.PPM);
        region = new TextureRegion(getTexture(), 2459, 195, 64, 64);
    }

    /**
     * Sets a new collision state
     *
     * @param collision
     */
    public void setHeroCollision(boolean collision) {
        this.heroCollision = collision;
    }

    /**
     * Sets the block static
     */
    public void setStatic() {
        this.changeToStatic = true;
    }

    /**
     * Sets the block dynamic
     */
    public void setDynamic() {
        this.changeToDynamic = true;
    }

    /**
     * Verfies if the block is colliding with a hero
     *
     * @return true if the block is colliding with the hero
     */
    public boolean hasHeroCollision() {
        return heroCollision;
    }

    public void setCategoryFilter(short filterBit) {
        filter = new Filter();
        filter.categoryBits = filterBit;
        filter.maskBits = BlockBoy.DEFAULT_BIT | BlockBoy.HERO_BIT | BlockBoy.BLOCK_BIT | BlockBoy.BLOCK_PICKED_BIT |
                BlockBoy.AIRGROUND_BIT | BlockBoy.BRICK_BIT | BlockBoy.EXIT_BIT;
        fixture.setFilterData(filter);
    }

    /**
     * Changes block to a new position
     *
     * @param x coordinate
     * @param y coordinate
     */
    public void setBodyPosition(float x, float y) {
        this.body.setTransform(x, y, body.getAngle());
    }

    /**
     *  Updates block state
     *
     * @param delta
     */
    @Override
    public void update(float delta) {
        if (changeToStatic && !isStatic) {
            body.setType(BodyDef.BodyType.StaticBody);
            changeToStatic = false;
            isDynamic = false;
            isStatic = true;
        } else if (changeToDynamic && !isDynamic){
            body.setType(BodyDef.BodyType.DynamicBody);
            changeToDynamic = false;
            isDynamic = true;
            isStatic = false;
        } else {
            setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
            setRegion(region);
        }
    }

    /**
     * Sets true if a block in collision with this block in the top side
     *
     * @param above
     */
    public void setBlockCollisionAbove(boolean above){
        this.blockTopCollision = above;
    }

    /**
     * Returns true if the block has another block above colliding
     *
     * @return true if the block has another block above colliding
     */
    public boolean getBlockTopCollision(){
        return blockTopCollision;
    }

    /**
     * Sets the block as picked by the Hero
     */
    public void setPicked(boolean pick) {
        this.picked = pick;

        if (picked)
            setCategoryFilter(BlockBoy.BLOCK_PICKED_BIT);
        else
            setCategoryFilter(BlockBoy.BLOCK_BIT);
    }

    /**
     * Verifies if the key is picked by the Hero
     *
     * @return true if the block is picked
     */
    public boolean isPicked() {
        return picked;
    }
}
