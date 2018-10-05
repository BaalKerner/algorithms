/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF wquUp;
    private WeightedQuickUnionUF wquDown;
    private boolean[] openSites;
    private int numOfOpenSites;
    private int N;
    private boolean percolates;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("size " + n + " should be bigger than 0");
        }

        N = n;
        numOfOpenSites = 0;
        wquUp = new WeightedQuickUnionUF(N * N + 1);
        wquDown = new WeightedQuickUnionUF(N * N + 1);
        openSites = new boolean[N * N + 1];
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
            wquUp.union(index, 0);
        } else {
            connect(row - 1, col, index, wquUp);
            connect(row - 1, col, index, wquDown);
        }

        if (row == N) {
            wquDown.union(index, 0);
        } else {
            connect(row + 1, col, index, wquUp);
            connect(row + 1, col, index, wquDown);
        }

        if (col > 1) {
            connect(row, col - 1, index, wquUp);
            connect(row, col - 1, index, wquDown);
        }

        if (col < N) {
            connect(row, col + 1, index, wquUp);
            connect(row, col + 1, index, wquDown);
        }

        if (wquUp.connected(0, index) && wquDown.connected(0, index)) {
            percolates = true;
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

        return wquUp.connected(index, 0);
    }

    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    public boolean percolates() {
        return percolates;
    }

    public static void main(String[] args) {

    }

    private void connect(int row, int col, int index, WeightedQuickUnionUF wqu) {
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
