import java.lang.reflect.Array;
import java.util.*;

public class StandartGameLogic extends GameLogic {

    /**
     * Constructor.
     *
     * @param board board to play on.
     */
    public StandartGameLogic(Board board) {
        super(board);
    }

    @Override
    Map<BoardCoordinates, List<BoardCoordinates>> getPossibleGameMoves(List<BoardCoordinates> playerMoves, Enums.PlayersColors playerColor) {
        Map<BoardCoordinates, List<BoardCoordinates>> possibleMoves = new HashMap<>();
        BoardCoordinates resultCoordinate;
        //For each player move, create vector of it's possible flip symbols of opponent.
        for (int i = 0; i < playerMoves.size(); i++) {
            List<BoardCoordinates> movementsForCurrentMove = new ArrayList<>();
            //Get column.
            int column = playerMoves.get(i).getColumn();
            //Get row.
            int row = playerMoves.get(i).getRow();
            //Check right possible plays.
            resultCoordinate = CheckByDirection(row, column, Enums.DirectionCheck.Right, playerColor);
            if ((resultCoordinate.getRow() != -1) && (resultCoordinate.getColumn() != -1)) {
                movementsForCurrentMove.add(resultCoordinate);
            }
            //Check left possible plays.
            resultCoordinate = CheckByDirection(row, column, Enums.DirectionCheck.Left, playerColor);
            if ((resultCoordinate.getRow() != -1) && (resultCoordinate.getColumn() != -1)) {
                movementsForCurrentMove.add(resultCoordinate);
            }
            //Check up possible plays.
            resultCoordinate = CheckByDirection(row, column, Enums.DirectionCheck.Up, playerColor);
            if ((resultCoordinate.getRow() != -1) && (resultCoordinate.getColumn() != -1)) {
                movementsForCurrentMove.add(resultCoordinate);
            }
            //Check down possible plays.
            resultCoordinate = CheckByDirection(row, column, Enums.DirectionCheck.Down, playerColor);
            if ((resultCoordinate.getRow() != -1) && (resultCoordinate.getColumn() != -1)) {
                movementsForCurrentMove.add(resultCoordinate);
            }
            //Check up left possible plays.
            resultCoordinate = CheckByDirection(row, column, Enums.DirectionCheck.UpLeft, playerColor);
            if ((resultCoordinate.getRow() != -1) && (resultCoordinate.getColumn() != -1)) {
                movementsForCurrentMove.add(resultCoordinate);
            }
            //Check down right possible plays.
            resultCoordinate = CheckByDirection(row, column, Enums.DirectionCheck.DownRight, playerColor);
            if ((resultCoordinate.getRow() != -1) && (resultCoordinate.getColumn() != -1)) {
                movementsForCurrentMove.add(resultCoordinate);
            }
            //Check up right possible plays.
            resultCoordinate = CheckByDirection(row, column, Enums.DirectionCheck.UpRight, playerColor);
            if ((resultCoordinate.getRow() != -1) && (resultCoordinate.getColumn() != -1)) {
                movementsForCurrentMove.add(resultCoordinate);
            }
            //Check down left possible plays.
            resultCoordinate = CheckByDirection(row, column, Enums.DirectionCheck.DownLeft, playerColor);
            if ((resultCoordinate.getRow() != -1) && (resultCoordinate.getColumn() != -1)) {
                movementsForCurrentMove.add(resultCoordinate);
            }
            //Sort moves.
            Collections.sort(movementsForCurrentMove);
            //Add it to map by relevant move.
            possibleMoves.put(playerMoves.get(i), movementsForCurrentMove);
        }
        //Return possible moves.
        return possibleMoves;

    }

    @Override
    List<BoardCoordinates> flipSymbols(Map<BoardCoordinates, List<BoardCoordinates>> allChoices, BoardCoordinates wantedChoice, Enums.PlayersColors playerColor) {
        List<BoardCoordinates> removePlaces = new ArrayList<>();
        //For each coordinate check if player choice is in it and flip the row.
        for (Map.Entry<BoardCoordinates, List<BoardCoordinates>> moves : allChoices.entrySet()) {
            //Get start coordinate.
            BoardCoordinates fromCoordinate = moves.getKey();
            //Get all possible moves.
            List<BoardCoordinates> availableMoves = moves.getValue();
            //Check if wanted choice is in available moves.
            if (availableMoves.contains(wantedChoice)) {
                //Flip wanted row.
                List<BoardCoordinates> singleRow = singleRowToFlip(fromCoordinate,
                        wantedChoice, playerColor);
                flipOnBoard(singleRow, playerColor);
                //insert flipped row into remove places vector.
                for (int i = 0; i < singleRow.size(); ++i) {
                    if (!(removePlaces.contains(singleRow.get(i)))) {
                        removePlaces.add(singleRow.get(i));
                    }
                }

            }
        }
        return removePlaces;
    }

    @Override
    int numberOfPossibleFlips(Map<BoardCoordinates, List<BoardCoordinates>> allChoices, BoardCoordinates wantedChoice, Enums.PlayersColors playerColor) {
        //Counter.
        int flipNumbers = 0;
        List<BoardCoordinates> removePlaces = new ArrayList<>();
        //For each coordinate check if player choice is in it and flip the row.
        for (Map.Entry<BoardCoordinates, List<BoardCoordinates>> moves : allChoices.entrySet()) {
            //Get start coordinate.
            BoardCoordinates fromCoordinate = moves.getKey();
            //Get all possible moves.
            List<BoardCoordinates> availableMoves = moves.getValue();
            //Check if wanted choice is in available moves.
            if (availableMoves.contains(wantedChoice)) {
                //Flip wanted row.
                List<BoardCoordinates> singleRow = singleRowToFlip(fromCoordinate,
                        wantedChoice, playerColor);
                flipNumbers = flipNumbers + singleRow.size();
                //insert flipped row into remove places vector.
                for (int i = 0; i < singleRow.size(); ++i) {
                    if (!availableMoves.contains(singleRow.get(i)))
                        removePlaces.remove(singleRow.get(i));
                }
            }
        }

        return flipNumbers;
    }


    /**
     * Check possible moves by direction.
     *
     * @param startRow     row from where to look
     * @param startColumn  column from where to look
     * @param direction    for looking
     * @param playerSymbol player symbol to know what not to look.
     * @return possible moves.
     */
    private BoardCoordinates CheckByDirection(int startRow, int startColumn,
                                              Enums.DirectionCheck direction, Enums.PlayersColors playerColor) {
        int rowStep, columnStep, counter = 0;
        //Set by direction steps of row and column.
        switch (direction) {
            case Up: {
                rowStep = -1;
                columnStep = 0;
                break;
            }
            case Down: {
                rowStep = 1;
                columnStep = 0;
                break;
            }
            case Left: {
                rowStep = 0;
                columnStep = -1;
                break;
            }
            case Right: {
                rowStep = 0;
                columnStep = 1;
                break;
            }
            case UpLeft: {
                rowStep = -1;
                columnStep = -1;
                break;
            }
            case UpRight: {
                rowStep = -1;
                columnStep = 1;
                break;
            }
            case DownRight: {
                rowStep = 1;
                columnStep = 1;
                break;
            }
            case DownLeft: {
                rowStep = 1;
                columnStep = -1;
                break;
            }
            default: {
                rowStep = 1;
                columnStep = 1;
                break;
            }
        }

        int rowMovement = startRow + rowStep;
        int columnMovement = startColumn + columnStep;
        Enums.PlayersColors currentSymbol = null;
        //Check if wanted row and col to check is valid.
        if (getBoard().isOnBoard(rowMovement, columnMovement)) {
            //Run on board while you see opponent symbol.
            do {
                currentSymbol = getBoard().getSymbolByPlace(rowMovement, columnMovement);
                rowMovement = rowMovement + rowStep;
                columnMovement = columnMovement + columnStep;
                counter++;
            } while (getBoard().isOnBoard(rowMovement, columnMovement)
                    && (currentSymbol != playerColor)
                    && (currentSymbol != Enums.PlayersColors.NoColor));
        }
        //Check if coordinate is valid and return it.
        if (getBoard().isOnBoard(rowMovement - rowStep, columnMovement - columnStep)
                && (counter > 1) && (currentSymbol == Enums.PlayersColors.NoColor)) {
            return new BoardCoordinates(rowMovement - rowStep, columnMovement - columnStep);
        } else {
            //Mark that BoardCoordinate isn't valid.
            return new BoardCoordinates(-1, -1);
        }
    }

    /**
     * Return single row that can be flipped.
     *
     * @param start        from where to flip.
     * @param end          where to flip.
     * @param playerSymbol symbol to put instead.
     * @return all board coordinates that flipped.
     */
    List<BoardCoordinates> singleRowToFlip(BoardCoordinates start,
                                           BoardCoordinates end, Enums.PlayersColors playerColor) {
        List<BoardCoordinates> flipCoordinates = new ArrayList<>();
        //Get difference of row and col to know wanted direction.
        int rowDiff = start.getRow() - end.getRow();
        int colDiff = start.getColumn() - end.getColumn();

        int stepRow = 0, stepCol = 0, numberOfFlips = 0;

        //Set step row by the difference.
        if (rowDiff < 0) {
            stepRow = 1;
        } else if (rowDiff > 0) {
            stepRow = -1;
        }
        //Set step col by the difference.
        if (colDiff < 0) {
            stepCol = 1;
        } else if (colDiff > 0) {
            stepCol = -1;
        }

        //Check how many flips we need to do.
        if (colDiff != 0) {
            numberOfFlips = Math.abs(colDiff);
        } else {
            numberOfFlips = Math.abs(rowDiff);
        }

        int rowMovement = start.getRow();
        int colMovement = start.getColumn();
        int flipCounter = 0;
        //Flip symbols
        do {
            //Step by row.
            rowMovement = rowMovement + stepRow;
            //Step by column.
            colMovement = colMovement + stepCol;
            //Put symbols in wanted place on board.
            //getBoard().putSymbolOnBoard(rowMovement, colMovement, playerSymbol);
            //Remember flipped coordinates.
            flipCoordinates.add(new BoardCoordinates(rowMovement, colMovement));
            flipCounter++;
        } while (flipCounter < numberOfFlips);
        //Return all flipped coordinates.
        return flipCoordinates;

    }


}
