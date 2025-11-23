import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RadixSort {


    public static void main(String[] args) {

        String archivoEntrada = "numeros.txt";
        String archivoSalida = "numeros_ordenados_radix.txt";

        System.out.println("Leyendo archivo: " + archivoEntrada);
        int[] numeros = leerArchivo(archivoEntrada);

        System.out.println("\n numeros originales:");
        imprimirArray(numeros);

        System.out.println("\n Se esta ejecutando RadixSort (se ordena en tiempo real):");
        radixSort(numeros);

        System.out.println("\n numeros ordenados:");
        imprimirArray(numeros);

        guardarArchivo(archivoSalida, numeros);
        System.out.println("\n Archivo ordenado guardado como: " + archivoSalida);

    }


    public static int[] leerArchivo(String nombreArchivo) {
        ArrayList<Integer> numeros = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split("[,\\s]+");
                for (String v : valores) {
                    if (!v.trim().isEmpty()) {
                        numeros.add(Integer.parseInt(v.trim()));
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            System.exit(1);
        }
        return numeros.stream().mapToInt(i -> i).toArray();
    }

    public static int obtenerMaximo(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
        }
        return max;
    }
 public static void radixSort(int[] arr) {
        int max = obtenerMaximo(arr);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortPorDigito(arr, exp);
            System.out.print("Ordenando por dígito (" + exp + "): ");
            imprimirArray(arr); 
        }
    }






public static void countingSortPorDigito(int[] arr, int exp) {
        int[] salida = new int[arr.length];
        int[] conteo = new int[10];

        for (int num : arr) {
            int indice = (num / exp) % 10;
            conteo[indice]++;
        }



    public static void guardarArchivo(String nombreArchivo, int[] datos) {
        try (FileWriter fw = new FileWriter(nombreArchivo)) {
            for (int num : datos) {
                fw.write(num + " ");
            }
        } catch (IOException e) {
            System.out.println("Error al guardar archivo: " + e.getMessage());
        }
    }

    public static void imprimirArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}