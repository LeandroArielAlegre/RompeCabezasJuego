
package View;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import FormControl.Sonido;

public class Menu {

	private JFrame pantallaMenu;
	private PantallaPuzzleImagenes pantallaPuzzleImagenes;
	private PantallaPuzzleNumeros pantallaPuzzleNumeros;
	private Sonido sonido;
	
	/**
	 * Launch the application.
	 */
	public void setPantalla(PantallaPuzzleImagenes pantallaImagen) {
		this.pantallaPuzzleImagenes = pantallaImagen;
	}
	public void setPantalla(PantallaPuzzleNumeros pantalla) {
		this.pantallaPuzzleNumeros = pantalla;
	}

	public void setVisiblePantalla(boolean condicion) {
		pantallaMenu.setVisible(condicion);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.pantallaMenu.setVisible(true);
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
		pantallaMenu = new JFrame();
		pantallaMenu.setResizable(false);
		pantallaPuzzleImagenes = new PantallaPuzzleImagenes();
		pantallaPuzzleNumeros = new PantallaPuzzleNumeros();
	    sonido = new Sonido();
		pantallaMenu.setBounds(100, 100, 400, 400);
		pantallaMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pantallaMenu.getContentPane().setLayout(null);
		pantallaMenu.getContentPane().setBackground(new Color(172, 242, 183));
		//Cancion
		
		sonido.reproducirSonido("/recursos/menu.wav", "menu");
		
		
		JButton botonPuzzleImagen = new JButton("Juego Imagen");
		botonPuzzleImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantallaMenu.setVisible(false);
				pantallaPuzzleImagenes.setVisiblePantalla(true);
				pantallaMenu.dispose();
				sonido.reproducirSonido("/recursos/boton.wav", "boton");
				sonido.detenerSonido("menu");

			}
		});
		botonPuzzleImagen.setBounds(79, 68, 223, 62);
		pantallaMenu.getContentPane().add(botonPuzzleImagen);

		JButton botonPuzzleNumeros = new JButton("Juego numeros");
		botonPuzzleNumeros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantallaMenu.setVisible(false);
				pantallaPuzzleNumeros.setVisiblePantalla(true);
				sonido.reproducirSonido("/recursos/boton.wav", "boton");
				
				pantallaMenu.dispose();
				sonido.detenerSonido("menu");
				
			}
		});
		botonPuzzleNumeros.setBounds(79, 141, 223, 52);
		pantallaMenu.getContentPane().add(botonPuzzleNumeros);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantallaMenu.setVisible(false);
				pantallaMenu.dispose();
				sonido.reproducirSonido("/recursos/boton.wav", "boton");
				sonido.detenerSonido("menu");
			}
		});
		btnSalir.setBounds(79, 204, 223, 52);
		pantallaMenu.getContentPane().add(btnSalir);

		JLabel labelNombreDelJuego = new JLabel("ROMPECABEZAS EL JUEGO");
		labelNombreDelJuego.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombreDelJuego.setFont(new Font("Snap ITC", Font.PLAIN, 20));
		labelNombreDelJuego.setBounds(20, 10, 341, 47);
		pantallaMenu.getContentPane().add(labelNombreDelJuego);
	}
}