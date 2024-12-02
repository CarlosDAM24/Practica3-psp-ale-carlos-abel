package controller;

import model.CuentaSA;
import java.io.*;
import java.util.ArrayList;

public class Utils {

    private static final String FICHERO_CUENTAS = "cuentas.txt";

    // Método para añadir una cuenta y guardarla en el fichero
    public void añadirCuenta(CuentaSA cuenta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHERO_CUENTAS, true))) {
            writer.write(cuenta.getTitularCuenta() + "," + cuenta.getDebeCuenta() + "," + cuenta.getHaberCuenta());
            writer.newLine();
            System.out.println("Cuenta añadida: " + cuenta);
        } catch (IOException e) {
            System.err.println("Error al guardar la cuenta: " + e.getMessage());
        }
    }

    // Método para recorrer el fichero en orden directo
    public void recorrerDirecto() {
        System.out.println("Recorriendo el fichero en orden directo:");
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHERO_CUENTAS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                CuentaSA cuenta = parseLinea(linea);
                System.out.println(cuenta);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    // Método para recorrer el fichero en orden inverso
    public void recorrerInverso() {
        System.out.println("Recorriendo el fichero en orden inverso:");
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHERO_CUENTAS))) {
            ArrayList<CuentaSA> cuentas = new ArrayList<>();
            String linea;
            while ((linea = reader.readLine()) != null) {
                cuentas.add(parseLinea(linea));
            }
            for (int i = cuentas.size() - 1; i >= 0; i--) {
                System.out.println(cuentas.get(i));
            }
        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    // Método para vaciar el fichero
    public void vaciarLista() {
        try (PrintWriter writer = new PrintWriter(FICHERO_CUENTAS)) {
            writer.print("");
            System.out.println("El fichero ha sido vaciado.");
        } catch (IOException e) {
            System.err.println("Error al vaciar el fichero: " + e.getMessage());
        }
    }

    // Método auxiliar para convertir una línea del fichero en una cuenta
    private CuentaSA parseLinea(String linea) {
        String[] partes = linea.split(",");
        String titular = partes[0];
        double debe = Double.parseDouble(partes[1]);
        double haber = Double.parseDouble(partes[2]);
        return new CuentaSA(titular, debe, haber);
    }

    // Método principal para probar la funcionalidad
    public static void main(String[] args) {
        Utils utils = new Utils();

        CuentaSA cuenta1 = new CuentaSA("Titular 1", 1000, 2000);
        CuentaSA cuenta2 = new CuentaSA("Titular 2", 500, 1500);
        CuentaSA cuenta3 = new CuentaSA("Titular 3", 200, 800);

        // Añadir cuentas al fichero
        utils.añadirCuenta(cuenta1);
        utils.añadirCuenta(cuenta2);
        utils.añadirCuenta(cuenta3);

        // Recorrer el fichero en orden directo
        utils.recorrerDirecto();

        // Recorrer el fichero en orden inverso
        utils.recorrerInverso();

        // Vaciar el fichero
        utils.vaciarLista();

        // Intentar recorrer el fichero después de vaciar
        utils.recorrerDirecto();
    }
}
