package org.example;

import model.CuentaAhorro;
import model.CuentaCorriente;
import model.CuentaSA;
import model.MiList;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Crear una lista de cuentas
        MiList<CuentaSA> listaCuentas = new MiList<>();

        // Crear instancias de CuentaSA y sus subclases
        CuentaSA cuenta1 = new CuentaSA("Titular1", 100.0, 200.0,LocalDate.of(2004,02,02));
        CuentaAhorro cuenta2 = new CuentaAhorro("Titular2", 50.0, 150.0,LocalDate.of(2004,02,02));
        CuentaCorriente cuenta3 = new CuentaCorriente("Titular3", 20.0, 80.0,LocalDate.of(2004,02,02));
        CuentaAhorro cuenta4 = new CuentaAhorro("Titular4", 120.0, 300.0,LocalDate.of(2004,02,02));

        // Insertar cuentas en la lista
        listaCuentas.insertarNodo(cuenta1);
        listaCuentas.insertarNodo(cuenta2);
        listaCuentas.insertarNodo(cuenta3);
        listaCuentas.insertarNodo(cuenta4);

        // Mostrar las cuentas en la lista
        System.out.println("Contenido de la lista:");
        listaCuentas.mostrarLista();

        // Obtener el primer nodo
        MiList.Node<CuentaSA> primerNodo = listaCuentas.getInicio();
        System.out.println("\nPrimer nodo:");
        if (primerNodo != null) {
            System.out.println(primerNodo.getPrincipal().toString());
        }

        // Ejemplo de getSiguiente
        System.out.println("\nSegundo nodo (usando getSiguiente):");
        MiList.Node<CuentaSA> segundoNodo = listaCuentas.getSiguiente(primerNodo);
        if (segundoNodo != null) {
            System.out.println(segundoNodo.getPrincipal().toString());
        }

        // Ejemplo de getAnterior
        System.out.println("\nAnterior al segundo nodo (usando getAnterior):");
        MiList.Node<CuentaSA> anteriorAlSegundo = listaCuentas.getAnterior(segundoNodo);
        if (anteriorAlSegundo != null) {
            System.out.println(anteriorAlSegundo.getPrincipal().toString());
        }

        // Insertar otra cuenta y mostrar nuevamente
        CuentaCorriente cuenta5 = new CuentaCorriente("Titular5", 500.0, 1000.0,LocalDate.of(2004,02,02));
        listaCuentas.insertarNodo(cuenta5);
        System.out.println("\nContenido de la lista después de insertar otra cuenta:");
        listaCuentas.mostrarLista();
    }
}