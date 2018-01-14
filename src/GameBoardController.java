import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


import java.io.IOException;

public class GameBoardController extends GridPane {
    private char[][] board;

    @FXML
    private Label currentPlayer;
    @FXML
    private GridPane GameBoard;

    public GameBoardController() {
        //this.board = board;
        /*FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("GameBoard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }*/
    }

    @FXML
    protected void setName(){
        String bla = currentPlayer.getText();
    }

    @FXML
    protected void setPlayer(){

    }
}
