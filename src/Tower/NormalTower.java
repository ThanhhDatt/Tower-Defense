package Tower;

import Bullet.NormalBullet;
import Enemy.Enemy;
import Enemy.NormalEnemy;
import Initialization.ImageProcessing;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class NormalTower extends Tower {
    public NormalTower(GraphicsContext gc, ArrayList<Enemy> enemyList) {
        this.gc = gc;
        range = 64 * 4;
        pos = new Vec2d(7 * 64, 8 * 64);
        speed = 8;
        image = ImageProcessing.splits(19, 10);
        enemyListPos = enemyList;
        bullet = new NormalBullet(this, gc);
    }
}
