import java.util.List;

public class JavaGuiDisplay implements GuiDisplay {

    private GameGuiController gameGuiController;

    public JavaGuiDisplay(GameGuiController gameGuiController){
        this.gameGuiController = gameGuiController;
    }

    @Override
    public void printBoard(Board board) {

    }

    @Override
    public void printOtherPlayerMove(char playerSymbol, BoardCoordinates move) {

    }

    @Override
    public void printMessage(String message) {

    }

    @Override
    public void printPlayerTurn(char playerSymbol) {

    }

    @Override
    public void printPossibleMoves(List<BoardCoordinates> possibleMoves) {

    }

    @Override
    public void printScore(char playerSymbol, int playerScore) {

    }

    @Override
    public void printWinner(char playerSymbol) {

    }

    @Override
    public void printMainMenu() {

    }

    @Override
    public void printClientMenu() {

    }
}
