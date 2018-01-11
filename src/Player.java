import java.util.ArrayList;
import java.util.List;
import java.util.Map;

abstract public class Player {

    protected char symbol;
    protected Board board;
    protected List<BoardCoordinates> playerMoves;
    protected GameLogic gameLogic;
    protected GuiDisplay display;

    /**
     * Player constructor.
     *
     * @param symbol
     * @param board
     * @param gameLogic
     */
    Player(char symbol, Board board, GameLogic gameLogic, GuiDisplay display) {
        this.symbol = symbol;
        this.gameLogic = gameLogic;
        this.board = board;
        this.display = display;
        this.playerMoves = new ArrayList<>();
    }

    /**
     * Virtual function that plays ones turn for player.
     *
     * @return flipped board coordinates vector.
     */
    abstract Map<BoardCoordinates, List<BoardCoordinates>> playOneTurn();


    /**
     * Add player symbol to board.
     *
     * @param row    row where to add.
     * @param column column where to add.
     */
    void addPlayerSymbol(int row, int column) {
        //Create new coordinate.
        BoardCoordinates newCoordinate = new BoardCoordinates(row, column);
        //Insert it to player moves.
        playerMoves.add(newCoordinate);
        //Put this symbol on board.
        board.putSymbolOnBoard(row, column, symbol);
    }

    /**
     * Gets remove coordinates and remove it from player moves vector.
     *
     * @param removeCoordinates coordinates to remove.
     */
    void updatePlayerSymbolRemoved(List<BoardCoordinates> removeCoordinates) {
        //Remove each coordinate from vector.
        for (int i = 0; i < removeCoordinates.size(); ++i) {
            if (playerMoves.contains(removeCoordinates.get(i))) {
                playerMoves.remove(removeCoordinates.get(i));
            }
        }
    }

    /**
     * Gets add coordinates and add it to player moves vector.
     *
     * @param addCoordinates coordinates to add.
     */
    void updatePlayerSymbolAdd(List<BoardCoordinates> addCoordinates) {
        //Insert coordinates
        for (int i = 0; i < addCoordinates.size(); ++i) {
            if (!this.playerMoves.contains(addCoordinates.get(i))) {
                this.playerMoves.add(addCoordinates.get(i));
            }
        }
    }

    /**
     * Getter
     *
     * @return Returns player symbol.
     */
    char getSymbol() {
        return this.symbol;
    }

    /**
     * Getter
     *
     * @return Return player score(how many symbols on board).
     */
    int getScore() {
        return this.playerMoves.size();
    }

    /**
     * Getter.
     *
     * @return Return player vector of cells with his symbols (his moves on board).
     */
    List<BoardCoordinates> getPlayerMoves() {
        return this.playerMoves;
    }

}
