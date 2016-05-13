package com.lpoo.blockboy.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.math.Rectangle;
import com.lpoo.blockboy.BlockBoy;
import com.lpoo.blockboy.logic.GameLogic;
import com.lpoo.blockboy.logic.Hero;

/**
 * Created by Manuel Gomes on 12/05/2016.
 */
public class GameScreen implements Screen {

    // Variables to be used to keep aspect ratio for any screen size
    private static final int VWIDTH = 800;
    private static final int VHEIGHT = 450;
    public static final float PPM = 100;

    private BlockBoy game;
    private GameLogic gameLogic;

    // Box2d variables
    private World world;
    private Box2DDebugRenderer boxDebug;

    // Screen variables
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    // Tiled maps variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    public GameScreen(BlockBoy game){
        this.game = game;

        // Creates a camera to follow the hero
        this.gameCam = new OrthographicCamera();

        // This enables to keep aspect ratio despite of screen size
        gamePort = new FitViewport(VWIDTH / GameScreen.PPM, VHEIGHT / GameScreen.PPM, gameCam);

        // Prepares the map to be rendered
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("levels/level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / GameScreen.PPM);
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        // Create a Box2D world, setting no gravity in X axis, -9.8 gravity in Y axis
        world = new World(new Vector2(0, -9.8f), true);

        // Creates an instance of logic of the game itself
        gameLogic = new GameLogic(this);

        boxDebug = new Box2DDebugRenderer();
    }

    public void handleInput(float delta){
        // TODO - PASSAR AS KEYS DO INPUT PARA ALGO DETETAVEL PELO TLM
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
            gameLogic.getHero().jump();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && gameLogic.getHero().getBody().getLinearVelocity().x <= 2)
            gameLogic.getHero().run(0.1f);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && gameLogic.getHero().getBody().getLinearVelocity().x >= -2)
            gameLogic.getHero().run(-0.1f);

    }

    public void update(float delta){
        handleInput(delta);
        world.step(1 / 60f, 6, 2);

        // TODO - MAKE CAMERA FOLLOW HERO ALSO IN Y AXIS
        // Makes the camera follow the hero
        gameCam.position.x = gameLogic.getHero().getBody().getPosition().x;
        gameCam.position.y = gameLogic.getHero().getBody().getPosition().y;

        gameCam.update();
        // Tells renderer to only draw what the camera can see
        mapRenderer.setView(gameCam);
    }

    public World getWorld(){
        return world;
    }

    public TiledMap getMap(){
        return map;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        // Clears game screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render map
        mapRenderer.render();

        // Box2DDebugRenderer renderer
        boxDebug.render(world, gameCam.combined);
        game.batch.setProjectionMatrix(gameCam.combined);
    }

    @Override
    public void resize(int width, int height) {
        // Update game Viewport to the new resized screen
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        world.dispose();
        mapRenderer.dispose();
    }
}
