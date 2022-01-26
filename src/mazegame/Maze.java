/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazegame;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Maze {

    public boolean[][] hWall, vWall;
    public int column, row;
    private Stack<Cell> visit;
    private Bag<Cell> nonVisit;

    
    public Maze(int column, int row) {

        this.column = column;
        this.row = row;
 
        hWall = new boolean[column][row + 1];
        vWall = new boolean[row][column + 1];
        Cell cVisit = random();
        visit = new Stack<>();
        nonVisit = new Bag<>(10000);

        for (int i = 0; i < hWall.length; i++) {
            for (int j = 0; j < hWall[i].length; j++) {
                hWall[i][j] = true;
            }
        }

        for (int i = 0; i < vWall.length; i++) {
            for (int j = 0; j < vWall[i].length; j++) {
                vWall[i][j] = true;
            }
        }

        visit.push(cVisit);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i != cVisit.x || j != cVisit.y) {
                    Cell cNonVisit = new Cell();
                    cNonVisit.x = i;
                    cNonVisit.y = j;
                    nonVisit.add(cNonVisit);
                }
            }
        }

        while (!visit.isEmpty()) {

            Cell next = findNext();
            if (next != null) {
                // System.out.println(next);
                visit.push(nonVisit.remove(next));
            } else {
                // System.out.println("backtrack");
                Cell temp = visit.pop();
                if (visit.isEmpty()) {
                    break;
                }
                if (visit.peek().x == temp.x) {
                    if (visit.peek().y < temp.y) {
                        vWall[temp.x][temp.y] = false;
                    } else {
                        vWall[temp.x][temp.y + 1] = false;
                    }
                }
                if (visit.peek().y == temp.y) {
                    if (visit.peek().x < temp.x) {
                        hWall[temp.y][temp.x] = false;
                    } else {
                        hWall[temp.y][temp.x + 1] = false;
                    }
                }
            }

        }//end while loop

    }// end of constructor

    public Cell random() {

        Random random = new Random();
        int x = random.nextInt(row);
        int y = random.nextInt(column);
        Cell cell = new Cell();
        cell.x = x;
        cell.y = y;
        return cell;
    }
    
    private Cell findNext() {
        Cell[] adj = new Cell[4];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Cell();
        }
        Cell c = visit.peek();
        adj[0].x = c.x - 1;
        adj[0].y = c.y;
        adj[1].x = c.x + 1;
        adj[1].y = c.y;
        adj[2].x = c.x;
        adj[2].y = c.y - 1;
        adj[3].x = c.x;
        adj[3].y = c.y + 1;
        Random random = new Random();
        Collections.shuffle(Arrays.asList(adj), random);
        
        for (int i = 0; i < adj.length; i++) {
            if (nonVisit.contains(adj[i])) {
                return adj[i];
            }
        }
        return null;
    }
}
