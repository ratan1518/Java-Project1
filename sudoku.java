import java.util.Scanner;
// Base class for handling 4x4 Sudoku functionality
class SudokuBase4x4 {
    protected char[][] board = new char[4][4];

    // Method to input the 4x4 Sudoku puzzle
    public void inputBoard() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the 4x4 Sudoku puzzle (use '.' for empty cells):");
        for (int i = 0; i < 4; i++) {
            String row;
            do {
                System.out.print("Enter row " + (i + 1) + ": ");
                row = scanner.nextLine();
            } while (row.length() != 4);  
         board[i] = row.toCharArray();
        }
        scanner.close();
    }

    // Method to print the 4x4 Sudoku board with grid lines
    public void printBoard() {
        for (int i = 0; i < 4; i++) {
            if (i == 2) System.out.println("----+----");
            for (int j = 0; j < 4; j++) {
                if (j == 2) System.out.print("| ");
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Method to check if placing a number is safe        //recursion
    protected boolean isSafe(int row, int col, char num) {
        for (int i = 0; i < 4; i++) {
            if (board[row][i] == num || board[i][col] == num) return false;
            
        }

        // Check in the 2x2 grid
        int startRow = (row / 2) * 2;
        int startCol = (col / 2) * 2;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (board[startRow + i][startCol + j] == num) return false;
            }
        }
        return true;
    }
}

// Subclass that solves the 4x4 Sudoku puzzle
class SudokuSolver4x4 extends SudokuBase4x4 {  //inheritance
    public boolean solve() {
        return solveHelper(0, 0);
    }

    private boolean solveHelper(int row, int col) {
        if (row == 4) return true; // Puzzle solved

        int nextRow = (col == 3) ? row + 1 : row;
        int nextCol = (col == 3) ? 0 : col + 1;

        if (board[row][col] != '.') return solveHelper(nextRow, nextCol); // Skip filled cells

        for (char num = '1'; num <= '4'; num++) {
            if (isSafe(row, col, num)) {
                board[row][col] = num;
                if (solveHelper(nextRow, nextCol)) return true;
                board[row][col] = '.'; // Backtrack
            }
        }
        return false; // No valid number found
    }
}

// Main class to run the 4x4 Sudoku solver
public class sudoku {
    public static void main(String[] args) {
        SudokuSolver4x4 solver = new SudokuSolver4x4();
        solver.inputBoard();
        System.out.println("Original 4x4 Sudoku:");
        solver.printBoard();

        if (solver.solve())
         {
            System.out.println("\nSolved 4x4 Sudoku:");
            solver.printBoard();
        } else {    
            System.out.println("\nNo solution exists.");
        }
    }
}