package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class panelReloj extends JPanel implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	private JLabel seg1,seg0;
	private JLabel min1,min0;
	private JLabel hs1,hs0;
	private JLabel separador1,separador0;
	private Timer t;
    private int h, m, s;
    private String[] rutaImagenes;
    
    public panelReloj() {
    	h = 0;m = 0; s = 0;
    	rutaImagenes = new String[11];
    	cargarImagenes();
    	initComponents();
        t = new Timer(1000, this);
    }
    
    private void initComponents() 
    {
    	seg0 = new JLabel();
    	seg1 = new JLabel();
    	min0 = new JLabel();
    	min1 = new JLabel();
    	hs0 = new JLabel();
    	hs1 = new JLabel();
    	separador0 = new JLabel();
    	separador1 = new JLabel();
    	
    	this.setBackground(Color.black); 
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setLayout(new GridLayout(0, 8));
        
        ImageIcon cero = new ImageIcon(getClass().getResource(rutaImagenes[0]));
        ImageIcon logo = new ImageIcon(getClass().getResource(rutaImagenes[10]));
        
        hs1.setBackground(Color.black);
        hs1.setIcon(new ImageIcon(cero.getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
        add(hs1);
        hs1.setBounds(5,0,12,12);
        
        hs0.setBackground(Color.black);
        hs0.setIcon(new ImageIcon(cero.getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
        add(hs0);
        hs0.setBounds(19,0,12,12);
        
        separador1.setBackground(Color.black);
     	separador1.setIcon(new ImageIcon(logo.getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
        add(separador1);
        separador1.setBounds(33,0,12,12);
        
        min1.setBackground(Color.black);
        min1.setIcon(new ImageIcon(cero.getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
        add(min1);
        min1.setBounds(47,0,12,12);
        
        min0.setBackground(Color.black);
        min0.setIcon(new ImageIcon(cero.getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
        min0.setBounds(61,0,12,12);
        add(min0);
        
        separador0.setBackground(Color.black);
     	separador0.setIcon(new ImageIcon(logo.getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
        add(separador0);
        separador0.setBounds(75,0,12,12);
        
        seg1.setBackground(Color.black);
        seg1.setIcon(new ImageIcon(cero.getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
        add(seg1);
        seg1.setBounds(89,0,12,12);
        
        seg0.setBackground(Color.black);
        seg0.setIcon(new ImageIcon(cero.getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
        add(seg0);
        seg0.setBounds(103,0,12,12);
  
    }
    
    private void cargarImagenes() {
    	
    	for(int i = 0;i < rutaImagenes.length;i++)
    	{
    		rutaImagenes[i] = "/img/digit"+i+".png";
    	}
    }
       
    private void actualizarLabel(JLabel label1,JLabel label2,int n) 
    {
    	int d1= n%10,d2 =n/10;
    	ImageIcon image = new ImageIcon(getClass().getResource(rutaImagenes[d1]));
      	label1.setIcon(new ImageIcon(image.getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
      	label1.repaint();
      	ImageIcon image2 = new ImageIcon(getClass().getResource(rutaImagenes[d2]));
      	label2.setIcon(new ImageIcon(image2.getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH)));
      	label2.repaint();
    }
        
    public void iniciar() 
    {
    	t.start();
    }

    public void parar() 
    {
    	if(t.isRunning()) 
        {
    		t.stop();
        }
    }

	@Override
	public void actionPerformed(ActionEvent arg0)
	{   
		++s;
        if(s == 60) 
        {
            s = 0;
            ++m;
        }
        if(m == 60)
        {
            m = 0;
            ++h;
        }	
        actualizarLabel(seg0,seg1,s);
        actualizarLabel(min0,min1,m);
        actualizarLabel(hs0,hs1,h);
	}
}
