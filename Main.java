import strategies.HillClimbing;
import systeminterface.INQueens;

public class Main {

    public static void main (String [] args) {
        INQueens solver = new HillClimbing();
        solver.solve(8);
        
    }
    
}
