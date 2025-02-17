package artaraz.socket;

import java.awt.BorderLayout;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.swing.*;

public class ServidorSocket {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MarcoServidor mimarco=new MarcoServidor();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

class MarcoServidor extends JFrame implements Runnable{
	
	private JTextArea areatexto;
	
	public MarcoServidor() {
		
		setBounds(1200, 300, 280, 350);
		JPanel milamina=new JPanel();
		milamina.setLayout(new BorderLayout());
		areatexto=new JTextArea();
		milamina.add(areatexto, BorderLayout.CENTER);
		add(milamina);
		setVisible(true);
		Thread mihilo=new Thread(this);
		mihilo.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			ServerSocket servidor=new ServerSocket(9999);
			String nick,ip, mensaje;
			ArrayList<String>listaIp=new ArrayList<String>();
			PaqueteEnvio paquete_recibido;
			
			//Servidor a la escucha. bucle infinito
			while(true) {
				
				Socket misocket=servidor.accept();
				
				ObjectInputStream paquete_datos=new ObjectInputStream(misocket.getInputStream());
				paquete_recibido=(PaqueteEnvio) paquete_datos.readObject();
				nick=paquete_recibido.getNick();
				ip=paquete_recibido.getIp();
				mensaje=paquete_recibido.getMensaje();
				
				if(!mensaje.equals(" online")) {
					
					areatexto.append("\n" + nick + ": " + mensaje + " para " + ip);
					//paquete de uno de los clientes a enviar al otro cliente
					Socket enviaDestinatario=new Socket(ip, 9090);
					ObjectOutputStream paqueteReenvio=new ObjectOutputStream(enviaDestinatario.getOutputStream());
					paqueteReenvio.writeObject(paquete_recibido);
					paqueteReenvio.close();
					enviaDestinatario.close();	
					misocket.close();
	
				}else {
		//------------------------- DETECTA A LOS USUARIOS ONLINE -------------------------------
					
					InetAddress localizacion=misocket.getInetAddress();
					String IpRemota=localizacion.getHostAddress();
					System.out.println("Online: " + IpRemota);
					listaIp.add(IpRemota);
					paquete_recibido.setIps(listaIp);
					
		//--------- ��Esta parte me da fallo, al detectarme el socket con la ip de Servidor en vez de la del cliente			
					
					/*for(String z:listaIp){
						
						System.out.println("Array: " + z);
						Socket enviaDestinatario=new Socket(z, 9090);
						ObjectOutputStream paqueteReenvio=new ObjectOutputStream(enviaDestinatario.getOutputStream());
						paqueteReenvio.writeObject(paquete_recibido);
						paqueteReenvio.close();
						enviaDestinatario.close();	
						misocket.close();
					}*/
					
		//----------------------------------------------------------------------------------------
				}

				
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
