import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import org.w3c.dom.css.Rect;


import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.TRANSPARENT;
import static javafx.scene.paint.Color.WHITE;

public class GameBoardController implements Initializable, PressNotifier {
    private List<BoardRectangle> rectangles;
    private int boardSize;
    private boolean wasClick;
    @FXML
    private GridPane root;
    @FXML
    private GridPane gameBoard;
    @FXML
    private Label currentPlayer;
    @FXML
    private Label blackPlayerScore;
    @FXML
    private Label whitePlayerScore;


    public GameBoardController(int boardSize) {
        this.boardSize = boardSize;
        this.rectangles = new ArrayList<BoardRectangle>();
        this.wasClick = false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.gameBoard = new GridPane();
        gameBoard.setOnMouseClicked(event -> {
            this.onBoardClick();
        });
        this.gameBoard.getChildren().clear();
        this.gameBoard.setGridLinesVisible(false);
        this.gameBoard.setGridLinesVisible(true);

        int guiHeight = (int) root.getPrefHeight() - 50;
        int guiWidth = (int) root.getPrefWidth() - 50;
        int cellHeight = guiHeight / this.boardSize;
        int cellWidth = guiWidth / this.boardSize;

        for (int i = 0; i < this.boardSize; i++) {
            gameBoard.add(new Rectangle(cellWidth, cellHeight, TRANSPARENT), i, i);
        }

        Circle newCircle = new Circle(15);
        gameBoard.add(newCircle, (int) (boardSize * 0.5), (int) (boardSize * 0.5));
        GridPane.setValignment(newCircle, VPos.CENTER);
        GridPane.setHalignment(newCircle, HPos.CENTER);

        newCircle = new Circle(15);
        gameBoard.add(newCircle, (int) (boardSize * 0.5 - 1), (int) (boardSize * 0.5 - 1));
        GridPane.setValignment(newCircle, VPos.CENTER);
        GridPane.setHalignment(newCircle, HPos.CENTER);

        newCircle = new Circle(15);
        newCircle.setFill(WHITE);
        newCircle.setStroke(BLACK);
        gameBoard.add(newCircle, (int) (boardSize * 0.5 - 1), (int) (boardSize * 0.5));
        GridPane.setValignment(newCircle, VPos.CENTER);
        GridPane.setHalignment(newCircle, HPos.CENTER);

        newCircle = new Circle(15);
        newCircle.setFill(WHITE);
        newCircle.setStroke(BLACK);
        gameBoard.add(newCircle, (int) (boardSize * 0.5), (int) (boardSize * 0.5 - 1));
        GridPane.setValignment(newCircle, VPos.CENTER);
        GridPane.setHalignment(newCircle, HPos.CENTER);


        root.getChildren().add(this.gameBoard);




        /*root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            mazeBoard.setPrefWidth(boardNewWidth);
            mazeBoard.draw();
        });

        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            mazeBoard.setPrefHeight(newValue.doubleValue());
            mazeBoard.draw();
        });*/


    }

    public void drawBoard(Board board) {
        int guiHeight = (int) root.getPrefHeight() - 50;
        int guiWidth = (int) root.getPrefWidth() - 50;
        int cellHeight = guiHeight / this.boardSize;
        int cellWidth = guiWidth / this.boardSize;

        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                BoardRectangle newRec = new BoardRectangle(cellWidth, cellHeight, TRANSPARENT, i, j);
                rectangles.add(newRec);
                gameBoard.add(newRec, i, i);
                if (board.getSymbolByPlace(i + 1, j + 1) == 'x') {
                    Circle black = new Circle(15);
                    black.setFill(BLACK);
                    gameBoard.add(black, i, j);
                    GridPane.setValignment(black, VPos.CENTER);
                    GridPane.setHalignment(black, HPos.CENTER);
                } else if (board.getSymbolByPlace(i + 1, j + 1) == 'o') {
                    Circle white = new Circle(15);
                    white.setFill(WHITE);
                    white.setStroke(BLACK);
                    gameBoard.add(white, i, j);
                    GridPane.setValignment(white, VPos.CENTER);
                    GridPane.setHalignment(white, HPos.CENTER);
                }
            }
        }
    }

    public void changeCurrentPlayer(String playerName) {
        this.currentPlayer.setText(playerName);
    }

    public void changeBlackPlayerScore(int blackPlayerScore) {
        this.blackPlayerScore.setText(String.valueOf(blackPlayerScore));
    }

    public void changeWhitePlayerScore(int whitePlayerScore) {
        this.whitePlayerScore.setText(String.valueOf(whitePlayerScore));
    }


    public void onBoardClick() {
        this.wasClick = true;
    }

    public BoardCoordinates getPlayerMove() {
        BoardCoordinates move = null;

        while(!this.wasClick){
            move = getClickedPlace();

        }

        this.wasClick = false;

        return move;
    }

    public BoardCoordinates getClickedPlace() {
        for (int i = 0; i < rectangles.size(); i++) {
            for (int j = 0; j < rectangles.size(); j++) {
                if (this.rectangles.get(i).isClicked()) {
                    return new BoardCoordinates(i, j);
                }
            }
        }
        return null;
    }

    @Override
    public void pressNotify(int row, int col) {

    }

    @Override
    public void addPressListener(PressListener pressListener) {

    }

    @Override
    public void removePressListener(PressListener pressListener) {

    }
}
