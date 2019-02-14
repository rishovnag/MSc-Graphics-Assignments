import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings({ "unused", "serial" })
public class PolygonApplet extends javax.swing.JApplet {
    
    public static final int WIDTH = 600;
    public static final int HEIGHT =600;
   
    private DrawPanel drawPanel;
    

    public void init() {
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        setSize(WIDTH, HEIGHT);
        
        drawPanel = new DrawPanel(WIDTH, HEIGHT - 50);
        this.add(drawPanel);
        drawPanel.setBackground(Color.YELLOW);
        
        jPanel1.setBounds(0, HEIGHT - 50, WIDTH, 50);
        

        this.FillPolyBut.addActionListener(new ActionListener()
        {		
        
            public void actionPerformed(ActionEvent evt)
            {
                drawPanel.FillPolygon();
            }
        });        
        

        this.ClearPolyBut.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                drawPanel.Reset();
            }
        });
        
    }
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        FillPolyBut = new javax.swing.JButton();
        ClearPolyBut = new javax.swing.JButton();

        getContentPane().setLayout(null);
        
        jPanel1.setLayout(null);
        jPanel1.setBackground(Color.blue);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        FillPolyBut.setText("Fill Polygon");
        jPanel1.add(FillPolyBut);
        FillPolyBut.setBounds(50, 15, 130, 30);


        ClearPolyBut.setText("Clear Polygon");
        jPanel1.add(ClearPolyBut);
        ClearPolyBut.setBounds(350, 15, 130, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 50, 400, 100);

    }
    private javax.swing.JButton ClearPolyBut;
    private javax.swing.JButton FillPolyBut;
    private javax.swing.JPanel jPanel1;
    
    
}
