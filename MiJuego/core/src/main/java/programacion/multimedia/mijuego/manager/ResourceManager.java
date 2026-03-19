package programacion.multimedia.mijuego.manager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class ResourceManager {

    private static AssetManager assetManager = new AssetManager();

    public static void loadAllResources() {
        // ToDo Aquí cargaremos un TextureAtlas
        // assetManager.load("",  Texture.class);

        loadSounds();
        loadMusics();
    }

    public static boolean update() {
        return assetManager.update();
    }

    private static void loadSounds() {
        assetManager.load("sounds/waterdrop.mp3", Sound.class);
    }

    private static void loadMusics() {
        assetManager.load("sounds/undertreeinrain.mp3", Music.class);
    }

    public static Sound getSound(String name) {
        return assetManager.get("sounds/" + name, Sound.class);
    }

    public static Music getMusic(String name) {
        return assetManager.get("sounds/" + name, Music.class);
    }

    public static TextureRegion getRegion(String name) {
        return null;
    }

    public static Array<TextureRegion> getRegions(String name) {
        return null;
    }
}
