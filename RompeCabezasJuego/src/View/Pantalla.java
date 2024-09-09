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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
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
	private JButton botonAyuda;
	private JLabel dondeVaElCero;
	private JLabel instructivo;

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
		frame.setBounds(150, 200, 390, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelPantallaJuego = new JPanel();
		panelPantallaJuego.setBackground(new Color(128, 64, 0));
		frame.getContentPane().add(panelPantallaJuego, BorderLayout.CENTER);
		panelPantallaJuego.setLayout(null);
		
		JLabel lblElVacioTerminaAqui = new JLabel("");
		lblElVacioTerminaAqui.setHorizontalAlignment(SwingConstants.CENTER);
		lblElVacioTerminaAqui.setHorizontalTextPosition(SwingConstants.CENTER);
		lblElVacioTerminaAqui.setFont(new Font("Arial Black", Font.PLAIN, 40));
		lblElVacioTerminaAqui.setOpaque(true);
		lblElVacioTerminaAqui.setBackground(new Color(190, 140, 65));
		lblElVacioTerminaAqui.setAutoscrolls(true);
		lblElVacioTerminaAqui.setFocusable(false);
		lblElVacioTerminaAqui.setBounds(156, 175, 46, 52);
		panelPantallaJuego.add(lblElVacioTerminaAqui);
	    lblElVacioTerminaAqui.setBorder(new LineBorder(Color.BLACK, 2));
	    lblElVacioTerminaAqui.setText(controlador.devolverElementoFinalMatriz());
	    

		
		JLabel lblCampoDeTextoAyuda = new JLabel("");
		lblCampoDeTextoAyuda.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCampoDeTextoAyuda.setOpaque(true);
		lblCampoDeTextoAyuda.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCampoDeTextoAyuda.setHorizontalAlignment(SwingConstants.CENTER);
		lblCampoDeTextoAyuda.setFocusable(false);
		lblCampoDeTextoAyuda.setBounds(243, 89, 121, 67);
		panelPantallaJuego.add(lblCampoDeTextoAyuda);
		
		//botones
		botonVolverAMenu = new JButton("Atras");
		botonVolverAMenu.setOpaque(false);
		botonVolverAMenu.setFocusable(false);
		botonVolverAMenu.setBounds(0, 238, 89, 23);
		panelPantallaJuego.add(botonVolverAMenu);
		
		botonAyuda = new JButton("Ayuda");
		botonAyuda.setOpaque(false);
		botonAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblCampoDeTextoAyuda.setText("<html>" + controlador.proximoMovimiento().replace("\n", "<br>") + 
											"</html>");
			}
		});
		botonAyuda.setFocusable(false);
		botonAyuda.setBounds(179, 238, 89, 23);
		panelPantallaJuego.add(botonAyuda);
		
		matrizDeNumerosVisible = new JLabel("");
		matrizDeNumerosVisible.setBorder(new LineBorder(new Color(0, 0, 0)));
		matrizDeNumerosVisible.setHorizontalAlignment(SwingConstants.CENTER);
		matrizDeNumerosVisible.setOpaque(true);
		matrizDeNumerosVisible.setBackground(new Color(190, 140, 65));
		matrizDeNumerosVisible.setAutoscrolls(true);
		matrizDeNumerosVisible.setFont(new Font("Arial Black", Font.PLAIN, 40));
		matrizDeNumerosVisible.setBounds(56, 57, 163, 170);
		panelPantallaJuego.add(matrizDeNumerosVisible);
		
		matrizDeNumerosVisible.setText("<html>" + controlador.imprimirMatriz().replace("\n", "<br>") + "</html>");
		
		contadorMovimientos = new JLabel("Contador: 0");
		contadorMovimientos.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		contadorMovimientos.setHorizontalAlignment(SwingConstants.CENTER);
		contadorMovimientos.setOpaque(true);
		contadorMovimientos.setForeground(new Color(0, 0, 0));
		contadorMovimientos.setBackground(new Color(255, 255, 255));
		contadorMovimientos.setFont(new Font("Arial Black", Font.BOLD, 15));
		contadorMovimientos.setBounds(56, 15, 163, 29);
		panelPantallaJuego.add(contadorMovimientos);
		
		dondeVaElCero = new JLabel();
		dondeVaElCero.setFont(new Font("Tahoma", Font.PLAIN, 10));
		dondeVaElCero.setHorizontalTextPosition(SwingConstants.CENTER);
		dondeVaElCero.setHorizontalAlignment(SwingConstants.CENTER);
		dondeVaElCero.setOpaque(true);
		dondeVaElCero.setBorder(new LineBorder(new Color(255, 128, 64), 2));
		dondeVaElCero.setBounds(243, 11, 121, 67);
		dondeVaElCero.setText("<html>" + "El casillero vacio representado con el numero 0 debe terminar en el marco delineado".replace("\n", "<br>") + "</html>");
		panelPantallaJuego.add(dondeVaElCero);
		
		instructivo = new JLabel();
		instructivo.setOpaque(true);
		instructivo.setFocusable(false);
		instructivo.setBorder(new LineBorder(new Color(255, 128, 0), 2));
		instructivo.setFont(new Font("Tahoma", Font.PLAIN, 8));
		instructivo.setBounds(243, 166, 121, 67);
		instructivo.setText("<html>" + "Puedes moverte con las siguientes teclas: \n w=Arriba \n a=Izquierda \n s=Abajo \n d=Derecha".replace("\n", "<br>") + "</html>");
		panelPantallaJuego.add(instructivo);
		
		
		
		 // ESCUCHAR INPUTS DEL TECLADO
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
			    char teclaPresionada = e.getKeyChar();
			    String caracterTeclaPresionada = String.valueOf(teclaPresionada);
			    botonVolverAMenu.setVisible(false);
			    contador = controlador.desplazarmeEnMatriz(caracterTeclaPresionada, contador);


			    // Verifica que el borde esté configurado
			    lblElVacioTerminaAqui.setBorder(new LineBorder(Color.BLACK, 2));
			    
			    // Actualiza el texto en lblElVacioTerminaAqui
			    lblElVacioTerminaAqui.setText(controlador.devolverElementoFinalMatriz());
			    
			    // Actualiza el texto en matrizDeNumerosVisible y contadorMovimientos
			    matrizDeNumerosVisible.setText("<html>" + controlador.imprimirMatriz().replace("\n", "<br>") + "</html>");
			    String contadorVisible = String.valueOf(contador);
			    contadorMovimientos.setText("Contador: " + contadorVisible);
			    
			    if (controlador.gano()) {
			        controlador.imprimirGane();
			        botonVolverAMenu.setVisible(true);
			    }
			}		
			@Override
			public void keyReleased(KeyEvent e) 
			{
				// TODO Auto-generated method stub	
			}
			 
		 });
			
		botonVolverAMenu.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Menu menu = new Menu();
				
				menu.setVisiblePantalla(true);
				frame.setVisible(false);
				frame.dispose();
			}
		}); 
							
	}

	public void setVisiblePantalla(boolean condicion) 
	{
		frame.setVisible(condicion);
	}
}
