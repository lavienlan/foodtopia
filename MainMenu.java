import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.TexturePaint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;

public class MainMenu {
    Font diloWorldL, diloWorldS, pixeltype;
    JFrame frame;
    Color background = new Color(130,182,255);
    Color text = new Color (249,157,7);

    public MainMenu() {
        frame = new JFrame("Console");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.add(new Drawing());
        frame.setVisible(true);
    }

    class Drawing extends JComponent
    {
        

        public void paint (Graphics g) 
        {  
            Graphics2D g2d = (Graphics2D) g;
            try {
                diloWorldL = Font.createFont(Font.TRUETYPE_FONT, new File("DiloWorld.ttf")).deriveFont(75f);
                diloWorldS = Font.createFont(Font.TRUETYPE_FONT, new File("DiloWorld.ttf")).deriveFont(40f);
                pixeltype = Font.createFont(Font.TRUETYPE_FONT, new File("Pixeltype.ttf")).deriveFont(75f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("DiloWorld.ttf")));
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("Pixeltype.ttf")));
            }
            catch(IOException | FontFormatException e) {
            }

            frame.getContentPane().setBackground(background);
            BufferedImage gingham;
            try {
                gingham = ImageIO.read(new File("gingham pattern.jpg"));
                TexturePaint ginghamText = new TexturePaint(gingham, new Rectangle2D.Float(0, 0, 400, 400));
                g2d.setPaint(ginghamText);
                g.fillRect(0, 0, 800, 500);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            g.setColor(new Color(247,246,224));
            g.fillRect(40, 50, 700, 375);

            g.setFont(diloWorldL);

            //shadow
            g.setColor(Color.BLACK);
            g.drawString("Foodtopia", 200, 155);

            g.setColor(text);
            g.drawString("Foodtopia", 205, 150);

            g.setColor(Color.ORANGE);
            g.fillPolygon(new int[] {255, 470, 470}, new int[] {235, 200, 270}, 3);
            g.fillArc(260, 220, 20, 20, 90, 180);
            g.fillPolygon(new int[] {260, 495, 495}, new int[] {100, 20, 100}, 3);

            g.setColor(Color.BLUE);
            g.setFont(diloWorldS);
            g.drawString("NEW GAME", 280, 250);
            g.drawString("SOURCES", 290, 300);
            g.drawString("EXIT", 325, 350);

            
            frame.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.setTitle("X: " + e.getX() + "; Y: " + e.getY());
            }
        });
        }

    }

    public static void main (String [] args) {
        new MainMenu();
    }
}