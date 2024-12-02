package view;

import controller.PrecargaDatos;
import model.CuentaAhorro;
import model.CuentaCorriente;
import model.CuentaSA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class jFramePrincipal extends JFrame {
    private JPanel panelCrear, panelAñadir, panelDatos;
    private JButton btnCargar, btnGuardar, btnVaciar, btnCrearCuenta, btnBuscar;
    private JList<String> listaCuentas;
    private DefaultListModel<String> modeloLista;

    private PrecargaDatos precargaDatos;
    private ArrayList<CuentaSA> cuentas = new ArrayList<>();

    public jFramePrincipal() {
         new PrecargaDatos(cuentas);
//        CuentaAhorro cuentaAhorro = new CuentaAhorro("Ale",200.00,200.00);
//        cuentas.add(cuentaAhorro);
//        CuentaCorriente cuentaCorriente = new CuentaCorriente("Abel",2000.00,2000.00);
//        cuentas.add(cuentaCorriente);
        setTitle("Gestión de Cuentas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Crear panel principal con pestañas
        JTabbedPane pestañas = new JTabbedPane();
        panelCrear = new JPanel(new FlowLayout());
        panelAñadir = new JPanel(new BorderLayout());

        pestañas.addTab("Crear", panelCrear);
        pestañas.addTab("Añadir", panelAñadir);

        // Pestaña "Crear"
        btnCargar = new JButton("Cargar");
        btnGuardar = new JButton("Guardar");
        btnVaciar = new JButton("Vaciar");
        panelCrear.add(btnCargar);
        panelCrear.add(btnGuardar);
        panelCrear.add(btnVaciar);

        // Pestaña "Añadir"
        btnCrearCuenta = new JButton("Crear Cuenta");
        btnBuscar = new JButton("Buscar");
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnCrearCuenta);
        panelBotones.add(btnBuscar);

        panelDatos = new JPanel();
        modeloLista = new DefaultListModel<>();
        listaCuentas = new JList<>(modeloLista);

        JScrollPane scrollPane = new JScrollPane(listaCuentas);
        panelAñadir.add(panelBotones, BorderLayout.NORTH);
        panelAñadir.add(scrollPane, BorderLayout.CENTER);

        // Añadir pestañas al JFrame
        add(pestañas);

        // Listeners
        addListeners();

        setVisible(true);
                
    }

    private void addListeners() {
        // Botón Crear Cuenta
        btnCrearCuenta.addActionListener(e -> mostrarFormularioCreacion());

        // Botón Buscar (actualizar lista)
        btnBuscar.addActionListener(e -> actualizarListaCuentas());
    }

    private void mostrarFormularioCreacion() {
        JFrame formCuenta = new JFrame("Nueva Cuenta");
        formCuenta.setSize(400, 300);
        formCuenta.setLayout(new BorderLayout());

        // Centrar el JFrame en la pantalla
        formCuenta.setLocationRelativeTo(null);

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2));
        JTextField txtTitular = new JTextField();
        JTextField txtDebe = new JTextField();
        JTextField txtHaber = new JTextField();
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Cuenta Ahorro", "Cuenta Corriente"});

        panelFormulario.add(new JLabel("Titular:"));
        panelFormulario.add(txtTitular);
        panelFormulario.add(new JLabel("Debe:"));
        panelFormulario.add(txtDebe);
        panelFormulario.add(new JLabel("Haber:"));
        panelFormulario.add(txtHaber);
        panelFormulario.add(new JLabel("Tipo:"));
        panelFormulario.add(comboTipo);

        JPanel panelSubclase = new JPanel(new GridLayout(2, 2));
        JTextField txtExtra1 = new JTextField();
        JTextField txtExtra2 = new JTextField();
        panelSubclase.add(new JLabel());
        panelSubclase.add(new JLabel());

        comboTipo.addActionListener(e -> {
            panelSubclase.removeAll();
            if (comboTipo.getSelectedItem().equals("Cuenta Ahorro")) {
                panelSubclase.add(new JLabel("Interés Anual:"));
                panelSubclase.add(txtExtra1);
                panelSubclase.add(new JLabel("Interés Retiros:"));
                panelSubclase.add(txtExtra2);
            } else {
                panelSubclase.add(new JLabel("Comisión Mantenimiento:"));
                panelSubclase.add(txtExtra1);
                panelSubclase.add(new JLabel("Comisión Mensual:"));
                panelSubclase.add(txtExtra2);
            }
            panelSubclase.revalidate();
            panelSubclase.repaint();
        });

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            String titular = txtTitular.getText();
            double debe = Double.parseDouble(txtDebe.getText());
            double haber = Double.parseDouble(txtHaber.getText());
            if (comboTipo.getSelectedItem().equals("Cuenta Ahorro")) {
                double interesAnual = Double.parseDouble(txtExtra1.getText());
                double interesRetiros = Double.parseDouble(txtExtra2.getText());
                //cuentas.add(new CuentaAhorro(titular, debe, haber, interesAnual, interesRetiros));
            } else {
                double comisionMantenimiento = Double.parseDouble(txtExtra1.getText());
                double comisionMensual = Double.parseDouble(txtExtra2.getText());
                //cuentas.add(new CuentaCorriente(titular, debe, haber, comisionMantenimiento, comisionMensual));
            }
            JOptionPane.showMessageDialog(formCuenta, "Cuenta creada correctamente.");
            formCuenta.dispose();
        });

        formCuenta.add(panelFormulario, BorderLayout.NORTH);
        formCuenta.add(panelSubclase, BorderLayout.CENTER);
        formCuenta.add(btnGuardar, BorderLayout.SOUTH);
        formCuenta.setVisible(true);
    }

    private void actualizarListaCuentas() {
        modeloLista.clear();
        modeloLista.addElement(" \n ");
        for (CuentaSA cuenta : cuentas) {
            if (cuenta instanceof CuentaCorriente) {
                modeloLista.addElement("Titular: "+((CuentaCorriente) cuenta).getTitularCuenta()+"   Debe: "+((CuentaCorriente) cuenta).getDebeCuenta()+"   Haber: "+((CuentaCorriente) cuenta).getHaberCuenta()+"   Saldo: "+((CuentaCorriente) cuenta).getSaldo() +"   Comisión/Mes: "+((CuentaCorriente) cuenta).getComisionMensual()+"   Comision/Mantenimiento: " + ((CuentaCorriente) cuenta).getComisionMantenimiento());
            } else if (cuenta instanceof CuentaAhorro) {
                modeloLista.addElement("Titular: "+((CuentaAhorro) cuenta).getTitularCuenta()+"   Debe: "+((CuentaAhorro) cuenta).getDebeCuenta()+"   Haber: "+((CuentaAhorro) cuenta).getHaberCuenta()+"   Saldo: "+((CuentaAhorro) cuenta).getHaberCuenta()+"   Comisión/Mes: "+((CuentaAhorro) cuenta).getInteresAnual()+"   Comision/Mantenimiento: " +((CuentaAhorro) cuenta).getLimiteRetiros());
            } else {
                modeloLista.addElement(cuenta.toString()); // Caso general para CuentaSA
            }
        }
    }

    public static void main(String[] args) {
        new jFramePrincipal();
    }
}
