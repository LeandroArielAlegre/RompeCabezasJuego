
package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import FormControl.LogicaPuzzleImagenes;
import FormControl.Sonido;

import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.border.EtchedBorder;

public class PantallaPuzzleImagenes {
	private LogicaPuzzleImagenes logicaPuzzleImagenes;
	private JFrame pantallaPrincipal;
	private Sonido sonido;
	private BufferedImage[][] matrizImagenes;
	private JLabel [][] labelsImagenesRecortadas = new JLabel[3][3];
	private int contadorMovimientos = 0;
	private boolean estaJuegoIniciado = false;

	/**
	 * Create the application.
	 */
	public PantallaPuzzleImagenes() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		logicaPuzzleImagenes = new LogicaPuzzleImagenes();
		sonido = new Sonido();
		pantallaPrincipal = new JFrame();
		pantallaPrincipal.setResizable(false);
		pantallaPrincipal.setBounds(100, 100, 705, 516);
		pantallaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pantallaPrincipal.getContentPane().setLayout(null);
		pantallaPrincipal.getContentPane().setBackground(new Color(250, 250, 150));
		
		setIconoDeVentana();

		JComboBox<String> comboBoxSeleccionImagenes = new JComboBox<>(new String[] {"Imagen 1", "Imagen 2", "Imagen 3", "Imagen 4","Imagen 5"});
		comboBoxSeleccionImagenes.setFocusable(false);
		String [] rutaImagenPuzzle = {"/recursos/imagen1.png","/recursos/imagen2.jpg","/recursos/imagen3.jpg", "/recursos/imagen4.png", "/recursos/imagen5.png"};
		comboBoxSeleccionImagenes.setBounds(10, 344, 126, 20);
		pantallaPrincipal.getContentPane().add(comboBoxSeleccionImagenes);

		JLabel labelPuzzleResuelto = new JLabel("");
		labelPuzzleResuelto.setFocusable(false);
		labelPuzzleResuelto.setBounds(559, 187, 120, 114);

		JButton btnImagen = new JButton("Mostrar");
		btnImagen.setFocusable(false);
		btnImagen.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int indexArrayRutaImagen = comboBoxSeleccionImagenes.getSelectedIndex();
				try 
				{
					//Leo la imagen dentro del proyecto
					BufferedImage imagenOriginal = ImageIO.read(PantallaPuzzleImagenes.class.getResourceAsStream(rutaImagenPuzzle[indexArrayRutaImagen]));
					//le paso la imagen a mi logica de negocio y me devuelve una matriz con las imagenes
					matrizImagenes = logicaPuzzleImagenes.cortarImagen(imagenOriginal,"desordenada");
					logicaPuzzleImagenes.cortarImagen(imagenOriginal,"ordenada");
					logicaPuzzleImagenes.mezclarMatriz();

					setRompecabezas(matrizImagenes, labelsImagenesRecortadas);

					mostrarImagenCompleta(labelPuzzleResuelto, imagenOriginal);
					estaJuegoIniciado = true;

				} catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();

				}
			}

			private void mostrarImagenCompleta(JLabel labelImagenCompleta, BufferedImage imagenOriginal) {
				ImageIcon imageIconSinEscalar = new ImageIcon(imagenOriginal);
				ImageIcon iconoImagenCompletaEscalada = new ImageIcon(imageIconSinEscalar.getImage().getScaledInstance(labelImagenCompleta.getWidth(), labelImagenCompleta.getHeight(), Image.SCALE_SMOOTH));
				labelImagenCompleta.setIcon(iconoImagenCompletaEscalada);
			}


		});
		btnImagen.setBounds(10, 375, 126, 50);
		pantallaPrincipal.getContentPane().add(btnImagen);

		JButton btnVolver = new JButton("Atras");
		btnVolver.setFocusable(false);
		btnVolver.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				Menu m1 = new Menu();
				m1.setVisiblePantalla(true);
				pantallaPrincipal.setVisible(false);
				pantallaPrincipal.dispose();
			}
		});
		btnVolver.setBounds(10, 443, 89, 23);
		pantallaPrincipal.getContentPane().add(btnVolver);

		// Label de movimientos
		JLabel labelMensajeDeSugerencia = new JLabel(" ");

		labelMensajeDeSugerencia.setBounds(10, 101, 190, 160);
		pantallaPrincipal.getContentPane().add(labelMensajeDeSugerencia);


		//Label de contador, se inicializa oculto
		JLabel labelContadorDeMovimentos = new JLabel("Contador de Movimentos: 0");
		labelContadorDeMovimentos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		labelContadorDeMovimentos.setHorizontalAlignment(SwingConstants.CENTER);
		labelContadorDeMovimentos.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelContadorDeMovimentos.setBackground(new Color(128, 255, 255));
		labelContadorDeMovimentos.setOpaque(true);
		labelContadorDeMovimentos.setVisible(false);
		labelContadorDeMovimentos.setBounds(260, 11, 167, 30);
		pantallaPrincipal.getContentPane().add(labelContadorDeMovimentos);

		JButton btnMensajeDeAyuda = new JButton("Ayuda");
		btnMensajeDeAyuda.setFocusable(false);
		btnMensajeDeAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logicaPuzzleImagenes.proximoMovimiento();
				setLabelMensajeAyuda(labelMensajeDeSugerencia);
			}

			
		});
		btnMensajeDeAyuda.setBounds(10, 67, 89, 23);
		pantallaPrincipal.getContentPane().add(btnMensajeDeAyuda);

		// INPUTS
		// ESCUCHAR INPUTS DEL TECLADO
		pantallaPrincipal.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyTyped(KeyEvent e) 
			{
				// TODO Auto-generated method stub

			}
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(estaJuegoIniciado) {
					char tecla = e.getKeyChar();
					String teclaS1 =String.valueOf(tecla);

					contadorMovimientos = logicaPuzzleImagenes.desplazarmeEnMatriz(teclaS1, contadorMovimientos);

					actualizarMatrizMostradaPorPantalla(logicaPuzzleImagenes.getMatrizDesordenada(), labelsImagenesRecortadas);

					labelsImagenesRecortadas[logicaPuzzleImagenes.getFilaVacio()][logicaPuzzleImagenes.getColVacio()].setIcon(null);
					labelsImagenesRecortadas[logicaPuzzleImagenes.getFilaVacio()][logicaPuzzleImagenes.getColVacio()].setOpaque(true);
					labelsImagenesRecortadas[logicaPuzzleImagenes.getFilaVacio()][logicaPuzzleImagenes.getColVacio()].setBackground(new Color(160,70,80));

					labelContadorDeMovimentos.setVisible(true);
					labelContadorDeMovimentos.setText("Contador de Movimentos:" + contadorMovimientos);				

					comboBoxSeleccionImagenes.setVisible(false);
					btnImagen.setVisible(false);
					
					/*
					//Sonido
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
						contadorMovimientos = logicaPuzzleImagenes.desplazarmeEnMatriz(caracterTeclaPresionada, contadorMovimientos);

						if (contadorMovimientos > movimientosAnteriores) 
						{
							sonido.reproducirSonido("/recursos/boton.wav", "boton");
						}
					
					} */
					
					
					

					if (logicaPuzzleImagenes.gano()) 
					{
						logicaPuzzleImagenes.imprimirGane();
						labelContadorDeMovimentos.setText("Contador de Movimentos: 0");
						labelContadorDeMovimentos.setVisible(false);
						
					}
				
				}
				
				
				}
				
			@Override
			public void keyReleased(KeyEvent e) 
			{
				// TODO Auto-generated method stub
			}
		});
	}

	private void setIconoDeVentana() {
		Image icon = new ImageIcon(getClass().getResource("/recursos/icono.png")).getImage();	
		pantallaPrincipal.setIconImage(icon);
	}

	public void setVisiblePantalla(boolean condicion) 
	{
		pantallaPrincipal.setVisible(condicion);
		
	}

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					PantallaPuzzleImagenes window = new PantallaPuzzleImagenes();
					window.pantallaPrincipal.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public void setRompecabezas(BufferedImage [][] matrizImagenes, JLabel[][] matrizLabels) 
	{
		int anchoLabel = 190;
		int altoLabel = 70;
		int auxiliarAnchoLabel = 190;
		int tamañoAltoLabel = 100;
		int tamañoAnchoLabel = 100;
		int margenEnPixeles = 2;
		int seperacionEntreLabels = tamañoAnchoLabel + margenEnPixeles;
		//int auYlabel = 70;

		for (int fila = 0; fila < matrizImagenes.length; fila++) 
		{
			for (int columna = 0; columna < matrizImagenes[fila].length; columna++) 
			{
				if(matrizLabels[fila][columna] == null) 
				{
					matrizLabels[fila][columna] = new JLabel();
					matrizLabels[fila][columna].setBounds(anchoLabel, altoLabel, tamañoAnchoLabel, tamañoAltoLabel);
					pantallaPrincipal.getContentPane().add(matrizLabels[fila][columna]);

					ImageIcon imageIconSinEscalar = new ImageIcon(matrizImagenes[fila][columna]); //PARSEO BUFFERED IMAGEN A IMAGEICON (obtengo la primera posicion de la matriz)

					ImageIcon imagenEscaladaALabel = new ImageIcon(imageIconSinEscalar.getImage().getScaledInstance(matrizLabels[fila][columna].getWidth(), matrizLabels[fila][columna].getHeight(), Image.SCALE_SMOOTH));
					matrizLabels[fila][columna].setIcon(imagenEscaladaALabel);
				}
				actualizarImagen(fila,columna);

				anchoLabel+= seperacionEntreLabels;
			}
			anchoLabel= auxiliarAnchoLabel;
			altoLabel += seperacionEntreLabels;

		}

	}

	public void actualizarImagen(int fila, int columna) 
	{
		if (this.matrizImagenes[fila][columna] != null && labelsImagenesRecortadas[fila][columna] != null) 
		{
			ImageIcon imageIconSinEscalar = new ImageIcon(matrizImagenes[fila][columna]); //PARSEO BUFFERED IMAGEN A IMAGEICON (obtengo la primera posicion de la matriz)
			// Ahora ajusto el tamaño de la imagen para que se adapate a la jlabel
			ImageIcon imagenEscaladaALabel = new ImageIcon(imageIconSinEscalar.getImage().getScaledInstance(labelsImagenesRecortadas[fila][columna].getWidth(), labelsImagenesRecortadas[fila][columna].getHeight(), Image.SCALE_SMOOTH));
			labelsImagenesRecortadas[fila][columna].setIcon(imagenEscaladaALabel); 

		}
	}

	public void actualizarMatrizMostradaPorPantalla(BufferedImage [][] MatrizImagenes, JLabel[][] labels) 
	{
		for (int fila = 0; fila < MatrizImagenes.length; fila++) 
		{
			for (int columna = 0; columna < MatrizImagenes.length; columna++) 
			{
				ImageIcon imageIconSinEscalar = new ImageIcon(MatrizImagenes[fila][columna]); //PARSEO BUFFERED IMAGEN A IMAGEICON (obtengo la primera posicion de la matriz)
				// Ahora ajusto el tamaño de la imagen para que se adapate a la jlabel
				ImageIcon imagenEscaladaALabel = new ImageIcon(imageIconSinEscalar.getImage().getScaledInstance(labels[fila][columna].getWidth(), labels[fila][columna].getHeight(), Image.SCALE_SMOOTH));
				labels[fila][columna].setIcon(imagenEscaladaALabel); 
			}

		}
	}
	
	
	
	private void setLabelMensajeAyuda(JLabel labelMensajeDeSugerencia) {
		labelMensajeDeSugerencia.setText("<html>" + logicaPuzzleImagenes.proximoMovimiento().replace("\n", "<br>") + "</html>");
	}
	
	
}
