package Enemy;

import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;


public abstract class GameEntity {
    protected Vec2d pos;
    protected Image image = null;
    protected static GraphicsContext gc;

    public Vec2d getPos() {
        return pos;
    }


}
