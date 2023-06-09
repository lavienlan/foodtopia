import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sources {
    JFrame frame = new JFrame("Console");
    Drawing draw = new Drawing();
    MainMenu m;
    Font diloWorldL, diloWorldS, pixeltype, dogicaB, dogicaBL;

    public Sources() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        draw.addKeyListener(new KeyHandler());
        try {
            dogicaB = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(30f);
            dogicaBL = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(10f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        } catch (IOException | FontFormatException e) {
        }
        frame.add(draw);
        frame.setVisible(true);
        draw.setFocusable(true); // Set the drawing component as focusable to receive key events
    }

    class KeyHandler extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            char keyCode = e.getKeyChar();

            if(keyCode == KeyEvent.VK_R || keyCode == 'r' ) {
                m = new MainMenu();
                frame.dispose();
            }
        }
    }

    class Drawing extends JComponent {
        public void paint(Graphics g) {
            // background
            frame.getContentPane().setBackground(new Color(255, 204, 228));

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
            // click anywhere
            g.setFont(dogicaBL);
            g.setColor(new Color(0, 45, 56));
            g.drawString("[R] to return to the main menu.", 280, 445);

            g.setFont(dogicaB);
            g.setColor(Color.BLACK);
            g.drawString("References", 130, 90);
            g.setFont(dogicaBL);
            g.drawString("All citations are shortened in order to fit.", 40, 140);
            g.drawString("1. The Body Odd. (2012). Chocolate allergies linked to cockroach parts. NBC News.", 40, 170);
            g.drawString("2. Harvard. (2022). Fiber. Harvard T.H. Chan School of Public Health.", 40, 200);
            g.drawString("3. Rethy, J. (2020). Choose Water for Healthy Hydration. HealthyChildren.org.", 40, 230);
            g.drawString("4. Russo, C. H. (2016). You Know What Red Food Dye Is Made Of, Right? HuffPost.", 40, 260);
            g.drawString("5. Sass, C. (2023). Health Benefits of Broccoli - Nutrition. Health.", 40, 290);
            g.drawString("6. Warwick, K. W. (2020). How Many Calories Are in Glazed Doughnuts? Healthline.", 40, 320);
            g.drawString("7. Zelman, K. M. (2022). Cantaloupe. WebMD.", 40, 350);

        }
    }

    public static void main(String[] args) {
        new Sources();
    }
}
