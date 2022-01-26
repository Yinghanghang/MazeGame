/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;



public class MazeGUI extends JPanel {
    private static final int UNIT = 10;
    private int [][] maze;
    private int playerX;
    private int playerY;
    private int goldX;
    private int goldY;

    MazeGUI(Maze mazeObj) {
        maze = new int[mazeObj.row * 2 + 1][mazeObj.column * 2 + 1];// convert the mazeObj dimension to a new maze with doube column and row
        initWall(mazeObj);
        initGUI();
        initLocation();
    }

    private void initWall(Maze mazeObj) { // initial every element of the maze[][]
        for (int col = 0; col < mazeObj.hWall.length; col++) { //convert the coordiantes of hWall to corresponding coordinates of pixel
            for (int row = 0; row < mazeObj.hWall[col].length; row++) {
                if (mazeObj.hWall[col][row]) { //check if the hWall is true and then set the correspoding pixel to 1
                    maze[row * 2][col * 2] = 1;
                    maze[row * 2][col * 2 + 1] = 1;
                    maze[row * 2][col * 2 + 2] = 1;
                }
            }
        }
        for (int row = 0; row < mazeObj.vWall.length; row++) { //convert the coordiantes of vWall to corresponding coordinates of pixel
            for (int col = 0; col < mazeObj.vWall[row].length; col++) {
                if (mazeObj.vWall[row][col]) { //check if the vWall is true
                    maze[row * 2][col * 2] = 1;
                    maze[row * 2 + 1][col * 2] = 1;
                    maze[row * 2 + 2][col * 2] = 1;
                }
            }
        }
    }
    
    private void initLocation() { //set up the location of player and gold
        Random random = new Random();
        while (true) { //place the player in the maze 
            playerX = random.nextInt(maze[0].length);
            playerY = random.nextInt(maze.length);
            if (maze[playerY][playerX] == 0) { // make sure it is not placed in a wall
                break;
            }
        }
        while(true){ //place the gold in the maze
        goldX = random.nextInt(maze[0].length);
        goldY = random.nextInt(maze.length);
        if (maze[goldY][goldX] == 0) {
                break;
            }
        }
    }

    private void initGUI() {
        JFrame frame = new JFrame();
        frame.setTitle("Maze Game");
        frame.setSize(maze[0].length * UNIT + 75, maze.length * UNIT + 95);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(makeMenuBar());
        frame.add(this);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {

                if (ke.getKeyCode() == KeyEvent.VK_LEFT) { //go to the left cell if there is a path
                    if (playerX > 0 && maze[playerY][playerX - 1] == 0) //g.cleaOval(playerX * UNIT, playerY * UNIT, UNIT, UNIT);
                    {
                        playerX--;
                    }

                } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) { // go to the right cell if there is a path
                    if (playerX < maze[0].length && maze[playerY][playerX + 1] == 0) {
                        playerX++;
                    }
                } else if (ke.getKeyCode() == KeyEvent.VK_UP) { // go to the higher cell if there is a path
                    if (playerY > 0 && maze[playerY - 1][playerX] == 0) {
                        playerY--;
                    }
                } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) { // go the the lower cell if there is a path
                    if (playerY < maze.length && maze[playerY + 1][playerX] == 0) {
                        playerY++;
                    }
                }

                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });
        frame.setVisible(true);
    }
    
    private JMenuBar makeMenuBar() { // maze menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorder(null);
        JMenu mazeMenu = new JMenu("Maze");
        mazeMenu.setBorderPainted(false);
        mazeMenu.getPopupMenu().setBorder(null);
        menuBar.add(mazeMenu);

        // Reset the maze
        final JMenuItem resetItem = new JMenuItem("Reset");
        resetItem.setBorderPainted(false);
        resetItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Maze mazeNew = new Maze(30,20);
                maze = new int[mazeNew.row * 2 + 1][mazeNew.column * 2 + 1];               
                initWall(mazeNew);
                initLocation();
                repaint();
                resetItem.setEnabled(true);
            }

        });
        mazeMenu.add(resetItem);
        return menuBar;
    }

    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        g.translate(25, 25);

        // draw the maze
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                Color color = maze[row][col] == 1 ? Color.BLACK : Color.WHITE;
                g.setColor(color);
                g.fillRect(UNIT * col, UNIT * row, UNIT, UNIT);//fill the cell
                g.setColor(Color.BLACK);
                g.drawRect(UNIT * col, UNIT * row, UNIT, UNIT);//draw the frame of the cell
            }
        }

        // draw the player and gold
        g.setColor(Color.BLUE);
        g.fillOval(playerX * UNIT, playerY * UNIT, UNIT, UNIT);//draw the player
        g.setColor(Color.RED);
        g.fillOval(goldX * UNIT, goldY * UNIT, UNIT, UNIT);//draw the gold
    }



    public void render() {
        setVisible(true);
    }

}
