package programacion.multimedia.mijuego.manager;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import programacion.multimedia.mijuego.domain.Enemy;

import static programacion.multimedia.mijuego.domain.Character.State.RUNNING_LEFT;

public class LevelManager {

    private LogicManager logicManager;
    private int currentLevel;

    TiledMap map;
    OrthogonalTiledMapRenderer mapRenderer;
    public Batch batch;
    TiledMapTileLayer collisionLayer;
    MapLayer enemiesLayer;
    MapLayer itemsLayer;

    public LevelManager(LogicManager logicManager) {
        this.logicManager = logicManager;

        currentLevel = 1;
    }

    public void loadCurrentLevel() {
        map = new TmxMapLoader().load("levels/level" + currentLevel + ".tmx");
        collisionLayer = (TiledMapTileLayer) map.getLayers().get("terrain");
        enemiesLayer = map.getLayers().get("enemies");
        itemsLayer = map.getLayers().get("items");

        mapRenderer = new OrthogonalTiledMapRenderer(map);
        batch = mapRenderer.getBatch();

        loadEnemies();
        loadItems();
    }

    private void loadEnemies() {
        Enemy enemy = null;
        for (MapObject mapObject : enemiesLayer.getObjects()) {
            TiledMapTileMapObject object = (TiledMapTileMapObject) mapObject;
            if (object.getProperties().get("type").equals("green")) {
                int speed = (Integer) object.getProperties().get("speed");
                float x = object.getX();
                float y = object.getY();
                enemy = new Enemy(ResourceManager.getRegion("green_bubble_left"), new Vector2(x, y),
                    RUNNING_LEFT, speed, "green_bubble_left", "green_bubble_right");
            } else if (object.getProperties().get("type").equals("gray")) {
                // ToDo Instanciar un GrayEnemy
            }

            logicManager.enemies.add(enemy);
        }
    }

    private void loadItems() {
        // ToDo Cargar los items
    }

    public void restartCurrentLevel() {

    }

    public void loadNextlevel() {
        currentLevel++;
        // ToDo Algo más?
        loadCurrentLevel();
    }
}
