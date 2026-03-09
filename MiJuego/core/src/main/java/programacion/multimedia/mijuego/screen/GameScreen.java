package programacion.multimedia.mijuego.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import programacion.multimedia.mijuego.domain.Drop;
import programacion.multimedia.mijuego.domain.Player;

import static programacion.multimedia.mijuego.util.Constants.TIME_BETWEEN_DROPS;

/** Pantalla de juego */
public class GameScreen implements Screen {

    private SpriteBatch batch;
    private Player player;
    private Texture dropTexture;
    private Array<Drop> drops;
    private long lastDrop;
    private Sound dropSound;
    private Music gameMusic;

    @Override
    public void show() {
        player = new Player(new Texture(Gdx.files.internal("textures/bucket.png")));
        dropTexture = new Texture(Gdx.files.internal("textures/drop.png"));
        dropSound = Gdx.audio.newSound(Gdx.files.internal("sounds/waterdrop.mp3"));
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/undertreeinrain.mp3"));
        drops = new Array<>();
        batch = new SpriteBatch();

        lastDrop = TimeUtils.millis();

        gameMusic.play();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Generación de gotas/enemigos
        if(TimeUtils.timeSinceMillis(lastDrop) >= TIME_BETWEEN_DROPS) {
            Drop newDrop = new Drop(dropTexture, MathUtils.random(0, 1024), 768);
            drops.add(newDrop);
            lastDrop = TimeUtils.millis();
        }

        batch.begin();
        player.draw(batch);
        for(Drop drop : drops) {
            drop.draw(batch);
        }
        batch.end();

        player.handleInput(delta);

        for(Drop drop : drops) {
            drop.move(delta);

            // Una gota colisiona con el jugador
            if(drop.getRectangle().overlaps(player.getRectangle())) {
                dropSound.play();
                drops.removeValue(drop, true);
            }

            // Eliminar las gotas cuando llegan al suelo
            if(drop.getTopPosition() < 0) {
                drops.removeValue(drop, true);
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
        }
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
        player.dispose();
        dropTexture.dispose();
        batch.dispose();
        drops.clear();
    }
}
