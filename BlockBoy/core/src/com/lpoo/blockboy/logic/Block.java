package com.lpoo.blockboy.logic;

/**
 * Class that represents a block
 */
public class Block extends GameElement {

    private boolean picked = false;

    /**
     * Box's constructor
     *
     * @param x coordinate of the key
     * @param y coordinate of the key
     */
    Block(int x, int y){
        super(x, y);
    }

    /**
     * Sets the block as picked by the Hero
     */
    public void setPicked(){
        picked = true;
    }

    /**
     * Verifies if the block is picked by the Hero
     *
     * @return true if the block is picked
     */
    public boolean isPicked(){
        return picked;
    }
}