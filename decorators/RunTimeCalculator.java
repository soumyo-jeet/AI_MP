package decorators;

import java.time.LocalDateTime;
import java.time.Duration;
import systeminterface.INQueens;

public class RunTimeCalculator implements INQueens {
    private INQueens solver;

    public RunTimeCalculator (INQueens solver) {
        this.solver = solver;
    }

    @Override
    public void solve(int n) {
        LocalDateTime start = LocalDateTime.now();

        solver.solve(n);

        LocalDateTime end = LocalDateTime.now();
        System.out.println("Runtime: " + Duration.between(start, end).toMillis() + " ms");
    }

    @Override
    public String getAlgorithmName() {
        return solver.getAlgorithmName();
    }

    @Override
    public long getHx() {
        return solver.getHx();
    }
    
}
