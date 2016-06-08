package com.lpoo.blockboy.logic;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
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
    private final int SPRITESIZE = 64;
    private boolean picked = false;
    private boolean heroCollision = false;
    private boolean blockCollision = false;
    private boolean brickCollision = false;
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

    /**
     * Initiates the block's variables
     */
    @Override
    public void init() {
        // Creates the body
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

    /**
     * Loads the block's textures
     */
    @Override
    public void loadTextures() {
        // In game sprite size
        setBounds(0, 0, SPRITESIZE / BlockBoy.PPM, SPRITESIZE / BlockBoy.PPM);
        region = new TextureRegion(getTexture(), 2459, 195, SPRITESIZE, SPRITESIZE);
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

    /**
     * Sets a new category filter of the coin used for detecting collisions with the coin
     *
     * @param filterBit
     */
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

    /**
     * Sets a new collision state in relation to a brick
     *
     * @param collision
     */
    public void setBrickCollision(boolean collision){
        this.brickCollision = collision;
    }

    /**
     * Returns the brick collision state
     *
     * @return true if the block  is colliding with a brick
     */
    public  boolean getBrickCollision(){
        return brickCollision;
    }

    /**
     * Sets a new collision state in relation to a block
     *
     * @param collision
     */
    public void setBlockCollision(boolean collision){
        this.blockCollision = collision;
    }

    /**
     * Returns the brick collision state
     *
     * @return true if the block  is colliding with a block
     */
    public  boolean getBlockCollision(){
        return blockCollision;
    }

}
