package com.example.pong;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Physics implementation from:
 *
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class PhysicsSample extends GameApplication {

    enum Type {
        PLAYER, ENEMY
    }

    private Entity Player;

    @Override
    protected void initSettings(GameSettings settings) {
    }

    @Override
    protected void initGame() {
        FXGL.entityBuilder()
                .type(Type.PLAYER)
                .at(100, 100)
                // 1. define hit boxes manually
                .bbox(new HitBox(BoundingShape.box(40, 40)))
                .view(new Rectangle(40, 40, Color.BLUE))
                // 2. make it collidable
                .collidable()
                // Note: in case you are copy-pasting, this class is in dev.DeveloperWASDControl
                // and enables WASD movement for testing
                .buildAndAttach();

        FXGL.entityBuilder()
                .type(Type.ENEMY)
                .at(200, 100)
                // 1. OR let the view generate it from view data
                .viewWithBBox(new Rectangle(40, 40, Color.RED))
                // 2. make it collidable
                .collidable()
                .buildAndAttach();


    }

    @Override
    protected void initPhysics() {
        // the order of entities is determined by
        // the order of their types passed into this method
        FXGL.onCollision(Type.PLAYER, Type.ENEMY, (player, enemy) -> {
            System.out.println("On Collision");
        });


    }

    public static void main(String[] args) {
        launch(args);
    }
}