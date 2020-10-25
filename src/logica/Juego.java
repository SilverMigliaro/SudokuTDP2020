package logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class Juego
{
	private final static int SIZE = 9;
	private final static int COLOR_CLARO = 0;
	private final static int COLOR_OSCURO = 10;
	private Casilla [][] grilla;
	private Integer [][] solucion;
	private int cantidad_errores;
	private boolean juegoValido;
	
	public Juego() 
	{
		this.grilla = new Casilla[SIZE][SIZE];
		this.solucion = new Integer[SIZE][SIZE];
		this.cantidad_errores = 0;
		cargarSolucion("file/solucion.txt");
		this.juegoValido = validarJuego();
		if(juegoValido) 
		{	
			eliminarCasillas();
		}
		else
		{
			finalizarJuego();
		}
	}
	
	/*
	 * Carga la solucion del juego.
	 */
	private void cargarSolucion(String path) 
	{	
		try {
			InputStream in = getClass().getClassLoader().getResourceAsStream(path);
			InputStreamReader inr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(inr);
			String linea;
			int fila = 0;
			while ((linea = br.readLine()) != null)
			{
				String[]valores = linea.split(" ");
				for (int col = 0; col < valores.length; col++) 
				{
					grilla[fila][col] = new Casilla();
					solucion[fila][col] = Integer.parseInt(valores[col]);
				}
				fila++;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Verifica si la solucion del juego es valida.
	 */
	private boolean validarJuego() 
	{
		boolean resultado = true;
		
		for(int fila = 0;fila<SIZE && resultado;fila++)
		{
			for(int col = 0;col<SIZE && resultado;col++)
			{	
				if(!esCorrecto(fila,col,solucion[fila][col]))
				{
					resultado = false;
				}
				else
				{
					int color = (((fila/3)+(col/3))%2 == 0)?COLOR_CLARO:COLOR_OSCURO;
					grilla[fila][col].setColor(color);	
					grilla[fila][col].setValor(solucion[fila][col]);
				}
			}
		}
		
		return resultado;
	}
	
	/*
	 * Elimina el valor de casillas al azar. 
	 */
	private void eliminarCasillas() 
	{
		Random r = new Random();
		for(int i = 0;i<SIZE;i++)
		{
			int cantEliminar = r.nextInt(3)+4;
			
			while(cantEliminar>0)
			{
				int j = r.nextInt(9);
				int aux = grilla[i][j].getValor();
				if(aux!= 0)
				{
					grilla[i][j].setValor(0);
					cantEliminar--;
				}
				
			}
		}
	}
	
	/*
	 * Verifica si la juegada se puede realizar.
	 */
	private boolean esCorrecto(int fila,int col,int valor)
    {
		return !estaEnFila(fila,valor) && !estaEnColumna(col,valor) && !estaEnCuadrante(fila,col,valor);
    }

	/*
	 * Retorna si la partida es valida o no.
	 */
	public boolean juegoEsValido()
	{
		return juegoValido;
	}
	
	/*
	 * Actualiza el valor de una casilla.
	 */
	public void actualizarValor(Casilla c,int valor) 
	{
		c.setValor(valor);
		
	}
	
	/*
	 * Retorna una casilla en particular.
	 */
	public Casilla getCasilla(int i, int j) 
	{
		return this.grilla[i][j];
	}
	
	/*
	 * Retorna la cantidad de errores cometidos.
	 */
	public int getErrores()
	{
		return cantidad_errores;
	}
	
	/*
	 * Actualiza la cantidad de errores cometidos.
	 */
	public void actualizarErrores() 
	{
		cantidad_errores++; 
	}
	
	/*
	 * Retorna la cantidad de filas.
	 */
	public int getCantFilas() 
	{
		return SIZE;
	}
	
	/*
	 * Retorna la cantidad de columnas.
	 */
	public int getCantColumnas() 
	{
		return SIZE;
	}
	
	/*
	 * Verifica si la casilla esta vacia.
	 */
    public boolean estaVaciaCelda(int i,int j)
    { 
    	return grilla[i][j].getValor() == 0;
    }

    /*
     * Verifica si no hay numeros repetidos en la misma fila.
     */
    public boolean estaEnFila(int fila,int valor)
    {
    	boolean resultado = false;
        for(int i=0;i<SIZE && !resultado;i++)
        {
        	if(grilla[fila][i].getValor() == valor)
            {
        		resultado = true;
            }
        }
        	return resultado;
    }
     
    /*
     * Verifica si no hay numeros repetidos en la misma columna.
	*/
    public boolean estaEnColumna(int col,int valor)
    {
    	boolean resultado = false;
        for(int i=0;i<SIZE && !resultado;i++)
        {
        	if(grilla[i][col].getValor() == valor)
            {
        		resultado = true;
            }
         }
        return resultado;
    }
     
    /*
     * Verifica que dentro de un mismo panel no haya numeros repetidos.
     */
    public boolean estaEnCuadrante(int fila,int col,int valor)
    {
    	boolean resultado = false;
        int f = fila - fila%3;
        int c = col -col%3;
            
        for(int i = f; i<f+3 && !resultado;i++)
        {
        	for(int j = c;j<c+3 && !resultado;j++)
            {
        		if(grilla[i][j].getValor() == valor)
                {
        			resultado = true;
                }
            }
        }
        return resultado;
    }
    
    /*
     * Verifica que si se completo el tablero.
     */
    public boolean estaLlenaGrilla()
    {
    	boolean resultado = true;
        for(int i = 0;i<SIZE && resultado;i++)
        {
        	for(int j = 0;j<SIZE && resultado;j++)
        	{
        		if(grilla[i][j].getValor() == 0)
        		{
        			resultado = false;
        		}
        	}
        }
        return resultado;
    }
        
    /*
     * Finaliza finaliza la partida 
     */
    public void finalizarJuego() 
    {	
    	for(int i = 0;i<SIZE;i++)
        {
    		for(int j = 0;j<SIZE;j++)
          	{
          		grilla[i][j].setValor(null);
          		grilla[i][j].setColor(null);
          		grilla[i][j].setGrafica(null);
          		solucion[i][j] = null;
          	}
          }
          grilla = null;
          solucion = null;
    }
     
}
