package com.lpoo.blockboy.logic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.lpoo.blockboy.gui.GameScreen;

/**
 * Class that represents the hero of the game
 */
public class Hero extends GameElement {

    private enum State {STANDING, RUNNING, JUMPING, FALLING}
    private State currentState;
    private State previousState;
    private boolean carryBock = false;
    public Body b2body;

    /**
     * Hero's constructor
     *
     * @param screen where the Hero will be displayed
     */
    public Hero (GameScreen screen){
        super(screen);
        currentState = State.STANDING;
        previousState = State.STANDING;

        // Create the body of the Hero
        initElement();
    }

    @Override
    public void initElement() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/ GameScreen.PPM, 64/ GameScreen.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(16 / GameScreen.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

    @Override
    public void update() {

    }

    public void run(){}

    public void jump(){
        if (currentState != State.JUMPING ) {
            body.applyLinearImpulse(new Vector2(0, 2f), body.getWorldCenter(), true);
            currentState = State.JUMPING;
        }
    }

    // TODO - DECIDIR SE ESTAS FUNÇÕES SÃO OU NAO REUTILIZAVEIS USANDO-SE MAQUINA DE ESTADOS
    /**
     * Changes the Hero state about carrying the box
     *
     * @param carry with the new state of the Hero
     */
    public void setCarryBock(boolean carry){
        carryBock = carry;
    }

    /**
     * Verifies if the Hero is carrying a block
     *
     * @return true if the Hero is carrying a block
     */
    public boolean isCarryBock(){
        return carryBock;
    }
}
