package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/*
This class inherites JPanel class.
*/

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // *setPreferredSize(new Dimension(width, height)) ---> Sets the size of this class (JPanel).
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // *setDoubleBuffered(true) ---> If set to true, all the drawing from this component will be done in an offscreen painting buffer.
        this.addKeyListener(keyH);
        this.setFocusable(true); // *setFocusable(true) --> With this, this GamePanel can be "focused" to recive key input.
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() { // game loop
        while(gameThread != null) {
            // System.out.println("The game loop is running");

            // 1 UPDATE: update information such as character positions
            update();
            // 2 DRAW: draw the screen with the updated information
            repaint();
        }
    }
    public void update() {

    }
    public void paintComponent(Graphics g) { // *Graphics ---> A class that has many fuctions to draw objects on the screen
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; // *Graphics2D ---> This class extends the Graphics class to provide more sophisticated control over geometry, coordinate transformations, color management, and text layout.

        g2.setColor(Color.white); // *setColor(Color c) ---> Sets a color to use for drawing objects.

        g2.fillRect(100, 100, tileSize, tileSize); // *fillRect(int x, int y, int width, int height) ---> Draws a rectable and fills it with the specified color.

        g2.dispose(); // *dispose() ---> Dispose of this graphics context and release any system resources that it is using.
    }
}
