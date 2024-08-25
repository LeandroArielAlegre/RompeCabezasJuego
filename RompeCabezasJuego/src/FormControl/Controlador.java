package FormControl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class Controlador {

	private int[][] matriz = new int[3][3];
	private int filaVacio = 2; // Posición inicial del casillero vacío
	private int colVacio = 2;
	
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
	// Método para inicializar la matriz con números únicos del 1 al 8 y un casillero vacío
	private void inicializarMatriz() {
		List<Integer> numeros = new ArrayList<>();
		for (int i = 1; i <= 8; i++) {
			numeros.add(i);
		}
		numeros.add(0); // Añadir el casillero vacío
		Collections.shuffle(numeros);//mesclar numeros (Randomly permutes the specified list using a default source ofrandomness)

		int index = 0;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				matriz[i][j] = numeros.get(index++);
				if (matriz[i][j] == 0) {
					this.filaVacio = i;
					this.colVacio = j;
				}
			}
		}
	}
	public static void imprimirGane() {
		JOptionPane.showMessageDialog(null, "Juego finalizado, Ganaste!!!!!1!!1");
	}

	public int desplazarmeEnMatriz(String tecla, int contador) {
		int fila = this.filaVacio;
		int columna = this.colVacio;
		int aux;
		//Si se mueve a la derecha
		if(tecla.equals("d")) {
			if (columna < matriz[fila].length - 1) {
				aux = matriz[fila][columna + 1];
				matriz[fila][columna + 1] = matriz[fila][columna];
				matriz[fila][columna] = aux;
				this.colVacio++;
				contador = incrementarContador(contador);
				return contador;


			}
		}


		//Si se mueve a la arriba
		if(tecla.equals("w")) {
			if (fila > 0) {
				aux = matriz[fila - 1][columna];
				matriz[fila - 1][columna] = matriz[fila][columna];
				matriz[fila][columna] = aux;
				this.filaVacio--;
				contador = incrementarContador(contador);
				return contador;

			}
		}



		//Si se mueve a la abajo
		if(tecla.equals("s")) {
			if (fila < matriz.length - 1) {
				aux = matriz[fila + 1][columna];
				matriz[fila + 1][columna] = matriz[fila][columna];
				matriz[fila][columna] = aux;
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
				aux = matriz[fila][columna - 1];
				matriz[fila][columna - 1] = matriz[fila][columna];
				matriz[fila][columna] = aux;
				this.colVacio--;
				contador = incrementarContador(contador);

				return contador;
			}	
		}
		return contador;
	}

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
