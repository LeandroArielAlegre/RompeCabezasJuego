package FormControl;
import java.awt.image.BufferedImage;
public class ControladorImagen {
	
	
	private BufferedImage[][] MatrizGanadora;
//	private BufferedImage[][] MatrizDesordenada;
	public ControladorImagen() {
		
	}
	
	
	 public BufferedImage[][] cortarImagen(BufferedImage imagen) {
	        int filas = 3;
	        int columnas = 3;
	        int ancho = imagen.getWidth() / columnas;
	        int alto = imagen.getHeight() / filas;

	         this.MatrizGanadora = new BufferedImage[filas][columnas];
	         
	         // Aqui ocurre la magia
	        for (int i = 0; i < filas; i++) {
	            for (int j = 0; j < columnas; j++) {
	            	this.MatrizGanadora[i][j] = imagen.getSubimage(j * ancho, i * alto, ancho, alto);
	            }
	        }

	        return this.MatrizGanadora;
	    }
	
}
