
public class SincronizandoHilos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HilosVarios hilo1=new HilosVarios();
		HilosVarios2 hilo2=new HilosVarios2(hilo1);
		
	//Estas dos tareas se ir�n sincronizando entre ellas
		
		hilo2.start();
		hilo1.start();
		
		//System.out.println("Terminadas las tareas");
	}

}

class HilosVarios extends Thread{
	
	public void run() {
		
		for(int i=0;i<15;i++) {
			//Los hilos por defecto se van ejecutando de forma aleatoria
			System.out.println("Ejecucion hilo" + getName());
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

class HilosVarios2 extends Thread{
	
	private Thread hilo;
	
	public HilosVarios2(Thread hilo) {
		
		this.hilo=hilo;
	}
	
	public void run() {
		
		// Con este metodo join no empezar� un hilo y cuando termine hir� al otro. SINCRONIZAR� las tareas.	
		try {
			hilo.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(int i=0;i<15;i++) {
			//Los hilos por defecto se van ejecutando de forma aleatoria
			System.out.println("Ejecucion hilo" + getName());
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
