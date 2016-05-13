package com.lpoo.blockboy.logic;

import com.badlogic.gdx.maps.MapObject;
import com.lpoo.blockboy.gui.GameScreen;

/**
 * Class that represents a key
 */
public class Key extends GameElement {

    private boolean picked = false;
    private MapObject object;

    /**
     * Key's constructor
     *
     * @param screen where the key will be displayed
     */
    public Key (GameScreen screen, MapObject object){
        super(screen);
        this.object = object;
    }

    @Override
    public void init() {

    }

    @Override
    public void update(float delta) {

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
