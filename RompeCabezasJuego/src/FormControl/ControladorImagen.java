package FormControl;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JOptionPane;
public class ControladorImagen {
	private BufferedImage[][] MatrizDesordenada;
//	private BufferedImage[][] MatrizOrdenada;
	private int filaVacio = 2; // Posición inicial del casillero vacío
	private int colVacio = 2;
	public ControladorImagen() {
		
	}
	public  void imprimirGane() {
		JOptionPane.showMessageDialog(null, "Juego finalizado, Ganaste!!!!!1!!1");
	}

	public boolean gano(BufferedImage[][] MatrizDesordenada, BufferedImage[][] MatrizOrdenada) {
	    
	    for (int i = 0; i < MatrizDesordenada.length; i++) {
	        for (int j = 0; j < MatrizDesordenada[i].length; j++) {
	            if (!imagenIgual(MatrizDesordenada[i][j], MatrizOrdenada[i][j])) {
	                return false;
	            }
	        }
	    }
	    
	    return true;  // Si todas las imágenes coinciden gano
	}

    private boolean imagenIgual(BufferedImage img1, BufferedImage img2) {
//        if (img1 == null || img2 == null) {
//            return img1 == img2;
//        }
        for (int x = 0; x < img1.getWidth(); x++) {
            for (int y = 0; y < img1.getHeight(); y++) {
                if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
                    return false;
                }
            }
        }
        return true;
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
	 	 	 
	 public int desplazarmeEnMatriz(String tecla, int contador) {
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

//	public BufferedImage[][] copiarMatriz(BufferedImage[][] matrizImagenOrdenada) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
