package Bullet;

import Enemy.Enemy;
import Enemy.GameEntity;
import Initialization.ImageProcessing;
import Tower.Tower;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;

public abstract class Bullet extends GameEntity {
    protected double speed;
    protected int damage;
    protected int range;
    protected Vec2d towerPos;
    public boolean isMoving;

    public void shoot(Enemy dangerousEnemy) {
        if (dangerousEnemy!= null) {
            double d= Vec2d.distance(pos.x,pos.y,dangerousEnemy.getPos().x,dangerousEnemy.getPos().y);
            double dx= dangerousEnemy.getPos().x - pos.x;
            double dy= dangerousEnemy.getPos().y - pos.y;
            double vx;
            double vy;
            if(d<speed){
                vx=dx;
                vy=dy;
                pos.x+=vx;
                pos.y+=vy;
                isMoving = false;
                pos=new Vec2d(towerPos);
            }
            else{
                vx=speed*dx/d;
                vy=speed*dy/d;
                pos.x+=vx;
                pos.y+=vy;
                isMoving = true;
            }
        }
    }

    public void draw() {
        if (Vec2d.distance(towerPos.x, towerPos.y, pos.x, pos.y) >= 32) {
            gc.drawImage(image, pos.x, pos.y);
        }
    }


}
