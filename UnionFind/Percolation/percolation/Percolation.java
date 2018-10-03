/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF wqu;
    private boolean[] openSites;
    private int numOfOpenSites;
    private int N;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("size " + n + " should be bigger than 0");
        }

        N = n;
        numOfOpenSites = 0;
        wqu = new WeightedQuickUnionUF(N * N + 2);
        openSites = new boolean[N * N + 2];
    }

    public void open(int row, int col) {
        isValidIndex(row);
        isValidIndex(col);

        if (isOpen(row, col)) {
            return;
        }

        int index = xyTo1d(row, col);
        openSites[index] = true;
        numOfOpenSites++;

        if (row == 1) {
            wqu.union(index, 0);
        } else {
            connect(row - 1, col, index);
        }

        if (row == N) {
            wqu.union(index, N * N + 1);
        } else {
            connect(row + 1, col, index);
        }

        if (col > 1) {
            connect(row, col - 1, index);
        }

        if (col < N) {
            connect(row, col + 1, index);
        }
    }

    public boolean isOpen(int row, int col) {
        isValidIndex(row);
        isValidIndex(col);

        int index = xyTo1d(row, col);

        return openSites[index];
    }

    public boolean isFull(int row, int col) {
        isValidIndex(row);
        isValidIndex(col);

        int index = xyTo1d(row, col);

        return wqu.connected(index, 0);
    }

    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    public boolean percolates() {
        return wqu.connected(0, N * N + 1);
    }

    public static void main(String[] args) {

    }

    private void connect(int row, int col, int index) {
        if (isOpen(row , col)) {
            int indexUp = xyTo1d(row , col);

            wqu.union(index, indexUp);
        }
    }

    private void isValidIndex(int i) {
        if (i <= 0 || i > N) {
            throw new IllegalArgumentException("index " + i + " is not between 0 and " + (N - 1) );
        }
    }

    private int xyTo1d(int row, int col) {
        return (row - 1) * N + col;
    }
}
