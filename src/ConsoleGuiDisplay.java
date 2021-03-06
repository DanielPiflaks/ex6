import java.util.List;
import java.util.Scanner;

public class ConsoleGuiDisplay implements GuiDisplay {

    @Override
    public void printBoard(Board board) {
        System.out.println("Current board:");
        //Print columns.
        for (int j = 0; j <= board.getNumCols(); j++) {
            if (j == 0) {
                System.out.print(" | ");
            } else {
                System.out.print(j + " | ");
            }
        }
        System.out.println();

        //Print rows.
        for (int i = 1; i <= board.getNumRows(); i++) {
            for (int k = 0; k < (board.getNumCols() * 4) + 2; ++k) {
                System.out.print("-");
            }
            System.out.println();
            System.out.print(i + "|");
            for (int j = 1; j <= board.getNumCols(); j++) {
                System.out.print(" " + board.getSymbolByPlace(i, j) + " |");
            }
            System.out.println();
        }
        for (int k = 0; k < (board.getNumCols() * 4) + 2; ++k) {
            System.out.print("-");
        }
        System.out.println();
    }

    @Override
    public void printOtherPlayerMove(char playerSymbol, BoardCoordinates move) {
        System.out.println();
        System.out.println(playerSymbol + " Played" + move);
        System.out.println();
    }

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void printPlayerTurn(char playerSymbol) {
        System.out.println(playerSymbol + ": It's your move");
    }

    @Override
    public void printPossibleMoves(List<BoardCoordinates> possibleMoves) {
        System.out.print("Your possible moves: ");
        //Print each possible move.
        for (int i = 0; i < possibleMoves.size(); i++) {
            System.out.print(possibleMoves.get(i));
        }
        System.out.println();
        System.out.println();
    }

    @Override
    public void printScore(char playerSymbol, int playerScore) {
        System.out.println("Player " + playerSymbol + " score is: " + playerScore);
    }

    @Override
    public void printWinner(char playerSymbol) {
        System.out.println("Player " + "Player " + " wins!");
    }

    @Override
    public void printMainMenu() {
        //Print menu for user.
        System.out.println("choose your opponent:");
        System.out.println("1. a human local player");
        System.out.println("2. an AI player");
        System.out.println("3. a remote player");
    }

    @Override
    public void printClientMenu() {
        System.out.println("please choose one of the following options:");
        System.out.println("1. start new game");
        System.out.println("2. get list of optional games to play");
        System.out.println("3. join exiting game");
    }

    @Override
    public BoardCoordinates getUserPlayChoice(List<BoardCoordinates> possibleMoves, Board board) {
        int row, column;
        boolean validChoice = false;
        Scanner in = new Scanner(System.in);

        //While player choice isn't valid, try to get it.
        do {
            printMessage("Please enter your move row col:");
            row = in.nextInt();
            column = in.nextInt();
            //Ignore \n.
            //cin.ignore();

            if (!board.isOnBoard(row, column)) {
                printMessage("Choice is out of board! Please choose valid row and column");
                //cin.clear();
                //cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
                continue;
            }

            //Check if choice is possible move.
            for (int i = 0; i < possibleMoves.size(); i++) {
                if ((row == possibleMoves.get(i).getRow()) && (column == possibleMoves.get(i).getColumn())) {
                    validChoice = true;
                    break;
                }
            }

            if (!validChoice) {
                printMessage("No such move exist in your option.Please Select valid one.");
                printPossibleMoves(possibleMoves);
            }

        } while (((row > board.getNumRows()) || (column > board.getNumCols())) || (!validChoice));
        //Return player choice.
        return new BoardCoordinates(row, column);

    }
}
