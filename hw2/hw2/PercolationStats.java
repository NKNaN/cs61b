package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private double[] fracs;

    private int[] oneDtoXy(int x, int N) {
        int[] ret = new int[2];
        ret[0] = x / N;
        ret[1] = x % N;
        return ret;
    }

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Arguments should be greater than 0");
        }
        Percolation p = null;
        fracs = new double[T];
        for (int i = 0; i < T; i++) {
            p = pf.make(N);
            while (!p.percolates()) {
                int r = StdRandom.uniform(N * N);
                int[] point = oneDtoXy(r, N);
                p.open(point[0], point[1]);
            }
            fracs[i] = (double) p.numberOfOpenSites() / (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(fracs);
    }

    public double stddev() {
        return StdStats.stddev(fracs);
    }

    public double confidenceLow() {
        int t = fracs.length;
        return mean() - 1.96 * stddev() / Math.sqrt(t);
    }

    public double confidenceHigh() {
        int t = fracs.length;
        return mean() + 1.96 * stddev() / Math.sqrt(t);
    }

/*    public static void main(String[] arg) {
        PercolationStats p = new PercolationStats(20, 50, new PercolationFactory());
        System.out.println(p.mean());
        System.out.println(p.stddev());
        System.out.println(p.confidenceLow());
        System.out.println(p.confidenceHigh());
    }*/
}
