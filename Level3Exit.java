import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Level3Exit {
    JFrame frame = new JFrame("Console");
    BufferedImage baguette, cantie, masterCorn;
    Drawing draw = new Drawing();
    MainMenu r;
    Credits c;
    Font dogicaBL, dogicaB;
    Color sky = new Color(169, 208, 245);
    int score;
    boolean win;

    /*
    * Constructor of Level 3 exit
    */
    public Level3Exit(int scr, boolean didWin) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        try {
            dogicaBL = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(19f);
            dogicaB = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(11f);
        } catch (IOException | FontFormatException e) {
        }
        score = scr;
        win = didWin;
        frame.add(draw);
        draw.setFocusable(true); // Request focus for the drawing component
        draw.addKeyListener(new KeyHandler());
        draw.addMouseListener(new ClickHandler());
        frame.setVisible(true);
    }

    class ClickHandler extends MouseAdapter {
        public void mouseClicked (MouseEvent e) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/click.wav").getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception ex) {
            }
        }
    }

    class KeyHandler extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            char keyCode = e.getKeyChar();
            if (keyCode == KeyEvent.VK_R || keyCode == 'r') { // what happens if user presses R
                r = new MainMenu();
                frame.dispose();
            } else if (keyCode == KeyEvent.VK_E || keyCode == 'e' ) { // what happens if user presses E
                c = new Credits();
                frame.dispose();
            }
        }
    }

    class Drawing extends JComponent {
        public Drawing() {
            try {
                cantie = ImageIO.read(new File("characters/cantie.png"));
                masterCorn = ImageIO.read(new File("characters/master corn.png"));
                baguette = ImageIO.read(new File("characters/bad baguette.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void paint(Graphics g) {
            int lowness = 30;
            // background
            frame.getContentPane().setBackground(sky);
            // level name
            g.setFont(dogicaBL);
            g.setColor(Color.black);
            if (win) {
                g.drawString("Congratulations! You are definitely", 130, 60 + lowness);
                g.drawString("a real FOOD MASTER now!! Foodtopia", 130, 90 + lowness);
                g.drawString("is thankful for your help!", 130, 120 + lowness);
            } else {
                g.drawString("Good try! You were almost there!", 130, 60 + lowness);
                g.drawString("Keep learning and better", 130, 90 + lowness);
                g.drawString("luck next time!", 130, 120 + lowness);
            }
            g.drawString("Score: " + score, 330, 180 + lowness);
            Image cantScaled = cantie.getScaledInstance(250, 250, Image.SCALE_DEFAULT);
            // g.drawImage(cantScaled, 20, 100, this);
            Image masterScaled = masterCorn.getScaledInstance(250, 250, Image.SCALE_DEFAULT);
            // g.drawImage(masterScaled, 305, 150, this);
            Image bagScaled = baguette.getScaledInstance(275, 275, Image.SCALE_DEFAULT);
            // g.drawImage(bagScaled, 505, 100, this);

            // ground
            g.setColor(new Color(182, 215, 168));
            g.fillOval(0, 300, 800, 100);
            g.fillRect(0, 350, 800, 150);
            // gravel
            g.setColor(new Color(238, 238, 238));
            int[] xs = { 100, 375, 425, 700 };
            int[] ys = { 500, 300, 300, 500 };
            g.fillPolygon(xs, ys, 4);
            // egg sun
            g.setColor(Color.white);
            g.fillOval(25, 25, 80, 80);
            g.fillOval(20, 50, 20, 30);
            g.fillOval(90, 50, 20, 30);
            g.fillOval(50, 90, 30, 20);
            g.fillOval(50, 20, 30, 20);
            g.fillOval(40, 20, 30, 30);
            g.fillOval(40, 80, 30, 30);
            g.setColor(new Color(255, 217, 102));
            g.fillOval(45, 45, 40, 40);

            g.drawImage(cantScaled, 20, 160, this);
            g.drawImage(masterScaled, 515, 150, this);
            g.drawImage(bagScaled, 270, 180, this);

            // click anywhere
            g.setFont(dogicaB);
            g.setColor(new Color(0, 45, 56));
            g.drawString("[R] to return to the main menu. [E] to exit.", 200, 445);
        }
    }
}
