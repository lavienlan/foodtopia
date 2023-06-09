/** 
 * Course Info:
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
 
 public class Level3Exit {
     JFrame frame = new JFrame("Console");
     BufferedImage baguette, cantie, masterCorn;
     Drawing draw = new Drawing();
     Level3Intro a;
     Font dogicaBL, dogicaB;
     Color sky = new Color(169, 208, 245);
     int score;
     /*
     * constructor of Level 3 exit
     */
     public Level3Exit (int scr)
     {
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(800,500);
         try{
             dogicaBL = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(19f);
             dogicaB = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(11f);
         }
         catch(IOException | FontFormatException e) {
         }
         score = scr;
         draw.addMouseListener(new ClickHandler());
         frame.add(draw);
         frame.setVisible(true);
     }
     class ClickHandler extends MouseAdapter
     {
         public void mouseClicked (MouseEvent e)
         {
             frame.dispose();
             a = new Level3Intro();
         }
     }
     class Drawing extends JComponent
     {
        public Drawing() {
            try {
                cantie = ImageIO.read(new File("characters/cantie.png"));
                masterCorn = ImageIO.read(new File("characters/master corn.png"));
                baguette = ImageIO.read(new File("characters/bad baguette.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         public void paint (Graphics g)
         {
            int lowness = 30;
             //background
             frame.getContentPane().setBackground(sky);
             //level name
             g.setFont(dogicaBL);
             g.setColor(Color.black);
             g.drawString("Congratulations! You are definitely", 130, 60+lowness);
             g.drawString("a real FOOD MASTER now!! Foodtopia", 130, 90+lowness);
             g.drawString("is thankful for your help!", 130, 120+lowness);
             g.drawString("Score: " + score, 330,180+lowness);
             Image cantScaled = cantie.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
             g.drawImage(cantScaled, 105, 100, this);
             Image masterScaled = masterCorn.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
             g.drawImage(masterScaled, 305, 100, this);
             Image bagScaled = baguette.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
             g.drawImage(bagScaled, 505, 100, this);
             //ground
             g.setColor(new Color(182, 215, 168));
             g.fillOval(0, 300, 800, 100);
             g.fillRect(0, 350, 800, 150);
             //gravel
             g.setColor(new Color(238, 238, 238));
             int[] xs = {100, 375, 425, 700};
             int[] ys = {500, 300, 300, 500};
             g.fillPolygon(xs, ys, 4);
             //egg sun
             g.setColor(Color.white);
             g.fillOval(25, 25, 80,80);
             g.fillOval(20, 50, 20,30);
             g.fillOval(90, 50, 20,30);
             g.fillOval(50, 90, 30,20);
             g.fillOval(50, 20, 30,20);
             g.fillOval(40, 20, 30,30);
             g.fillOval(40, 80, 30,30);
             g.setColor(new Color(255, 217, 102));
             g.fillOval(45, 45, 40,40);
             //click anywhere
             g.setFont(dogicaB);
             g.setColor(new Color(0, 45, 56));
             g.drawString("Click anywhere to continue.", 280, 445);
         }
     }
 }