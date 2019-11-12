package Game;


import Enemy.Enemy;
import Enemy.NormalEnemy;
import Enemy.TankerEnemy;
import Initialization.Background;
import Initialization.Controler;
import Tower.Tower;
import Tower.NormalTower;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class GameField extends AnimationTimer {

    protected GraphicsContext gc;
    protected Stage stage;
    private int round;
    private Controler controler;

    public GameField(GraphicsContext gc) {
        this.gc = gc;
        this.stage = stage;
        round = 0;
        road = new Background(gc);


        enemyQ = new LinkedList<>();
        for (int j = 0; j < 10; j++) {
            if (Math.round(Math.random()) == 0) {
                enemyQ.add(new NormalEnemy(gc));
            } else enemyQ.add(new TankerEnemy(gc));
        }
//        for (int j = 0; j < 10; j++) {
//            enemyQ.add(new TankerEnemy(gc));
//        }

        enemyList = new ArrayList<>();

        towerList = new ArrayList<>();

        for(int i=0;i<100;i++) {
            towerList.add(new NormalTower(gc, enemyList));
        }
    }

    private Background road;
    private Queue<Enemy> enemyQ;
    private ArrayList<Enemy> enemyList;
    private ArrayList<Tower> towerList;

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
                enemyList.get(i).update(road.getMap());
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
    }

    @Override
    public void handle(long now) {
        update();
        draw();
    }



}
