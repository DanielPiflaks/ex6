import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private Board gameBoard;
    private Player firstPlayer;
    private Player secondPlayer;
    private GuiDisplay display;

    /**
     * Constructor.
     *
     * @param gameParameters game parameters.
     */
    Game(GameParameters gameParameters) {
        //Get game board.
        gameBoard = gameParameters.getGameBoard();
        //Get enum who start first.
        Enums.PlayersColors startFirstOptions = gameParameters.getStartFirst();
        //Initialize gui display.
        display = gameParameters.getGuiDisplay();
        //Initialize players by who start first options.
        switch (startFirstOptions) {
            case Black: {
                if (gameParameters.getPlayer1().getSymbol() == 'x') {
                    firstPlayer = gameParameters.getPlayer1();
                    secondPlayer = gameParameters.getPlayer2();
                } else {
                    secondPlayer = gameParameters.getPlayer1();
                    firstPlayer = gameParameters.getPlayer2();
                }
                break;
            }

            case White: {
                if (gameParameters.getPlayer1().getSymbol() == 'o') {
                    firstPlayer = gameParameters.getPlayer1();
                    secondPlayer = gameParameters.getPlayer2();
                } else {
                    secondPlayer = gameParameters.getPlayer1();
                    firstPlayer = gameParameters.getPlayer2();
                }
                break;
            }
        }
    }


    /**
     * Runs single game by input game parameters.
     */
    void RunSingleGame() {
        //Set game over indicator to be 0.
        int gameOverIndicator = 0;
        Map<BoardCoordinates, List<BoardCoordinates>> mapOfLastMove = new HashMap<>();
        List<BoardCoordinates> symbolsToUpdate = new ArrayList<>();
        //Put symbols to it start position.
        putSymbolsStartPosition();

        //Set game over indicator to be 0.
        gameOverIndicator = 0;
        //Game loop. ends when both players don't have any possible moves.
        while (gameOverIndicator < 2) {
            //Draw game board.
            this.display.printPlayerTurn(firstPlayer.getSymbol());
            this.display.printBoard(this.gameBoard);

            //Play one turn.
            mapOfLastMove = this.firstPlayer.playOneTurn();
            //Check if map of moves is not empty.
            if (!mapOfLastMove.isEmpty()) {
                //Extract the vector of cell to update from map.
                Map.Entry<BoardCoordinates, List<BoardCoordinates>> entry = mapOfLastMove.entrySet().iterator().next();
                symbolsToUpdate = entry.getValue();

                //Check if there is any symbols to updated.
                if (symbolsToUpdate.isEmpty()) {
                    //If not, that means that player didn't have any move.
                    gameOverIndicator++;
                } else {
                    //Set game over indicator to be 0.
                    gameOverIndicator = 0;
                    //Update first player of his new symbols.
                    this.firstPlayer.updatePlayerSymbolAdd(symbolsToUpdate);
                    //Update second player of symbols that he lost.
                    this.secondPlayer.updatePlayerSymbolRemoved(symbolsToUpdate);
                }
            } else {
                gameOverIndicator++;
            }

            if (gameOverIndicator == 2) {
                break;
            }
            //Draws game board.
            this.display.printPlayerTurn(secondPlayer.getSymbol());
            this.display.printBoard(this.gameBoard);
            //Play one turn.
            mapOfLastMove = this.secondPlayer.playOneTurn();
            //Check if map of moves is not empty.
            if (!mapOfLastMove.isEmpty()) {
                //Extract the vector of cell to update from map.
                Map.Entry<BoardCoordinates, List<BoardCoordinates>> entry = mapOfLastMove.entrySet().iterator().next();
                symbolsToUpdate = entry.getValue();
                //Check if any symbols updated.
                if (symbolsToUpdate.isEmpty()) {
                    //If not, that means that player didn't have any move.
                    gameOverIndicator++;
                } else {
                    //Set game over indicator to be 0.
                    gameOverIndicator = 0;
                    //Update second player of his new symbols.
                    this.secondPlayer.updatePlayerSymbolAdd(symbolsToUpdate);
                    //Update first player of symbols that he lost.
                    this.firstPlayer.updatePlayerSymbolRemoved(symbolsToUpdate);
                }
            } else {
                gameOverIndicator++;
            }
        }
        //Print player 1 score.
        this.display.printScore(this.firstPlayer.getSymbol(), this.firstPlayer.getScore());
        //Print player 2 score.
        this.display.printScore(this.secondPlayer.getSymbol(), this.secondPlayer.getScore());

        this.display.printMessage("Game result is:");
        //Check who won and print the result.
        if (this.firstPlayer.getScore() > this.secondPlayer.getScore()) {
            this.display.printWinner(this.firstPlayer.getSymbol());
        } else if (this.firstPlayer.getScore() < this.secondPlayer.getScore()) {

            this.display.printWinner(this.secondPlayer.getSymbol());
        } else {
            this.display.printMessage("It's a tie!");
        }

    }


    /**
     * Puts player symbols to start position.
     */
    private void putSymbolsStartPosition() {
        //Get number of columns.
        int numCols = this.gameBoard.getNumCols();
        //Get number of rows.
        int numRows = this.gameBoard.getNumRows();
        //Divide by 2 to put start symbols in the middle.
        int middlePlaceCol = numCols / 2;
        int middlePlaceRow = numRows / 2;

        //Put player1 symbols by its start position.
        this.firstPlayer.addPlayerSymbol(middlePlaceRow, middlePlaceCol + 1);
        this.firstPlayer.addPlayerSymbol(middlePlaceRow + 1, middlePlaceCol);

        //Put player2 symbols by its start position.
        this.secondPlayer.addPlayerSymbol(middlePlaceRow + 1, middlePlaceCol + 1);
        this.secondPlayer.addPlayerSymbol(middlePlaceRow, middlePlaceCol);
    }


}
