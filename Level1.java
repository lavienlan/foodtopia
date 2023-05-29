import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Image;
import java.io.*;

public class Level1 implements MouseListener {
    Font diloWorldL, diloWorldS, pixeltype;
    Color sky = new Color(169, 208, 245);
    JFrame frame;
    Graphics g;
    String character_select = "";

    public Level1() {
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
        if (x >= 20 && y >= 230 && y <= 330 && x <= 110) {
            character_select = "wally";
            System.out.println(character_select);
            frame.repaint();
        } else if (x >= 145 && y >= 230 && y <= 330 && x <= 245) {
            character_select = "rhonda";
            System.out.println(character_select);
        } else if (x >= 275 && y >= 205 && y <= 305 && x <= 375) {
            character_select = "darry";
            System.out.println(character_select);
        } else if (x >= 390 && y >= 220 && y <= 320 && x <= 490) {
            character_select = "barry";
            System.out.println(character_select);
        } else if (x >= 515 && y >= 205 && y <= 305 && x <= 615) {
            character_select = "carla";
            System.out.println(character_select);
        } else if (x >= 640 && y >= 250 && y <= 350 && x <= 740) {
            character_select = "larry";
            System.out.println(character_select);
        } //else if ()
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
        /*public Drawing() {
            setPreferredSize(new Dimension(800, 500));
            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    frame.setTitle("X: " + e.getX() + "; Y: " + e.getY());
                }
            });
        }*/
        private BufferedImage wally2;
        public Drawing() {
            try {
                wally2 = ImageIO.read(new File("characters/wally.png"));
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
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("DiloWorld.ttf")));
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("Pixeltype.ttf")));
            }
            catch(IOException | FontFormatException e) {
            }
            //background
            frame.getContentPane().setBackground(sky);
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

            // character imports
            // .getScaledInstance(200, 200, Image.SCALE_DEFAULT)
            Toolkit t = Toolkit.getDefaultToolkit();
            Image donna = t.getImage("characters/donna.png");
            g.drawImage(donna, 275, 205, this);
            Image carla = t.getImage("characters/carla.png");
            g.drawImage(carla, 515, 205, this);
            Image rhonda = t.getImage("characters/rhonda.png");
            g.drawImage(rhonda, 145, 240, this);
            Image barry = t.getImage("characters/barry.png");
            g.drawImage(barry, 390, 220, this);
            Image larry = t.getImage("characters/larry.png");
            g.drawImage(larry, 640, 250, this);
            Image wally = t.getImage("characters/wally.png");
            g.drawImage(wally, 20, 230, this);
            
            // magnifying glass cursor
            Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(getClass().getResource("images/magnifying glass.png")).getImage(), new Point(0, 0), "magnifying glass");
            setCursor(cursor);

            // textbox
            g.setColor(new Color(238, 238, 238));
            g2d.fillRoundRect(80, 350, 640, 110, 30, 30); 
            
            if (character_select.equals("wally")) {
                //background
                g.setColor(sky);
                g.fillRect(0, 0, 800, 500);
                //ground
                g.setColor(new Color(182, 215, 168));
                g2d.fillOval(0, 300, 800, 100);
                g.fillRect(0, 350, 800, 150);

                g.setFont(diloWorldL);
                g.setColor(Color.WHITE);
                g.drawString("Wally the Water", 50, 80);

                Image wallyScaled = wally2.getScaledInstance(350, 350, Image.SCALE_DEFAULT);
                g.drawImage(wallyScaled, 100, 100, this);
                
                g.setColor(Color.RED);
                //int [] x = {670, 680, 690, }
            }
        }
    }

    public void characterInfo () {
        g.drawRect(0, 0, 100, 100);
    }

    public static void main (String [] args) {
        //new Level1();
        SwingUtilities.invokeLater(Level1::new);
    }
}
    
 
