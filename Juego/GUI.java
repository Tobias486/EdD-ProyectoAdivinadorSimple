package Juego;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import TDAArbolBinario.BoundaryViolationException;
import TDAArbolBinario.Position;
import TDAPila.EmptyStackException;
import TDAPila.Stack;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GUI {

	private JFrame frame;
	private JTextField juegoTextField;
	private JLabel juegoLabelPregunta;
	
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
	 * Lanza el juego
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
	 * Constructor vacío de la interfaz
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		/*
		 * CREANDO OBJETO LÓGICA DEL JUEGO
		 */
		logica = new LogicaAdivinador();
		
		
		/*
		 * CREANDO Y ACOMODANDO LOS COMPONENTES DE LA INTERFAZ 
		 */
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		CardLayout cards = (CardLayout) frame.getContentPane().getLayout();
		
		JPanel panelPrincipal = new JPanel();
		frame.getContentPane().add(panelPrincipal, "panelPrincipal");
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JLabel lblNewLabel = new JLabel("Titulo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelPrincipal.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel mainButtonContainer = new JPanel();
		panelPrincipal.add(mainButtonContainer, BorderLayout.CENTER);
		mainButtonContainer.setLayout(new GridLayout(3, 0, 0, 30));
		mainButtonContainer.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));
		
		JButton mainJugarButton = new JButton("JUGAR");
		mainButtonContainer.add(mainJugarButton);
		
		JButton mainEditarButton = new JButton("EDITAR");
		mainButtonContainer.add(mainEditarButton);
		
		JButton mainInfoButton = new JButton("INFO");
		mainButtonContainer.add(mainInfoButton);
		
		JPanel panelJuego = new JPanel();
		frame.getContentPane().add(panelJuego, "panelJuego");
		panelJuego.setLayout(new GridLayout(3, 0, 0, 0));
		panelJuego.setBorder(new EmptyBorder(40, 0, 0, 0));
		
		juegoLabelPregunta = new JLabel("?");
		juegoLabelPregunta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		juegoLabelPregunta.setHorizontalAlignment(SwingConstants.CENTER);
		panelJuego.add(juegoLabelPregunta);
		
		JPanel juegoTextFieldContainer = new JPanel();
		panelJuego.add(juegoTextFieldContainer);
		
		juegoTextField = new JTextField();
		juegoTextFieldContainer.add(juegoTextField);
		juegoTextField.setColumns(30);
		
		JPanel juegoButtonContainer = new JPanel();
		panelJuego.add(juegoButtonContainer);
		juegoButtonContainer.setLayout(new BorderLayout(0, 0));
		juegoButtonContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		JButton juegoVolverButton = new JButton("Volver");
		juegoButtonContainer.add(juegoVolverButton, BorderLayout.WEST);
		
		
		JPanel panelEditar = new JPanel();
		frame.getContentPane().add(panelEditar, "panelEditar");
		panelEditar.setLayout(new GridLayout(7, 0, 0, 5));
		panelEditar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JButton editarCargarButton = new JButton("Cargar partida");
		panelEditar.add(editarCargarButton);
		
		JButton editarSalvarButton = new JButton("Salvar partida");
		panelEditar.add(editarSalvarButton);
		
		JButton editarMostrarInfoArbol = new JButton("Mostrar info del árbol");
		panelEditar.add(editarMostrarInfoArbol);
		
		JButton editarMostrarNodosButton = new JButton("Mostrar nodos");
		panelEditar.add(editarMostrarNodosButton);
		
		JButton editarMostrarDescripciones = new JButton("Mostrar descripciones");
		panelEditar.add(editarMostrarDescripciones);
		
		JButton editarEliminarButton = new JButton("Eliminar subárbol");
		editarEliminarButton.setForeground(Color.RED);
		panelEditar.add(editarEliminarButton);
		
		JPanel editarButtonContainer = new JPanel();
		panelEditar.add(editarButtonContainer);
		editarButtonContainer.setLayout(new BorderLayout(0, 0));
		
		JButton editarVolverButton = new JButton("Volver");
		editarButtonContainer.add(editarVolverButton, BorderLayout.WEST);
		
		JPanel panelInfo = new JPanel();
		frame.getContentPane().add(panelInfo, "panelInfo");
		panelInfo.setLayout(null);
		
		JButton infoVolverButton = new JButton("Volver");
		infoVolverButton.setBounds(10, 227, 89, 23);
		panelInfo.add(infoVolverButton);
		
		
		
		JPanel panelEliminar = new JPanel();
		frame.getContentPane().add(panelEliminar, "panelEliminar");
		panelEliminar.setLayout(new GridLayout(4, 0, 0, 0));
		
		JLabel eliminarLabel = new JLabel("");
		eliminarLabel.setText("<html>Seleccione el rótulo del subárbol que desea eliminar<br>(estos cambios no se pueden deshacer)</html>");
		eliminarLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		eliminarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelEliminar.add(eliminarLabel);
		
		JPanel eliminarComboBoxContainer = new JPanel();
		panelEliminar.add(eliminarComboBoxContainer);
		eliminarComboBoxContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JComboBox eliminarComboBox = new JComboBox();
		eliminarComboBoxContainer.add(eliminarComboBox);
		
		JPanel eliminarButtonContainer = new JPanel();
		panelEliminar.add(eliminarButtonContainer);
		
		JButton eliminarEliminarButton = new JButton("Eliminar");
		eliminarEliminarButton.setForeground(Color.RED);
		eliminarButtonContainer.add(eliminarEliminarButton);
		
		JPanel eliminarButtonContainer2 = new JPanel();
		panelEliminar.add(eliminarButtonContainer2);
		eliminarButtonContainer2.setLayout(new BorderLayout(0, 0));
		eliminarButtonContainer2.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		JButton eliminarVolverButton = new JButton("Volver");
		eliminarButtonContainer2.add(eliminarVolverButton, BorderLayout.WEST);
		
		
		
		
		/*
		 * SETEANDO LISTENERS EN EL PANEL PRINCIPAL
		 */
		
		mainJugarButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {			
				reiniciarJuego();				
				cards.show(frame.getContentPane(), "panelJuego");				
			}
		});
		mainEditarButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.show(frame.getContentPane(), "panelEditar");				
			}
		});
		mainInfoButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.show(frame.getContentPane(), "panelInfo");				
			}
		});
		
		
		
		
		/*
		 * SETEANDO COMPONENTES DEL PANEL DE JUEGO
		 */

		juegoTextField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = juegoTextField.getText();
				juegoTextField.setText("");

				boolean resuestaPorSiNo = false; //si es verdadero el usuario ingresa "si" ó "no", falso en caso contrario
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
							juegoLabelPregunta.setText(preguntar());
							estado = 0;
						} else { //pasÃ© a una hoja, trato de adivinar
							juegoLabelPregunta.setText(adivinar());
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
							juegoLabelPregunta.setText("En qué instrumento estabas pensando?");
							estado = 2;
						}
					}
					break;
				case (2): 
					if (!input.equals("")) {
						juegoLabelPregunta.setText("Y cual es la diferencia entre " + 
								input + " y " + logica.preguntaActual() + "?");
						nuevoInstrumento = input;
						estado = 3;
					}
					break;
				case (3):
					if (!input.equals("")) {
						logica.agregarObjeto(nuevoInstrumento, input);
						JOptionPane.showMessageDialog(null, "Jugá de nuevo!"); 					
						reiniciarJuego(); 
					}
					break;				
				}				
			}
		});
		
		juegoVolverButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.show(frame.getContentPane(), "panelPrincipal");			
			}
		});
		
		
		
		
		
		/*
		 * SETEANDO LISTENERS EN EL PANEL PARA EDITAR Y VER INFO DE LA ESCTRUCTURA
		 */
		editarSalvarButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de partida", "dat");
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(filter);
				
				if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
					java.io.File file = fileChooser.getSelectedFile();
					System.out.println(file.getPath());
					logica.grabarPartida(file);
				}
				
				
				
			}
		});
		
		editarCargarButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de partida", "dat");
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(filter);
				
				if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					java.io.File file = fileChooser.getSelectedFile();
					System.out.println(file.getPath());
					logica.cargarPartida(file);
				}
				
			}
		});
		
		
		editarMostrarNodosButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String info = ""; //esto se va a imprimir
				Stack<Position<String>> pilaNodos = logica.pilaInternos();

				while (!pilaNodos.isEmpty()) {
					try {
						info = info + pilaNodos.pop().element() + "\n";
					} catch (EmptyStackException ex) {}
				}
				if (info.equals(""))
					info = "No hay nodos que mostrar";
					
				JOptionPane.showMessageDialog(null, info);
			}
		});
		
		editarMostrarDescripciones.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String info = "";
				boolean finLinea = false;
				for (String s : logica.generarDescripciones()){
					if(finLinea){
						info +=s + "\n"; 
						finLinea = !finLinea;
					}
					else{
						info+=s.substring(0,1).toUpperCase()+s.substring(1);
						finLinea = !finLinea;
					}
				}
				JOptionPane.showMessageDialog(null, info);
			}
		});
		
		editarEliminarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.show(frame.getContentPane(), "panelEliminar");	
				
				Stack<Position<String>> pilaNodos = logica.pilaInternos();
				eliminarComboBox.removeAllItems();
				eliminarComboBox.addItem(null);
					
				while (!pilaNodos.isEmpty()) {
					try {
						Position<String> pos = pilaNodos.pop();
						eliminarComboBox.addItem(pos);
					} catch (EmptyStackException ex) {}
				}
			}
		});
		
		editarMostrarInfoArbol.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String info = 
						"Altura del árbol: " + logica.alturaArbol() + "\n" +
						"Cantidad de preguntas: " + logica.cantidadPreguntas() + "\n" +
						"Cantidad de objetos: " + logica.cantidadObjetos(); 
				JOptionPane.showMessageDialog(null, info);
			}
		});
		
		editarVolverButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.show(frame.getContentPane(), "panelPrincipal");			
			}
		});
		
		
		
		
		/*
		 * SETEANDO LISTENERS PARA EL PANEL DE ELIMINAR SUBÁRBOL
		 */
		
		eliminarVolverButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.show(frame.getContentPane(), "panelEditar");	
			}
		});

		eliminarEliminarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Position<String> item = (Position<String>) eliminarComboBox.getSelectedItem();
				if (item != null) {
					String borrado = item.element();
					logica.eliminarSubarbol(item);
					JOptionPane.showMessageDialog(null, borrado + " fue eliminado");

					cards.show(frame.getContentPane(), "panelEditar");	
				}
			}
		});
		
		
		
		/*
		 * SETEANDO LISTENERS DEL PANEL DE INFO
		 */
		
		infoVolverButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.show(frame.getContentPane(), "panelPrincipal");	
			}
		});
		
	}
	

	/*
	 * METODOS AUXILIARES, PRIVADOS
	 */
	
	/**
	 * Reincia la lógica y la interfaz para jugar de nuevo
	 */
	private void reiniciarJuego () {
		logica.reiniciar();
		
		String text = logica.haySiguientePregunta() ? preguntar() : adivinar();
		juegoLabelPregunta.setText(text);
		
		estado = logica.haySiguientePregunta() ? 0 : 1;
	}
	
	/**
	 * Devuelve la pregunta en cadena de caracteres
	 * @return la pregunta en cadena de caracteres
	 */
	private String preguntar () {
		return "El instrumento " + logica.preguntaActual() + "?";
	}

	/**
	 * Devuelve el intento de adivinar en cadena de caracteres
	 * @return el intento de adivinar en cadena de caracteres
	 */
	private String adivinar () {
		return "El instrumento es " + logica.preguntaActual() + "?";
	}

}
