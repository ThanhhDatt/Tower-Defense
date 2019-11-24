package Tower;

import Bullet.NormalBullet;
import Enemy.Enemy;
import Enemy.NormalEnemy;
import Initialization.ImageProcessing;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class NormalTower extends Tower {
    public NormalTower(GraphicsContext gc, ArrayList<Enemy> enemyList, Vec2d pos) {
        this.gc = gc;
        range = 3*64;
        damage = 5;
        speed = 8;
        this.pos=pos;
        image = ImageProcessing.splits(19, 10);
        enemyListPos = enemyList;
        bullet = new NormalBullet(this, gc);
        damage=5;
        pedestal = ImageProcessing.splits(19,7);
        priceBuy =15;
        priceSell = 8;
    }



}
