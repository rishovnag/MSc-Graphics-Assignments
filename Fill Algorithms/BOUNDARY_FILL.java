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

public class BOUNDARY_FILL extends JPanel implements MouseListener
{
    private BufferedImage image;
    private Graphics2D graphics2;
    
    public BOUNDARY_FILL()
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
    
    public void boundaryfill(int seedx,int seedy,int fill,int boundary)
    {
        int xmove = seedx,ymove = seedy;
        //first the left side of the seed pixel is filled and then the right side
        while(image.getRGB(xmove, ymove) != fill && image.getRGB(xmove, ymove) != boundary)//checking current color is boundary and fill or not
        {//this loop colors the left side of the seed pixel
        	while(image.getRGB(xmove, ymove) != fill && image.getRGB(xmove, ymove) != boundary)
        	{//here we fill the bottom portion of the pixel
                    image.setRGB(xmove,ymove,Color.white.getRGB());//changing current color to white
                    update(getGraphics());
                    ymove--; //downward move
        	}
            ymove=seedy+1; //reset y    
            while(image.getRGB(xmove, ymove) != fill && image.getRGB(xmove, ymove) != boundary)
            {//here we fill the top portion of the pixel
                    image.setRGB(xmove,ymove,Color.white.getRGB());//changing current color to white
                    update(getGraphics());
                    ymove++; //upward move
            }
            ymove=seedy; //reset y to original value
            xmove--;
        }    
        xmove = seedx+1;
        ymove = seedy;    
        while(image.getRGB(xmove, ymove) != fill && image.getRGB(xmove, ymove) != boundary)//checking current color is boundary and fill or not
        {//this loop colors the right side of the seed pixel
        	while(image.getRGB(xmove, ymove) != fill && image.getRGB(xmove, ymove) != boundary)
        	{ //here we fill the bottom portion of the pixel
                    image.setRGB(xmove,ymove,Color.white.getRGB());//changing current color to white
                    update(getGraphics());
                    ymove--; //downward movement
        	}
            ymove=seedy+1; //reset y
          	while(image.getRGB(xmove, ymove) != fill && image.getRGB(xmove, ymove) != boundary)
          	{//here we fill the bottom portion of the pixel
                    image.setRGB(xmove,ymove,Color.white.getRGB());//changing current color to white
                    update(getGraphics());
                    ymove++;//upward movement
          	}
            ymove=seedy; //resetting y to oroginal value
            xmove++; 
        }
    }
    
public static void main(String[] args) 
 {
        JFrame frame = new JFrame("BOUNDARY FILL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BOUNDARY_FILL fill = new BOUNDARY_FILL();
        frame.add(fill);
        frame.pack();
        frame.setVisible(true);
 }

 @Override
 public void mouseClicked(MouseEvent me) 
 {
	 if(me.getX()>100 && me.getX() < 200 && me.getY()>100 && me.getY() < 200)
        boundaryfill(me.getX(),me.getY(),Color.white.getRGB(),Color.blue.getRGB());
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