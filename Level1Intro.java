/** 
 * This class creates an introduction screen to the level
 * Course Info:
 * ICS4U0 with Krasteva, V.
 *
 * @version 06/09/2023
 * @author Monellie Ghaffari-Haghi
 */

import java.awt.*;
import java.awt.event.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import java.io.*;

public class Level1Intro {
    JFrame frame = new JFrame("Console");
    Font dogicaB, dogicaBL;
    Drawing draw = new Drawing();
    Level1 a;

    /**
     * Constructor for Level1Intro.
     * Initializes the frame and sets up the drawing component.
     */
    public Level1Intro() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.add(draw);
        draw.addMouseListener(new ClickHandler());
        frame.setVisible(true);
    }

    /**
     * MouseAdapter to handle mouse click events.
     */
    class ClickHandler extends MouseAdapter {
        /**
         * Invoked when a mouse button has been clicked (pressed and released).
         * Plays a click sound and creates an instance of Level1.
         * Closes the current frame.
         * @param e The MouseEvent representing the mouse click event.
         */
        public void mouseClicked(MouseEvent e) {
            try {
                // Play click sound
                AudioInputStream audioInputStream = AudioSystem
                        .getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("click.wav")));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception ex) {
                 // Handle exception if audio cannot be played
            }
             // Create an instance of Level1
            a = new Level1();
            // Close the current frame
            frame.dispose();
        }
    }

    /**
     * A custom JComponent class that represents the drawing area for the level 1 introduction screen.
     */
    class Drawing extends JComponent {
        /**
         * Paints the graphics for the drawing area.
         * @param g The Graphics object used for rendering.
         */
        public void paint(Graphics g) {
            try {
                dogicaB = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("dogicapixelbold.ttf")).deriveFont(11f);
                dogicaBL = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("dogicapixelbold.ttf")).deriveFont(30f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            } catch (IOException | FontFormatException e) {
            }
            
            // Set background color
            frame.getContentPane().setBackground(new Color(229, 250, 255));
            
            // Draw level name
            g.setFont(dogicaBL);
            g.setColor(Color.BLACK);
            g.drawString("Level 1: Meet and Greet", 100, 195);
            g.drawString("(Learning Level)", 170, 260);

            // Draw ground
            g.setColor(new Color(182, 215, 168));
            g.fillOval(0, 300, 800, 100);
            g.fillRect(0, 350, 800, 150);

            // Draw gravel
            g.setColor(new Color(238, 238, 238));
            int[] xs = { 100, 375, 425, 700 };
            int[] ys = { 500, 300, 300, 500 };
            g.fillPolygon(xs, ys, 4);

            // Draw egg sun
            g.setColor(Color.WHITE);
            g.fillOval(80, 50, 90, 90);
            g.fillOval(75, 10, 90, 90);
            g.fillOval(30, 25, 90, 90);
            g.fillOval(30, 50, 90, 90);
            g.setColor(new Color(255, 217, 102));
            g.fillOval(65, 45, 60, 60);
            
            // Draw click anywhere text
            g.setFont(dogicaB);
            g.setColor(new Color(0, 45, 56));
            g.drawString("Click anywhere to continue.", 280, 445);
        }
    }

    /**
     * The main method that creates an instance of Level1Intro.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Level1Intro();
    }
}
