package com.lpoo.blockboy.logic;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.lpoo.blockboy.BlockBoy;
import com.lpoo.blockboy.gui.GameScreen;

/**
 * Class that represents the hero of the game
 */
public class Hero extends GameElement {
    public enum State {STANDING, RUNNING, JUMPING, FALLING, WIN, DEAD}

    private State currentState;
    private State previousState;
    private boolean heroDead = false;
    private boolean arriveExit = false;
    private boolean carryBlock = false;
    private Boolean facingRight;

    // Textures
    private TextureRegion heroJumping;
    private TextureRegion heroFalling;
    private Animation heroRunning;
    private Animation heroStanding;
    private Rectangle bounds;
    protected CircleShape shape;
    private float stateTimer;

    /**
     * Hero's constructor
     *
     * @param screen where the Hero will be displayed
     */
    public Hero(GameScreen screen, MapObject object) {
        super(screen, "herosprite");
        this.object = object;
        this.bounds = ((RectangleMapObject) object).getRectangle();
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        facingRight = true;
        init();
    }

    /**
     * Initializes the hero and creates its body
     */
    @Override
    public void init() {
        // Creating the body
        bodyDef = new BodyDef();
        shape = new CircleShape();
        fixtureDef = new FixtureDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2) / BlockBoy.PPM, (bounds.getY() + bounds.getHeight() / 2) / BlockBoy.PPM);
        body = world.createBody(bodyDef);
        shape.setRadius(bounds.getWidth() / 2 / BlockBoy.PPM);
        fixtureDef.filter.categoryBits = BlockBoy.HERO_BIT;
        fixtureDef.filter.maskBits = BlockBoy.DEFAULT_BIT | BlockBoy.BLOCK_BIT |
                BlockBoy.AIRGROUND_BIT | BlockBoy.BRICK_BIT | BlockBoy.EXIT_BIT;
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);

        // Creating the sensor in the body
        EdgeShape diagonal = new EdgeShape();
        diagonal.set(new Vector2(-33 / BlockBoy.PPM, 30 / BlockBoy.PPM), new Vector2(33 / BlockBoy.PPM, -30 / BlockBoy.PPM));
        fixtureDef.shape = diagonal;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData("heroSensorDiagonal");

        // Creating the textures
        loadTextures();
    }

    /**
     * Loads the textures
     */
    @Override
    public void loadTextures() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        // In game sprite size
        setBounds(0, 0, 48 / BlockBoy.PPM, 64 / BlockBoy.PPM);

        // Creates running animation
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(getTexture(), 1 + i * 307, 20, 307, 409));
        }

        heroRunning = new Animation(0.1f, frames);
        frames.clear();

        // Creates standing animation
        for (int i = 4; i < 6; i++) {
            frames.add(new TextureRegion(getTexture(), 1 + i * 307, 20, 307, 409));
        }

        heroStanding = new Animation(0.14f, frames);
        frames.clear();

        // Creates jumping texture
        heroJumping = new TextureRegion(getTexture(), 1 + 6 * 307, 20, 307, 409);

        // Creates falling texture
        heroFalling = new TextureRegion(getTexture(), 1 + 7 * 307, 20, 307, 409);
    }

    /**
     * Verifies if the Hero is overlapping a coin
     *
     * @param coin to be checked
     * @return true if the Hero is overlapping the coin
     */
    boolean bodysOverlapping(Coin coin) {
        if ((body.getPosition().x + getWidth())< coin.getBody().getPosition().x)
            return false;
        else if (body.getPosition().x > 20)//(coin.getBody().getPosition().x + coin.getWidth()))
            return false;
        else if ((body.getPosition().y + getHeight()) < coin.getBody().getPosition().y)
            return false;
        else if (body.getPosition().y > (coin.getBody().getPosition().y + coin.getHeight()))
            return false;
        else
            return true;
       /* if ((getX() + getWidth()) < coin.getX())
            return false;
        else if (getX() > (coin.getX() + coin.getWidth()))
            return false;
        else if ((getY() + getHeight()) < coin.getY())
            return false;
        else if (getY() > (coin.getY() + coin.getHeight()))
            return false;
        else
            return true;*/
    }

    /**
     * Updates Hero's state
     *
     * @param delta time
     */
    @Override
    public void update(float delta) {
        // Makes the sprite (hero itself) to move within the graphical body in the world
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(delta));
    }

    /**
     * Returns the current frame
     *
     * @param delta time passed
     * @return frame
     */
    public TextureRegion getFrame(float delta) {
        currentState = getState();
        TextureRegion region;

        switch (currentState) {
            case JUMPING:
                region = heroJumping;
                break;
            case FALLING:
                region = heroFalling;
                break;
            case RUNNING:
                region = heroRunning.getKeyFrame(stateTimer, true);
                break;
            case STANDING:
            default:
                region = heroStanding.getKeyFrame(stateTimer, true);
                break;
        }

        // If the hero is running or turning to the left
        if ((body.getLinearVelocity().x < 0 || !facingRight) && !region.isFlipX()) {
            // Flips the sprite (first parameter x, second y)
            region.flip(true, false);
            facingRight = false;
        } else if ((body.getLinearVelocity().x > 0 || facingRight) && region.isFlipX()) {
            region.flip(true, false);
            facingRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;

        return region;
    }

    /**
     * Returns the current state of the hero
     *
     * @return the state of the hero
     */
    public State getState() {
        if (arriveExit)
            return State.WIN;
        else if (heroDead)
            return State.DEAD;
        else if (body.getLinearVelocity().y > 0)
            return State.JUMPING;
        else if (body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if (body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.STANDING;
    }

    /**
     * Makes the hero run
     *
     * @param dist do be traveled
     */
    public void run(float dist) {
        // Makes the hero run. Also sets a maximum speed for the hero
        if (body.getLinearVelocity().x > -3f && body.getLinearVelocity().x < 3f)
            body.applyLinearImpulse(new Vector2(dist, 0), body.getWorldCenter(), true);

        currentState = State.RUNNING;
    }

    /**
     * Makes the hero jump
     */
    public void jump() {
        if (currentState != State.JUMPING && body.getLinearVelocity().y == 0) {
            if (!BlockBoy.mute && !BlockBoy.testingMode)
                BlockBoy.jumpSound.play();
            body.applyLinearImpulse(new Vector2(0, 3.6f), body.getWorldCenter(), true);
            currentState = State.JUMPING;
        }
    }

    /**
     * Changes the Hero state about carrying the box
     *
     * @param carry with the new state of the Hero
     */
    public void setCarryBlock(boolean carry) {
        carryBlock = carry;
    }

    /**
     * Verifies if the Hero is carrying a block
     *
     * @return true if the Hero is carrying a block
     */
    public boolean hasBlock() {
        return carryBlock;
    }

    /**
     * Verifies if the hero is facing right side
     *
     * @return true if the hero is facing the right side
     */
    public boolean isFacingRight() {
        return facingRight;
    }

    /**
     * Sets the exit as being visited
     *
     * @param arriveExit
     */
    public void setArriveExit(boolean arriveExit) {
        this.arriveExit = arriveExit;
    }
}
