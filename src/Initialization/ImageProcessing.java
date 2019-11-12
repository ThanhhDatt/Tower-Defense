package Initialization;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ImageProcessing {
    public static Image imageSheet = new Image("Initialization/Entity.png");

    public static Image splits(int x, int y)
    {
        ImageView imageView = new ImageView(imageSheet);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        parameters.setViewport(new Rectangle2D(x*64,y*64,64,64));
        return imageView.snapshot(parameters, null);
    }

    public static Image rotateImage(Image img, double angle)
    {
        ImageView imageView = new ImageView(img);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        parameters.setViewport(new Rectangle2D(0,0,64,64));
        imageView.setRotate(angle);
        return imageView.snapshot(parameters, null);
    }

    private static void rotate(GraphicsContext gc, double angle, double px, double py) {
        javafx.scene.transform.Rotate r = new javafx.scene.transform.Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    public static void draw(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy) {
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2);
        gc.drawImage(image, tlpx, tlpy);
        gc.restore(); // back to original state (before rotation)
    }

}
