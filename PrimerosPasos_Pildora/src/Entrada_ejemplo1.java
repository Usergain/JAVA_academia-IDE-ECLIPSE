import java.util.Scanner;

public class Entrada_ejemplo1 {

	public static void main(String[] args) {
		
		Scanner entrada=new Scanner(System.in);
		
		System.out.println("Introduce tu nombre, por favor");
		
		String nombre_usuario=entrada.nextLine();
		
		System.out.println("Introduce edad, por favor");
		
		int edad=entrada.nextInt();
		
		System.out.println("Hola " + nombre_usuario + " El a�o que vien tendr�s " + (edad+1) + " a�os");

	}

}

