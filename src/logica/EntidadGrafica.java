package logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;

public class EntidadGrafica
{
	private ImageIcon grafico;
	private String[] imagenes;
	
	public EntidadGrafica(int n,String path)
	{
		this.grafico = new ImageIcon();
		this.imagenes = new String[n];
        cargarImagenes(path);       
	}
	
	/*
	 * Actualiza el recurso grafico de la entidad grafica.
	 */
	public void actualizarGrafico(int indice)
	{
		ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[indice]));
		this.grafico.setImage(imageIcon.getImage());
	}
	
	/*
	 * Retorna el recurso grafico que esta la entidad grafica. 
	 */
	public ImageIcon getGrafico()
	{
		return this.grafico;
	}

	/*
	 * Carga la los recursos graficos a la entidad grafica.
	 */
    private void cargarImagenes(String path)
    {        
        try 
        {
        	InputStream in = getClass().getClassLoader().getResourceAsStream(path);
			InputStreamReader inr = new InputStreamReader(in);
        	///FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(inr);
			String linea;
			while ((linea = br.readLine()) != null)
			{
				String[]dato = linea.split(",");
                for(int i = 0;i<dato.length;i++)
                {
                 imagenes[i]= dato[i];
                }
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}    
   }
	
}
