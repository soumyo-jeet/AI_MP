package utils;

import java.util.HashMap;
import systeminterface.INQueens;

public class AccuracyCalculator {
    public static void accuracyScore (INQueens solver, int k){
        double overall = 0;
        HashMap<Integer, Double> record = new HashMap<>();

        System.out.println("Accuracy calculating for algorithm " + solver.getAlgorithmName() + " with " + k + " test cases.");

        int[] tests = {8, 10, 20, 50};

        for(int test : tests) {
            double success = 0;

            for(int i = 1; i <= k; i++) {
            
                System.out.println("Test Case: " + i);
                solver.solve(test);
                long h = solver.getHx();

                if(h == 0) success++;

                // try {
                //     Thread.sleep(5000);
                // } catch (InterruptedException e) {
                //     e.printStackTrace();
                // }

            }
            record.put(test, success / k);

            // try {
            //         Thread.sleep(10000);
            //     } catch (InterruptedException e) {
            //         e.printStackTrace();
            //     }
            
            overall += success ;
        }

        System.out.println("Algorithm: " + solver.getAlgorithmName());
        System.out.println("--------------------------------");
        System.out.println("Accuracy Score for 8 Queen Problem: " + record.get(8));
        System.out.println("Accuracy Score for 10 Queen Problem: " + record.get(10));
        System.out.println("Accuracy Score for 20 Queen Problem: " + record.get(20));
        System.out.println("Accuracy Score for 50 Queen Problem: " + record.get(50));
        System.out.println("--------------------------------");
        System.out.println("Overall Accuracy Score: " + overall / (4 * k));
    }
}
