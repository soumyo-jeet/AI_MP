
package strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import systeminterface.INQueens;

public class RandomRestartHillClimbing implements INQueens {

    private int[] board;
    private int queens;
    private long iterations = 0;
    private long hx;

    private final Random random = new Random();

    // Generate random initial board
    private void generateBoard(int n) {

        board = new int[n];
        queens = n;

        for (int i = 0; i < n; i++) {
            board[i] = random.nextInt(n);
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

    // Generate all possible neighbours
    private List<int[]> getNeighbours(int[] board) {

        int n = board.length;
        List<int[]> neighbours = new ArrayList<>();

        for (int col = 0; col < n; col++) {

            for (int row = 0; row < n; row++) {

                // Skip current position
                if (board[col] == row) {
                    continue;
                }

                int[] neighbour = board.clone();

                // Move queen to another row
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

        if (bestBoards.isEmpty()) {
            return null;
        }

        // Random selection among equally good neighbours
        return bestBoards.get(random.nextInt(bestBoards.size()));
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

    // Random Restart Hill Climbing
    @Override
    public void solve(int n) {

        iterations = 0;
        hx = 0;

        if (n <= 0) {
            return;
        }

        while (true) {

            // Generate a fresh random initial state
            generateBoard(n);

            System.out.println("\nNew Random Initial State:");

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
                    System.out.println("Total Iterations: " + iterations);

                    return;
                }

                int[] neighbour = getBestNeighbour(board);

                if (neighbour == null) {
                    break;
                }

                int neighbourH = heuristic(neighbour);

                // Local minimum or plateau
                if (neighbourH >= currentH) {

                    System.out.println("Local minimum reached.");
                    System.out.println("Restarting with a new random state...");

                    break;
                }

                // Move to better neighbouring state
                board = neighbour;
            }
        }
    }

    @Override
    public String getAlgorithmName() {
        return "Random Restart Hill Climbing";
    }

    @Override
    public long getHx() {
        return hx;
    }
}
