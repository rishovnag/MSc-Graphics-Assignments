import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

public class CohenSutherland extends Applet implements ActionListener
{          
	int x1,x2,y1,y2,step,dx,dy,p,X,Y,xmin,xmax,ymin,ymax;      
	
	Label l1 = new Label("X1 = ");
	Label l2 = new Label("X2 = ");
	Label l3 = new Label("Y1 = ");
	Label l4 = new Label("Y2 = ");
    Label l6 = new Label("Xmin = ");
    Label l7 = new Label("Xmax = ");
    Label l8 = new Label("Ymin = ");
    Label l9 = new Label("Ymax = ");
        
	TextField t1 = new TextField(3);
	TextField t2 = new TextField(3);
	TextField t3 = new TextField(3);
	TextField t4 = new TextField(3);
    TextField t5 = new TextField(3);
    TextField t6 = new TextField(3);
    TextField t7 = new TextField(3);
    TextField t8 = new TextField(3);
    Button b = new Button("DRAW");
        
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
    	add(l6);
    	add(t5);
    	add(l7);
    	add(t6);
    	add(l8);
    	add(t7);
    	add(l9);
    	add(t8);
    	add(b);
    	b.addActionListener(this);
    }
    
    public int[] set(int x,int y) //setting the region code
    {
    	int arr[]=new int[4];
    	if(x<xmin)
    		arr[3]=1;
    	else
    		arr[3]=0;
    	if(x>xmax)
    		arr[2]=1;
    	else
    		arr[2]=0;
    	if(y<ymin)
    		arr[0]=1;
    	else
    		arr[0]=0;
    	if(y>ymax)
    		arr[1]=1;
    	else
    		arr[1]=0;
    	return arr;
    }
    
    boolean check(int a[])
    {
    	for(int i=0;i<a.length;i++)
    		if(a[i]==1)
    			return false;
    	return true;
    }
   
    int[] produceXY(int i,int x1,int y1,float m) // here the lines ar calculated
    {
    	int arr[]=new int [2];
    	float x=0,y=0;
    	switch(i)//making the lines
    	{
    		case 0:
    			x=xmin;
    			y=y1+m*(x-x1);
    			break;
    		case 1:
    			x=xmax;          
    			y=y1+m*(x-x1);
    			break;
    		case 3:
    			y=ymin;
    			x=x1+(y-y1)/m;
    			break;
    		case 2:
    			y=ymax;
    			x=x1+(y-y1)/m;
    			break;
    	}
    	arr[0]=(int)x;
    	arr[1]=(int)y;
    	return arr;
    }
    
    boolean doAnd(int a[],int b[]) //bitwise operation
    {
    	for(int i=0;i<a.length;i++)
    	{
    		int answer=a[i]&b[i];
    		if(answer==1)
    			return false;
    	}
    	return true;
    }
    
    @Override
    public void paint(Graphics g)
    {
    	if(!t1.getText().equals("")) x1=Integer.parseInt(t1.getText());
    	if(!t2.getText().equals("")) y1=Integer.parseInt(t3.getText());
    	if(!t3.getText().equals("")) x2=Integer.parseInt(t2.getText());
    	if(!t4.getText().equals("")) y2=Integer.parseInt(t4.getText());
    	if(!t5.getText().equals("")) xmin=Integer.parseInt(t5.getText());
    	if(!t6.getText().equals("")) xmax=Integer.parseInt(t6.getText());
    	if(!t7.getText().equals("")) ymin=Integer.parseInt(t7.getText());
    	if(!t8.getText().equals("")) ymax=Integer.parseInt(t8.getText());
    	if(!t5.getText().equals("") && !t6.getText().equals("") && !t7.getText().equals("") && !t8.getText().equals("") )
    	{ 
    		g.drawRect(xmin,ymin,xmax-xmin,ymax-ymin);
    		g.drawRect(xmin+100,ymin,xmax-xmin,ymax-ymin);
    	}
    	int a[][]=new int[2][4];
    	int b[][]=new int[2][4];
    	int c[]=new int[2];
    	int c1=20;		
    	float m=(y2-y1)*1.0f/(x2-x1);
    	g.drawLine(x1,y1,x2,y2);
    	a[0]=set(x1,y1);
    	a[1]=set(x2,y2);
    	if(check(a[0])&&check(a[1]))
    	{
    		g.drawLine(x1,y1,x2,y2);
    	}
    	else
    	{
    		if(doAnd(a[0],b[0]))
    		{
    			for(int i=a[0].length-1;i>=0;i--)
    			{
    				if(a[0][i]==1)
    				{
    					c=produceXY(a[0].length-1-i,x1,y1,m);
    					b[0]=set(c[0],c[1]);
    			
    					if(check(b[0]))
    					{
    						x1=c[0];
    						y1=c[1];
    						break;
    					}
    					c1+=20;
    				}
    			}
    			for(int i=a[0].length-1;i>=0;i--)
    			{
    				if(a[1][i]==1)
    				{
    					c=produceXY(a[0].length-1-i,x1,y1,m);
    					b[1]=set(c[0],c[1]);
    					if(check(b[1]))
    					{
    						x2=c[0];
    						y2=c[1];
    						break;
    					}
    				}
    			}
    			g.drawLine(x1+100,y1,x2+100,y2);
    		}
    	}
    }
    
    public void actionPerformed(ActionEvent a)
    {
    	repaint();
	}
}	