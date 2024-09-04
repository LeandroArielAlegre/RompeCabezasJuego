package FormControl;
//import java.awt.Graphics2D;
//import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

//import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
public class ControladorImagen {
	private BufferedImage[][] MatrizDesordenada;
	private BufferedImage[][] MatrizOrdenada;
	private int filaVacio = 2; // Posición inicial del casillero vacío
	private int colVacio = 2;
	public ControladorImagen() {

	}
	
	public  void imprimirGane() {
		JOptionPane.showMessageDialog(null, "Juego finalizado, Ganaste!!!!!1!!1");
	}



	public void mezclarMatriz() 
	{
		Random rand = new Random();
		String[] movimientos = {"w", "a", "s", "d"};

		for (int cantidadMovimientosAleatorios = 0; cantidadMovimientosAleatorios < 1000; cantidadMovimientosAleatorios++) 
		{
			String movimiento = movimientos[rand.nextInt(movimientos.length)];
			desplazarmeEnMatrizDesordenada(movimiento, 0);
		}
	}
	public BufferedImage[][] cortarImagen(BufferedImage imagen) {
		int filas = 3;
		int columnas = 3;
		int ancho = imagen.getWidth() / columnas;
		int alto = imagen.getHeight() / filas;

		this.MatrizDesordenada = new BufferedImage[filas][columnas];
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				this.MatrizDesordenada[i][j] = imagen.getSubimage(j * ancho, i * alto, ancho, alto);
			}
		}
		return this.MatrizDesordenada;
	}
	public BufferedImage[][] cortarImagenConOrden(BufferedImage imagen) {
		int filas = 3;
		int columnas = 3;
		int ancho = imagen.getWidth() / columnas;
		int alto = imagen.getHeight() / filas;


		this.MatrizOrdenada = new BufferedImage[filas][columnas];
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				this.MatrizOrdenada[i][j] = imagen.getSubimage(j * ancho, i * alto, ancho, alto);
			}
		}
		return this.MatrizOrdenada;
	}	 
	public int desplazarmeEnMatrizDesordenada(String tecla, int contador) {
		int fila = this.filaVacio;
		int columna = this.colVacio;
		BufferedImage aux;
		//Si se mueve a la derecha
		if(tecla.equals("d")) {
			if (columna < this.MatrizDesordenada[fila].length - 1) {
				aux = this.MatrizDesordenada[fila][columna + 1];
				MatrizDesordenada[fila][columna + 1] = this.MatrizDesordenada[fila][columna];
				MatrizDesordenada[fila][columna] = aux;
				this.colVacio++;
				contador = incrementarContador(contador);
				return contador;


			}
		}


		//Si se mueve a la arriba
		if(tecla.equals("w")) {
			if (fila > 0) {
				aux = this.MatrizDesordenada[fila - 1][columna];
				this.MatrizDesordenada[fila - 1][columna] = this.MatrizDesordenada[fila][columna];
				this.MatrizDesordenada[fila][columna] = aux;
				this.filaVacio--;
				contador = incrementarContador(contador);
				return contador;

			}
		}
		//a


		//Si se mueve a la abajo
		if(tecla.equals("s")) {
			if (fila < this.MatrizDesordenada.length - 1) {
				aux = this.MatrizDesordenada[fila + 1][columna];
				this.MatrizDesordenada[fila + 1][columna] = this.MatrizDesordenada[fila][columna];
				this.MatrizDesordenada[fila][columna] = aux;
				this.filaVacio++;
				contador = incrementarContador(contador);

				return contador;

			}
		}



		//Si se mueve a la izquierda
		if(tecla.equals("a")) 
		{
			if (columna > 0) 
			{
				aux = this.MatrizDesordenada[fila][columna - 1];
				this.MatrizDesordenada[fila][columna - 1] = this.MatrizDesordenada[fila][columna];
				this.MatrizDesordenada[fila][columna] = aux;
				this.colVacio--;
				contador = incrementarContador(contador);

				return contador;
			}	
		}
		return contador;
	}
	private int incrementarContador(int contador) {
		return ++contador;
	}
//	public boolean sonIconosIguales(ImageIcon test, ImageIcon test2) {
//
//		BufferedImage a=iconoABufferedImage(test.getImage());
//		BufferedImage b=iconoABufferedImage(test2.getImage());
//		for (int pixelX=0;pixelX<a.getWidth();pixelX++) {
//			for (int pixelY=0;pixelY<a.getHeight();pixelY++) {
//				if (a.getRGB(pixelX, pixelY)!=b.getRGB(pixelX, pixelY))
//					return false;
//			}
//		}
//
//		return true;
//	}
	private boolean sonBufferedImagenIguales(BufferedImage imagenA, BufferedImage imagenB) {
		if(imagenA.getWidth()!=imagenB.getWidth()||imagenA.getHeight()!=imagenB.getHeight())
			return false;
		for (int pixelX = 0; pixelX < imagenA.getWidth(); pixelX++) {
			for (int pixelY = 0; pixelY < imagenA.getHeight(); pixelY++) {
				if (imagenA.getRGB(pixelX, pixelY) != imagenB.getRGB(pixelX, pixelY)) {
					return false;
				}
			}
		}
		return true;
	}
	public BufferedImage[][] getMatrizDesordenada() {
		return this.MatrizDesordenada;
	}
//	public static BufferedImage iconoABufferedImage(Image imagen)
//	{
//		BufferedImage newImage = new BufferedImage(imagen.getWidth(null), imagen.getHeight(null), BufferedImage.TYPE_INT_ARGB);//TYPE_INT_ARGB Represents an image with 8-bit RGBA color components packed intointeger pixels.
//		Graphics2D hojaDeDibujoImagen = newImage.createGraphics();
//		hojaDeDibujoImagen.drawImage(imagen, 0, 0, null);
//		hojaDeDibujoImagen.dispose();
//		return newImage;
//	}
	public boolean gano() {
		for (int i = 0; i < MatrizDesordenada.length; i++) {
			for (int j = 0; j < MatrizDesordenada[i].length; j++) {
				if (!sonBufferedImagenIguales(MatrizDesordenada[i][j], MatrizOrdenada[i][j])) {
					return false;
				}
			}
		}
		return true;
	}
	public int getFilaVacio() {
		return filaVacio;
	}
	public void setFilaVacio(int filaVacio) {
		this.filaVacio = filaVacio;
	}
	public int getColVacio() {
		return colVacio;
	}
	public void setColVacio(int colVacio) {
		this.colVacio = colVacio;
	}
	//	public BufferedImage[][] copiarMatriz(BufferedImage[][] matrizImagenOrdenada) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
}
