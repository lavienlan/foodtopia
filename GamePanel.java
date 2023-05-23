import javax.swing.JPanel;

public class GamePanel extends JPanel {
    public static int gameState;
    public static final int titleState = 0;
    public static final int playState = 1;
    public static final int pauseState = 2;
   

    public GamePanel() {

    }

    public static void main (String [] args) {
        gameState = titleState;
        if (gameState == titleState) {
            new MainMenu();
        } 
    }
}
