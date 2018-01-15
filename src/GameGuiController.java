import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameGuiController extends GridPane implements PressListener {

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
        GameBoardController gameBoardController = new GameBoardController(boardSize);
        GuiDisplay display = new JavaGuiDisplay(gameBoardController);

        char firstPlayerSymbol, secondPlayerSymbol;
        Enums.PlayersColors firstPlayerColor = propertyManager.getFirstPlayerColor();
        Enums.PlayersColors secondPlayerColor = propertyManager.getSecondPlayerColor();
        Enums.PlayersColors openingPlayer = propertyManager.getStartFirstOptions();

        switch (firstPlayerColor) {
            case Black:
                firstPlayerSymbol = 'x';
                break;
            case White:
                firstPlayerSymbol = 'o';
                break;
            default:
                firstPlayerSymbol = 'x';
                break;
        }

        switch (secondPlayerColor) {
            case Black:
                secondPlayerSymbol = 'x';
                break;
            case White:
                secondPlayerSymbol = 'o';
                break;
            default:
                secondPlayerSymbol = 'x';
                break;
        }


        //Create game parameters.
        GameParameters gameParameters = new GameParameters(Enums.PlayerOptions.HumanPlayerOp, firstPlayerSymbol, Enums.PlayerOptions.HumanPlayerOp,
                secondPlayerSymbol, boardSize, display,
                openingPlayer, Enums.GameLogicOptions.StandartGame);
        //Create game with those parameters.
        Game game = new Game(gameParameters);

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


        //Run single game.
        game.RunSingleGame();


    }

    @Override
    public void pressListen(int row, int col) {

    }
}

