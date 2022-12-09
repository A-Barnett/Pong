package com.example.pong;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.example.pong.entity;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.time.LocalTimer;
import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.animationBuilder;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.*;
import static com.example.pong.Main.BALL_SPEED;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitter;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.beans.binding.Bindings;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.getip;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.FXGL;
import javafx.util.Duration;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.dsl.components.RandomMoveComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

public class Factory implements EntityFactory {
    private static final double BALL_SIZE = 10;

    @Spawns("Player")
    public Entity Player(SpawnData data){
        var physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);


        return entityBuilder()
                .from(data)
                .with(physics)
                .type(entity.PLAYER)
                .viewWithBBox(new Rectangle(10,75, Color.BLACK))
                .with(new BatComponent())
                .build();
    }

    @Spawns("Enemy")
    public Entity Enemy(SpawnData data){
        var physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);


        return entityBuilder()
                .from(data)
                .with(physics)
                .type(entity.ENEMY)
                .with(new EnemyComponent())
                .viewWithBBox(new Rectangle(10,75, Color.BLACK))
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
                .viewWithBBox(new Circle(10,10,10))
                .with(new CollidableComponent(true))
                .with(new BallComponent())
                .collidable()
                .build();
    }




}
