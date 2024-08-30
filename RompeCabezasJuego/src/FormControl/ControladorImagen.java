package FormControl;
import java.awt.image.BufferedImage;
public class ControladorImagen {
	
	
	private BufferedImage[][] MatrizOrdenada;
	private BufferedImage[][] MatrizDesordenada;
	private int filaVacio = 2; // Posición inicial del casillero vacío
	private int colVacio = 2;
	public ControladorImagen() {
		
	}
	
	
	 public BufferedImage[][] cortarImagen(BufferedImage imagen) {
	        int filas = 3;
	        int columnas = 3;
	        int ancho = imagen.getWidth() / columnas;
	        int alto = imagen.getHeight() / filas;

	         this.MatrizOrdenada = new BufferedImage[filas][columnas];
	         
	         // Aqui ocurre la magia
	        for (int i = 0; i < filas; i++) {
	            for (int j = 0; j < columnas; j++) {
	            	this.MatrizOrdenada[i][j] = imagen.getSubimage(j * ancho, i * alto, ancho, alto);
	            }
	        }

	        return this.MatrizOrdenada;
	    }
	 
	 
	 
	 public int desplazarmeEnMatriz(String tecla, int contador) {
			int fila = this.filaVacio;
			int columna = this.colVacio;
			BufferedImage aux;
			//Si se mueve a la derecha
			if(tecla.equals("d")) {
				if (columna < this.MatrizOrdenada[fila].length - 1) {
					aux = this.MatrizOrdenada[fila][columna + 1];
					MatrizOrdenada[fila][columna + 1] = this.MatrizOrdenada[fila][columna];
					MatrizOrdenada[fila][columna] = aux;
					this.colVacio++;
					contador = incrementarContador(contador);
					return contador;


				}
			}


			//Si se mueve a la arriba
			if(tecla.equals("w")) {
				if (fila > 0) {
					aux = this.MatrizOrdenada[fila - 1][columna];
					this.MatrizOrdenada[fila - 1][columna] = this.MatrizOrdenada[fila][columna];
					this.MatrizOrdenada[fila][columna] = aux;
					this.filaVacio--;
					contador = incrementarContador(contador);
					return contador;

				}
			}



			//Si se mueve a la abajo
			if(tecla.equals("s")) {
				if (fila < this.MatrizOrdenada.length - 1) {
					aux = this.MatrizOrdenada[fila + 1][columna];
					this.MatrizOrdenada[fila + 1][columna] = this.MatrizOrdenada[fila][columna];
					this.MatrizOrdenada[fila][columna] = aux;
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
					aux = this.MatrizOrdenada[fila][columna - 1];
					this.MatrizOrdenada[fila][columna - 1] = this.MatrizOrdenada[fila][columna];
					this.MatrizOrdenada[fila][columna] = aux;
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
	//hola
}
