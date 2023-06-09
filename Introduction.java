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
                tommy = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/tommy.ttf")).deriveFont(50f);
                dogica_text = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/dogicapixelbold.ttf")).deriveFont(12f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("fonts/DiloWorld.ttf")));
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("fonts/dogicapixelbold.ttf")));
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
    public static void main(String[] args) {
        new Introduction();
    }
}