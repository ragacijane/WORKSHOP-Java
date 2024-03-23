package MediaPlayer;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Player player = new Player("file:///I:/test.mp4");
        Scene  scene = new Scene(player, 720,480, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
