package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // *setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ---> This lets the window properly close when user clicks the close ("X") button.
        window.setResizable(false);
        window.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // *pack() ---> Causes this window to be sized to fit the preferred size and layouts of its subcompoents (=GamePanel).

        window.setLocationRelativeTo(null); // Not specify the location of the window = The window will be displayed at the center of the screen.
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
