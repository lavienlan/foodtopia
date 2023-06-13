/** 
 * Course Info:
 * ICS4U0 with Krasteva, V.
 *
 * @version 06/09/2023
 * @author Monellie Ghaffari-Haghi and Rachel Jing
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Level3 {
    // Fonts
    Font diloWorldL, diloWorldS, diloWorldSS, dogicaB, dogicaBM, dogicaBL;
    
    //Images
    BufferedImage baguette, heartFull, heartHalf, heartGone;
    BufferedImage mushroom, egg, fries, sushi, carrot, bean, coke, chicken, pear, cheese;
    
    // Other components
    Level3Exit a;
    JFrame frame;
    Graphics g;
    Drawing draw = new Drawing();
    
    // Instructions and game variables
    String instruction = "";
    int instructionPoint = 0;
    boolean roadTime = true, done = false;
    private static final int CELL_SIZE = 130;
    int score = 0;
    boolean win = true;
    int points = 3;
    private int[] road = {1,0,0,0,1};
    private int playerCol = 1;
    boolean deducted = false, nextDone = false;
    private int[] objCol = new int[20];
    private boolean[] healthy = new boolean[20];
    int round = 0;
    boolean thing = false;
    boolean[] eatens = new boolean[20];

    /*
     * constructor for level 3
     */
    public Level3() {
        frame = new JFrame("Console");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        for (int i = 0; i < objCol.length; i++) {
            objCol[i] = (int)(Math.random() * 3) + 1;
        }
        frame.getContentPane().add(draw);
        frame.addMouseListener(new ClickHandler());
        frame.addKeyListener(new HandlePress());
        frame.setVisible(true);
    }

    /*
     * Event handler for mouse clicks
     */
    class ClickHandler extends MouseAdapter {
        public void mouseClicked (MouseEvent e) {
            try {
            // Play a click sound
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("click.wav")));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            } catch (Exception ex) {
            }
                
            if (instructionPoint < 5) {
                frame.repaint();
                instructionPoint++;
            }else if (roadTime) {
                if (!done) {
                    done = true;
                }
                instructionPoint++;
                frame.repaint();
            } else {
                frame.dispose();
            }
        }
    }
    class HandlePress extends KeyAdapter {
       public void keyPressed(KeyEvent e) {
           int keyCode = e.getKeyCode();
           if (roadTime) {
               switch (keyCode) {
                   case KeyEvent.VK_LEFT:
                       movePlayer(-1);
                       break;
                   case KeyEvent.VK_RIGHT:
                       movePlayer(1);
                       break;
                   case KeyEvent.VK_D:
                       movePlayer(1);
                       break;
                   case KeyEvent.VK_A:
                       movePlayer(-1);
                       break;
               }
           }
           draw.repaint();
       }
        private void movePlayer(int colOffset) {
            int newCol = playerCol + colOffset;

            if (newCol >= 0 && newCol < road.length && road[newCol] != 1) {
                playerCol = newCol;
            }
        }
    }
    /*
     * Custom drawing class
     */
    class Drawing extends JComponent {
        private int objectY = -500; // Initial position of the mushroom
        private int rightCol = 110; // Initial position of the mushroom
        private int space = -300;
        private int mushroomSpeed = 5; // Speed at which the mushroom falls
        private Timer timer; // Timer for animation

        private int x = 55;
        private int y = 340;

        public Drawing() {
            try {
                baguette = ImageIO.read(getClass().getResourceAsStream("bad baguette.png"));
                heartFull = ImageIO.read(getClass().getResourceAsStream("full heart.png"));
                heartHalf = ImageIO.read(getClass().getResourceAsStream("half heart.png"));
                heartGone = ImageIO.read(getClass().getResourceAsStream("empty heart.png"));
                mushroom = ImageIO.read(getClass().getResourceAsStream("mushroom.png"));
                egg = ImageIO.read(getClass().getResourceAsStream("egg.png"));
                fries = ImageIO.read(getClass().getResourceAsStream("fries.png"));
                sushi = ImageIO.read(getClass().getResourceAsStream("sushi.png"));
                carrot = ImageIO.read(getClass().getResourceAsStream("carrot.png"));
                bean = ImageIO.read(getClass().getResourceAsStream("bean.png"));
                coke = ImageIO.read(getClass().getResourceAsStream("coke.png"));
                chicken = ImageIO.read(getClass().getResourceAsStream("chicken.png"));
                cheese = ImageIO.read(getClass().getResourceAsStream("cheese.png"));
                pear = ImageIO.read(getClass().getResourceAsStream("pear.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /*
         * Paint components or graphics for level 3
         * @param Graphics g The graphic console to draw on 
         */
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            Color brownBackground = new Color(255, 208, 135);
            Color breadBorder = new Color(74, 45, 0);
            Color breadBackground = new Color(245, 218, 176);
            Color bakeryGround = new Color(117, 71, 0);
            int playerX;
            try {
                diloWorldL = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("DiloWorld.ttf")).deriveFont(75f);
                diloWorldS = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("DiloWorld.ttf")).deriveFont(40f);
                dogicaBL = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("dogicapixelbold.ttf")).deriveFont(19f);
                dogicaB = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("dogicapixelbold.ttf")).deriveFont(9f);
                dogicaBM = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("dogicapixelbold.ttf")).deriveFont(14f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("DiloWorld.ttf")));
            }
            catch(IOException | FontFormatException e) {
            }

            Color sky = new Color(169, 208, 245);
            if (instructionPoint <= 4) {
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
    
                g.setColor(new Color(255, 181, 189));
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
    
                // Instructions
                if (instructionPoint == 0) {
                    instruction = "STOP! You can't leave just yet! It's time to put your knowledge to the test.";
                } else if (instructionPoint == 1) {
                    instruction = "I'm BAD BAGUETTE, guardian of the Mighty Bread Portal. We need to defeat unhealthy creatures!";
                } else if (instructionPoint == 2) {
                    instruction = "Use the arrow keys to avoid unhealthy foods while eating all the healthy ones.";
                } else if (instructionPoint == 3) {
                    instruction = "They'll be falling towards you at random so watch out!";
                } else if (instructionPoint == 4){
                    instruction = "Well then, let's be friends with and eat healthy foods that will boost your well-being!";
                } else {
                    instruction = "Click anywhere to start! Good luck!";
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
            //question, road, and answers:
            else {
                if (!thing) {
                    timer = new Timer(20, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Update the position of the mushroom
                            objectY += mushroomSpeed; //figure out how to update the rounds each time
                            if (objectY + space*round >= 420) {
                                round++;
                                deducted = false;
                            }
                            if (round >= 20){
                                if (!nextDone){
                                    a = new Level3Exit(score, win);
                                    nextDone = true;
                                    frame.dispose();
                                }
                            } else {
                                // Object Detection
                                if (((round != 6 && round != 15 && round != 19) && objectY + space*round > y-180 && objectY + space*round < 350) || ((round == 6 || round == 15 || round == 19) && objectY + space*round > y-120 && objectY + space*round < 420)) {
                                    if (!healthy[round]) {
                                        if (playerCol == objCol[round] && !deducted) {
                                            points--;
                                            deducted = true;
                                            eatens[round] = true;
                                            try {
                                                // Play a eating sound
                                                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("munch.wav")));
                                                Clip clip = AudioSystem.getClip();
                                                clip.open(audioInputStream);
                                                clip.start();
                                            } catch (Exception ex) {
                                            }
                                        }
                                    } else {
                                        if (playerCol == objCol[round] && !deducted) {
                                            score++;
                                            deducted = true;
                                            eatens[round] = true;
                                            try {
                                                // Play a eating sound
                                                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("munch.wav")));
                                                Clip clip = AudioSystem.getClip();
                                                clip.open(audioInputStream);
                                                clip.start();
                                            } catch (Exception ex) {
                                            }
                                        }
                                    }
                                } 
                            }
                            // Repaint the frame
                            repaint();
                        }
                    });
                    timer.start(); // Start the timer
                    thing = true;
                }
                //background
                frame.getContentPane().setBackground(brownBackground);
                //ground
                g.setColor(bakeryGround);
                g.fillRect(0,380, 800, 120);

                playerX = playerCol * CELL_SIZE+x;

                g.setColor(breadBackground);
                g.fillRect(CELL_SIZE+x, 0, 3*CELL_SIZE, 500);

                //surrounding lines
                g.setColor(breadBorder);
                g.drawLine(CELL_SIZE+x, 0, CELL_SIZE+x, 500);
                g.drawLine(CELL_SIZE*2+x, 0, CELL_SIZE*2+x, 500);
                g.drawLine(CELL_SIZE*3+x, 0, CELL_SIZE*3+x, 500);
                g.drawLine(CELL_SIZE*4+x, 0, CELL_SIZE*4+x, 500);

                Image cheeseScaled = cheese.getScaledInstance(CELL_SIZE+60, CELL_SIZE+60, Image.SCALE_DEFAULT);
                g.drawImage(cheeseScaled, playerX+3-30, y+3-35, this);

                int textboX = 600, textboY = 320;
                g.setColor(new Color(247, 184, 82));
                g2d.fillRoundRect(textboX, textboY, 180, 90, 30, 30);
                g.setFont(dogicaB);
                g.setColor(Color.white);
                g.drawString("Use the arrow keys", textboX+10, textboY+20);
                g.drawString("to avoid unhealthy", textboX+10, textboY+40);
                g.drawString("foods while eating", textboX+10, textboY+60);
                g.drawString("all the healthy ones!", textboX+10, textboY+80);
                
                //heart lives
                int heartSize = 160;
                Image heartFullScaled = heartFull.getScaledInstance(heartSize, heartSize, Image.SCALE_DEFAULT);
                Image heartGoneScaled = heartGone.getScaledInstance(heartSize, heartSize, Image.SCALE_DEFAULT);
                int yHeart = 350;
                int xStartHeart = -80;
                int xDistHeart = 50;
                switch (points) {
                    case 3:
                        g.drawImage(heartFullScaled, xStartHeart+xDistHeart, yHeart, this);
                        g.drawImage(heartFullScaled, xStartHeart+xDistHeart*2, yHeart, this);
                        g.drawImage(heartFullScaled, xStartHeart+xDistHeart*3, yHeart, this);
                        break;
                    case 2:
                        g.drawImage(heartFullScaled, xStartHeart+xDistHeart, yHeart, this);
                        g.drawImage(heartFullScaled, xStartHeart+xDistHeart*2, yHeart, this);
                        g.drawImage(heartGoneScaled, xStartHeart+xDistHeart*3, yHeart, this);
                        break;
                    case 1:
                        g.drawImage(heartFullScaled, xStartHeart+xDistHeart, yHeart, this);
                        g.drawImage(heartGoneScaled, xStartHeart+xDistHeart*2, yHeart, this);
                        g.drawImage(heartGoneScaled, xStartHeart+xDistHeart*3, yHeart, this);
                        break;
                    
                    case 0:
                        win = false;
                        if (!nextDone){
                            a = new Level3Exit(score, win);
                            nextDone = true;
                            frame.dispose();
                        }

                    default:
                        a = new Level3Exit(score, win);
                        frame.dispose();
                }

                // Draw score
                g.setFont(dogicaBL);
                g.setColor(Color.black);
                g.drawString("Score: " + score, 30, 200);

                // Object drops
                Image mushroomScaled = mushroom.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
                Image beanScaled = bean.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
                if (!eatens[0]) {
                    g.drawImage(mushroomScaled, rightCol + CELL_SIZE * (objCol[0]-1), objectY, this);
                    healthy[0] = true;
                }
                if (!eatens[1]) {
                    g.drawImage(egg, rightCol + CELL_SIZE * (objCol[1]-1)+5, objectY + space, this);
                    healthy[1] = true;
                }
                if (!eatens[2]) {
                    g.drawImage(fries, rightCol + CELL_SIZE * (objCol[2]-1) + 10, objectY + (space * 2), this);
                    healthy[2] = false;
                }
                if (!eatens[3]) {
                    g.drawImage(sushi, rightCol + CELL_SIZE * (objCol[3]-1), objectY + (space * 3), this);
                    healthy[3] = true;
                }
                if (!eatens[4]) {
                    g.drawImage(coke, rightCol + CELL_SIZE * (objCol[4]-1) + 15, objectY + (space * 4), this);
                    healthy[4] = false;
                }
                if (!eatens[5]) {
                    g.drawImage(carrot, rightCol + CELL_SIZE * (objCol[5]-1)-10, objectY + (space * 5), this);
                    healthy[5] = true;
                }
                if (!eatens[6]) {
                    g.drawImage(beanScaled, rightCol + CELL_SIZE * (objCol[6]-1)+42, objectY + (space * 6), this);
                    healthy[6] = true;
                }
                if (!eatens[7]) {
                    g.drawImage(pear, rightCol + CELL_SIZE * (objCol[7]-1) +5, objectY + (space * 7), this);
                    healthy[7] = true;
                }
                if (!eatens[8]) {
                    g.drawImage(mushroomScaled, rightCol + CELL_SIZE * (objCol[8]-1), objectY + (space * 8), this);
                    healthy[8] = true;
                }
                if (!eatens[9]) {
                    g.drawImage(chicken, rightCol + CELL_SIZE * (objCol[9]-1), objectY + (space * 9), this);
                    healthy[9] = false;
                }
                if (!eatens[10]) {
                    g.drawImage(sushi, rightCol + CELL_SIZE * (objCol[10]-1), objectY + (space * 10), this);
                    healthy[10] = true;
                }
                if (!eatens[11]) {
                    g.drawImage(fries, rightCol + CELL_SIZE * (objCol[11]-1) + 10, objectY + (space * 11), this);
                    healthy[11] = false;
                }
                if (!eatens[12]) {
                    g.drawImage(carrot, rightCol + CELL_SIZE * (objCol[12]-1), objectY + (space * 12), this);
                    healthy[12] = true;
                }
                if (!eatens[13]) {
                    g.drawImage(coke, rightCol + CELL_SIZE * (objCol[13]-1) + 15, objectY + (space * 13), this);
                    healthy[13] = false;
                }
                if (!eatens[14]) {
                    g.drawImage(egg, rightCol + CELL_SIZE * (objCol[14]-1)+5, objectY + (space * 14), this);
                    healthy[14] = true;
                }
                if (!eatens[15]) {
                    g.drawImage(beanScaled, rightCol + CELL_SIZE * (objCol[15]-1)+42, objectY + (space * 15), this);
                    healthy[15] = true;
                }
                if (!eatens[16]) {
                    g.drawImage(chicken, rightCol + CELL_SIZE * (objCol[16]-1), objectY + (space * 16), this);
                    healthy[16] = false;
                }
                if (!eatens[17]) {
                    g.drawImage(carrot, rightCol + CELL_SIZE * (objCol[17]-1) - 10, objectY + (space * 17), this);
                    healthy[17] = true;
                }
                if (!eatens[18]) {
                    g.drawImage(coke, rightCol + CELL_SIZE * (objCol[18]-1)  + 15, objectY + (space * 18), this);
                    healthy[18] = false;
                }
                if (!eatens[19]) {
                    g.drawImage(beanScaled, rightCol + CELL_SIZE * (objCol[19]-1)+42, objectY + (space * 19), this);
                    healthy[19] = true;
                }
                
            }
        }
    }

    /*
     * Main method of Level 3
     */
    public static void main (String [] args) {
        SwingUtilities.invokeLater(Level3::new);
    }
}
