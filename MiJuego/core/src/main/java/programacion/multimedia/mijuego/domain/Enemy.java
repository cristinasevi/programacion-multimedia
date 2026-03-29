package programacion.multimedia.mijuego.domain;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends Character {

    private int speed;

    public Enemy(TextureRegion currentFrame, Vector2 position, State initialState, int speed,
                 String leftAnimationName, String rightAnimationName) {
        super(currentFrame, position, initialState, leftAnimationName, rightAnimationName);

        this.speed = speed;
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        position.x -= speed * dt;
    }
}
