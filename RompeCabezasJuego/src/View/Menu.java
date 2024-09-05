
package View;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Menu {

	private JFrame frame;
	private PantallaImagen pantallaImagen;
	private Pantalla pantallaPuzzleNumeros;
	

	/**
	 * Launch the application.
	 */
	public void setPantalla(PantallaImagen pantallaImagen) {
		this.pantallaImagen = pantallaImagen;
	}
	public void setPantalla(Pantalla pantalla) {
		this.pantallaPuzzleNumeros = pantalla;
	}
	
	public void setVisiblePantalla(boolean condicion) {
		frame.setVisible(condicion);
	}
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
		pantallaPuzzleNumeros = new Pantalla();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.RED);
		JButton botonPuzzleImagen = new JButton("Juego Imagen");
		botonPuzzleImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				pantallaImagen.setVisiblePantalla(true);
				frame.dispose();
				
			}
		});
		botonPuzzleImagen.setBounds(123, 68, 223, 62);
		frame.getContentPane().add(botonPuzzleImagen);
		
		JButton botonPuzzleNumeros = new JButton("Juego numeros");
		botonPuzzleNumeros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				pantallaPuzzleNumeros.setVisiblePantalla(true);
				
				frame.dispose();
			}
		});
		botonPuzzleNumeros.setBounds(123, 141, 223, 52);
		frame.getContentPane().add(botonPuzzleNumeros);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnSalir.setBounds(106, 204, 255, 112);
		frame.getContentPane().add(btnSalir);
		
		JLabel lblNewLabel = new JLabel("ROMPECABEZAS EL JUEGO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Snap ITC", Font.PLAIN, 20));
		lblNewLabel.setBounds(65, 10, 341, 47);
		frame.getContentPane().add(lblNewLabel);
	}
}