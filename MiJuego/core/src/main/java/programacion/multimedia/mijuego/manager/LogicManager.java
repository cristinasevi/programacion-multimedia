package programacion.multimedia.mijuego.manager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import programacion.multimedia.mijuego.domain.Enemy;
import programacion.multimedia.mijuego.domain.Player;
import programacion.multimedia.mijuego.screen.MainMenuScreen;

import static programacion.multimedia.mijuego.domain.Character.State.RUNNING_LEFT;

public class LogicManager implements Disposable {

    protected Player player;
    // ToDo Gestionar todos los enemigos como un array
    protected Enemy enemy;

    private Music gameMusic;

    public LogicManager() {
    }

    public void load() {
        player = new Player(ResourceManager.getRegion("player_idle_right"), "player_run_left", "player_run_right");
        enemy = new Enemy(ResourceManager.getRegion("green_bubble_left"), new Vector2(1000, 100),
            RUNNING_LEFT, "green_bubble_left", "green_bubble_right");

        // FIXME
//        if(ConfigurationManager.isMusicEnabled())
//            gameMusic.play();

        // ToDo Gestionar como hacer aparecer los enemigos
    }

    public void update(float dt) {
        player.handleInput(dt);
        player.update(dt);

        // ToDo Gestionar todos los enemigos como un array
        enemy.update(dt);

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
        }
    }

    @Override
    public void dispose() {
        player.dispose();
    }
}
