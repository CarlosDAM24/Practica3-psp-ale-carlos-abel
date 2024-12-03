package view;

import controller.PrecargaDatos;
import controller.Utils;
import model.CuentaAhorro;
import model.CuentaCorriente;
import model.CuentaSA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class jFramePrincipal extends JFrame {
    private JPanel panelCrear, panelAñadir, panelDatos;
    private JButton btnCargar, btnGuardar, btnVaciar, btnCrearCuenta, btnBuscar;
    private JList<String> listaCuentas;
    private DefaultListModel<String> modeloLista;

    private ArrayList<CuentaSA> cuentas = new ArrayList<>();

    public jFramePrincipal() {
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

    Utils utils = new Utils();

    private void addListeners() {
        // Botón Crear Cuenta
        btnCrearCuenta.addActionListener(e -> mostrarFormularioCreacion());

        // Botón Buscar (actualizar lista)
        btnBuscar.addActionListener(e -> actualizarListaCuentas());

        btnCargar.addActionListener(e -> cargarDatos());

        btnVaciar.addActionListener(e -> vaciarDatos());

        btnGuardar.addActionListener(e -> guardarDatos());
    }

    private  void cargarDatos(){
        Utils utils = new Utils();
        utils.cargarCuentasDesdeFichero();
        this.cuentas = utils.getCuentas();
    }

    private void vaciarDatos(){
        this.cuentas.clear();
    }

    private  void guardarDatos(){utils.guardarCuentasDesdeLista(this.cuentas);}

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
                txtExtra1.setText("150.24");
                txtExtra1.disable();
                panelSubclase.add(txtExtra1);
                panelSubclase.add(new JLabel("Interés Retiros:"));
                txtExtra2.setText("3000.00");
                txtExtra2.disable();
                panelSubclase.add(txtExtra2);
            } else {
                panelSubclase.add(new JLabel("Comisión Mantenimiento:"));
                txtExtra1.setText("150.0");
                txtExtra1.disable();
                panelSubclase.add(txtExtra1);
                panelSubclase.add(new JLabel("Comisión Mensual:"));
                txtExtra2.setText("147.8");
                txtExtra2.disable();
                panelSubclase.add(txtExtra2);
            }
            panelSubclase.revalidate();
            panelSubclase.repaint();
        });

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            try {
                String titular = txtTitular.getText();
                double debe = Double.parseDouble(txtDebe.getText());
                double haber = Double.parseDouble(txtHaber.getText());

                // Validación: el saldo no puede iniciar en 0, y haber debe ser mayor que debe
                if (haber == 0 || haber <= debe) {
                    JOptionPane.showMessageDialog(formCuenta,
                            "El saldo inicial no puede ser 0, y el haber debe ser mayor que el debe.",
                            "Error de Validación",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Crear cuenta según el tipo seleccionado
                if (comboTipo.getSelectedItem().equals("Cuenta Ahorro")) {
                    // Descomentar y completar si tienes los campos adicionales
                    // double interesAnual = Double.parseDouble(txtExtra1.getText());
                    // double interesRetiros = Double.parseDouble(txtExtra2.getText());
                    cuentas.add(new CuentaAhorro(titular, debe, haber));
                } else if (comboTipo.getSelectedItem().equals("Cuenta Corriente")) {
                    // Descomentar y completar si tienes los campos adicionales
                    // double comisionMantenimiento = Double.parseDouble(txtExtra1.getText());
                    // double comisionMensual = Double.parseDouble(txtExtra2.getText());
                    cuentas.add(new CuentaCorriente(titular, debe, haber));
                }

                // Confirmación de creación
                JOptionPane.showMessageDialog(formCuenta, "Cuenta creada correctamente.");
                actualizarListaCuentas();
                formCuenta.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(formCuenta,
                        "Por favor, introduce valores numéricos válidos en los campos de debe y haber.",
                        "Error de Formato",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(formCuenta,
                        "Ocurrió un error inesperado: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        formCuenta.add(panelFormulario, BorderLayout.NORTH);
        formCuenta.add(panelSubclase, BorderLayout.CENTER);
        formCuenta.add(btnGuardar, BorderLayout.SOUTH);
        formCuenta.setVisible(true);
    }

    private void actualizarListaCuentas() {
        modeloLista.clear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        modeloLista.addElement(" \n ");
        for (CuentaSA cuenta : cuentas) {
            if (cuenta instanceof CuentaCorriente) {
                modeloLista.addElement("\n");
                modeloLista.addElement("Titular: "+((CuentaCorriente) cuenta).getTitularCuenta()+"   Debe: "+((CuentaCorriente) cuenta).getDebeCuenta()+"   Haber: "+((CuentaCorriente) cuenta).getHaberCuenta()+"   Saldo: "+Math.round(((CuentaCorriente) cuenta).getSaldo() * 100.0) / 100.0);
                modeloLista.addElement("Comisión/Mes: "+((CuentaCorriente) cuenta).getComisionMensual()+"   Comision/Mantenimiento: " + ((CuentaCorriente) cuenta).getComisionMantenimiento()+"   Fecha Apertura: " + ((CuentaCorriente) cuenta).getFechaApertura().format(formatter));
            } else if (cuenta instanceof CuentaAhorro) {
                modeloLista.addElement("\n");
                modeloLista.addElement("Titular: "+((CuentaAhorro) cuenta).getTitularCuenta()+"   Debe: "+((CuentaAhorro) cuenta).getDebeCuenta()+"   Haber: "+((CuentaAhorro) cuenta).getHaberCuenta()+"   Saldo: "+Math.round(((CuentaAhorro) cuenta).getSaldo() * 100.0) / 100.0);
                modeloLista.addElement("Interés Anual: "+((CuentaAhorro) cuenta).getInteresAnual()+"   Límite Retiros: " +((CuentaAhorro) cuenta).getLimiteRetiros()+"   Fecha Apertura: " + ((CuentaAhorro) cuenta).getFechaApertura().format(formatter));
            } else {
                modeloLista.addElement(cuenta.toString()); // Caso general para CuentaSA
            }
        }
    }

    public static void main(String[] args) {
        new jFramePrincipal();
    }
}
