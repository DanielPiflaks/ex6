import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        //Set size of game board.
        final int numberRows = 4;
        final int numberColumns = 4;

        GuiDisplay display = new ConsoleGuiDisplay();

        /*
        //Create bool parameter for while loop to get player wanted player.
        boolean waitingForInput = true;
        int input;
        Scanner in = new Scanner(System.in);

        while (waitingForInput) {
            display.printMainMenu();
            input = in.nextInt();
            if (input == 1) {
                //If input is 1, then the game is against local human player.
                player2Type = Enums.PlayerOptions.HumanPlayerOp;
                waitingForInput = false;
            } else if (input == 2) {
                //If input is 2, then the game is against AI player.
                player2Type = Enums.PlayerOptions.AIPlayerOp;
                waitingForInput = false;
            } else if (input == 3) {
                //If input is 3, then the game is against remote player.
                player2Type = Enums.PlayerOptions.RemotePlayerOp;
                waitingForInput = false;
            }
            //Clear not valid input.
            //cin.clear();
            //cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
        }*/


        //Create game parameters.
        GameParameters gameParameters = new GameParameters(Enums.PlayerOptions.HumanPlayerOp, 'x', Enums.PlayerOptions.HumanPlayerOp,
                'o', numberRows, numberColumns, display,
                Enums.StartFirstOptions.Player1First, Enums.GameLogicOptions.StandartGame);
        //Create game with those parameters.
        Game game = new Game(gameParameters);
        //Run single game.
        game.RunSingleGame();
    }

}
