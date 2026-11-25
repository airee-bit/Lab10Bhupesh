import java.util.Scanner;

public class TicTacToe {
    //Make game board here so it can be accessed by all methods
    private static String[][] board = new String[3][3];


    //main method - game simulated here
    public static void main(String[] args) {
        //Variables you might want to make
        Scanner scan = new Scanner(System.in);
        String player1 = "X";
        String player2 = "O";
        int p1row;
        int p1col;
        int p2row;
        int p2col;
        boolean player1Valid = false;
        boolean player2Valid = false;
        boolean gameOver;
        boolean playAgain;

        System.out.println("Welcome to Tic-Tac-Toe!");
        //This do-while loops allows for multiple games to be played
        do {
            clearBoard();
            gameOver = false;
//This do-while loop keeps running while the game is still going
//Stops once a winner or tie occurs
            do {

                //Player 1 - loops until valid move made
                System.out.println("\nPlayer 1:");
                do {
                    p1row = InputHelper.getRangedInt(scan, "Player 1 enter your move row [1 - 3] ", 1, 3);
                    p1col = InputHelper.getRangedInt(scan, "Player 1 enter your move col [1 - 3] ", 1, 3);
                    player1Valid = isValidMove(p1row - 1, p1col - 1);
                    if (!player1Valid){
                        System.out.println("That spot is taken. Try again.");
                    }
                } while (!player1Valid);

                board[p1row - 1][p1col - 1] = player1;
                displayBoard();

                if (isWin(player1)){
                    System.out.println("Player 1 wins!");
                    gameOver = true;
                } else if (isTie(player1, player2)){
                    System.out.println("It's a tie!");
                    gameOver = true;
                }

                //Player 2 - loops until valid move made
                if (!gameOver) {
                    System.out.println("\nPlayer 2:");
                    do {
                        p2row = InputHelper.getRangedInt(scan, "Player 2 enter your move row [1 - 3] ", 1, 3);
                        p2col = InputHelper.getRangedInt(scan, "Player 2 enter your move col [1 - 3] ", 1, 3);
                        player2Valid = isValidMove(p2row - 1, p2col - 1);
                        if (!player2Valid) {
                            System.out.println("That spot is taken. Try again.");
                        }
                    } while (!player2Valid);

                    board[p2row - 1][p2col - 1] = player2;
                    displayBoard();

                    if (isWin(player2)) {
                        System.out.println("Player 2 wins!");
                        gameOver = true;
                    } else if (isTie(player1, player2)) {
                        System.out.println("It's a tie!");
                        gameOver = true;
                    }
                }

            } while (!gameOver);

            playAgain = InputHelper.getYNConfirm(scan, "Would you like to play again? [Y/N]");
        } while (playAgain);
    }

    private static void clearBoard(){
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
               board[r][c] = "-";
            }
        }
    }

    private static void displayBoard(){
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                System.out.print(board[r][c]+ " ");
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int col){
        return board[row][col].equals("-");
    }

    private static boolean isWin(String player){
        return isColWin(player) || isDiagonalWin(player) || isRowWin(player);
    }

    private static boolean isColWin(String player){
        for (int c = 0; c < 3 ; c++) {
            if (board[0][c].equals(player) && board[1][c].equals(player) && board[2][c].equals(player)){
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player){
        for (int r = 0; r < 3 ; r++) {
            if (board[r][0].equals(player) && board[r][1].equals(player) && board[r][2].equals(player)){
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player){
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)){
            return true;
        }
            if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)){
            return true;
        }
        return false;
    }

    private static boolean isTie(String player1, String player2){
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (isValidMove(r, c)){
                    return false;
                }
            }
        }
        return !isWin(player1) && !isWin(player2);
    }
}