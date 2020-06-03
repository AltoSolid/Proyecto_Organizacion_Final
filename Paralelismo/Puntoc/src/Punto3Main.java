package src;

import java.io.*;

public class Punto3Main {
	static int limiteLinea = 1116772;
	public static String nombre_archivo;
	public static void main(String[] args) {
		nombre_archivo = args[0];

		long tiempoinicial = System.nanoTime();
		Punto3 Punto3 = new Punto3();
		// Creating producer and consumer threads
		Punto3.comparacionFinal[4] = 90;
		Punto3.comparacionFinal[5] = 90;
		Punto3.comparacionFinal[6] = 90;
		Punto3.comparacionFinal[7] = 90;
		// int cantidad = 20;
		Thread master = new Thread(new Master(Punto3));
		master.start();

		Thread consumer = new Thread(new Consumer(Punto3));
		consumer.start();
		try {
			consumer.join();

		} catch (InterruptedException e) {
			System.out.println(e);
		}
		Punto3.imprimirResultadoFinal();
		
		long tiempofinal = System.nanoTime();

        long tiempototal = (tiempofinal - tiempoinicial);
        double seconds = (double) tiempototal / 1_000_000_000.0;
        System.out.println(String.format(": demora %.18f segundos\n", seconds));
	}
}

class Master implements Runnable {
	String nombre = Punto3Main.nombre_archivo;
	//String nombre = "Paralelismo\\DAT_ASCII_EURUSD_M1_2017_2019.csv";
	private Punto3 Punto3;

	public Master(Punto3 Punto3) {
		this.Punto3 = Punto3;
	}

	@Override
	public void run() {
		String str = null;
		try {
			FileInputStream fstream = new FileInputStream(nombre);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			// Asignando los valores iniciales según la primera linea del archivo.
			// String primerLineraArchivo = br.readLine();
			// int cont = 0;
			try {
				for (int i = 0; i < Punto3Main.limiteLinea; ++i) {
					str = br.readLine();
					Punto3.put(str);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			br.close();

		} catch (IOException e) {
			System.out.println("Error: " + e);
		}

	}
}

class Consumer implements Runnable {
	private Punto3 Punto3;

	public Consumer(Punto3 Punto3) {
		this.Punto3 = Punto3;
	}

	@Override
	public void run() {
		for (int i = 0; i < Punto3Main.limiteLinea; i++) { //
			try {
				Punto3.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}