package FormControl;
import java.awt.Point;
//import java.awt.Graphics2D;
//import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
	
	public BufferedImage[][] cortarImagen(BufferedImage imagen,String ordenODesorden) 
	{
		int filas = 3;
		int columnas = 3;
		int ancho = imagen.getWidth() / columnas;
		int alto = imagen.getHeight() / filas;
		if(ordenODesorden.equals("a desordenar")) 
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
		}else if(ordenODesorden.equals("ordenada"))
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
	
	public String  proximoMovimiento() {
		
		   StringBuilder sb = new StringBuilder();
		   sb.append("Te recomiendo el siguiente movimiento: ").append(" ").append("\n");
				int auxFMenos = filaVacio -1;
				int auxFMas = filaVacio +1;
				int auxCMas = colVacio +1;
				int auxCMenos = colVacio -1;
				
				BufferedImage auxImagenOrdenada;
				BufferedImage auxImagenDesordenada;
				
				
				Point indicePosicionVacio;
				
				//caso c+1
				if(existePosicionEnMatriz(filaVacio ,auxCMas)) {
					//this.MatrizDesordenada[filaVacio][auxCMas]
					
					//Matriz ordenada
					auxImagenOrdenada = this.MatrizOrdenada[filaVacio][auxCMas];
					auxImagenDesordenada = this.MatrizDesordenada[filaVacio][auxCMas];
					
					indicePosicionVacio = new Point(filaVacio, colVacio);
					
					
					if(!sonBufferedImagenIguales(auxImagenDesordenada,auxImagenOrdenada ) ) {
						
						Point posicionImagen =devolverPosicionEnMatriz(auxImagenDesordenada);
						//Proceda
						if(posicionImagen != null && posicionImagen.equals(indicePosicionVacio)) {
							
							
							sb.append("Movete a la derecha").append(" ").append("\n");
							
						}
						
							
							//Listo mi vacio es igual a como deberia ser mi derecha
							//me muevo a la derecha	
							
						
						
					}
					
					
				}
				
				//caso c-1
				if(existePosicionEnMatriz(filaVacio, auxCMenos)) {
					//Matriz ordenada
					auxImagenOrdenada = this.MatrizOrdenada[filaVacio][auxCMenos];
					auxImagenDesordenada = this.MatrizDesordenada[filaVacio][auxCMenos];
					
					indicePosicionVacio = new Point(filaVacio, colVacio);
					
					
					if(!sonBufferedImagenIguales(auxImagenDesordenada,auxImagenOrdenada ) ) {
						
						Point posicionImagen =devolverPosicionEnMatriz(auxImagenDesordenada);
						//Proceda
						if(posicionImagen != null && posicionImagen.equals(indicePosicionVacio)) {
							
							
							sb.append("Movete a la Izquierda").append(" ").append("\n");
						}
						
							
							//Listo mi vacio es igual a como deberia ser mi izquierda
							//me muevo a la izquierda	
							
						
						
					}
					
					
				}
				
				//caso f+1
				if(existePosicionEnMatriz(auxFMas , colVacio)) {
					//Matriz ordenada
					auxImagenOrdenada = this.MatrizOrdenada[auxFMas][colVacio];
					auxImagenDesordenada = this.MatrizDesordenada[auxFMas][colVacio];
					
					indicePosicionVacio = new Point(filaVacio, colVacio);
					
					
					if(!sonBufferedImagenIguales(auxImagenDesordenada,auxImagenOrdenada ) ) {
						
						Point posicionImagen =devolverPosicionEnMatriz(auxImagenDesordenada);
						//Proceda
						if(posicionImagen != null && posicionImagen.equals(indicePosicionVacio)) {
							
							
							sb.append("Movete a la Abajo").append(" ").append("\n");
						}
						
							
							//Listo mi vacio es igual a como deberia ser mi abajo
							//me muevo a la abajo	
							
						
						
					}
				}
				
				//caso f-1
				if(existePosicionEnMatriz(auxFMenos, colVacio)) {
					//Matriz ordenada
					auxImagenOrdenada = this.MatrizOrdenada[auxFMenos][colVacio];
					auxImagenDesordenada = this.MatrizDesordenada[auxFMenos][colVacio];
					
					indicePosicionVacio = new Point(filaVacio, colVacio);
					
					
					if(!sonBufferedImagenIguales(auxImagenDesordenada,auxImagenOrdenada ) ) {
						
						Point posicionImagen =devolverPosicionEnMatriz(auxImagenDesordenada);
						//Proceda
						if(posicionImagen != null && posicionImagen.equals(indicePosicionVacio)) {
							
							
							sb.append("Movete a la Arriba").append(" ").append("\n");
						}
						
							
							//Listo mi vacio es igual a como deberia ser mi Arriba
							//me muevo a la Arriba	
							
						
						
					}
				}
				return sb.toString();
				
				
		
	}


	private Point devolverPosicionEnMatriz(BufferedImage imagenA) {
		Point indicePosicion;
		for (int f = 0; f < MatrizOrdenada.length; f++) {
			for (int c = 0; c < MatrizOrdenada[f].length; c++) {
				if(sonBufferedImagenIguales(this.MatrizOrdenada[f][c], imagenA)) {
					indicePosicion = new Point(f,c);
					return indicePosicion;
				}
			}
		}
		return null;
	
	}
	
	
	private boolean existePosicionEnMatriz(int fila, int columna) {
        if((columna  <= this.MatrizDesordenada.length - 1 && columna  >= 0) && (fila <= this.MatrizDesordenada.length - 1 && fila >= 0) ) {
            return true;

        }
        return false;
    }
	

	public int desplazarmeEnMatriz(String tecla, int contador) 
	{
		int fila = this.filaVacio;
		int columna = this.colVacio;
		BufferedImage aux;
		//Si se mueve a la derecha
		if(tecla.equals("d")) 
		{
			if (columna < this.MatrizDesordenada[fila].length - 1) 
			{
				aux = this.MatrizDesordenada[fila][columna + 1];
				MatrizDesordenada[fila][columna + 1] = this.MatrizDesordenada[fila][columna];
				MatrizDesordenada[fila][columna] = aux;
				this.colVacio++;
				contador = incrementarContador(contador);
				return contador;


			}
		}


		//Si se mueve a la arriba
		if(tecla.equals("w")) 
		{
			if (fila > 0) 
			{
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
		if(tecla.equals("s")) 
		{
			if (fila < this.MatrizDesordenada.length - 1) 
			{
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
	public BufferedImage[][] getMatrizDesordenada() 
	{
		return this.MatrizDesordenada;
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

}
