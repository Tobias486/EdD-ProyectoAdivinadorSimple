package Juego;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import TDAArbolBinario.BoundaryViolationException;
import TDAArbolBinario.Position;
import TDAPila.*;

import java.awt.Font;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import java.awt.Color;
import java.awt.FlowLayout;

public class GUI {

	private JFrame frame;
	private JTextField textFieldPrincipal;
	private JTextField textFieldEliminar;
	private JLabel preguntaLabel;
	
	private LogicaAdivinador logica;
	
	/*
	 * 0: preguntando; (esperando "si" o "no")
	 * 1: adivinando; (esperando "si" o "no")
	 * 2: pidiendo nuevo objeto; 
	 * 3: pidiendo diferencia entre el nuevo objeto y el del intento de adivinar
	 */
	private int estado = 0; 
	private String nuevoInstrumento;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		/*
		 *  SETEANDO PANELES Y CONTENEDORES (elementos estáticos) 
		 */
		
		logica = new LogicaAdivinador();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel container = new JPanel();
		frame.getContentPane().add(container, BorderLayout.CENTER);
		container.setLayout(new CardLayout(0, 0));
		CardLayout cards = (CardLayout) container.getLayout();
		
		JPanel panelPrincipal = new JPanel();
		container.add(panelPrincipal, "panelPrincipal");
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panelPrincipal.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 0, 0, 0));		
		
		// LABEL QUE CONTIENE A LA PREGUNTA		
		preguntaLabel = new JLabel("Piensa en un instrumento");
		preguntaLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		preguntaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(preguntaLabel);
		String text;
		if (logica.haySiguientePregunta()) {
			text = "El instrumento " + logica.preguntaActual() + "?";
		} else {
			text = "El instrumento es " + logica.preguntaActual() + "?";
			estado = 1;
		}
		preguntaLabel.setText(text);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JSplitPane splitPane = new JSplitPane();
		panelPrincipal.add(splitPane, BorderLayout.NORTH);
		
		JPanel panelInfo = new JPanel();
		container.add(panelInfo, "panelInfo");
		panelInfo.setLayout(new BorderLayout(0, 0));
		
		JPanel panelEliminar = new JPanel();
		container.add(panelEliminar, "panelEliminar");
		panelEliminar.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panelEliminar.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Ingrese r\u00F3tulo del sub\u00E1rbol");
		panel_4.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JPanel panel_3 = new JPanel();
		panel_4.add(panel_3);
		
		
		/*
		 * TEXTFIELD PRINCIPAL (oyente principal)
		 */

		textFieldPrincipal = new JTextField();
		panel_1.add(textFieldPrincipal);
		textFieldPrincipal.setColumns(30);
		textFieldPrincipal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = textFieldPrincipal.getText();
				textFieldPrincipal.setText("");

				boolean resuestaPorSiNo = false; //si es verdadero el usuario ingresó "si" ó "no", falso en caso contrario
				boolean respuesta = false; //si es verdadero la respuesta fue "si", falso en caso contrario (asumiendo que respuestaPorSiNo es verdadero)
				if (input.toLowerCase().equals("si")) {
					respuesta = true;
					resuestaPorSiNo = true;
				}
				if (input.toLowerCase().equals("no")) {
					respuesta = false;
					resuestaPorSiNo = true;
				}
				
				
				switch (estado) {
				case(0): //estoy en un nodo interno
					if (resuestaPorSiNo) {
						try {
							logica.siguientePregunta(respuesta);
						} 
						catch (BoundaryViolationException ex) { ex.printStackTrace(); }
						
						if (logica.haySiguientePregunta()) { //sigo en un nodo interno, continúo preguntando
							preguntaLabel.setText(preguntar());
							estado = 0;
						} else { //pasé a una hoja, trato de adivinar
							preguntaLabel.setText(adivinar());
							estado = 1;
						}
					}
					break;
				case(1): //estoy en una hoja
					if (resuestaPorSiNo) {
						if (respuesta) { //adiviné!
							JOptionPane.showMessageDialog(null, "Adiviné!");
							reiniciarJuego();
						} else { //no adiviné
							preguntaLabel.setText("En qué instrumento estabas pensando?");
							estado = 2;
						}
					}
					break;
				case (2): 
					preguntaLabel.setText("Y cual es la diferencia entre " + 
							input + " y " + logica.preguntaActual() + "?");
					nuevoInstrumento = input;
					estado = 3;
					break;
				case (3):
					logica.agregarObjeto(nuevoInstrumento, input);
					JOptionPane.showMessageDialog(null, "Jugá de nuevo!"); 					
					reiniciarJuego(); 
					break;				
				}				
			}
		});
		
		
		/*
		 * BOTONES ABRIR
		 */
		
		JButton abrirInfoButton = new JButton("Info");
		splitPane.setLeftComponent(abrirInfoButton);
		abrirInfoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.show(container, "panelInfo");
			}
		});
		
		JButton abrirEliminarButton = new JButton("Eliminar subárbol");
		abrirEliminarButton.setForeground(Color.RED);
		splitPane.setRightComponent(abrirEliminarButton);
		abrirEliminarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.show(container, "panelEliminar");
			}
		});
		
		
		/*
		 * PANEL INFO
		 */
		
		JButton infoVolverButton = new JButton("Volver");
		infoVolverButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelInfo.add(infoVolverButton, BorderLayout.SOUTH);
		infoVolverButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.show(container, "panelPrincipal");
			}
		});
		
		JPanel panel_2 = new JPanel();
		panelInfo.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton infoDescripcionObjetosButton = new JButton("Descripción de los objetos");
		panel_2.add(infoDescripcionObjetosButton);
		infoDescripcionObjetosButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/*String info = "";
				for (String s : logica.generarDescripciones())
					info = info + s + "\n"; 
				JOptionPane.showMessageDialog(null, info);*/
			}
		});
		
		JButton infoArbolButton = new JButton("Información sobre el árbol");
		panel_2.add(infoArbolButton);
		infoArbolButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/*String info = "";
				for (String s : logica.generarInformacion())
					info = info + s + "\n"; 
				JOptionPane.showMessageDialog(null, info);*/
			}
		});
		
		JButton infoMostrarNodosButton = new JButton("Mostrar nodos");
		panel_2.add(infoMostrarNodosButton);
		infoMostrarNodosButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String info = ""; //esto se va a imprimir
				Stack<Position<String>> pilaNodos = logica.pilaInternos();
				
				if (pilaNodos.isEmpty()) //por debug
					System.out.println("b");
					
				while (!pilaNodos.isEmpty()) {
					try {
						info = info + pilaNodos.pop().element() + "\n";
						System.out.println("a");
					} catch (EmptyStackException ex) {}
				}
				JOptionPane.showMessageDialog(null, info);
			}
		});
		
		
		/*
		 * PANEL ELIMINAR
		 */
		
		textFieldEliminar = new JTextField();
		panel_3.add(textFieldEliminar);
		textFieldEliminar.setColumns(30);
		
		JButton eliminarCancelarButton = new JButton("Cancelar");
		eliminarCancelarButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelEliminar.add(eliminarCancelarButton, BorderLayout.SOUTH);
		eliminarCancelarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.show(container, "panelPrincipal");
			}
		});
		
	}
	
	/*
	 * Reincia la lógica y la interfaz para jugar de nuevo
	 */
	private void reiniciarJuego () {
		logica.reiniciar();
		
		String text = logica.haySiguientePregunta() ? preguntar() : adivinar();
		preguntaLabel.setText(text);
		
		estado = logica.haySiguientePregunta() ? 0 : 1;
	}
	
	/*
	 * Devuelve la pregunta en cadena de caracteres
	 */
	private String preguntar () {
		return "El instrumento " + logica.preguntaActual() + "?";
	}

	/*
	 * Devuelve el intento de adivinar en cadena de caracteres
	 */
	private String adivinar () {
		return "El instrumento es " + logica.preguntaActual() + "?";
	}
}
