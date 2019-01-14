package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class MazeCompute {

    boolean[][] checkedNodes;
    int[][] grid;
    int startX,startY, endX, endY;

    Queue<Node> nodesToVisit = new LinkedList<>();


    MazeCompute(boolean[][] existingWalls, int[][] parsedGrid, int sX, int sY, int eX, int eY){

        checkedNodes = existingWalls;
        grid = parsedGrid;
        startX = sX;
        startY = sY;
        endX = eX;
        endY = eY;
    }

    // initiates the BFS search based on the starting position

    public Node computePath(){

        boolean finished = false;
        Node foundNode = null;
        Node start = new Node(null, startX, startY);
        nodesToVisit.add(start);

        while(!finished) {
            if (!nodesToVisit.isEmpty()) {
                Node n = nodesToVisit.remove();

                // a temporary band aid fix for the bug in findFurtherValidNodes() which is causing duplicated nodes in the queue
                // which will crash the program in sparse maze setting.

                // what I really need is grid[newNode.y][newNode.x] = True copied over six times in that method.
                // or better yet just create a wrapper method to do it for me.

                if(!checkIfNodeAlreadyExplored(n.xPos,n.yPos)) {
                    foundNode = searchNodes(n);
                }

                if(foundNode != null){
                    finished = true;
                }

            } else {
                finished = true;
            }
        }

        if(foundNode == null){
            return null;
        } else {
            return foundNode;
        }
    }

    // along with iterate() below (I'm too used to Python letting me do nested methods),
    // this takes in the final node of the route and then retroactively draws a path on the grid.
    // Since the grid is represented as integers, 3 is end position, 4 is the start position, 2 is the path marker

    //returns modified grid, ready to be rendered

    public int[][] plotRoute(Node n){

        if(n == null){
            System.out.println("No possible route found");
            return grid;
        }

        grid[n.yPos][n.xPos] = 3;

        iterate(n.parent);

        return grid;

    }

    // see one method above.

    public void iterate(Node n){
        if(n.parent == null){
            grid[n.yPos][n.xPos] = 4;
        } else {
            grid[n.yPos][n.xPos] = 2;
            iterate(n.parent);
        }
    }

    public Node searchNodes(Node n){

        if(n.xPos == endX && n.yPos == endY){
            return n;
        } else {

        checkedNodes[n.yPos][n.xPos] = true;
        findFurtherValidNodes(n);

        }

        return null;

    }

    public void findFurtherValidNodes(Node n) {

        int x = n.xPos;
        int y = n.yPos;
        int gridXLen = grid[0].length - 1;
        int gridYLen = grid.length - 1;

        boolean checkForWarp = false;

        //check UP

        checkForWarp = y - 1 < 0;

        if (checkForWarp) {
            if (!checkIfNodeAlreadyExplored(x, gridYLen)) {
                checkForWarp = false;
                Node t = new Node(n, x, gridYLen);
                nodesToVisit.add(t);
            }
        } else {
            if (!checkIfNodeAlreadyExplored(x, y - 1)) {
                Node t = new Node(n, x, y - 1);
                nodesToVisit.add(t);
            }
        }

        //check DOWN

        checkForWarp = y + 1 > gridYLen;

        if (checkForWarp) {
            if (!checkIfNodeAlreadyExplored(x, 0)) {
                checkForWarp = false;
                Node t = new Node(n, x, 0);
                nodesToVisit.add(t);
            }
        } else {
            if (!checkIfNodeAlreadyExplored(x, y + 1)) {
                Node t = new Node(n, x, y + 1);
                nodesToVisit.add(t);
            }
        }

        //check LEFT

        checkForWarp = x - 1 < 0;

        if (checkForWarp) {
            if (!checkIfNodeAlreadyExplored(gridXLen, y)) {
                checkForWarp = false;
                Node t = new Node(n, gridXLen, y);
                nodesToVisit.add(t);
            }
        } else {
            if (!checkIfNodeAlreadyExplored(x - 1, y)) {
                Node t = new Node(n, x - 1, y);
                nodesToVisit.add(t);
            }
        }

        //check RIGHT

        checkForWarp = x + 1 > gridXLen;

        if (checkForWarp) {
            if (!checkIfNodeAlreadyExplored(0, y)) {
                checkForWarp = false;
                Node t = new Node(n, 0, y);
                nodesToVisit.add(t);
            }
        } else {
            if (!checkIfNodeAlreadyExplored(x + 1, y)) {
                Node t = new Node(n, x + 1, y);
                nodesToVisit.add(t);
            }
        }
    }

    private boolean checkIfNodeAlreadyExplored(int x, int y){

        return checkedNodes[y][x];

    }


    private class Node{

        Node parent = null;
        int xPos = 0;
        int yPos = 0;

        Node(Node p, int x, int y){
            parent = p;
            xPos = x;
            yPos = y;
        }
    }
}
