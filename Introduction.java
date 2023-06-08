/** 
 * Course Info:
 * ICS4U0 with Krasteva, V.
 *
 * @version 05/08/2023
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
    /*
    * constructor of introduction
    */
    public Introduction ()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        frame.getContentPane().setBackground(Color.black);
        frame.add(draw);
        draw.addMouseListener(new ClickHandler());
        frame.setVisible(true);
    }
    class ClickHandler extends MouseAdapter
    {
        public void mouseClicked (MouseEvent e)
        {
            frame.dispose();
            a = new TitlePage();
        }
        
        public void mousePressed (MouseEvent e)
        {
            frame.dispose();
            a = new TitlePage();
        }
    }
    
    class Drawing extends JComponent
    {
        public Drawing () {
            try {
                logo = ImageIO.read(new File("images/logologo.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void paint (Graphics g)
        {

            try {
                tommy = Font.createFont(Font.TRUETYPE_FONT, new File("tommy.ttf")).deriveFont(50f);
                dogica_text = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(12f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("DiloWorld.ttf")));
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("Pixeltype.ttf")));
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
            g.drawString("Click anywhere to continue", 250, 420);
            

            //Image logoScaled = logo.getScaledInstance(600, 600, Image.SCALE_DEFAULT);
            
        }
    }
    public static void main(String[] args) {
        new Introduction();
    }
}