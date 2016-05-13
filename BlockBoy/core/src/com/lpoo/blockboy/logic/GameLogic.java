package com.lpoo.blockboy.logic;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoo.blockboy.gui.GameScreen;

import java.util.ArrayList;

/**
 * Created by Manuel Gomes on 12/05/2016.
 */
public class GameLogic {

    private Hero hero;
    private Key key;
    private ArrayList<Block> blocks;
    private Boolean running = true;

    private GameScreen screen;
    private World world;

    public GameLogic(GameScreen screen){
        // TODO - implementar aqui a logica do jogo
        this.screen = screen;
        this.world = screen.getWorld();
        init();
    }

    // TODO - CRIAR TODOS OS ELEMENTOS DE JOGO AQUI
    public void init(){
        hero = new Hero(screen);




        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for (MapObject object: screen.getMap().getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/ GameScreen.PPM, (rect.getY()+rect.getHeight()/2)/ GameScreen.PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2/ GameScreen.PPM, rect.getHeight()/2/ GameScreen.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        for (MapObject object: screen.getMap().getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/ GameScreen.PPM, (rect.getY()+rect.getHeight()/2)/ GameScreen.PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2/ GameScreen.PPM, rect.getHeight()/2/ GameScreen.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        for (MapObject object: screen.getMap().getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/ GameScreen.PPM, (rect.getY()+rect.getHeight()/2)/ GameScreen.PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2/ GameScreen.PPM, rect.getHeight()/2/ GameScreen.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        for (MapObject object: screen.getMap().getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/ GameScreen.PPM, (rect.getY()+rect.getHeight()/2)/ GameScreen.PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2/ GameScreen.PPM, rect.getHeight()/2/ GameScreen.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        for (MapObject object: screen.getMap().getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/ GameScreen.PPM, (rect.getY()+rect.getHeight()/2)/ GameScreen.PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2/ GameScreen.PPM, rect.getHeight()/2/ GameScreen.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
    }

    /**
     * Updates the game
     */
    public void update(){}

    public boolean isGameRunning(){
        return running;
    }

    public Hero getHero(){
        return hero;
    }
}
