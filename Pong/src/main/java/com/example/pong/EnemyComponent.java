package com.example.pong;

import com.almasb.fxgl.entity.Entity;


public class EnemyComponent extends BatComponent {
    private Entity ball;

    @Override
    public void onUpdate(double tpf) {
        if (ball == null) {
            ball = entity.getWorld().getSingletonOptional(com.example.pong.entity.BALL).orElse(null);
        } else {
            moveAI();
        }
    }

    //Simple AI, moves bat to follow the ball
    private void moveAI() {
        Entity bat = entity;

        boolean isBallToLeft = ball.getRightX() <= bat.getX();

        if (ball.getY() < (bat.getY() - 5)) {
            if (isBallToLeft)
                up();
            else
                down();
        } else if (ball.getY() > (bat.getY() + 5)) {
            if (isBallToLeft)
                down();
            else
                up();
        } else {
            stop();
        }
    }
}