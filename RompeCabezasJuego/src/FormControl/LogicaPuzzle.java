package FormControl;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogicaPuzzle {

	private int[][] matriz = new int[3][3];
	private int[][] matrizRespuesta = new int[3][3];
	private int filaCasillaVacia = 2; // Posición inicial del casillero vacío
	private int columnaCasillaVacia = 2;
	private int numeroRepresentativoDeVacio=0;
	
	private BufferedImage[][] MatrizDesordenada;
	private BufferedImage[][] MatrizOrdenada;
	private int filaDelCasilleroVacio = 2;
	private int columnaDelCasilleroVacio = 2;
	
	public LogicaPuzzle() {
		inicializarMatrizNumeros();
	}

	private void mezclarMatrizNumeros() 
	{
		Random indiceAleatorio = new Random();
		String[] movimientosEnTablero = {"w", "a", "s", "d"};

		for (int cantidadMovimientosAleatorios = 0; cantidadMovimientosAleatorios < 1000; cantidadMovimientosAleatorios++) 
		{
			String direccionDeMovimiento = movimientosEnTablero[indiceAleatorio.nextInt(movimientosEnTablero.length)];
			desplazarmeEnMatrizNumeros(direccionDeMovimiento, 0);
		}
	}

	private void inicializarMatrizNumeros() 
	{
		List<Integer> numerosDeMatriz = new ArrayList<>();
		for (int numero = 1; numero <= 8; numero++) {
			numerosDeMatriz.add(numero);
		}
		numerosDeMatriz.add(numeroRepresentativoDeVacio);

		int posicionEnMatriz = 0;
		for (int fila = 0; fila < matriz.length; fila++) {
			for (int columna = 0; columna < matriz[fila].length; columna++) {
				matriz[fila][columna] = numerosDeMatriz.get(posicionEnMatriz++);
				matrizRespuesta[fila][columna] = matriz[fila][columna];
				if (matriz[fila][columna] == 0) {
					this.filaCasillaVacia = fila;
					this.columnaCasillaVacia = columna;
				}
			}
		}
		mezclarMatrizNumeros();
	}

	public String proximoMovimientoNumeros() {
		StringBuilder sugerencia = new StringBuilder();
		sugerencia.append("Te recomiendo lo siguiente: ").append(" ").append("\n");

		int auxFMenos = filaCasillaVacia -1;
		int auxFMas = filaCasillaVacia +1;
		int auxCMas = columnaCasillaVacia +1;

		if(numeroDerechaMayorQueAbajo(auxFMas, auxCMas)	
				&& numeroDerechaMayorQueArriba(auxFMenos, auxCMas)) 
		{
			int casillaDeArribaAux=this.matriz[auxFMenos][columnaCasillaVacia];
			int casillaDeAbajoAux= this.matriz[auxFMas][columnaCasillaVacia];
			if((casillaDeArribaAux>casillaDeAbajoAux)) 
			{
				sugerencia.append("Movete hacia Arriba").append(" ").append("\n");
			}else{
				sugerencia.append("Movete hacia Abajo").append(" ").append("\n");
			}
		}else if(numeroDerechaMayorQueAbajo(auxFMas, auxCMas)) {
			sugerencia.append("Movete hacia Abajo").append(" ").append("\n");

		}else if(numeroDerechaMayorQueArriba(auxFMenos, auxCMas)) {
			sugerencia.append("Movete hacia Arriba").append(" ").append("\n");
		}			
		return sugerencia.toString();
	}

	private boolean numeroDerechaMayorQueArriba(int auxFMenos, int auxCMas) {
		if(existePosicionEnMatrizNumeros(filaCasillaVacia,auxCMas) && 
				existePosicionEnMatrizNumeros(auxFMenos ,columnaCasillaVacia)) {
			//Cual de ambos es mayor:
			if(this.matriz[filaCasillaVacia][auxCMas] >  this.matriz[auxFMenos][columnaCasillaVacia]) {
				//Si el antecedente "auxFMenos" es menor a auxCMas... recomiendo moverme hacia abajo
				return true;
			}
		}
		return false;
	}

	private boolean numeroDerechaMayorQueAbajo(int auxFMas, int auxCMas) {
		if(existePosicionEnMatrizNumeros(filaCasillaVacia,auxCMas) && 
				existePosicionEnMatrizNumeros(auxFMas ,columnaCasillaVacia)) {
			//Cual de ambos es mayor:
			if(this.matriz[filaCasillaVacia][auxCMas] >  this.matriz[auxFMas][columnaCasillaVacia]) {
				//Si el antecedente "auxFMas" es menor a auxCMas... recomiendo moverme hacia arriba
				return true;
			}
		}
		return false;
	}

	private boolean existePosicionEnMatrizNumeros(int fila, int columna) {
		if((columna  <= this.matriz.length - 1 && columna  >= 0) && (fila <= this.matriz.length - 1 && fila >= 0) ) {
			return true;

		}
		return false;
	}

	public int desplazarmeEnMatrizNumeros(String tecla, int contador) 
	{
		int fila = this.filaCasillaVacia;
		int columna = this.columnaCasillaVacia;
		int auxiliarIntercambio=0;

		if(tecla.equals("w")) {
			if (fila > 0) {
				moverArriba(fila,columna,auxiliarIntercambio);				
				contador = incrementarContador(contador);
				return contador;
			}
		}

		if(tecla.equals("a")) 
		{
			if (columna > 0) 
			{
				moverIzquierdaNumeros(fila,columna,auxiliarIntercambio);
				contador = incrementarContador(contador);
				return contador;
			}	
		}
		if(tecla.equals("s")) {
			if (fila < matriz.length - 1) 
			{
				moverAbajoNumeros(fila,columna,auxiliarIntercambio);
				contador = incrementarContador(contador);
				return contador;

			}
		}

		if(tecla.equals("d")) 
		{
			if (columna < matriz[fila].length - 1) 
			{
				moverDerecha(fila,columna,auxiliarIntercambio);
				contador = incrementarContador(contador);
				return contador;
			}
		}
		return contador;
	}

	private void moverIzquierdaNumeros(int fila, int columna, int auxiliarIntercambio) 
	{
		auxiliarIntercambio = matriz[fila][columna - 1];
		matriz[fila][columna - 1] = matriz[fila][columna];
		matriz[fila][columna] = auxiliarIntercambio;
		this.columnaCasillaVacia--;
	}

	private void moverAbajoNumeros(int fila, int columna, int auxiliarIntercambio) 
	{

		auxiliarIntercambio = matriz[fila + 1][columna];
		matriz[fila + 1][columna] = matriz[fila][columna];
		matriz[fila][columna] = auxiliarIntercambio;
		this.filaCasillaVacia++;
	}

	private void moverArriba(int fila, int columna, int auxiliarIntercambio) 
	{
		auxiliarIntercambio = matriz[fila - 1][columna];
		matriz[fila - 1][columna] = matriz[fila][columna];
		matriz[fila][columna] = auxiliarIntercambio;
		this.filaCasillaVacia--;
	}

	private void moverDerecha(int fila, int columna, int auxiliarIntercambio) 
	{
		auxiliarIntercambio = matriz[fila][columna + 1];
		matriz[fila][columna + 1] = matriz[fila][columna];
		matriz[fila][columna] = auxiliarIntercambio;
		this.columnaCasillaVacia++;
	}

	public String imprimirMatrizNumeros() 
	{
		StringBuilder sb = new StringBuilder();
		int aux=0;
		for (int i = 0; i < this.matriz.length; i++) 
		{
			for (int j = 0; j < this.matriz[i].length; j++) 
			{
				aux=this.matriz[i][j];
				if(aux!=0) {
					sb.append(this.matriz[i][j]).append(" ");
				}else {
					sb.append("⬜").append(" ");
				}
			}
			sb.append("\n"); // Salto de línea
		}
		return sb.toString();
	}

	public int[][] getMatrizNumeros() 
	{
		return this.matriz;
	}
	
	public boolean ganoNumeros() 
	{
		for (int i = 0; i < matriz.length; i++) 
		{
			for (int j = 0; j < matriz[i].length; j++) 
			{
				if (!sonEnterosIguales(matriz[i][j], matrizRespuesta[i][j])) 
				{
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean sonEnterosIguales(int i, int  j) {
		return i==j?true:false;
	}

	private int incrementarContador(int contador) {
		return ++contador;
	}

	public void mezclarMatrizImagenes() 
	{
		Random indiceAleatorio = new Random();
		String[] movimientosEnTablero = {"w", "a", "s", "d"};

		for (int cantidadMovimientosAleatorios = 0; cantidadMovimientosAleatorios < 1000; cantidadMovimientosAleatorios++) 
		{
			String direccionDeMovimiento = movimientosEnTablero[indiceAleatorio.nextInt(movimientosEnTablero.length)];
			desplazarmeEnMatrizImagen(direccionDeMovimiento, 0);
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

	public String proximoMovimientoImagenes() 
	{	
		StringBuilder sugerencia = new StringBuilder();
		sugerencia.append("Te recomiendo el siguiente movimiento: ").append(" ").append("\n");
		int auxArribaDeCasillaVacia = filaDelCasilleroVacio -1;
		int auxAbajoDeCasillaVacia = filaDelCasilleroVacio +1;
		int auxDerechaDeCasillaVacia = columnaDelCasilleroVacio +1;
		int auxIzquierdaDeCasillaVacia = columnaDelCasilleroVacio -1;

		if(existePosicionEnMatrizImagen(filaDelCasilleroVacio ,auxDerechaDeCasillaVacia)) 
		{
			sugerirMoverseDerechaImagen(sugerencia, auxDerechaDeCasillaVacia);
		}

		if(existePosicionEnMatrizImagen(filaDelCasilleroVacio, auxIzquierdaDeCasillaVacia)) 
		{
			sugerirMoverseIzquierdaImagen(sugerencia, auxIzquierdaDeCasillaVacia);
		}

		if(existePosicionEnMatrizImagen(auxAbajoDeCasillaVacia , columnaDelCasilleroVacio)) 
		{
			sugerirMoverseAbajoImagen(sugerencia, auxAbajoDeCasillaVacia);
		}

		if(existePosicionEnMatrizImagen(auxArribaDeCasillaVacia, columnaDelCasilleroVacio))
		{
			sugerirMoverseArribaImagen(sugerencia, auxArribaDeCasillaVacia);
		}
		return sugerencia.toString();

	}

	private void sugerirMoverseArribaImagen(StringBuilder sugerencia, int auxArribaDeCasillaVacia) 
	{
		BufferedImage auxImagenOrdenada;
		BufferedImage auxImagenDesordenada;
		Point indicePosicionVacio;

		
		auxImagenOrdenada = this.MatrizOrdenada[auxArribaDeCasillaVacia][columnaDelCasilleroVacio];
		auxImagenDesordenada = this.MatrizDesordenada[auxArribaDeCasillaVacia][columnaDelCasilleroVacio];

		indicePosicionVacio = new Point(filaDelCasilleroVacio, columnaDelCasilleroVacio);

		if(!sonBufferedImagenIguales(auxImagenDesordenada,auxImagenOrdenada)) 
		{
			Point posicionImagen = devolverPosicionEnMatrizImagen(auxImagenDesordenada);
			if(posicionImagen != null && posicionImagen.equals(indicePosicionVacio)) 
			{
				sugerencia.append("Movete a la Arriba").append(" ").append("\n");
			}
		}
	}

	private void sugerirMoverseAbajoImagen(StringBuilder sugerencia, int auxAbajoDeCasillaVacia) 
	{
		BufferedImage auxImagenOrdenada;
		BufferedImage auxImagenDesordenada;
		Point indicePosicionVacio;

		auxImagenOrdenada = this.MatrizOrdenada[auxAbajoDeCasillaVacia][columnaDelCasilleroVacio];
		auxImagenDesordenada = this.MatrizDesordenada[auxAbajoDeCasillaVacia][columnaDelCasilleroVacio];

		indicePosicionVacio = new Point(filaDelCasilleroVacio, columnaDelCasilleroVacio);


		if(!sonBufferedImagenIguales(auxImagenDesordenada,auxImagenOrdenada)) 
		{
			Point posicionImagen = devolverPosicionEnMatrizImagen(auxImagenDesordenada);
			if(posicionImagen != null && posicionImagen.equals(indicePosicionVacio)) 
			{
				sugerencia.append("Movete a la Abajo").append(" ").append("\n");
			}
		}
	}

	private void sugerirMoverseIzquierdaImagen(StringBuilder sugerencia, int auxIzquierdaDeCasillaVacia) 
	{
		BufferedImage auxImagenOrdenada;
		BufferedImage auxImagenDesordenada;
		Point indicePosicionVacio;

		auxImagenOrdenada = this.MatrizOrdenada[filaDelCasilleroVacio][auxIzquierdaDeCasillaVacia];
		auxImagenDesordenada = this.MatrizDesordenada[filaDelCasilleroVacio][auxIzquierdaDeCasillaVacia];

		indicePosicionVacio = new Point(filaDelCasilleroVacio, columnaDelCasilleroVacio);

		if(!sonBufferedImagenIguales(auxImagenDesordenada,auxImagenOrdenada)) 
		{
			Point posicionImagen = devolverPosicionEnMatrizImagen(auxImagenDesordenada);

			if(posicionImagen != null && posicionImagen.equals(indicePosicionVacio)) 
			{
				sugerencia.append("Movete a la Izquierda").append(" ").append("\n");
			}
		}
	}

	private void sugerirMoverseDerechaImagen(StringBuilder sb, int auxDerechaDeCasillaVacia) 
	{
		BufferedImage auxImagenOrdenada;
		BufferedImage auxImagenDesordenada;
		Point indicePosicionVacio;
		auxImagenOrdenada = this.MatrizOrdenada[filaDelCasilleroVacio][auxDerechaDeCasillaVacia];
		auxImagenDesordenada = this.MatrizDesordenada[filaDelCasilleroVacio][auxDerechaDeCasillaVacia];
		indicePosicionVacio = new Point(filaDelCasilleroVacio, columnaDelCasilleroVacio);

		if(!sonBufferedImagenIguales(auxImagenDesordenada,auxImagenOrdenada)) 
		{
			Point posicionImagen = devolverPosicionEnMatrizImagen(auxImagenDesordenada);					

			if(posicionImagen != null && posicionImagen.equals(indicePosicionVacio))
			{						
				sb.append("Movete a la derecha").append(" ").append("\n");							
			}					
		}
	}

	private Point devolverPosicionEnMatrizImagen(BufferedImage imagen) 
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

	private boolean existePosicionEnMatrizImagen(int fila, int columna) 
	{
		if((columna  <= this.MatrizDesordenada.length - 1 && columna  >= 0) && 
				(fila <= this.MatrizDesordenada.length - 1 && fila >= 0)) 
		{
			return true;
		}
		return false;
	}

	public int desplazarmeEnMatrizImagen(String teclaIngresada, int contadorMovimientos) 
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
				moverDerechaImagen(fila, columna);
				contadorMovimientos = incrementarContador(contadorMovimientos);
				return contadorMovimientos;
			}
		}

		//Si se mueve a la arriba
		if(teclaIngresada.equals(teclaMovimientoArriba)) 
		{
			if (fila > 0) 
			{
				moverArribaImagen(fila, columna);
				contadorMovimientos = incrementarContador(contadorMovimientos);
				return contadorMovimientos;
			}
		}

		//Si se mueve a la abajo
		if(teclaIngresada.equals(teclaMovimientoAbajo)) 
		{
			if (fila < this.MatrizDesordenada.length - 1) 
			{
				moverAbajoImagen(fila, columna);
				contadorMovimientos = incrementarContador(contadorMovimientos);
				return contadorMovimientos;
			}
		}

		//Si se mueve a la izquierda
		if(teclaIngresada.equals(teclaMovimientoIzquierda)) 
		{
			if (columna > 0) 
			{
				moverIzquierdaImagen(fila, columna);
				contadorMovimientos = incrementarContador(contadorMovimientos);
				return contadorMovimientos;
			}	
		}
		return contadorMovimientos;
	}

	private void moverIzquierdaImagen(int fila, int columna) {
		BufferedImage aux;
		aux = this.MatrizDesordenada[fila][columna - 1];
		this.MatrizDesordenada[fila][columna - 1] = this.MatrizDesordenada[fila][columna];
		this.MatrizDesordenada[fila][columna] = aux;
		this.columnaDelCasilleroVacio--;
	}

	private void moverAbajoImagen(int fila, int columna) {
		BufferedImage aux;
		aux = this.MatrizDesordenada[fila + 1][columna];
		this.MatrizDesordenada[fila + 1][columna] = this.MatrizDesordenada[fila][columna];
		this.MatrizDesordenada[fila][columna] = aux;
		this.filaDelCasilleroVacio++;
	}

	private void moverArribaImagen(int fila, int columna) {
		BufferedImage aux;
		aux = this.MatrizDesordenada[fila - 1][columna];
		this.MatrizDesordenada[fila - 1][columna] = this.MatrizDesordenada[fila][columna];
		this.MatrizDesordenada[fila][columna] = aux;
		this.filaDelCasilleroVacio--;
	}

	private void moverDerechaImagen(int fila, int columna) {
		BufferedImage aux;
		aux = this.MatrizDesordenada[fila][columna + 1];
		MatrizDesordenada[fila][columna + 1] = this.MatrizDesordenada[fila][columna];
		MatrizDesordenada[fila][columna] = aux;
		this.columnaDelCasilleroVacio++;
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

	public boolean ganoImagenes() 
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

	public int getColVacio() {
		return columnaDelCasilleroVacio;
	}
}
