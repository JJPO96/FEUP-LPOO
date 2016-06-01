package com.lpoo.blockboy.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.lpoo.blockboy.logic.Block;
import com.lpoo.blockboy.logic.Coin;

/**
 * Created by Manuel Gomes on 01/06/2016.
 */
public class CollisionListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA  = contact.getFixtureA();
        Fixture fixB  = contact.getFixtureB();

        if (fixA.getUserData() == "heroSensorDiagonal" || fixB.getUserData() == "heroSensorDiagonal" ){
            Fixture left = fixA.getUserData() == "heroSensorDiagonal"? fixA : fixB;
            Fixture object = left == fixA? fixB : fixA;

            if(object.getUserData() instanceof Coin){
                ((Coin) object.getUserData()).detectCollition();
            }

            if(object.getUserData() instanceof Block){
                ((Block) object.getUserData()).detectCollition();
            }
        }
        // TODO - PAGAR?
        /*if (fixA.getUserData() == "heroSensorRight" || fixB.getUserData() == "heroSensorRight"){
            Fixture right = fixA.getUserData() == "heroSensorRight"? fixA : fixB;
            Fixture object = right == fixA? fixB : fixA;

            if(object.getUserData() instanceof Coin){
                ((Coin) object.getUserData()).detectCollition();
            }

            if(object.getUserData() instanceof Block){
                ((Block) object.getUserData()).detectCollition();
            }
        }

        if (fixA.getUserData() == "heroSensorLeft" || fixB.getUserData() == "heroSensorLeft" ){
            Fixture left = fixA.getUserData() == "heroSensorLeft"? fixA : fixB;
            Fixture object = left == fixA? fixB : fixA;

            if(object.getUserData() instanceof Coin){
                ((Coin) object.getUserData()).detectCollition();
            }

            if(object.getUserData() instanceof Block){
                ((Block) object.getUserData()).detectCollition();
            }
        }*/
    }

    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("End Contact", "");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
