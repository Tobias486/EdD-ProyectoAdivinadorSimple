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
	
	/*private boolean esperandoRespuestaFinal = false;
	private boolean esperandoNuevoInstrumento = false;
	private boolean esperandoDiferencia = false;*/
	
	private LogicaAdivinador logica;
	
	private int estado = 0; //0: preguntando; 1: adivinando; 2: pidiendo nuevo objeto; 3: pidiendo diferencia
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
		
		JLabel preguntaLabel = new JLabel("Piensa en un instrumento");
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
		 * TEXTFIELD PRINCIPAL
		 */

		textFieldPrincipal = new JTextField();
		panel_1.add(textFieldPrincipal);
		textFieldPrincipal.setColumns(30);
		textFieldPrincipal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = textFieldPrincipal.getText();
				textFieldPrincipal.setText("");

				boolean resuestaPorSiNo = false;
				boolean respuesta = false;
				if (input.toLowerCase().equals("si")) {
					respuesta = true;
					resuestaPorSiNo = true;
				}
				if (input.toLowerCase().equals("no")) {
					respuesta = false;
					resuestaPorSiNo = true;
				}
				
				
				switch (estado) {
				case(0): //no estoy en una hoja
					if (resuestaPorSiNo) {
						try {
							logica.siguientePregunta(respuesta);
						} catch (BoundaryViolationException ex) {ex.printStackTrace();}
						preguntaLabel.setText("El instrumento " + logica.preguntaActual() + "?");
						if (!logica.haySiguientePregunta()) { //pasé a una hoja
							estado = 1;
							preguntaLabel.setText("El instrumento es " + logica.preguntaActual() + "?");
						}
					}
					break;
				case(1): //estoy en una hoja
					if (resuestaPorSiNo) {
						if (respuesta) { //adiviné
							JOptionPane.showMessageDialog(null, "Adiviné!");
							estado = 0; //reinciando lógica e interfaz
							logica.reiniciar();
							String text;
							if (logica.haySiguientePregunta()) {
								text = "El instrumento " + logica.preguntaActual() + "?";
							} else {
								text = "El instrumento es " + logica.preguntaActual() + "?";
								estado = 1;
							}
							preguntaLabel.setText(text);
						} else { //no adiviné, en qué elemento pensabas?
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
					JOptionPane.showMessageDialog(null, "Jugá de nuevo!"); //reinciando lógica e interfaz
					estado = 0;
					logica.reiniciar();
					String text;
					if (logica.haySiguientePregunta()) {
						text = "El instrumento " + logica.preguntaActual() + "?";
					} else {
						text = "El instrumento es " + logica.preguntaActual() + "?";
						estado = 1;
					}
					preguntaLabel.setText(text);
					break;				
				}
				
				/*
				if (!esperandoRespuestaFinal && resuestaPorSiNo) {
					if (logica.haySiguientePregunta()) { //no estoy en una hoja
						preguntaLabel.setText("El instrumento " + logica.preguntaActual() + "?");
						logica.setSiguientePregunta(respuesta);
					} else  { //estoy en una hoja
						preguntaLabel.setText("El instrumento es un/una " + logica.preguntaActual() + "?");						
						esperandoRespuestaFinal = true;
					}
				} else if (esperandoRespuestaFinal && resuestaPorSiNo) {
					esperandoRespuestaFinal = false;
					if (respuesta) { //adiviné
						JOptionPane.showMessageDialog(null, "Adiviné!");
						//TODO reiniciar interfaz
					} else { //no adiviné, en qué elemento pensabas?
						preguntaLabel.setText("En qué instrumento estabas pensando?");
						esperandoNuevoInstrumento = true;
					}
				} else if (esperandoNuevoInstrumento) {
					esperandoNuevoInstrumento = false;
					preguntaLabel.setText("Y cual es la diferencia entre un/una " + 
							respuesta +" y un/una " + logica.preguntaActual() + "?");
					nuevoElemento = respuesta;
					esperandoDiferencia = true;
				} else if (esperandoDiferencia) {
					esperandoDiferencia = false;
					logica.agregarObjeto(nuevoInstrumento, respuesta);
					//TODO reiniciar interfaz
				}*/
				
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
				/*String info = "";
				for (String s : logica.mostrarNodosInternos())
					info = info + s + "\n"; 
				JOptionPane.showMessageDialog(null, info);*/
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
}
