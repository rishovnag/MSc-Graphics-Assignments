import java.awt.*;
	import java.applet.*;
	import java.awt.event.*;

public class Scaling extends Applet implements ActionListener {
	
		int x1,x2,y1,y2,x3,y3,sx,sy,step,dx,dy,p,X,Y;
		Label l0 = new Label("Scaling of an object(assumed the object has only 3 points");
		Label l1 = new Label("X1 = ");
		Label l2 = new Label("Y1 = ");
		Label l3 = new Label("X2 = ");
		Label l4 = new Label("Y2 = ");
		Label l5 = new Label("X3 = ");
		Label l6 = new Label("Y3 = ");
		Label l7=new Label ("Scale Factor along X direction: ");
		Label l8=new Label("Scale Factor along Y direction");
		TextField t1 = new TextField(3);
		TextField t2 = new TextField(3);
		TextField t3 = new TextField(3);
		TextField t4 = new TextField(3);
		TextField t5=new TextField(3);
		TextField t6=new TextField(3);
		TextField t7=new TextField(3);
		TextField t8=new TextField(3);
		
		Button b = new Button("DRAW");

		public void init(){
			add(l0);
		 add(l1);
		 add(t1);
		 add(l2);
		 add(t2);
		 add(l3);
		 add(t3);
		 add(l4);
		 add(t4);
		 add(l5);
		 add(t5);
		 add(l6);
		 add(t6);
		 add(l7);
		 add(t7);
		 add(l8);
		 add(t8);
		 add(b);
		 b.addActionListener(this);
		 }
		
		//Graphics g;
public void paint(Graphics g)
{
	 x1=Integer.parseInt(t1.getText());
	 x2=Integer.parseInt(t3.getText());
	 y1=Integer.parseInt(t2.getText());
	 y2=Integer.parseInt(t4.getText());
	 x3=Integer.parseInt(t5.getText());
	 y3=Integer.parseInt(t6.getText());
	 sx=Integer.parseInt(t7.getText());
	 sy=Integer.parseInt(t8.getText());
	 g.drawLine(x1,y1,x2,y2);
	 g.drawLine(x1,y1,x3,y3);
	 g.drawLine(x2,y2,x3,y3);
	 int[][] s;
	 s=new int[2][2];
	 s[0][0]=sx;
	 s[0][1]=0;
	 s[1][0]=0;
	 s[1][1]=sy;
	 int[][] c;
	 c=new int[2][3];
	 for(int i=0;i<2;i++)
	 {
		 for(int j=0;j<2;j++)
		 {
			 c[i][j]=0;
		 }
	 }
	 int[][] a;
	 a=new int[2][3];
	 a[0][0]=x1;
	 a[0][1]=x2;
	 a[0][2]=x3;
	 a[1][0]=y1;
	 a[1][1]=y2;
	 a[1][2]=y3;
	 for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                for (int k = 0; k < 2; k++)
                {
                    c[i][j] = c[i][j] + s[i][k] * a[k][j];
                }
            }
        }
	 g.drawLine(c[0][0],c[1][0],c[0][1],c[1][1]);
	 g.drawLine(c[0][1],c[1][1],c[0][2],c[1][2]);
	 g.drawLine(c[0][0],c[1][0],c[0][2],c[1][2]);
}

}