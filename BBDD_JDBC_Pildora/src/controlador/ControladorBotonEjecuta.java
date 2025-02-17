package controlador;

import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.*;
import vista.*;

public class ControladorBotonEjecuta implements ActionListener{
	
	private ResultSet resultadoConsulta;
	private Marco_Aplicacion2 elmarco;
	EjecutaConsultas obj=new EjecutaConsultas();
	
	public ControladorBotonEjecuta(Marco_Aplicacion2 elmarco) {
		
		this.elmarco=elmarco;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String seleccionSeccion=(String)elmarco.secciones.getSelectedItem();
		String seleccionPais=(String)elmarco.paises.getSelectedItem();
		resultadoConsulta=obj.filtraBBDD(seleccionSeccion, seleccionPais);
		
		try {
			
			elmarco.resultado.setText("");
			
			while(resultadoConsulta.next()){
				
				elmarco.resultado.append(resultadoConsulta.getString(1));
				elmarco.resultado.append("===>");
				elmarco.resultado.append(resultadoConsulta.getString(2));
				elmarco.resultado.append("===>");
				elmarco.resultado.append(resultadoConsulta.getString(3));
				elmarco.resultado.append("===>");
				elmarco.resultado.append(resultadoConsulta.getString(4));
				elmarco.resultado.append("\n");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	

}
