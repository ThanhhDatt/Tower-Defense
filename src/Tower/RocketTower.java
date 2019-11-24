package Tower;

import Bullet.NormalBullet;
import Bullet.RocketBullet;
import Enemy.Enemy;
import Initialization.ImageProcessing;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class RocketTower extends Tower {
    public RocketTower(GraphicsContext gc, ArrayList<Enemy> enemyList, Vec2d pos) {
        this.gc = gc;
        range = 5*64;
        damage = 20;
        speed = 4;
        this.pos=pos;
        image = ImageProcessing.splits(22, 9);
        enemyListPos = enemyList;
        bullet = new RocketBullet(this, gc);
        damage=5;
        pedestal=ImageProcessing.splits(22,7);
        priceBuy = 25;
        priceSell = 15;
    }
}
