package animation;

import hsa2.GraphicsConsole;

import java.util.Random;
//https://github.com/salamander2/HSA2


class GameCompletedException extends Exception {
    public GameCompletedException(String errorMessage) {
        super(errorMessage);
    }
}


public class TicTacToeMain {

    static GraphicsConsole window;
    static String[][] gameField = {
            {"___", "|", "___", "|", "___"},
            {"___", "|", "___", "|", "___"},
            {"___", "|", "___", "|", "___"}
    };

    static String[][] cleanGameField = {
            {"___", "|", "___", "|", "___"},
            {"___", "|", "___", "|", "___"},
            {"___", "|", "___", "|", "___"}
    };

    static boolean[] positionFilledUp = new boolean[]{false, false, false, false, false, false, false, false, false};

    static int computerMove;
    static int move;
    static int roundsCounter = 0;
    static String userInput;

    static int nextMove = 1;
    static int humanWins = 2;
    static int computerWins = 3;
    static int itsATie = 4;

    public static void main(String[] args) throws InterruptedException, GameCompletedException {
//        window = new Console(80, 80);
        window = new GraphicsConsole(300, 300);
        window.setLocation(100, 100);
        printBoard(gameField);
        for (roundsCounter = 0; roundsCounter <= 9; ) {
            playGame();
        }

    }

    public static void printBoard(String[][] gameBoard) {

        window.println("Tic Tac Toe");
        printEquals(11);

        for (String[] row : gameBoard) {
            for (String i : row) {
                window.print(i);
            }
            window.println();
        }
        window.println();

    }

    public static void addDelayInSeconds(int maxDelay) throws InterruptedException {
        for (int i = maxDelay; i > 0; i--) {
            window.print("..");
            Thread.sleep(1000);
            window.print(i);
        }
        window.println();
    }

    public static void printEquals(int count) {
        for (int i = count; i > 0; i--) {
            window.print("=");
        }
        window.println();
    }

    public static void playGame() throws InterruptedException, NumberFormatException {

        String humanMarker = " X ";
        String computerMarker = " O ";

        //Human moves
        boolean repeatUntilValidKey = true;
        while (repeatUntilValidKey) {

            window.println();
            printEquals(11);
            window.print("Your move: ");
            userInput = window.readLine();
            window.println();

            try {
                move = Integer.parseInt(userInput);
                if (move < 1 || move > 9) {
                    window.println(move + " is outside range. Enter between 1 and 9");
                    continue;
                }
                if (positionFilledUp[move - 1]) {
                    window.println(move + " already occupied. Retry");
                    continue;
                }
            } catch (NumberFormatException e) {
                window.println(move + " is an invalid number. Retry");
                continue;
            }

            window.println("");
            window.print("You moved: " + move);
            window.println("");
            printEquals(12);

            markPosition(humanMarker, move);
            repeatUntilValidKey = false;
        }

        //Verdict
        try {
            int matchResult = winCheck();
            if (matchResult == humanWins || matchResult == computerWins || matchResult == itsATie) {
                if (!rematch()) {
                    throw new GameCompletedException("Game Over");
                }
            }
        } catch (GameCompletedException e) {
            window.println(e.getMessage());
            System.exit(0);
        }

        roundsCounter = roundsCounter++;

        //Computer moves
        window.print("computer makes the next move in ");
        addDelayInSeconds(3);
        printEquals(20);

        computerMove = computerMoves();

        boolean repeatUntilValidGenerated = true;
        while (repeatUntilValidGenerated) {
            if (positionFilledUp[computerMove - 1]) {//repeat until computer picks an unoccupied position
                computerMove = computerMoves();
                continue;
            }
            markPosition(computerMarker, computerMove);
            repeatUntilValidGenerated = false;
        }


        window.println();
        window.println("Computer moved: " + computerMove);
        printEquals(17);

        //Verdict
        try {
            int matchResult = winCheck();
            if (matchResult == humanWins || matchResult == computerWins || matchResult == itsATie) {
                if (!rematch()) {
                    throw new GameCompletedException("Game Over");
                }
            }
        } catch (GameCompletedException e) {
            window.println(e.getMessage());
            System.exit(0);
        }

        roundsCounter = roundsCounter++;

    }

    public static void markPosition(String character, int position) {

        switch (position) {
            case 1:
                gameField[0][0] = character;
                break;
            case 2:
                gameField[0][2] = character;
                break;
            case 3:
                gameField[0][4] = character;
                break;
            case 4:
                gameField[1][0] = character;
                break;
            case 5:
                gameField[1][2] = character;
                break;
            case 6:
                gameField[1][4] = character;
                break;
            case 7:
                gameField[2][0] = character;
                break;
            case 8:
                gameField[2][2] = character;
                break;
            case 9:
                gameField[2][4] = character;
                break;
            default:
                break;
        }
        printBoard(gameField);
        positionFilledUp[position - 1] = true;
    }

    public static int computerMoves() {

        int[] items = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int randomIndex = new Random().nextInt(items.length);
        return items[randomIndex];
    }

    public static int winCheck() throws InterruptedException, GameCompletedException {

        if (
                (gameField[0][0] == " X " && gameField[0][2] == " X " && gameField[0][4] == " X ")
                        ||
                        (gameField[1][0] == " X " && gameField[1][2] == " X " && gameField[1][4] == " X ")
                        ||
                        (gameField[2][0] == " X " && gameField[2][2] == " X " && gameField[2][4] == " X ")
                        ||
                        (gameField[0][0] == " X " && gameField[1][0] == " X " && gameField[2][0] == " X ")
                        ||
                        (gameField[0][2] == " X " && gameField[1][2] == " X " && gameField[2][2] == " X ")
                        ||
                        (gameField[0][4] == " X " && gameField[1][4] == " X " && gameField[2][4] == " X ")
                        ||
                        (gameField[0][0] == " X " && gameField[1][2] == " X " && gameField[2][4] == " X ")
                        ||
                        (gameField[0][4] == " X " && gameField[1][2] == " X " && gameField[2][0] == " X ")
        ) {
            Thread.sleep(2000);
            window.print("Congratulations. You WIN!!");
            return humanWins;
        } else if (
                (gameField[0][0] == " O " && gameField[0][2] == " O " && gameField[0][4] == " O ")
                        ||
                        (gameField[1][0] == " O " && gameField[1][2] == " O " && gameField[1][4] == " O ")
                        ||
                        (gameField[2][0] == " O " && gameField[2][2] == " O " && gameField[2][4] == " O ")
                        ||
                        (gameField[0][0] == " O " && gameField[1][0] == " O " && gameField[2][0] == " O ")
                        ||
                        (gameField[0][2] == " O " && gameField[1][2] == " O " && gameField[2][2] == " O ")
                        ||
                        (gameField[0][4] == " O " && gameField[1][4] == " O " && gameField[2][4] == " O ")
                        ||
                        (gameField[0][0] == " O " && gameField[1][2] == " O " && gameField[2][4] == " O ")
                        ||
                        (gameField[0][4] == " O " && gameField[1][2] == " O " && gameField[2][0] == " O ")
        ) {
            Thread.sleep(2000);
            window.print("Yay! The computer is the Winner. Better luck next time :)");
            return computerWins;
        } else if (roundsCounter >= 9) {
            Thread.sleep(1000);
            window.println();
            window.print("It's a Tie. Tough luck, but well played. Game over.");
            return itsATie;
        }
        window.println();
        window.print("Now ");
        Thread.sleep(1000);

        return nextMove;

    }

    public static boolean rematch() throws InterruptedException {

        window.println();
        window.print("Do you like to continue the game? (y/n) : ");

        userInput = window.readLine();
        if (userInput.equalsIgnoreCase("n")) {
            window.println("");
            Thread.sleep(1000);
            window.print("It was a pleasure playing with you. Looking forward to meet you again. Thank you!");
            Thread.sleep(1000);
            return false;
        } else {
            for (roundsCounter = 0; roundsCounter <= 9; ) {
                window.clear();
                gameField = cleanGameField;
                positionFilledUp = new boolean[]{false, false, false, false, false, false, false, false, false};
                printBoard(gameField);
                playGame();
            }
            return true;
        }
    }

}




