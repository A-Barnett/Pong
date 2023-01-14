package com.example.pong;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

public class Factory implements EntityFactory {
    private static final double BALL_SIZE = 10;

    @Spawns("Player")
    public Entity Player(SpawnData data) {
        var physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);


        return entityBuilder()
                .from(data)
                .with(physics)
                .type(entity.PLAYER)
                .viewWithBBox(new Rectangle(10, 75, Color.BLACK))
                .with(new BatComponent())
                .build();
    }

    @Spawns("Enemy")
    public Entity Enemy(SpawnData data) {
        var physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);


        return entityBuilder()
                .from(data)
                .with(physics)
                .type(entity.ENEMY)
                .with(new EnemyComponent())
                .viewWithBBox(new Rectangle(10, 75, Color.BLACK))
                .build();
    }

    @Spawns("Ball")
    public Entity Ball(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setFixtureDef(new FixtureDef().density(0.0f).restitution(1.1f));
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setOnPhysicsInitialized(() -> physics.setLinearVelocity(7 * 60, -7 * 60));


        return entityBuilder()
                .from(data)
                .type(entity.BALL)
                .bbox(new HitBox(BoundingShape.circle(5)))
                .with(physics)
                .viewWithBBox(new Circle(10, 10, 10))
                .with(new CollidableComponent(true))
                .with(new BallComponent())
                .collidable()
                .build();
    }


}
