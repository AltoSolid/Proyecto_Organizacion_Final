package Puntob;

public class punto2bMain_POCOS {
    public static void main(String[] args) {
        long tiempoinicial = System.nanoTime();
        int limiteLinea = 1116772;
        int cantidad = 20;
        int separador = limiteLinea / cantidad;
        int residuo = limiteLinea % cantidad;

        // Creacición de hilos
        Thread[] arreglito = new Thread[cantidad];
        for (int i =0; i < cantidad-1; i++){
            arreglito[i]= new Thread(new punto2b_POCOS("Hilo "+ (i+1), separador*i, separador*(i+1), args[0]));
        }
        arreglito[cantidad-1] = new Thread(new punto2b_POCOS("Hilo "+ (cantidad), separador*(cantidad-1), separador*cantidad+residuo, args[0])); //Último que lee el residuo

        for (int i=0; i< cantidad;i++){
            arreglito[i].start();
        }

        try {
            for (int i = 0; i < arreglito.length; i++) {
                arreglito[i].join();
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        punto2b_POCOS.imprimirResultadoFinal();
        long tiempofinal = System.nanoTime();

        long tiempototal = (tiempofinal - tiempoinicial);
        double seconds = (double) tiempototal / 1_000_000_000.0;
        System.out.println(String.format(": demora %.18f segundos\n", seconds));
    }
}