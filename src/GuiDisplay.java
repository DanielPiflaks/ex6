import java.util.List;

public interface GuiDisplay {

    /**
     * Print board.
     *
     * @param board current board.
     */
    void printBoard(Board board);

    /**
     * Print other player last played move.
     *
     * @param playerSymbol other player symbol.
     * @param move         other player last played move.
     */
    void printOtherPlayerMove(char playerSymbol, BoardCoordinates move);

    /**
     * Print message.
     *
     * @param message relevant message.
     */
    void printMessage(String message);

    /**
     * Print who's turn is it.
     *
     * @param playerSymbol player symbol of current player turn.
     */
    void printPlayerTurn(char playerSymbol);

    /**
     * Print list of possible current player moves.
     *
     * @param possibleMoves vector of player possible moves.
     */
    void printPossibleMoves(List<BoardCoordinates> possibleMoves);

    /**
     * Print player score in certain format.
     *
     * @param playerSymbol symbol of relevant player.
     * @param playerScore  score of relevant player.
     */
    void printScore(char playerSymbol, int playerScore);

    /**
     * Print winner in certain format.
     *
     * @param playerSymbol symbol of player who won.
     */
    void printWinner(char playerSymbol);

    /**
     * Printing main menu for user.
     */
    void printMainMenu();

    /**
     * If player choice was to play with remote player, print relevant menu.
     */
    void printClientMenu();

    /**
     * Getting from user wanted play.
     *
     * @return
     */
    BoardCoordinates getUserPlayChoice(List<BoardCoordinates> possibleMoves, Board board);

}
