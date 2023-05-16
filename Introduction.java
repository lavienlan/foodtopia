import java.awt.*;
import javax.swing.*;

public class Introduction {
    JFrame frame = new JFrame("Tricolour Buttons");
    public Introduction ()
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