package com.lpoo.blockboy.logic;

import com.badlogic.gdx.maps.MapObject;
import com.lpoo.blockboy.gui.GameScreen;

/**
 * Class that represents a block
 */
public class Block extends GameElement {

    private boolean picked = false;
    private MapObject objet;

    /**
     * Block's constructor
     *
     * @param screen where the block will be displayed
     */
    public Block (GameScreen screen, MapObject object){
        super(screen);
        this.objet = object;
    }

    @Override
    public void init() {

    }

    @Override
    public void update(float delta) {

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