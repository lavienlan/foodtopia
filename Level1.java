import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Image;
import java.io.*;

public class Level1 implements MouseListener {
    Font diloWorldL, diloWorldS, diloWorldSS, pixeltype, dogicaB, dogicaBM;
    Color sky = new Color(169, 208, 245);
    Color project = new Color(253, 235, 195, 20);
    JFrame frame;
    Graphics g;
    String character_select = "";
    int instructionPoint = 0;

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
        if (instructionPoint < 5 ) {
            frame.repaint();
            instructionPoint++;
        } else {
            if (character_select.equals("")) {
                if (x >= 20 && y >= 230 && y <= 330 && x <= 110) {
                    character_select = "wally";
                    System.out.println(character_select);
                    frame.repaint();
                } else if (x >= 145 && y >= 230 && y <= 330 && x <= 245) {
                    character_select = "rhonda";
                    System.out.println(character_select);
                    frame.repaint();
                } else if (x >= 275 && y >= 205 && y <= 305 && x <= 375) {
                    character_select = "donna";
                    System.out.println(character_select);
                    frame.repaint();
                } else if (x >= 390 && y >= 220 && y <= 320 && x <= 490) {
                    character_select = "barry";
                    System.out.println(character_select);
                    frame.repaint();
                } else if (x >= 515 && y >= 205 && y <= 305 && x <= 615) {
                    character_select = "carla";
                    System.out.println(character_select);
                    frame.repaint();
                } else if (x >= 640 && y >= 250 && y <= 350 && x <= 740) {
                    character_select = "larry";
                    System.out.println(character_select);
                    frame.repaint();
                }
            }
        }

        if (x >= 730 && y >= 20 && y <= 55 && x <= 760) { //&& y <= 30 && x <= 790) {
            character_select = "";
            System.out.println(character_select);
            frame.repaint();
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
        private BufferedImage wally, rhonda, donna, barry, carla, larry, cantie;
        private String instruction;

        public Drawing() {
            try {
                wally = ImageIO.read(new File("characters/wally.png"));
                rhonda = ImageIO.read(new File("characters/rhonda.png"));
                donna = ImageIO.read(new File("characters/donna.png"));
                barry = ImageIO.read(new File("characters/barry.png"));
                carla = ImageIO.read(new File("characters/carla.png"));
                larry = ImageIO.read(new File("characters/larry.png"));
                cantie = ImageIO.read(new File("characters/cantie.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 

        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            try {
                diloWorldL = Font.createFont(Font.TRUETYPE_FONT, new File("DiloWorld.ttf")).deriveFont(75f);
                diloWorldS = Font.createFont(Font.TRUETYPE_FONT, new File("DiloWorld.ttf")).deriveFont(40f);
                diloWorldSS = Font.createFont(Font.TRUETYPE_FONT, new File("DiloWorld.ttf")).deriveFont(50f);
                pixeltype = Font.createFont(Font.TRUETYPE_FONT, new File("Pixeltype.ttf")).deriveFont(75f);
                dogicaBM = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(11f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("DiloWorld.ttf")));
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("Pixeltype.ttf")));
            }
            catch(IOException | FontFormatException e) {
            }

            if(character_select.equals("")) {
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
                //Image donna = t.getImage("characters/donna.png");
                Toolkit t = Toolkit.getDefaultToolkit();
                
                
                Image cantieScaled = cantie.getScaledInstance(120, 120, Image.SCALE_DEFAULT);
                g.drawImage(cantieScaled, -10, 345, this);

                g.drawImage(donna, 275, 205, this);
                g.drawImage(carla, 515, 205, this);
                g.drawImage(rhonda, 145, 240, this);
                g.drawImage(barry, 390, 220, this);
                g.drawImage(larry, 640, 250, this);
                g.drawImage(wally, 20, 230, this);
                
                // magnifying glass cursor
                Cursor cursor = t.createCustomCursor(new ImageIcon(getClass().getResource("images/magnifying glass.png")).getImage(), new Point(0, 0), "magnifying glass");
                setCursor(cursor);

                // textbox
                g.setColor(new Color(238, 238, 238));
                g2d.fillRoundRect(100, 350, 640, 110, 30, 30); 
                int xPoly[] = {150, 250, 325, 375, 450, 275, 100};
                int yPoly[] = {150, 100, 125, 225, 250, 375, 300};

                //poly = new Polygon(xPoly, yPoly, xPoly.length);
               // g.drawPolygon(poly);

                if (instructionPoint == 0) {
                    instruction = "Welcome to Foodtopia! I'm Cantie the Cantaloupe, and I'm delighted to make your acquaintance. In this marvelous world of Foodtopia, you'll encounter a delightful creatures, each unique in size and appearance. Can you guess what everyfood has in common?";
                } else if (instructionPoint == 1) {
                    instruction = "That's right—we're all foods! Did you catch onto my little joke? Hehe. But enough chit-chat, let's meet some more friends!";
                } else if (instructionPoint == 2) {
                    instruction = "Do you see the little magnifying glass that moves with your mouse? Use it to tap on one of our friends and you'll get to peek inside and discover what they are made of!";
                } else if (instructionPoint == 3) {
                    instruction = "Keep in mind that some of our friends are super friendly and oh-so-healthy, while others may not be as much. So, it's important to be cautious and choose your friends wisely.";
                } else {
                    instructionPoint++;
                    instruction = "Alright, enough talking! Let's embark on an exciting journey by clicking on a food and discovering what surprises await us!";
                }
                g.setFont(dogicaBM);
                g.setColor(new Color(0, 61, 11));
                int lenIn = instruction.length();
                int start = 0, end = 0, loopCount = 0;
                /*loop to make sure that the instruction goes on
                a new line when it reaches the end of the box*/
                while (end < lenIn) {
                    if (instruction.charAt(end) == ' ') {
                        if (end - start >= 49) {
                            g.drawString(instruction.substring(start, end), 110, 370 + 20 * loopCount);
                            start = end + 1;
                            loopCount++;
                        }
                    }
                    end++;
                }
                //when there are leftover words after the line for the last line
                if (start < lenIn) {
                    g.drawString(instruction.substring(start), 110, 370 + 20 * loopCount);
                }
            }

            if (!character_select.equals("")) {
                //background
                g.setColor(sky);
                g.fillRect(0, 0, 800, 500);

                //ground
                g.setColor(new Color(182, 215, 168));
                g2d.fillOval(0, 300, 800, 100);
                g.fillRect(0, 350, 800, 150);

                g.setFont(diloWorldSS);
                g.setColor(Color.WHITE);

                g.setColor(project);
                g.fillRect(400, 150, 300, 200);

                
                switch (character_select) {
                    case "wally":
                        g.drawString("Wally the Water", 50, 80);
                        Image wallyScaled = wally.getScaledInstance(350, 350, Image.SCALE_DEFAULT);
                        g.drawImage(wallyScaled, 100, 100, this);
                        break;

                    case "rhonda":
                        g.drawString("Rhonda the Red Velvet Cake", 50, 80);
                        Image rhondaScaled = rhonda.getScaledInstance(350, 350, Image.SCALE_DEFAULT);
                        g.drawImage(rhondaScaled, 100, 100, this);
                        break;

                    case "donna":
                        g.drawString("Donna the Donut", 50, 80);
                        Image donnaScaled = donna.getScaledInstance(350, 350, Image.SCALE_DEFAULT);
                        g.drawImage(donnaScaled, 100, 100, this);
                        break;

                    case "barry":
                        g.drawString("Barry the Broccoli", 50, 80);
                        Image barryScaled = barry.getScaledInstance(350, 350, Image.SCALE_DEFAULT);
                        g.drawImage(barryScaled, 100, 100, this);
                        break;

                    case "carla":
                        g.drawString("Carla (the chocolate bar)", 50, 80);
                        Image carlaScaled = carla.getScaledInstance(350, 350, Image.SCALE_DEFAULT);
                        g.drawImage(carlaScaled, 100, 100, this);
                        break;

                    case "larry":
                        g.drawString("Larry the Loaf of Bread", 50, 80);
                        Image larryScaled = larry.getScaledInstance(350, 350, Image.SCALE_DEFAULT);
                        g.drawImage(larryScaled, 100, 100, this);
                        break;
                }
                
                g.setColor(Color.RED);
                g.setFont(diloWorldS);

                g2d.rotate(Math.toRadians(45), 770, 10);
                g2d.fillRoundRect(770, 20, 10, 30, 10, 10);
                g2d.rotate(Math.toRadians(90), 790, 30);
                g2d.fillRoundRect(790, 30, 10, 30, 10, 10);
                
            }
        }
    }

    public void characterInfo () {
        g.drawRect(0, 0, 100, 100);
    }

    public static void main (String [] args) {
        SwingUtilities.invokeLater(Level1::new);
    }
}
    
 
