package Bullet;

import Initialization.ImageProcessing;
import Tower.Tower;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;

public class RocketBullet extends Bullet {
    public RocketBullet(Tower tower, GraphicsContext gc) {
        this.gc = gc;
        image = ImageProcessing.splits(19, 11);
        damage = tower.getDamage();
        speed = tower.getSpeed();
        range = tower.getRange();
        pos = new Vec2d(tower.getPos());
        towerPos = tower.getPos();
        isMoving = false;
    }
}
