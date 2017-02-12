/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author faridrh
 */
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
/* perform trials independent experiments on an n-by-n grid */ 
public class PercolationStats {
        private int experiments;
        private final double[] fractions;

    /**
     *
     * @param n
     * @param trials
     */
    public  PercolationStats(int n, int trials) {
      if (n <= 0 || trials <= 0)
      {
          throw new IllegalArgumentException("argument must be positive");
                  }
      else {
      Percolation per1;
      experiments = trials;
      int openedSites;
      fractions = new double[experiments];   
      for (int i = 0; i < trials; i++) {
             per1 = new Percolation(n);   
            openedSites = 0;  
          while (!per1.percolates()) {
              int row = StdRandom.uniform(1, n+1); 
              int col = StdRandom.uniform(1, n+1); 
              if (!per1.isOpen(row, col)) {
                  per1.open(row, col);
                  openedSites = openedSites+1; 
              }

          } 
              fractions[i] = (double) openedSites/ (n*n);
      }
       }
    }

public double mean() {
// sample mean of percolation threshold
return StdStats.mean(fractions);
      }

//}
   public double stddev()    {
// sample standard deviation of percolation threshold
return StdStats.stddev(fractions);
}


   public double confidenceLo()  {
// low  endpoint of 95% confidence interval
return  mean() - 1.96*stddev()/Math.sqrt(experiments);

   }
   public double confidenceHi()  {
   // high endpoint of 95% confidence interval
   return  mean() + 1.96 * stddev()/Math.sqrt(experiments);
}

   public static void main(String[] args)   {     // test client (described below)
//             int N = Integer.parseInt(args[0]);
//        int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(50, 20);

        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.printf("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);
}


   }


