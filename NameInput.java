import java.awt.*;
import javax.swing.*;


public class NameInput {
    JFrame frame = new JFrame("Console");
    Drawing draw = new Drawing();
    String name;
    public NameInput ()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        frame.add(draw);
        frame.setVisible(true);
        name = JOptionPane.showInputDialog(frame,"Enter Name");
        draw.repaint();
    }
    public String getName() {
        return name;
    }
    class Drawing extends JComponent
    {
        public void paint (Graphics g)
        {
            int x = 0;
            int y = -140;
            frame.getContentPane().setBackground(new Color(229, 250, 255));
            Font largeSerifFont = new Font("Serif", Font.PLAIN, 40);
            g.setFont(largeSerifFont);
            g.setColor(Color.black);
            g.drawString("Hello Diner!",280+x,200+y);
            g.drawString("What is your name?",230+x,250+y);
            if (name != null){
                g.drawString("Hello, " + name, 300, 300);
            }
        }
    }
    public static void main(String[] args) {
        new NameInput();
    }
}
