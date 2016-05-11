package com.lpoo.blockboy.logic;

/**
 * Class that represents the hero of the game
 */
public class Hero extends GameElement {

    private boolean carryBock = false;

    /**
     * Hero's constructor
     *
     * @param x coordinate of the Hero
     * @param y coordinate of the Hero
     */
    Hero (int x, int y){
        super(x, y);
    }

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
