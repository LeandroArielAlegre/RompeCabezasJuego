package View;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ImagenesAuxiliar {
	
	private HashMap<Integer, BufferedImage> hashMapImagenesCortadas;	
	
	ImagenesAuxiliar(){
		hashMapImagenesCortadas=new HashMap<Integer, BufferedImage>();
	}

	public void cortarImagen(BufferedImage imagen) 
	{

		int filas = 3;
		int columnas = 3;
		int ancho = imagen.getWidth() / columnas;
		int alto = imagen.getHeight() / filas;

		for (int fila = 0; fila < filas; fila++) 
		{
			for (int columna = 0; columna < columnas; columna++) 
			{
				this.hashMapImagenesCortadas.put(fila * 3 + columna, imagen.getSubimage(columna * ancho, fila * alto, ancho, alto));
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
