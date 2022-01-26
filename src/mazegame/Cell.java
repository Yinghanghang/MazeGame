/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazegame;

//(x,y) to represent a cell

public class Cell {

    int x;
    int y;
    
    @Override
    public boolean equals(Object obj) {
        Cell c = (Cell) obj;
        return c.x == x && c.y == y;
    }
    
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
