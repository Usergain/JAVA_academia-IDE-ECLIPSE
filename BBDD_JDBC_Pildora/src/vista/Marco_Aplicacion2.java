package vista;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import controlador.*;

public class Marco_Aplicacion2 extends JFrame {

	public JComboBox secciones;
	public JComboBox paises;
	public JTextArea resultado;

	public Marco_Aplicacion2() {

		setTitle("Consulta BBDD");
		setBounds(500, 300, 400, 400);
		setLayout(new BorderLayout());
		JPanel menus = new JPanel();
		menus.setLayout(new FlowLayout());
		secciones = new JComboBox();
		secciones.setEditable(false);
		secciones.addItem("Todos");
		paises = new JComboBox();
		paises.setEditable(false);
		paises.addItem("Todos");
		resultado = new JTextArea(4, 50);
		resultado.setEditable(false);
		add(resultado);
		menus.add(secciones);
		menus.add(paises);
		add(menus, BorderLayout.NORTH);
		add(resultado, BorderLayout.CENTER);
		JButton botonConsulta = new JButton("Consulta");
		add(botonConsulta, BorderLayout.SOUTH);
		addWindowListener(new ControladorCargaMenus(this));
		botonConsulta.addActionListener(new ControladorBotonEjecuta(this));

	}

}
