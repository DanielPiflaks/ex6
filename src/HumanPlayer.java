import java.util.*;

public class HumanPlayer extends Player {

    HumanPlayer(char symbol, Board board, GameLogic gameLogic, GuiDisplay display) {
        super(symbol, board, gameLogic, display);
    }

    @Override
    Map<BoardCoordinates, List<BoardCoordinates>> playOneTurn() {
        //Print who it's turn to play.
        this.display.printPlayerTurn(getSymbol());
        //Get map of all possible moves.
        Map<BoardCoordinates, List<BoardCoordinates>> possibleMoves = this.gameLogic.getPossibleGameMoves(playerMoves,
                symbol);
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
        //Sort vector.
        Collections.sort(allMoves);

        //Check if there are no possible moves and notify player about it.
        if (allMoves.isEmpty()) {
            this.display.printMessage("No possible moves. Play passes back to the other player" +
                    ". Press enter to continue.");
            //cin.ignore();
            //Return empty map.
            return playerMove;
        }

        //Print all possible moves.
        this.display.printPossibleMoves(allMoves);
        //printPossibleMoves(allMoves);
        //Get player choice.
        BoardCoordinates playerChoice = getPlayerChoice(allMoves);

        //Get flipped symbols vector.
        flippedSymbols = this.gameLogic.flipSymbols(possibleMoves,
                playerChoice, getSymbol());
        //Return them.
        playerMove.put(playerChoice, flippedSymbols);
        return playerMove;
    }


    public BoardCoordinates getPlayerChoice(List<BoardCoordinates> possibleMoves) {
        BoardCoordinates playerChice = display.getUserPlayChoice(possibleMoves, this.board);
        return playerChice;
    }

}

