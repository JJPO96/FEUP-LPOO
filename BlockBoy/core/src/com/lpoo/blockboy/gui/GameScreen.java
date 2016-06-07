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
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo.blockboy.BlockBoy;
import com.lpoo.blockboy.logic.GameLogic;

/**
 * Created by Manuel Gomes on 12/05/2016.
 */
public class GameScreen implements Screen {
    private BlockBoy game;
    private GameLogic gameLogic;
    private int level;
    private TextureAtlas atlas;
    private World world;

    // Screen variables
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    // Tiled map variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    public GameScreen(BlockBoy game) {
        this.game = game;
        // Creating an atlas
        atlas = new TextureAtlas("sprites/gamesprites.pack");

        // Creates a camera to follow the hero
        this.gameCam = new OrthographicCamera();

        // This enables to keep aspect ratio despite of screen size
        gamePort = new FitViewport(game.VWIDTH / game.PPM, game.VHEIGHT / game.PPM, gameCam);

        // Prepares the map to be rendered
        mapLoader = new TmxMapLoader();
        // TODO - CHANGE TO USE THE LEVEL SELECTED
        map = mapLoader.load("levels/level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / game.PPM);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        // Create a Box2D world, setting no gravity in X axis, -9.8 gravity in Y axis
        world = new World(new Vector2(0, -9.8f), true);

        // Creates an instance of logic of the game itself
        gameLogic = new GameLogic(this);

        world.setContactListener(new CollisionListener(gameLogic));
        hud = new Hud(this);
    }

    public GameScreen(BlockBoy game, String lvlMap) {
        this.game = game;
        // Creating an atlas
        atlas = new TextureAtlas("sprites/gamesprites.pack");

        // Creates a camera to follow the hero
        this.gameCam = new OrthographicCamera();

        // This enables to keep aspect ratio despite of screen size
        gamePort = new FitViewport(game.VWIDTH / game.PPM, game.VHEIGHT / game.PPM, gameCam);

        // Prepares the map to be rendered
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(lvlMap);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        // Create a Box2D world, setting no gravity in X axis, -9.8 gravity in Y axis
        world = new World(new Vector2(0, -9.8f), true);

        // Creates an instance of logic of the game itself
        gameLogic = new GameLogic(this);

        world.setContactListener(new CollisionListener(gameLogic));
    }

    public void checkInput(float delta) {
        // Hero is always at the center of the screen
        if (Gdx.input.isTouched()) {
            if (Gdx.input.getY() < 2 * Gdx.graphics.getHeight() / 3) {
                if (Gdx.input.getY() < Gdx.graphics.getWidth() / 4)
                    gameLogic.getHero().jump();
                if (Gdx.input.getX() > Gdx.graphics.getWidth() / 2)
                    gameLogic.getHero().run(0.1f);
                if (Gdx.input.getX() < Gdx.graphics.getWidth() / 2)
                    gameLogic.getHero().run(-0.1f);
            }
        }

        // Desktop commands
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
            gameLogic.getHero().jump();
        if (Gdx.input.isKeyJustPressed(Input.Keys.A))
            gameLogic.setMoveBlock(true);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && gameLogic.getHero().getBody().getLinearVelocity().x <= 2)
            gameLogic.getHero().run(0.1f);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && gameLogic.getHero().getBody().getLinearVelocity().x >= -2)
            gameLogic.getHero().run(-0.1f);
    }

    /**
     * Updates the game according to the game's state
     *
     * @param delta time
     */
    public void gameStateUpdate(float delta) {
        switch (gameLogic.getState()) {
            case WIN:
                if (BlockBoy.levelInd < BlockBoy.lockLevels.length - 1) {
                    BlockBoy.levelInd++;
                    BlockBoy.lockLevels[BlockBoy.levelInd] = false;
                }
                Gdx.input.vibrate(600);
                if(!BlockBoy.mute)  BlockBoy.winSound.play(BlockBoy.volume/100);
                BlockBoy.coinScore+=gameLogic.getCoinScore();
                BlockBoy.saveData();
                game.setScreen(new WinScreen(game));
                dispose();
                break;
            case LOOSE:
                Gdx.input.vibrate(600);
                if(!BlockBoy.mute) BlockBoy.gameOverSound.play(BlockBoy.volume/100);
                BlockBoy.coinScore+=gameLogic.getCoinScore();
                BlockBoy.saveData();
                game.setScreen(new GameOverScreen(game));
                dispose();
                break;
            case RUNNING:
                update(delta);
                break;
            default:
                break;
        }
    }

    /**
     * Updates the game
     *
     * @param delta
     */
    public void update(float delta){
        checkInput(delta);
        world.step(1 / 60f, 6, 2);

        // Updates hud
        hud.update(delta, gameLogic.getCoinScore());

        // Updates the camera position in relation to the hero
        gameCam.position.x = gameLogic.getHero().getBody().getPosition().x;
        gameCam.position.y = gameLogic.getHero().getBody().getPosition().y;
        gameCam.update();
        // Tells renderer to only draw what the camera can see
        mapRenderer.setView(gameCam);
        gameLogic.update(delta);
    }

    public World getWorld() {
        return world;
    }

    public TiledMap getMap() {
        return map;
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public BlockBoy getGame() {
        return game;
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        // Clears game screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render map
        mapRenderer.render();

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        // Draw hero
        gameLogic.getHero().draw(game.batch);
        // Draw game's elements
        for (int i = 0; i < gameLogic.getCoins().size(); i++)
            if (!gameLogic.getCoins().get(i).isPicked())
                gameLogic.getCoins().get(i).draw(game.batch);

        for (int i = 0; i < gameLogic.getBlocks().size(); i++)
            gameLogic.getBlocks().get(i).draw(game.batch);

        game.batch.end();
        // Draws HUD
        game.batch.setProjectionMatrix(hud.getStage().getCamera().combined);

        hud.getStage().draw();
        gameStateUpdate(delta);
    }

    public int getLevel() {
        return level;
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
        map.dispose();
        hud.dispose();
    }
}
