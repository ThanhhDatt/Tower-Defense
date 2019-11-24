package Background;

import Enemy.GameEntity;
import Initialization.Define;
import Initialization.ImageProcessing;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Background extends GameEntity {

    protected static Image[][] images = load();
    protected static Image[] obstacles = loadObstacle();
    protected static int[][] map = loadMapFromFile("src/Background/Map.txt");
    protected static int[][] obstacle = loadMapFromFile("src/Background/obstacle.txt");
    protected static GraphicsContext gc;

    public Background(GraphicsContext gc) {
        this.gc = gc;
    }

    public int[][] getMap() {
        return map;
    }

    public int[][] getObstacles() {
        return obstacle;
    }

    private static Image[] loadObstacle() {
        Image[] obstacles = new Image[6];
        obstacles[1] = ImageProcessing.splits(15, 5);
        obstacles[2] = ImageProcessing.splits(17, 5);
        obstacles[3] = ImageProcessing.splits(19, 5);
        obstacles[4] = ImageProcessing.splits(20, 5);
        obstacles[5] = ImageProcessing.splits(21, 5);

        return obstacles;
    }


    private static Image[][] load() {
        Image[][] images = new Image[3][5];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                images[i][j] = ImageProcessing.splits(j, i);
            }
        }
        return images;
    }

    private static int[][] loadMapFromFile(String path) {
        int a[][] = new int[12][20];
        File file = new File(path);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
            return null;
        }
        for (int i = 0; i < 12; i++)
            for (int j = 0; j < 20; j++)
                a[i][j] = sc.nextInt();
        //sc.close();
        return a;
    }


    public static void draw() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 20; j++) {
                int index = map[i][j] - 1;
                gc.drawImage(images[index / 5][index % 5], j * Define.LENGTH_IMAGE, i * Define.LENGTH_IMAGE);
            }
        }
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 20; j++) {
                if (obstacle[i][j] != 0) {
                    gc.drawImage(obstacles[obstacle[i][j]], j * Define.LENGTH_IMAGE, i * Define.LENGTH_IMAGE);
                }
            }
        }
    }


}
