
import java.awt.Canvas;
import java.awt.Graphics;


public class rotate_shape_about_fixed_point extends Canvas {
    static double rot[][]=new double[2][2];
    static int m[][]=new int[2][3];
    static int t[][]=new int[2][3];
    static int trans[]=new int[2];
    rotate_shape_about_fixed_point(int x0,int y0,int x1,int y1,int x2,int y2,double deg)
 {
    rot[0][0]=Math.cos(deg);
    rot[0][1]=(Math.sin(deg)*-1);
    rot[1][0]=Math.sin(deg);
    rot[1][1]=Math.cos(deg);
    m[0][0]=x0;
    m[0][1]=x1;
    m[0][2]=x2;
    m[1][0]=y0;
    m[1][1]=y1;
    m[1][2]=y2;
    trans[0]=x0;
    trans[1]=y0;
 }
    public void paint(Graphics g)
  {
     g.drawLine(m[0][0], m[1][0],m[0][1],m[1][1]);
    g.drawString("A["+m[0][0]+"]["+m[1][0]+"]",(m[0][0]+4) , m[1][0]);
     g.drawLine(m[0][1],m[1][1],m[0][2], m[1][2]);
     g.drawString("B["+m[0][1]+"]["+m[1][1]+"]",(m[0][1]+4) , m[1][1]);
     g.drawLine(m[0][2], m[1][2],m[0][0],m[1][0]);
     g.drawString("C["+m[0][2]+"]["+m[1][2]+"]",(m[0][2]+4) , m[1][2]);
     for(int i=0;i<2;i++)
	 {
		 for(int j=0;j<2;j++)
		 {
			 t[i][j]=0;
		 }
	 }
     for(int i=0;i<3;i++)
     {
         m[0][i]=m[0][i]-trans[0];
         m[1][i]=m[1][i]-trans[1];
     }
     for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                for (int k = 0; k < 2; k++)
                {
                    t[i][j] = t[i][j] +(int)(rot[i][k] * m[k][j]);
                }
            }
        }
      for(int i=0;i<3;i++)
     {
        t[0][i]=t[0][i]+trans[0];
         t[1][i]=t[1][i]+trans[1];
     }
     g.drawLine(t[0][0], t[1][0],t[0][1],t[1][1]);
     g.drawString("A`["+t[0][0]+"]["+t[1][0]+"]",(t[0][0]+4) , t[1][0]);
     g.drawLine(t[0][1],t[1][1],t[0][2], t[1][2]);
     g.drawString("B`["+t[0][1]+"]["+t[1][1]+"]",(t[0][1]+4) , t[1][1]);
     g.drawLine(t[0][0], t[1][0],t[0][2],t[1][2]);
     g.drawString("C`["+t[0][2]+"]["+t[1][2]+"]",(t[0][2]+4) , t[1][2]);
  }
    
}
