package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grids; // 0: blocked; 1: open
    private WeightedQuickUnionUF grids1DTop;
    private WeightedQuickUnionUF grids1DBottom;
    private int numberOfOpenSites;
    private int top; // index in UF
    private int bottom; // index in UF

    private boolean percolate = false;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N should be greater than 0.");
        }
        grids = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grids[i][j] = 0;
            }
        }
        grids1DTop = new WeightedQuickUnionUF(N * N + 1);
        grids1DBottom = new WeightedQuickUnionUF(N * N + 1);
        top = N * N;
        bottom = N * N;
        numberOfOpenSites = 0;
    }

    public void open(int row, int col) {
        if (row < 0 || row >= grids.length || col < 0 || col >= grids.length) {
            throw new IndexOutOfBoundsException("Index range should be [0, N-1].");
        }
        if (isOpen(row, col)) {
            return;
        }
        grids[row][col] = 1;
        numberOfOpenSites++;
        connectSurroundingOpenSite(row, col);
        if (row == 0) {
            grids1DTop.union(top, xyTo1D(row, col));
        } else if (row == grids.length - 1) {
            grids1DBottom.union(bottom, xyTo1D(row, col));
        }
        if (isFull(row, col) && grids1DBottom.connected(bottom, xyTo1D(row, col))) {
            percolate = true;
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= grids.length || col < 0 || col >= grids.length) {
            throw new IndexOutOfBoundsException("Index range should be [0, N-1].");
        }
        return grids[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row >= grids.length || col < 0 || col >= grids.length) {
            throw new IndexOutOfBoundsException("Index range should be [0, N-1].");
        }
        return grids1DTop.connected(top, xyTo1D(row, col));
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return percolate;
    }

    private int xyTo1D(int x, int y) {
        int N = grids.length;
        return N * x + y;
    }

    private void connectSurroundingOpenSite(int row, int col) {
        int N = grids.length;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ((i == -1 || i == 1) && (j == -1 || j == 1)) {
                    continue;
                }
                int x = row + i;
                int y = col + j;
                if (x >= 0 && x < N && y >= 0 && y < N) {
                    if (isOpen(x, y)) {
                        grids1DTop.union(xyTo1D(x, y), xyTo1D(row, col));
                    }
                }
            }
        }
    }
/*
    public static void main(String[] args) {
        Percolation p = new Percolation(4);
        System.out.println(p.numberOfOpenSites()); // 0
        System.out.println(p.isFull(1,3)); // f
        System.out.println(p.isOpen(1,3)); // f
        p.open(1,3);
        System.out.println(p.isFull(1,3)); // f
        System.out.println(p.isOpen(1,3)); // t
        System.out.println("--------------------\n");
        p.open(2,2);
        System.out.println(p.isFull(2,2)); // f
        System.out.println(p.isOpen(2,2)); // t
        System.out.println(p.isFull(1,2)); // f
        System.out.println(p.isOpen(1,2)); // f
        System.out.println("--------------------\n");
        p.open(0,3);
        System.out.println(p.isFull(0,3)); // t
        System.out.println(p.isOpen(0,3)); // t
        System.out.println(p.isFull(1,3)); // t
        System.out.println(p.isOpen(1,3)); // t
        System.out.println(p.isFull(2,2)); // f
        System.out.println(p.isOpen(2,2)); // t
        System.out.println(p.numberOfOpenSites()); // 3
        System.out.println("--------------------\n");
        p.open(1,2);
        System.out.println(p.isFull(1,2)); // t
        System.out.println(p.isOpen(1,2)); // t
        System.out.println(p.isFull(2,2)); // t
        System.out.println(p.isOpen(2,2)); // t
        System.out.println(p.numberOfOpenSites()); // 4
        System.out.println(p.percolates()); // f
        System.out.println("--------------------\n");
        p.open(3,2);
        System.out.println(p.numberOfOpenSites()); // 5
        System.out.println(p.percolates()); // t
    }
*/
}
