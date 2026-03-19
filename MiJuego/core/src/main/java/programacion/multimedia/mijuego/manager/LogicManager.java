package programacion.multimedia.mijuego.manager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.TimeUtils;
import programacion.multimedia.mijuego.domain.Drop;
import programacion.multimedia.mijuego.domain.Player;
import programacion.multimedia.mijuego.screen.MainMenuScreen;

import static programacion.multimedia.mijuego.util.Constants.TIME_BETWEEN_DROPS;

public class LogicManager implements Disposable {

    protected Player player;
    protected Array<Drop> drops;

    private long lastDrop;
    private Sound dropSound;
    private Music gameMusic;
    private Texture dropTexture;

    public LogicManager() {
        drops = new Array<>();
        dropTexture = new Texture(Gdx.files.internal("textures/drop.png"));
        dropSound = ResourceManager.getSound("waterdrop.mp3");
        gameMusic = ResourceManager.getMusic("undertreeinrain.mp3");

    }

    public void load() {
        player = new Player(new Texture(Gdx.files.internal("textures/bucket.png")));

        lastDrop = TimeUtils.millis();

        if(ConfigurationManager.isMusicEnabled())
            gameMusic.play();
    }

    public void update(float dt) {
        // Generación de gotas/enemigos
        if(TimeUtils.timeSinceMillis(lastDrop) >= TIME_BETWEEN_DROPS / ConfigurationManager.getGameLevel()) {
            Drop newDrop = new Drop(dropTexture, MathUtils.random(0, 1024), 768);
            drops.add(newDrop);
            lastDrop = TimeUtils.millis();
        }

        player.handleInput(dt);

        for(Drop drop : drops) {
            drop.move(dt);

            // Una gota colisiona con el jugador
            if(drop.getRectangle().overlaps(player.getRectangle())) {
                if (ConfigurationManager.isSoundEnabled())
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
    public void dispose() {
        player.dispose();
        dropTexture.dispose();
        drops.clear();
    }
}
