/** 
 * <h2>Course Info:</h2>
 * ICS4U0 with Krasteva, V.
 *
 * @version 05/08/2023
 * @author Monellie Ghaffari-Haghi
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Level2 {
    JFrame frame = new JFrame("Console");
    Drawing draw = new Drawing();
    int instructionPoint = 0, questionPoint = 0, xWhich = 0, whichQ = 0, xRealWhich = 0;
    Level3Intro a;
    Font diloWorldL, diloWorldS, pixeltype, dogicaB, dogicaBM, dogicaBML, dogicaBL;
    Color colorChange = new Color(212, 231, 203);
    boolean done = false, questionTime = true;
    boolean[] usedQ = new boolean[6], corrects = new boolean[5];
    int[] qsPicked;
    String[] answers = new String[6];

    public Level2 ()
    {
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
        draw.addMouseListener(new ClickHandler());
        frame.add(draw);
        frame.setVisible(true);
    }
    class ClickHandler extends MouseAdapter
    {
        public void mouseClicked (MouseEvent e) {
            //instructionPoint less than 3 means that instructions are still being given
            if (instructionPoint < 3){
                draw.repaint();
                instructionPoint++;
            }
            //once instructionPoint reaches 4, there are 6 question points
            else if (instructionPoint == 4 && questionPoint < 5) {
                /*this runs one time to add the MouseMotionListener
                only after instructions are given*/
                if (!done) {
                    draw.addMouseMotionListener(new MotionHandler());
                    done = true;
                }
                //if questionPoint is even, it is time to display a question
                if (questionPoint % 2 == 0) {
                    if (xWhich != 0) {
                        questionTime = false;
                        questionPoint++;
                        whichQ++;
                    }
                }
                //if questionPoint is odd, it is time to change 
                else {
                    questionTime = true;
                    questionPoint++;
                }
                draw.repaint();
            }
            //after all of this, moves on to Level 3
            else {
                frame.dispose();
                a = new Level3Intro();
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
        public void paint (Graphics g)
        {
            int x = 0;
            int y = 0;
            int x2 = 0;
            int y2 = 0;
            //instructions
            if (instructionPoint <= 3) {
                String instruction = "Whoa! What's happening? I think you're entering a corn maze!";
                //background
                frame.getContentPane().setBackground(new Color(204, 255, 178));
                //ground
                g.setColor(new Color(182, 215, 168));
                g.fillOval(0+x, 300+y, 800, 100);
                g.fillRect(0+x, 350+y, 800, 150);
                //egg sun
                g.setColor(Color.white);
                g.fillOval(25+x2, 25+y2, 80,80);
                g.fillOval(20+x2, 50+y2, 20,30);
                g.fillOval(90+x2, 50+y2, 20,30);
                g.fillOval(50+x2, 90+y2, 30,20);
                g.fillOval(50+x2, 20+y2, 30,20);
                g.fillOval(40+x2, 20+y2, 30,30);
                g.fillOval(40+x2, 80+y2, 30,30);
                g.setColor(new Color(255, 217, 102));
                g.fillOval(45+x2, 45+y2, 40,40);
                //Corn
                //Find master corn here
                //text box
                g.setColor(new Color(223, 252, 210));
                Graphics2D g2d;
                g2d = (Graphics2D) g;
                g2d.fillRoundRect(80, 350, 640, 110, 30, 30);            
                
                //instructions
                g.setColor(Color.black);
                //changes value of instruction depending on instructionPoint
                if (instructionPoint == 0) {
                    instruction = "Whoa! What's happening? I think you're entering a corn maze!";
                } else if (instructionPoint == 1) {
                    instruction = "I can't go in there, but Master Corn can take it from here!";
                } else if (instructionPoint == 2) {
                    instruction = "Hi, I’m Master Corn, the ruler of the corn maze! I can help you leave this place, but only if I know that you are a REAL FOOD MASTER.";
                } else {
                    instructionPoint++;
                    instruction = "So, if you can answer my questions, I can help you through the maze. Are you ready?";
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
            //question and answers:
            else {
                //when the question and options are shown
                if (questionTime) {
                    frame.getContentPane().setBackground(new Color(182, 215, 168));
                    g.setColor(new Color(223, 252, 210));
                    //question pane
                    g.fillRect(30, 10, 740, 90);
                    //image box
                    g.fillRect(30, 110, 440, 250);
                    //5 option buttons
                    g.setColor(new Color(223, 252, 210));
                    g.fillRect(30, 370, 140, 90); //#1
                    g.fillRect(180, 370, 140, 90); //#2
                    g.fillRect(330, 370, 140, 90); //#3
                    g.fillRect(480, 370, 140, 90); //#4
                    g.fillRect(630, 370, 140, 90); //#5
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
                //when the answer is shown:
                else {
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
    public static void main(String[] args) {
        new Level2();
    }
}