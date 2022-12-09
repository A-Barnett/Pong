package com.example.pong;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;

import static com.almasb.fxgl.dsl.FXGL.*;
import static java.lang.Math.*;

/**
 * @author Almas Baimagambetov (AlmasB) (almaslvl@gmail.com)
 */
public class BallComponent extends Component {

    public PhysicsComponent physics;

    @Override
    public void onUpdate(double tpf) {
        limitVelocity();
        checkOffscreen();
    }

   public void limitVelocity() {
        // we don't want the ball to move too slow in X direction
        if (abs(physics.getVelocityX()) < 7 * 60) {
            physics.setVelocityX(signum(physics.getVelocityX()) * 7 * 60);
        }
       if (abs(physics.getVelocityX())== 0){
           physics.setVelocityX(7 * 60);
       }
       if(abs(physics.getVelocityY()) > 20 * 60){
           physics.setVelocityY(7*60);
       }
       if(abs(physics.getVelocityX()) > 20 * 60){
           physics.setVelocityX(signum(physics.getVelocityX()) - (1));
       }


        // we don't want the ball to move too fast in Y direction

    }

    // this is a hack:
    // we use a physics engine, so it is possible to push the ball through a wall to outside of the screen
    public void checkOffscreen() {
        if (getEntity().getBoundingBoxComponent().isOutside(getGameScene().getViewport().getVisibleArea())) {
            physics.overwritePosition(new Point2D(
                    getAppWidth() / 2,
                    getAppHeight() / 2
            ));
        }
    }
}