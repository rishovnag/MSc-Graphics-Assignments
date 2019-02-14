import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class ELLIPSE extends Applet implements ActionListener
{
	int a,b,X,Y,xc,yc,a2,b2,d1,d2;
	double p;
	Label l1 =new Label("X Co=Ordinate value for Center: ");
	Label l2= new Label("Y Co-Ordinate value for Center: ");
	Label l3 = new Label("X-intercept value = ");
	Label l4=new Label("Y-intercept value= ");

	TextField t1 = new TextField(3);
	TextField t2=new TextField(3);
	TextField t3=new TextField(3);
	TextField t4=new TextField(3);
	
	Button c = new Button("DRAW");

	public void init()
	{
		add(l1);
		add(t1);
		add(l2);
		add(t2);		
		add(l3);
		add(t3);
		add(l4);
		add(t4);
		add(c);
		c.addActionListener(this);
	 }

	public void paint (Graphics g)
	{
		xc=Integer.parseInt(t1.getText());
		yc=Integer.parseInt(t2.getText());
		a=Integer.parseInt(t3.getText());
		b=Integer.parseInt(t4.getText());
		X=0;
		Y=b;
		g.drawString(".",xc+X,yc+Y);
		g.drawString(".",xc-X,yc+Y);
		g.drawString(".",xc+X,yc-Y);
		g.drawString(".",xc-X,yc-Y);
		a2=a*a;
		b2=b*b; 
		d1=Math.round(b2-(a2*b)+ Math.round(0.25*a2)); 
		while(a2*(Y-0.5)>b2*(X+1))
		{
			X=X+1;
			if(d1<0)
			{
				d1=d1+b2*(2*X+3);
			}
			else
			{
				d1=d1+b2*(2*X+3)+a2*(2-2*Y);
				Y=Y-1;
			}
			g.setColor(Color.BLUE);
			g.drawString(".",xc+X,yc+Y);
			g.drawString(".",xc-X,yc+Y);
			g.drawString(".",xc+X,yc-Y);
			g.drawString(".",xc-X,yc-Y);
		}
		d2=Math.round(b2*Math.round((X+0.5)*(X+0.5))+(a2*(Y-1)*(Y-1))-a2*b2);
		while(Y>0)
		{
			Y=Y-1;
			if(d2<0)
			{
				d2=d2+b2*(2*X+2)+a2*(3-2*Y);
				X=X+1;
			}
			else
			{
				d2=d2+a2*(3-2*Y);
			}		
			g.setColor(Color.BLUE);
			g.drawString(".",xc+X,yc+Y);
			g.drawString(".",xc-X,yc+Y);
			g.drawString(".",xc+X,yc-Y);
			g.drawString(".",xc-X,yc-Y);	 
		}
	}
	public void actionPerformed(ActionEvent a)
	{
		repaint();
	}
}
