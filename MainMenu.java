/* 
 * Course Info:
 * ICS4U0 with Krasteva, V.
 * 
 * @version 05/23/2023
 * @author Rachel Jing
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.TexturePaint;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu implements KeyListener {
    // font variables
    Font diloWorldL, diloWorldS, pixeltype, dogicaB, dogicaBM;  

    // other variable declaration
    JFrame frame;
    Color background = new Color(247, 246, 224);
    Color text = new Color(249, 157, 7);
    int carrotY = 210, leafX = 455, leafY = 210, leafY2 = -270; // initial positions of carrot
    Color select0, select1, select2; 
    Level1Intro lvl1I;

    /*
     * constructor of main menu
     */
    public MainMenu() {
        frame = new JFrame("Console");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.getContentPane().add(new Drawing());
        frame.addKeyListener(this);
        frame.setVisible(true);
    }

    int keyNum = 0;
    /*
     * overrided method of keyPressed; what happens when different keys are pressed
     * @param KeyEvent e
     * @return void
     */
    @Override
    public void keyPressed(KeyEvent e) {  
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {    // what happens if user presses up arrow
            keyNum--;
            if (keyNum < 0) {   // if the keyNum is already at bounds (prohibits from moving higher)
                carrotY -= 0;   // everything stays the same
                keyNum++;
            } else {
                // otherwise, moves everything up
                carrotY -= 55; 
                leafX -= 50;
                leafY -= 19;
                leafY2 -= 30;
                colorHighlight();   // calls colorHighlight method
            }
            frame.repaint();    // repaints frame with new coordinates of carrot
        } else if (keyCode == KeyEvent.VK_DOWN) {   // same thing but what happens when user presses down arrow
            keyNum++;
            if (keyNum > 2) {   // upper bounds
                carrotY += 0;
                keyNum--;
            } else {
                // otherwise, moves everything down
                carrotY += 55;
                leafX += 50;
                leafY += 19;
                leafY2 += 30;
                colorHighlight();
            }
            frame.repaint();    // repaints frame with new carrot
        } else if (keyCode == KeyEvent.VK_SPACE) {  // if the user presses space
            if (keyNum == 0) {  // user selects "NEW GAME"
                frame.dispose();
                lvl1I = new Level1Intro();  // begins level one
            }
        }
    }

    /*
     * this method will set the colour of the main menu options upon call
     * @return void
     */
    public void colorHighlight () {
        if (keyNum == 0) {
            select0 = background;
            select1 = Color.BLACK;
            select2 = Color.BLACK;
        } else if (keyNum == 1) {
            select0 = Color.BLACK;
            select1 = background;
            select2 = Color.BLACK;
        } else if (keyNum == 2) {
            select0 = Color.BLACK;
            select1 = Color.BLACK;
            select2 = background; 
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /*
     * graphics for main menu class
     */
    class Drawing extends JComponent {
        public Drawing() {
            setPreferredSize(new Dimension(800, 500));
            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    frame.setTitle("X: " + e.getX() + "; Y: " + e.getY());
                }
            });
        }

        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            try {
                diloWorldL = Font.createFont(Font.TRUETYPE_FONT, new File("DiloWorld.ttf")).deriveFont(75f);
                diloWorldS = Font.createFont(Font.TRUETYPE_FONT, new File("DiloWorld.ttf")).deriveFont(40f);
                pixeltype = Font.createFont(Font.TRUETYPE_FONT, new File("Pixeltype.ttf")).deriveFont(75f);
                dogicaB = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(23f);
                dogicaBM = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(12f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("DiloWorld.ttf")));
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("Pixeltype.ttf")));
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
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

            g.setColor(new Color(247, 246, 224));
            g.fillRect(40, 50, 700, 375);

            g.setFont(diloWorldL);
            //shadow
            g.setColor(Color.BLACK);
            g.drawString("Foodtopia", 200, 155);

            g.setColor(text);
            g.drawString("Foodtopia", 205, 150);

            //carrot
            g.setColor(Color.ORANGE);
            g.fillRoundRect(340, carrotY, 140, 50, 20, 20);
            g.fillOval(260, carrotY, 150, 50);

            AffineTransform originalTransform = g2d.getTransform(); // Store the original transform

            // Apply the rotation transformation
            g2d.rotate(Math.toRadians(70), 485, 255); // Rotate around the center of the oval
            g2d.setColor(Color.GREEN);
            g2d.fillOval(leafX, leafY, 20, 40); // Adjusted coordinates after rotation

            // Apply the reflection transformation
            AffineTransform reflection = AffineTransform.getScaleInstance(1, -1);
            g2d.transform(reflection);
            g2d.rotate(Math.toRadians(310), 485, -255); // Rotate around the center of the oval
            g2d.fillOval(leafX, leafY2, 20, 40); // Adjusted coordinates after reflection
            g2d.setTransform(originalTransform); // restores original transformation

            // text
            g2d.setFont(dogicaB);
            g2d.setColor(background);
            g2d.setColor(select0);
            g2d.drawString("NEW GAME", 280, 245);

            // arrow
            g2d.setColor(Color.BLACK);
            g2d.drawString(">", 240, carrotY + 35);

            g2d.setColor(select1);
            g2d.drawString("SOURCES", 290, 300);
            g2d.setColor(select2);
            g2d.drawString("EXIT", 325, 355);

            g.setFont(dogicaBM);
            g.setColor(Color.BLACK);
            g2d.drawString("use the UP and DOWN arrows. press SPACE to select. ", 120, 450);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
