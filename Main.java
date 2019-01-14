package com.company;

public class Main {

    //Done in about 2-3pm to 4:30pm?

    //I was considering implementing Dijkstra's or A*, but frankly it seemed more than a little overkill in this instance, so I've settled on BFS.
    //It doesn't really matter for other inputs, but it's pretty obviously not the optimal shortest path on that open-field maze one.
    //
    //The BFS solution is self-implemented and as I've tried not to crib from Wikipedia/StackOverflow too much besides a very brief refresher to roughly remind myself
    //how BFS is structured. It's also my first ever actual implementation of BFS so between that and being written on-the-fly in a relatively short amount of time,
    //there's a bit of goofiness in terms of


    public static void main(String[] args) {

        GridParser gp = new GridParser(args[0]);

        GridRenderer gr = new GridRenderer();

        MazeCompute mc = new MazeCompute(gp.getWalls(), gp.getGrid(), gp.startX, gp.startY, gp.endX, gp.endY);

        int [][] grid = mc.plotRoute(mc.computePath());

        gr.drawGrid(grid);
    }
}
