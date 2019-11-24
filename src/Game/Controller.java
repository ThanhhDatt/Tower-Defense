package Game;


import Initialization.Define;
import Initialization.ImageProcessing;
import Player.Player;
import Tower.NormalTower;
import Tower.RocketTower;
import com.sun.javafx.geom.Vec2d;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller extends AnimationTimer {
    private static Vec2d pos = new Vec2d();
    public static GameField gameField;
    private static boolean isRunning = false;
    private static boolean addNormalTower = false;
    private static boolean addRockerTower = false;
    private static boolean filledMap = false;
    private static boolean filledTower = false;
    private static boolean visibilityRange = false;
    private static int range;
    private static Vec2d posRangeTower;
    private static int sell;

    private Controller() {
    }

    private static Controller controllerInstance = null;

    public static Controller getInstance() {
        if (controllerInstance == null) controllerInstance = new Controller();
        return controllerInstance;
    }

    public static Button startButton;
    public static Button stopButton;
    public static Button nextWaveButton;
    public static Button normalTowerButton;
    public static Button rockerTowerButton;
    public static Button machineGunTowerButton;


    static {
        // startButton
        startButton = new Button("Start");
        startButton.setMinSize(100, 100);
        startButton.setMaxSize(100, 100);
        startButton.setOnAction(e -> {
            isRunning = true;
        });

        //stopButton
        stopButton = new Button("Stop");
        stopButton.setMinSize(100, 100);
        stopButton.setMaxSize(100, 100);
        stopButton.setOnAction(e -> {
            isRunning = false;
        });

//        //nextWave Button
//        nextWaveButton = new Button("Next Wave");
//        nextWaveButton.setMinSize(100, 100);
//        nextWaveButton.setOnAction(e -> {
//            controllerInstance.gameField.nextWave();
//        });
        //normalTowerButton
        normalTowerButton = new Button("Normal Tower\n", new ImageView(ImageProcessing.splits(19, 10)));
        normalTowerButton.setMaxWidth(200);
        normalTowerButton.setOnAction(e -> {
            if (!addNormalTower && Player.money > Define.normalTowerPrice) {
                addNormalTower = true;
                addRockerTower = false;
                Player.money -= Define.normalTowerPrice;
            }
        });
//
        //rocketTowerButton
        rockerTowerButton = new Button("Rocket Tower\n", new ImageView(ImageProcessing.splits(22, 8)));
        rockerTowerButton.setMaxWidth(200);
        rockerTowerButton.setOnAction(e -> {
            if (!addNormalTower && Player.money > Define.rocketTowerPrice) {
                addNormalTower = false;
                addRockerTower = true;
                Player.money -= Define.rocketTowerPrice;
            }
        });
//
//        //machineGunButton
//        machineGunTowerButton = new Button("Machine Tower\n" + Config.MACHINE_GUN_TOWER_COST + "$", new ImageView(Sprite.machineGunTowerTop.scale(.6, .6).getImage()));
//        machineGunTowerButton.setMaxWidth(200);
//        machineGunTowerButton.setOnAction(e -> {
//            controllerInstance.towerOnDrag = new MachineGunTower(controllerInstance.gameField);
//        });


    }

    @Override
    public void handle(long now) {
        if (isRunning) {
            gameField.update();
        }
        gameField.draw();
        this.draw();
    }

    public static void mouseClicked(MouseEvent mouseEvent) {
        if (addNormalTower) {
            if (!filledMap && !filledTower) {
                gameField.getTowerList().add(new NormalTower(gameField.gc, gameField.getEnemyList(), new Vec2d(pos.x - 32, pos.y - 32)));
                addNormalTower = false;
            }
        }
        if (addRockerTower) {
            if (!filledMap && !filledTower) {
                gameField.getTowerList().add(new RocketTower(gameField.gc, gameField.getEnemyList(), new Vec2d(pos.x - 32, pos.y - 32)));
                addRockerTower = false;
            }
        }
        if (visibilityRange && mouseEvent.getButton() == MouseButton.SECONDARY) {
            Player.money += gameField.getTowerList().get(sell).getPriceSell();
            gameField.getTowerList().remove(sell);
            visibilityRange = false;
        }
    }

    public void draw() {
        if (addNormalTower || addRockerTower) {
            if (addNormalTower) {
                gameField.gc.drawImage(ImageProcessing.splits(19, 10), pos.x - 32, pos.y - 32);
                gameField.gc.strokeOval((pos.x) - Define.normalRange, (pos.y) - Define.normalRange, Define.normalRange * 2, Define.normalRange * 2);

            }
            if (addRockerTower) {
                gameField.gc.drawImage(ImageProcessing.splits(22, 8), pos.x - 32, pos.y - 32);
                gameField.gc.strokeOval((pos.x) - Define.rocketRange, (pos.y) - Define.rocketRange, Define.rocketRange * 2, Define.rocketRange * 2);

            }
            for (int i = 0; i < 24; i++) {
                for (int j = 0; j < 40; j++) {
                    if (filledMap || filledTower) {
                        gameField.gc.setFill(Color.RED);
                    } else {
                        gameField.gc.setFill(Color.BLACK);
                    }
                    gameField.gc.fillRect(j * Define.LENGTH_IMAGE / 2, i * Define.LENGTH_IMAGE / 2, 1, Define.LENGTH_IMAGE / 2);
                    gameField.gc.fillRect(j * Define.LENGTH_IMAGE / 2, i * Define.LENGTH_IMAGE / 2, Define.LENGTH_IMAGE / 2, 1);
                }
            }
        } else {
            if (visibilityRange) {
                gameField.gc.strokeOval((posRangeTower.x + 32) - range, (posRangeTower.y + 32) - range, range * 2, range * 2);
            }
        }
    }

    public static void mouseMove(MouseEvent mouseEvent) {
        pos.x = mouseEvent.getX();
        pos.y = mouseEvent.getY();
        if (addNormalTower || addRockerTower) {
            if (gameField.getRoad().getMap()[(int) (pos.y / 64)][(int) (pos.x / 64)] != 7 || gameField.getRoad().getObstacles()[(int) (pos.y / 64)][(int) (pos.x / 64)] != 0) {
                filledMap = true;
            } else {
                filledMap = false;
            }

            for (int i = 0; i < gameField.getTowerList().size(); i++) {
                if (Math.abs(pos.x - gameField.getTowerList().get(i).getPos().x - 32) <= 56 && Math.abs(pos.y - gameField.getTowerList().get(i).getPos().y - 32) <= 56) {
                    filledTower = true;
                    break;
                } else {
                    filledTower = false;
                }
            }
        } else {
            visibilityRange = false;
            for (int i = 0; i < gameField.getTowerList().size(); i++) {
                if (Math.abs(pos.x - gameField.getTowerList().get(i).getPos().x - 32) <= 32 && Math.abs(pos.y - gameField.getTowerList().get(i).getPos().y - 32) <= 32) {
                    visibilityRange = true;
                    posRangeTower = gameField.getTowerList().get(i).getPos();
                    range = gameField.getTowerList().get(i).getRange();
                    sell = i;
                }
            }
        }
    }
}





