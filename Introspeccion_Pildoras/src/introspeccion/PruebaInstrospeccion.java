package introspeccion;


import java.lang.reflect.*;
import java.util.Scanner;

public class PruebaInstrospeccion {
	
	/**
	 * 
	 * @author Arkaitz
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner entrada = new Scanner(System.in);
		System.out.println("Introduce el nombre de la clase");
		String nombreClase;
		nombreClase = entrada.next();

		// Imprimir clase y superclase
		try {
			Class cl = Class.forName(nombreClase);
			Class superCl = cl.getSuperclass();
			System.out.println("Clase " + nombreClase);
			
			if (superCl != null && superCl != Object.class) {
				System.out.println("extends " + superCl.getName());
			}
			
			System.out.println();
			
			imprimirConstructores(cl);
			System.out.println();
			imprimirMetodos(cl);
			System.out.println();
			imprimirCampos(cl);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	public static void imprimirConstructores(Class cl) {
		// TODO Auto-generated method stub
		Constructor[]constructores=cl.getDeclaredConstructors();
		
		for (Constructor c : constructores) {
			String nombre=c.getName();
			System.out.print("  " + Modifier.toString(c.getModifiers()));
			System.out.print("  " + nombre + "(" );
			
			//imprimir parámetros
			Class[]tipoParams=c.getParameterTypes();
			
			for (int i = 0; i < tipoParams.length; i++) {
				if(i>0)System.out.print(",   ");
				System.out.print(tipoParams[i].getName());
				
			}
			System.out.print(");");
		}
		
	}
	

	public static void imprimirMetodos(Class cl) {
		// TODO Auto-generated method stub
		Method[]metodos=cl.getDeclaredMethods();
		
		for (Method m : metodos) {
			Class tipoDevuelto=m.getReturnType();
			String nombre=m.getName();
			//Imprime modificadores, tipo y nombre:
			System.out.print("   " + Modifier.toString(m.getModifiers()));
			System.out.print("   " + tipoDevuelto.getName() + " " + nombre + "(" ); 
			//Imprime tipo de parámetros
			Class[] tipoParams=m.getParameterTypes();
			
			for (int i = 0; i < tipoParams.length ; i++) {
				if(i>0) System.out.print(", ");
				System.out.print(tipoParams[i].getName());
			}
			
			System.out.println(");");
			
		}
	}
	

	private static void imprimirCampos(Class cl) {
		// TODO Auto-generated method stub
		Field[]campos=cl.getDeclaredFields();
		
		for (Field f : campos) {
			Class tipoCampo=f.getType();
			String nombre=f.getName();
			System.out.print("   " + Modifier.toString(f.getModifiers()));
			System.out.println("   " + tipoCampo.getName() + " " + nombre + ";" );
		}
	}
}
