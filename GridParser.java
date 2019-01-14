package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GridParser {

    int[][] grid;
    Scanner sc;
    boolean[][] walls;
    int startY;
    int startX;
    int endY;
    int endX;

    // Standard stuff. I'm keeping track of the walls as I parse so I can later use that as my baseline for BFS.

    GridParser(String filepath){

        try {
            File f = new File(filepath);
            sc = new Scanner(f);
            parse(sc);

        } catch (FileNotFoundException e) {
            e.printStackTrace(); // All inputs are "perfect" in this example scenario.
        }
    }

    private void parse(Scanner sc){

        int gridW = sc.nextInt();
        int gridH = sc.nextInt();

        startX = sc.nextInt();
        startY = sc.nextInt();

        endX = sc.nextInt();
        endY = sc.nextInt();

        grid = new int[gridH][gridW];
        walls = new boolean[gridH][gridW];

        for(int row = 0; row < gridH; row++){
            for(int col = 0; col < gridW; col++){
                grid[row][col] = sc.nextInt();
                if(grid[row][col] == 1){
                    walls[row][col] = true;
                }
            }
        }
    }

    public int[][] getGrid(){
        return grid;
    }

    public boolean[][] getWalls(){
        return walls;
    }



}
