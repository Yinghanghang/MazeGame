/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazegame;


public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze(30,20);
        MazeGUI mazegui = new MazeGUI(maze);
        mazegui.render();

        /*for (int i = 0; i < maze.column; i++) {
            for (int j = 0; j < maze.row + 1; j++) {
                System.out.print(maze.hWall[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < maze.row; i++) {
            for (int j = 0; j < maze.column + 1; j++) {
                System.out.print(maze.vWall[i][j] + " ");
            }
            System.out.println();
        }*/
    }
}
