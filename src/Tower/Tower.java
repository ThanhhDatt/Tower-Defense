package Tower;

import Bullet.Bullet;
import Enemy.Enemy;
import Enemy.GameEntity;
import Initialization.ImageProcessing;
import Initialization.Rotate;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class Tower extends GameEntity {
    protected int damage;
    protected double speed;
    protected int range;
    private double angle = 0;
    protected ArrayList<Enemy> enemyListPos = new ArrayList<>();
    protected Bullet bullet;
    protected Enemy dangerousEnemy;
    protected Image pedestal;
    protected int priceBuy;
    protected int priceSell;

    public int getPriceSell(){
        return priceSell;
    }

    public Enemy getDangerousEnemy() {
        return dangerousEnemy;
    }

    public int getRange() {
        return range;
    }

    public void setPos(Vec2d pos) {
        this.pos = pos;
    }

    public Vec2d getPos() {
        return pos;
    }

    public double getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public void update() {
        getTarget();
        if (dangerousEnemy != null) {
            angle = Rotate.setAngle(dangerousEnemy.getPos(), pos);
        }
        bullet.shoot(dangerousEnemy);
    }

    public void getTarget() {
        if (!bullet.isMoving) {
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
        gc.drawImage(pedestal, pos.x, pos.y);
        ImageProcessing.draw(gc, image, angle, pos.x, pos.y);
        bullet.draw();
    }
}
