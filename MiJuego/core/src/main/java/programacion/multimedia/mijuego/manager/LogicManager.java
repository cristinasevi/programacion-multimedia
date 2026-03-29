package programacion.multimedia.mijuego.manager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import programacion.multimedia.mijuego.domain.Enemy;
import programacion.multimedia.mijuego.domain.Item;
import programacion.multimedia.mijuego.domain.Player;
import programacion.multimedia.mijuego.screen.MainMenuScreen;

public class LogicManager implements Disposable {

    protected Player player;
    protected Array<Enemy> enemies;
    protected Array<Item> items;

    private Music gameMusic;

    public LogicManager() {
        this.enemies = new Array<>();
        this.items = new Array<>();
    }

    public void load() {
        player = new Player(ResourceManager.getRegion("player_idle_right"), "player_run_left", "player_run_right");

        // FIXME
//        if(ConfigurationManager.isMusicEnabled())
//            gameMusic.play();

        // ToDo Gestionar como hacer aparecer los enemigos
    }

    public void update(float dt) {
        player.handleInput(dt);
        player.update(dt);

        for(Enemy enemy : enemies){
            enemy.update(dt);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
        }
    }

    @Override
    public void dispose() {
        player.dispose();
    }
}
