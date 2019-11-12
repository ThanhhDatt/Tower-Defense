package Initialization;

import Enemy.Enemy;
import com.sun.javafx.geom.Vec2d;

import java.util.ArrayList;

public class Rotate {
    public static double setAngle(Vec2d posEnemy, Vec2d pos) {
        Vec2d vector = new Vec2d(posEnemy.x - pos.x, posEnemy.y - pos.y);
        double lengthVector = Math.sqrt(Math.pow(vector.x, 2) + Math.pow(vector.y, 2));
        double cos = -vector.y / lengthVector;
        double angle = (Math.acos(cos) / Math.PI) * 180;
        if (posEnemy.x <= pos.x) {
            return -angle ;
        }
        else {
            return angle;
        }
    }
}
