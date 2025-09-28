import java.util.Scanner;

public class connectFour {
    static Scanner inputReader = new Scanner(System.in);

    public static void main(String[] args) {

        int rows = 6;
        int columns = 7;
        char[][] board = new char[rows][columns];
        createBoard(board, rows, columns);
        boolean gameOver = false;
        boolean playerTurn = true;
        while (!gameOver) {
            displayBoard(board, rows, columns);
            if (playerTurn) {
                playerMoveTurn(board, columns, rows);
            } else {
                computerMoveTurn(board, columns, rows);
            }
            gameOver = checkWinConditions(board, rows, columns) || fullBoard(board, rows, columns);
            playerTurn = !playerTurn;
        }
        displayBoard(board, rows, columns);
        if (!playerTurn) {
            lineSeparator();
            System.out.println("Well done! You've won the game!");
            lineSeparator();
        } else {
            lineSeparator();
            System.out.println("hahaha, You Lost, Computer Wins!");
            lineSeparator();
        }

        System.out.println("Game Finished!");

    }

    private static void createBoard(char[][] board, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = '.';
            }
        }
    }

    private static void lineSeparator() {
        System.out.println("-------------");
    }

    private static void displayBoard(char[][] board, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void playerMoveTurn(char[][] board, int columns, int rows) {
        int playerColumn;
        while (true) {
            lineSeparator();
            System.out.print("Enter the column (1-7) to drop your piece: ");
            playerColumn = inputReader.nextInt();
            inputReader.nextLine();

            if (playerColumn > 0 && playerColumn <= columns && board[0][playerColumn - 1] == '.') {
                break;
            } else {
                lineSeparator();
                System.out.println("Invalid move. Try again!.");

            }
        }

        dropPieces(board, rows, playerColumn - 1, 'X');
    }

    private static void computerMoveTurn(char[][] board, int columns, int rows) {
        int lowerBound = 1;
        int upperBound = 7;
        int computerColumn;
        while (true) {
            computerColumn = lowerBound + (int) (Math.random() * (upperBound - lowerBound + 1));

            if (board[0][computerColumn - 1] == '.') {
                break;
            }
        }
        lineSeparator();
        System.out.println("Computer drops a piece in column " + computerColumn);
        lineSeparator();
        dropPieces(board, rows, computerColumn - 1, 'O');
    }

    private static void dropPieces(char[][] board, int rows, int column, char piece) {
        for (int i = rows - 1; i >= 0; i--) {
            if (board[i][column] == '.') {
                board[i][column] = piece;
                break;
            }
        }
    }

    private static boolean checkWinConditions(char[][] board, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns - 3; j++) {
                if (board[i][j] != '.' && board[i][j] == board[i][j + 1] && board[i][j] == board[i][j + 2]
                        && board[i][j] == board[i][j + 3]) {
                    return true;
                }
            }
        }

        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] != '.' && board[i][j] == board[i + 1][j] && board[i][j] == board[i + 2][j]
                        && board[i][j] == board[i + 3][j]) {
                    return true;
                }
            }
        }

        for (int i = 3; i < rows; i++) {
            for (int j = 0; j < columns - 3; j++) {
                if (board[i][j] != '.' && board[i][j] == board[i - 1][j + 1] && board[i][j] == board[i - 2][j + 2]
                        && board[i][j] == board[i - 3][j + 3]) {
                    return true;
                }
            }
        }

        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < columns - 3; j++) {
                if (board[i][j] != '.' && board[i][j] == board[i + 1][j + 1] && board[i][j] == board[i + 2][j + 2]
                        && board[i][j] == board[i + 3][j + 3]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean fullBoard(char[][] board, int rows, int columns) {
        for (int j = 0; j < columns; j++) {
            if (board[0][j] != '.') {
                return true;
            }
        }
        return false;
    }
}