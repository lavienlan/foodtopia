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

public class Level2Intro {
    JFrame frame = new JFrame("Console");
    Drawing draw = new Drawing();
    Level2 a;
    Font diloWorldL, diloWorldS, pixeltype, dogicaB, dogicaBL;
    /*
    * constructor of level 2 introduction
    */
    public Level2Intro ()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        draw.addMouseListener(new ClickHandler());
        try {
            diloWorldL = Font.createFont(Font.TRUETYPE_FONT, new File("DiloWorld.ttf")).deriveFont(75f);
            diloWorldS = Font.createFont(Font.TRUETYPE_FONT, new File("DiloWorld.ttf")).deriveFont(40f);
            pixeltype = Font.createFont(Font.TRUETYPE_FONT, new File("Pixeltype.ttf")).deriveFont(75f);
            dogicaB = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(11f);
            dogicaBL = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(40f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("DiloWorld.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("Pixeltype.ttf")));
        }
        catch(IOException | FontFormatException e) {
        }
        frame.add(draw);
        frame.setVisible(true);
    }
    class ClickHandler extends MouseAdapter
    {
        public void mouseClicked (MouseEvent e)
        {
            a = new Level2();
            frame.dispose();
        }
    }
    class Drawing extends JComponent
    {
        public void paint (Graphics g)
        {
            //background
            frame.getContentPane().setBackground(new Color(106, 168, 79));
            //level name
            g.setFont(dogicaBL);
            g.setColor(Color.BLACK);
            g.drawString("Level 2",280,180);
            //ground
            g.setColor(new Color(182, 215, 168));
            g.fillOval(0, 300, 800, 100);
            g.fillRect(0, 350, 800, 150);
            //gravel
            g.setColor(new Color(238, 238, 238));
            int[] xs = {100, 375, 425, 700};
            int[] ys = {500, 300, 300, 500};
            g.fillPolygon(xs, ys, 4);
            //egg sun
            g.setColor(Color.white);
            g.fillOval(25, 25, 80,80);
            g.fillOval(20, 50, 20,30);
            g.fillOval(90, 50, 20,30);
            g.fillOval(50, 90, 30,20);
            g.fillOval(50, 20, 30,20);
            g.fillOval(40, 20, 30,30);
            g.fillOval(40, 80, 30,30);
            g.setColor(new Color(255, 217, 102));
            g.fillOval(45, 45, 40,40);
            //click anywhere
            g.setFont(dogicaB);
            g.setColor(new Color(0, 45, 56));
            g.drawString("Click anywhere to continue.", 280, 445);
        }
    }
    public static void main(String[] args) {
        new Level2Intro();
    }
}