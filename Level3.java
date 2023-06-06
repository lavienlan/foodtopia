import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;

public class Level3 implements MouseListener{
    Font diloWorldL, diloWorldS, diloWorldSS, pixeltype, dogicaB, dogicaBM;
    BufferedImage baguette;
    JFrame frame;
    Graphics g;

    int instructionPoint = 0;

    public Level3() {
        frame = new JFrame("Console");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.getContentPane().add(new Drawing());
        frame.addMouseListener(this);
        frame.setVisible(true);
    }

    @Override
    public void mouseClicked (MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (instructionPoint < 5 ) {
            frame.repaint();
            instructionPoint++;
        }
    }

    @Override
    public void mousePressed (MouseEvent e) {
    }

    @Override
    public void mouseReleased (MouseEvent e) {
    }

    @Override
    public void mouseEntered (MouseEvent e) {
    }

    @Override
    public void mouseExited (MouseEvent e) {
    }

    class Drawing extends JComponent {

        public Drawing() {
            try {
                baguette = ImageIO.read(new File("characters/bad baguette.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            try {
                diloWorldL = Font.createFont(Font.TRUETYPE_FONT, new File("DiloWorld.ttf")).deriveFont(75f);
                diloWorldS = Font.createFont(Font.TRUETYPE_FONT, new File("DiloWorld.ttf")).deriveFont(40f);
                pixeltype = Font.createFont(Font.TRUETYPE_FONT, new File("Pixeltype.ttf")).deriveFont(75f);
                dogicaBM = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(11f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("DiloWorld.ttf")));
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("Pixeltype.ttf")));
            }
            catch(IOException | FontFormatException e) {
            }
            
            Color sky = new Color(169, 208, 245);
            g.setColor(sky);
            g.fillRect(0, 0, 800, 500);

            //ground
            g.setColor(new Color(182, 215, 168));
            g2d.fillOval(0, 300, 800, 100);
            g.fillRect(0, 350, 800, 150);

            //egg sun
            g.setColor(Color.WHITE);
            g.fillOval(55, 50, 60, 60);
            g.fillOval(50, 20, 60, 60);
            g.fillOval(20, 30, 60, 60);
            g.fillOval(20, 50, 60, 60);

            //yolk
            g.setColor(new Color(255, 217, 102));
            g.fillOval(45, 45, 40,40);

            g.drawImage(baguette, 0, 0, this);

        }
    }

}
