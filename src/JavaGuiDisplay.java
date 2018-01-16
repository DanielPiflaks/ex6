import java.util.List;

public class JavaGuiDisplay implements GuiDisplay, PressListener {

    private GameBoardController gameBoardController;

    public JavaGuiDisplay(GameBoardController gameBoardController) {
        this.gameBoardController = gameBoardController;
    }

    @Override
    public void printBoard(Board board) {
        this.gameBoardController.drawBoard(board);
    }

    @Override
    public void printOtherPlayerMove(char playerSymbol, BoardCoordinates move) {

    }

    @Override
    public void printMessage(String message) {

    }

    @Override
    public void printPlayerTurn(char playerSymbol) {
        /*String currentPlayerTurn;
        if (playerSymbol == 'x') {
            currentPlayerTurn = "Black";
        } else {
            currentPlayerTurn = "White";
        }
        gameBoardController.changeCurrentPlayer(currentPlayerTurn.);*/
    }

    @Override
    public void printPossibleMoves(List<BoardCoordinates> possibleMoves) {

    }

    @Override
    public void printScore(char playerSymbol, int playerScore) {
        /*if (playerSymbol == 'x') {
            gameBoardController.changeBlackPlayerScore(playerScore);
        } else {
            gameBoardController.changeWhitePlayerScore(playerScore);
        }*/

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

    @Override
    public BoardCoordinates getUserPlayChoice(List<BoardCoordinates> possibleMoves, Board board) {
        return null;
    }

    @Override
    public void pressListen(int row, int col) {

    }
}
