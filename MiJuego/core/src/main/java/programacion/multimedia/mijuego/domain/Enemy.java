package programacion.multimedia.mijuego.domain;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends Character {

    public Enemy(TextureRegion currentFrame, Vector2 position, State initialState, String leftAnimationName, String rightAnimationName) {
        super(currentFrame, position, initialState, leftAnimationName, rightAnimationName);
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        position.x -= 100 * dt;
    }
}
