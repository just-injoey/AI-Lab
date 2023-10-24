public class TerminalChecker {
    public static void main(String[] args) {
        int[][] board = {
            {1, 0, 1},
            {0, 1, 0},
            {0, 0, 1}
        };

        int result = checkTerminalState(board);
        System.out.println("Result: " + result);
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
