package graficos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PreubaTexto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MarcoTexto mimarco=new MarcoTexto();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

class MarcoTexto extends JFrame{
	
	public MarcoTexto() {
		
		setBounds(600,300,600,350);
		LaminaTexto milamina=new LaminaTexto();
		add(milamina);
		setVisible(true);
	}
	
}

class LaminaTexto extends JPanel{
	
	private JTextField campo1;
	private JLabel resultado;
	
	public LaminaTexto() {

		setLayout(new BorderLayout());

		JPanel milamina2 = new JPanel();
		milamina2.setLayout(new FlowLayout());

		resultado = new JLabel("",JLabel.CENTER);
		JLabel texto1 = new JLabel("Email:");
		milamina2.add(texto1);

		campo1 = new JTextField(20);
		milamina2.add(campo1);

		JButton miboton = new JButton("Comprobar");
		DameTexto mievento = new DameTexto();
		miboton.addActionListener(mievento);
		milamina2.add(miboton);
		milamina2.add(miboton);
		
		add(milamina2,BorderLayout.NORTH);
		
		add(resultado,BorderLayout.CENTER);
	}
	
	private class DameTexto implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int correcto=0; // evalua las @
			String email=campo1.getText().trim();
			
			for (int i = 0; i < email.length(); i++) {
				if(email.charAt(i)=='@') {
					correcto++;
				}
			}
			
			if(correcto!=1) {
				resultado.setText("Incorrecto");
			}
			else {
				resultado.setText("Correcto");
			}
			
			//System.out.println(campo1.getText().trim());//Para que no aparezca por consola espacis adicionales
		}
		
	}
	
}
