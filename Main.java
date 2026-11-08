import decorators.Logger;
import decorators.RunTimeCalculator;
import strategies.HillClimbing;
import systeminterface.INQueens;
import utils.AccuracyCalculator;

public class Main {

    public static void main (String [] args) {
        INQueens solver = new Logger(new RunTimeCalculator(new HillClimbing()));
        // solver.solve(4);

        new AccuracyCalculator().accuracyScore(solver, 10);
    }
    
}
