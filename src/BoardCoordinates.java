public class BoardCoordinates implements Comparable {

    private int row;
    private int column;

    //Board Coordinates constructor.
    BoardCoordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Check if input coordinate is bigger then this coordinate.
     *
     * @param boardCoordinate
     * @return true if input coordinate is bigger, else otherwise.
     */
    boolean isBigger(BoardCoordinates boardCoordinate) {
        if (this.getRow() == boardCoordinate.getRow()) {
            return (this.getColumn() < boardCoordinate.getColumn());
        } else {
            return (this.getRow() < boardCoordinate.getRow());
        }
    }

    /**
     * Check if input coordinate is smaller then this coordinate.
     *
     * @param boardCoordinate
     * @return true if input coordinate is smaller, else otherwise.
     */
    boolean isSmaller(BoardCoordinates boardCoordinate) {
        if (this.getRow() == boardCoordinate.getRow()) {
            return (this.getColumn() > boardCoordinate.getColumn());
        } else {
            return (this.getRow() > boardCoordinate.getRow());
        }
    }

    /**
     * to String overload
     *
     * @return string to represent board coordinate.
     */
    public String toString() {
        String result = "(" + this.row + ", " + this.column + ')';
        return result;
    }


    /**
     * Getter.
     *
     * @return Returns row parameter.
     */
    int getRow() {
        return this.row;
    }

    /**
     * Getter.
     *
     * @return Returns column parameter.
     */
    int getColumn() {
        return this.column;
    }

    @Override
    public int compareTo(Object o) {
        BoardCoordinates boardCoordinate = (BoardCoordinates) o;

        if (this.getRow() == boardCoordinate.getRow()) {
            if (this.getColumn() < boardCoordinate.getColumn())
                return -1;
            else
                return 1;
        } else if (this.getColumn() == boardCoordinate.getColumn()) {
            return 0;
        } else {
            if (this.getRow() < boardCoordinate.getRow()) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        BoardCoordinates boardCoordinate = (BoardCoordinates) other;
        return ((this.getRow() == boardCoordinate.getRow()) &&
                (this.getColumn() == boardCoordinate.getColumn()));

    }

}
