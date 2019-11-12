package Tower;

import Bullet.Bullet;
import Enemy.Enemy;
import Enemy.GameEntity;
import Initialization.ImageProcessing;
import Initialization.Rotate;
import com.sun.javafx.geom.Vec2d;

import java.util.ArrayList;

public abstract class Tower extends GameEntity {
    protected int damage;
    protected double speed;
    protected int range;
    private double angle = 0;
    protected ArrayList<Enemy> enemyListPos = new ArrayList<>();
    protected Bullet bullet;
    protected Enemy dangerousEnemy;

    public Enemy getDangerousEnemy() {
        return dangerousEnemy;
    }

    public int getRange() {
        return range;
    }

    public double getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public void update() {
        getTarget();
        if(dangerousEnemy!=null) {
            angle = Rotate.setAngle(dangerousEnemy.getPos(), pos);
        }
        bullet.shoot(dangerousEnemy);
    }

    public void getTarget()
    {
        if(!bullet.isMoving) {
            for (int i = 0; i < enemyListPos.size(); i++) {
                if (Vec2d.distance(enemyListPos.get(i).getPos().x, enemyListPos.get(i).getPos().y, pos.x, pos.y) <= range) {
                    dangerousEnemy = enemyListPos.get(i);
                    return;
                }
            }
            dangerousEnemy = null;
        }
    }

    public void draw() {
        ImageProcessing.draw(gc,image,angle,pos.x,pos.y);
        bullet.draw();
        gc.strokeOval((pos.x + 32) - range, (pos.y + 32) - range, range * 2, range * 2);
        for (int i = 0; i < enemyListPos.size(); i++) {
            if (Vec2d.distance(enemyListPos.get(i).getPos().x, enemyListPos.get(i).getPos().y, pos.x, pos.y) <= range) {
                gc.strokeLine(pos.x + 32, pos.y + 32, enemyListPos.get(i).getPos().x + 32, enemyListPos.get(i).getPos().y + 32);
                break;
            }
        }
    }
}
