package View;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ImagenesAuxiliar {
	//private BufferedImage[][] MatrizDesordenada;
	//private BufferedImage[][] MatrizOrdenada;
	private HashMap<Integer, BufferedImage> hashMapImagenesCortadas;
	
	
	
	
	

	public void cortarImagen(BufferedImage imagen) 
	{

		int filas = 3;
		int columnas = 3;
		int ancho = imagen.getWidth() / columnas;
		int alto = imagen.getHeight() / filas;
		
			for (int i = 0; i < filas; i++) 
			{
				for (int j = 0; j < columnas; j++) {
					this.hashMapImagenesCortadas.put(i, imagen.getSubimage(j * ancho, i * alto, ancho, alto));
				}
			  
			}

		}
	
	 
	 boolean sonBufferedImagenIguales(BufferedImage imagenA, BufferedImage imagenB) 
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
	 public HashMap<Integer, BufferedImage> getHashMapImagenesCortadas() {
			return hashMapImagenesCortadas;
		}

		public void setHashMapImagenesCortadas(HashMap<Integer, BufferedImage> hashMapImagenesCortadas) {
			this.hashMapImagenesCortadas = hashMapImagenesCortadas;
		}
	 
}
