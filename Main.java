import strategies.BeamSearching;
//import strategies.HillClimbing;
import systeminterface.INQueens;

public class Main {

    public static void main (String [] args) {
        /*INQueens solver = new HillClimbing();
        solver.solve(4);*/

        INQueens solver1 = new BeamSearching();
        solver1.solve(4);
        
    }
    
}
