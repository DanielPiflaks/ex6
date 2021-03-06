import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class main  extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane root = FXMLLoader.load(getClass().getResource("GameGui.fxml"));
        primaryStage.setTitle("Reversi game");
        primaryStage.setScene(new Scene(root, 800, 450));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
