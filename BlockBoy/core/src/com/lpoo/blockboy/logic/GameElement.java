package com.lpoo.blockboy.logic;

/**
 * Class parent from which the game elements inherit
 */
public class GameElement {

    private Position pos;

    /**
     * GameElement's Constructor
     *
     * @param x coordinate of the element
     * @param y coordinate of the element
     */
    GameElement(int x, int y){
        pos = new Position(x, y);
    }

    /**
     * Returns the position of an element of the Game
     *
     * @return position of an element of the game
     */
    public Position getPos(){
        return pos;
    }
}
