package Game;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage stage) throws FileNotFoundException {
        GameStage gameStage= new GameStage(stage);
        gameStage.run();
    }
}