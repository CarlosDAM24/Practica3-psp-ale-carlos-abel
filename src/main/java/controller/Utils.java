package controller;

import model.CuentaAhorro;
import model.CuentaCorriente;
import model.CuentaSA;

import java.io.*;
import java.util.ArrayList;

public class Utils {

    private static final String FICHERO_CUENTAS = "cuentas.txt";
    private ArrayList<CuentaSA> cuentas;

    // Constructor
    public Utils() {
        cuentas = new ArrayList<>();

    }

    // Función para añadir una cuenta y guardarla en el fichero
    public void añadirCuenta(CuentaSA cuenta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHERO_CUENTAS, true))) {
            writer.write(cuenta.getTitularCuenta() + "," + cuenta.getDebeCuenta() + "," + cuenta.getHaberCuenta() + "," + cuenta.getFechaApertura());
            writer.newLine();
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

    // Método auxiliar para convertir una línea del fichero en una cuenta
    private CuentaSA parseLinea(String linea) {
        String[] partes = linea.split(",");
        String titular = partes[0];
        double debe = Double.parseDouble(partes[1]);
        double haber = Double.parseDouble(partes[2]);
        // Aquí determinamos el tipo de cuenta basándonos en la información
        if (titular.contains("Ahorro")) {
            return new CuentaAhorro(titular, debe, haber);
        } else {
            return new CuentaCorriente(titular, debe, haber);
        }
    }

    public static void main(String[] args) {
        // Crear una instancia de Utils
        Utils utils = new Utils();

        // Recorrer el fichero en orden directo
        System.out.println("Recorriendo el fichero en orden directo:");
        utils.recorrerDirecto();

        // Recorrer el fichero en orden inverso
        System.out.println("\nRecorriendo el fichero en orden inverso:");
        utils.recorrerInverso();
    }
}
