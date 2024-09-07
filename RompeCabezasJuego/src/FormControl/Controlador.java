package FormControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class Controlador {

	private int[][] matriz = new int[3][3];
	private int[][] matrizRespuesta = new int[3][3];
	private int filaCasillaVacia = 2; // Posición inicial del casillero vacío
	private int columnaCasillaVacia = 2;
	private int numeroRepresentativoDeVacio=0;
	public Controlador() {
		inicializarMatriz();
	}

	private void mezclarMatriz() 
	{
		Random rand = new Random();
		String[] movimientos = {"w", "a", "s", "d"};

		for (int cantidadMovimientosAleatorios = 0; cantidadMovimientosAleatorios < 1000; cantidadMovimientosAleatorios++) 
		{
			String movimiento = movimientos[rand.nextInt(movimientos.length)];
			desplazarmeEnMatriz(movimiento, 0);
		}
	}

	private void inicializarMatriz() 
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
		mezclarMatriz();
	}

	//	public void proximoMovimiento() {
	//		
	//		//caso c+1
	//		int auxFMenos = filaCasillaVacia -1;
	//		int auxFMas = filaCasillaVacia +1;
	//		int auxCMas = columnaCasillaVacia +1;
	//		if(existePosicionEnMatriz(filaCasillaVacia,auxCMas) && 
	//				existePosicionEnMatriz(auxFMas ,columnaCasillaVacia)) {
	//			//Cual de ambos es mayor:
	//			if(this.matriz[filaCasillaVacia][auxCMas] >  this.matriz[auxFMas][columnaCasillaVacia]) {
	//				//Si el antecedente "auxFMas" es menor a auxCMas... recomiendo moverme hacia arriba
	//				System.out.println("Es menor a mi columna derecha: " + auxFMas + " " + columnaCasillaVacia);
	//				System.out.println("Muevete hacia abajo");
	//			}
	//		}
	//		if(existePosicionEnMatriz(filaCasillaVacia,auxCMas) && 
	//				existePosicionEnMatriz(auxFMenos ,columnaCasillaVacia)) {
	//			//Cual de ambos es mayor:
	//			if(this.matriz[filaCasillaVacia][auxCMas] >  this.matriz[auxFMenos][columnaCasillaVacia]) {
	//				//Si el antecedente "auxFMenos" es menor a auxCMas... recomiendo moverme hacia abajo
	//				System.out.println("Es menor a mi columna derecha: " + auxFMenos + " " + columnaCasillaVacia);
	//				System.out.println("Muevete hacia arriba");
	//			}
	//		}
	//	}
	//	

	//	private boolean existePosicionEnMatriz(int fila, int columna) {
	//        if((columna  <= this.matriz.length - 1 && columna  >= 0) && (fila <= this.matriz.length - 1 && fila >= 0) ) {
	//            return true;
	//
	//        }
	//        return false;
	//    }

	public void imprimirGane() 
	{
		JOptionPane.showMessageDialog(null, "Juego finalizado, Ganaste!!!!!1!!1");
	}
	//
	public int desplazarmeEnMatriz(String tecla, int contador) 
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
				moverIzquierda(fila,columna,auxiliarIntercambio);
				contador = incrementarContador(contador);
				return contador;
			}	
		}
		if(tecla.equals("s")) {
			if (fila < matriz.length - 1) 
			{
				moverAbajo(fila,columna,auxiliarIntercambio);
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


	private void moverIzquierda(int fila, int columna, int auxiliarIntercambio) 
	{
		auxiliarIntercambio = matriz[fila][columna - 1];
		matriz[fila][columna - 1] = matriz[fila][columna];
		matriz[fila][columna] = auxiliarIntercambio;
		this.columnaCasillaVacia--;
	}

	private void moverAbajo(int fila, int columna, int auxiliarIntercambio) 
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


	public String imprimirMatriz() 
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.matriz.length; i++) 
		{
			for (int j = 0; j < this.matriz[i].length; j++) 
			{
				sb.append(this.matriz[i][j]).append(" ");
			}
			sb.append("\n"); // Salto de línea
		}
		return sb.toString();
	}


	public int[][] getMatriz() 
	{
		return this.matriz;
	}
	public boolean gano() 
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
}
