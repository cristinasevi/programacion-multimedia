package programacion.multimedia.mijuego.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.*;

import static programacion.multimedia.mijuego.util.Constants.GAME_NAME;

/** Pantalla de juego */
public class ConfigurationScreen implements Screen {

    private Stage stage;
    private Preferences preferences;

    private void loadPreferences() {
        preferences = Gdx.app.getPreferences(GAME_NAME);

        // Por defecto el sonido y música estarán activas
        if (!preferences.contains("sound_effects")) {
            preferences.putBoolean("sound_effects", true);
        }
        if (!preferences.contains("music")) {
            preferences.putBoolean("music", true);
        }
        // Nivel por defecto: Medio (2)
        if (!preferences.contains("level")) {
            preferences.putInteger("level", 2);
        }

        preferences.flush();
    }

    @Override
    public void show() {
        if(!VisUI.isLoaded()) {
            VisUI.load();
        }

        loadPreferences();

        stage = new Stage();

        VisTable table = new VisTable(true);
        table.setFillParent(true);
        stage.addActor(table);

        VisCheckBox checkSound = new VisCheckBox("Efectos de sonido");
        checkSound.setChecked(preferences.getBoolean("sound_effects", true));
        checkSound.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                preferences.putBoolean("sound_effects", checkSound.isChecked());
                preferences.flush();
            }
        });

        VisCheckBox checkMusic = new VisCheckBox("Música de fondo");
        checkMusic.setChecked(preferences.getBoolean("music", true));
        checkMusic.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                preferences.putBoolean("music", checkMusic.isChecked());
                preferences.flush();
            }
        });

        VisList<String> selectLevel = new VisList<>();
        selectLevel.getItems().add("Fácil");
        selectLevel.getItems().add("Medio");
        selectLevel.getItems().add("Difícil");
        selectLevel.setSelectedIndex(preferences.getInteger("level", 2) - 1);
        selectLevel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                preferences.putInteger("level", selectLevel.getSelectedIndex() + 1);
                preferences.flush();
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
        table.add(selectLevel).center().width(300).height(50).pad(10);
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
