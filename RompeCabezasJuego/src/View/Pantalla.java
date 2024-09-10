package View;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import FormControl.Controlador;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Cursor;

public class Pantalla {

	private JFrame frame;
	private Controlador controlador;
	private JLabel matrizDeNumerosVisible;
	private JLabel lblContadorDeMovimientos;
	private int contador = 0;
	private JButton botonVolverAMenu;
	private JButton botonAyuda;
	private JLabel lblDondeTerminaElCasilleroVacio;
	private JLabel lblInstructivo;

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
	 * Crear aplicacion
	 */
	public Pantalla() {
		inicializar();
	}

	private void inicializar() {
		frame = new JFrame();
		controlador = new Controlador();
		frame.setBounds(150, 200, 390, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelPantallaJuego = new JPanel();
		panelPantallaJuego.setBackground(new Color(128, 64, 0));

//		JLabel lblElVacioTerminaAqui = new JLabel("");
//		lblElVacioTerminaAqui.setHorizontalAlignment(SwingConstants.CENTER);
//		lblElVacioTerminaAqui.setHorizontalTextPosition(SwingConstants.CENTER);
//		lblElVacioTerminaAqui.setFont(new Font("Arial Black", Font.PLAIN, 40));
//		lblElVacioTerminaAqui.setOpaque(true);
//		lblElVacioTerminaAqui.setBackground(new Color(190, 140, 65));
//		lblElVacioTerminaAqui.setAutoscrolls(true);
//		lblElVacioTerminaAqui.setFocusable(false);
//		lblElVacioTerminaAqui.setBounds(156, 175, 46, 52);
//		panelPantallaJuego.add(lblElVacioTerminaAqui);
//		lblElVacioTerminaAqui.setBorder(new LineBorder(Color.BLACK, 2));
//		lblElVacioTerminaAqui.setText(controlador.devolverElementoFinalMatriz());



		JLabel lblCampoDeTextoAyuda = new JLabel("");
		lblCampoDeTextoAyuda.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCampoDeTextoAyuda.setOpaque(true);
		lblCampoDeTextoAyuda.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCampoDeTextoAyuda.setHorizontalAlignment(SwingConstants.CENTER);
		lblCampoDeTextoAyuda.setFocusable(false);

		botonVolverAMenu = new JButton("Atras");
		botonVolverAMenu.setOpaque(false);
		botonVolverAMenu.setFocusable(false);

		botonAyuda = new JButton("Ayuda");
		botonAyuda.setOpaque(false);
		botonAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblCampoDeTextoAyuda.setText("<html>" + controlador.proximoMovimiento().replace("\n", "<br>") + 
						"</html>");
			}
		});
		botonAyuda.setFocusable(false);

		matrizDeNumerosVisible = new JLabel("");
		matrizDeNumerosVisible.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		matrizDeNumerosVisible.setBorder(new LineBorder(new Color(0, 0, 0)));
		matrizDeNumerosVisible.setHorizontalAlignment(SwingConstants.CENTER);
		matrizDeNumerosVisible.setOpaque(true);
		matrizDeNumerosVisible.setBackground(new Color(190, 140, 65));
		matrizDeNumerosVisible.setAutoscrolls(true);
		matrizDeNumerosVisible.setFont(new Font("Arial Black", Font.PLAIN, 40));

		matrizDeNumerosVisible.setText("<html>" + controlador.imprimirMatriz().replace("\n", "<br>") + "</html>");

		lblContadorDeMovimientos = new JLabel("Contador: 0");
		lblContadorDeMovimientos.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblContadorDeMovimientos.setHorizontalAlignment(SwingConstants.CENTER);
		lblContadorDeMovimientos.setOpaque(true);
		lblContadorDeMovimientos.setForeground(new Color(0, 0, 0));
		lblContadorDeMovimientos.setBackground(new Color(255, 255, 255));
		lblContadorDeMovimientos.setFont(new Font("Arial Black", Font.BOLD, 15));

		lblDondeTerminaElCasilleroVacio = new JLabel();
		lblDondeTerminaElCasilleroVacio.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDondeTerminaElCasilleroVacio.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDondeTerminaElCasilleroVacio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDondeTerminaElCasilleroVacio.setOpaque(true);
		lblDondeTerminaElCasilleroVacio.setBorder(new LineBorder(new Color(255, 128, 64), 2));
		lblDondeTerminaElCasilleroVacio.setText("<html>" + "El casillero vacio representado con el numero 0 debe terminar en la esquina inferior derecha".replace("\n", "<br>") + "</html>");

		lblInstructivo = new JLabel();
		lblInstructivo.setOpaque(true);
		lblInstructivo.setFocusable(false);
		lblInstructivo.setBorder(new LineBorder(new Color(255, 128, 0), 2));
		lblInstructivo.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblInstructivo.setText("<html>" + "Puedes moverte con las siguientes teclas: \n w=Arriba \n a=Izquierda \n s=Abajo \n d=Derecha".replace("\n", "<br>") + "</html>");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panelPantallaJuego, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panelPantallaJuego, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
		);
		GroupLayout gl_panelPantallaJuego = new GroupLayout(panelPantallaJuego);
		gl_panelPantallaJuego.setHorizontalGroup(
			gl_panelPantallaJuego.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPantallaJuego.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panelPantallaJuego.createParallelGroup(Alignment.LEADING)
						.addComponent(lblContadorDeMovimientos, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
						.addComponent(matrizDeNumerosVisible, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
					.addGap(24)
					.addGroup(gl_panelPantallaJuego.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDondeTerminaElCasilleroVacio, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
						.addComponent(lblCampoDeTextoAyuda, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
						.addComponent(lblInstructivo, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
					.addGap(10))
				.addGroup(gl_panelPantallaJuego.createSequentialGroup()
					.addComponent(botonVolverAMenu, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(90)
					.addComponent(botonAyuda, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
		);
		gl_panelPantallaJuego.setVerticalGroup(
			gl_panelPantallaJuego.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPantallaJuego.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panelPantallaJuego.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelPantallaJuego.createSequentialGroup()
							.addGap(4)
							.addComponent(lblContadorDeMovimientos, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
							.addGap(13)
							.addComponent(matrizDeNumerosVisible, GroupLayout.PREFERRED_SIZE, 170, Short.MAX_VALUE)
							.addGap(6))
						.addGroup(gl_panelPantallaJuego.createSequentialGroup()
							.addComponent(lblDondeTerminaElCasilleroVacio, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
							.addGap(11)
							.addComponent(lblCampoDeTextoAyuda, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(lblInstructivo, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)))
					.addGap(5)
					.addGroup(gl_panelPantallaJuego.createParallelGroup(Alignment.LEADING)
						.addComponent(botonVolverAMenu)
						.addComponent(botonAyuda)))
		);
		panelPantallaJuego.setLayout(gl_panelPantallaJuego);
		frame.getContentPane().setLayout(groupLayout);



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


//				//Mantiene el borde configurado
//				lblElVacioTerminaAqui.setBorder(new LineBorder(Color.BLACK, 2));
//
//				// Actualiza el texto del label recuadrado
//				lblElVacioTerminaAqui.setText(controlador.devolverElementoFinalMatriz());

				// Actualiza el texto en la matriz visible y en el 
				matrizDeNumerosVisible.setText("<html>" + controlador.imprimirMatriz().replace("\n", "<br>") + "</html>");
				String contadorVisible = String.valueOf(contador);
				lblContadorDeMovimientos.setText("Contador: " + contadorVisible);

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
