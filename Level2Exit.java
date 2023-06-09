/** 
 * Course Info:
 * ICS4U0 with Krasteva, V.
 *
 * @version 05/29/2023
 * @author Monellie Ghaffari-Haghi
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Level2Exit {
    JFrame frame = new JFrame("Console");
    Drawing draw = new Drawing();
    Level3Intro a;
    Font dogicaBL, dogicaB;
    int score;
    /*
    * constructor of Level 2 exit
    */
    public Level2Exit (int scr)
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        try{
            dogicaBL = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(19f);
            dogicaB = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(11f);
        }
        catch(IOException | FontFormatException e) {
        }
        score = scr;
        draw.addMouseListener(new ClickHandler());
        frame.add(draw);
        frame.setVisible(true);
    }

    class ClickHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            try {
                AudioInputStream audioInputStream = AudioSystem
                        .getAudioInputStream(new File("click.wav").getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception ex) {
            }
            a = new Level3Intro();
            frame.dispose();
        }
    }

    class Drawing extends JComponent
    {
        public void paint (Graphics g)
        {
            int x = 0;
            int y = 0;
            int x1 = 0;
            int y1 = 0;
            int x2 = 0;
            int y2 = 0;
            //background
            frame.getContentPane().setBackground(new Color(106, 168, 79));
            //level name
            g.setFont(dogicaBL);
            g.setColor(Color.black);
            g.drawString("Score: " + score + "/3",300+x1,180+y1);
            //ground
            g.setColor(new Color(182, 215, 168));
            g.fillOval(0+x, 300+y, 800, 100);
            g.fillRect(0+x, 350+y, 800, 150);
            //gravel
            g.setColor(new Color(238, 238, 238));
            int[] xs = {100+x, 375+x, 425+x, 700+x};
            int[] ys = {500+y, 300+y, 300+y, 500+y};
            g.fillPolygon(xs, ys, 4);
            //egg sun
            g.setColor(Color.white);
            g.fillOval(25+x2, 25+y2, 80,80);
            g.fillOval(20+x2, 50+y2, 20,30);
            g.fillOval(90+x2, 50+y2, 20,30);
            g.fillOval(50+x2, 90+y2, 30,20);
            g.fillOval(50+x2, 20+y2, 30,20);
            g.fillOval(40+x2, 20+y2, 30,30);
            g.fillOval(40+x2, 80+y2, 30,30);
            g.setColor(new Color(255, 217, 102));
            g.fillOval(45+x2, 45+y2, 40,40);
            //click anywhere
            g.setFont(dogicaB);
            g.setColor(new Color(0, 45, 56));
            g.drawString("Click anywhere to continue.", 280, 445);
        }
    }
}