/** 
 * Course Info:
 * ICS4U0 with Krasteva, V.
 *
 * @version 06/12/2023
 * @author Monellie Ghaffari-Haghi
 */

 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import java.io.*;
 
 public class CollisionDetect {
     JFrame frame = new JFrame("Console");
     Drawing draw = new Drawing();
     Level2 a;
     Font dogicaBL, dogicaB;
     int x = 0, rockX = 100, rockW = 30;
     int y = 0, rockY = 300, rockH = 50;
     boolean done = false, stop = false; //done makes sure that the points are lost 1 time each, stop becomes true when its time to close the screen
     int points = 3;
     /*
     * constructor of level 2 introduction
     */
     public CollisionDetect ()
     {
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(800,500);
         draw.addMouseMotionListener(new MotionHandler());
         try{
             dogicaBL = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(30f);
             dogicaB = Font.createFont(Font.TRUETYPE_FONT, new File("dogicapixelbold.ttf")).deriveFont(11f);
         }
         catch(IOException | FontFormatException e) {
         }
         frame.add(draw);
         frame.setVisible(true);
     }
     public class MotionHandler implements MouseMotionListener {
         public void mouseDragged (MouseEvent e) {}
         public void mouseMoved (MouseEvent e) {
            int locX = e.getX();
            int locY = e.getY();
            if (locX < rockX || locX > rockX+rockW) {
                x = locX;
                y = locY;
                done = false;
            } else if (locY < rockY || locY > rockY+rockH){
                x = locX;
                y = locY;
                done = false;
            } else {
                //points counter
                if (!done) {
                    points--;
                    done = true;
                }
            }
            if (points <= 0) {
                stop = true;
            }
            draw.repaint();
         }
     }
     class Drawing extends JComponent
     {
         public void paint (Graphics g)
         {
            if (!stop) {
                //background
                frame.getContentPane().setBackground(new Color(106, 168, 79));
                //level name
                g.setFont(dogicaBL);
                g.setColor(Color.black);
                g.drawString("Level 2",300,180);
                //ground
                g.setColor(new Color(182, 215, 168));
                g.fillOval(0, 300, 800, 100);
                g.fillRect(0, 350, 800, 150);
                //gravel
                g.setColor(new Color(238, 238, 238));
                int[] xs = {100, 375, 425, 700};
                int[] ys = {500, 300, 300, 500};
                g.fillPolygon(xs, ys, 4);
                g.fillRect(rockX, rockY, rockW, rockH);
                g.fillOval(x-15, y-15, 30, 30);
            } else {
                /*//background
                frame.getContentPane().setBackground(Color.black);
                try {
                    System.out.println("Start of delay: now");
                    g.drawString("HIHIHI", 100, 100);
                    // Delay for 7 seonds
                    Thread.sleep(7000);
                    System.out.println("End of delay: now");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                frame.dispose();*/
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.WHITE);
                g2d.drawString("HIHIHI", 100, 100);
                
                try {
                    System.out.println("Start of delay: now");
                    Thread.sleep(7000);
                    System.out.println("End of delay: now");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                frame.dispose();
            }
         }
     }
     public static void main(String[] args) {
         new CollisionDetect();
     }
 }