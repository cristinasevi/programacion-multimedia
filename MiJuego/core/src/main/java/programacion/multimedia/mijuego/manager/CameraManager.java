package programacion.multimedia.mijuego.manager;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraManager {

    private LogicManager logicManager;
    private LevelManager levelManager;
    OrthographicCamera camera;

    public CameraManager(LogicManager logicManager, LevelManager levelManager) {
        this.logicManager = logicManager;
        this.levelManager = levelManager;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 12 * 32, 12 * 32);
        camera.update();
    }

    public void handleCamera() {
        // ToDo Seguir al personaje para que la camara le enfoque
        camera.position.set(logicManager.player.getPosition().x, 10 * 32 / 2, 0);

        camera.update();
        levelManager.mapRenderer.setView(camera);
        levelManager.mapRenderer.render(new int[]{0, 1, 2});
    }
}
