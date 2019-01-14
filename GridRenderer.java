package com.company;

public class GridRenderer {

    GridRenderer(){

    }

    public void drawGrid(int[][] grid) {

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                int current = grid[row][col];
                switch(current){
                    case 0: System.out.print(" "); break;
                    case 1: System.out.print("#"); break;
                    case 2: System.out.print("x"); break;
                    case 3: System.out.print("E"); break;
                    case 4: System.out.print("S"); break;
                }
            }
            System.out.print("\n");
        }
    }

}
