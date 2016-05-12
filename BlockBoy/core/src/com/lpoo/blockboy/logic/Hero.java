package com.lpoo.blockboy.logic;

import com.badlogic.gdx.math.Vector2;
import com.lpoo.blockboy.gui.GameScreen;

/**
 * Class that represents the hero of the game
 */
public class Hero extends GameElement {

    private enum State {STANDING, RUNNING, JUMPING, FALLING, CARRYBLOCK}
    private State currentState;
    private State previousState;
    private boolean carryBock = false;

    /**
     * Hero's constructor
     *
     * @param screen where the Hero will be displayed
     */
    Hero (GameScreen screen){
        super(screen);
        currentState = State.STANDING;
        previousState = State.STANDING;
    }

    @Override
    public void initElement() {

    }

    @Override
    public void update() {

    }

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
