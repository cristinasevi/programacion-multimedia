package programacion.multimedia.mijuego.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

/** Pantalla de juego */
public class ConfigurationScreen implements Screen {

    private Stage stage;

    @Override
    public void show() {
        if(!VisUI.isLoaded()) {
            VisUI.load();
        }

        stage = new Stage();

        VisTable table = new VisTable(true);
        table.setFillParent(true);
        stage.addActor(table);

        VisCheckBox checkSound = new VisCheckBox("Efectos de sonido");
        checkSound.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // ToDo Activar/Desactivar sonido
            }
        });

        VisCheckBox checkMusic = new VisCheckBox("Música de fondo");
        checkMusic.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // ToDo Activar/Desactivar música
            }
        });

        VisTextButton backMainMenuButton = new VisTextButton("Volver");
        backMainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
            }
        });

        table.row();
        table.add(checkSound).center().width(300).height(50).pad(5);
        table.row();
        table.add(checkMusic).center().width(300).height(50).pad(5);
        table.row();
        table.add(backMainMenuButton).center().width(300).height(100).pad(5);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        stage.getViewport().update(width, height, true);
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
        // Destroy screen's assets here.
    }
}
