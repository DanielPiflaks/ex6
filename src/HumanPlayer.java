import java.util.*;

public class HumanPlayer extends Player {

    HumanPlayer(Enums.PlayersColors color, Board board, GameLogic gameLogic, GuiDisplay display) {
        super(color, board, gameLogic, display);
    }

    @Override
    Map<BoardCoordinates, List<BoardCoordinates>> playOneTurn(BoardCoordinates boardCoordinates) {
        //Print who it's turn to play.
        //this.display.printPlayerTurn(getSymbol());
        //Get map of all possible moves.
        Map<BoardCoordinates, List<BoardCoordinates>> possibleMoves = this.gameLogic.getPossibleGameMoves(playerMoves,
                color);
        List<BoardCoordinates> allMoves = new ArrayList<>();
        List<BoardCoordinates> flippedSymbols = new ArrayList<>();
        Map<BoardCoordinates, List<BoardCoordinates>> playerMove = new HashMap<>();

        //For board coordinate, get its possible move and insert in to all moves vector.

        for (Map.Entry<BoardCoordinates, List<BoardCoordinates>> moves : possibleMoves.entrySet()) {
            for (int i = 0; i < moves.getValue().size(); i++) {
                if (!allMoves.contains(moves.getValue().get(i))) {
                    allMoves.add(moves.getValue().get(i));
                }
            }
        }

        //Check if there are no possible moves and notify player about it.
        if (allMoves.isEmpty()) {
            //this.display.printMessage("No possible moves. Play passes back to the other player" +
            //        ". Press enter to continue.");
            //cin.ignore();
            //Return empty map.
            return playerMove;
        }

        //Print all possible moves.
        //this.display.printPossibleMoves(allMoves);
        //printPossibleMoves(allMoves);
        //Get player choice.
        //BoardCoordinates playerChoice = getPlayerChoice(allMoves);

        //Get flipped symbols vector.
        flippedSymbols = this.gameLogic.flipSymbols(possibleMoves,
                boardCoordinates, getSymbol());
        //Return them.
        playerMove.put(boardCoordinates, flippedSymbols);
        return playerMove;
    }

    @Override
    Boolean checkIfValidMove(BoardCoordinates coordinates) {
        //Print who it's turn to play.
        //this.display.printPlayerTurn(getSymbol());
        //Get map of all possible moves.
        Map<BoardCoordinates, List<BoardCoordinates>> possibleMoves = this.gameLogic.getPossibleGameMoves(playerMoves,
                color);
        List<BoardCoordinates> allMoves = new ArrayList<>();
        List<BoardCoordinates> flippedSymbols = new ArrayList<>();
        Map<BoardCoordinates, List<BoardCoordinates>> playerMove = new HashMap<>();

        //For board coordinate, get its possible move and insert in to all moves vector.

        for (Map.Entry<BoardCoordinates, List<BoardCoordinates>> moves : possibleMoves.entrySet()) {
            for (int i = 0; i < moves.getValue().size(); i++) {
                if (!allMoves.contains(moves.getValue().get(i))) {
                    allMoves.add(moves.getValue().get(i));
                }
            }
        }

        return allMoves.contains(coordinates);
    }

    @Override
    Boolean isThereMoves() {
        //Print who it's turn to play.
        //this.display.printPlayerTurn(getSymbol());
        //Get map of all possible moves.
        Map<BoardCoordinates, List<BoardCoordinates>> possibleMoves = this.gameLogic.getPossibleGameMoves(playerMoves,
                color);
        List<BoardCoordinates> allMoves = new ArrayList<>();
        List<BoardCoordinates> flippedSymbols = new ArrayList<>();
        Map<BoardCoordinates, List<BoardCoordinates>> playerMove = new HashMap<>();

        //For board coordinate, get its possible move and insert in to all moves vector.

        for (Map.Entry<BoardCoordinates, List<BoardCoordinates>> moves : possibleMoves.entrySet()) {
            for (int i = 0; i < moves.getValue().size(); i++) {
                if (!allMoves.contains(moves.getValue().get(i))) {
                    allMoves.add(moves.getValue().get(i));
                }
            }
        }

        return (!allMoves.isEmpty());
    }


    public BoardCoordinates getPlayerChoice(List<BoardCoordinates> possibleMoves) {
        BoardCoordinates playerChice = display.getUserPlayChoice(possibleMoves, this.board);
        return playerChice;
    }

}

