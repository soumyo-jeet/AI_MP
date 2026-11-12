import decorators.Logger;
import decorators.RunTimeCalculator;
import strategies.BeamSearching;
import strategies.HillClimbing;
import strategies.RandomRestartHillClimbing;
import systeminterface.INQueens;
import utils.AccuracyCalculator;

public class Main {

    public static void main (String [] args) {

        // solvers with 1 test case
        INQueens solver1 = new Logger(new RunTimeCalculator(new HillClimbing()));
        // solver1.solve(4);
        
        INQueens solver2 = new Logger(new RunTimeCalculator(new RandomRestartHillClimbing()));
        // solver2.solve(4);

        // INQueens solver3 = new Logger(new RunTimeCalculator(new BeamSearching()));
        // solver3.solve(4);

        // accuracy calculations
        // AccuracyCalculator.accuracyScore(solver1, 100);
        // AccuracyCalculator.accuracyScore(solver2, 100);
        // AccuracyCalculator.accuracyScore(solver3, 100);
    }
    
}
