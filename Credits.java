/**
 * A class that represents the credits screen, displaying acknowledgments and game information.
 * 
 * <p>Course Info:
 * ICS4U0 with Krasteva, V.
 *
 * @version 06/09/2023
 * @author Rachel Jing
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Credits {
    JFrame frame = new JFrame("Console");
    Drawing draw = new Drawing();
    Font diloWorldL, diloWorldS, dogicaB, dogicaBL;
    
    /**
     * Constructs a Credits object and initializes the JFrame and components.
     */
    public Credits() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        draw.addMouseListener(new ClickHandler());
        try {
            dogicaB = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("dogicapixelbold.ttf")).deriveFont(30f);
            dogicaBL = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("dogicapixelbold.ttf")).deriveFont(16f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        } catch (IOException | FontFormatException e) {
        }
        frame.add(draw);
        frame.setVisible(true);

        // Timer to dispose the frame after 4 seconds
        int delay = 4000; // 4 seconds
        Timer timer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });
        timer.setRepeats(false); // Execute only once
        timer.start();
    }

    /**
     * A mouse adapter class that handles mouse click events on the drawing area.
     */
    class ClickHandler extends MouseAdapter {
        /**
         * Invoked when the mouse is clicked. Plays a click sound.
         * 
         * @param e the MouseEvent representing the mouse click event
         */
        public void mouseClicked(MouseEvent e) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("click.wav")));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception ex) {
            }
        }
    }

    /**
     * A custom JComponent class that represents the drawing area for the credits screen.
     */
    class Drawing extends JComponent {
        /**
         * Paints the graphics on the drawing area.
         * 
         * @param g the Graphics object to paint on
         */
        public void paint(Graphics g) {
            // background
            frame.getContentPane().setBackground(new Color(255, 204, 228));

            g.setFont(dogicaB);
            g.setColor(Color.BLACK);
            g.drawString("Thank you for playing!", 110, 130);
            g.setFont(dogicaBL);
            g.drawString("Game Presented by Mister Studio", 160, 190);
            g.drawString("Created by Monellie Ghaffari-Haghi and Rachel Jing", 40, 230);
            g.drawString("June 2023 ICS4U0 with V. Krasteva", 160, 270);

            // ground
            g.setColor(new Color(182, 215, 168));
            g.fillOval(0, 300, 800, 100);
            g.fillRect(0, 350, 800, 150);
            // gravel
            g.setColor(new Color(238, 238, 238));
            int[] xs = { 100, 375, 425, 700 };
            int[] ys = { 500, 300, 300, 500 };
            g.fillPolygon(xs, ys, 4);
            // egg sun
            g.setColor(Color.white);
            g.fillOval(25, 25, 80, 80);
            g.fillOval(20, 50, 20, 30);
            g.fillOval(90, 50, 20, 30);
            g.fillOval(50, 90, 30, 20);
            g.fillOval(50, 20, 30, 20);
            g.fillOval(40, 20, 30, 30);
            g.fillOval(40, 80, 30, 30);
            g.setColor(new Color(255, 217, 102));
            g.fillOval(45, 45, 40, 40);
        }
    }

    /**
     * The main method that starts the Credits application.
     * 
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        new Credits();
    }
}
