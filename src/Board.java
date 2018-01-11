public class Board {

    private int numRows;
    private int numCols;
    private char[][] boardMatrix;

    /**
     * Constructor.
     *
     * @param numRows number of rows.
     * @param numCols number of columns.
     */
    Board(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        boardMatrix = new char[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                boardMatrix[i][j] = ' ';
            }
        }
    }

    /**
     * Take symbol and put it on board in wanted row and column.
     *
     * @param row    wanted row.
     * @param column wanted column.
     * @param symbol wanted symbol.
     */
    void putSymbolOnBoard(int row, int column, char symbol) {
        boardMatrix[row - 1][column - 1] = symbol;
    }

    //Returns true if row and column is

    /**
     * Checks if given coordinate is on board.
     *
     * @param row    wanted row.
     * @param column wanted column.
     * @return true if on board, else otherwise.
     */
    boolean isOnBoard(int row, int column) {
        return ((row > 0) && (row <= numRows) && (column > 0) && (column <= numCols));
    }

    //Returns symbol that is in input row and column place.

    /**
     * Return symbol by place.
     *
     * @param row    row place.
     * @param column column place.
     * @return symbol from wanted place.
     */
    char getSymbolByPlace(int row, int column) {
        if (!isOnBoard(row, column)) {
            throw new RuntimeException("Invalid place!");
        }
        return boardMatrix[row - 1][column - 1];
    }

    /**
     * Getter.
     *
     * @return numRows.
     */
    int getNumRows() {
        return this.numRows;
    }

    /**
     * Getter
     *
     * @return numCols.
     */
    int getNumCols() {
        return this.numCols;
    }


}
