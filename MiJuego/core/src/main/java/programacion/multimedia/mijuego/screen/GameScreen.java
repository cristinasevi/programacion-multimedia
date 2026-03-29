package programacion.multimedia.mijuego.screen;

import com.badlogic.gdx.Screen;
import programacion.multimedia.mijuego.manager.*;

/** Pantalla de juego */
public class GameScreen implements Screen {

    private LogicManager logicManager;
    private RenderManager renderManager;
    private LevelManager levelManager;
    private CameraManager cameraManager;

    public GameScreen() {
        logicManager = new LogicManager();
        levelManager = new LevelManager(logicManager);
        levelManager.loadCurrentLevel();
        renderManager = new RenderManager(logicManager, levelManager.batch);
        cameraManager = new CameraManager(logicManager, levelManager);
    }

    @Override
    public void show() {
        logicManager.load();
    }

    @Override
    public void render(float delta) {
        logicManager.update(delta);
        cameraManager.handleCamera();
        renderManager.drawFrame();
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        logicManager.dispose();
        renderManager.dispose();
    }
}
