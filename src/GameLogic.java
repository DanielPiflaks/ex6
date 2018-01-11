import java.util.List;
import java.util.Map;

abstract public class GameLogic {

    private Board board;

    /**
     * Game logic constructor.
     *
     * @param board board to play on.
     */
    GameLogic(Board board) {
        this.board = board;
    }


    /**
     * @return Returns board that game logic holds.
     */
    public Board getBoard() {
        return this.board;
    }


    /**
     * Gets vector of player moves on board and player symbol,
     * and returns map of each move to it possible play.
     *
     * @param playerMoves  player options on board.
     * @param playerSymbol player symbol.
     * @return map of all possible moves.
     */
    abstract Map<BoardCoordinates, List<BoardCoordinates>> getPossibleGameMoves(List<BoardCoordinates> playerMoves,
                                                                                char playerSymbol);

    /**
     * Gets map of all moves, player choice to flip and flips it.
     * the function returns all flipped symbols.
     *
     * @param allChoices   map of all choices.
     * @param wantedChoice wanted choice to flip.
     * @param playerSymbol player symbol to know what not to flip.
     * @return flipped symbols.
     */
    abstract List<BoardCoordinates> flipSymbols(Map<BoardCoordinates, List<BoardCoordinates>> allChoices,
                                                BoardCoordinates wantedChoice, char playerSymbol);


    /**
     * Gets map of all moves, player choice to flip and flips it.
     * the function returns all flipped symbols number without flip.
     *
     * @param allChoices   map of all choices.
     * @param wantedChoice wanted choice to flip.
     * @param playerSymbol player symbol to know what not to flip.
     * @return number of possible flips.
     */
    abstract int numberOfPossibleFlips(Map<BoardCoordinates, List<BoardCoordinates>> allChoices,
                                       BoardCoordinates wantedChoice, char playerSymbol);

    /**
     * Flips single board coordinate in given board.
     *
     * @param coordinatesToFlip coordinate to flip.
     * @param symbol            symbol to put.
     */
    protected void flipOnBoard(List<BoardCoordinates> coordinatesToFlip, char symbol) {
        for (int i = 0; i < coordinatesToFlip.size(); i++) {
            board.putSymbolOnBoard(coordinatesToFlip.get(i).getRow(),
                    coordinatesToFlip.get(i).getColumn(), symbol);
        }
    }

}
