package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import logica.Juego;


import java.awt.event.ActionEvent;

public class ventanaJuego extends javax.swing.JFrame
{
	private static final long serialVersionUID = 1L;
	private JButton botonIniciar;  
	private JButton botonReiniciar;
	private JLabel titulo;
	private JLabel textoTiempo;
	private JLabel cantidad_errores;
	private JLabel textoErrores;
	private panelReloj relojDigital;
	private JPanel tablero;
	private Juego juego;
	private	int posX,posY;
	private JLabel[][] casillas;
	private final static int POS_NULL = -1;
	private final static int ERROR_SALIDA = -1;
	private final static int GANO = 1;
	private final static int PERDIO = 0;
	
    public ventanaJuego()
    {
    	juego = new Juego();
    	posX = POS_NULL;
    	posY = POS_NULL;
        iniciarComponentes(); 
    }
    
           
    private void iniciarComponentes() 
    {
    	//Inicialización del panel.
    	tablero = new JPanel();
    	relojDigital = new panelReloj();
    	//Inicialización de las etiquetas.
        titulo = new JLabel();
        textoTiempo = new JLabel();
        textoErrores = new JLabel();
        cantidad_errores = new JLabel();
        //Inicializacion de botones.
        botonIniciar = new JButton();
        botonReiniciar = new JButton();
        
        //Personalización del frame.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        getContentPane().setBackground(Color.BLACK);
        setPreferredSize(new Dimension(462, 640));
        setResizable(false);
        getContentPane().setLayout(null);
        this.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0) {
				switch(arg0.getKeyCode()) {
				
					case KeyEvent.VK_1:{
						insertarElemento(1);
						break;
					}
					case KeyEvent.VK_2:{
						insertarElemento(2);
						break;
					}
					case KeyEvent.VK_3:{
						insertarElemento(3);
						break;
					}
					case KeyEvent.VK_4:{
						insertarElemento(4);
						break;
					}
					case KeyEvent.VK_5:{
						insertarElemento(5);
						break;
					}
					case KeyEvent.VK_6:{
						insertarElemento(6);
						break;
					}
					case KeyEvent.VK_7:{
						insertarElemento(7);
						break;
					}
					case KeyEvent.VK_8:{
						insertarElemento(8);
						break;
					}
					case KeyEvent.VK_9:{
						insertarElemento(9);
						break;
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyTyped(KeyEvent arg0) {}

        });
       
        this.setFocusable(true);
        
        //Personalización del panel
        tablero.setBackground(Color.darkGray); 
        tablero.setBorder(BorderFactory.createEtchedBorder());
        tablero.setLayout(new GridLayout(9, 0));
        tablero.addMouseListener(new MouseListener() {
        	 public void mouseClicked(MouseEvent e) 
        	 {
        		posX = e.getX()/48;
        		posY = e.getY()/48; 
        	 }
			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
        });
        tablero.setFocusable(true);
        getContentPane().add(tablero);
        tablero.setBounds(15, 50, 432, 432);
        
        //Personalización de la etiqueta del titulo.
        ImageIcon logo = new ImageIcon(getClass().getResource("/img/sudoku_logo.png"));
     	titulo.setIcon(new ImageIcon(logo.getImage().getScaledInstance(150, 30, Image.SCALE_SMOOTH)));
        getContentPane().add(titulo);
        titulo.setBounds(156, 10, 150, 30);

        //Personalización panel relojDigital.
        getContentPane().add(relojDigital);
        relojDigital.setBounds(55, 522, 120, 20);
        
        //Personalizacion texto de errores.
        textoErrores.setFont(new Font("Ubuntu", 1, 15));
        textoErrores.setForeground(Color.white);
        textoErrores.setText("Errores cometidos:");
        getContentPane().add(textoErrores);
        textoErrores.setBounds(271,492, 200, 18);
        
        //Personalizacion de texto cantidd de errores.
        cantidad_errores.setFont(new Font("Ubuntu", 1, 16));
        cantidad_errores.setForeground(new Color(0, 100, 0));
        cantidad_errores.setText("0/3:");
        getContentPane().add(cantidad_errores);
        cantidad_errores.setBounds(321, 522, 70, 20);
        
        //Personalización etiqueta textoTiempo.
        textoTiempo.setFont(new Font("Ubuntu", 1, 15)); 
        textoTiempo.setForeground(Color.white);
        textoTiempo.setText("Tiempo trancurrido:");
        getContentPane().add(textoTiempo);
        textoTiempo.setBounds(40, 492, 150, 20);

        //Boton iniciar
        botonIniciar.setBackground(Color.black);
        botonIniciar.setFont(new Font("Ubuntu", 1, 15)); 
        botonIniciar.setForeground(Color.white);
        botonIniciar.setText("Iniciar");
        botonIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                oyenteBotonIniciar(evt);
            }
        });
        getContentPane().add(botonIniciar);
        botonIniciar.setBounds(70, 562, 90, 30);
        //Boton reiniciar
        botonReiniciar.setBackground(Color.BLACK);
        botonReiniciar.setFont(new Font("Ubuntu", 1, 15)); 
        botonReiniciar.setForeground(Color.white);
        botonReiniciar.setText("Reiniciar");
        botonReiniciar.setEnabled(false);
        botonReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                oyenteBotonReiniciar(evt);
            }
        });
        getContentPane().add(botonReiniciar);
        botonReiniciar.setBounds(291, 562, 90, 30);
        
        pack();
        this.setLocationRelativeTo(null);
    }

    /*
     * Oyente boton iniciar.
     */
    private void oyenteBotonIniciar(ActionEvent evt) 
    {   
    	cargarJuego();
    	relojDigital.iniciar();
      	this.botonIniciar.setEnabled(false); 
     	this.botonReiniciar.setEnabled(true); 
    }  

    /*
     * Oyente boton reiniciar
     */
    private void oyenteBotonReiniciar(ActionEvent evt) 
    {
        this.botonIniciar.setEnabled(true); 
      	this.botonReiniciar.setEnabled(false);
      	relojDigital.parar();
      	jugarDevuelta();  
    }
    
    /*
     * Inserta un nuevo numero en el tablero, si posible.
     */
    private void insertarElemento(int valor) {

    	if(posX != POS_NULL && posY != POS_NULL) 
    	{
    		if(juego.estaVaciaCelda(posY, posX ) && mostrarJugadaIncorrecta(posY,posX,valor)&&juego.getErrores()<3) 
    		{
    			juego.actualizarValor(juego.getCasilla(posY, posX), valor);
    	    	ImageIcon grafico = juego.getCasilla(posY, posX).getGrafica().getGrafico();
    	    	reDimensionar(casillas[posY][posX], grafico);
                casillas[posY][posX].setIcon(grafico);
    	    	posX=posY=-1;
    	    	if(juego.estaLlenaGrilla()) 
    	    	{
    	   			relojDigital.parar();
    	    		finalizoPartida(GANO);
    	    		juego.finalizarJuego();
    	    	}
    		}
    		else
			{
    			if(juego.getErrores() == 3) 
    			{
    				relojDigital.parar();
        			finalizoPartida(PERDIO);
        			juego.finalizarJuego();
    			}
			}	
    	}
    }
      
    /*
     * Muestra si hay una jugada invalidad.
     */
    public boolean mostrarJugadaIncorrecta(int fila,int col,int valor)
    {	
    	boolean opcA = juego.estaEnFila(fila, valor);
    	boolean opcB = juego.estaEnColumna(col, valor);
    	boolean opcC = juego.estaEnCuadrante(fila,col, valor);
    	Icon icono = new ImageIcon(getClass().getResource("/img/error.png"));
    	
    	if(opcA)
    	{	
    		 JOptionPane.showMessageDialog(null,"No se puede insertar el valor "+valor+",\n ya se encuentra en la misma fila "+(fila+1)+"." ,"¡¡Error!!",JOptionPane.ERROR_MESSAGE,icono);	
    	}
    	if(opcB)
    	{
    		 JOptionPane.showMessageDialog(null,"No se puede insertar el valor "+valor+",\n ya se encuentra en la misma columna "+(col+1)+"." ,"¡¡Error!!",JOptionPane.ERROR_MESSAGE,icono);	
    	}
    	if(opcC)
    	{
    		 JOptionPane.showMessageDialog(null,"No se puede insertar el valor "+valor+",\n ya se encuentra en el mismo panel" ,"¡¡Error!!",JOptionPane.ERROR_MESSAGE,icono);	
    	}
    	
    	if(opcA || opcB ||opcC)
    	{
    		juego.actualizarErrores();
    		cantidad_errores.setText(juego.getErrores()+"/3");
    	}
    	return !opcA && !opcB && !opcC;	
    }    
 
    /*
     * Se encagar de inicializar el tablero del juego, si es posible.
     */
    private void cargarJuego()
    {
    	casillas = new JLabel[juego.getCantFilas()][juego.getCantColumnas()];
    	
    	if(juego.juegoEsValido()) {
    	
	    	for (int i = 0; i <juego.getCantFilas(); i++) 
	        {
	            for(int j =0; j<juego.getCantColumnas(); j++) 
	            {
	            	ImageIcon grafico = juego.getCasilla(i, j).getGrafica().getGrafico();
	            	JLabel label = new JLabel();
	            	casillas[i][j] = label;
	            	tablero.add(label);
	            	tablero.revalidate();
	            	tablero.repaint();
					
	            	label.addComponentListener(new ComponentAdapter() { 
	                		
	            		@Override
	            		public void componentResized(ComponentEvent e)
	                    {
	            			reDimensionar(label, grafico);
	                    	label.setIcon(grafico);
	                    }	
	            	});
	            }   
	        }	
    	}
    	else
    		finalizoPartida(ERROR_SALIDA);
    }
   
    /*
     * Adecua el tamaño de un grafico.
     */
    private void reDimensionar(JLabel label, ImageIcon grafico)
    {
		Image image = grafico.getImage();
		if (image != null) {  
			Image newimg = image.getScaledInstance(label.getWidth()-5, label.getHeight()-5,  java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}
    
    /*
     * Finaliza la partida grafica.
     */
	public void finalizoPartida(int mensaje) 
	{
		String[] pathIcon = {"/img/win.png","/img/loser.png","/img/error.png"};
		Icon icono;
		int respuesta = -1;
		
		if(mensaje == ERROR_SALIDA)
		{
			icono = new ImageIcon(getClass().getResource(pathIcon[2]));
			JOptionPane.showMessageDialog(null, "No se puede iniciar juego,\nestado inicial invalido","¡¡Error!!",JOptionPane.OK_OPTION,icono);
		}
		if(mensaje == PERDIO)
		{
			icono = new ImageIcon(getClass().getResource(pathIcon[1]));
			respuesta = JOptionPane.showConfirmDialog(null,"Has perdido,\n¿desea intentarlo de vuelta?","Fin del juego",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, icono);
		}
		if(mensaje == GANO)
		{
			icono = new ImageIcon(getClass().getResource(pathIcon[0]));
			respuesta = JOptionPane.showConfirmDialog(null,"¡¡¡Felicitaciones!!!\nHas ganado,\n¿desea intentarlo de vuelta?","Fin del juego",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, icono);
		}
		
		if(respuesta == 0)
			jugarDevuelta();
		else
		{
			System.exit(respuesta);
		}	
	}
	
	/*
	 * Se crea una partida nueva.
	 */
	public void jugarDevuelta() 
	{
		this.dispose();
		ventanaJuego.main(null); 
	}
    
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ventanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventanaJuego().setVisible(true);
            }
        });
    }
}
