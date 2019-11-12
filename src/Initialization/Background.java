package Initialization;

import Enemy.GameEntity;
import Tower.Tower;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Background extends GameEntity{

    protected static Image[][] images = load();
    protected static int[][] map = loadMapFromFile();
    protected static GraphicsContext gc;

    public Background(GraphicsContext gc) {
        this.gc = gc;
    }

    public int[][] getMap()
    {
        return map;
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

    private static int[][] loadMapFromFile() {
        int a[][] = new int[12][20];
        File file = new File("src/Initialization/Map.txt");
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
                gc.drawImage(images[index / 5 ][index % 5 ], j * Define.LENGTH_IMAGE, i * Define.LENGTH_IMAGE);
                gc.fillRect(j*Define.LENGTH_IMAGE, i*Define.LENGTH_IMAGE, 1, Define.LENGTH_IMAGE);
                gc.fillRect(j*Define.LENGTH_IMAGE,i*Define.LENGTH_IMAGE, Define.LENGTH_IMAGE,1);
            }
        }
    }
}
