package Punto3;

import java.io.*;

public class CustomBlockingQueueMain {
	static int limiteLinea = 1116772;

	public static void main(String[] args) {

		CustomBlockingQueue customBlockingQueue = new CustomBlockingQueue();
		// Creating producer and consumer threads
		CustomBlockingQueue.comparacionFinal[4] = 90;
		CustomBlockingQueue.comparacionFinal[5] = 90;
		CustomBlockingQueue.comparacionFinal[6] = 90;
		CustomBlockingQueue.comparacionFinal[7] = 90;
		// int cantidad = 20;
		Thread master = new Thread(new Master(customBlockingQueue));
		master.start();

		Thread consumer = new Thread(new Consumer(customBlockingQueue));
		// Thread consumer1 = new Thread(new Consumer(customBlockingQueue));
		consumer.start();
		try {
			consumer.join();
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		CustomBlockingQueue.imprimirResultadoFinal();
		// consumer1.start(); #Aquí se queda esperando algo cuando hacemos más de un Thread. Revisar
		/*
		 * Thread[] arreglito = new Thread[cantidad]; for (int i =0; i < cantidad; i++){
		 * arreglito[i]= new Thread(new Consumer(customBlockingQueue)); }
		 * 
		 * for (int i=0; i< cantidad;i++){ arreglito[i].start(); }
		 * 
		 * try { //for (int i = 0; i < arreglito.length; i++) { // arreglito[i].join();
		 * //} } catch (InterruptedException e) { System.out.println(e); }
		 */

	}
}

class Master implements Runnable {
	String nombre_archivo = "ProyectoPunto2/paralelismo/DAT_ASCII_EURUSD_M1_2017_2019.csv";

	private CustomBlockingQueue customBlockingQueue;

	public Master(CustomBlockingQueue customBlockingQueue) {
		this.customBlockingQueue = customBlockingQueue;
	}

	@Override
	public void run() {
		String str = null;
		try {
			FileInputStream fstream = new FileInputStream(nombre_archivo);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			// Asignando los valores iniciales según la primera linea del archivo.
			// String primerLineraArchivo = br.readLine();
			// int cont = 0;
			try {
				for (int i = 0; i < CustomBlockingQueueMain.limiteLinea; ++i) {
					str = br.readLine();
					customBlockingQueue.put(str);
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
	private CustomBlockingQueue customBlockingQueue;

	public Consumer(CustomBlockingQueue customBlockingQueue) {
		this.customBlockingQueue = customBlockingQueue;
	}

	@Override
	public void run() {
		for (int i = 0; i < CustomBlockingQueueMain.limiteLinea; i++) { //
			try {
				customBlockingQueue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}