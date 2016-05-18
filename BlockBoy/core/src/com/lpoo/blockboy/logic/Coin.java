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
 * Class that represents a key
 */
public class Coin extends GameElement {

    // TODO - CREATE HUD.ADDSCORE

    private boolean picked = false;
    private MapObject object;
    private TiledMap map;
    private Rectangle bounds;
    private PolygonShape shape;
    private Fixture fixture;

    private Animation coinAnim;
    private TextureRegion region;
    private float stateTimer;

    // TODO - CORRIGIR TODO A CLASSE
    // TODO - CORRIGIR CONSTRUTOR
    /**
     * Coin's constructor
     *
     * @param screen where the key will be displayed
     */
    public Coin (GameScreen screen, MapObject object){
        super(screen, "coinpack");
        this.object = object;
        this.map = screen.getMap();
        this.bounds = ((RectangleMapObject) object).getRectangle();
        this.stateTimer = 0;

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
        Array<TextureRegion> frames = new Array<TextureRegion>();
        // In game sprite size
        setBounds(0, 0, 32 / GameScreen.PPM, 32 / GameScreen.PPM);

        // Creates coin animation
        for (int i = 0; i < 10; i++){
            frames.add(new TextureRegion(getTexture(), 1+ i*496, 1, 496, 496 ));
        }

        coinAnim = new Animation(0.50f, frames);
    }

    @Override
    public void update(float delta) {
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(delta));
    }

    public TextureRegion getFrame(float delta){
        TextureRegion region;
        region = coinAnim.getKeyFrame(stateTimer, true);
        stateTimer = stateTimer + delta;

        return region;
    }

    /**
     * Sets the key as picked by the Hero
     */
    public void setPicked(){
        picked = true;
    }

    /**
     * Verifies if the key is picked by the Hero
     *
     * @return true if the key is picked
     */
    public boolean isPicked(){
        return picked;
    }
}
