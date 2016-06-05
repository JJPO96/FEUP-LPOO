package com.lpoo.blockboy.gui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.lpoo.blockboy.BlockBoy;
import com.lpoo.blockboy.logic.Block;
import com.lpoo.blockboy.logic.Coin;
import com.lpoo.blockboy.logic.GameLogic;
import com.lpoo.blockboy.logic.Hero;

/**
 * Created by Manuel Gomes on 01/06/2016.
 */
public class CollisionListener implements ContactListener {

    private GameLogic gameLogic;

    public CollisionListener (GameLogic gameLogic){
        this.gameLogic = gameLogic;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA  = contact.getFixtureA();
        Fixture fixB  = contact.getFixtureB();
        int bits = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (bits){
            case BlockBoy.HERO_BIT | BlockBoy.BLOCK_BIT:
                if(fixA.getFilterData().categoryBits == BlockBoy.HERO_BIT)
                    ((Block) fixB.getUserData()).setCollision(true);
                else
                    ((Block) fixA.getUserData()).setCollision(true);
                break;

            case BlockBoy.HERO_BIT | BlockBoy.EXIT_BIT:
                gameLogic.getHero().setArriveExit(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int bits = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (bits){
            case BlockBoy.HERO_BIT | BlockBoy.BLOCK_BIT:
                if(fixA.getFilterData().categoryBits == BlockBoy.HERO_BIT)
                    ((Block) fixB.getUserData()).setCollision(false);
                else
                    ((Block) fixA.getUserData()).setCollision(false);
                break;
            case BlockBoy.BRICK_BIT | BlockBoy.BLOCK_BIT:
                if(fixA.getFilterData().categoryBits == BlockBoy.HERO_BIT){
                    ((Block) fixB.getUserData()).setDynamic();
                }
                else
                    ((Block) fixA.getUserData()).setDynamic();
                break;
            default:
                break;
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
