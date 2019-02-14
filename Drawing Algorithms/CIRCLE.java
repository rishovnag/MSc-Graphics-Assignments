import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class CIRCLE extends Applet implements ActionListener 
{
	int r,X,Y,xc,yc;
	double p;
	Label l2 =new Label("X Co=Ordinate value for origin: ");
	Label l3= new Label("Y Co-Ordinate value for origin: ");
	Label l1 = new Label("Radius of the Circle= ");

	TextField t1 = new TextField(3);
	TextField t2=new TextField(3);
	TextField t3=new TextField(3);
	
	Button b = new Button("DRAW");

	public void init()
	{
		add(l1);
		add(t1);
		add(l2);
		add(t2);
		add(l3);
		add(t3);
		add(b);
		b.addActionListener(this);
	 }

	public void paint (Graphics g)
	{
		r=Integer.parseInt(t1.getText());
		xc=Integer.parseInt(t2.getText());
		yc=Integer.parseInt(t3.getText());
		X=0;
		Y=r;
		p=1-r;
		//g.setColor(Color.Red);
		g.drawString(".",X,Y);
		while(X<Y)
		{
			X++;
			if(p<0)
			{	 
				p=p+2*X+3;
			}
			else
			{	 
				Y=Y-1;
				p=p+2*X-2*Y+5;
			}
			g.setColor(Color.BLUE);
			g.drawString(".",xc+X,yc+Y);
			g.drawString(".",xc-X,yc+Y);
			g.drawString(".",xc+X,yc-Y);
			g.drawString(".",xc-X,yc-Y);
			g.drawString(".",xc+Y,yc+X);
			g.drawString(".",xc-Y,yc+X);
			g.drawString(".",xc+Y,yc-X);
			g.drawString(".",xc-Y,yc-X);
		}
	}
	public void actionPerformed(ActionEvent a)
	{
		repaint();
	}
}