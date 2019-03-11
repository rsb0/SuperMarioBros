package com.runesb.mariobros.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.runesb.mariobros.MarioBros;
import com.runesb.mariobros.Scenes.Hud;
import com.runesb.mariobros.Sprites.Mario;

public class PlayScreen implements Screen {

    private MarioBros game;
    private Texture texture;

    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    //Tiled Map variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;

    //Gravity Vector
    private Vector2 gravityVec = new Vector2(0,-10);

    private Mario player;


    public PlayScreen(MarioBros game){
        this.game = game;

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(MarioBros.V_WIIDTH / MarioBros.PPM,
                MarioBros.V_HEIGHT / MarioBros.PPM, gameCam);
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / MarioBros.PPM);

        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(gravityVec, true);
        b2dr = new Box2DDebugRenderer();

        player = new Mario(world);

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;


        //Cycle through every object in layer 2 of level1.tmx. I.e. all the ground bricks
        for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect =  ((RectangleMapObject) object).getRectangle();

            //define body type. For ground: Static Body is a body that does not move. Not affected
            //forces or velocities.
            bdef.type = BodyDef.BodyType.StaticBody;
            //define position for body
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM,
                    (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth() / 2) / MarioBros.PPM, (rect.getHeight() / 2) / MarioBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //Cycle through every object in layer 3 of level1.tmx. I.e. all the pipes
        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect =  ((RectangleMapObject) object).getRectangle();

            //define body type. For ground: Static Body is a body that does not move. Not affected
            //forces or velocities.
            bdef.type = BodyDef.BodyType.StaticBody;
            //define position for body
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM,
                    (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth() / 2) / MarioBros.PPM, (rect.getHeight() / 2) / MarioBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //Cycle through every object in layer 5 of level1.tmx. I.e. all the bricks in the air
        for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect =  ((RectangleMapObject) object).getRectangle();

            //define body type. For ground: Static Body is a body that does not move. Not affected
            //forces or velocities.
            bdef.type = BodyDef.BodyType.StaticBody;
            //define position for body
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM,
                    (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth() / 2) / MarioBros.PPM, (rect.getHeight() / 2) / MarioBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //Cycle through every object in layer 4 of level1.tmx. I.e. all the bricks in the air
        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect =  ((RectangleMapObject) object).getRectangle();

            //define body type. For ground: Static Body is a body that does not move. Not affected
            //forces or velocities.
            bdef.type = BodyDef.BodyType.StaticBody;
            //define position for body
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM,
                    (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth() / 2) / MarioBros.PPM, (rect.getHeight() / 2) / MarioBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            player.b2body.applyLinearImpulse(new Vector2(0,4f),
                    player.b2body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2){
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(),true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x <= 2) {
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
        }
    }

    public void update(float dt){
        handleInput(dt);

        world.step(1/60f, 6, 2);

        gameCam.position.x = player.b2body.getPosition().x;
        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void render(float delta) {
        //separate our update logic from renderer
        update(delta);

        //clear screen with red
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render game map
        renderer.render();

        //render Box2DDebugLines
        b2dr.render(world, gameCam.combined);

        //set batch to now draw what the Hud camera sees
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
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

    }
}
