package FicheroDirectorio;

import java.io.*;

public class Acceso_Ficheros {

	public static void main(String[] args) {
		
		File ruta=new File("C:\\Users\\userg\\Documents\\Pildoras");
		System.out.println(ruta.getAbsolutePath());
		
		String[]nombres_archivos=ruta.list();
		
		for (int i = 0; i < nombres_archivos.length; i++) {
			System.out.println(nombres_archivos[i]);
			File f=new File(ruta.getAbsoluteFile(),nombres_archivos[i]);
			
			if(f.isDirectory()) {
				String[]archivos_subcarpeta=f.list();
				for (int j = 0; j < archivos_subcarpeta.length; j++) {
					System.out.println(archivos_subcarpeta[j]);
				}
			}
		}
		
	}
}
