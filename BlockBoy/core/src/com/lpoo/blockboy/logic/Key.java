package com.lpoo.blockboy.logic;

/**
 * Class that represents a key
 */
public class Key extends GameElement {

    private boolean picked = false;

    /**
     * Key's constructor
     *
     * @param x coordinate of the key
     * @param y coordinate of the key
     */
    Key(int x, int y){
        super(x, y);
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
