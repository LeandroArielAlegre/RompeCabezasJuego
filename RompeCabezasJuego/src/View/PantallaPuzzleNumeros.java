package View;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import FormControl.LogicaPuzzle;
import FormControl.Sonido;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.Cursor;

public class PantallaPuzzleNumeros {

	private JFrame pantallaPrincipal;
	private LogicaPuzzle logicaPuzzle;
	private Sonido sonido;
	private JLabel matrizDeNumerosVisible;
	private JLabel lblContadorDeMovimientos;
	private int contadorMovimientos = 0;
	private JButton botonVolverAMenu;
	private JButton botonAyuda;
	private JLabel lblDondeTerminaElCasilleroVacio;
	private JLabel lblInstructivo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPuzzleNumeros window = new PantallaPuzzleNumeros();
					window.pantallaPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crear aplicacion
	 */
	public PantallaPuzzleNumeros() {
		inicializar();
	}

	private void inicializar() {
		pantallaPrincipal = new JFrame();
		pantallaPrincipal.setBounds(150, 200, 390, 300);
		pantallaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		logicaPuzzle = new LogicaPuzzle();
		sonido = new Sonido();

		setIconoDeVentana();

		JPanel panelPantallaJuego = new JPanel();
		panelPantallaJuego.setBackground(new Color(128, 64, 0));

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
				setLabelMensajeAyuda(lblCampoDeTextoAyuda);
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

		matrizDeNumerosVisible.setText("<html>" + logicaPuzzle.imprimirMatriz().replace("\n", "<br>") + "</html>");

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
		lblDondeTerminaElCasilleroVacio.setText("<html>" + "El casillero vacio representado con el numero 0 debe terminar en la esquina superior izquierda".replace("\n", "<br>") + "</html>");

		lblInstructivo = new JLabel();
		lblInstructivo.setOpaque(true);
		lblInstructivo.setFocusable(false);
		lblInstructivo.setBorder(new LineBorder(new Color(255, 128, 0), 2));
		lblInstructivo.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblInstructivo.setText("<html>" + "Puedes moverte con las siguientes teclas: \n w=Arriba \n a=Izquierda \n s=Abajo \n d=Derecha".replace("\n", "<br>") + "</html>");

		setComponentesResponsiveEnPantallaPrincipal(panelPantallaJuego, lblCampoDeTextoAyuda);

		pantallaPrincipal.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
			@Override
			public void keyPressed(KeyEvent e) {
				char teclaPresionada = e.getKeyChar();
				String caracterTeclaPresionada = String.valueOf(teclaPresionada);

				ArrayList<String> teclasPermitidas= new ArrayList<String>()
				{

					private static final long serialVersionUID = -1760916167558614943L;

				};

				teclasPermitidas.add("w");
				teclasPermitidas.add("a");
				teclasPermitidas.add("s");
				teclasPermitidas.add("d");
				if(teclasPermitidas.contains(caracterTeclaPresionada)) 
				{
					int movimientosAnteriores = contadorMovimientos;
					contadorMovimientos = logicaPuzzle.desplazarmeEnMatriz(caracterTeclaPresionada, contadorMovimientos);

					if (contadorMovimientos > movimientosAnteriores) 
					{
						sonido.reproducirSonido("/recursos/boton.wav", "boton");
					}
				}

				// Actualiza el texto en la matriz visible y en el 
				matrizDeNumerosVisible.setText("<html>" + logicaPuzzle.imprimirMatriz().replace("\n", "<br>") + "</html>");
				String contadorVisible = String.valueOf(contadorMovimientos);
				lblContadorDeMovimientos.setText("Contador: " + contadorVisible);

				if (logicaPuzzle.gano()) {
					JOptionPane.showMessageDialog(null, "Juego finalizado, Ganaste!");
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
				pantallaPrincipal.setVisible(false);
				pantallaPrincipal.dispose();
			}
		}); 

	}

	private void setIconoDeVentana() {
		Image icon = new ImageIcon(getClass().getResource("/recursos/icono.png")).getImage();	
		pantallaPrincipal.setIconImage(icon);
	}

	private void setComponentesResponsiveEnPantallaPrincipal(JPanel panelPantallaJuego, JLabel lblCampoDeTextoAyuda) {
		GroupLayout groupLayout = crearGrupoDeElementosDePantallaPrincipal(panelPantallaJuego);
		GroupLayout gl_panelPantallaJuego = agruparElementoDelPanelPrincipal(panelPantallaJuego, lblCampoDeTextoAyuda);
		panelPantallaJuego.setLayout(gl_panelPantallaJuego);
		pantallaPrincipal.getContentPane().setLayout(groupLayout);
	}

	private GroupLayout agruparElementoDelPanelPrincipal(JPanel panelPantallaJuego, JLabel lblCampoDeTextoAyuda) {
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
		return gl_panelPantallaJuego;
	}

	private GroupLayout crearGrupoDeElementosDePantallaPrincipal(JPanel panelPantallaJuego) {
		GroupLayout groupLayout = new GroupLayout(pantallaPrincipal.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panelPantallaJuego, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panelPantallaJuego, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
				);
		return groupLayout;
	}

	public void setVisiblePantalla(boolean condicion) 
	{
		pantallaPrincipal.setVisible(condicion);
	}

	private void setLabelMensajeAyuda(JLabel lblCampoDeTextoAyuda) {
		lblCampoDeTextoAyuda.setText("<html>" + logicaPuzzle.proximoMovimiento().replace("\n", "<br>") + 
				"</html>");
	}
}
