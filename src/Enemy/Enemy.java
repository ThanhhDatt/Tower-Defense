package Enemy;

import Initialization.Define;
import com.sun.javafx.geom.Vec2f;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javax.jws.Oneway;
import java.util.ArrayList;

public abstract class Enemy extends GameEntity {
    protected int blood;
    protected float speed;
    protected int armor;
    protected int prize;
    protected double dangerousLevel;
    protected ArrayList<Image> images = new ArrayList<>();

    public double getDangerousLevel()
    {
        return dangerousLevel;
    }

    protected enum Direction{
        LEFT,RIGHT,UP,DOWN;
    }

    //status begin
    protected Direction direction = Direction.RIGHT;


    protected String check = new String("TURN_RIGHT");
    protected void check(int map[][])
    {
        //checkRight
        if(direction==Direction.RIGHT)
        {
            if(map[(int)((Math.round(pos.y)+32)/ Define.LENGTH_IMAGE)][(int)((Math.round(pos.x)+32)/ Define.LENGTH_IMAGE)]==10)
            {
                check= "TURN_UP";
            }

            if(map[(int)((Math.round(pos.y)+31)/ Define.LENGTH_IMAGE)][(int)((Math.round(pos.x)+32)/ Define.LENGTH_IMAGE)]==5)
            {
                check= "TURN_DOWN";
            }
        }


        if(direction==Direction.LEFT)
        {
            if(map[(int)((Math.round(pos.y)+32)/ Define.LENGTH_IMAGE)][(int)((Math.round(pos.x)+31)/ Define.LENGTH_IMAGE)]==9)
            {
                check= "TURN_UP";
            }

            if(map[(int)((Math.round(pos.y)+31)/ Define.LENGTH_IMAGE)][(int)((Math.round(pos.x)+31)/ Define.LENGTH_IMAGE)]==4)
            {
                check= "TURN_DOWN";
            }
        }


        if(direction==Direction.UP)
        {
            if(map[(int)((Math.round(pos.y)+31)/ Define.LENGTH_IMAGE)][(int)((Math.round(pos.x)+31)/ Define.LENGTH_IMAGE)]==4)
            {
                check= "TURN_RIGHT";
            }

            if(map[(int)((Math.round(pos.y)+31)/ Define.LENGTH_IMAGE)][(int)((Math.round(pos.x)+32)/ Define.LENGTH_IMAGE)]==5)
            {
                check= "TURN_LEFT";
            }
        }


        if(direction==Direction.DOWN)
        {
            if(map[(int)((Math.round(pos.y)+32)/ Define.LENGTH_IMAGE)][(int)((Math.round(pos.x)+31)/ Define.LENGTH_IMAGE)]==9)
            {
                check= "TURN_RIGHT";
            }

            if(map[(int)((Math.round(pos.y)+32)/ Define.LENGTH_IMAGE)][(int)((Math.round(pos.x)+32)/ Define.LENGTH_IMAGE)]==10)
            {
                check= "TURN_LEFT";
            }
        }
    }


    public void move(int map[][])
    {
        check(map);

        if(check.equals("TURN_RIGHT"))
        {
            pos.x=pos.x+speed;
            direction=Direction.RIGHT;
        }

        if(check.equals("TURN_LEFT"))
        {
            pos.x=pos.x-speed;
            direction=Direction.LEFT;
        }

        if(check.equals("TURN_UP"))
        {
            pos.y=pos.y-speed;
            direction=Direction.UP;
        }

        if(check.equals("TURN_DOWN"))
        {
            pos.y=pos.y+speed;
            direction=Direction.DOWN;
        }
    }

    public void update(int map[][])
    {
        move(map);
        dangerousLevel += speed;
    }

    public void draw()
    {
        if(check.equals("TURN_RIGHT"))
        {
            gc.drawImage(images.get(0),pos.x,pos.y);
        }
        if(check.equals("TURN_LEFT"))
        {
            gc.drawImage(images.get(2),pos.x,pos.y);
        }
        if(check.equals("TURN_UP"))
        {
            gc.drawImage(images.get(3),pos.x,pos.y);
        }
        if(check.equals("TURN_DOWN"))
        {
            gc.drawImage(images.get(1),pos.x,pos.y);
        }
    }
}
