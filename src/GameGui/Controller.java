package GameGui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class Controller extends GridPane {
    private static final int FREE = 0;
    private static final int WALL = 1;

    public Controller() {
        /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameGui.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }*/
    }
}
