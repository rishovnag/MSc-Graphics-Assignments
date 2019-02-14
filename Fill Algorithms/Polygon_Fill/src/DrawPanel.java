import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;


public class DrawPanel extends javax.swing.JPanel {
    
    private Vector<Point> polygon;      
    
    private boolean isPolyClosed = false;   
    
    int mouseX = -10;           
    int mouseY = -10;               
    
    public DrawPanel(int width, int height) 
    {
        this.setLayout(null);
        this.setBounds(0, 0, width, height);
        this.setBackground(new Color(255, 156, 50));
        
        polygon = new Vector<Point>();

        this.addMouseMotionListener(new MouseAdapter()
        {
           public void mouseMoved(MouseEvent event)
           {
               mouseX = event.getX();
               mouseY = event.getY();
               if (!isPolyClosed)
                repaint();
           }
        });
        
        this.addMouseListener(new MouseAdapter()
        {
            public void mouseReleased(MouseEvent event)
            {
                if (isPolyClosed) return;
                
                if ( (polygon.size() > 2) && 
                     (Math.abs(event.getX() - polygon.firstElement().x) < 5) &&
                     (Math.abs(event.getY() - polygon.firstElement().y) < 5) )
                {
                    isPolyClosed = true;
                    polygon.add(polygon.firstElement());
                    System.out.println("Polygon closed");
                }
                else
                {
                    polygon.add(new Point(event.getX(), event.getY()));
                }
                repaint();
            }
        });
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
                
        Point p;
        for (int i = 0; i < polygon.size(); i++)
        {
            p = polygon.get(i);
            g.fillRect(p.x - 2, p.y - 2, 5, 5);
        }
        
        if (polygon.size() < 1) return;
        Point p2;
        for (int i = 0; i < polygon.size() - 1; i++)
        {
            p = polygon.get(i);
            p2 = polygon.get(i+1);
            g.drawLine(p.x, p.y, p2.x, p2.y);
        }
        
        
        if (this.isPolyClosed) return;
        

        g.drawLine(polygon.lastElement().x, polygon.lastElement().y, mouseX, mouseY);
        
        if ( (polygon.size() > 2) && 
             (Math.abs(mouseX - polygon.firstElement().x) < 5) &&
             (Math.abs(mouseY - polygon.firstElement().y) < 5) )
        {
            g.drawString("Closing polygon", mouseX + 5, mouseY + 5);
        }
    }

    public Edge[] createEdges()
    {
        Edge[] sortedEdges = new Edge[polygon.size()-1];
        for (int i = 0; i < polygon.size() - 1; i++)
        {
            if (polygon.elementAt(i).y < polygon.elementAt(i+1).y)
                sortedEdges[i] = new Edge(polygon.elementAt(i), polygon.elementAt(i+1));
            else
                sortedEdges[i] = new Edge(polygon.elementAt(i+1), polygon.elementAt(i));
        }
        return sortedEdges;
    }
    
    
    public void FillPolygon()
    {
        if (!this.isPolyClosed) return;
        Edge[] sortedEdges = this.createEdges();
       
        Edge tmp;
        
        for (int i = 0; i < sortedEdges.length - 1; i++)
            for (int j = 0; j < sortedEdges.length - 1; j++)
            {
                if (sortedEdges[j].p1.y > sortedEdges[j+1].p1.y) 
                {
                   
                    tmp = sortedEdges[j];
                    sortedEdges[j] = sortedEdges[j+1];
                    sortedEdges[j+1] = tmp;
                }  
            }
        
        
        int scanlineEnd = 0;
        for (int i = 0; i < sortedEdges.length; i++)
        {
            if (scanlineEnd < sortedEdges[i].p2.y)
                scanlineEnd = sortedEdges[i].p2.y;
        }
  
        int scanline = sortedEdges[0].p1.y;
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        Graphics g = this.getGraphics();
        
        
        for (scanline = sortedEdges[0].p1.y; scanline <= scanlineEnd; scanline++)
        {
        
            
            list.clear();
            
        
            for (int i = 0; i < sortedEdges.length; i++)
            {   
                
        
                if (scanline == sortedEdges[i].p1.y) 
                {
                    if (scanline == sortedEdges[i].p2.y)
                    {
        
                        sortedEdges[i].deactivate();
                        list.add((int)sortedEdges[i].curX);
                    }
                    else
                    {
                        sortedEdges[i].activate();
        
        
                    }
                }
                
        
                if (scanline == sortedEdges[i].p2.y)
                {
                    sortedEdges[i].deactivate();
                    list.add((int)sortedEdges[i].curX);
                }
                
        
                if (scanline > sortedEdges[i].p1.y && scanline < sortedEdges[i].p2.y)
                {
                    sortedEdges[i].update();
                    list.add((int)sortedEdges[i].curX);
                }
                
            }
            
        
            int swaptmp;
            for (int i = 0; i < list.size(); i++)
                for (int j = 0; j < list.size() - 1; j++)
                {
                    if (list.get(j) > list.get(j+1))
                    {
                        swaptmp = list.get(j);
                        list.set(j, list.get(j+1));
                        list.set(j+1, swaptmp);
                    }
                
                }
            
            g.setColor(Color.black);

            if (list.size() < 2 || list.size() % 2 != 0) 
            {
                System.out.println("Please reconsider the inputs...");
                continue;
            }
             
        
            for (int i = 0; i < list.size(); i+=2)
            {
                g.drawLine(list.get(i), scanline, 
                           list.get(i+1), scanline);
            }
            
        }

    }

    public void Reset()
    {
        polygon.clear();
        isPolyClosed = false;
        this.repaint();
    }
    
    public boolean isPolygonClosed() { return this.isPolyClosed; }
}
