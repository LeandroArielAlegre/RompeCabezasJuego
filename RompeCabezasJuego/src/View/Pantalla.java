package View;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import FormControl.Controlador;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pantalla {

	private JFrame frame;
	private Controlador controlador;
	private JLabel Matriz;
	private JLabel contadorMovimientos;
	private int contador = 0;
	private String [] teclado =  {"a","d","w","s"};
	private JButton btnVolver;
	private menu menu;
	/**
	 * Launch the application.
	 */
	public void setVisiblePantalla(boolean condicion) {
		frame.setVisible(condicion);
	}
	
	public void setMenu(menu m1) {
		this.menu = m1;
	}
	
	
	
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
		frame.setBounds(150, 200, 284, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 64, 0));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//boton
		//botones
		btnVolver = new JButton("Atras");
		btnVolver.setFocusable(false);
		btnVolver.setBounds(0, 238, 89, 23);
		panel.add(btnVolver);
		//
		Matriz = new JLabel("");
		Matriz.setHorizontalAlignment(SwingConstants.CENTER);
		Matriz.setOpaque(true);
		Matriz.setBackground(new Color(190, 140, 65));
		Matriz.setAutoscrolls(true);
		Matriz.setFont(new Font("Arial Black", Font.PLAIN, 40));
		Matriz.setBounds(56, 55, 163, 170);
		panel.add(Matriz);
		
		//Matriz.setText(controlador.imprimirMatriz());
		Matriz.setText("<html>" + controlador.imprimirMatriz().replace("\n", "<br>") + "</html>");
		
		contadorMovimientos = new JLabel("Contador: 0");
		contadorMovimientos.setHorizontalAlignment(SwingConstants.CENTER);
		contadorMovimientos.setOpaque(true);
		contadorMovimientos.setForeground(new Color(255, 0, 0));
		contadorMovimientos.setBackground(new Color(0, 255, 64));
		contadorMovimientos.setFont(new Font("Arial Black", Font.BOLD, 15));
		contadorMovimientos.setBounds(83, 15, 117, 29);
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
				
			
			}
			

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			 
		 });
		
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu m1 = new menu();
				
				m1.setVisiblePantalla(true);
				frame.setVisible(false);
				//frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				frame.dispose();
				//menu.setVisiblePantalla(true);
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
