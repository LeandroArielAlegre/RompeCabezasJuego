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
//import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pantalla {

	private JFrame frame;
	private Controlador controlador;
	private JLabel matrizDeNumerosVisible;
	private JLabel contadorMovimientos;
	private int contador = 0;
	private JButton botonVolverAMenu;

	public void mostrarPantalla(boolean condicion) {
		frame.setVisible(condicion);
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
		inicializar();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void inicializar() {
		frame = new JFrame();
		controlador = new Controlador();
		frame.setBounds(150, 200, 284, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelPantallaJuego = new JPanel();
		panelPantallaJuego.setBackground(new Color(128, 64, 0));
		frame.getContentPane().add(panelPantallaJuego, BorderLayout.CENTER);
		panelPantallaJuego.setLayout(null);
		
		//boton
		//botones
		botonVolverAMenu = new JButton("Atras");
		botonVolverAMenu.setFocusable(false);
		botonVolverAMenu.setBounds(0, 238, 89, 23);
		panelPantallaJuego.add(botonVolverAMenu);
		
		matrizDeNumerosVisible = new JLabel("");
		matrizDeNumerosVisible.setHorizontalAlignment(SwingConstants.CENTER);
		matrizDeNumerosVisible.setOpaque(true);
		matrizDeNumerosVisible.setBackground(new Color(190, 140, 65));
		matrizDeNumerosVisible.setAutoscrolls(true);
		matrizDeNumerosVisible.setFont(new Font("Arial Black", Font.PLAIN, 40));
		matrizDeNumerosVisible.setBounds(56, 55, 163, 170);
		panelPantallaJuego.add(matrizDeNumerosVisible);
		
		matrizDeNumerosVisible.setText("<html>" + controlador.imprimirMatriz().replace("\n", "<br>") + "</html>");
		
		contadorMovimientos = new JLabel("Contador: 0");
		contadorMovimientos.setHorizontalAlignment(SwingConstants.CENTER);
		contadorMovimientos.setOpaque(true);
		contadorMovimientos.setForeground(new Color(255, 0, 0));
		contadorMovimientos.setBackground(new Color(0, 255, 64));
		contadorMovimientos.setFont(new Font("Arial Black", Font.BOLD, 15));
		contadorMovimientos.setBounds(56, 15, 163, 29);
		panelPantallaJuego.add(contadorMovimientos);
		
		
		
		
		
		 // ESCUCHAR INPUTS DEL TECLADO
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				char teclaPresionada = e.getKeyChar();
				String caracterTeclaPresionada =String.valueOf(teclaPresionada);

					botonVolverAMenu.setVisible(false);
					contador = controlador.desplazarmeEnMatriz(caracterTeclaPresionada, contador);
                    matrizDeNumerosVisible.setText("<html>" + controlador.imprimirMatriz().replace("\n", "<br>") + "</html>");
                    String contadorVisible = String.valueOf(contador);
                    contadorMovimientos.setText("Contador: " +contadorVisible);
                    if (controlador.gano()) 
    				{
    		            // cuando el jugador gana...
                    	controlador.imprimirGane();
    		            botonVolverAMenu.setVisible(true);
    		            
    		        }

				
			
			}
			

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			 
		 });
		
		
		botonVolverAMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				
				menu.setVisiblePantalla(true);
				
				frame.setVisible(false);
				frame.dispose();
			}
		}); 
		
		
		
		
			
		
	}

	public void setVisiblePantalla(boolean condicion) {
		frame.setVisible(condicion);
	}
	
}
