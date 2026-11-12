package strategies;

import systeminterface.INQueens;
import java.util.*;

public class BeamSearching implements INQueens {
    private final int width;
    private long hx=-1;
    public BeamSearching(int width){
        this.width=width;
    }
    
    private int heuristic(int[] board) {
        int conflict = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = i + 1; j < board.length; j++) {
                if (board[i] == board[j]) conflict++;
                if (Math.abs(board[i] - board[j]) == Math.abs(i - j)) conflict++;
            }
        }
        return conflict;
    }

    @Override
    public void solve(int n) {
        hx=-1;
        Scanner sc=new Scanner(System.in);

        ArrayList<int[]> board = new ArrayList<>();

        board.add(new int[0]);

        while (!board.isEmpty()) {
            ArrayList<int[]> candidates = new ArrayList<>();

            for (int[] state : board) {
                //System.out.println("Current state: " + Arrays.toString(state));
                if (state.length == n && heuristic(state) == 0) {
                    hx=0;
                    System.out.println("Solution found..");
                    printBoard(state, n);
                    return;
                }
                int row = state.length, length;
                for (int col = 0; col < n; col++) {
                    int[] newState = Arrays.copyOf(state, row + 1);
                    newState[row] = col;

                    if (heuristic(newState) == 0) candidates.add(newState);
                }
            }
            //System.out.println("Candidates: " + candidates.size());
            if (candidates.isEmpty()) {
                System.out.println("No solution found.");
                return;
            }
            candidates.sort(Comparator.comparingInt(this::heuristic));
            board = new ArrayList<>(candidates.subList(0, Math.min(width, candidates.size())));
        }
        System.out.println("No solution found.");
    }

    private void printBoard(int[] state, int n) {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (state[row] == col) System.out.print("Q");
                else System.out.print(".");
            }
            System.out.println();
        }
    }

    @Override 
    public String getAlgorithmName() {
        return "Beam Searching Algorithm(Beam Width: "+this.width+")";
    }    

    @Override
    public long getHx() {
        return hx;
    }
}
    

