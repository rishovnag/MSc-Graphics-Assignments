import java.awt.Point;

public class Edge {
    
    public Point p1;        
    public Point p2;        
    float m;                
    
    float curX;             
    
    public Edge(Point a, Point b)
    {
        p1 = new Point(a);
        p2 = new Point(b);
        
    
        m = (float)((float)(p1.y - p2.y) / (float)(p1.x - p2.x));
    }
    
    public void activate()
    {
        curX = p1.x;
    }
    public void update()
    {
        curX += (float)((float)1/(float)m);
    }
    
    public void deactivate()
    {
        curX = p2.x;
    }
    
}
