import java.util.Scanner;

public class TicTacToe{
    public static void main(String[] args) {
        int[][] board = {
            {-1, -1, -1},
            {-1, -1, -1},
            {-1, -1, -1}
        };

        int currentPlayer = 1; // 1 for user, 0 for agent
        boolean gameOver = false;

        Scanner scanner = new Scanner(System.in);



        while (!gameOver) {
            printBoard(board);

            // if (currentPlayer == 1) {
            //     userMove(board, scanner);
            // } else {
            //     agentMove(board);
            // }
            userMove(board, scanner);
            printBoard(board);
            agentMove(board);
            printBoard(board);

            int gameState = checkTerminalState(board);
            if (gameState != 0) {
                printBoard(board);
                if (gameState == 1) {
                    System.out.println("User wins!");
                } else if (gameState == -1) {
                    System.out.println("Agent wins!");
                } else {
                    System.out.println("It's a tie!");
                }
                gameOver = true;
            }

            // Switch players
            // currentPlayer = 1 - currentPlayer;
        }

        scanner.close();
    }

    public static void printBoard(int[][] board) {
        System.out.println("Tic-Tac-Toe Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 1) {
                    System.out.print("X ");
                } else if (board[i][j] == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void userMove(int[][] board, Scanner scanner) {
        int row, col;
        // do {
            // System.out.print("Enter your move (row and column, e.g., 1 2): ");
            // row = scanner.nextInt() - 1;
            // col = scanner.nextInt() - 1;
        // } while (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != 0);
        System.out.print("Enter your move (row and column, e.g., 1 2): ");
        row = scanner.nextInt() - 1;
        col = scanner.nextInt() - 1;
        board[row][col] = 1;
    }

    public static void agentMove(int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                    
                    return;
                }
            }
        }
    }

    public static int checkTerminalState(int[][] board) {
        for (int i = 0; i < 3; i++) {
            // cols
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if(board[i][0]==1){
                return 1;
            }
            else if(board[i][0]==0){
                return -1;
            }
            else{
                return 0;
            }
                // return board[i][0] == 1 ? 1 : -1; 
            }
            // rows
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                if(board[0][i]==1){
                return 1;
            }
            else if(board[0][i]==0){
                return -1;
            }
            else{
                return 0;
            }
                // return board[0][i] == 1 ? 1 : -1;
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if(board[0][0]==1){
                return 1;
            }
            else if(board[0][0]==0){
                return -1;
            }
            else{
                return 0;
            }
            // return board[0][0] == 1 ? 1 : -1;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if(board[0][2]==1){
                return 1;
            }
            else if(board[0][2]==0){
                return -1;
            }
            else{
                return 0;
            }
            // return board[0][2] == 1 ? 1 : -1;
        }

        // Check for a tie
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return 0; // Game is still ongoing
                }
            }
        }

        // If no win or tie, the game is ongoing
        return 0;
    }
    
}
