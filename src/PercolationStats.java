import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * * serg
 * Created by serg on 29.09.2016.
 */
public class PercolationStats {
    private double[] arr;
    private final int  trials;
    private final int n;

    /**
     * *  perform trials independent experiments on an n-by-n grid

     * @param n number size
     * @param trials number time repeat
     */
     public PercolationStats(int n, int trials) {
         this.n = n;
         this.trials = trials;
         if (this.n <= 0 || this.trials <= 0)
         {
            throw new IllegalArgumentException("argument");
         }
            int opened;
            arr = new double[this.trials];
            for (int t = 0; t < this.trials; t++) {
                int i, j;

                // repeatedly read in sites to open and draw resulting system
                Percolation perc = new Percolation(this.n);
                opened = 0;
                do {
                    do {
                        i = StdRandom.uniform(1, this.n + 1);
                        j = StdRandom.uniform(1, this.n + 1);
                    } while (perc.isOpen(i, j));
                    perc.open(i, j);
                    opened++;
                } while (!perc.percolates());
                arr[t] = (double) opened / (this.n * this.n);
            }


    }

    /**
     * // sample mean of percolation threshold
     * @return mean
     */
    public double mean()
    {
        if (checkParam()) {
            return StdStats.mean(arr);
        } else {
            return Double.NaN;
        }
    }

    /**
     * // sample standard deviation of percolation threshold
     * @return stddev
     */
    public double stddev()
    {
        if (checkParam()) {
            return StdStats.stddev(arr);
        } else {
            return Double.NaN;
        }
    }

    /**
     * // low  endpoint of 95% confidence interval
     * @return low  endpoint
     */
    public double confidenceLo()
    {
        if (checkParam()) {
            return mean() - (1.96 * stddev() / Math.sqrt(this.trials));
        } else {
            return Double.POSITIVE_INFINITY;
        }
    }

    /**
     * // high endpoint of 95% confidence interval
     * @return high endpoint
     */
    public double confidenceHi()
    {
        if (checkParam()) {
            return mean() + (1.96 * stddev() / Math.sqrt(this.trials));
        } else {
            return Double.NEGATIVE_INFINITY;
        }
    }

    private boolean checkParam()
    {
        return this.n > 0 && this.trials > 0;
    }

    public static void main(String[] args)  // test client (described below)
    {
        PercolationStats percolationStats =  new PercolationStats(20, 10);
        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.println("stddev                  = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = "
                + percolationStats.confidenceLo() +
                "," + percolationStats.confidenceHi());

    }
}
