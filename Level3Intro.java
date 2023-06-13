/** 
 * This class draws the introduction screen for level 3
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

public class Level3Intro {
    JFrame frame = new JFrame("Console");
    Font diloWorldL, diloWorldS, dogicaB, dogicaBL;
    Drawing draw = new Drawing();
    Level3 a;
    
    /**
     * Constructor of Level3Intro
     */
    public Level3Intro ()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        frame.add(draw);
        draw.addMouseListener(new ClickHandler());
        frame.setVisible(true);
    }

    /**
     * Mouse click handler
     */
    class ClickHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("sounds/sounds/click.wav")));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception ex) {
            }
            a = new Level3();
            frame.dispose();
        }
    }

    /**
     * Custom drawing component.
     */
    class Drawing extends JComponent
    {
        public void paint (Graphics g)
        {
            int x2 = 310;
            int y2 = 210;
            //background
            frame.getContentPane().setBackground(new Color(246, 223, 181));
            try {
                dogicaB = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/dogicapixelbold.ttf")).deriveFont(11f);
                dogicaBL = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/dogicapixelbold.ttf")).deriveFont(35f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            }
            catch(IOException | FontFormatException e) {
            }
            // Draw egg sun
            g.setColor(Color.WHITE);
            g.fillOval(80+x2, 50+y2, 90, 90);
            g.fillOval(75+x2, 10+y2, 90, 90);
            g.fillOval(30+x2, 25+y2, 90, 90);
            g.fillOval(30+x2, 50+y2, 90, 90);
            g.setColor(new Color(255, 217, 102));
            g.fillOval(65+x2, 45+y2, 60, 60);
            //ground
            g.setColor(new Color(182, 215, 168));
            g.fillOval(0, 300, 800, 100);
            g.fillRect(0, 350, 800, 150);
            //gravel
            g.setColor(new Color(238, 238, 238));
            int[] xs = {100, 375, 425, 700};
            int[] ys = {500, 300, 300, 500};
            g.fillPolygon(xs, ys, 4);
            //click anywhere
            g.setFont(dogicaB);
            g.setColor(new Color(0, 45, 56));
            g.drawString("Click anywhere to continue.", 280, 445);

            //level name
            g.setFont(dogicaBL);
            g.setColor(Color.BLACK);
            g.drawString("Level 3: Pick Your Food!",45,180);
            g.drawString("(Escape Level)", 170, 260);
        }
    }

    /*
     * Main method of class
     */
    public static void main(String[] args) {
        new Level3Intro();
    }
}