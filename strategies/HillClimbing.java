
package strategies;

import java.util.ArrayList;
import java.util.List;
import systeminterface.INQueens;

public class HillClimbing implements INQueens {

    private int[] board;
    private int queens;
    private long  iterations = 0;
    private long hx;

    // Generate initial board
    private void generateBoard(int n) {
        board = new int[n];
        queens = n;

        // Initially all queens are placed in row 0
        for (int i = 0; i < n; i++) {
            board[i] = 0;
        }
    }

    // Calculate number of attacking pairs
    private int heuristic(int[] board) {

        int conflicts = 0;
        int n = board.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                // Same row or diagonal attack
                if (board[i] == board[j] ||
                    Math.abs(board[i] - board[j]) == Math.abs(i - j)) {

                    conflicts++;
                }
            }
        }

        return conflicts;
    }

    private List<int[]> getNeighbours(int[] board) {

        int n = board.length;
        List<int[]> neighbours = new ArrayList<>();

        for (int col = 0; col < n; col++) {

            for (int row = 0; row < n; row++) {

                // Skip the current position
                if (board[col] == row) {
                    continue;
                }

                // Create a new neighbouring state
                int[] neighbour = board.clone();

                // Move the queen to another row
                neighbour[col] = row;

                neighbours.add(neighbour);
            }
        }

        return neighbours;
    }

    // Find the best neighbouring board
    private int[] getBestNeighbour(int[] board) {

        List<int[]> neighbours = getNeighbours(board);

        int bestH = Integer.MAX_VALUE;
        List<int[]> bestBoards = new ArrayList<>();

        for (int[] neighbour : neighbours) {

            int h = heuristic(neighbour);

            if (h < bestH) {

                bestH = h;
                bestBoards.clear();
                bestBoards.add(neighbour);
            }
            else if (h == bestH) {

                bestBoards.add(neighbour);
            }
        }

        // Random selection among equally good neighbours
        // Only tie-breaking; no random restart
        if (bestBoards.isEmpty()) {
            return null;
        }

        return bestBoards.get(
            (int) (Math.random() * bestBoards.size())
        );
    }


    
    // Display chessboard
    void printBoard(int[] board) {

        int n = board.length;

        for (int row = 0; row < n; row++) {

            for (int col = 0; col < n; col++) {

                if (board[col] == row) {
                    System.out.print(" Q ");
                }
                else {
                    System.out.print(" . ");
                }
            }

            System.out.println();
        }

        System.out.println("Heuristic: " + heuristic(board));
    }


    // Hill Climbing 
    @Override
    public void solve(int n) {

        iterations = 0;

        if (n <= 0) {
            return;
        }

        generateBoard(n);

        while (true) {

            iterations++;

            System.out.println("\nIteration: " + iterations);
            System.out.println("Current State:");
            printBoard(board);

            int currentH = heuristic(board);
            this.hx = currentH;

            // Goal state reached
            if (currentH == 0) {
                System.out.println("Solution Found!");
                return;
            }

            int[] neighbour = getBestNeighbour(board);

            // No neighbour available
            if (neighbour == null) {
                System.out.println("No neighbours available.");
                return;
            }

            int neighbourH = heuristic(neighbour);

            // Local minimum or plateau: terminate
            if (neighbourH >= currentH) {

                System.out.println("No better neighbour found.");
                System.out.println("Hill Climbing terminated.");
                return;
            }

            // Move to the better neighbouring state
            board = neighbour;
        }
    }


    @Override 
    public String getAlgorithmName() {
        return "Hill Climbing";
    }    

    @Override
    public long getHx() {
        return hx;
    }
}
