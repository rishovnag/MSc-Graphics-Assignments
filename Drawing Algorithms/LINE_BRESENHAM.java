import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class LINE_BRESENHAM extends Applet implements ActionListener
{
	int x1,x2,y1,y2,step,dx,dy,p,X,Y;
	Label l5 = new Label("Bresenham Line Drawing Algorithm");
	Label l1 = new Label("X1 = ");
	Label l2 = new Label("Y1 = ");
	Label l3 = new Label("Y1 = ");
	Label l4 = new Label("Y2 = ");

	TextField t1 = new TextField(3);
	TextField t2 = new TextField(3);
	TextField t3 = new TextField(3);
	TextField t4 = new TextField(3);
	Button b = new Button("DRAW");

	public void init()
	{
		add(l5);
		add(l1);
		add(t1);
		add(l2);
		add(t2);
		add(l3);
		add(t3);
		add(l4);
		add(t4);
		add(b);
		b.addActionListener(this);
	 }
	public void paint (Graphics g)
	{
		x1=Integer.parseInt(t1.getText());
		x2=Integer.parseInt(t3.getText());
		y1=Integer.parseInt(t2.getText());
		y2=Integer.parseInt(t4.getText());
		dx=x2-x1;
		dy=y2-y1;
		step=dx;
		p=2*dy-2*dx;
		X=x1;
		Y=y1;
		g.drawString(".",X,Y);
		for(int i=1;i<step;i++)
		{
			if(p<0)
			{
				p=p+2*dy;
				X=X+1;
			}
			else
			{
				p=p+2*dy-2*dx;
				X=X+1;
				Y=Y+1;
			}	
			g.setColor(Color.BLUE);
			g.drawString(".",X,Y);
		}
	}
	public void actionPerformed(ActionEvent a)
	{
		repaint();
	}
}