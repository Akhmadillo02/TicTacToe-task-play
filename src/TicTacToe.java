import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
    private static boolean gameFinished = false;

    public static void main(String[] args) {
        initializeBoard();
        printBoard();

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (!gameFinished) {
            if (currentPlayer == 'X') {
                System.out.println("It's your turn, enter a row and column (e.g. 1 2):");
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                if (isValidMove(row, col)) {
                    placeMove(row, col);
                    printBoard();
                    if (checkWin()) {
                        System.out.println("Congratulations! You won!");
                        gameFinished = true;
                    } else if (isBoardFull()) {
                        System.out.println("It's a tie!");
                        gameFinished = true;
                    }
                    currentPlayer = 'O';
                } else {
                    System.out.println("Invalid move, try again.");
                }
            } else {
                System.out.println("It's the computer's turn:");
                int row = random.nextInt(3);
                int col = random.nextInt(3);
                if (isValidMove(row, col)) {
                    placeMove(row, col);
                    printBoard();
                    if (checkWin()) {
                        System.out.println("The computer won!");
                        gameFinished = true;
                    } else if (isBoardFull()) {
                        System.out.println("It's a tie!");
                        gameFinished = true;
                    }
                    currentPlayer = 'X';
                }
            }
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }
        if (board[row][col] != '-') {
            return false;
        }
        return true;
    }

    private static void placeMove(int row, int col) {
        board[row][col] = currentPlayer;
    }

    private static boolean checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != '-' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}