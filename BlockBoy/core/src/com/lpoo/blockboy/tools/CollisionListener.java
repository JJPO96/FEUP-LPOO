package com.lpoo.blockboy.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.lpoo.blockboy.BlockBoy;
import com.lpoo.blockboy.logic.Block;
import com.lpoo.blockboy.logic.GameLogic;

/**
 * Created by Manuel Gomes on 01/06/2016.
 */

/**
 * Class responsible for detecting and handling all object collisions
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
                    ((Block) fixB.getUserData()).setHeroCollision(true);
                else
                    ((Block) fixA.getUserData()).setHeroCollision(true);
                break;
            case BlockBoy.BRICK_BIT | BlockBoy.BLOCK_BIT:
                Gdx.app.log("begin collision", "");
                if(fixA.getFilterData().categoryBits == BlockBoy.BRICK_BIT){
                    ((Block) fixB.getUserData()).setBrickCollision(true);
                }
                else
                    ((Block) fixA.getUserData()).setBrickCollision(true);
                break;
            case BlockBoy.BLOCK_BIT | BlockBoy.BLOCK_BIT:
                if (((Block) fixA.getUserData()).getBody().getPosition().y == ((Block) fixB.getUserData()).getBody().getPosition().y){
                    if (((Block) fixA.getUserData()).getBrickCollision())
                        ((Block) fixB.getUserData()).setBrickCollision(true);
                    else if (((Block) fixB.getUserData()).getBrickCollision())
                        ((Block) fixA.getUserData()).setBrickCollision(true);
                }
                break;
            case BlockBoy.BRICK_BIT | BlockBoy.BLOCK_PICKED_BIT:
                if(fixA.getFilterData().categoryBits == BlockBoy.BRICK_BIT){
                    ((Block) fixB.getUserData()).setBrickCollision(true);
                }
                else
                    ((Block) fixA.getUserData()).setBrickCollision(true);
                break;
            case BlockBoy.HERO_BIT | BlockBoy.EXIT_BIT:
                gameLogic.getHero().setArriveExit(true);
                break;
            case BlockBoy.BLOCK_PICKED_BIT | BlockBoy.AIRGROUND_BIT:
                if(fixA.getFilterData().categoryBits == BlockBoy.AIRGROUND_BIT){
                    if (((Block) fixB.getUserData()).isPicked())
                    {
                        ((Block) fixB.getUserData()).setPicked(false);
                        gameLogic.getHero().setCarryBlock(false);
                    }
                }
                else{
                    if (((Block) fixA.getUserData()).isPicked())
                    {
                        ((Block) fixA.getUserData()).setPicked(false);
                        gameLogic.getHero().setCarryBlock(false);
                    }
                }
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
                    ((Block) fixB.getUserData()).setHeroCollision(false);
                else
                    ((Block) fixA.getUserData()).setHeroCollision(false);
                break;
            case BlockBoy.BLOCK_BIT | BlockBoy.BLOCK_BIT:
                if(fixA.getFilterData().categoryBits == BlockBoy.HERO_BIT)
                    ((Block) fixB.getUserData()).setHeroCollision(false);
                else
                    ((Block) fixA.getUserData()).setHeroCollision(false);
                break;
            case BlockBoy.BRICK_BIT | BlockBoy.BLOCK_BIT:
                Gdx.app.log("end collision", "");
                if(fixA.getFilterData().categoryBits == BlockBoy.BRICK_BIT){
                    ((Block) fixB.getUserData()).setBrickCollision(false);
                }
                else
                    ((Block) fixA.getUserData()).setBrickCollision(false);
                break;
            case BlockBoy.BRICK_BIT | BlockBoy.BLOCK_PICKED_BIT:
                Gdx.app.log("end collision", "");
                if(fixA.getFilterData().categoryBits == BlockBoy.BRICK_BIT){
                    ((Block) fixB.getUserData()).setBrickCollision(false);
                }
                else
                    ((Block) fixA.getUserData()).setBrickCollision(false);
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
