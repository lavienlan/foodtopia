import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;


public class NameInput {
    JFrame frame = new JFrame("Console");
    Drawing draw = new Drawing();
    String name;
    MainMenu a;
    public NameInput ()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        draw.addMouseListener(new ClickHandler());
        frame.add(draw);
        frame.setVisible(true);
        name = JOptionPane.showInputDialog(frame,"Enter Name");
        draw.repaint();
    }
    class ClickHandler extends MouseAdapter
    {
        public void mouseClicked (MouseEvent e)
        {
            if (name != null){
                frame.dispose();
                a = new MainMenu();
            }
        }
        public void mousePressed (MouseEvent e)
        {
            if (name != null){
                frame.dispose();
                a = new MainMenu();
            }
        }
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
