package com.example.pong;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;

import static com.almasb.fxgl.dsl.FXGL.*;
import static java.lang.Math.abs;
import static java.lang.Math.signum;


public class BallComponent extends Component {

    public PhysicsComponent physics;

    @Override
    public void onUpdate(double tpf) {
        limitVelocity();
        checkOffscreen();
    }

    public void limitVelocity() {
        // stops the ball moving too slow
        if (abs(physics.getVelocityX()) < 7 * 60) {
            physics.setVelocityX(signum(physics.getVelocityX()) * 7 * 60);
        }
        if (abs(physics.getVelocityX()) == 0) {
            physics.setVelocityX(7 * 60);
        }
        if (abs(physics.getVelocityY()) > 20 * 60) {
            physics.setVelocityY(7 * 60);
        }
        if (abs(physics.getVelocityX()) > 20 * 60) {
            physics.setVelocityX(signum(physics.getVelocityX()) - (1));
        }


    }

    // if the ballis pushed through a wall to outside of the screen, respawn ball
    public void checkOffscreen() {
        if (getEntity().getBoundingBoxComponent().isOutside(getGameScene().getViewport().getVisibleArea())) {
            physics.overwritePosition(new Point2D(
                    getAppWidth() / 2,
                    getAppHeight() / 2
            ));
        }
    }
}