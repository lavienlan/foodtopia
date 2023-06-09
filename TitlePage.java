/** 
 * Course Info:
 * ICS4U0 with Krasteva, V.
 *
 * @version 06/09/2023
 * @author Monellie Ghaffari-Haghi and Rachel Jing
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class TitlePage
{
    Font tommy, dogica_text, diloWorldL, dogicaB;
    BufferedImage donna, barry;
    JFrame frame = new JFrame("Console");
    Drawing draw = new Drawing();
    boolean isTime = false;
    MainMenu a;

    /*
    * constructor of title page
    */
    public TitlePage ()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        draw.addMouseListener(new ClickHandler());
        frame.add(draw);
        frame.setVisible(true);
    }
    
    class ClickHandler extends MouseAdapter
    {
        public void mouseClicked (MouseEvent e)
        {
            a = new MainMenu();
            frame.dispose();
        }
    }

    class Drawing extends JComponent
    {
        public Drawing() {
            try {
                donna = ImageIO.read(new File("characters/donna.png"));
                barry = ImageIO.read(new File("characters/barry.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 

        public void paint (Graphics g)
        {
            try {
                diloWorldL = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/DiloWorld.ttf")).deriveFont(75f);
                dogica_text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/dogicapixelbold.ttf")).deriveFont(12f);
                dogicaB = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/dogicapixelbold.ttf")).deriveFont(25f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }
        
            int x1 = 0;
            int y1 = 30;
            frame.getContentPane().setBackground(new Color(237, 202, 252));
            g.setFont(diloWorldL);
            g.setColor(Color.black);
            g.drawString("Foodtopia:",220+x1,190+y1);
            g.setFont(dogicaB);
            g.drawString("Pick Your Food",240+x1,250+y1);

            Image donnaScaled = donna.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
            Image barryScaled = barry.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
            g.drawImage(donnaScaled, 0, 120, this);
            g.drawImage(barryScaled, 580, 120, this);

            /* 
            g.setColor(new Color(176, 127, 85));
            g2d.fillRoundRect(20+x, 140+y, 140, 140, 30, 30);
            g.setColor(new Color(251, 247, 225));
            g2d.fillRoundRect(30+x, 150+y, 120, 120, 30, 30);
            g.setColor(new Color(176, 127, 85));
            g.fillOval(20+x, 109+y, 140, 45);
            g.setColor(new Color(251, 247, 225));
            g.fillOval(30+x, 120+y, 120, 35);
            g.setColor(new Color(255, 217, 102));
            int[] xs = {580+x2, 720+x2, 650+x2};
            int[] ys = {110+y2, 110+y2, 260+y2};
            g.fillPolygon(xs, ys, 3);
            g.setColor(new Color(230, 188, 152));
            g2d.fillRoundRect(570+x2, 90+y2, 160, 30, 20, 20);
            g.setColor(new Color(204, 0, 0));
            g.fillOval(610+x2, 130+x2, 30,30);
            g.fillOval(655+x2, 145+x2, 30,30);
            g.fillOval(635+x2, 180+x2, 30,30);*/

            g.setFont(dogica_text);
            g.drawString("Click anywhere to continue.", 260, 440);  
        }
    }
    public static void main(String[] args) {
        new TitlePage();
    }
}
