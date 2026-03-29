package programacion.multimedia.mijuego.domain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import lombok.Data;

import static programacion.multimedia.mijuego.domain.Character.State.*;
import static programacion.multimedia.mijuego.util.Constants.PLAYER_SPEED;

@Data
public class Player extends Character implements Disposable {

    public Player(TextureRegion texture, String leftAnimationName, String rightAnimationName) {
        super(texture, new Vector2(100, 100), IDLE_RIGHT, leftAnimationName, rightAnimationName);
    }

    public void handleInput(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            state = RUNNING_LEFT;
            position.x -= PLAYER_SPEED * delta;
        } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            state = RUNNING_RIGHT;
            position.x += PLAYER_SPEED * delta;
        } else if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            state = JUMPING;
        } else {
            if(state != IDLE_LEFT && state != IDLE_RIGHT)
                state = state == RUNNING_LEFT ? IDLE_LEFT : IDLE_RIGHT;
        }

        rectangle.setPosition(position.x, position.y);
    }

    @Override
    public void dispose() {

    }
}
