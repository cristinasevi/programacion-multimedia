package programacion.multimedia.mijuego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/** Pantalla de juego */
public class GameScreen implements Screen {

    private SpriteBatch batch;
    private Texture playerTexture;
    private Rectangle playerRectangle;
    private Vector2 playerPosition;
    private Texture dropTexture;
    private Array<Vector2> drops;
    private Array<Rectangle> dropRectangles;

    @Override
    public void show() {
        playerTexture = new Texture(Gdx.files.internal("bucket.png"));
        playerPosition = new Vector2(100, 100);
        playerRectangle = new Rectangle(
            playerPosition.x, playerPosition.y, playerTexture.getWidth(), playerTexture.getHeight());

        dropTexture = new Texture(Gdx.files.internal("drop.png"));
        drops = new Array<>();
        dropRectangles = new Array<>();
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // ToDo Controlar la velocidad a la que se generan gotas nuevas
        Vector2 dropPosition = new Vector2(MathUtils.random(0, 1024), 768);
        drops.add(dropPosition);
        dropRectangles.add(new Rectangle(
            dropPosition.x, dropPosition.y, dropTexture.getWidth(), dropTexture.getHeight()));

        batch.begin();
        batch.draw(playerTexture, playerPosition.x, playerPosition.y, 100, 100);
        for(Vector2 drop : drops) {
            batch.draw(this.dropTexture, drop.x, drop.y, 50, 50);
        }
        batch.end();

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerPosition.x -= 10;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerPosition.x += 10;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            playerPosition.y += 10;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playerPosition.y -= 10;
        }
        playerRectangle.setPosition(playerPosition.x, playerPosition.y);

        for(int i=0; i<dropRectangles.size; i++) {
            drops.get(i).y -= 10;
            dropRectangles.get(i).y = dropRectangles.get(i).y;
        }

        // Comprueba cuando una gota colisiona con el cubo
        for(Rectangle drop : dropRectangles) {
            if(drop.overlaps(playerRectangle)) {
                dropRectangles.removeValue(drop, true);
            }
        }

        // ToDo Qué pasa con las gotas que se caen al suelo?
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        playerTexture.dispose();
        dropTexture.dispose();
        batch.dispose();
        drops.clear();
    }
}
