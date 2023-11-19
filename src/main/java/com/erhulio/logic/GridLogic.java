package com.erhulio.logic;

import java.util.Random;

public class GridLogic {

    public static boolean[][] startGrid(int n, int m) {
        Random rand_numb = new Random();
        boolean[][] grid;
        double probability;
        grid = new boolean[n][m];
        probability = (n*m) < 500 ? 0.3 : 0.15;
        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                grid[i][j] = rand_numb.nextFloat() < probability ? true : false;
            }
        }
        return grid;
    }

    public static boolean[][] updateGrid(boolean[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int n_neighbours = 0;
        boolean[][] new_grid = new boolean[n][m];
        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                for(int x = -1; x<2; x++) {
                    for(int y = -1; y<2; y++) {
                        try {
                            if(grid[i+x][j+y] && (x!=0 || y!=0)){
                                n_neighbours++;
                            }
                        }
                        catch(IndexOutOfBoundsException e) {
                            ;
                        }
                    }
                }
                if(grid[i][j]) {
                    new_grid[i][j] = (n_neighbours > 3 || n_neighbours < 2) ? false : true;
                }
                else {
                    new_grid[i][j] = n_neighbours == 3 ? true : false;
                }
                n_neighbours = 0;
            }
        }
        return new_grid;
    }
}
