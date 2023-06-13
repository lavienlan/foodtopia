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
                donna = ImageIO.read(getClass().getResourceAsStream("donna.png"));
                barry = ImageIO.read(getClass().getResourceAsStream("barry.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 

        public void paint (Graphics g)
        {
            try {
                diloWorldL = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("DiloWorld.ttf")).deriveFont(75f);
                dogica_text = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("dogicapixelbold.ttf")).deriveFont(12f);
                dogicaB = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("dogicapixelbold.ttf")).deriveFont(25f);
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
            g.drawString("Pick Your Food!",230+x1,250+y1);

            Image donnaScaled = donna.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
            Image barryScaled = barry.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
            g.drawImage(donnaScaled, 0, 120, this);
            g.drawImage(barryScaled, 580, 120, this);

            g.setFont(dogica_text);
            g.drawString("Sound on!", 350, 425);
            g.drawString("Click anywhere to continue.", 250, 445);  
        }
    }
    public static void main(String[] args) {
        new TitlePage();
    }
}
