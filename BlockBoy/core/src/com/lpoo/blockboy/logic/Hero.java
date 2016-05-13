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
        init();
    }

    /**
     * Initializes the hero and creates its body
     */
    @Override
    public void init() {
        // TODO - CORRIGIR POSIÇAO INICILA DO HEROI CONSOANTE O MAPA ESCOLHIDO
        bodyDef = new BodyDef();
        bodyDef.position.set(200/ GameScreen.PPM, 64/ GameScreen.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        fixtureDef = new FixtureDef();
        shape = new CircleShape();
        shape.setRadius(16 / GameScreen.PPM);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }

    @Override
    public void update() {

    }

    public void run(float dist){
        body.applyLinearImpulse(new Vector2(dist, 0), body.getWorldCenter(), true);
        currentState = State.RUNNING;
    }

    public void jump(){
        if (currentState != State.JUMPING ) {
            body.applyLinearImpulse(new Vector2(0, 4f), body.getWorldCenter(), true);
            currentState = State.JUMPING;
        }

        // TODO - REMOVER/CORRIGIR - HEROI SEM ISTO NAO SALTA NA VERTICAL QD ESTÁ PARADO
        currentState = State.STANDING;
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
