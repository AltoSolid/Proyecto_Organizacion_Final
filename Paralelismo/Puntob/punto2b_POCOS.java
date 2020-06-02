package Puntob;

import java.io.*;

public class punto2b_POCOS implements Runnable {
    int filaInicioHilo;
    int filaFinHilo;
    String nombre;
    String separator = ";";
    String nombre_archivo;
    static double[] comparacionFinal = new double[8];

    public punto2b_POCOS(String nombre, int filaInicioHilo, int filaFinHilo, String nombre_archivo) {
        this.nombre = nombre;
        this.filaInicioHilo = filaInicioHilo;
        this.filaFinHilo = filaFinHilo;
        this.nombre_archivo = nombre_archivo;
    }

    // Todo lo que se meta aquí se ejecutará por cada hilo creado
    @Override
    public void run() {
        try {

            // Lectura del archivo
            FileInputStream fstream = new FileInputStream(nombre_archivo);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;

            comparacionFinal[4] = 90.0;
            comparacionFinal[5] = 90.0;
            comparacionFinal[6] = 90.0;
            comparacionFinal[7] = 90.0;

            // Asignando los valores iniciales según la primera linea del archivo.
            String primerLineraArchivo = br.readLine();
            String[] preciosIniciales = primerLineraArchivo.split(separator);

            double precioMenorApertura;
            double precioMayorApertura = precioMenorApertura = Double.parseDouble(preciosIniciales[2]);

            double precioMayorMayor;
            double precioMenorMayor = precioMayorMayor = precioMenorMayor = Double.parseDouble(preciosIniciales[3]);

            double precioMayorClausura;
            double precioMenorClausura = precioMayorClausura = precioMenorClausura = Double
                    .parseDouble(preciosIniciales[4]);

            double precioMayorMenor;
            double precioMenorMenor = precioMayorMenor = precioMenorMenor = Double.parseDouble(preciosIniciales[5]);

            // Inicio de ejecución del programa.
            

            // Cerrando the input stream
            fstream.close();
            br.close();

            int contador = 0;

            // Lectura del archivo
            FileInputStream fstream2 = new FileInputStream(nombre_archivo);
            BufferedReader br2 = new BufferedReader(new InputStreamReader(fstream2));

            while (contador<filaInicioHilo-1){
                br2.readLine();
                ++contador;
            }
            // Leer linea por linea
            while (filaInicioHilo!=filaFinHilo){
                strLine = br2.readLine();
                String[] separada = new String[4];
                separada = strLine.substring(16, strLine.length() - 2).split(separator);

                    // Determinar valores mayores
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
                    //if (contador == filaFinHilo)
                    //    break;
                //++contador;
                ++filaInicioHilo;
            } 
            br2.close();

            // Fin de ejecución del programa.


            // Metiendo valores en el arreglo de los mayores de cada columna.
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

            
        } catch (IOException e) {
            System.out.println("Error: " + e);
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