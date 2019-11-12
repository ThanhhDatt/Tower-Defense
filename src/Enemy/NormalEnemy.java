package Enemy;

import Initialization.ImageProcessing;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;


public class NormalEnemy extends Enemy {

    public NormalEnemy( GraphicsContext gc)
    {
        this.gc=gc;
        pos = new Vec2d(-32,64*10+32);
        blood=10;
        speed= (float) 2;
        armor=10;
        prize=10;
        image= ImageProcessing.splits(15,10);
        for(int i=0;i<4;i++)
        {
            images.add(ImageProcessing.rotateImage(image,i*90));
        }
    }
}
