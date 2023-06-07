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

    String instruction = "";
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
        if (instructionPoint < 5 ) {
            frame.repaint();
            instructionPoint++;
        } else {
            frame.dispose();
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
                dogicaB = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(9f);
                dogicaBM = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(14f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("DiloWorld.ttf")));
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("Pixeltype.ttf")));
            }
            catch(IOException | FontFormatException e) {
            }

            Color sky = new Color(169, 208, 245);
            g.setColor(sky);
            g.fillRect(0, 0, 800, 500);

            // ground
            g.setColor(new Color(182, 215, 168));
            g.fillRect(0, 320, 800, 200);

            // egg sun
            g.setColor(Color.WHITE);
            g.fillOval(80, 70, 90, 90);
            g.fillOval(75, 30, 90, 90);
            g.fillOval(30, 45, 90, 90);
            g.fillOval(30, 70, 90, 90);
            
            // yolk
            g.setColor(new Color(255, 217, 102));
            g.fillOval(65, 65, 60, 60);

        
            // bread portal
            g.setColor(new Color(176,127,85));
            g.fillRoundRect(250, 170, 300, 230, 20, 20);
            g.fillOval(220, 80, 360, 150);

            g.setColor(new Color(251,247,225));
            g.fillRoundRect(270, 190, 260, 190, 20, 20);
            g.fillOval(245, 100, 310, 120);
            Image bagScaled = baguette.getScaledInstance(400, 400, Image.SCALE_DEFAULT);
            g.drawImage(bagScaled, 205, 100, this);

            g.setColor(new Color(247, 184, 82));
            g2d.fillRoundRect(530, 80, 200, 110, 30, 30); 
            int [] xPoly = {550, 575, 630};
            int [] yPoly = {180, 210, 180};
            Polygon tri = new Polygon (xPoly, yPoly, xPoly.length);
            g.fillPolygon(tri);


            g.setColor(new Color(176,127,85));
            g.fillRoundRect(60, 250, 130, 80, 20, 20);
            g.fillRoundRect(117, 300, 15, 80, 10, 10);
            g.setFont(dogicaBM);
            g.setColor(Color.WHITE);
            g.drawString("go back", 78, 285);
            g.drawString("home ->", 85, 308);

            if (instructionPoint == 0) {
                instruction = "STOP! You can't leave just yet! It's time to put your knowledge to the test.";
            } else if (instructionPoint == 1) {
                instruction = "I'm BAD BAGUETTE, guardian of the Mighty Bread Portal. We need to defeat unhealthy creatures!";
            } else if (instructionPoint == 2) {
                instruction = "Use the arrow keys to avoid unhealthy foods while eating all the healthy ones.";
            } else if (instructionPoint == 3) {
                instruction = "They'll be falling at you so watch out! Let's team up to be friends with";
            } else {
                instructionPoint++;
                instruction = "and eat healthy foods that will boost your well-being too! Good luck!";
            }
            
            g.setFont(dogicaB);
            int lenIn = instruction.length();
            int start = 0, end = 0, loopCount = 0;
            /*loop to make sure that the instruction goes on
            a new line when it reaches the end of the box */
            while (end < lenIn) {
                if ( instruction.charAt(end) == ' ') {
                    if (end - start >= 15) {
                        g.drawString(instruction.substring(start, end), 540, 100 + 20 * loopCount);
                        start = end + 1;
                        loopCount++;
                    }
                }
                end++;
            }
            //when there are leftover words after the line for the last line
            if (start < lenIn) {
                g.drawString(instruction.substring(start), 540, 100 + 20 * loopCount);
            }
                


        }
    }
    public static void main (String [] args) {
        SwingUtilities.invokeLater(Level3::new);
    }

}
