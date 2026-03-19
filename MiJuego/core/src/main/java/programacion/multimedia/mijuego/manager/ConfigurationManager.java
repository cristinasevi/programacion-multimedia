package programacion.multimedia.mijuego.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import static programacion.multimedia.mijuego.util.Constants.GAME_NAME;

public class ConfigurationManager {

    private static Preferences preferences = Gdx.app.getPreferences(GAME_NAME);

    public static boolean isSoundEnabled() {
        return preferences.getBoolean("sound_effects", true);
    }

    public static boolean isMusicEnabled() {
        return preferences.getBoolean("music", true);
    }

    public static int getGameLevel() {
        return preferences.getInteger("level", 2);
    }
}
