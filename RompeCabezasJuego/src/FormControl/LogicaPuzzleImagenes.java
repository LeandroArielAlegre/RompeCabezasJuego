package FormControl;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JOptionPane;

public class LogicaPuzzleImagenes
{
	private BufferedImage[][] MatrizDesordenada;
	private BufferedImage[][] MatrizOrdenada;
	private int filaDelCasilleroVacio = 2;
	private int columnaDelCasilleroVacio = 2;

	public LogicaPuzzleImagenes() 
	{

	}

	public  void imprimirGane() 
	{
		JOptionPane.showMessageDialog(null, "¡Felicitaciones Haz ganado!");
	}

	public void mezclarMatriz() 
	{
		Random indiceAleatorio = new Random();
		String[] movimientosEnTablero = {"w", "a", "s", "d"};

		for (int cantidadMovimientosAleatorios = 0; cantidadMovimientosAleatorios < 1000; cantidadMovimientosAleatorios++) 
		{
			String direccionDeMovimiento = movimientosEnTablero[indiceAleatorio.nextInt(movimientosEnTablero.length)];
			desplazarmeEnMatriz(direccionDeMovimiento, 0);
		}
	}

	public BufferedImage[][] cortarImagen(BufferedImage imagen,String tipoDeOrdenamientoDeMatriz) 
	{
		int filas = 3;
		int columnas = 3;
		int ancho = imagen.getWidth() / columnas;
		int alto = imagen.getHeight() / filas;
		if(tipoDeOrdenamientoDeMatriz.equals("desordenada")) 
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

		}else if(tipoDeOrdenamientoDeMatriz.equals("ordenada"))
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
		int auxArribaDeCasillaVacia = filaDelCasilleroVacio -1;
		int auxAbajoDeCasillaVacia = filaDelCasilleroVacio +1;
		int auxDerechaDeCasillaVacia = columnaDelCasilleroVacio +1;
		int auxIzquierdaDeCasillaVacia = columnaDelCasilleroVacio -1;

		if(existePosicionEnMatriz(filaDelCasilleroVacio ,auxDerechaDeCasillaVacia)) 
		{
			sugerirMoverseDerecha(sugerencia, auxDerechaDeCasillaVacia);
		}

		if(existePosicionEnMatriz(filaDelCasilleroVacio, auxIzquierdaDeCasillaVacia)) 
		{
			sugerirMoverseIzquierda(sugerencia, auxIzquierdaDeCasillaVacia);
		}

		if(existePosicionEnMatriz(auxAbajoDeCasillaVacia , columnaDelCasilleroVacio)) 
		{
			sugerirMoverseAbajo(sugerencia, auxAbajoDeCasillaVacia);
		}

		if(existePosicionEnMatriz(auxArribaDeCasillaVacia, columnaDelCasilleroVacio))
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

		
		auxImagenOrdenada = this.MatrizOrdenada[auxArribaDeCasillaVacia][columnaDelCasilleroVacio];
		auxImagenDesordenada = this.MatrizDesordenada[auxArribaDeCasillaVacia][columnaDelCasilleroVacio];

		indicePosicionVacio = new Point(filaDelCasilleroVacio, columnaDelCasilleroVacio);

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

		auxImagenOrdenada = this.MatrizOrdenada[auxAbajoDeCasillaVacia][columnaDelCasilleroVacio];
		auxImagenDesordenada = this.MatrizDesordenada[auxAbajoDeCasillaVacia][columnaDelCasilleroVacio];

		indicePosicionVacio = new Point(filaDelCasilleroVacio, columnaDelCasilleroVacio);


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

		auxImagenOrdenada = this.MatrizOrdenada[filaDelCasilleroVacio][auxIzquierdaDeCasillaVacia];
		auxImagenDesordenada = this.MatrizDesordenada[filaDelCasilleroVacio][auxIzquierdaDeCasillaVacia];

		indicePosicionVacio = new Point(filaDelCasilleroVacio, columnaDelCasilleroVacio);

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
		auxImagenOrdenada = this.MatrizOrdenada[filaDelCasilleroVacio][auxDerechaDeCasillaVacia];
		auxImagenDesordenada = this.MatrizDesordenada[filaDelCasilleroVacio][auxDerechaDeCasillaVacia];
		indicePosicionVacio = new Point(filaDelCasilleroVacio, columnaDelCasilleroVacio);

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

	public int desplazarmeEnMatriz(String teclaIngresada, int contadorMovimientos) 
	{
		int fila = this.filaDelCasilleroVacio;
		int columna = this.columnaDelCasilleroVacio;
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
				contadorMovimientos = incrementarContador(contadorMovimientos);
				return contadorMovimientos;
			}
		}

		//Si se mueve a la arriba
		if(teclaIngresada.equals(teclaMovimientoArriba)) 
		{
			if (fila > 0) 
			{
				moverArriba(fila, columna);
				contadorMovimientos = incrementarContador(contadorMovimientos);
				return contadorMovimientos;
			}
		}

		//Si se mueve a la abajo
		if(teclaIngresada.equals(teclaMovimientoAbajo)) 
		{
			if (fila < this.MatrizDesordenada.length - 1) 
			{
				moverAbajo(fila, columna);
				contadorMovimientos = incrementarContador(contadorMovimientos);
				return contadorMovimientos;
			}
		}

		//Si se mueve a la izquierda
		if(teclaIngresada.equals(teclaMovimientoIzquierda)) 
		{
			if (columna > 0) 
			{
				moverIzquierda(fila, columna);
				contadorMovimientos = incrementarContador(contadorMovimientos);
				return contadorMovimientos;
			}	
		}
		return contadorMovimientos;
	}

	private void moverIzquierda(int fila, int columna) {
		BufferedImage aux;
		aux = this.MatrizDesordenada[fila][columna - 1];
		this.MatrizDesordenada[fila][columna - 1] = this.MatrizDesordenada[fila][columna];
		this.MatrizDesordenada[fila][columna] = aux;
		this.columnaDelCasilleroVacio--;
	}

	private void moverAbajo(int fila, int columna) {
		BufferedImage aux;
		aux = this.MatrizDesordenada[fila + 1][columna];
		this.MatrizDesordenada[fila + 1][columna] = this.MatrizDesordenada[fila][columna];
		this.MatrizDesordenada[fila][columna] = aux;
		this.filaDelCasilleroVacio++;
	}

	private void moverArriba(int fila, int columna) {
		BufferedImage aux;
		aux = this.MatrizDesordenada[fila - 1][columna];
		this.MatrizDesordenada[fila - 1][columna] = this.MatrizDesordenada[fila][columna];
		this.MatrizDesordenada[fila][columna] = aux;
		this.filaDelCasilleroVacio--;
	}

	private void moverDerecha(int fila, int columna) {
		BufferedImage aux;
		aux = this.MatrizDesordenada[fila][columna + 1];
		MatrizDesordenada[fila][columna + 1] = this.MatrizDesordenada[fila][columna];
		MatrizDesordenada[fila][columna] = aux;
		this.columnaDelCasilleroVacio++;
	}

	private int incrementarContador(int contadorMovimientos) 
	{
		return ++contadorMovimientos;
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
		for (int fila = 0; fila < MatrizDesordenada.length; fila++) 
		{
			for (int columna = 0; columna < MatrizDesordenada[fila].length; columna++) 
			{
				if (!sonBufferedImagenIguales(MatrizDesordenada[fila][columna], MatrizOrdenada[fila][columna])) 
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
		return filaDelCasilleroVacio;
	}

	public void setFilaVacio(int filaVacio) {
		this.filaDelCasilleroVacio = filaVacio;
	}

	public int getColVacio() {
		return columnaDelCasilleroVacio;
	}

	public void setColVacio(int colVacio) {
		this.columnaDelCasilleroVacio = colVacio;
	}
}
