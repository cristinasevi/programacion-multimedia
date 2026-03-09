package programacion.multimedia.mijuego;

import com.badlogic.gdx.Game;
import programacion.multimedia.mijuego.screen.MainMenuScreen;

public class MiJuego extends Game {
    @Override
    public void create() {
        setScreen(new MainMenuScreen());
    }
}
