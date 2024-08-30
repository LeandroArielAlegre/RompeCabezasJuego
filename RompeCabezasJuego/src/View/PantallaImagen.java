package View;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import FormControl.Controlador;
import FormControl.ControladorImagen;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PantallaImagen {
	private ControladorImagen controladorImagen;
	private JFrame frame;
	private BufferedImage[][] MatrizImagenes;
	private JLabel [][] labels = new JLabel[3][3];
	
	
	public void setRompecabezas(BufferedImage [][] MatrizImagenes, JLabel[][] labels) {
		int xlabel = 190;
		int ylabel = 70;
		int auXlabel = 190;
		//int auYlabel = 70;
		
		for (int i = 0; i < MatrizImagenes.length; i++) {
			for (int j = 0; j < MatrizImagenes[i].length; j++) {
				if(labels[i][j] == null) {
					labels[i][j] = new JLabel();
					labels[i][j].setBounds(xlabel, ylabel, 90, 90);
					frame.getContentPane().add(labels[i][j]);
					
					ImageIcon imageIcon = new ImageIcon(MatrizImagenes[i][j]); //PARSEO BUFFERED IMAGEN A IMAGEICON (obtengo la primera posicion de la matriz)
					// Ahora ajusto el tamaño de la imagen para que se adapate a la jlabel
					ImageIcon img = new ImageIcon(imageIcon.getImage().getScaledInstance(labels[i][j].getWidth(), labels[i][j].getHeight(), Image.SCALE_SMOOTH));
					labels[i][j].setIcon(img); 
					
					
				}
				actualizarImagen(i,j);
					
				xlabel+= 90;
			}
			xlabel= auXlabel;
			ylabel += 90;
			
		}
		
		
		System.out.println("PRONTO");
		
	}
	
	 public void actualizarImagen(int i, int j) {
	        if (this.MatrizImagenes[i][j] != null && labels[i][j] != null) {
	        	ImageIcon imageIcon = new ImageIcon(MatrizImagenes[i][j]); //PARSEO BUFFERED IMAGEN A IMAGEICON (obtengo la primera posicion de la matriz)
				// Ahora ajusto el tamaño de la imagen para que se adapate a la jlabel
				ImageIcon img = new ImageIcon(imageIcon.getImage().getScaledInstance(labels[i][j].getWidth(), labels[i][j].getHeight(), Image.SCALE_SMOOTH));
				labels[i][j].setIcon(img); 
	          
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
		
		//declaracion de labels
		JLabel imagenLabel = new JLabel("New label");
		imagenLabel.setBounds(10, 298, 74, 45);
		frame.getContentPane().add(imagenLabel);
		
		JComboBox<String> comboBox = new JComboBox<>(new String[] {"Imagen 1", "Imagen 2", "Imagen 3"});
		//Listas simetricas
		String [] rutaImagen = {"/resources/imagen1.png","/resources/imagen2.jpg","/resources/imagen3.jpg"};
		comboBox.setBounds(10, 344, 126, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel imagenCompleta = new JLabel("");
		imagenCompleta.setBounds(403, 240, 262, 177);
		frame.getContentPane().add(imagenCompleta);
		
		JButton btnImagen = new JButton("Mostrar");
		btnImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = comboBox.getSelectedIndex();
				try {
						//Leo la imagen dentro del proyecto
						BufferedImage imagenOriginal = ImageIO.read(PantallaImagen.class.getResourceAsStream(rutaImagen[index]));
						//le paso la imagen a mi logica de negocio y me devuelve una matriz con las imagenes
						MatrizImagenes = controladorImagen.cortarImagen(imagenOriginal);
						
						setRompecabezas(MatrizImagenes, labels);
						//test para ver la imagen 
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
		btnVolver.setBounds(10, 443, 89, 23);
		frame.getContentPane().add(btnVolver);
		
		
	
		
		
	}
	
}
