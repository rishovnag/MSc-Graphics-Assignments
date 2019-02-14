

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

//In the output a rectangle will be shown. Click within the rectangle to color the enclosed area.

public class FLOOD_FILL extends JPanel implements MouseListener
{
    private BufferedImage image;
    private Graphics2D graphics2;
    
    public FLOOD_FILL()
    {
        image = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
        setPreferredSize(new Dimension(image.getWidth(),image.getHeight()));
        setMinimumSize(getPreferredSize());
        graphics2 = image.createGraphics();
        graphics2.setColor(Color.blue);
        graphics2.drawRect(100, 100, 100, 100);
        addMouseListener(this);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(image, 0, 0, null);
    }
    
    public void floodfill(int seedx,int seedy) //seedx and seedy are the starting points
    {
    	int xmove = seedx,ymove = seedy;    
    	
    	//first we fill the left side of the starting point and then fill the right
    	
        while(image.getRGB(xmove, ymove) != Color.blue.getRGB())//checking if the current color is boundary or not. this loop fills the region to the left of the seed pixel
        {       
        	while(image.getRGB(xmove, ymove) != Color.blue.getRGB())//this loop considers the pixels below the current pixel
            {
        		image.setRGB(xmove,ymove,Color.white.getRGB());//changing the current color to white
                update(getGraphics());
                ymove--; //moving downwards
            }
            ymove=seedy; //resetting y value to original value
            //filling downwards
            while(image.getRGB(xmove, ymove) != Color.blue.getRGB())//this loop considers the pixels above the current pixel
            {
            	image.setRGB(xmove,ymove,Color.white.getRGB());//changing the current color to white
                update(getGraphics());
                ymove++; //moving downwards
            }
            ymove=seedy; //resetting y to original value
            xmove--; 
        }
        xmove = seedx;
        ymove = seedy;  
        while(image.getRGB(xmove, ymove) != Color.blue.getRGB())//checking if the current color is boundary or not. this loop fills the region to the right of the seed pixel
        {
        	while(image.getRGB(xmove, ymove) != Color.blue.getRGB())//this loop considers the pixels below the current pixel
        	{ 
                    image.setRGB(xmove,ymove,Color.white.getRGB());//changing the current color to white
                    update(getGraphics());
                    ymove--;
        	}
            ymove=seedy; //resetting y to original value   
            while(image.getRGB(xmove, ymove) != Color.blue.getRGB())//this loop considers the pixels above the current pixel
            {
                    image.setRGB(xmove,ymove,Color.white.getRGB());//changing the current color to white
                    update(getGraphics());
                    ymove++;
            }
            ymove=seedy;
            xmove++; 
        }
 }
    
 public static void main(String[] args) 
 {
        JFrame frame = new JFrame("FLOOD FILL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FLOOD_FILL fill = new FLOOD_FILL();
        frame.add(fill);
        frame.pack();
        frame.setVisible(true);
 }
 
 @Override
 public void mouseClicked(MouseEvent me) 
  {
        if(me.getX()>100 && me.getX() < 200 && me.getY()>100 && me.getY() < 200)
            floodfill(me.getX(),me.getY());
  }

  @Override
  public void mousePressed(MouseEvent me) 
  {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

    @Override
  public void mouseReleased(MouseEvent me) 
   {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void mouseEntered(MouseEvent me) 
   {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   @Override
   public void mouseExited(MouseEvent me) 
   {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
}