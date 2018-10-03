/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int T;
    private double[] XT;
    private double X;
    private double S;

    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("size " + n + " should be bigger than 0");
        }

        if (trials <= 0) {
            throw new IllegalArgumentException("# of trials " + n + " should be bigger than 0");
        }

        T = trials;
        XT = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);

            while (!perc.percolates()) {
                int row = 1 + StdRandom.uniform(n);
                int col = 1 + StdRandom.uniform(n);

                perc.open(row, col);
            }

            XT[i] = (double)perc.numberOfOpenSites() / ( n * n );
        }

        X = StdStats.mean(XT);
        S = StdStats.stddev(XT);
    }

    public double mean() {
        return X;
    }

    public double stddev() {
        return S;
    }

    public double confidenceLo() {
        return X - 1.96 * S / Math.sqrt(T);
    }

    public double confidenceHi() {
        return X + 1.96 * S / Math.sqrt(T);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats percStats = new PercolationStats(n, trials);

        StdOut.println("mean                    = " + percStats.mean());
        StdOut.println("stddev                  = " + percStats.stddev());
        StdOut.println("95% confidence interval = [" + percStats.confidenceLo() + ", " + percStats.confidenceHi() + "]");
    }
}
