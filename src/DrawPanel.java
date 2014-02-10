import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawPanel extends JPanel
{
	boolean ready = false;
	Variables v;
    public DrawPanel()                       // set up graphics window
    {
        super();
        setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g)  // draw graphics in the panel
    {
        int width = getWidth();             // width of window in pixels
        int height = getHeight();           // height of window in pixels

        super.paintComponent(g);            // call superclass to make panel display correctly
        
        g.setFont(new Font("TimesRoman",Font.PLAIN,24));

        if(ready == false)
        {
        g.drawString("Alarm-o-bot is Loading the Display...", 100, 150); 
        }
        else
        {
        	g.drawString("Enemy controls:                                                                   Enemy Mana: " + v.enemymana + "/" + v.enemymaxmana,10,30);
        	g.drawString("    0. Enemy has " + v.enemyhealth + " health",10,60);
    		for (int i = 0; i < 8; i++) {
    			if (v.enemyminions[i].name != "null") {

    				if(v.enemyminions[i].currenthealth > 0)
    				{
    					g.setColor(Color.RED);
    					g.fillRect(100*i + 30, 90, 80, 80);
    					g.setColor(Color.BLACK);
    					g.drawString(Integer.toString(v.enemyminions[i].damage), 100*i + 30, 110);
    					g.drawString(Integer.toString(v.enemyminions[i].currenthealth)+"/"+Integer.toString(v.enemyminions[i].maxhealth), 100*i + 80, 110);
    					g.drawString(v.enemyminions[i].name, 100*i + 30, 170);
    				}
    				
    			}
    		}
    		//nl();

    		g.drawString("You control:",10,530);
    		g.drawString("    0. You have " + v.playerhealth + " health",10,560);
    		for (int i = 0; i < 8; i++) {

    			if(v.playerminions[i].currenthealth > 0)
    			{

    				if (v.playerminions[i].name != "null") {
    					g.setColor(Color.GREEN);
    					g.fillRect(100*i + 30, 590, 80, 80);
    				}
    				
    			}
    			
    		}
        }
        
        
    }

//    public static void main(String[] args)
//    {
//        DrawPanel panel = new DrawPanel();                            // window for drawing
//        JFrame application = new JFrame();                            // the program itself
//        
//        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // set frame to exit
//                                                                      // when it is closed
//        application.add(panel);           
//
//
//        application.setSize(500, 400);         // window is 500 pixels wide, 400 high
//        application.setVisible(true);          
//    }
    
    public void redraw()
    {
    	repaint();
    }

}