package com.lpoo.blockboy.logic;

import com.lpoo.blockboy.gui.GameScreen;

/**
 * Class that represents a key
 */
public class Key extends GameElement {

    private boolean picked = false;

    /**
     * Key's constructor
     *
     * @param screen where the key will be displayed
     */
    public Key (GameScreen screen){
        super(screen);
    }

    @Override
    public void initElement() {

    }

    @Override
    public void update() {

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
