import java.util.*;

public class NQueens {
    private int n;
    private int[][] board;

    public NQueens(int n) {
        this.n = n;
        board = new int[n][n];
    }

    // Function to check if it's safe to place a queen
    private boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1 || (col - (row - i) >= 0 && board[i][col - (row - i)] == 1) 
                || (col + (row - i) < n && board[i][col + (row - i)] == 1)) 
                return false;
        }
        return true;
    }

    // Backtracking function to solve N-Queens
    private boolean solve(int row) {
        if (row >= n) return true;
        for (int col = 0; col < n; col++) {
            if (isSafe(row, col)) {
                board[row][col] = 1;
                if (solve(row + 1)) return true;
                board[row][col] = 0; // Backtrack
            }
        }
        return false;
    }

    // Display the board
    private void display() {
        for (int[] row : board) {
            for (int cell : row) System.out.print(cell == 1 ? "Q " : ". ");
            System.out.println();
        }
    }

    // Place the first queen and solve
    public void solveWithFirstQueen(int row, int col) {
        board[row][col] = 1;
        if (solve(row + 1)) display();
        else System.out.println("No solution exists with the first queen at (" + row + ", " + col + ")");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the chessboard (n): ");
        int n = scanner.nextInt();
        System.out.print("Enter the row and column to place the first queen (0-based index): ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        
        if (row < n && col < n) new NQueens(n).solveWithFirstQueen(row, col);
        else System.out.println("Invalid position for the first queen.");

        scanner.close();
    }
}




/*
indev will allways starts from 0
Enter the size of the chessboard (n): 4
Enter the row and column to place the first queen (0-based index): 0 2
. . Q . 
Q . . .
. . . Q
. Q . .

Enter the size of the chessboard (n): 5
Enter the row and column to place the first queen (0-based index): 0 3
. . . Q .
Q . . . .
. . Q . .
. . . . Q
. Q . . .

*/