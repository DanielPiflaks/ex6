public class GameParameters {

    private Board gameBoard;
    private Enums.PlayersColors startFirst;
    private GameLogic gameLogic;
    private Player player1;
    private Player player2;
    private GuiDisplay display;


    /**
     * Game parameter constructor. sets default parameters to start first to be player1, and
     * the game to be standart game logic.
     *
     * @param player1Type     what type player 1 is.
     * @param player1Symbol   what symbol will represent player 1.
     * @param player2Type     what type player 2 is.
     * @param player2Symbol   what symbol will represent player 2.
     * @param startFirst      who start first. by default its player1.
     * @param gameLogicOption what game logic to play. by default its standart game logic.
     */
    GameParameters(Enums.PlayerOptions player1Type, char player1Symbol, Enums.PlayerOptions player2Type, char player2Symbol,
                   int boardSize, GuiDisplay displayOption,
                   Enums.PlayersColors startFirst, Enums.GameLogicOptions gameLogicOption) {
        //Set who start first.
        this.startFirst = startFirst;
        //Create new game board.
        gameBoard = new Board(boardSize, boardSize);
        display = displayOption;
        //Create game logic by input.
        switch (gameLogicOption) {
            case StandartGame: {
                gameLogic = new StandartGameLogic(gameBoard);
                break;
            }
        }

        //Create player 1 by it's symbol and type.
        switch (player1Type) {
            case HumanPlayerOp: {
                player1 = new HumanPlayer(player1Symbol, gameBoard, gameLogic, display);
                break;
            }
            case AIPlayerOp: {
                break;
            }
            case RemotePlayerOp: {
                break;
            }
            default:
                break;
        }

        //Create player 2 by it's symbol and type.
        switch (player2Type) {
            case HumanPlayerOp: {
                player2 = new HumanPlayer(player2Symbol, gameBoard, gameLogic, display);
                break;
            }
            case AIPlayerOp: {
                break;
            }
            case RemotePlayerOp: {
                break;
            }
            default:
                break;
        }
    }


    /**
     * Getter.
     *
     * @return Returns game board.
     */
    Board getGameBoard() {
        return this.gameBoard;
    }

    /**
     * Getter.
     *
     * @return Returns player1.
     */
    Player getPlayer1() {
        return this.player1;
    }

    /**
     * Getter.
     *
     * @return Returns player2.
     */
    Player getPlayer2() {
        return this.player2;
    }

    /**
     * Returns who start first.
     *
     * @return who start first.
     */
    Enums.PlayersColors getStartFirst() {
        return this.startFirst;
    }

    GuiDisplay getGuiDisplay() {
        return this.display;
    }


}
