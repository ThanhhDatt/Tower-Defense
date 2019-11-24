package Game;


import Enemy.Enemy;
import Enemy.NormalEnemy;
import Enemy.TankerEnemy;
import Background.Background;
import Player.Player;
import Tower.Tower;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class GameField {

    public static GraphicsContext gc;
    public static GraphicsContext infgc;
    private int round;

    public GameField(GraphicsContext gc, GraphicsContext infgc) {
        GameField.gc = gc;
        round = 0;
        road = new Background(gc);

        GameField.infgc =infgc;

        GameField.infgc.setFill(Color.BLACK);
        GameField.infgc.setFont(new Font(32));

        enemyQ = new LinkedList<>();
        for (int j = 0; j < 10; j++) {
            if (Math.round(Math.random()) == 0) {
                enemyQ.add(new NormalEnemy(gc));
            } else enemyQ.add(new TankerEnemy(gc));
        }

//        wave = new ArrayList<>();
//        for(int i=0;i<10;i++){
//
//        }

        enemyList = new ArrayList<>();

        towerList = new ArrayList<>();

    }

    private Background road;
    private Queue<Enemy> enemyQ;
    private ArrayList<Enemy> enemyList;
    private ArrayList<Tower> towerList;
    private ArrayList<Queue<Enemy>> waves;
    private int wave;

    public Background getRoad() {
        return road;
    }

    public ArrayList<Tower> getTowerList() {
        return towerList;
    }

    public ArrayList getEnemyList() {
        return enemyList;
    }

    public void update() {
        if (!enemyQ.isEmpty()) {
            if (round % 20 == 0) {
                enemyList.add(enemyQ.poll());
            }
        }
        round++;


        for (int i = 0; i < towerList.size(); i++) {
            towerList.get(i).update();
        }
        for (int i = 0; i < enemyList.size(); i++) {
            if (enemyList.get(i).getCurrentBlood() <= 0) {
                Player.money += enemyList.get(i).getPrize();
                enemyList.remove(i);
            }
            if(!enemyList.isEmpty()) {
                if (enemyList.get(i).getOnDestination()) {
                    enemyList.remove(i);
                    Player.blood--;
                }
            }
            if (!enemyList.isEmpty()) {
                enemyList.get(i).update(road.getMap());
            }
        }

        Collections.sort(enemyList, (Enemy a, Enemy b) -> {
            if (a.getDangerousLevel() > b.getDangerousLevel()) return -1;
            if (a.getDangerousLevel() < b.getDangerousLevel()) return 1;
            return 0;
        });

    }


    public void draw() {
        road.draw();
        for (int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).draw();
        }
        for (int i = 0; i < towerList.size(); i++) {
            towerList.get(i).draw();
        }
        infgc.setFill(Color.WHITE);
        infgc.fillRect(0, 0,200,200);
        infgc.setFill(Color.BLACK);
        infgc.fillText("Blood: "+Player.blood + "\n" +"$: " +Player.money,0,50 );

    }

}
