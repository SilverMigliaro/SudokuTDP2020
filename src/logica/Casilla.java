package logica;

public class Casilla
{
	private Integer color;
	private Integer valor;
	private EntidadGrafica grafico;
	
	public Casilla()
	{
		this.valor = 0;
		this.grafico = new EntidadGrafica(20,"file/imagenes.txt");
	}

	/*
	 * Retorna el valor que almacena la casilla.
	 */
	public Integer getValor() 
	{
		return this.valor;
	}
	
	/*
	 * Setea el valor que tiene la casilla.
	 */
	public void setValor(Integer valor) 
	{
		if(valor != null) {
			
			this.valor = valor;
			this.grafico.actualizarGrafico(this.valor+color);
		}
	}
	
	/*
	 * Brinda el recurso grafico de la casilla.
	 */
	public EntidadGrafica getGrafica() 
	{
		return this.grafico;
	}
	
	/*
	 * Setea una entidad grafica
	 */
	public void setGrafica(EntidadGrafica e)
	{
		this.grafico = e;
	}

	/*
	 * Setea el color del recurso grafico de la casilla.
	 */
	public void setColor(Integer color)
	{
		this.color = color;
	}
	
	
}
