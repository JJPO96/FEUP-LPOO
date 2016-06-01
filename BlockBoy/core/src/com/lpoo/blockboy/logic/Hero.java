package com.lpoo.blockboy.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
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
    private enum State {STANDING, RUNNING, JUMPING, FALLING}

    private State currentState;
    private State previousState;
    private boolean carryBlock = false;
    private Boolean facingRight;

    // Textures
    private TextureRegion heroJumping;
    private TextureRegion heroFalling;
    private Animation heroRunning;
    private Animation heroStanding;
    private int radius = 32;

    protected CircleShape shape;
    private float stateTimer;

    /**
     * Hero's constructor
     *
     * @param screen where the Hero will be displayed
     */
    public Hero(GameScreen screen) {
        super(screen, "herosprite");
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
        // TODO - CORRIGIR POSIÇAO INICIAL DO HEROI CONSOANTE O MAPA ESCOLHIDO
        // Creating the body
        bodyDef = new BodyDef();
        bodyDef.position.set(200 / BlockBoy.PPM, 64 / BlockBoy.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        fixtureDef = new FixtureDef();
        shape = new CircleShape();
        shape.setRadius(radius / BlockBoy.PPM);
        fixtureDef.filter.categoryBits = BlockBoy.HERO_BIT;
        fixtureDef.filter.maskBits = BlockBoy.DEFAULT_BIT | BlockBoy.BLOCK_BIT;
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);

        // Creating sensor
        EdgeShape diagonal = new EdgeShape();
        diagonal.set(new Vector2(-33 / BlockBoy.PPM, 30 / BlockBoy.PPM), new Vector2(33 / BlockBoy.PPM, -32 / BlockBoy.PPM));
        fixtureDef.shape = diagonal;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData("heroSensorDiagonal");

        // Create textures
        loadTextures();
    }

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
    boolean bodysOverlaping(Coin coin) {
        if ((getX() + getWidth()) < coin.getX())
            return false;
        if (getX() > (coin.getX() + coin.getWidth()))
            return false;
        if ((getY() + getHeight()) < coin.getY())
            return false;
        if (getY() > (coin.getY() + coin.getHeight()))
            return false;

        return true;
    }

    // TODO - FALTA TERMINAR
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
        if (body.getLinearVelocity().y > 0)
            return State.JUMPING;
        else if (body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if (body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.STANDING;
    }

    public void run(float dist) {
        // Makes the hero run. Sets also a maximum speed
        if (body.getLinearVelocity().x > -3f && body.getLinearVelocity().x < 3f)
            body.applyLinearImpulse(new Vector2(dist, 0), body.getWorldCenter(), true);

        currentState = State.RUNNING;
    }

    public void jump() {
        if (currentState != State.JUMPING && body.getLinearVelocity().y == 0) {
            body.applyLinearImpulse(new Vector2(0, 3.5f), body.getWorldCenter(), true);
            currentState = State.JUMPING;
        }
    }

    // TODO - DECIDIR SE ESTAS FUNÇÕES SÃO OU NAO REUTILIZAVEIS USANDO-SE MAQUINA DE ESTADOS

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
    public boolean isCarryBlock() {
        return carryBlock;
    }
}
