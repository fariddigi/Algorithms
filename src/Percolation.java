/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author faridrh
 */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

 import java.util.Scanner;
 import java.io.File;
 import java.io.FileNotFoundException;
public class Percolation {

    private final boolean[][] grid;
    private final int virtualTop;
    private final int virtualBottom;

    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF uf2;
    private int count;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0)
      {
          throw new IllegalArgumentException("argument must be positive");
                  } else {
        grid = new boolean[n][n];
        count=0;
        uf = new WeightedQuickUnionUF(n * n + 2);
        uf2 = new WeightedQuickUnionUF(n * n + 2); 
        virtualTop = 0;  
        virtualBottom = n * n + 1;  
        for (int i = 1; i <= n; i++) {
            uf.union(toIndex(1, i), virtualTop); 
            uf.union(toIndex(n, i), virtualBottom); 
        }
    }
    }
    private int toIndex(int row, int col) {
        return grid.length * (row - 1) + col;
    }
    
     public boolean isOpen(int row, int col) {
        if (row <= 0 || col <= 0 || row > grid.length || col > grid.length) {
            throw new java.lang.ArrayIndexOutOfBoundsException("IndexOutOfBounds");
        } else {
            return grid[row - 1][col - 1];
        }

    }
    

    public void open(int row, int col) {
        if (row <= 0 || col <= 0 || row > grid.length || col > grid.length) {
             throw new java.lang.ArrayIndexOutOfBoundsException("IndexOutOfBounds");
        }
        if (!isOpen(row,col)) {
        grid[row - 1][col - 1] = true;
       
           count++;
       
       
        if (row == 1) {
            uf.union(toIndex(row, col), virtualTop);
            uf2.union(toIndex(row, col), virtualTop);

        }
        if (row == grid.length) {
            uf.union(toIndex(row, col), virtualBottom);
          //  uf2.union(toIndex(row, col), virtualBottom);

        }

        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(toIndex(row, col), toIndex(row, col - 1));
            uf2.union(toIndex(row, col), toIndex(row, col - 1));

        }
        if (col < grid.length && isOpen(row, col + 1)) {
            uf.union(toIndex(row, col), toIndex(row, col + 1));
            uf2.union(toIndex(row, col), toIndex(row, col + 1));

        }
        if (row > 1 && isOpen(row - 1, col)) {
            uf.union(toIndex(row, col), toIndex(row - 1, col));
            uf2.union(toIndex(row, col), toIndex(row - 1, col));

        }
        if (row < grid.length && isOpen(row + 1, col)) {
            uf.union(toIndex(row, col), toIndex(row + 1, col));
            uf2.union(toIndex(row, col), toIndex(row + 1, col));
           
        }
        }
    }

   

    public boolean isFull(int row, int col) {
        if (row <= 0 || col <= 0 || row > grid.length || col > grid.length) {
            throw new java.lang.ArrayIndexOutOfBoundsException("IndexOutOfBounds");
        } else { return isOpen(row, col) && uf2.connected(toIndex(row, col), virtualTop);
   
    }
    }
    public int numberOfOpenSites() { // number of open sites
        
//        int count = 0;
//        for (int i = 1; i <= grid.length; i++) {
//            for (int j = 1; j <= grid.length; j++) {
//                if (isOpen(i, j)) {
//                    count = count + 1;
//                }
//            }
//        }
        
        return count;
    }

    public boolean percolates() {
        if (numberOfOpenSites() > 0) {
            return uf.connected(virtualTop, virtualBottom);
        } else {
            return false;
        }
    }

// test client (optional)

    public static void main(String[] args) throws FileNotFoundException{
        File file;
      file = new File ("/home/faridrh/Documents/algorithms_course/week1/percolation/input10.txt");
      Scanner scan = new Scanner(file) ;
      int q = scan.nextInt();
      Percolation p;
      p= new Percolation(q);
      while(scan.hasNext() ){
         int a,b;
         a=scan.nextInt();
         b=scan.nextInt();
         p.open(a,b);
      }
        System.out.println("virtt:"+p.virtualTop);
        System.out.println(p.isFull(7,1));
        boolean c= p.percolates();
        System.out.println("percolates:" + c);
        PercolationStats ps1;
        ps1 = new PercolationStats(4,100);
        System.out.println("num:"+p.numberOfOpenSites());
}
}
