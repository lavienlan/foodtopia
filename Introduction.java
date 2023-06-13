/**
 * A class that represents the introduction screen of the game, displaying the logo and introductory text.
 *
 * <p>Course Info:
 * ICS4U0 with Krasteva, V.
 *
 * @version 06/09/2023
 * @author Monellie Ghaffari-Haghi
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Introduction {
    Font tommy, dogica_text;
    JFrame frame = new JFrame("Console");
    Drawing draw = new Drawing();
    boolean isTime = false;
    TitlePage a;
    BufferedImage logo;
    
    /**
     * Constructs an Introduction object and initializes the JFrame and components.
     */
    public Introduction ()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        frame.getContentPane().setBackground(Color.black);
        frame.add(draw);
        frame.setVisible(true);

        // Timer to dispose the frame after 3 seconds
        int delay = 3000; // 3 seconds
        Timer timer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                a = new TitlePage();
                frame.dispose();
            }
        });
        timer.setRepeats(false); // Execute only once
        timer.start();
    }
    
    /**
     * A custom JComponent class that represents the drawing area for the introduction screen.
     */
    class Drawing extends JComponent
    {
        /**
         * Constructs a Drawing object and loads the logo image.
         */
        public Drawing () {
            try {
                logo = ImageIO.read(getClass().getResourceAsStream("images/logologo.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Paints the graphics on the drawing area.
         *
         * @param g the Graphics object to paint on
         */
        public void paint (Graphics g)
        {

            try {
                tommy = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/tommy.ttf")).deriveFont(50f);
                dogica_text = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/dogicapixelbold.ttf")).deriveFont(12f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/DiloWorld.ttf")));
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/dogicapixelbold.ttf")));
            }
            catch(IOException | FontFormatException e) {
            }

            Graphics2D g2 = (Graphics2D)g;
            RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setRenderingHints(rh);
            g2.drawImage(logo, 140, -80, this);
            g.setFont(tommy);
            g.setColor(Color.white);
            g.drawString("Mister Studio Presents",120,300);

            g.setFont(dogica_text);
           // g.drawString("Copyright Mister Studio 2023", 250, 420);     
        }
    }
    
    /**
     * The main method that starts the Introduction application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        new Introduction();
    }
}