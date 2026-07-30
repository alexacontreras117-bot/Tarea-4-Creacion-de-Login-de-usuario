package com.tarea4.ui;

import com.tarea4.factory.UsuarioFactory;
import com.tarea4.model.Usuario;
import com.tarea4.repository.UsuarioRepository;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Pantalla de registro.

public class RegistroFrame extends JFrame {

    private JTextField txtNombre, txtApellido, txtUsername, txtTelefono, txtCorreo;
    private JPasswordField txtPassword, txtConfirmar;
    private final LoginFrame loginFrame;

    public RegistroFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
        setTitle("Registro");
        setSize(400, 470);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (loginFrame != null) {
                    loginFrame.setVisible(true);
                }
            }
        });
    }

// Cuerpo de la pantalla de registro.
    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(174, 198, 231));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("REGISTRO", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titulo, gbc);
        gbc.gridwidth = 1;

        int fila = 1;
        gbc.gridy = fila;
        gbc.gridx = 0;
        panel.add(new JLabel("Nombre:"), gbc);
        txtNombre = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtNombre, gbc);
        fila++;

        gbc.gridy = fila;
        gbc.gridx = 0;
        panel.add(new JLabel("Apellido:"), gbc);
        txtApellido = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtApellido, gbc);
        fila++;

        gbc.gridy = fila;
        gbc.gridx = 0;
        panel.add(new JLabel("Usuario:"), gbc);
        txtUsername = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtUsername, gbc);
        fila++;

        gbc.gridy = fila;
        gbc.gridx = 0;
        panel.add(new JLabel("Telefono:"), gbc);
        txtTelefono = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtTelefono, gbc);
        fila++;

        gbc.gridy = fila;
        gbc.gridx = 0;
        panel.add(new JLabel("Correo electronico:"), gbc);
        txtCorreo = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtCorreo, gbc);
        fila++;

        gbc.gridy = fila;
        gbc.gridx = 0;
        panel.add(new JLabel("Contraseña:"), gbc);
        txtPassword = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(txtPassword, gbc);
        fila++;

        gbc.gridy = fila;
        gbc.gridx = 0;
        panel.add(new JLabel("Confirmar contraseña:"), gbc);
        txtConfirmar = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(txtConfirmar, gbc);
        fila++;

        JButton btnRegistrar = new JButton("Registrar");
        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.gridwidth = 2;
        panel.add(btnRegistrar, gbc);

        add(panel);

        btnRegistrar.addActionListener(e -> registrar());
    }

// Obligacion de cada campo.
    private void registrar() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String username = txtUsername.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();
        String password = new String(txtPassword.getPassword());
        String confirmar = new String(txtConfirmar.getPassword());

        if (nombre.isEmpty()) {
            mostrarError("El campo Nombre es obligatorio");
            return;
        }
        if (apellido.isEmpty()) {
            mostrarError("El campo Apellido es obligatorio");
            return;
        }
        if (username.isEmpty()) {
            mostrarError("El campo Nombre de usuario es obligatorio");
            return;
        }
        if (telefono.isEmpty()) {
            mostrarError("El campo Numero de telefono es obligatorio");
            return;
        }
        if (correo.isEmpty()) {
            mostrarError("El campo Correo electronico es obligatorio");
            return;
        }
        if (password.isEmpty()) {
            mostrarError("El campo Contraseña es obligatorio");
            return;
        }
        if (confirmar.isEmpty()) {
            mostrarError("El campo Confirmar contraseña es obligatorio");
            return;
        }
        if (!password.equals(confirmar)) {
            mostrarError("La contraseña y la confirmacion no coinciden");
            return;
        }
        if (UsuarioRepository.getInstance().existeUsername(username)) {
            mostrarError("El nombre de usuario ya esta registrado");
            return;
        }

        Usuario nuevo = UsuarioFactory.crearUsuario(username, nombre, apellido, telefono, correo, password);
        UsuarioRepository.getInstance().agregar(nuevo);

        JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente");

        if (loginFrame != null) {
            loginFrame.limpiarCampos();
            loginFrame.setVisible(true);
        }
        dispose();
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
