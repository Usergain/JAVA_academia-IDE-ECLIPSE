package graficos;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class EventoTeclado {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoConTeclas mimarco=new MarcoConTeclas();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

class MarcoConTeclas extends JFrame{
	
	public MarcoConTeclas(){
		setVisible(true);
		setBounds(300,300,500,350);
		
		EventoDeTeclado tecla=new EventoDeTeclado();
		addKeyListener(tecla);
		
	}
}

class EventoDeTeclado implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char letra=e.getKeyChar();
		System.out.println(letra);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int codigo= e.getKeyCode();
		System.out.println(codigo);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
