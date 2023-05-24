/** 
 * Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 05/08/2023
 * @author Monellie Ghaffari-Haghi
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TitlePage// implements ActionListener
{
    JFrame frame = new JFrame("Console");
    Drawing draw = new Drawing();
    boolean isTime = false;
    NameInput a;

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
            frame.dispose();
            a = new NameInput();
        }
        public void mousePressed (MouseEvent e)
        {
            frame.dispose();
            a = new NameInput();
        }
    }

    /*public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == button)
        {
            frame.dispose();
            a = new NameInput();
        }
    }*/

    class Drawing extends JComponent
    {
        public void paint (Graphics g)
        {
            int x = 0;
            int y = 30;
            int x1 = 0;
            int y1 = 30;
            int x2 = 40;
            int y2 = 40;
            frame.getContentPane().setBackground(new Color(229, 250, 255));
            Font boldFont = new Font("Serif", Font.BOLD, 70);
            g.setFont(boldFont);
            g.setColor(Color.black);
            g.drawString("Foodtopia:",240+x1,180+y1);
            Font plainFont = new Font("Serif", Font.PLAIN, 60);
            g.setFont(plainFont);
            g.drawString("Pick Your Food",210+x1,280+y1);
            Graphics2D g2d;
            g2d = (Graphics2D) g;
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
            g.fillOval(635+x2, 180+x2, 30,30);
        }
    }
    public static void main(String[] args) {
        new TitlePage();
    }
}
