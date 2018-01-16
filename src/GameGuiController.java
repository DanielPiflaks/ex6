import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameGuiController extends GridPane {

    @FXML
    Button startGameButton;

    public GameGuiController() {

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startGame() {
        final String propertyFileName = "GameProperties";

        SettingsGameManager propertyManager = new SettingsGameManager(propertyFileName);
        int boardSize = propertyManager.getBoardSize();
        GuiDisplay display = new ConsoleGuiDisplay();

        Enums.PlayersColors firstPlayerColor = propertyManager.getFirstPlayerColor();
        Enums.PlayersColors secondPlayerColor = propertyManager.getSecondPlayerColor();
        Enums.PlayersColors openingPlayer = propertyManager.getStartFirstOptions();

        //Create game parameters.
        GameParameters gameParameters = new GameParameters(Enums.PlayerOptions.HumanPlayerOp, firstPlayerColor, Enums.PlayerOptions.HumanPlayerOp,
                secondPlayerColor, boardSize, display,
                openingPlayer, Enums.GameLogicOptions.StandartGame);
        //Create game with those parameters.
        //Game game = new Game(gameParameters);

        GameBoardController gameBoardController = new GameBoardController(gameParameters);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
            fxmlLoader.setController(gameBoardController);
            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = (Stage) this.startGameButton.getScene().getWindow();
            stage.setScene(new Scene(root1, 800, 450));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

