package com.tarea4.ui;

import com.tarea4.model.Usuario;
import com.tarea4.repository.UsuarioRepository;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

// Actualizar datos de un ususrio ya existente (Funcion).

public class EditarUsuarioDialog extends JDialog {

    private JTextField txtNombre, txtApellido, txtUsername, txtTelefono, txtCorreo;
    private JPasswordField txtPassword;
    private final String usernameOriginal;

    public EditarUsuarioDialog(Frame parent, Usuario usuario) {
        super(parent, "Actualizar Usuario", true);
        this.usernameOriginal = usuario.getUsername();
        setSize(380, 420);
        setLocationRelativeTo(parent);
        initComponents(usuario);
    }

    private void initComponents(Usuario usuario) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int fila = 0;
        gbc.gridy = fila;
        gbc.gridx = 0;
        panel.add(new JLabel("Nombre:"), gbc);
        txtNombre = new JTextField(usuario.getNombre(), 15);
        gbc.gridx = 1;
        panel.add(txtNombre, gbc);
        fila++;

        gbc.gridy = fila;
        gbc.gridx = 0;
        panel.add(new JLabel("Apellido:"), gbc);
        txtApellido = new JTextField(usuario.getApellido(), 15);
        gbc.gridx = 1;
        panel.add(txtApellido, gbc);
        fila++;

        gbc.gridy = fila;
        gbc.gridx = 0;
        panel.add(new JLabel("Usuario:"), gbc);
        txtUsername = new JTextField(usuario.getUsername(), 15);
        gbc.gridx = 1;
        panel.add(txtUsername, gbc);
        fila++;

        gbc.gridy = fila;
        gbc.gridx = 0;
        panel.add(new JLabel("Telefono:"), gbc);
        txtTelefono = new JTextField(usuario.getTelefono(), 15);
        gbc.gridx = 1;
        panel.add(txtTelefono, gbc);
        fila++;

        gbc.gridy = fila;
        gbc.gridx = 0;
        panel.add(new JLabel("Correo:"), gbc);
        txtCorreo = new JTextField(usuario.getCorreo(), 15);
        gbc.gridx = 1;
        panel.add(txtCorreo, gbc);
        fila++;

        gbc.gridy = fila;
        gbc.gridx = 0;
        panel.add(new JLabel("Nueva contraseña:"), gbc);
        txtPassword = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(txtPassword, gbc);
        fila++;

        JLabel nota = new JLabel("(dejar en blanco para no cambiarla)");
        nota.setFont(new Font("Arial", Font.ITALIC, 10));
        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.gridwidth = 2;
        panel.add(nota, gbc);
        fila++;

        JButton btnGuardar = new JButton("Guardar Cambios");
        gbc.gridy = fila;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(btnGuardar, gbc);

        add(panel);

        btnGuardar.addActionListener(e -> guardar());
    }

    private void guardar() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String username = txtUsername.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (nombre.isEmpty() || apellido.isEmpty() || username.isEmpty()
                || telefono.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Todos los campos (excepto contraseña) son obligatorios",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario datosNuevos = new Usuario(username, nombre, apellido, telefono, correo,
                password.isEmpty() ? null : password);

        boolean ok = UsuarioRepository.getInstance().actualizar(usernameOriginal, datosNuevos);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el usuario",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
