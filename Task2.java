import java.util.Scanner;

public class Task2 {

    static char[] board = { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
    static char currentPlayer = 'X';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playing = true;

        while (playing) {
            resetBoard();
            boolean gameOn = true;

            System.out.println("Welcome to Tic-Tac-Toe!");
            while (gameOn) {
                printBoard();
                playerMove(scanner);
                gameOn = !checkWinner() && !checkDraw();
                if (gameOn) {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch players
                }
            }

            printBoard();
            if (checkWinner()) {
                System.out.println("Player " + currentPlayer + " wins!");
            } else {
                System.out.println("It's a draw!");
            }

            System.out.println("Do you want to play again? (y/n): ");
            char response = scanner.next().charAt(0);
            playing = (response == 'y');
        }

        scanner.close();
    }

    public static void resetBoard() {
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
        currentPlayer = 'X';
    }

    public static void printBoard() {
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("***********");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("***********");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8]);
    }

    public static void playerMove(Scanner scanner) {
        boolean validMove = false;
        int move;

        while (!validMove) {
            System.out.print("Player " + currentPlayer + ", enter your move (1-9): ");
            move = scanner.nextInt() - 1;
            if (move >= 0 && move < 9 && board[move] == ' ') {
                board[move] = currentPlayer;
                validMove = true;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    public static boolean checkWinner() {
        // Rows, columns, and diagonals
        int[][] winConditions = {
                { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, // Rows
                { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, // Columns
                { 0, 4, 8 }, { 2, 4, 6 } // Diagonals
        };

        for (int[] condition : winConditions) {
            if (board[condition[0]] == currentPlayer &&
                    board[condition[1]] == currentPlayer &&
                    board[condition[2]] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkDraw() {
        for (char c : board) {
            if (c == ' ') {
                return false;
            }
        }
        return true;
    }
}
