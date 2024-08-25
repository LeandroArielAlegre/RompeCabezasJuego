package View;
import FormControl.Controlador;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class Pantalla {

	private JFrame frame;
	private Controlador controlador;
	private JLabel Matriz;
	private JLabel contadorMovimientos;
	private int contador = 0;
	private String [] teclado =  {"a","d","w","s"};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pantalla window = new Pantalla();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Pantalla() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		controlador = new Controlador();
		frame.setBounds(150, 200, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		Matriz = new JLabel("");
		Matriz.setFont(new Font("Tahoma", Font.PLAIN, 50));
		Matriz.setBounds(137, 34, 152, 179);
		panel.add(Matriz);
		
		//Matriz.setText(controlador.imprimirMatriz());
		Matriz.setText("<html>" + controlador.imprimirMatriz().replace("\n", "<br>") + "</html>");
		
		contadorMovimientos = new JLabel("Contador: 0");
		contadorMovimientos.setForeground(new Color(255, 0, 0));
		contadorMovimientos.setBackground(new Color(255, 0, 0));
		contadorMovimientos.setFont(new Font("Arial Black", Font.BOLD, 15));
		contadorMovimientos.setBounds(10, 11, 117, 29);
		panel.add(contadorMovimientos);
		
		 // ESCUCHAR INPUTS DEL TECLADO
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				char tecla = e.getKeyChar();
				String teclaS1 =String.valueOf(tecla);
				if(esTecla(teclaS1)) {
					contador = controlador.desplazarmeEnMatriz(teclaS1, contador);
                    Matriz.setText("<html>" + controlador.imprimirMatriz().replace("\n", "<br>") + "</html>");
                    String contadorS = String.valueOf(contador);
                    contadorMovimientos.setText("Contador: " +contadorS);
					
				}
				
				/*
				  if (e.getKeyChar() == 'd') { // Si se presiona la tecla "a"
	                    controlador.desplazarmeEnMatriz("d");
	                    contador +=1;
	                    Matriz.setText("<html>" + controlador.imprimirMatriz().replace("\n", "<br>") + "</html>");
	                    String contadorS = String.valueOf(contador);
	                    contadorMovimientos.setText("Contador: " +contadorS);
	                    
	                }
				  if (e.getKeyChar() == 'a') { // Si se presiona la tecla "a"
					  contador +=1;
					  controlador.desplazarmeEnMatriz("a");
					  Matriz.setText("<html>" + controlador.imprimirMatriz().replace("\n", "<br>") + "</html>");
					  String contadorS = String.valueOf(contador);
					  contadorMovimientos.setText("Contador: " +contadorS);
	                    
	                }
				  if (e.getKeyChar() == 'w') { // Si se presiona la tecla "a"
					  contador +=1;
					  controlador.desplazarmeEnMatriz("w");
					  Matriz.setText("<html>" + controlador.imprimirMatriz().replace("\n", "<br>") + "</html>");
					  String contadorS = String.valueOf(contador);
					  contadorMovimientos.setText("Contador: " +contadorS);
	                }
				  if (e.getKeyChar() == 's') { // Si se presiona la tecla "a"
					  contador +=1;
					  controlador.desplazarmeEnMatriz("s");
					  Matriz.setText("<html>" + controlador.imprimirMatriz().replace("\n", "<br>") + "</html>");
					  String contadorS = String.valueOf(contador);
					  contadorMovimientos.setText("Contador: " +contadorS);
	                    
	                }
	                */
			}
			

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			 
		 });
		
			
		
	}
	public boolean esTecla(String tecla) {
		for (int i = 0; i< this.teclado.length; i++) {
			if(tecla.equals(this.teclado[i]));
				return true;
			
		}
		return false;
	}
}
