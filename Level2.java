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
    int instructionPoint = 0, questionPoint = 0;
    Level3Intro a;
    Font diloWorldL, diloWorldS, pixeltype, dogicaB;

    public Level2 ()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        draw.addMouseListener(new ClickHandler());
        frame.add(draw);
        frame.setVisible(true);
    }
    class ClickHandler extends MouseAdapter
    {
        public void mouseClicked (MouseEvent e)
        {
            if (instructionPoint < 3){
                draw.repaint();
                instructionPoint++;
            } else if (instructionPoint == 4) {
                draw.repaint();
            }
            else {
                frame.dispose();
                a = new Level3Intro();
            }
        }
        public void mousePressed (MouseEvent e)
        {
            if (instructionPoint < 4){
                draw.repaint();
                //instructionPoint++;
                //commented because otherwise it will increment twice
            }
            else {
                //frame.dispose();
                //a = new Level3Intro();
            }
        }
    }
    class Drawing extends JComponent
    {
        public void paint (Graphics g)
        {
            int x = 0;
            int y = 0;
            int x1 = 0;
            int y1 = 0;
            int x2 = 0;
            int y2 = 0;
            if (instructionPoint <= 3) {
                try {
                    diloWorldL = Font.createFont(Font.TRUETYPE_FONT, new File("DiloWorld.ttf")).deriveFont(75f);
                    diloWorldS = Font.createFont(Font.TRUETYPE_FONT, new File("DiloWorld.ttf")).deriveFont(40f);
                    pixeltype = Font.createFont(Font.TRUETYPE_FONT, new File("Pixeltype.ttf")).deriveFont(30f);
                    dogicaB = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(11f);
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("DiloWorld.ttf")));
                    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File ("Pixeltype.ttf")));
                }
                catch(IOException | FontFormatException e) {
                }
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
                //Corn guy
                //Find master corn here
                //text box
                g.setColor(new Color(238, 238, 238));
                Graphics2D g2d;
                g2d = (Graphics2D) g;
                g2d.fillRoundRect(80, 350, 640, 110, 30, 30);            
                
                g.setColor(Color.black);
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
                g.setFont(dogicaB);
                int lenIn = instruction.length();
                int start = 0, end = 0, loopCount = 0;
                while (end < lenIn) {
                    if (instruction.charAt(end) == ' ') {
                        if (end - start >= 60) {
                            g.drawString(instruction.substring(start, end), 100, 380 + 20 * loopCount);
                            start = end + 1;
                            loopCount++;
                        }
                    }
                    end++;
                }
                if (start < lenIn) {
                    g.drawString(instruction.substring(start), 100, 380 + 20 * loopCount);
                }
            } else {
                frame.getContentPane().setBackground(new Color(106, 168, 79));
                g.setColor(new Color(212, 231, 203));
                //question pane
                g.fillRect(30, 10, 740, 90);
                //image box
                g.fillRect(30, 110, 440, 250);
                //5 option buttons
                g.fillRect(30, 370, 140, 90); //#1
                g.fillRect(180, 370, 140, 90); //#2
                g.fillRect(330, 370, 140, 90); //#3
                g.fillRect(480, 370, 140, 90); //#4
                g.fillRect(630, 370, 140, 90); //#5
            }
        }
    }
    public static void main(String[] args) {
        new Level2();
    }
}