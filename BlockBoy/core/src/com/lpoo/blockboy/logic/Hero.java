package com.lpoo.blockboy.logic;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.lpoo.blockboy.gui.GameScreen;

/**
 * Class that represents the hero of the game
 */
public class Hero extends GameElement {

    // TODO - CORRIGIR AS TEXTURES (VER CLASSE GAMEELEMENT QUE ESTA A FUNCIONAR APAENAS PARA A CLASSE HERO)
    private enum State {STANDING, RUNNING, JUMPING, FALLING}
    private State currentState;
    private State previousState;
    private boolean carryBock = false;

    // Textures
    private TextureRegion heroStannding;

    /**
     * Hero's constructor
     *
     * @param screen where the Hero will be displayed
     */
    public Hero (GameScreen screen){
        //super(screen.getAtlas().findRegion("frame-1"));
        super(screen);
        currentState = State.STANDING;
        previousState = State.STANDING;

        // Create the body of the Hero
        init();
    }

    /**
     * Initializes the hero and creates its body
     */
    @Override
    public void init() {
        // TODO - CORRIGIR POSIÇAO INICILA DO HEROI CONSOANTE O MAPA ESCOLHIDO
        bodyDef = new BodyDef();
        bodyDef.position.set(200/ GameScreen.PPM, 64/ GameScreen.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        fixtureDef = new FixtureDef();
        shape = new CircleShape();
        shape.setRadius(32 / GameScreen.PPM);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);

        // Create textures
        heroStannding = new TextureRegion(getTexture(), 1, 412, 307, 409);
        setBounds(0, 0, 48 / GameScreen.PPM, 64 / GameScreen.PPM);
        setRegion(heroStannding);
    }

    // TODO - FALTA TERMINAR
    @Override
    public void update() {
        setPosition(body.getPosition().x - getWidth()/2, body.getPosition().y - getHeight()/2);
    }

    public void run(float dist){
        body.applyLinearImpulse(new Vector2(dist, 0), body.getWorldCenter(), true);
        currentState = State.RUNNING;
    }

    public void jump(){
        if (currentState != State.JUMPING ) {
            body.applyLinearImpulse(new Vector2(0, 4f), body.getWorldCenter(), true);
            currentState = State.JUMPING;
        }

        // TODO - REMOVER/CORRIGIR - HEROI SEM ISTO NAO SALTA NA VERTICAL QD ESTÁ PARADO
        currentState = State.STANDING;
    }

    // TODO - DECIDIR SE ESTAS FUNÇÕES SÃO OU NAO REUTILIZAVEIS USANDO-SE MAQUINA DE ESTADOS
    /**
     * Changes the Hero state about carrying the box
     *
     * @param carry with the new state of the Hero
     */
    public void setCarryBock(boolean carry){
        carryBock = carry;
    }

    /**
     * Verifies if the Hero is carrying a block
     *
     * @return true if the Hero is carrying a block
     */
    public boolean isCarryBock(){
        return carryBock;
    }
}
