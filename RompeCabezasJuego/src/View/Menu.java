package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Menu {

	private JFrame frame;
	private PantallaImagen pantallaImagen;
	private Pantalla pantalla;
	

	/**
	 * Launch the application.
	 */
	public void setPantalla(PantallaImagen pantallaImagen) {
		this.pantallaImagen = pantallaImagen;
	}
	public void setPantalla(Pantalla pantalla) {
		this.pantalla = pantalla;
	}
	
	public void setVisiblePantalla(boolean condicion) {
		frame.setVisible(condicion);
	}
//	private void crearPantalla() {
//		pantallaImagen = new PantallaImagen();
//	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
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
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		pantallaImagen = new PantallaImagen();
		pantalla = new Pantalla();
		frame.setBounds(100, 100, 685, 549);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnPantallaImagen = new JButton("Juego Imagen");
		btnPantallaImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				pantallaImagen.setVisiblePantalla(true);
				frame.dispose();
				
			}
		});
		btnPantallaImagen.setBounds(151, 79, 223, 62);
		frame.getContentPane().add(btnPantallaImagen);
		
		JButton btnPantalla = new JButton("Juego numeros");
		btnPantalla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				pantalla.mostrarPantalla(true);
				
				frame.dispose();
			}
		});
		btnPantalla.setBounds(152, 179, 238, 52);
		frame.getContentPane().add(btnPantalla);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnSalir.setBounds(147, 281, 255, 112);
		frame.getContentPane().add(btnSalir);
		
		JLabel lblNewLabel = new JLabel("ROMPECABEZAS EL JUEGO");
		lblNewLabel.setBounds(195, 44, 152, 14);
		frame.getContentPane().add(lblNewLabel);
	}
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(true);
	}
}
