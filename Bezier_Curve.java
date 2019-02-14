import java.awt.*;
import java.applet.*;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
public class Bezier_Curve extends Applet
{
            Point[] controlPoints,curvePoints;
            public void init()
            {
                        controlPoints=new Point[4];
                        curvePoints=new Point[25];
                        controlPoints[0]=new Point(20,260);
                        controlPoints[1]=new Point(50,10);
                        controlPoints[2]=new Point(250,50);
                        controlPoints[3]=new Point(450,290);
                        for(int i=0;i<curvePoints.length;i++)
                                    curvePoints[i]=new Point(0,0);
            }
            public void SubDivide(Point p1,Point p2,double t)
            {
                        if(p1.x>p2.x)
                                    p1.x-=Math.abs(p1.x-p2.x)*t;
                        else
                                    p1.x+=Math.abs(p1.x-p2.x)*t;
                        if (p1.y>p2.y)
                                    p1.y-=Math.abs(p1.y-p2.y)*t;
                        else
                                    p1.y+=Math.abs(p1.y-p2.y)*t;
            }
            public void Compute()
            {
                        Point[] tmp=new Point[controlPoints.length];
                        for (int i=0; i<tmp.length; i++)
                                    tmp[i] = new Point(0,0);
                        for (int i=0; i<curvePoints.length; i++)
                        {
                                    double t = ((double) i)/(curvePoints.length-1);
                                    for (int j=0; j<controlPoints.length; j++)
                                                tmp[j]=new Point(controlPoints[j].x, controlPoints[j].y);
                                    int Depth = tmp.length;
                                    while (Depth>1)
                                    {
                                                for (int j=0; j<Depth-1; j++)
                                                            SubDivide(tmp[j], tmp[j+1], t);
                                                Depth--;
                                    }
                                    curvePoints[i]=new Point(tmp[0].x, tmp[0].y);
                        }                      
            }
            public void Draw(Graphics2D g2d)
            {
                        g2d.setColor(Color.BLUE);
                        for (int i=0; i<controlPoints.length-1; i++)
                                    g2d.drawLine((int) controlPoints[i].x,(int) controlPoints[i].y,
                                                            (int) controlPoints[i+1].x,(int) controlPoints[i+1].y);
                        GeneralPath path = new GeneralPath();                           // Bezier curve
                        g2d.setColor(Color.RED);
                        path.moveTo(curvePoints[0].x, curvePoints[0].y);
                        for (int i=1; i<curvePoints.length; i++)
                                    path.lineTo(curvePoints[i].x, curvePoints[i].y);
                        g2d.draw(path);
            }
            public void paint(Graphics g)
            {
                        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
                        Graphics2D g2d = image.createGraphics();
                        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2d.setColor(Color.WHITE);
                        g2d.fillRect(0, 0, getWidth(), getHeight());
                        Compute();
                        Draw(g2d);
                        g.drawImage(image, 0, 0, this);
            }
}