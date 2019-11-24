package Enemy;

import Initialization.ImageProcessing;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;



public class TankerEnemy extends Enemy{
    public TankerEnemy( GraphicsContext gc)
    {
        this.gc=gc;
        pos = new Vec2d(-32,64*10+32);
        blood=50;
        currentBlood = 50;
        speed= (float) 1;
        armor=10;
        prize=7;
        image= ImageProcessing.splits(16,10);
        for(int i=0;i<4;i++)
        {
            images.add(ImageProcessing.rotateImage(image,i*90));
        }
    }
}
