package FormControl;

import java.util.ArrayList;
//import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class Controlador {

	private int[][] matriz = new int[3][3];
	private int filaCasillaVacia = 2; // Posición inicial del casillero vacío
	private int columnaCasillaVacia = 2;
	private int numeroRepresentativoDeVacio=0;
	public Controlador() {
		inicializarMatriz();
	}

	// Método para inicializar la matriz con números únicos del 1 al 9
	//    private void inicializarMatriz() {
	//        List<Integer> numeros = new ArrayList<>();
	//        for (int i = 1; i <= 9; i++) {
	//            numeros.add(i);
	//        }
	//        Collections.shuffle(numeros);
	//
	//        int index = 0;
	//        for (int i = 0; i < matriz.length; i++) {
	//            for (int j = 0; j < matriz[i].length; j++) {
	//                matriz[i][j] = numeros.get(index++);
	//            }
	//        }
	//    }
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

		//Collections.shuffle(numeros);//mesclar numeros (Randomly permutes the specified list using a default source ofrandomness)

		int posicionEnMatriz = 0;
		for (int fila = 0; fila < matriz.length; fila++) {
			for (int columna = 0; columna < matriz[fila].length; columna++) {
				matriz[fila][columna] = numerosDeMatriz.get(posicionEnMatriz++);
				if (matriz[fila][columna] == 0) {
					this.filaCasillaVacia = fila;
					this.columnaCasillaVacia = columna;
				}
			}
		}
		mezclarMatriz();
	}
	//QUE PASO?
	
	//	private void mesclarMatriz(List<Integer> numeros) {
	//
	////		int fila = this.filaVacio;
	////		int columna = this.colVacio;
	////		int aux;
	//		for (int movimientos=0;movimientos<=1000;movimientos++) {
	//			Random rand= new Random();
	//			int auxNumero= rand.nextInt() * 4 + 0;//
	////			rand.nextInt(3);
	//			if(auxNumero==0) 
	//			{
	//			desplazarmeEnMatriz("w", 0);
	//			}else if(auxNumero==1) 
	//			{
	//			desplazarmeEnMatriz("a", 0);
	//			}else if(auxNumero==2) 
	//			{
	//			desplazarmeEnMatriz("s", 0);
	//			}else 
	//			{
	//			desplazarmeEnMatriz("d", 0);				
	//			}
	//		}
	////			rand.
	//		}


	//	private void mezclarMatriz() {
	//	    Random rand = new Random();
	//	    String[] movimientos = {"w", "a", "s", "d"};
	//	    
	//	    for (int i = 0; i < 1000; i++) {
	//	        String movimiento = movimientos[rand.nextInt(movimientos.length)];
	//	        desplazarmeEnMatriz(movimiento, 0);
	//	    }
	//	}
	public static void imprimirGane() {
		JOptionPane.showMessageDialog(null, "Juego finalizado, Ganaste!!!!!1!!1");
	}
	//
	public int desplazarmeEnMatriz(String tecla, int contador) {
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
			if (fila < matriz.length - 1) {
				moverAbajo(fila,columna,auxiliarIntercambio);

				contador = incrementarContador(contador);

				return contador;

			}
		}




		if(tecla.equals("d")) {
			if (columna < matriz[fila].length - 1) {
				moverDerecha(fila,columna,auxiliarIntercambio);
				contador = incrementarContador(contador);
				return contador;
			}
		}
		return contador;
	}


	private void moverIzquierda(int fila, int columna, int auxiliarIntercambio) {

		auxiliarIntercambio = matriz[fila][columna - 1];
		matriz[fila][columna - 1] = matriz[fila][columna];
		matriz[fila][columna] = auxiliarIntercambio;
		this.columnaCasillaVacia--;
	}

	private void moverAbajo(int fila, int columna, int auxiliarIntercambio) {

		auxiliarIntercambio = matriz[fila + 1][columna];
		matriz[fila + 1][columna] = matriz[fila][columna];
		matriz[fila][columna] = auxiliarIntercambio;
		this.filaCasillaVacia++;
	}

	private void moverArriba(int fila, int columna, int auxiliarIntercambio) {

		auxiliarIntercambio = matriz[fila - 1][columna];
		matriz[fila - 1][columna] = matriz[fila][columna];
		matriz[fila][columna] = auxiliarIntercambio;
		this.filaCasillaVacia--;
	}

	private void moverDerecha(int fila, int columna, int auxiliarIntercambio) {

		auxiliarIntercambio = matriz[fila][columna + 1];
		matriz[fila][columna + 1] = matriz[fila][columna];
		matriz[fila][columna] = auxiliarIntercambio;
		this.columnaCasillaVacia++;


	}

	//	private void desplazarmeEnMatriz(String direccion, int animacion) {
	//	    int nuevaFila = filaVacio;
	//	    int nuevaColumna = colVacio;
	//	    
	//	    switch (direccion) {
	//	        case "w": nuevaFila--; break;
	//	        case "s": nuevaFila++; break;
	//	        case "a": nuevaColumna--; break;
	//	        case "d": nuevaColumna++; break;
	//	    }
	//	    
	//	    if (esMovimientoValido(nuevaFila, nuevaColumna)) {
	//	        intercambiarCasillas(filaVacio, colVacio, nuevaFila, nuevaColumna);
	//	        filaVacio = nuevaFila;
	//	        colVacio = nuevaColumna;
	//	    }
	//	}

	//	private boolean esMovimientoValido(int fila, int columna) {
	//	    return fila >= 0 && fila < 4 && columna >= 0 && columna < 4;
	//	}

	//	private void intercambiarCasillas(int fila1, int col1, int fila2, int col2) {
	//	    int aux = matriz[fila1][col1];
	//	    matriz[fila1][col1] = matriz[fila2][col2];
	//	    matriz[fila2][col2] = aux;
	//	}
	public String imprimirMatriz() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.matriz.length; i++) {
			for (int j = 0; j < this.matriz[i].length; j++) {
				sb.append(this.matriz[i][j]).append(" ");
			}
			sb.append("\n"); // Salto de línea
		}
		return sb.toString();
	}

	//    private int[] buscarCasilleroVacio() {
	//        for (int i = 0; i < matriz.length; i++) {
	//            for (int j = 0; j < matriz[i].length; j++) {
	//                if (matriz[i][j] == 0) {
	//                    return new int[]{i, j};
	//                }
	//            }
	//        }
	//        return null; // Si no se encuentra un casillero vacío
	//    }

	public int[][] getMatriz() {
		return this.matriz;
	}

	private int incrementarContador(int contador) {
		return ++contador;
	}
}
