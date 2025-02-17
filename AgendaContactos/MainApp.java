package AgendaContactos;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainApp extends JFrame {

	private JLayeredPane contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldEmail;
	private JTextField textFieldTelefono;
	private JTextField textFieldBuscar;
	private JTextField textField_TlfnoBusqueda;
	private String nombre;
	private String email;
	private String telefono;
	private Contacto contacto;
	private Agenda agenda;
	private JList<String> listContactos;
	private ContactosListModel modelo = new ContactosListModel();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp frame = new MainApp();
					frame.agenda = new Agenda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 262);
		contentPane.add(tabbedPane);

		JPanel panelBusqueda = new JPanel();
		tabbedPane.addTab("Busqueda", null, panelBusqueda, null);
		panelBusqueda.setLayout(null);

		textFieldBuscar = new JTextField();
		textFieldBuscar.setBounds(145, 11, 163, 20);
		panelBusqueda.add(textFieldBuscar);
		textFieldBuscar.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(72, 14, 47, 14);
		panelBusqueda.add(lblNombre);

		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nombre = MainApp.this.textFieldBuscar.getText();
				listarContactos(nombre);
				textFieldBuscar.setText("");
			}
		});
		btnNewButton_1.setBounds(330, 10, 89, 23);
		panelBusqueda.add(btnNewButton_1);

		JLabel lblTelefonoDePersona = new JLabel("Telefono de persona:");
		lblTelefonoDePersona.setBounds(25, 209, 110, 14);
		panelBusqueda.add(lblTelefonoDePersona);

		textField_TlfnoBusqueda = new JTextField();
		textField_TlfnoBusqueda.setColumns(10);
		textField_TlfnoBusqueda.setBounds(145, 206, 151, 20);
		panelBusqueda.add(textField_TlfnoBusqueda);

		listContactos = new JList<>(modelo);
		listContactos.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				//numTelefono();
				textField_TlfnoBusqueda.setText(agenda.telefono()));
			}
			
		});

		listContactos.setBounds(23, 46, 396, 148);
		panelBusqueda.add(listContactos);

		JPanel panelAltas = new JPanel();
		tabbedPane.addTab("Altas", null, panelAltas, null);
		panelAltas.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(46, 33, 46, 14);
		panelAltas.add(lblNewLabel);

		JLabel lblEmail = new JLabel("@mail:");
		lblEmail.setBounds(46, 76, 46, 14);
		panelAltas.add(lblEmail);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(46, 127, 46, 14);
		panelAltas.add(lblTelefono);

		textFieldNombre = new JTextField();

		textFieldNombre.setBounds(126, 30, 202, 20);
		panelAltas.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldEmail = new JTextField();

		textFieldEmail.setBounds(126, 73, 202, 20);
		panelAltas.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		textFieldTelefono = new JTextField();

		textFieldTelefono.setBounds(126, 124, 202, 20);
		panelAltas.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);

		JButton btnAnadir = new JButton("Contacto nuevo");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				registrarContacto();
			}
		});
		btnAnadir.setBounds(206, 185, 122, 23);
		panelAltas.add(btnAnadir);
	}

	private void listarContactos(String nombre) {
		// Obtenci�n de matriz de votaciones
		modelo.update(agenda.buscar(nombre));
		listContactos.updateUI();

	}

	private void registrarContacto() {
		// Solicitud de nombre de nuevo contacto
		nombre = MainApp.this.textFieldNombre.getText();
		email = MainApp.this.textFieldEmail.getText();
		telefono = MainApp.this.textFieldTelefono.getText();
		contacto = new Contacto(nombre, email, telefono);
		
		System.out.println(contacto.toString());

		textFieldNombre.setText("");
		textFieldEmail.setText("");
		textFieldTelefono.setText("");
		// Registro del nuevo partido
		if (agenda.registrarContacto(contacto)) {
			System.out.println(agenda.toString());
			// Cargo el modelo actual y lo muestro en lista
			modelo.update(agenda.listar());
			listContactos.updateUI();
		} else
			// No se registro -> Ya existe otro partido con el mismo nombre
			JOptionPane.showMessageDialog(this, "El registro est� repetido o no hay ning�n registro.",
					"Prueba otra vez", JOptionPane.INFORMATION_MESSAGE);
	}

	/*private void numTelefono() {
		
		//this.textField_TlfnoBusqueda.setText(telefono);
	this.textField_TlfnoBusqueda.setText(listContactos.getSelectedValue().valueOf(agenda);
	}*/

}
