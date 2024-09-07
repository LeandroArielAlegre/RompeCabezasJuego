package FormControl;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JOptionPane;

public class ControladorImagen
{
	private BufferedImage[][] MatrizDesordenada;
	private BufferedImage[][] MatrizOrdenada;
	private int filaInicialCasilleroVacio = 2;
	private int columnaInicialasilleroVacio = 2;

	public ControladorImagen() 
	{

	}

	public  void imprimirGane() 
	{
		JOptionPane.showMessageDialog(null, "Juego finalizado, Ganaste!!!!!1!!1");
	}

	public void mezclarMatriz() 
	{
		Random rand = new Random();
		String[] movimientos = {"w", "a", "s", "d"};

		for (int cantidadMovimientosAleatorios = 0; cantidadMovimientosAleatorios < 1000; cantidadMovimientosAleatorios++) 
		{
			String movimiento = movimientos[rand.nextInt(movimientos.length)];
			desplazarmeEnMatriz(movimiento, 0);
		}
	}

	public BufferedImage[][] cortarImagen(BufferedImage imagen,String esMatrizOrdenadaODesordenada) 
	{
		int filas = 3;
		int columnas = 3;
		int ancho = imagen.getWidth() / columnas;
		int alto = imagen.getHeight() / filas;
		if(esMatrizOrdenadaODesordenada.equals("desordenada")) 
		{
			this.MatrizDesordenada = new BufferedImage[filas][columnas];
			for (int i = 0; i < filas; i++) 
			{
				for (int j = 0; j < columnas; j++) 
				{
					this.MatrizDesordenada[i][j] = imagen.getSubimage(j * ancho, i * alto, ancho, alto);
				}
			}
			return this.MatrizDesordenada;

		}else if(esMatrizOrdenadaODesordenada.equals("ordenada"))
		{
			this.MatrizOrdenada = new BufferedImage[filas][columnas];
			for (int i = 0; i < filas; i++) 
			{
				for (int j = 0; j < columnas; j++) 
				{
					this.MatrizOrdenada[i][j] = imagen.getSubimage(j * ancho, i * alto, ancho, alto);
				}
			}
			return this.MatrizOrdenada;
		}
		return null;
	}

	public String proximoMovimiento() 
	{	
		StringBuilder sugerencia = new StringBuilder();
		sugerencia.append("Te recomiendo el siguiente movimiento: ").append(" ").append("\n");
		int auxArribaDeCasillaVacia = filaInicialCasilleroVacio -1;
		int auxAbajoDeCasillaVacia = filaInicialCasilleroVacio +1;
		int auxDerechaDeCasillaVacia = columnaInicialasilleroVacio +1;
		int auxIzquierdaDeCasillaVacia = columnaInicialasilleroVacio -1;

		if(existePosicionEnMatriz(filaInicialCasilleroVacio ,auxDerechaDeCasillaVacia)) 
		{
			sugerirMoverseDerecha(sugerencia, auxDerechaDeCasillaVacia);
		}

		if(existePosicionEnMatriz(filaInicialCasilleroVacio, auxIzquierdaDeCasillaVacia)) 
		{
			sugerirMoverseIzquierda(sugerencia, auxIzquierdaDeCasillaVacia);
		}

		if(existePosicionEnMatriz(auxAbajoDeCasillaVacia , columnaInicialasilleroVacio)) 
		{
			sugerirMoverseAbajo(sugerencia, auxAbajoDeCasillaVacia);
		}

		if(existePosicionEnMatriz(auxArribaDeCasillaVacia, columnaInicialasilleroVacio))
		{
			sugerirMoverseArriba(sugerencia, auxArribaDeCasillaVacia);
		}
		return sugerencia.toString();

	}

	private void sugerirMoverseArriba(StringBuilder sugerencia, int auxArribaDeCasillaVacia) 
	{
		BufferedImage auxImagenOrdenada;
		BufferedImage auxImagenDesordenada;
		Point indicePosicionVacio;

		//imagenes de la Matriz ordenada y desordenada
		auxImagenOrdenada = this.MatrizOrdenada[auxArribaDeCasillaVacia][columnaInicialasilleroVacio];
		auxImagenDesordenada = this.MatrizDesordenada[auxArribaDeCasillaVacia][columnaInicialasilleroVacio];

		indicePosicionVacio = new Point(filaInicialCasilleroVacio, columnaInicialasilleroVacio);

		if(!sonBufferedImagenIguales(auxImagenDesordenada,auxImagenOrdenada)) 
		{
			Point posicionImagen = devolverPosicionEnMatriz(auxImagenDesordenada);
			if(posicionImagen != null && posicionImagen.equals(indicePosicionVacio)) 
			{
				sugerencia.append("Movete a la Arriba").append(" ").append("\n");
			}
		}
	}

	private void sugerirMoverseAbajo(StringBuilder sugerencia, int auxAbajoDeCasillaVacia) 
	{
		BufferedImage auxImagenOrdenada;
		BufferedImage auxImagenDesordenada;
		Point indicePosicionVacio;

		auxImagenOrdenada = this.MatrizOrdenada[auxAbajoDeCasillaVacia][columnaInicialasilleroVacio];
		auxImagenDesordenada = this.MatrizDesordenada[auxAbajoDeCasillaVacia][columnaInicialasilleroVacio];

		indicePosicionVacio = new Point(filaInicialCasilleroVacio, columnaInicialasilleroVacio);


		if(!sonBufferedImagenIguales(auxImagenDesordenada,auxImagenOrdenada)) 
		{
			Point posicionImagen = devolverPosicionEnMatriz(auxImagenDesordenada);
			if(posicionImagen != null && posicionImagen.equals(indicePosicionVacio)) 
			{
				sugerencia.append("Movete a la Abajo").append(" ").append("\n");
			}
		}
	}

	private void sugerirMoverseIzquierda(StringBuilder sugerencia, int auxIzquierdaDeCasillaVacia) 
	{
		BufferedImage auxImagenOrdenada;
		BufferedImage auxImagenDesordenada;
		Point indicePosicionVacio;

		auxImagenOrdenada = this.MatrizOrdenada[filaInicialCasilleroVacio][auxIzquierdaDeCasillaVacia];
		auxImagenDesordenada = this.MatrizDesordenada[filaInicialCasilleroVacio][auxIzquierdaDeCasillaVacia];

		indicePosicionVacio = new Point(filaInicialCasilleroVacio, columnaInicialasilleroVacio);

		if(!sonBufferedImagenIguales(auxImagenDesordenada,auxImagenOrdenada)) 
		{
			Point posicionImagen = devolverPosicionEnMatriz(auxImagenDesordenada);

			if(posicionImagen != null && posicionImagen.equals(indicePosicionVacio)) 
			{
				sugerencia.append("Movete a la Izquierda").append(" ").append("\n");
			}
		}
	}

	private void sugerirMoverseDerecha(StringBuilder sb, int auxDerechaDeCasillaVacia) 
	{
		BufferedImage auxImagenOrdenada;
		BufferedImage auxImagenDesordenada;
		Point indicePosicionVacio;
		auxImagenOrdenada = this.MatrizOrdenada[filaInicialCasilleroVacio][auxDerechaDeCasillaVacia];
		auxImagenDesordenada = this.MatrizDesordenada[filaInicialCasilleroVacio][auxDerechaDeCasillaVacia];
		indicePosicionVacio = new Point(filaInicialCasilleroVacio, columnaInicialasilleroVacio);

		if(!sonBufferedImagenIguales(auxImagenDesordenada,auxImagenOrdenada)) 
		{
			Point posicionImagen = devolverPosicionEnMatriz(auxImagenDesordenada);					

			if(posicionImagen != null && posicionImagen.equals(indicePosicionVacio))
			{						
				sb.append("Movete a la derecha").append(" ").append("\n");							
			}					
		}
	}

	private Point devolverPosicionEnMatriz(BufferedImage imagen) 
	{
		Point indicePosicion;
		for (int f = 0; f < MatrizOrdenada.length; f++) 
		{
			for (int c = 0; c < MatrizOrdenada[f].length; c++) 
			{
				if(sonBufferedImagenIguales(this.MatrizOrdenada[f][c], imagen)) 
				{
					indicePosicion = new Point(f,c);
					return indicePosicion;
				}
			}
		}
		return null;
	}

	private boolean existePosicionEnMatriz(int fila, int columna) 
	{
		if((columna  <= this.MatrizDesordenada.length - 1 && columna  >= 0) && 
				(fila <= this.MatrizDesordenada.length - 1 && fila >= 0)) 
		{
			return true;
		}
		return false;
	}

	public int desplazarmeEnMatriz(String teclaIngresada, int contador) 
	{
		int fila = this.filaInicialCasilleroVacio;
		int columna = this.columnaInicialasilleroVacio;
		String teclaMovimientoDerecha="d";
		String teclaMovimientoIzquierda="a";
		String teclaMovimientoAbajo="s";
		String teclaMovimientoArriba="w";

		//Si se mueve a la derecha
		if(teclaIngresada.equals(teclaMovimientoDerecha)) 
		{
			if (columna < this.MatrizDesordenada[fila].length - 1) 
			{
				moverDerecha(fila, columna);
				contador = incrementarContador(contador);
				return contador;
			}
		}

		//Si se mueve a la arriba
		if(teclaIngresada.equals(teclaMovimientoArriba)) 
		{
			if (fila > 0) 
			{
				moverArriba(fila, columna);
				contador = incrementarContador(contador);
				return contador;
			}
		}

		//Si se mueve a la abajo
		if(teclaIngresada.equals(teclaMovimientoAbajo)) 
		{
			if (fila < this.MatrizDesordenada.length - 1) 
			{
				moverAbajo(fila, columna);
				contador = incrementarContador(contador);
				return contador;
			}
		}

		//Si se mueve a la izquierda
		if(teclaIngresada.equals(teclaMovimientoIzquierda)) 
		{
			if (columna > 0) 
			{
				moverIzquierda(fila, columna);
				contador = incrementarContador(contador);
				return contador;
			}	
		}
		return contador;
	}

	private void moverIzquierda(int fila, int columna) {
		BufferedImage aux;
		aux = this.MatrizDesordenada[fila][columna - 1];
		this.MatrizDesordenada[fila][columna - 1] = this.MatrizDesordenada[fila][columna];
		this.MatrizDesordenada[fila][columna] = aux;
		this.columnaInicialasilleroVacio--;
	}

	private void moverAbajo(int fila, int columna) {
		BufferedImage aux;
		aux = this.MatrizDesordenada[fila + 1][columna];
		this.MatrizDesordenada[fila + 1][columna] = this.MatrizDesordenada[fila][columna];
		this.MatrizDesordenada[fila][columna] = aux;
		this.filaInicialCasilleroVacio++;
	}

	private void moverArriba(int fila, int columna) {
		BufferedImage aux;
		aux = this.MatrizDesordenada[fila - 1][columna];
		this.MatrizDesordenada[fila - 1][columna] = this.MatrizDesordenada[fila][columna];
		this.MatrizDesordenada[fila][columna] = aux;
		this.filaInicialCasilleroVacio--;
	}

	private void moverDerecha(int fila, int columna) {
		BufferedImage aux;
		aux = this.MatrizDesordenada[fila][columna + 1];
		MatrizDesordenada[fila][columna + 1] = this.MatrizDesordenada[fila][columna];
		MatrizDesordenada[fila][columna] = aux;
		this.columnaInicialasilleroVacio++;
	}

	private int incrementarContador(int contador) 
	{
		return ++contador;
	}

	private boolean sonBufferedImagenIguales(BufferedImage imagenA, BufferedImage imagenB) 
	{
		if(imagenA.getWidth()!=imagenB.getWidth()||imagenA.getHeight()!=imagenB.getHeight())
			return false;
		for (int pixelX = 0; pixelX < imagenA.getWidth(); pixelX++) 
		{
			for (int pixelY = 0; pixelY < imagenA.getHeight(); pixelY++) 
			{
				if (imagenA.getRGB(pixelX, pixelY) != imagenB.getRGB(pixelX, pixelY)) 
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean gano() 
	{
		for (int i = 0; i < MatrizDesordenada.length; i++) 
		{
			for (int j = 0; j < MatrizDesordenada[i].length; j++) 
			{
				if (!sonBufferedImagenIguales(MatrizDesordenada[i][j], MatrizOrdenada[i][j])) 
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public BufferedImage[][] getMatrizDesordenada() 
	{
		return this.MatrizDesordenada;
	}

	public int getFilaVacio() {
		return filaInicialCasilleroVacio;
	}

	public void setFilaVacio(int filaVacio) {
		this.filaInicialCasilleroVacio = filaVacio;
	}

	public int getColVacio() {
		return columnaInicialasilleroVacio;
	}

	public void setColVacio(int colVacio) {
		this.columnaInicialasilleroVacio = colVacio;
	}
}
