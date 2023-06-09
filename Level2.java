/** 
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 * 
 * @version 05/29/2023
 * @author Monellie Ghaffari-Haghi
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.awt.geom.Line2D;

public class Level2 {
    JFrame frame = new JFrame("Console");
    Drawing draw = new Drawing();
    ClickHandler click = new ClickHandler();
    int instructionPoint = 0, questionPoint = 0, xWhich = 0, whichQ = 0, xRealWhich = 0;
    Level2Exit a;
    Font diloWorldL, diloWorldS, pixeltype, dogicaB, dogicaBM, dogicaBML, dogicaBL;
    Color colorChange = new Color(212, 231, 203);
    boolean done = false, questionTime = false, mazeTime = true, first = true, time = false;
    boolean[] usedQ = new boolean[6], corrects = new boolean[5], doneQ = new boolean[3];
    int[] qsPicked;
    String[] answers = new String[6];
    int score = 0;
    Color sky = new Color(169, 208, 245);
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int CELL_SIZE = 30;

    private int[][] maze = {
        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0},
        {0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1},
        {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
        {1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1},
        {0, 1, 0, 0, 2, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1},
        {0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 2, 0, 1, 1, 1, 1, 1},
        {0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 2, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
        {0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
        {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
        {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 3}
    };
    private int playerRow;
    private int playerCol;

    public Level2 ()
    {
        /*SwingUtilities.invokeLater(() -> {
            MazeGame mazeGame = new MazeGame(0, 0);
            mazeGame.setVisible(true);
        });*/
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        //creating fonts
        try {
            diloWorldL = Font.createFont(Font.TRUETYPE_FONT, new File("DiloWorld.ttf")).deriveFont(75f);
            diloWorldS = Font.createFont(Font.TRUETYPE_FONT, new File("DiloWorld.ttf")).deriveFont(40f);
            pixeltype = Font.createFont(Font.TRUETYPE_FONT, new File("Pixeltype.ttf")).deriveFont(30f);
            dogicaB = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(11f);
            dogicaBM = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(13f);
            dogicaBL = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(19f);
            dogicaBML = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(19f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("DiloWorld.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("Pixeltype.ttf")));
        }
        catch(IOException | FontFormatException e) {
        }
        //randomizes the questions asked
        qsPicked = new int[3];
        qsPicked[0] = (int)(Math.random() * 6);
        usedQ[qsPicked[0]] = true;
        do {
            qsPicked[1] = (int)(Math.random() * 6);
        } while (qsPicked[1] == qsPicked[0]);
        usedQ[qsPicked[1]] = true;
        do {
            qsPicked[2] = (int)(Math.random() * 6);
        } while (qsPicked[2] == qsPicked[0] || qsPicked[2] == qsPicked[1]);
        usedQ[qsPicked[2]] = true;
        answers[0] = "If you have too many donuts, it could lead to diabetes and heart disease. Unless you want to get these health problems, you shouldn't eat so many donuts.";
        answers[1] = "Like most fruits and veggies, broccoli has fiber in it. Fiber helps your digestive system, which means it helps you poo easily!";
        answers[2] = "The reason for the red colour of red velvet cake is that there are crushed cochineal insects in the colour. Lots of food dyes are made of bugs. Next time you think about eating something because of its colour, remember that it might be made of bugs.";
        answers[3] = "Chocolate bar makers are allowed to have up to 8 insect parts put in their chocolate bars. This is because they are made in giant factories and can't keep track of all of the bugs. Gross!";
        answers[4] = "Cantaloupes have vitamin A and C. This helps protect your skin, eyes, breathing, heart, and general nutrition.";
        answers[5] = "Water makes your bones, joints, and teeth healthier and strong. It also improves your memory and makes people happier.";
        draw.addMouseListener(click);
        frame.add(draw);
        frame.setVisible(true);
    }
    class ClickHandler extends MouseAdapter
    {
        public void mouseClicked (MouseEvent e) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/click.wav").getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception ex) {
            }
            //instructionPoint less than 3 means that instructions are still being given
            if (instructionPoint < 3){
                draw.repaint();
                instructionPoint++;
            }
            //once instructionPoint reaches 4, there are 6 question points
            else if (instructionPoint == 4 && questionPoint < 10) {
                /*this runs one time to add the MouseMotionListener
                only after instructions are given*/
                if (!done) {
                    draw.addMouseMotionListener(new MotionHandler());
                    frame.addKeyListener(new HandlePress());
                    done = true;
                }
                //if questionPoint is the 2nd, it is time to move on to the answer
                if (questionPoint % 3 == 1) {
                    if (xWhich != 0) {
                        questionTime = false;
                        mazeTime = false;
                        questionPoint++;
                        whichQ++;
                    }
                }
                //if questionPoint is third, it is time to go back to the maze
                else if (questionPoint % 3 == 2) {
                    questionTime = false;
                    mazeTime = true;
                    doneQ[questionPoint/3] = true;
                    questionPoint++;
                }
                draw.repaint();
            }
            //after all of this, moves on to Level 3
            else {
                a = new Level2Exit(score/2);
                frame.dispose();
            }
        }
        public void mousePressed (MouseEvent e)
        {
            if (instructionPoint < 4){
                //draw.repaint();
                //instructionPoint++;
                //commented because otherwise it will increment twice
            }
            else {
                //frame.dispose();
                //a = new Level3Intro();
            }
        }
    }
    public class MotionHandler implements MouseMotionListener {
        public void mouseDragged (MouseEvent e) {}
        public void mouseMoved (MouseEvent e) {
            //checks whether to actually figure out the mouse location
            if (questionTime) {
                int x = e.getX();
                int y = e.getY();
                int xRight = (x-20) % 150; //puts the mouse location within 150
                xWhich = (x-20) / 150 + 1; //finds the button that is hovered over
                xRealWhich = xWhich;
                //checks if location of mouse is on a button
                if (xRight >= 10 && x <= 770 && y >= 370 && y <= 460) {
                    colorChange = new Color(37, 59, 28);
                    draw.repaint();
                } else {
                    xWhich = 0; //set it to 0, which is not an option so nothing happens
                    colorChange = new Color(212, 231, 203);
                    draw.repaint();
                }
            }
        }
    }
    class Drawing extends JComponent
    {
        private BufferedImage donna;
        private BufferedImage rhonda;
        private BufferedImage carla;
        private BufferedImage wally;
        private BufferedImage barry;
        private BufferedImage cantie;
        private BufferedImage masterCorn;
        //private BufferedImage larry;
        private int imgX = 70;
        private int imgY = 80;
        private int imgSize = 300;
        private int nameX = 340;
        private int nameY = 350;
        public Drawing() {
            try {
                donna = ImageIO.read(new File("characters/donna.png"));
                rhonda = ImageIO.read(new File("characters/rhonda.png"));
                carla = ImageIO.read(new File("characters/carla.png"));
                wally = ImageIO.read(new File("characters/wally.png"));
                barry = ImageIO.read(new File("characters/barry.png"));
                cantie = ImageIO.read(new File("characters/cantie.png"));
                masterCorn = ImageIO.read(new File("characters/master corn.png"));
                //larry = ImageIO.read(new File("characters/larry.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 
        public void paint (Graphics g)
        {
            int x = 55;
            int y = 40;
            int x2 = 0;
            int y2 = 0;
            Color greenBackground = new Color(117, 235, 146);
            Color mazeBorder = new Color(18, 74, 30);
            Color mazeBackground = new Color(199, 255, 211);
            Color mazeBall = new Color(94, 196, 116);
            Color checkpoint = new Color(255, 191, 0);
            Color mazeGround = new Color(0, 156, 70);
            int playerX;
            int playerY;

            //instructions
            if (instructionPoint <= 3) {
                String instruction = "Whoa! What's happening? I think you're entering a corn maze!";
                //background
                frame.getContentPane().setBackground(greenBackground);
                //ground
                g.setColor(mazeGround);
                g.fillRect(0,380, 800, 120);
                
                
                //maze

                playerX = playerCol * CELL_SIZE+x;
                playerY = playerRow * CELL_SIZE+y;

                g.setColor(mazeBorder);
                g.fillRect(50, 35, 700, 310);

                for (int row = 0; row < maze.length; row++) {
                    for (int col = 0; col < maze[row].length; col++) {
                        int x3 = col * (CELL_SIZE)+x;
                        int y3 = row * (CELL_SIZE)+y;

                        if (maze[row][col] == 1) {
                            g.setColor(mazeBackground);
                            g.fillRect(x3, y3, (CELL_SIZE), (CELL_SIZE));
                            g.setColor(mazeBorder);
                            g.fillRect(x3+5, y3+5, (CELL_SIZE)-10, (CELL_SIZE)-10);
                        } else if (maze[row][col] == 0) {
                            g.setColor(mazeBackground);
                            g.fillRect(x3, y3, (CELL_SIZE), (CELL_SIZE));
                        } else if (maze[row][col] == 3) {
                            g.setColor(mazeBackground);
                            g.fillRect(x3, y3, (CELL_SIZE), (CELL_SIZE));
                            g.setColor(new Color(255, 69, 69));
                            Graphics2D g2 = (Graphics2D) g;
                            g2.setStroke(new BasicStroke(10));
                            g2.draw(new Line2D.Float(x3+5+2, y3+5, x3+20+2, y3+20));
                            g2.draw(new Line2D.Float(x3+20+2, y3+5, x3+5+2, y3+20));
                            //g.drawLine();
                        } else {
                            g.setColor(mazeBackground);
                            g.fillRect(x3, y3, (CELL_SIZE), (CELL_SIZE));
                            g.setColor(checkpoint);
                            g.fillRect(x3+5, y3+5, (CELL_SIZE)-10, (CELL_SIZE)-10);
                        }
                    }
                }

                g.setColor(mazeBall);
                g.fillOval(playerX+3, playerY+3, CELL_SIZE-6, CELL_SIZE-6);
                //master corn image
                if (instructionPoint == 0) {
                    Image cantieScaled = cantie.getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT);
                    g.drawImage(cantieScaled, 210, 110, this);
                } else if (instructionPoint == 1) {
                    Image cantieScaled = cantie.getScaledInstance(imgSize-60, imgSize-60, Image.SCALE_DEFAULT);
                    g.drawImage(cantieScaled, 120, 190, this);
                } else if (instructionPoint == 2) {
                    Image cornScaled = masterCorn.getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT);
                    g.drawImage(cornScaled, 320, 110, this);
                } else if (instructionPoint == 3) {
                    Image cornScaled = masterCorn.getScaledInstance(imgSize+30, imgSize+30, Image.SCALE_DEFAULT);
                    g.drawImage(cornScaled, 260, 80, this);
                }
                //text box
                g.setColor(new Color(199, 255, 211));
                Graphics2D g2d;
                g2d = (Graphics2D) g;
                g2d.fillRoundRect(80, 350, 640, 110, 30, 30);            
                //instructions
                //changes value of instruction depending on instructionPoint
                if (instructionPoint == 0) {
                    instruction = "Whoa! What's happening? I think you're entering a CORN MAZE!";
                } else if (instructionPoint == 1) {
                    instruction = "I can't go in there, but Master Corn can take it from here!";
                } else if (instructionPoint == 2) {
                    instruction = "Hi, I'm Master Corn, the ruler of the CORN MAZE! I can help you leave this place, but only if I know that you are a REAL FOOD MASTER.";
                } else {
                    instructionPoint++;
                    instruction = "So, if you can answer my questions, I can help you through the MAZE. Are you ready?";
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
                            g.drawString(instruction.substring(start, end), 100, 380 + 20 * loopCount);
                            start = end + 1;
                            loopCount++;
                        }
                    }
                    end++;
                }
                //when there are leftover words after the line for the last line
                if (start < lenIn) {
                    g.drawString(instruction.substring(start), 100, 380 + 20 * loopCount);
                }
                g.setFont(dogicaB);
                g.setColor(new Color(0, 45, 56));
                g.drawString("Click anywhere to continue.", 435, 445);
            }
            //question, maze, and answers:
            else {
                //when the question and options are shown
                if (questionTime) {
                    frame.getContentPane().setBackground(greenBackground);
                    //ground
                    g.setColor(mazeGround);
                    g.fillRect(0,380, 800, 120);
                    //question pane
                    g.setColor(new Color(199, 255, 211));
                    g.fillRect(30, 10, 740, 90);
                    //image box
                    g.setColor(sky);
                    g.fillRect(30, 110, 440, 250);
                    //5 option buttons
                    g.setColor(new Color(199, 255, 211));
                    g.fillRect(30, 370, 140, 90); //#1
                    g.fillRect(180, 370, 140, 90); //#2
                    g.fillRect(330, 370, 140, 90); //#3
                    g.fillRect(480, 370, 140, 90); //#4
                    g.fillRect(630, 370, 140, 90); //#5
                    //master corn image
                    Image cornScaled = masterCorn.getScaledInstance(imgSize-40, imgSize-40, Image.SCALE_DEFAULT);
                    g.drawImage(cornScaled, 510, 110, this);
                    if (xWhich != 0) {
                        g.setColor(colorChange);
                        g.drawRect(30 + (xWhich-1) * 150, 370, 140, 90);
                        g.drawRect(31 + (xWhich-1) * 150, 371, 138, 88);
                    }
                    if (qsPicked[whichQ] == 0) {
                        question1(g);
                    } else if (qsPicked[whichQ] == 1) {
                        question2(g);
                    } else if (qsPicked[whichQ] == 2) {
                        question3(g);
                    } else if (qsPicked[whichQ] == 3) {
                        question4(g);
                    } else if (qsPicked[whichQ] == 4) {
                        question5(g);
                    } else {
                        question6(g);
                    }
                }
                //when the player moves in the maze
                else if (mazeTime) {
                    //background
                    frame.getContentPane().setBackground(greenBackground);
                    //ground
                    g.setColor(mazeGround);
                    g.fillRect(0,380, 800, 120);

                    playerX = playerCol * CELL_SIZE + x;
                    playerY = playerRow * CELL_SIZE + y;
    
                    g.setColor(mazeBorder);
                    g.fillRect(50, 35, 700, 310);
    
                    for (int row = 0; row < maze.length; row++) {
                        for (int col = 0; col < maze[row].length; col++) {
                            int x3 = col * (CELL_SIZE)+x;
                            int y3 = row * (CELL_SIZE)+y;
    
                            if (maze[row][col] == 1) {
                                g.setColor(mazeBackground);
                                g.fillRect(x3, y3, (CELL_SIZE), (CELL_SIZE));
                                g.setColor(mazeBorder);
                                g.fillRect(x3+5, y3+5, (CELL_SIZE)-10, (CELL_SIZE)-10);
                            } else if (maze[row][col] == 0) {
                                g.setColor(mazeBackground);
                                g.fillRect(x3, y3, (CELL_SIZE), (CELL_SIZE));
                            } else if (maze[row][col] == 3) {
                                g.setColor(mazeBackground);
                                g.fillRect(x3, y3, (CELL_SIZE), (CELL_SIZE));
                                g.setColor(new Color(255, 69, 69));
                                Graphics2D g2 = (Graphics2D) g;
                                g2.setStroke(new BasicStroke(10));
                                g2.draw(new Line2D.Float(x3+5+2, y3+5, x3+20+2, y3+20));
                                g2.draw(new Line2D.Float(x3+20+2, y3+5, x3+5+2, y3+20));
                                //g.drawLine();
                            } else {
                                g.setColor(mazeBackground);
                                g.fillRect(x3, y3, (CELL_SIZE), (CELL_SIZE));
                                g.setColor(checkpoint);
                                g.fillRect(x3+5, y3+5, (CELL_SIZE)-10, (CELL_SIZE)-10);
                            }
                        }
                    }
    
                    g.setColor(mazeBall);
                    g.fillOval(playerX+3, playerY+3, CELL_SIZE-6, CELL_SIZE-6);

                    //master corn image
                    Image cornScaled = masterCorn.getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT);
                    g.drawImage(cornScaled, 10, 290, this);

                    int textX = 280, textY = 370;
                    //text box
                    g.setColor(new Color(199, 255, 211));
                    Graphics2D g2d;
                    g2d = (Graphics2D) g;
                    g2d.fillRoundRect(textX, textY, 470, 70, 30, 30);   

                    String instruction = "Use the arrows on your keyboard to";
                    String instruction2 = "move the dot!";
                    g.setFont(dogicaBM);
                    g.setColor(new Color(0, 61, 11));
                    g.drawString(instruction, textX+20, textY+30);
                    g.drawString(instruction2, textX+20, textY+50);
                }
                //when the answer is shown:
                else {
                    //master corn image
                    Image cornScaled = masterCorn.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
                    g.drawImage(cornScaled, 480, 10, this);
                    //checks the array corrects to see if the selected answer is correct
                    if (corrects[xRealWhich-1]) {
                        winner(g);
                    } else {
                        loser(g);
                    }
                    g.setColor(Color.black);
                    //set String answer to the responsed of this question
                    String answer = answers[qsPicked[whichQ-1]];
                    int lenIn = answer.length();
                    int start = 0, end = 0, loopCount = 0;
                    /*loop to make sure that the answer goes on
                    a new line when it reaches the end of the screen*/
                    while (end < lenIn) {
                        if (answer.charAt(end) == ' ') {
                            if (end - start >= 35) {
                                g.drawString(answer.substring(start, end), 30, 175 + 30 * loopCount);
                                start = end + 1;
                                loopCount++;
                            }
                        }
                        end++;
                    }
                    //when there are leftover words after the line for the last line
                    if (start < lenIn) {
                        g.drawString(answer.substring(start), 30, 175 + 30 * loopCount);
                    }
                    g.setFont(dogicaBM);
                    g.drawString("Click anywhere to continue.", 220, 420);
                }
            }
        }
        public void question1(Graphics g) {
            g.setColor(Color.black);
            g.setFont(dogicaBML);
            g.drawString("If you eat too many donuts, what diseases", 35,50);
            g.drawString("might you get? (more than 1 answer)", 35,80);
            // character image
            Image donnaScaled = donna.getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT);
            g.drawImage(donnaScaled, imgX, imgY, this);
            //character name
            g.setFont(dogicaBML);
            g.drawString("Donna", nameX, nameY);
            //options:
            g.setFont(dogicaBM);
            //OPTION 1
            g.drawString("Diabetes", 32, 420);
            corrects[0] = true;
            //OPTION 2
            g.drawString("Heart", 32+150, 410);
            g.drawString("Disease", 32+150, 430);
            corrects[1] = true;
            //OPTION 3
            g.drawString("Happiness", 32+300, 420);
            corrects[2] = false;
            //OPTION 4
            g.drawString("Longer", 32+450, 410);
            g.drawString("Lifespan", 32+450, 430);
            corrects[3] = false;
            //OPTION 5
            g.drawString("Big", 32+600, 410);
            g.drawString("Brain", 32+600, 430);
            corrects[4] = false;
        }
        public void question2(Graphics g) {
            g.setColor(Color.black);
            g.setFont(dogicaBML);
            g.drawString("What will fiber in broccoli help", 35,50);
            g.drawString("you with?", 35,80);
            // character image
            Image barryScaled = barry.getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT);
            g.drawImage(barryScaled, imgX, imgY, this);
            //character name
            g.setFont(dogicaBML);
            g.drawString("Barry", nameX, nameY);
            //options:
            g.setFont(dogicaBM);
            //OPTION 1
            g.drawString("Posture", 32, 420);
            corrects[0] = false;
            //OPTION 2
            g.drawString("Strong", 32+150, 410);
            g.drawString("teeth", 32+150, 430);
            corrects[1] = false;
            //OPTION 3
            g.drawString("Poo", 32+300, 420);
            corrects[2] = true;
            //OPTION 4
            g.drawString("Longer", 32+450, 410);
            g.drawString("lifespan", 32+450, 430);
            corrects[3] = false;
            //OPTION 5
            g.drawString("Build", 32+600, 410);
            g.drawString("muscle", 32+600, 430);
            corrects[4] = false;
        }
        public void question3(Graphics g) {
            g.setColor(Color.black);
            g.setFont(dogicaBML);
            g.drawString("Which of these ingredients is in", 35,50);
            g.drawString("red velvet cake food dye?", 35,80);
            // character image
            Image rhondaScaled = rhonda.getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT);
            g.drawImage(rhondaScaled, imgX, imgY, this);
            //character name
            g.setFont(dogicaBML);
            g.drawString("Rhonda", nameX, nameY);
            //options:
            g.setFont(dogicaBM);
            //OPTION 1
            g.drawString("Frog legs", 32, 420);
            corrects[0] = false;
            //OPTION 2
            g.drawString("Strawberry", 32+150, 420);
            corrects[1] = false;
            //OPTION 3
            g.drawString("A red", 32+300, 420);
            corrects[2] = false;
            //OPTION 4
            g.drawString("Cochineal", 32+450, 410);
            g.drawString("insects", 32+450, 430);
            corrects[3] = true;
            //OPTION 5
            g.drawString("Elmo", 32+600, 420);
            corrects[4] = false;
        }
        public void question4(Graphics g) {
            g.setColor(Color.black);
            g.setFont(dogicaBML);
            g.drawString("Which of these might you find in a", 35, 50);
            g.drawString(" chocolate bar?", 35, 80);
            // character image
            Image carlaScaled = carla.getScaledInstance(imgSize-30, imgSize-30, Image.SCALE_DEFAULT);
            g.drawImage(carlaScaled, imgX, imgY+10, this);
            //character name
            g.setFont(dogicaBML);
            g.drawString("Carla", nameX+15, nameY);
            //options:
            g.setFont(dogicaBM);
            //OPTION 1
            g.drawString("A brown", 32, 420);
            corrects[0] = false;
            //OPTION 2
            g.drawString("Cheese", 32+150, 420);
            corrects[1] = false;
            //OPTION 3
            g.drawString("Cat hair", 32+300, 420);
            corrects[2] = false;
            //OPTION 4
            g.drawString("Blueberry", 32+450, 420);
            corrects[3] = false;
            //OPTION 5
            g.drawString("Insect", 32+600, 410);
            g.drawString("legs", 32+600, 430);
            corrects[4] = true;
        }
        public void question5(Graphics g) {
            g.setColor(Color.black);
            g.setFont(dogicaBML);
            g.drawString("What vitamin is in a cantaloupe?\n", 35,50);
            g.drawString("(more than 1 answer)", 35,80);
            // character image
            Image cantieScaled = cantie.getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT);
            g.drawImage(cantieScaled, imgX, imgY+10, this);
            //character name
            g.setFont(dogicaBML);
            g.drawString("Cantie", nameX, nameY);
            g.setFont(dogicaBM);
            //OPTION 1
            g.drawString("Vitamin A", 32, 420);
            corrects[0] = true;
            //OPTION 2
            g.drawString("Vitamin B", 32+150, 420);
            corrects[1] = false;
            //OPTION 3
            g.drawString("Vitamin C", 32+300, 420);
            corrects[2] = true;
            //OPTION 4
            g.drawString("Vitamin D", 32+450, 420);
            corrects[3] = false;
            //OPTION 5
            g.drawString("Vitamin E", 32+600, 420);
            corrects[4] = false;
        }
        public void question6(Graphics g) {
            g.setColor(Color.black);
            g.setFont(dogicaBML);
            g.drawString("How does water help your body?", 35,65);
            // character image
            Image wallyScaled = wally.getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT);
            g.drawImage(wallyScaled, imgX, imgY, this);
            //character name
            g.setFont(dogicaBML);
            g.drawString("Wally", nameX, nameY);
            //options:
            g.setFont(dogicaBM);
            //OPTION 1
            g.drawString("It has", 32, 410);
            g.drawString("protein", 32, 430);
            corrects[0] = false;
            //OPTION 2
            g.drawString("It does", 32+150, 410);
            g.drawString("not help", 32+150, 430);
            corrects[1] = false;
            //OPTION 3
            g.drawString("It has", 32+300, 410);
            g.drawString("fiber", 32+300, 430);
            corrects[2] = false;
            //OPTION 4
            g.drawString("Healthy", 32+450, 410);
            g.drawString("bones", 32+450, 430);
            corrects[3] = true;
            //OPTION 5
            g.drawString("It has", 32+600, 410);
            g.drawString("sugar", 32+600, 430);
            corrects[4] = false;
        }
        public void winner(Graphics g) {
            //set background to greenish
            frame.getContentPane().setBackground(new Color(141, 227, 123));
            g.setFont(dogicaBL);
            g.setColor(Color.BLACK);
            g.drawString("You are right!!", 20, 100);
            g.setColor(new Color(16, 92, 0));
            g.fillRect(24, 134, 760, 250);
            g.setColor(new Color(203, 255, 192));
            g.fillRect(20, 130, 760, 250);
            score += 1;
        }
        public void loser(Graphics g) {
            //set background to orange
            frame.getContentPane().setBackground(new Color(255, 212, 128));
            g.setFont(dogicaBL);
            g.setColor(Color.BLACK);
            g.drawString("Sorry, that's not right.", 20, 100);
            g.setColor(new Color(125, 83, 0));
            g.fillRect(24, 134, 760, 250);
            g.setColor(new Color(255, 234, 192));
            g.fillRect(20, 130, 760, 250);
        }
    }
    class HandlePress extends KeyAdapter {
        private int stepCount = 0;
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();

            if (mazeTime) {
                try {
                    if (stepCount % 2 == 0) {
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/step1.wav").getAbsoluteFile());
                        Clip step1 = AudioSystem.getClip();
                        step1.open(audioInputStream);
                        step1.start();
                        stepCount++;
                    } else {
                        AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(new File("sounds/step2.wav").getAbsoluteFile());
                        Clip step2 = AudioSystem.getClip();
                        step2.open(audioInputStream2);
                        step2.start(); 
                        stepCount++;
                    }
                } catch (Exception ex) {
                }
                
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        movePlayer(-1, 0);
                        break;
                    case KeyEvent.VK_DOWN:
                        movePlayer(1, 0);
                        break;
                    case KeyEvent.VK_LEFT:
                        movePlayer(0, -1);
                        break;
                    case KeyEvent.VK_RIGHT:
                        movePlayer(0, 1);
                        break;
                    case KeyEvent.VK_W:
                        movePlayer(-1, 0);
                        break;
                    case KeyEvent.VK_S:
                        movePlayer(1, 0);
                        break;
                    case KeyEvent.VK_A:
                        movePlayer(0, -1);
                        break;
                    case KeyEvent.VK_D:
                        movePlayer(0, 1);
                        break;
                }

                //when it reaches a checkpoint
                if ((playerCol == 4 && playerRow == 4 && !doneQ[0]) || (playerCol == 10 && playerRow == 6 && !doneQ[1]) || (playerCol == 16 && playerRow == 5 && !doneQ[2]) && !first && questionPoint < 9) {
                    mazeTime = false;
                    questionTime = true;
                    questionPoint++;
                }
                //when it reaches the end
                else if (playerCol == maze[0].length-1 && playerRow == maze.length-1) {
                    frame.dispose();
                    a = new Level2Exit(score/2);
                }
                first = false;
            }
            draw.repaint();

        }

        private void movePlayer(int rowOffset, int colOffset) {
            int newRow = playerRow + rowOffset;
            int newCol = playerCol + colOffset;

            if (newRow >= 0 && newRow < maze.length && newCol >= 0 && newCol < maze[0].length && maze[newRow][newCol] != 1) {
                playerRow = newRow;
                playerCol = newCol;
            }
        }
    }
    public static void main(String[] args) {
        new Level2();
    }
}