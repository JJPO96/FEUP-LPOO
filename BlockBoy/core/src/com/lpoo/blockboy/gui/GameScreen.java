package com.lpoo.blockboy.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.FPSLogger;
import com.lpoo.blockboy.BlockBoy;
import com.lpoo.blockboy.logic.GameLogic;
import com.sun.javafx.scene.traversal.Hueristic2D;

/**
 * Created by Manuel Gomes on 12/05/2016.
 */
public class GameScreen implements Screen {
    private BlockBoy game;
    private GameLogic gameLogic;

    // Sprites
    private TextureAtlas atlas;

    // Box2d variables
    private World world;
    private Box2DDebugRenderer boxDebug;

    // Screen variables
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    // Tiled maps variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    public GameScreen(BlockBoy game){
        this.game = game;

        // Creating an atlas
        atlas = new TextureAtlas("sprites/gamesprites.pack");

        // Creates a camera to follow the hero
        this.gameCam = new OrthographicCamera();

        // This enables to keep aspect ratio despite of screen size
        gamePort = new FitViewport(game.VWIDTH / game.PPM, game.VHEIGHT / game.PPM, gameCam);

        // Prepares the map to be rendered
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("levels/level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / game.PPM);
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        // Create a Box2D world, setting no gravity in X axis, -9.8 gravity in Y axis
        world = new World(new Vector2(0, -9.8f), true);

        // Creates an instance of logic of the game itself
        gameLogic = new GameLogic(this);
        boxDebug = new Box2DDebugRenderer();
        hud = new Hud(game.batch, game.font);
    }

    public void checkInput(float delta){
        // TODO - PASSAR AS KEYS DO INPUT PARA ALGO DETETAVEL PELO TLM

        // TODO - Testar para o caso de multitouch
        // Hero is always at the center of the screen
        if (Gdx.input.isTouched()) {
            if (Gdx.input.getY() < Gdx.graphics.getWidth()/3)
                gameLogic.getHero().jump();
            if (Gdx.input.getX() > Gdx.graphics.getWidth()/2)
                gameLogic.getHero().run(0.1f);
            if (Gdx.input.getX() < Gdx.graphics.getWidth()/2)
                gameLogic.getHero().run(-0.1f);
        }

        // TODO - REMOVER - DESKTOP KEYS (FAST DEBUGGING)
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
            gameLogic.getHero().jump();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && gameLogic.getHero().getBody().getLinearVelocity().x <= 2)
            gameLogic.getHero().run(0.1f);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && gameLogic.getHero().getBody().getLinearVelocity().x >= -2)
            gameLogic.getHero().run(-0.1f);

    }

    public void update(float delta){
        checkInput(delta);
        world.step(1 / 60f, 6, 2);

        // Updates the game itself
        gameLogic.update(delta);
        hud.update(delta);

        // Updates the camera position in relation to the hero
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

    public TextureAtlas getAtlas(){ return atlas; }

    @Override
    public void show() {}
    
    
    // TODO - SEPARAR RENDER EM "UPDATE GAME" E EM "DRAW GAME"?

    @Override
    public void render(float delta) {
        update(delta);

        // Clears game screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render map
        mapRenderer.render();

        // TODO - APAGAR QD NAO FOR MAIS NECESSARIO - Box2DDebugRenderer renderer
        boxDebug.render(world, gameCam.combined);

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        // Draw hero
        gameLogic.getHero().draw(game.batch);
        // Draw game elements
        for (int i = 0; i < gameLogic.getCoins().size(); i++)
            gameLogic.getCoins().get(i).draw(game.batch);

        for (int i = 0; i < gameLogic.getBlocks().size(); i++)
            gameLogic.getBlocks().get(i).draw(game.batch);

        game.batch.end();

        // TODO - COLOCAR ALGO AQUI NO NO FICHEIRO BLOCKBOY PARA FAZER MUDANÇA DO SCREEN
        /*        if(gameOver()){
            game.setScreen(new GameOverScreen(game));
            dispose();
        }         */

        game.batch.setProjectionMatrix(hud.getStage().getCamera().combined);
        hud.getStage().draw();
    }

    @Override
    public void resize(int width, int height) {
        // Update game Viewport to the new resized screen
        gamePort.update(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        world.dispose();
        mapRenderer.dispose();
        map.dispose();
        boxDebug.dispose();
    }


}
