
package View;

import java.awt.EventQueue;
//import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import FormControl.ControladorImagen;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

//verificarGano(mat ord,mat des) boolean TO DO

public class PantallaImagen {
	private ControladorImagen controladorImagen;
	private JFrame frame;
	private BufferedImage[][] MatrizImagenes;
	private BufferedImage[][] MatrizImagenesOrdenadas;
	private JLabel [][] labels = new JLabel[3][3];
	private int contador = 0;
//	private boolean condicionGanada=false;
	public void setRompecabezas(BufferedImage [][] MatrizImagenes, JLabel[][] labels) {
		int xlabel = 190;
		int ylabel = 70;
		int auXlabel = 190;
		//int auYlabel = 70;

		for (int i = 0; i < MatrizImagenes.length; i++) 
		{
			for (int j = 0; j < MatrizImagenes[i].length; j++) 
			{
				if(labels[i][j] == null) {
					labels[i][j] = new JLabel();
					labels[i][j].setBounds(xlabel, ylabel, 100, 100);
					frame.getContentPane().add(labels[i][j]);

					ImageIcon imageIcon = new ImageIcon(MatrizImagenes[i][j]); //PARSEO BUFFERED IMAGEN A IMAGEICON (obtengo la primera posicion de la matriz)
					// Ahora ajusto el tamaño de la imagen para que se adapate a la jlabel
					ImageIcon img = new ImageIcon(imageIcon.getImage().getScaledInstance(labels[i][j].getWidth(), labels[i][j].getHeight(), Image.SCALE_SMOOTH));
					labels[i][j].setIcon(img);

				}
				actualizarImagen(i,j);

				xlabel+= 102;
			}
			xlabel= auXlabel;
			ylabel += 102;

		}

	}

	public void actualizarImagen(int i, int j) 
	{
		if (this.MatrizImagenes[i][j] != null && labels[i][j] != null) 
		{
			ImageIcon imageIcon = new ImageIcon(MatrizImagenes[i][j]); //PARSEO BUFFERED IMAGEN A IMAGEICON (obtengo la primera posicion de la matriz)
			// Ahora ajusto el tamaño de la imagen para que se adapate a la jlabel
			ImageIcon img = new ImageIcon(imageIcon.getImage().getScaledInstance(labels[i][j].getWidth(), labels[i][j].getHeight(), Image.SCALE_SMOOTH));
			labels[i][j].setIcon(img); 

		}
	}
	// Metodo actualiza la pantalla en el juego
	public void imagenActualizada(BufferedImage [][] MatrizImagenes, JLabel[][] labels) {
		for (int i = 0; i < MatrizImagenes.length; i++) {
			for (int j = 0; j < MatrizImagenes.length; j++) {
				ImageIcon imageIcon = new ImageIcon(MatrizImagenes[i][j]); //PARSEO BUFFERED IMAGEN A IMAGEICON (obtengo la primera posicion de la matriz)
				// Ahora ajusto el tamaño de la imagen para que se adapate a la jlabel
				ImageIcon img = new ImageIcon(imageIcon.getImage().getScaledInstance(labels[i][j].getWidth(), labels[i][j].getHeight(), Image.SCALE_SMOOTH));
				labels[i][j].setIcon(img); 
			}

		}
	}

	/**
	 * Launch the application.
	 */

	public void setVisiblePantalla(boolean condicion) {
		frame.setVisible(condicion);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaImagen window = new PantallaImagen();
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
	public PantallaImagen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		controladorImagen = new ControladorImagen();
		frame.setBounds(100, 100, 705, 516);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JComboBox<String> comboBox = new JComboBox<>(new String[] {"Imagen 1", "Imagen 2", "Imagen 3", "Imagen 4","Imagen 5"});
		comboBox.setFocusable(false);
		String [] rutaImagen = {"/resources/imagen1.png","/resources/imagen2.jpg","/resources/imagen3.jpg", "/resources/imagen4.png", "/resources/imagen5.png"};
		comboBox.setBounds(10, 344, 126, 20);
		frame.getContentPane().add(comboBox);

		JLabel labelImagenCompleta = new JLabel("");
		labelImagenCompleta.setFocusable(false);
		labelImagenCompleta.setBounds(559, 187, 120, 114);
		frame.getContentPane().add(labelImagenCompleta);
		//		JLabel lblNewLabel = new JLabel("New label");
		//		lblNewLabel.setBounds(593, 187, 46, 14);
		//		frame.getContentPane().add(lblNewLabel);

		JLabel labelOtraImagenCompleta2 = new JLabel("");
		labelOtraImagenCompleta2.setFocusable(false);
		labelOtraImagenCompleta2.setBounds(559, 11, 120, 114);
		frame.getContentPane().add(labelOtraImagenCompleta2);

		JButton btnImagen = new JButton("Mostrar");
		btnImagen.setFocusable(false);
		btnImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int index = comboBox.getSelectedIndex();
				try {
					//Leo la imagen dentro del proyecto
					BufferedImage imagenOriginal = ImageIO.read(PantallaImagen.class.getResourceAsStream(rutaImagen[index]));
					//le paso la imagen a mi logica de negocio y me devuelve una matriz con las imagenes
					MatrizImagenes = controladorImagen.cortarImagen(imagenOriginal);
					MatrizImagenesOrdenadas = controladorImagen.cortarImagenConOrden(imagenOriginal); 
					controladorImagen.mezclarMatriz();
					setRompecabezas(controladorImagen.getMatrizDesordenada(), labels);
					//test imprimir por pantalla
					ImageIcon icono = new ImageIcon(imagenOriginal);
					ImageIcon iconoImagenCompleta = new ImageIcon(icono.getImage().getScaledInstance(labelOtraImagenCompleta2.getWidth(), labelOtraImagenCompleta2.getHeight(), Image.SCALE_SMOOTH));
					labelImagenCompleta.setIcon(iconoImagenCompleta);

//					ImageIcon icono2 = new ImageIcon(imagenOriginal);
//					ImageIcon otroIconoImagenCompleta = new ImageIcon(icono2.getImage().getScaledInstance(labelOtraImagenCompleta2.getWidth(), labelOtraImagenCompleta2.getHeight(), Image.SCALE_SMOOTH));
//					labelOtraImagenCompleta2.setIcon(otroIconoImagenCompleta);
//
//
//					//TEST DE IMAGENES IGUALES
//					ImageIcon test = (ImageIcon) labelImagenCompleta.getIcon();
//					ImageIcon test2 = (ImageIcon) labelOtraImagenCompleta2.getIcon();
//					System.out.println((controladorImagen.sonIguales(test,test2)));//@Expected(verify) = assertTrue(test1==test2)
//					
//					
//					//TEST DE IMAGENES DISTINTAS
//					//Leo dos imagenes del proyecto diferentes
//					
//					BufferedImage imagenOriginal2 = ImageIO.read(PantallaImagen.class.getResourceAsStream(rutaImagen[0]));
//					BufferedImage imagenOriginal3 = ImageIO.read(PantallaImagen.class.getResourceAsStream(rutaImagen[1]));
//					
//					ImageIcon icono3 = new ImageIcon(imagenOriginal2);
//					ImageIcon iconoImagenCompleta3 = new ImageIcon(icono3.getImage().getScaledInstance(labelImagenCompleta.getWidth(), labelImagenCompleta.getHeight(), Image.SCALE_SMOOTH));
//					ImageIcon icono4 = new ImageIcon(imagenOriginal3);
//					ImageIcon iconoImagenCompleta4 = new ImageIcon(icono4.getImage().getScaledInstance(labelOtraImagenCompleta2.getWidth(), labelOtraImagenCompleta2.getHeight(), Image.SCALE_SMOOTH));
//					
//					labelImagenCompleta.setIcon(iconoImagenCompleta3);
//					
//					labelOtraImagenCompleta2.setIcon(iconoImagenCompleta4);
//					
//					ImageIcon test3 = (ImageIcon) labelImagenCompleta.getIcon();
//					ImageIcon test4 = (ImageIcon) labelOtraImagenCompleta2.getIcon();
//					System.out.println((controladorImagen.sonIguales(test3,test4)));//@Expected(verify) = assertFalse(test3==test4)
//					
////					TEST DE IGUALDAD DENUEVO (CHEQUEAR CONSOLA)
//					labelImagenCompleta.setIcon(iconoImagenCompleta3);
//					
//					labelOtraImagenCompleta2.setIcon(iconoImagenCompleta3);
//					
//					ImageIcon test5 = (ImageIcon) labelImagenCompleta.getIcon();
//					ImageIcon test6 = (ImageIcon) labelOtraImagenCompleta2.getIcon();
//					System.out.println((controladorImagen.sonIguales(test5,test6)));//@Expected(verify) = assertTrue(test5==test6)
//					
//				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

				}

			}

			
		});
		btnImagen.setBounds(10, 375, 190, 50);
		frame.getContentPane().add(btnImagen);

		JButton btnVolver = new JButton("Atras");
		btnVolver.setFocusable(false);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu m1 = new Menu();
				m1.setVisiblePantalla(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnVolver.setBounds(10, 443, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(298, 443, 89, 23);
		frame.getContentPane().add(btnNewButton);


		// INPUTS
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

				contador = controladorImagen.desplazarmeEnMatrizDesordenada(teclaS1, contador);

				imagenActualizada(controladorImagen.getMatrizDesordenada(), labels);
				comboBox.setVisible(false);
				btnVolver.setVisible(false);
				btnImagen.setVisible(false);
				if (controladorImagen.gano()) {
		            controladorImagen.imprimirGane();
		            // cuando el jugador gana...
		            
		        }
				//				controladorImagen.gano(MatrizImagenes, MatrizImagenes);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}
}
