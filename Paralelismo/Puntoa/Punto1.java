package ProyectoPunto2.paralelismo;

import java.io.*;

public class Punto1 {
    public static void main(String[] args) {
        try {
            FileInputStream fstream = new FileInputStream("ProyectoPunto2/paralelismo/DAT_ASCII_EURUSD_M1_2017_2019.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            String separator = ";";

            // Asignando los valores iniciales según la primera linea del archivo.
            String primerLineraArchivo = br.readLine();
            String[] preciosIniciales = primerLineraArchivo.substring(16, strLine.length() - 2).split(separator);

            double precioMayorApertura;
            double precioMenorApertura;
            precioMayorApertura = precioMenorApertura = Double.parseDouble(preciosIniciales[0]);

            double precioMayorMayor;
            double precioMenorMayor;
            precioMayorMayor = precioMenorMayor = Double.parseDouble(preciosIniciales[1]);

            double precioMayorClausura;
            double precioMenorClausura;
            precioMayorClausura = precioMenorClausura = Double.parseDouble(preciosIniciales[2]);

            double precioMayorMenor;
            double precioMenorMenor;
            precioMayorMenor = precioMenorMenor = Double.parseDouble(preciosIniciales[3]);

            // Inicio de ejecución del programa.
            long tiempoinicial = System.nanoTime();
            // Leer linea por linea
            while ((strLine = br.readLine()) != null) {
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
            }

            // Cerrando the input stream
            fstream.close();
            br.close();

            // Fin de ejecución del programa.
            long tiempofinal = System.nanoTime();

            long tiempototal = (tiempofinal - tiempoinicial);
            double seconds = (double)tiempototal / 1_000_000_000.0;

            System.out.println(String.format("Tiempo ejecución secuencial: %.18f segundos\n", seconds));

            // Solamente imprimir los valores bonitos.
            /*System.out.println(String.format("Precio mayor de apertura: %.6f", precioMayorApertura));
            System.out.println(String.format("Precio mayor de Mayores: %.6f", precioMayorMayor));
            System.out.println(String.format("Precio mayor de clausura: %.6f", precioMayorClausura));
            System.out.println(String.format("Precio mayor de menores: %.6f", precioMayorMenor));
            System.out.println("-------------------------------------");
            System.out.println(String.format("Precio menor de apertura: %.6f", precioMenorApertura));
            System.out.println(String.format("Precio menor de Mayores: %.6f", precioMenorMayor));
            System.out.println(String.format("Precio menor de clausura: %.6f", precioMenorClausura));
            System.out.println(String.format("Precio menor de menores: %.6f", precioMenorMenor));
            */
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}