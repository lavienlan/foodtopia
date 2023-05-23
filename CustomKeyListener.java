import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class CustomKeyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Check if the specific key is pressed (e.g., KeyEvent.VK_DOWN for the Down arrow key)
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            // Translate the entire shape down by 20 units
            int translateY = 20;
            g2d.translate(0, translateY);
            frame.repaint(); // Redraw the shape after translation
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }
}
