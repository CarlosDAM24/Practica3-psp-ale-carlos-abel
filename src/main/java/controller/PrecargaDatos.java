package controller;

import model.CuentaAhorro;
import model.CuentaCorriente;
import model.CuentaSA;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class PrecargaDatos {

    public PrecargaDatos(ArrayList<CuentaSA> cuentas) {
        Random random = new Random();
        // Generar 5,000 cuentas de ahorro
        for (int i = 0; i < 5000; i++) {
            String titular = "AhorroTitular" + i; // Nombre único para cada cuenta
            double debe = Math.round((100 + random.nextDouble() * 900) * 100.0) / 100.0; // Valores aleatorios entre 100 y 1000
            double haber = Math.round((100 + random.nextDouble() * 900) * 100.0) / 100.0;

            CuentaAhorro cuentaAhorro = new CuentaAhorro(titular, debe, haber, LocalDate.now());
            cuentas.add(cuentaAhorro);
        }

        // Generar 5,000 cuentas corrientes
        for (int i = 0; i < 5000; i++) {
            String titular = "CorrienteTitular" + i; // Nombre único para cada cuenta
            double debe = Math.round((1000 + random.nextDouble() * 9000) * 100.0) / 100.0; // Valores aleatorios entre 1000 y 10000
            double haber = Math.round((1000 + random.nextDouble() * 9000) * 100.0) / 100.0;

            CuentaCorriente cuentaCorriente = new CuentaCorriente(titular, debe, haber, LocalDate.now());
            cuentas.add(cuentaCorriente);
        }
    }

}
