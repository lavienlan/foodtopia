/** 
 * This class draws the introduction screen for level 2
 * Course Info:
 * ICS4U0 with Krasteva, V.
 *
 * @version 06/09/2023
 * @author Monellie Ghaffari-Haghi
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Level2Intro {
    JFrame frame = new JFrame("Console");
    Drawing draw = new Drawing();
    Level2 a;
    Font diloWorldL, diloWorldS, dogicaB, dogicaBL;
    
    /**
     * Constructor for Level2Intro.
     * Initializes the frame and sets up the drawing component.
     */
    public Level2Intro ()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        draw.addMouseListener(new ClickHandler());
        try {
            dogicaB = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/dogicapixelbold.ttf")).deriveFont(11f);
            dogicaBL = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/dogicapixelbold.ttf")).deriveFont(35f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        }
        catch(IOException | FontFormatException e) {
        }
        frame.add(draw);
        frame.setVisible(true);
    }

    /**
     * MouseAdapter to handle mouse click events.
     */
    class ClickHandler extends MouseAdapter {
        /**
         * Invoked when a mouse button has been clicked (pressed and released).
         * Plays a click sound and creates an instance of Level2.
         * Closes the current frame.
         * @param e The MouseEvent representing the mouse click event.
         */
        public void mouseClicked(MouseEvent e) {
            try {
                AudioInputStream audioInputStream = AudioSystem
                        .getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("sounds/click.wav")));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception ex) {
            }
            a = new Level2();
            frame.dispose();
        }
    }

    /**
     * A custom JComponent class that represents the drawing area for the level 2 intro screen.
     */
    class Drawing extends JComponent
    {
        /**
         * Paints the graphics for the drawing area.
         * @param g The Graphics object used for rendering.
         */
        public void paint (Graphics g)
        {
            //background
            frame.getContentPane().setBackground(new Color(106, 168, 79));
            //level name
            g.setFont(dogicaBL);
            g.setColor(Color.BLACK);
            g.drawString("Level 2: The Corn Maze",75,190);
            g.drawString("(Maze Level)", 225, 260);
            //ground
            g.setColor(new Color(182, 215, 168));
            g.fillOval(0, 300, 800, 100);
            g.fillRect(0, 350, 800, 150);
            //gravel
            g.setColor(new Color(238, 238, 238));
            int[] xs = {100, 375, 425, 700};
            int[] ys = {500, 300, 300, 500};
            g.fillPolygon(xs, ys, 4);
            
            // Draw egg sun
            g.setColor(Color.WHITE);
            g.fillOval(80, 50, 90, 90);
            g.fillOval(75, 10, 90, 90);
            g.fillOval(30, 25, 90, 90);
            g.fillOval(30, 50, 90, 90);
            g.setColor(new Color(255, 217, 102));
            g.fillOval(65, 45, 60, 60);

            //click anywhere
            g.setFont(dogicaB);
            g.setColor(new Color(0, 45, 56));
            g.drawString("Click anywhere to continue.", 280, 445);
        }
    }
    
    /**
     * The entry point of the program.
     * Creates an instance of Level2Intro to start the application.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Level2Intro();
    }
}