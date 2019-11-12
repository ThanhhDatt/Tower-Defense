package Game;

import Initialization.Controler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameStage {
    protected static Stage stage = new Stage();

    GameStage(Stage stage) {
        this.stage = stage;
    }

    public void run() {
        stage.setTitle("Timeline Example");

        AnchorPane root = new AnchorPane();


        Scene theScene = new Scene(root);
        stage.setScene(theScene);

        Canvas canvas = new Canvas(1280, 768);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        GameField gameField = new GameField(gc);
        Controler.gameField = gameField;
        gameField.start();


        stage.show();

    }


}
