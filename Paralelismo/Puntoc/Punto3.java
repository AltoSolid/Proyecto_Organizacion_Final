package Puntoc;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
 
public class Punto3 {
	long tiempoinicial = System.nanoTime();
	final Lock lock = new ReentrantLock();
 
	// Conditions
	final Condition produceCond  = lock.newCondition(); 
	final Condition consumeCond = lock.newCondition(); 
	
	// Array to store element for CustomBlockingQueue
	final Object[] array = new Object[4];
	int putIndex, takeIndex;
	int count;
	double precioMayorApertura;
	double precioMayorClausura;
	double precioMayorMayor;
	double precioMayorMenor;
	double precioMenorApertura;
	double precioMenorClausura;
	double precioMenorMayor;
	double precioMenorMenor;
	static double[] comparacionFinal = new double[8];
	public void put(Object x) throws InterruptedException {
 
		lock.lock();
		try {
			while (count == array.length){
				// Queue is full, producers need to wait
				produceCond.await();
			}
			String str=""+x;
			String linea= str.substring(16,51);
			array[putIndex] = linea;
			putIndex++;
			if (putIndex == array.length){
				putIndex = 0;
			}
			// Increment the count for the array
			++count;
			consumeCond.signal();
		} finally {
			lock.unlock();
		}
	}
 
	public Object take() throws InterruptedException {
		lock.lock();
		try {

			while (count == 0){
				// Queue is empty, consumers need to wait
				consumeCond.await();
			}
			Object x = array[takeIndex];
			String str = ""+x;
			String separada [] = str.split(";");
			precioMenorApertura=Double.parseDouble(separada[0]);
			precioMenorClausura=Double.parseDouble(separada[1]);
			precioMenorMayor=Double.parseDouble(separada[2]);
			precioMenorMenor=Double.parseDouble(separada[3]);
			// System.out.println(precioMenorApertura);
			if (precioMayorApertura < (Double.parseDouble(separada[0]))) { // Precio mayor Apertura
				precioMayorApertura = Double.parseDouble(separada[0]);
			}
			if (precioMayorMayor < (Double.parseDouble(separada[1]))) { // Precio mayor Mayor
				precioMayorMayor = Double.parseDouble(separada[1]);
			}
			if (precioMayorClausura < (Double.parseDouble(separada[2]))) { // Precio mayor clausura
				precioMayorClausura = Double.parseDouble(separada[2]);
			}
			if (precioMayorMenor < (Double.parseDouble(separada[3]))) { // Precio mayor menor
				precioMayorMenor = Double.parseDouble(separada[3]);
			}

			// Determinar valores menores
			if (precioMenorApertura > (Double.parseDouble(separada[0]))) { // Precio menor Apertura
				precioMenorApertura = Double.parseDouble(separada[0]);
			}
			if (precioMenorMayor > (Double.parseDouble(separada[1]))) { // Precio menor Mayor
				precioMenorMayor = Double.parseDouble(separada[1]);
			}
			if (precioMenorClausura > (Double.parseDouble(separada[2]))) { // Precio menor clausura
				precioMenorClausura = Double.parseDouble(separada[2]);
			}
			if (precioMenorMenor > (Double.parseDouble(separada[3]))) { // Precio menor menor
				precioMenorMenor = Double.parseDouble(separada[3]);
			}



			if (precioMayorApertura > comparacionFinal[0]) {
                comparacionFinal[0] = precioMayorApertura;
            }
            if (precioMayorMayor > comparacionFinal[1]) {
                comparacionFinal[1] = precioMayorMayor;
            }
            if (precioMayorClausura > comparacionFinal[2]) {
                comparacionFinal[2] = precioMayorClausura;
            }
            if (precioMayorMenor > comparacionFinal[3]) {
                comparacionFinal[3] = precioMayorMenor;
            }
            if (precioMenorApertura < comparacionFinal[4]) {
				comparacionFinal[4] = precioMenorApertura;
            }
            if (precioMenorMayor < comparacionFinal[5]) {
				comparacionFinal[5] = precioMenorMayor;
            }
            if (precioMenorClausura < comparacionFinal[6]) {
				comparacionFinal[6] = precioMenorClausura;
            }
            if (precioMenorMenor < comparacionFinal[7]) {
				comparacionFinal[7] = precioMenorMenor;
            }
			// System.out.println("Producing - " + x);
			// System.out.println("Consuming - " + x);
			takeIndex++;
			if (takeIndex == array.length){
				takeIndex = 0;
			}
			// reduce the count for the array
			--count;
			// send signal producer
			produceCond.signal();
			return x;
		} finally {
			lock.unlock();
		}
	}
	public static void imprimirResultadoFinal() {
        // Imprimir números mayores y menores de todo el archivo
        System.out.println("----------------");
        for (int i = 0; i < comparacionFinal.length; ++i) {
            System.out.println(String.format(" Valor final: %.6f", comparacionFinal[i]));
        }
        System.out.println("----------------");
    }
}