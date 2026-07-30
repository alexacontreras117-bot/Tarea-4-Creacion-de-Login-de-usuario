package com.tarea4.ui;

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
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

// Pantalla de Login.
public class LoginFrame extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;

    public LoginFrame() {
        setTitle("Login");
        setSize(360, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

// Cuerpo de la pantalla de Login.
    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(174, 198, 231));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("LOGIN", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(new JLabel("Usuario:"), gbc);
        txtUsuario = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtUsuario, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(new JLabel("Contraseña:"), gbc);
        txtPassword = new JPasswordField(15); // cifrar la contraseña.
        gbc.gridx = 1;
        panel.add(txtPassword, gbc);

        JButton btnEntrar = new JButton("Entrar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(btnEntrar, gbc);

        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBorderPainted(false);
        btnRegistrarse.setContentAreaFilled(false);
        btnRegistrarse.setForeground(Color.BLUE);
        gbc.gridy = 4;
        panel.add(btnRegistrarse, gbc);

        add(panel);

        btnEntrar.addActionListener(e -> intentarLogin());
        btnRegistrarse.addActionListener(e -> {
            new RegistroFrame(this).setVisible(true);
            setVisible(false);
        });

        getRootPane().setDefaultButton(btnEntrar);
    }

// Obligacion de cada campo.
    private void intentarLogin() {
        String usuario = txtUsuario.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe ingresar su usuario y contraseña, si no esta registrado debe registrarse",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario u = UsuarioRepository.getInstance().buscarPorUsername(usuario);
        if (u == null || !u.validarPassword(password)) {
            JOptionPane.showMessageDialog(this,
                    "Usuario o contraseña incorrectos",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.dispose();
        SwingUtilities.invokeLater(() -> new PrincipalFrame().setVisible(true));
    }

    public void limpiarCampos() {
        txtUsuario.setText("");
        txtPassword.setText("");
    }
}
