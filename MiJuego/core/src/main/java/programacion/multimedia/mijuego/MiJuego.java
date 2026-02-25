package programacion.multimedia.mijuego;

import com.badlogic.gdx.Game;

public class MiJuego extends Game {
    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
