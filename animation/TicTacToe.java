package animation;
import hsa2.GraphicsConsole;
//https://github.com/salamander2/HSA2

public class TicTacToeMain {

    static GraphicsConsole window;
    static char gameBoard;
    static int row;
    static int col;
    static String[][] board = new String [3][3];
    static String[][] gameField = {
            {"___","|","___","|","___"},
            {"___","|","___","|","___"},
            {"___","|","___","|","___"}
    };
    static boolean computerMoveValid = false;
    static int computerMove;
    static int position;
    static int move;
    static int roundsCounter = 0;
    static String userInput;
    static boolean playerPositionValid = false;
    static String character;
    static boolean win = false;


    public static void main(String[] args) throws InterruptedException {
//        window = new Console(80, 80);
        window = new GraphicsConsole(300, 300);
        window.setLocation(100, 100);
        printBoard(gameField);
        for (roundsCounter = 0; roundsCounter <= 9;) {
            playGame();
        }

    }

    public static void printBoard(String [][] gameBoard){

        window.println("Tic Tac Toe");
        printEquals(11);

        for(String[] row : gameBoard){
            for( String i : row){
                window.print(i);
            }
            window.println();
        }
        window.println();

    }

    public static void addDelayInSeconds(int maxDelay) throws InterruptedException{
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

    public static void playGame() throws InterruptedException {
        while (playerPositionValid == false) {
            window.println();
            printEquals(11);
            window.print("Your move: ");
            userInput = window.readLine();
            window.println();
            window.clear();
            try {
                move = Integer.parseInt(userInput);
                playerPositionValid = true;

            }
            catch(NumberFormatException e) {
                window.clear();
                window.println("Invalid entry. Please retry");
            }
        }

        window.println("");
        window.print("You moved: " + move);
        window.println("");
        printEquals(12);

        if (playerPositionValid == true) {
            positionMark(move, 1, gameField);

        }

        window.print("Computer to move in ");
        addDelayInSeconds(3);
        printEquals(20);

        computerMove();
        if (computerMoveValid == true) {

            window.println();
            window.println("Computer moved: " + computerMove);
            printEquals(17);
        }
        positionMark(computerMove, 2, gameField);
        computerMoveValid = false;
        playerPositionValid = false;
        if (winCheck() == false) {
            System.exit(0);
        };
        roundsCounter = roundsCounter ++;

    }

    public static void positionMark( int position, int player, String[][] gameBoard) {


        if (player == 1) {   // 1 = player(X); 2 = computer (O)
            character = " X ";
        } else {
            character = " O ";
        }


        if (position >= 1 && position <= 9) {

            switch (position) {

                case 1:
                    if (player == 1 && gameField[0][0] != "___") {
                        window.println("Space is already occupied. Try again.");
                        playerPositionValid = false;
                    } else if (player == 2 && gameField[0][0] != "___" ) {
                        computerMoveValid = false;
                        computerMove();
                    }
                    else gameField[0][0] = character;
                    printBoard(gameField);
                    break;
                case 2:
                    if (player == 1 && gameField[0][2] != "___") {
                        window.println("Space is already occupied. Try again.");
                        playerPositionValid = false;
                    } else if (player == 2 && gameField[0][2] != "___" ) {
                        computerMoveValid = false;
                        computerMove();
                    }else
                        gameField[0][2] = character;
                    printBoard(gameField);
                    break;
                case 3:
                    if (player == 1 && gameField[0][4] != "___") {
                        window.println("Space is already occupied. Try again.");
                    } else if (player == 2 && gameField[0][4] != "___" ) {
                        computerMoveValid = false;
                        computerMove();
                    }else
                        gameField[0][4] = character;
                    printBoard(gameField);
                    break;
                case 4:
                    if (player == 1 && gameField[1][0] != "___") {
                        window.println("Space is already occupied. Try again.");
                    } else if (player == 2 && gameField[1][0]!= "___" ) {
                        computerMoveValid = false;
                        computerMove();
                    }else
                        gameField[1][0] = character;
                    printBoard(gameField);
                    break;
                case 5:
                    if (player == 1 && gameField[1][2]!= "___") {
                        window.println("Space is already occupied. Try again.");
                    } else if (player == 2 && gameField[1][2] != "___" ) {
                        computerMoveValid = false;
                        computerMove();
                    }else
                        gameField[1][2] = character;
                    printBoard(gameField);
                    break;
                case 6:
                    if (player == 1 && gameField[1][4] != "___") {
                        window.println("Space is already occupied. Try again.");
                    } else if (player == 2 && gameField[1][4] != "___" ) {
                        computerMoveValid = false;
                        computerMove();
                    }else
                        gameField[1][4] = character;
                    printBoard(gameField);
                    break;
                case 7:
                    if (player == 1 && gameField[2][0] != "___" ) {
                        window.println("Space is already occupied. Try again.");
                    } else if (player == 2 && gameField[2][0] != "___" ) {
                        computerMove();
                        roundsCounter--;
                    }else
                        gameField[2][0] = character;
                    printBoard(gameField);
                    break;
                case 8:
                    if (player == 1 && gameField[2][2] != "___" ) {
                        window.println("Space is already occupied. Try again.");
                    } else if (player == 2 && gameField[2][2] != "___"  ) {
                        computerMoveValid = false;
                        computerMove();
                    }else
                        gameField[2][2] = character;
                    printBoard(gameField);
                    break;
                case 9:
                    if (player == 1 && gameField[2][4] != "___") {
                        window.println("Space is already occupied. Try again.");
                    } else if (player == 2 && gameField[2][4] != "___" ) {
                        computerMoveValid = false;
                        computerMove();
                    }else
                        gameField[2][4] = character;
                    printBoard(gameField);
                    break;

                default:
                    break;
            }
        } else {
            window.println("Invalid input. Try again.");
        }
    }

    public static int computerMove() {

        while (computerMoveValid == false) {
            computerMove = (int) Math.round(Math.random() * 9);

            if (computerMove == move || computerMove == 0 /* gameField[row][col] == character*/) {
                computerMoveValid = false;

            }else
                computerMoveValid = true;
        }
        System.out.println(move + "  " + computerMove);
        return computerMove;
    }

    public static boolean winCheck() throws InterruptedException {
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
            (gameField[0][4] == " X " && gameField[1][4] == " X " &&  gameField[2][4] == " X ")
            ||
            (gameField[0][0] == " X " && gameField[1][2] == " X " && gameField[2][4] == " X ")
            ||
            (gameField[0][4] == " X " && gameField[1][2] == " X " && gameField[2][0] == " X ")
        )
        {
            window.print("Congratulations. You win!!");
            return redo();
        }
        else if (
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
        )
        {
            window.print("Computer wins!. Better luck next time :)");
            return redo();
        }
        else if (roundsCounter >= 9) {
            window.println();
            window.print("It's a Tie. Tough luck, well played. Game over.");
            return redo();
        }else {
            window.println();
            window.print("Next");
            Thread.sleep(1000);
        }
        return true;

    }

    public static boolean redo() throws InterruptedException {

        window.println();
        window.print("Do you like to play again? (y/n) : ");

        userInput = window.readLine();
        //TODO - Add try catch for invalid inputs
        if (userInput.equalsIgnoreCase("n")) {
            window.print("It was a pleasure playing with you. Looking forward to meet you again. Thank you!");
            Thread.sleep(1000);
            return false;
        }
        return true;
    }

}




