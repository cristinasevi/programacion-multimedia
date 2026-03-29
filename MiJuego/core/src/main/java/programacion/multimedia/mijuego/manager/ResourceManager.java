package programacion.multimedia.mijuego.manager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class ResourceManager {

    private static AssetManager assetManager = new AssetManager();

    public static void loadAllResources() {
        assetManager.load("mijuego.atlas",  TextureAtlas.class);

        loadSounds();
        loadMusics();
    }

    public static boolean update() {
        return assetManager.update();
    }

    private static void loadSounds() {
    }

    private static void loadMusics() {
        assetManager.load("musics/bso.mp3", Music.class);
    }

    public static Sound getSound(String name) {
        return assetManager.get("sounds/" + name, Sound.class);
    }

    public static Music getMusic(String name) {
        return assetManager.get("musics/" + name, Music.class);
    }

    public static TextureRegion getRegion(String name) {
        return assetManager.get("mijuego.atlas", TextureAtlas.class).findRegion(name);
    }

    public static Array<TextureAtlas.AtlasRegion> getRegions(String name) {
        return assetManager.get("mijuego.atlas", TextureAtlas.class).findRegions(name);
    }
}
