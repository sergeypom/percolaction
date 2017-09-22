import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    private WeightedQuickUnionUF arrSite;
    private WeightedQuickUnionUF arrSite1;
    private final int sizeArray;
    private final int n; // size
    private  byte[] arrSiteOpen;
    private int opened;

    /**
     * create n-by-n grid, with all sites blocked
     * @param  n size of array
     */
    public Percolation(int n)
    {
        if (n <= 0) throw new IllegalArgumentException("N less 0 or equal zero 0");
        arrSite = new WeightedQuickUnionUF(n*n);
        arrSite1 = new WeightedQuickUnionUF(n*n);
        sizeArray = n*n;
        this.n = n;
        this.arrSiteOpen = new byte[n*n];
        this.opened = 0;


    }

    /**
     * open site (row i, column j) if it is not open already
     *
     * @param i row
     * @param j column
     */
    public void open(int i, int j)
    {

       if (!isOpen(i, j)) {
           arrSiteOpen[xyTo1D(i, j)] = 1;
           opened++;
               if (i == 1) {
                   arrSite.union(0, xyTo1D(i, j));
                   arrSite1.union(0, xyTo1D(i, j));
               }

               if (i == n) {
                   arrSite.union(xyTo1D(n, n), xyTo1D(i, j));
               }
               if (i > 1 && isOpen(i - 1, j)) {
                   arrSite.union(xyTo1D(i - 1, j), xyTo1D(i, j));
                   arrSite1.union(xyTo1D(i - 1, j), xyTo1D(i, j));

               }
               if (i < n && isOpen(i + 1, j)) {
                   arrSite.union(xyTo1D(i, j), xyTo1D(i + 1, j));
                   arrSite1.union(xyTo1D(i, j), xyTo1D(i + 1, j));

               }
               if (j > 1 && isOpen(i, j - 1)) {
                   arrSite.union(xyTo1D(i, j - 1), xyTo1D(i, j));
                   arrSite1.union(xyTo1D(i, j - 1), xyTo1D(i, j));

               }
               if (j < n && isOpen(i, j + 1)) {
                   arrSite.union(xyTo1D(i, j), xyTo1D(i, j + 1));
                   arrSite1.union(xyTo1D(i, j), xyTo1D(i, j + 1));

               }

        }
    }

    /**
     *
     * @param i raw
     * @param j column
     * @return true if size not equals 0
     */
    public boolean isOpen(int i, int j)   {
        return arrSiteOpen[xyTo1D(i, j)] != 0;
    }

    /**
     * // is site (row i, column j) full?
     * @param i raw
     * @param j column
     * @return
     */
    public boolean isFull(int i, int j)
    {
       if (!isOpen(i, j)) return false;
       return  arrSite1.connected(0, xyTo1D(i, j));
    }
    /**
     * does the system percolate?
     * @return true if system percolate
     */
    public boolean percolates()
    {
        return  opened > 0 && arrSite.connected(0, xyTo1D(n, n));
    }

    /**
     *
     * @param x row
     * @param y column
     * @return the integer represent size
     */
    private int xyTo1D(int x, int y)
    {
        checkIndex(x, y);
        return (x-1)*n+(y-1);
    }

    /**
     * check valid index
     * @param x raw
     * @param y column
     */
     private void checkIndex(int x, int y)
     {
        if (x <= 0 || x > n)
            throw new IndexOutOfBoundsException("row index x out of bounds");
        if (y <= 0 || y > n)
            throw new IndexOutOfBoundsException("row index y out of bounds");
     }

}
