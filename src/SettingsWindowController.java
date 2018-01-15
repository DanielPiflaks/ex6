import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsWindowController implements Initializable {
    final String propertyFileName = "GameProperties";

    @FXML
    private RadioButton startsFirstBlack;
    @FXML
    private RadioButton startsFirstWhite;
    @FXML
    private RadioButton firstPlayerColorBlack;
    @FXML
    private RadioButton firstPlayerColorWhite;
    @FXML
    private RadioButton secondPlayerColorBlack;
    @FXML
    private RadioButton secondPlayerColorWhite;
    @FXML
    private ComboBox<String> comboBoxBoardSize;

    private SettingsGameManager settingsGameManager;

    public SettingsWindowController() {
        this.settingsGameManager = new SettingsGameManager(propertyFileName);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Enums.PlayersColors firstPlayerColors = this.settingsGameManager.getFirstPlayerColor();
        Enums.PlayersColors secondPlayerColors = this.settingsGameManager.getSecondPlayerColor();
        Enums.PlayersColors startFirstOptions = this.settingsGameManager.getStartFirstOptions();
        int size = this.settingsGameManager.getBoardSize();

        switch (firstPlayerColors) {
            case Black:
                firstPlayerColorBlack.setSelected(true);
                break;
            case White:
                firstPlayerColorWhite.setSelected(true);
                break;
            default:
                break;
        }

        switch (secondPlayerColors) {
            case Black:
                secondPlayerColorBlack.setSelected(true);
                break;
            case White:
                secondPlayerColorWhite.setSelected(true);
                break;
            default:
                break;
        }

        switch (startFirstOptions) {
            case Black:
                startsFirstBlack.setSelected(true);
                break;
            case White:
                startsFirstWhite.setSelected(true);
                break;
            default:
                break;

        }

        ObservableList<String> sizeOptions = FXCollections.observableArrayList();
        for (int i = 4; i <= 20; i = i + 2) {
            sizeOptions.add(i + "x" + i);
        }
        this.comboBoxBoardSize.setItems(sizeOptions);
        this.comboBoxBoardSize.setValue(size + "x" + size);
    }

    @FXML
    private void changeFirstPlayerToBlack() {
        this.firstPlayerColorBlack.setSelected(true);
        this.settingsGameManager.setFirstPlayerColor(Enums.PlayersColors.Black);
    }

    @FXML
    private void changeFirstPlayerToWhite() {
        this.firstPlayerColorWhite.setSelected(true);
        this.settingsGameManager.setFirstPlayerColor(Enums.PlayersColors.White);
    }

    @FXML
    private void changeSecondPlayerToBlack() {
        this.secondPlayerColorBlack.setSelected(true);
        this.settingsGameManager.setSecondPlayerColor(Enums.PlayersColors.Black);
    }

    @FXML
    private void changeSecondPlayerToWhite() {
        this.secondPlayerColorWhite.setSelected(true);
        this.settingsGameManager.setSecondPlayerColor(Enums.PlayersColors.White);
    }

    @FXML
    private void changeStartFirstPlayerBlack() {
        this.startsFirstBlack.setSelected(true);
        this.settingsGameManager.setStartFirstOptions(Enums.PlayersColors.Black);
    }

    @FXML
    private void changeStartFirstPlayerWhite() {
        this.startsFirstWhite.setSelected(true);
        this.settingsGameManager.setStartFirstOptions(Enums.PlayersColors.White);
    }

    @FXML
    private void changeBoardSize() {
        final int doubleDigitSizeString = 5;
        String boardSize = this.comboBoxBoardSize.getValue();
        String numberString;
        if (boardSize.length() == doubleDigitSizeString) {
            numberString = boardSize.substring(0, 2);
        } else {
            numberString = boardSize.substring(0, 1);
        }
        int boardSizeInt = Integer.parseInt(numberString);
        this.settingsGameManager.setBoardSize(boardSizeInt);
    }

    @FXML
    private void save() {
        try {
            this.settingsGameManager.save();
        } catch (Exception e) {
            System.out.println("Can't save new file.");
        }
        // get a handle to the stage
        Stage stage = (Stage) comboBoxBoardSize.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
