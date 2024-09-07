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
//	private String [] teclado =  {"a","d","w","s"};
	private JButton botonVolverAMenu;
	private JButton botonAyuda;
//	JButton btnVolver;
//	private Menu menu;

	public void mostrarPantalla(boolean condicion) {
		frame.setVisible(condicion);
	}
	
//	public void setMenu(Menu m1) {
//		this.menu = m1;
//	}
	
	
	
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
		
		botonAyuda = new JButton("Ayuda");
		botonAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.proximoMovimiento();
			}
		});
		botonAyuda.setFocusable(false);
		botonAyuda.setBounds(1, 1, 89, 23);
		panelPantallaJuego.add(botonAyuda);
		
//		btnVolver = new JButton("Atras");
//		btnVolver.setFocusable(false);
//		btnVolver.addActionListener(new ActionListener() 
//		{
//			public void actionPerformed(ActionEvent e) {
//				Menu m1 = new Menu();
//				m1.setVisiblePantalla(true);
//				frame.setVisible(false);
//				frame.dispose();
//			}
//		});
//		btnVolver.setBounds(10, 443, 89, 23);
//		frame.getContentPane().add(btnVolver);
		
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
//				if(esTecla(teclaS1)) {
					botonVolverAMenu.setVisible(false);
					contador = controlador.desplazarmeEnMatriz(caracterTeclaPresionada, contador);
                    matrizDeNumerosVisible.setText("<html>" + controlador.imprimirMatriz().replace("\n", "<br>") + "</html>");
                    String contadorVisible = String.valueOf(contador);
                    contadorMovimientos.setText("Contador: " +contadorVisible);
                    
                    if (controlador.gano()) 
    				{
//    					Image imagenCasilleroVacio= MatrizImagenes[controladorImagen.getFilaVacio()][controladorImagen.getColVacio()].getScaledInstance(tecla, tecla, tecla);
//    					labels[controladorImagen.getFilaVacio()][controladorImagen.getColVacio()].setIcon((Icon) imagenCasilleroVacio);
    		            controlador.imprimirGane();
    		            botonVolverAMenu.setVisible(true);
    		            // cuando el jugador gana...
//    		            btnVolver.setVisible(true);
    		        }
//				}
				
			
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
				//frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				frame.dispose();
				//menu.setVisiblePantalla(true);
			}
		}); 
		
		
		
		
			
		
	}

	public void setVisiblePantalla(boolean condicion) {
		frame.setVisible(condicion);
	}
	
//	public boolean esTecla(String tecla) {
//		for (int i = 0; i< this.teclado.length; i++) {
//			if(tecla.equals(this.teclado[i]));
//				return true;
//			
//		}
//		return false;
//	}
}
