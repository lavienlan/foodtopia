/** 
 * Course Info:
 * ICS4U0 with Krasteva, V.
 *
 * @version 05/08/2023
 * @author Monellie Ghaffari-Haghi
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Level3Intro {
    JFrame frame = new JFrame("Console");
    Font diloWorldL, diloWorldS, pixeltype, dogicaB;
    Drawing draw = new Drawing();
    Level3 a;
    /*
    * constructor of level 3 introduction
    */
    public Level3Intro ()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        frame.add(draw);
        draw.addMouseListener(new ClickHandler());
        frame.setVisible(true);
    }
    class ClickHandler extends MouseAdapter
    {
        public void mouseClicked (MouseEvent e)
        {
            a = new Level3();
            frame.dispose();
        }
    }
    class Drawing extends JComponent
    {
        public void paint (Graphics g)
        {
            int x = 0;
            int y = 0;
            int x2 = 335;
            int y2 = 240;
            //background
            frame.getContentPane().setBackground(new Color(246, 223, 181));
            try {
                diloWorldL = Font.createFont(Font.TRUETYPE_FONT, new File("DiloWorld.ttf")).deriveFont(75f);
                diloWorldS = Font.createFont(Font.TRUETYPE_FONT, new File("DiloWorld.ttf")).deriveFont(40f);
                pixeltype = Font.createFont(Font.TRUETYPE_FONT, new File("Pixeltype.ttf")).deriveFont(75f);
                dogicaB = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(11f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("DiloWorld.ttf")));
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("Pixeltype.ttf")));
            }
            catch(IOException | FontFormatException e) {
            }
            //level name
            g.setFont(diloWorldL);
            g.setColor(Color.BLACK);
            g.drawString("Level 3",285,180);
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
        }
    }
    public static void main(String[] args) {
        new Level3Intro();
    }
}