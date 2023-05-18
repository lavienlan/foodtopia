import java.awt.*;
import javax.swing.*;

public class Level1Intro {
    JFrame frame = new JFrame("Console");
    public Level1Intro ()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        frame.add(new Drawing());
        frame.setVisible(true);
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
            frame.getContentPane().setBackground(new Color(229, 250, 255));
            //level name
            Font boldFont = new Font("Serif", Font.PLAIN, 70);
            g.setFont(boldFont);
            g.setColor(Color.black);
            g.drawString("Level 1",240+x1,180+y1);
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
        }
    }
    public static void main(String[] args) {
        new Level1Intro();
    }
}
