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
import java.util.*;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.TRANSPARENT;
import static javafx.scene.paint.Color.WHITE;

public class GameBoardController implements Initializable {

    private GameParameters gameParameters;
    private Player firstPlayer;
    private Player secondPlayer;
    private Player currentPlayerPlaying;

    @FXML
    private GridPane root;
    @FXML
    private Board gameBoard;
    @FXML
    private Label currentPlayer;
    @FXML
    private Label blackPlayerScore;
    @FXML
    private Label whitePlayerScore;


    public GameBoardController(GameParameters gameParameters) {
        this.gameParameters = gameParameters;
        //Get game board.
        this.gameBoard = gameParameters.getGameBoard();
        //Get enum who start first.
        Enums.PlayersColors startFirstOptions = gameParameters.getStartFirst();
        //Initialize players by who start first options.
        switch (startFirstOptions) {
            case Black: {
                if (gameParameters.getPlayer1().getSymbol() == Enums.PlayersColors.Black) {
                    firstPlayer = gameParameters.getPlayer1();
                    secondPlayer = gameParameters.getPlayer2();
                } else {
                    secondPlayer = gameParameters.getPlayer1();
                    firstPlayer = gameParameters.getPlayer2();
                }
                break;
            }

            case White: {
                if (gameParameters.getPlayer1().getSymbol() == Enums.PlayersColors.White) {
                    firstPlayer = gameParameters.getPlayer1();
                    secondPlayer = gameParameters.getPlayer2();
                } else {
                    secondPlayer = gameParameters.getPlayer1();
                    firstPlayer = gameParameters.getPlayer2();
                }
                break;
            }
        }
        putSymbolStartPlace();
        currentPlayerPlaying = firstPlayer;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //this.gameBoard = new GridPane();
        changeCurrentPlayer(currentPlayerPlaying.getSymbol());
        //Print player 1 score.
        changePlayerScore(this.firstPlayer.getSymbol(), this.firstPlayer.getScore());
        //Print player 2 score.
        changePlayerScore(this.secondPlayer.getSymbol(), this.secondPlayer.getScore());
        gameBoard.setOnMouseClicked(event -> {
            this.playTurn();
        });
        /*this.gameBoard.getChildren().clear();
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
        */


        this.gameBoard.drawBoard((int) root.getPrefHeight(), (int) root.getPrefWidth());
        root.getChildren().add(this.gameBoard);
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 120;
            gameBoard.setPrefWidth(boardNewWidth);
            gameBoard.drawBoard((int) root.getPrefHeight(), (int) root.getPrefWidth());
        });

        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            gameBoard.setPrefHeight(newValue.doubleValue());
            gameBoard.drawBoard((int) root.getPrefHeight(), (int) root.getPrefWidth());
        });


    }

    public void putSymbolStartPlace() {
        //Get number of columns.
        int numCols = this.gameBoard.getNumCols();
        //Get number of rows.
        int numRows = this.gameBoard.getNumRows();
        //Divide by 2 to put start symbols in the middle.
        int middlePlaceCol = numCols / 2;
        int middlePlaceRow = numRows / 2;

        if (firstPlayer.getSymbol() == Enums.PlayersColors.Black) {
            //Put symbols by its start position.
            this.secondPlayer.addPlayerSymbol(middlePlaceRow + 1, middlePlaceCol + 1);
            this.secondPlayer.addPlayerSymbol(middlePlaceRow, middlePlaceCol);

            //Put player1 symbols by its start position.
            this.firstPlayer.addPlayerSymbol(middlePlaceRow, middlePlaceCol + 1);
            this.firstPlayer.addPlayerSymbol(middlePlaceRow + 1, middlePlaceCol);
        } else {
            //Put symbols by its start position.
            this.firstPlayer.addPlayerSymbol(middlePlaceRow + 1, middlePlaceCol + 1);
            this.firstPlayer.addPlayerSymbol(middlePlaceRow, middlePlaceCol);

            //Put player1 symbols by its start position.
            this.secondPlayer.addPlayerSymbol(middlePlaceRow, middlePlaceCol + 1);
            this.secondPlayer.addPlayerSymbol(middlePlaceRow + 1, middlePlaceCol);
        }
    }

    public void drawBoard(Board board) {
        /*int guiHeight = (int) root.getPrefHeight() - 50;
        int guiWidth = (int) root.getPrefWidth() - 50;
        int cellHeight = guiHeight / this.boardSize;
        int cellWidth = guiWidth / this.boardSize;

        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                BoardRectangle newRec = new BoardRectangle(cellWidth, cellHeight, TRANSPARENT, i, j);
                this.rectangles.add(newRec);
                gameBoard.add(newRec, i, i);
                if (board.getSymbolByPlace(i + 1, j + 1) == Enums.PlayersColors.Black) {
                    Circle black = new Circle(15);
                    black.setFill(BLACK);
                    gameBoard.add(black, i, j);
                    GridPane.setValignment(black, VPos.CENTER);
                    GridPane.setHalignment(black, HPos.CENTER);
                } else if (board.getSymbolByPlace(i + 1, j + 1) == Enums.PlayersColors.White) {
                    Circle white = new Circle(15);
                    white.setFill(WHITE);
                    white.setStroke(BLACK);
                    gameBoard.add(white, i, j);
                    GridPane.setValignment(white, VPos.CENTER);
                    GridPane.setHalignment(white, HPos.CENTER);
                }
            }
        }*/
    }

    public void changeCurrentPlayer(Enums.PlayersColors color) {
        this.currentPlayer.setText(color.toString());
    }

    public void changePlayerScore(Enums.PlayersColors color, int score) {
        switch (color) {
            case Black:
                this.blackPlayerScore.setText(String.valueOf(score));
                break;
            case White:
                this.whitePlayerScore.setText(String.valueOf(score));
                break;
        }
    }


    public void playTurn() {
        BoardCoordinates move = this.gameBoard.getClickedPlace();
        if (move == null) {
            return;
        } else if (currentPlayerPlaying.checkIfValidMove(move)) {
            Map<BoardCoordinates, List<BoardCoordinates>> mapOfLastMove = new HashMap<>();
            List<BoardCoordinates> symbolsToUpdate = new ArrayList<>();
            mapOfLastMove = this.currentPlayerPlaying.playOneTurn(move);
            //Check if map of moves is not empty.
            if (!mapOfLastMove.isEmpty()) {
                //Extract the vector of cell to update from map.
                Map.Entry<BoardCoordinates, List<BoardCoordinates>> entry = mapOfLastMove.entrySet().iterator().next();
                symbolsToUpdate = entry.getValue();

                Player nextPlayer;
                if (this.currentPlayerPlaying == this.firstPlayer) {
                    nextPlayer = this.secondPlayer;
                } else {
                    nextPlayer = this.firstPlayer;
                }

                //Check if there is any symbols to updated.
                if (symbolsToUpdate.isEmpty()) {
                    //If not, that means that player didn't have any move.
                    //gameOverIndicator++;
                } else {
                    //Set game over indicator to be 0.
                    //gameOverIndicator = 0;
                    //Update first player of his new symbols.
                    this.currentPlayerPlaying.updatePlayerSymbolAdd(symbolsToUpdate);
                    //Update second player of symbols that he lost.
                    nextPlayer.updatePlayerSymbolRemoved(symbolsToUpdate);
                }

                this.gameBoard.drawBoard((int) root.getPrefHeight(), (int) root.getPrefWidth());

                //Print player 1 score.
                changePlayerScore(this.firstPlayer.getSymbol(), this.firstPlayer.getScore());
                //Print player 2 score.
                changePlayerScore(this.secondPlayer.getSymbol(), this.secondPlayer.getScore());


                if (nextPlayer.isThereMoves()) {
                    this.currentPlayerPlaying = nextPlayer;
                    changeCurrentPlayer(currentPlayerPlaying.getSymbol());
                }
            }
        }

    }
}
