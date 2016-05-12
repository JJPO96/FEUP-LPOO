package com.lpoo.blockboy.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo.blockboy.BlockBoy;

import javax.swing.event.TableModelEvent;

/**
 * Created by Manuel Gomes on 12/05/2016.
 */
public class GameScreen implements Screen {

    // Variables to be used to keep aspect ratio for any screen size
    private final int VWIDTH = 800;
    private final int VHEIGHT = 450;

    private BlockBoy game;
    private World world;

    // Screen variables
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    // Tile maps variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    public GameScreen(BlockBoy game){
        this.game = game;
        // Creates a camera to follow the hero
        this.gameCam = new OrthographicCamera();
        // This enables to keep aspect ratio despite of screen size
        gamePort = new FitViewport(VWIDTH, VHEIGHT, gameCam);

        // Create a Box2D world, setting no gravity in X axis, -9.8 gravity in Y axis
        world = new World(new Vector2(0, -9.8f), true);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("levels/level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);
    }

    public World getWorld(){
        return world;
    }

    public void handInput(float deltaTime){
        // TODO - CORRIGIR PARA OS INPUTS CORRETOS
        if (Gdx.input.isTouched())
            gameCam.position.x+=50*deltaTime;
    }

    public void update (float deltaTime){
        handInput(deltaTime);
        gameCam.update();
        mapRenderer.setView(gameCam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        update(delta);
        // Clears the game screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.render();

        // Makes render only what can be seen
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Update game Viewport
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
    }
}
