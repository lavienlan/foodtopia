import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Introduction {
    JFrame frame = new JFrame("Console");
    Drawing draw = new Drawing();
    boolean isTime = false;
    TitlePage a;
    public Introduction ()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        draw.addMouseListener(new ClickHandler());
        frame.add(draw);
        frame.setVisible(true);
        try {
            while (!isTime) {
                System.out.println(isTime);
                Thread.sleep((long)10000);
            }
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }
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
    public boolean isItTime(){
        return isTime;
    }
    class Drawing extends JComponent
    {
        public void paint (Graphics g)
        {
            
            frame.getContentPane().setBackground(Color.black);
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