
package View;

import java.awt.Color;
import java.awt.EventQueue;
//import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import FormControl.ControladorImagen;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.border.EtchedBorder;


public class PantallaImagen {
	private ControladorImagen controladorImagen;
	private JFrame frame;
	private BufferedImage[][] MatrizImagenes;
	private BufferedImage[][] MatrizImagenesOrdenadas;
	private JLabel [][] labels = new JLabel[3][3];
	private int contador = 0;
	
	

	/**
	 * Create the application.
	 */
	public PantallaImagen() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		controladorImagen = new ControladorImagen();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 705, 516);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(250, 250, 150));
		
		JComboBox<String> comboBox = new JComboBox<>(new String[] {"Imagen 1", "Imagen 2", "Imagen 3", "Imagen 4","Imagen 5"});
		comboBox.setFocusable(false);
		String [] rutaImagen = {"/resources/imagen1.png","/resources/imagen2.jpg","/resources/imagen3.jpg", "/resources/imagen4.png", "/resources/imagen5.png"};
		comboBox.setBounds(10, 344, 126, 20);
		frame.getContentPane().add(comboBox);

		JLabel labelImagenCompleta = new JLabel("");
		labelImagenCompleta.setFocusable(false);
		labelImagenCompleta.setBounds(559, 187, 120, 114);

		JButton btnImagen = new JButton("Mostrar");
		btnImagen.setFocusable(false);
		btnImagen.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{

				int index = comboBox.getSelectedIndex();
				try 
				{
					//Leo la imagen dentro del proyecto
					BufferedImage imagenOriginal = ImageIO.read(PantallaImagen.class.getResourceAsStream(rutaImagen[index]));
					//le paso la imagen a mi logica de negocio y me devuelve una matriz con las imagenes
					MatrizImagenes = controladorImagen.cortarImagen(imagenOriginal,"a desordenar");
					MatrizImagenesOrdenadas = controladorImagen.cortarImagen(imagenOriginal,"ordenada");
					controladorImagen.mezclarMatriz();
					
					setRompecabezas(MatrizImagenes, labels);
				
					//test imprimir por pantalla
					ImageIcon icono = new ImageIcon(imagenOriginal);
					ImageIcon iconoImagenCompleta = new ImageIcon(icono.getImage().getScaledInstance(labelImagenCompleta.getWidth(), labelImagenCompleta.getHeight(), Image.SCALE_SMOOTH));
					labelImagenCompleta.setIcon(iconoImagenCompleta);
					
				} catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();

				}

			}

			
		});
		btnImagen.setBounds(10, 375, 190, 50);
		frame.getContentPane().add(btnImagen);

		JButton btnVolver = new JButton("Atras");
		btnVolver.setFocusable(false);
		btnVolver.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				Menu m1 = new Menu();
				m1.setVisiblePantalla(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnVolver.setBounds(10, 443, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		// Label de movimientos
		JLabel lbMovimientos = new JLabel(" ");
		
		lbMovimientos.setBounds(10, 101, 190, 160);
		frame.getContentPane().add(lbMovimientos);
		
		
		//Label de contador, se inicializa oculto
		JLabel lbContador = new JLabel("Contador de Movimentos: 0");
		lbContador.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lbContador.setHorizontalAlignment(SwingConstants.CENTER);
		lbContador.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbContador.setBackground(new Color(128, 255, 255));
		lbContador.setOpaque(true);
		lbContador.setVisible(false);
		lbContador.setBounds(260, 11, 167, 30);
		frame.getContentPane().add(lbContador);
		
		JButton lbAyuda = new JButton("Ayuda");
		lbAyuda.setFocusable(false);
		lbAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorImagen.proximoMovimiento();
				lbMovimientos.setText("<html>" + controladorImagen.proximoMovimiento().replace("\n", "<br>") + "</html>");
			}
		});
		lbAyuda.setBounds(10, 67, 89, 23);
		frame.getContentPane().add(lbAyuda);
		
		


		// INPUTS
		// ESCUCHAR INPUTS DEL TECLADO
		frame.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyTyped(KeyEvent e) 
			{
				// TODO Auto-generated method stub

			}
			@Override
			public void keyPressed(KeyEvent e) 
			{

				char tecla = e.getKeyChar();
				String teclaS1 =String.valueOf(tecla);

				contador = controladorImagen.desplazarmeEnMatriz(teclaS1, contador);
				
				actualizarMatrizMostradaPorPantalla(controladorImagen.getMatrizDesordenada(), labels);
				labels[controladorImagen.getFilaVacio()][controladorImagen.getColVacio()].setIcon(null);
//				labels[controladorImagen.getFilaVacio()][controladorImagen.getColVacio()].setOpaque(true);
//				labels[controladorImagen.getFilaVacio()][controladorImagen.getColVacio()].setBackground(new Color(128, 64, 0));;
				lbContador.setVisible(true);
				lbContador.setText("Contador de Movimentos:" + contador);
				
				comboBox.setVisible(false);
				btnVolver.setVisible(false);
				btnImagen.setVisible(false);
				if (controladorImagen.gano()) 
				{
//					Image imagenCasilleroVacio= MatrizImagenes[controladorImagen.getFilaVacio()][controladorImagen.getColVacio()].getScaledInstance(tecla, tecla, tecla);
//					labels[controladorImagen.getFilaVacio()][controladorImagen.getColVacio()].setIcon((Icon) imagenCasilleroVacio);
		            controladorImagen.imprimirGane();
		            // cuando el jugador gana...
		            lbContador.setText("Contador de Movimentos: 0");
		            lbContador.setVisible(false);
		            btnVolver.setVisible(true);
		        }
			}
			@Override
			public void keyReleased(KeyEvent e) 
			{
				// TODO Auto-generated method stub
			}
		});
	}

	public void setVisiblePantalla(boolean condicion) 
	{
		frame.setVisible(condicion);
	}
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					PantallaImagen window = new PantallaImagen();
					window.frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setRompecabezas(BufferedImage [][] MatrizImagenes, JLabel[][] labels) 
	{
		int xlabel = 190;
		int ylabel = 70;
		int auXlabel = 190;
		//int auYlabel = 70;

		for (int fila = 0; fila < MatrizImagenes.length; fila++) 
		{
			for (int columna = 0; columna < MatrizImagenes[fila].length; columna++) 
			{
				if(labels[fila][columna] == null) 
				{
					labels[fila][columna] = new JLabel();
					labels[fila][columna].setBounds(xlabel, ylabel, 100, 100);
					frame.getContentPane().add(labels[fila][columna]);

					ImageIcon imageIcon = new ImageIcon(MatrizImagenes[fila][columna]); //PARSEO BUFFERED IMAGEN A IMAGEICON (obtengo la primera posicion de la matriz)
					
					ImageIcon img = new ImageIcon(imageIcon.getImage().getScaledInstance(labels[fila][columna].getWidth(), labels[fila][columna].getHeight(), Image.SCALE_SMOOTH));
					labels[fila][columna].setIcon(img);
//					ImageIcon imgCasillavacia = new ImageIcon(imageIcon.getImage().getScaledInstance(labels[fila][columna].getWidth(), labels[fila][columna].getHeight(), Image.SCALE_SMOOTH));
				}
				actualizarImagen(fila,columna);

				xlabel+= 102;
			}
			xlabel= auXlabel;
			ylabel += 102;

		}

	}

	public void actualizarImagen(int fila, int columna) 
	{
		if (this.MatrizImagenes[fila][columna] != null && labels[fila][columna] != null) 
		{
			ImageIcon imageIcon = new ImageIcon(MatrizImagenes[fila][columna]); //PARSEO BUFFERED IMAGEN A IMAGEICON (obtengo la primera posicion de la matriz)
			// Ahora ajusto el tamaño de la imagen para que se adapate a la jlabel
			ImageIcon img = new ImageIcon(imageIcon.getImage().getScaledInstance(labels[fila][columna].getWidth(), labels[fila][columna].getHeight(), Image.SCALE_SMOOTH));
			labels[fila][columna].setIcon(img); 

		}
	}
	
	public void actualizarMatrizMostradaPorPantalla(BufferedImage [][] MatrizImagenes, JLabel[][] labels) 
	{
		for (int fila = 0; fila < MatrizImagenes.length; fila++) 
		{
			for (int columna = 0; columna < MatrizImagenes.length; columna++) 
			{
				ImageIcon imageIcon = new ImageIcon(MatrizImagenes[fila][columna]); //PARSEO BUFFERED IMAGEN A IMAGEICON (obtengo la primera posicion de la matriz)
				// Ahora ajusto el tamaño de la imagen para que se adapate a la jlabel
				ImageIcon img = new ImageIcon(imageIcon.getImage().getScaledInstance(labels[fila][columna].getWidth(), labels[fila][columna].getHeight(), Image.SCALE_SMOOTH));
				labels[fila][columna].setIcon(img); 
			}

		}
	}
}
