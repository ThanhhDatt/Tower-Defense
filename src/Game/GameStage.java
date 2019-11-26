package Game;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class GameStage {
    protected static Stage stage = new Stage();

    GameStage(Stage stage) {
        this.stage = stage;
    }

    public void run() {
        stage.setTitle("Timeline Example");

        AnchorPane root = new AnchorPane();

        Canvas canvas = new Canvas(1280, 768);
        Canvas inf = new Canvas(200, 200);
        GraphicsContext infgc = inf.getGraphicsContext2D();
        GameField gameField = new GameField(canvas.getGraphicsContext2D(), infgc);
        Scene theScene = new Scene(root);

        Group menuRoot = new Group();
        ImageView imv = new ImageView(new Image("Background/backmenu.png", 1280 + 200,768,false,true));
        Scene menu = new Scene(menuRoot, 1280+200, 768);
        stage.setScene(menu);
        menu.setOnMouseClicked(mouse -> {
            int x =(int) mouse.getX();
            int y =(int) mouse.getY();
            if(x > 577 && x < 900 && y > 581 && y < 656) {
                stage.setScene(theScene);
            }
        });

        menuRoot.getChildren().addAll(imv);

//        stage.setScene(theScene);

        GridPane buttonList = new GridPane();
        canvas.setOnMouseClicked(Controller::mouseClicked);
        canvas.setOnMouseMoved(Controller::mouseMove);

        AnchorPane.setLeftAnchor(buttonList, (double) 20 * 64);
        buttonList.add(Controller.restartButton,0,0,2,1);
        buttonList.add(Controller.startButton, 0, 1);
        buttonList.add(Controller.stopButton, 1, 1);
        buttonList.add(Controller.normalTowerButton, 0, 2, 2, 1);
        buttonList.add(Controller.rockerTowerButton, 0, 3, 2, 1);
        buttonList.add(inf, 0, 4, 2, 1);

        root.getChildren().addAll(canvas, buttonList);


        Controller.gameField = gameField;
        Controller.getInstance().start();


        stage.show();

    }


}
