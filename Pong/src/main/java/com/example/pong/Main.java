package com.example.pong;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.MenuItem;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.shape.Line;

import java.util.EnumSet;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGL.addUINode;
import com.example.pong.EnemyComponent;

public class Main extends GameApplication {

    public Entity Player;
    private Entity Enemy;
    public Entity Ball;
    @FXML
    private Label labelScorePlayer;


    static final int BALL_SPEED = 5;
    private BatComponent playerBat;
    private Entity Floor;


    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setTitle("Pong");


    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0, 0);
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(entity.BALL, entity.WALL) {
            @Override
            protected void onHitBoxTrigger(Entity a, Entity b, HitBox boxA, HitBox boxB) {
                if (boxB.getName().equals("LEFT")) {
                    inc("player2score", +1);
                } else if (boxB.getName().equals("RIGHT")) {
                    inc("player1score", +1);
                }
            }
        });

        }





    @Override
    protected void initUI() {
        Text scoreText = getUIFactoryService().newText("", Color.BLACK, 24);
        scoreText.textProperty().bind(getip("player1score").asString("Score: [%d]"));
        addUINode(scoreText, getAppWidth()/2-90, 30);
        Text scoreText2 = getUIFactoryService().newText("", Color.BLACK, 24);
        scoreText2.textProperty().bind(getip("player2score").asString("Score: [%d]"));
        addUINode(scoreText2, getAppWidth()/2+80, 30);
        Line line_d = new Line(450, 600, 450, 0);
        line_d.getStrokeDashArray().addAll(6d);
        addUINode(line_d);







    }
    private void showGameOver(String winner) {
        if(winner.equals("Player 1")){
            getDialogService().showMessageBox("Game Over \nYou win!", getGameController()::exit);
        }else if(winner.equals("Player 2")){
            getDialogService().showMessageBox("Game Over \nYou Lose!", getGameController()::exit);
        }

    }
    private void initScreenBounds() {
        Entity walls = entityBuilder()
                .type(entity.WALL)
                .collidable()
                .buildScreenBounds(150);

        getGameWorld().addEntity(walls);
    }

   public void initGameObjects() {
        Ball = spawn("Ball", getAppWidth() / 2 - 5, getAppHeight() / 2 - 5);
      Player = spawn("Player", new SpawnData(getAppWidth() / 4 - 130, getAppHeight() / 2 - 30));
        Enemy = spawn("Enemy", new SpawnData(getAppWidth()  - 80, getAppHeight() / 2 - 30));

        playerBat = Player.getComponent(BatComponent.class);


    }

        @Override
        protected void initGame(){
            getWorldProperties().<Integer>addListener("player1score", (old, newScore) -> {
                if (newScore == 11) {
                    showGameOver("Player 1");
                }
            });

            getWorldProperties().<Integer>addListener("player2score", (old, newScore) -> {
                if (newScore == 11) {
                    showGameOver("Player 2");
                }
            });
            getGameWorld().addEntityFactory(new Factory());

            initScreenBounds();
            initGameObjects();


    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("player1score", 0);
        vars.put("player2score", 0);
    }






    @Override
    protected void initInput(){
        getInput().addAction(new UserAction("Up") {
            @Override
            protected void onAction() {
                playerBat.up();
            }

            @Override
            protected void onActionEnd() {
                playerBat.stop();
            }
        }, KeyCode.W);

        getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
                playerBat.down();
            }

            @Override
            protected void onActionEnd() {
                playerBat.stop();
            }
        }, KeyCode.S);
    }



    public static void main(String[] args){
        System.setProperty("quantum.multithreaded", "false");
        launch(args);
    }

}
