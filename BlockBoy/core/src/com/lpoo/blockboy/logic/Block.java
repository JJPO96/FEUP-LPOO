package com.lpoo.blockboy.logic;

import com.lpoo.blockboy.gui.GameScreen;

/**
 * Class that represents a block
 */
public class Block extends GameElement {

    private boolean picked = false;

    /**
     * Block's constructor
     *
     * @param screen where the block will be displayed
     */
    Block (GameScreen screen){
        super(screen);
    }

    @Override
    public void initElement() {

    }

    @Override
    public void update() {

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