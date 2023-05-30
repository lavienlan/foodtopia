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

public class Introduction {
    JFrame frame = new JFrame("Console");
    Drawing draw = new Drawing();
    boolean isTime = false;
    TitlePage a;
    /*
    * constructor of introduction
    */
    public Introduction ()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        frame.getContentPane().setBackground(Color.black);
        //start logo
        JLabel lbl = new JLabel();
        lbl.setBounds(260, 70, 280, 280);
        ImageIcon icon = new ImageIcon("images/logologo.png");
        Image img1 = icon.getImage();
        Image newImg = img1.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        lbl.setIcon(newImc);
        frame.add(lbl);
        //logo has been added
        //add draw
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
        public void paint (Graphics g)
        {
            Font largeSerifFont = new Font("Serif", Font.PLAIN, 40);
            g.setFont(largeSerifFont);
            g.setColor(Color.white);
            g.drawString("Mister Studio Presents",230,280);
        }
    }
    public static void main(String[] args) {
        new Introduction();
    }
}