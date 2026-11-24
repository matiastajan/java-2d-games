package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/*
This class inherites JPanel class.
*/

public class GamePanel extends JPanel implement Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set the size of this class (JPanel)
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // If set to true, all the drawing from this component will be done in an offscreen buffer
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public voud run() { // game loop

    }
}
