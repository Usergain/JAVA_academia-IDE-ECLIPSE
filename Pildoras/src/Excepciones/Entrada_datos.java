package Excepciones;

import java.io.IOException;
import java.util.*;


public class Entrada_datos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Que deseas hacer?");
		System.out.println("1. Introducir datos");
		System.out.println("2. Salir del programa");
		
		Scanner entrada=new Scanner(System.in);
		int decision=entrada.nextInt();
		
		if(decision==1) {
			
			try {
				
			pedirDatos();
			
			}catch(Exception e){
		
				System.out.println("Que demonios has introducido en la edad?");
				
			}
		}else {
			System.out.println("Adios");
			System.exit(0);
		}
		entrada.close();
	}	
	
		static void pedirDatos() throws IOException{ 
			
				//try { Este try catch no tiene sentido capturarlo en el propio metodo.
					
			Scanner entrada=new Scanner(System.in);
			
			System.out.println("Introduce tu nombre, por favor");
			String nombre_usuario=entrada.nextLine();
			
			System.out.println("Introduce edad, por favor");
			int edad=entrada.nextInt();
			
			System.out.println("Hola " + nombre_usuario + ". El a�o que viene tendr�s " + (edad+1) + " a�os");
			entrada.close();
			
				/*}catch(InputMismatchException e) { // catch(Exception) asi tb y no me como la cabeza.
					
				System.out.println("Que demonios has introducido en la edad?");
			}*/
			System.out.println("Hemos terminado");
		}
	
}

	
