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

    // FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // Set player's default postiton
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

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

    // public void run() { // game loop

    //     double drawInterval = 1000000000 / FPS; // 0.1666 seconds
    //     double nextDrawTime = System.nanoTime() + drawInterval; // *System.nanoTime() ---> Returns the current value of the running Java Virtual Machine's high-resultion time source, in nanoseconds.


    //     while(gameThread != null) {
    //         update();

    //         repaint();

    //         try {
    //             double reamainingTime = nextDrawTime - System.nanoTime();
    //             reamainingTime = reamainingTime / 1000000;

    //             if(reamainingTime < 0) {
    //                 reamainingTime = 0;
    //             }

    //             Thread.sleep((long) reamainingTime);

    //             nextDrawTime += drawInterval;
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        double lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update() {
        if(keyH.upPressed == true) {
            playerY -= playerSpeed;
        } else if(keyH.downPressed == true) {
            playerY += playerSpeed;
        } else if(keyH.leftPressed == true) {
            playerX -= playerSpeed;
        } else if(keyH.rightPressed == true) {
            playerX += playerSpeed;
        }
    }
    public void paintComponent(Graphics g) { // *Graphics ---> A class that has many fuctions to draw objects on the screen
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; // *Graphics2D ---> This class extends the Graphics class to provide more sophisticated control over geometry, coordinate transformations, color management, and text layout.

        g2.setColor(Color.white); // *setColor(Color c) ---> Sets a color to use for drawing objects.

        g2.fillRect(playerX, playerY, tileSize, tileSize); // *fillRect(int x, int y, int width, int height) ---> Draws a rectable and fills it with the specified color.

        g2.dispose(); // *dispose() ---> Dispose of this graphics context and release any system resources that it is using.
    }
}
