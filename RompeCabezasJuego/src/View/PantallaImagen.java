
package View;

import java.awt.EventQueue;
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
//	private BufferedImage[][] MatrizImagenesOrdenadas;
	private JLabel [][] labels = new JLabel[3][3];
	private int contador = 0;

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

		JLabel imagenCompleta = new JLabel("");
		imagenCompleta.setFocusable(false);
		imagenCompleta.setBounds(559, 11, 120, 114);
		frame.getContentPane().add(imagenCompleta);

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
//					MatrizImagenesOrdenadas = controladorImagen.copiarMatriz(MatrizImagenes); // Create ordered matrix
					controladorImagen.mezclarMatriz();
					setRompecabezas(MatrizImagenes, labels);
					
					ImageIcon icono = new ImageIcon(imagenOriginal);
					ImageIcon imgOriginal = new ImageIcon(icono.getImage().getScaledInstance(imagenCompleta.getWidth(), imagenCompleta.getHeight(), Image.SCALE_SMOOTH));
					imagenCompleta.setIcon(imgOriginal);
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

				contador = controladorImagen.desplazarmeEnMatriz(teclaS1, contador);
				
				imagenActualizada(MatrizImagenes , labels);
				comboBox.setVisible(false);
				btnVolver.setVisible(false);
				btnImagen.setVisible(false);
//				controladorImagen.gano(MatrizImagenes, MatrizImagenes);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}
}
