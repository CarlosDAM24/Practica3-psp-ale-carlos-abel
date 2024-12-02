package controller;

import model.CuentaAhorro;
import model.CuentaCorriente;
import model.CuentaSA;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    // Método para leer el fichero y almacenar las cuentas en la lista
    public void cargarCuentasDesdeFichero() {
        System.out.println("Cargando cuentas desde el fichero:");
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHERO_CUENTAS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                CuentaSA cuenta = parseLinea(linea);
                cuentas.add(cuenta);  // Guardar la cuenta en la lista
            }
        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
        }
    }

    // Método para recorrer el fichero en orden directo
    public void recorrerDirecto() {
        System.out.println("Recorriendo el fichero en orden directo:");
        for (CuentaSA cuenta : cuentas) {
            System.out.println(cuenta);
        }
    }

    // Método para recorrer el fichero en orden inverso
    public void recorrerInverso() {
        System.out.println("Recorriendo el fichero en orden inverso:");
        for (int i = cuentas.size() - 1; i >= 0; i--) {
            System.out.println(cuentas.get(i));
        }
    }

    // Método auxiliar para convertir una línea del fichero en una cuenta
    private CuentaSA parseLinea(String linea) {
        String[] partes = linea.split(",");
        String titular = partes[0];
        double debe = Double.parseDouble(partes[1]);
        double haber = Double.parseDouble(partes[2]);
        String fechaString = partes[3];

        // Convertir la fecha de apertura desde el formato String
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaApertura = LocalDate.parse(fechaString, formatter);

        // Aquí determinamos el tipo de cuenta basándonos en el nombre del titular
        if (titular.startsWith("Corriente")) {
            return new CuentaCorriente(titular, debe, haber);
        } else if (titular.startsWith("Ahorro")) {
            return new CuentaAhorro(titular, debe, haber);
        } else {
            return new CuentaSA(titular, debe, haber); // En caso de un tipo desconocido
        }
    }

    // Getter para las cuentas
    public ArrayList<CuentaSA> getCuentas() {
        return cuentas;
    }
}
