package FormControl;

import javax.swing.JOptionPane;


public class Controlador {
	
private	int[][] matriz = {
		    {1, 2, 3},
		    {0, 5, 6},
		    {7, 8, 9},
		    
		}; // Matriz nxn con valores predefinidos 
			// o(n*2)
public Controlador() {
		
	}
	

	public static void ImprimirGane(){
		JOptionPane.showMessageDialog(null, "Juego finalizado, Ganaste!!!!!1!!1");
		
	}

	public int desplazarmeEnMatriz(String tecla, int contador) {
		int Posicion [] = buscarCasilleroVacio();
		int fila = Posicion[0];
		int columna = Posicion[1];
		int aux = 0;
		
		//Si se mueve a la derecha
		if(tecla.equals("d")) {
			if(columna < this.matriz[fila].length - 1) {
				aux = this.matriz[fila][columna +1]; //me desplazo en uno
			    this.matriz[fila][columna +1] = this.matriz[fila][columna];
			    this.matriz[fila][columna] = aux;
			    contador = incrementarContador(contador);
				return contador;
			   
				
			}
		}
			
		
	//Si se mueve a la arriba
		if(tecla.equals("w")) {
			if(fila > 0) {
				aux = this.matriz[fila -1][columna]; //me desplazo en uno
			    this.matriz[fila -1][columna] = this.matriz[fila][columna];
			    this.matriz[fila][columna] = aux;
			    contador = incrementarContador(contador);
				return contador;
				
				
			}
		}
		
		
		
		//Si se mueve a la abajo
		if(tecla.equals("s")) {
			if(fila < this.matriz.length -1) {
				aux = this.matriz[fila +1][columna]; //me desplazo en uno
			    this.matriz[fila +1][columna] = this.matriz[fila][columna];
			    this.matriz[fila][columna] = aux;
			    contador = incrementarContador(contador);
				return contador;
			
				
				
			}
		}
		
		
		
		//Si se mueve a la izquierda
		if(tecla.equals("a")) {
			if(columna > 0) {
				aux = this.matriz[fila][columna -1]; //me desplazo en uno
			    this.matriz[fila][columna -1] = this.matriz[fila][columna];
			    this.matriz[fila][columna] = aux;
			    contador = incrementarContador(contador);
				return contador;
			
				
			}
		}
		
		return contador;
		
		
		
	}
	
	
	public String imprimirMatriz() {
		StringBuilder sb = new StringBuilder();
		for (int i =0; i< this.matriz.length;i++) {
			for (int j =0; j< this.matriz.length;j++) {
				 sb.append(matriz[i][j]).append(" ");
				
			}
			 sb.append("\n"); // salto de linea
		}
		return sb.toString();
	
		
	}
	private int[] buscarCasilleroVacio() {
		for (int i =0; i< this.matriz.length;i++) {
			for (int j =0; j< this.matriz.length;j++) {
				if (this.matriz[i][j] == 0) {
					 return new int[] {i, j}; // Devuelve las coordenadas como un array de dos elementos
				}
			}
		}
		return null;
	}


	public int[][] getMatriz() {
		return matriz;
	}
	public int incrementarContador(int contador) {
		contador +=1;
		return contador;
		
	}
	public void a() {
		System.out.println("a");
		
		
		
	}



	
	
}
