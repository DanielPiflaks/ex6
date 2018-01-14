import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GameGuiController extends GridPane {

    public GameGuiController() {
        //this.board = board;
        /*FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("GameGui.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }*/
    }

    public void openSettingsWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SettingsWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void startGame(){
        final String propertyFileName = "GameProperties";
        //Set size of game board.
        final int numberRows = 4;
        final int numberColumns = 4;

        GuiDisplay display = new ConsoleGuiDisplay();

        SettingsGameManager propertyManager = new SettingsGameManager(propertyFileName);
        //Create game parameters.
        GameParameters gameParameters = new GameParameters(Enums.PlayerOptions.HumanPlayerOp, 'x', Enums.PlayerOptions.HumanPlayerOp,
                'o', numberRows, numberColumns, display,
                Enums.PlayersColors.Black, Enums.GameLogicOptions.StandartGame);
        //Create game with those parameters.
        Game game = new Game(gameParameters);
        //Run single game.
        game.RunSingleGame();
    }

}

