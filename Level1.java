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

public class Level1 {
    JFrame frame;
    public Level1() {
        frame = new JFrame("Console");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.getContentPane().add(new Drawing());
        frame.setVisible(true);
    }

    class Drawing extends JComponent {
            public void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g.fillRect(0, 0, 200, 100);
        }
    }
}
