package decorators;

import systeminterface.INQueens;

public class Logger implements INQueens {
    private INQueens solver;

    public Logger (INQueens solver) {
        this.solver = solver;
    }
    
    @Override 
    public void solve(int n) {
        System.out.println("-----------------------------------------------------------");
        System.out.println(n + " Queen Problem is beign solved by " + solver.getAlgorithmName() + " Algorithm.");

        solver.solve(n);

        System.out.println("Execution completed.");
        System.out.println("-----------------------------------------------------------");
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
