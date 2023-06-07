import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Image;
import java.io.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Level1 implements MouseListener {
    Font diloWorldL, diloWorldS, diloWorldSS, dogicaB, dogicaBM, dogica_text;
    Color sky = new Color(169, 208, 245);
    Color project = new Color(253, 235, 195, 150);
    JFrame frame;
    Graphics g;
    BufferedImage wally, rhonda, donna, barry, carla, larry, cantie;
    String instruction;
    Color button_col = Color.WHITE;

    String character_select = "";
    String character_info = "";
    String personality = ""; 
    String ingredients = "";
    String advice = "";

    int info_slider = 0;
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
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("click.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
        }

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
            if (!character_select.equals("")) {
                if (x >= 730 && y >= 20 && y <= 55 && x <= 760) {
                    character_select = "";
                    info_slider = 0;
                    System.out.println(character_select);
                    frame.repaint();
                } else if (x >= 670 && y >= 300 && y <= 350 && x <= 710) {
                    if (info_slider < 2) {
                        info_slider++;
                    }
                } else if (x >= 630 && y >= 300 && y <= 350 && x <= 660) {
                    if (info_slider != 0) {
                        info_slider--;   
                    }      
                }
            }
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
                dogicaBM = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(11f);
                dogica_text = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(12f);
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

                // egg sun
                g.setColor(Color.WHITE);
                g.fillOval(80, 70, 90, 90);
                g.fillOval(75, 30, 90, 90);
                g.fillOval(30, 45, 90, 90);
                g.fillOval(30, 70, 90, 90);
                
                // yolk
                g.setColor(new Color(255, 217, 102));
                g.fillOval(65, 65, 60, 60);

                // character imports
    
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

                g.setColor(project);
                int xPoly[] = {475, 600, 350, 725};
                int yPoly[] = {500, 500, 350, 350};
                Polygon poly = new Polygon(xPoly, yPoly, xPoly.length);
                g.fillPolygon(poly);
                g.setColor(new Color(253, 235, 195));
                g.fillRect(350, 100, 375, 250);

                g.setFont(diloWorldSS);
                g.setColor(Color.WHITE);
                
                switch (character_select) {
                    case "wally":
                        g.drawString("Wally the Water", 50, 80);
                        Image wallyScaled = wally.getScaledInstance(350, 350, Image.SCALE_DEFAULT);
                        g.drawImage(wallyScaled, 0, 100, this);
                        character_info = "Say hello to Wally the Water! Wally is not like the other characters in Foodtopia because he is not a food. Instead, Wally is a friendly water droplet. He's clear and cool, just like fresh water you drink!";
                        personality = "Wally is super important for our bodies. He helps us stay healthy and strong. When we drink water, Wally goes inside us and does amazing things. He keeps us hydrated, helps our muscles work, and keeps our skin looking good!";
                        advice = "Remember to drink water every day to keep Wally happy and your body healthy. Wally loves to be your best friend, so make sure to take sips of water throughout the day.";
                        break;

                    case "rhonda":
                        g.drawString("Rhonda the Red Velvet Cake", 50, 80);
                        Image rhondaScaled = rhonda.getScaledInstance(350, 350, Image.SCALE_DEFAULT);
                        g.drawImage(rhondaScaled, 0, 100, this);
                        character_info = "Uh-oh, here comes Rhonda the Red Velvet Cake! She might look delicious, but she's not as friendly as she seems. Rhonda is made from ingredients that can be bad for your health if you have too much of her.";
                        personality = "Rhonda may taste yummy, but eating too much of her and other unhealthy treats can be risky. They can make you gain too much weight, get sick, or even hurt your teeth. It's important to be careful and not let Rhonda trick you!";
                        advice = "Remember, Rhonda is a special treat for special times. It's okay to enjoy her every once in a while, but you should focus on eating healthy foods most of the time. Look for snacks made with better things or try other tasty options that are good for you.";
                        break;

                    case "donna":
                        g.drawString("Donna the Donut", 50, 80);
                        Image donnaScaled = donna.getScaledInstance(350, 350, Image.SCALE_DEFAULT);
                        g.drawImage(donnaScaled, 0, 100, this);
                        character_info = "Beware of Darry the Donut! He may look tempting with his sugary coating, but he holds a secret that's not so sweet. Inside that crispy exterior, Darry is a devious treat, hiding a sinister, chewy piece of dough!";
                        personality = "Darry may seem friendly, but he's not your healthiest choice. Eating too much of Darry can lead to trouble. His sugary nature can cause bad diseases and give you a dangerous sugar spike. Beware of the risks before becoming friends with Darry!";
                        advice = "It's important to be careful around Darry. While eating him occasionally is okay, remember to choose healthier options most of the time. Let's make smart choices and prioritize our well-being!";
                        break;

                    case "barry":
                        g.drawString("Barry the Broccoli", 50, 80);
                        Image barryScaled = barry.getScaledInstance(350, 350, Image.SCALE_DEFAULT);
                        g.drawImage(barryScaled, 0, 100, this);
                        character_info = "Say hello to Barry the Broccoli! He's a vibrant green veggie with a big smile. Barry is part of the mighty vegetable family and packed with amazing nutrients that are great for your body!";
                        personality = "Barry is a true superhero of nutrition. He's rich in vitamins, fiber, and minerals, which can help you grow strong and stay healthy. Eating Barry and other veggies can give you energy, make your bones strong, and even help you see better!";
                        advice = "Don't be afraid of Barry's unique appearance. Embrace his goodness and include him in your meals. Try steamed or roasted broccoli for a tasty and nutritious treat. Remember, the more colorful veggies you eat, the happier and healthier you'll be!";
                        break;

                    case "carla":
                        g.drawString("Carla (the chocolate bar)", 50, 80);
                        Image carlaScaled = carla.getScaledInstance(350, 350, Image.SCALE_DEFAULT);
                        g.drawImage(carlaScaled, 0, 100, this);
                        character_info = "Watch out for Carla the Chocolate Bar! She may seem yummy with her smooth and creamy texture, but she's not the best choice for your health. Carla is made from sugary chocolate that can cause problems if you eat too much.";
                        personality = "Carla may be sweet, but she can be a bit tricky. Eating too much chocolate, like Carla, can make you gain weight and hurt your teeth. It can also give you a burst of energy that quickly disappears.";
                        advice = "Remember, Carla is a treat to enjoy once in a while. It's important to choose healthier snacks most of the time, like fruits or nuts. They give your body what it needs to grow strong and stay healthy.";
                        break;

                    case "larry":
                        g.drawString("Larry the Loaf of Bread", 50, 80);
                        Image larryScaled = larry.getScaledInstance(350, 350, Image.SCALE_DEFAULT);
                        g.drawImage(larryScaled, 0, 100, this);
                        character_info = "Say hello to Larry the Loaf of Bread! He's a wholesome and nutritious friend made from whole grains. Larry is known for his golden crust and soft, fluffy insides.";
                        personality = "Larry is a health-conscious hero! Being made from whole grains, he's packed with important nutrients like fiber, vitamins, and minerals. Larry helps keep your body strong, gives you energy, and keeps your tummy happy.";
                        advice = "Embrace Larry as your ally for a balanced diet. Whole grain bread like Larry is a great choice for sandwiches, toast, and more. Make him a regular part of your meals to enjoy his goodness and nourish your body.";
                        break;
                }
                
                int xButton[] = {680, 710, 680};
                int yButton[] = {300, 320, 340};
                Polygon button = new Polygon(xButton, yButton, xButton.length);
                g.setColor(Color.ORANGE);
                g.fillPolygon(button);

                int xButton2 [] = {660, 630, 660};
                int yButton2 [] = {300, 320, 340};
                Polygon button2 = new Polygon(xButton2, yButton2, xButton2.length);
                g.fillPolygon(button2);

                g.setColor(Color.ORANGE);
                g.setFont(dogicaBM);
                String displayed = "";

                if (info_slider == 0) {
                    displayed = character_info;
                } else if (info_slider == 1) {
                    displayed = personality;
                } else if (info_slider == 2) {
                    displayed = advice; 
                } 

                frame.repaint();
                g.setFont(dogica_text);
                g.setColor(new Color(255, 190, 48));
                
                int lenIn = displayed.length();
                int start = 0, end = 0, loopCount = 0;
                /*loop to make sure that the instruction goes on
                a new line when it reaches the end of the box */
                while (end < lenIn) {
                    if ( displayed.charAt(end) == ' ') {
                        if (end - start >= 25) {
                            g.drawString(displayed.substring(start, end), 360, 130 + 25 * loopCount);
                            start = end + 1;
                            loopCount++;
                        }
                    }
                    end++;
                }
                //when there are leftover words after the line for the last line
                if (start < lenIn) {
                    g.drawString(displayed.substring(start), 360, 130 + 25 * loopCount);
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

    public static void main (String [] args) {
        SwingUtilities.invokeLater(Level1::new);
    }
}
    
 
