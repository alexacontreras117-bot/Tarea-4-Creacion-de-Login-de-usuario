package com.tarea4.ui;

import com.tarea4.model.Usuario;
import com.tarea4.repository.UsuarioRepository;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

// Pantalla principal (Una vez se inicie session) lista de usuarios con la funcion de eliminar, crear nuevo, actualizar y cerrar session.
public class PrincipalFrame extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    public PrincipalFrame() {
        setTitle("Clientes Registrados");
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        cargarDatos();
    }

    private void initComponents() {
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnNuevo = new JButton("Nuevo");
        panelSuperior.add(btnNuevo);

        modelo = new DefaultTableModel(
                new Object[]{"Nombre", "Apellido", "Telefono", "Correo electronico", "Usuario"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tabla = new JTable(modelo);

        JPanel panelInferior = new JPanel(new FlowLayout());
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCerrarSesion = new JButton("Cerrar Seccion");
        panelInferior.add(btnActualizar);
        panelInferior.add(btnEliminar);
        panelInferior.add(btnCerrarSesion);

        setLayout(new BorderLayout());
        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        btnNuevo.addActionListener(e -> {
            RegistroFrame rf = new RegistroFrame(null);
            rf.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent ev) {
                    cargarDatos();
                }
            });
            rf.setVisible(true);
        });

        btnActualizar.addActionListener(e -> actualizarSeleccionado());
        btnEliminar.addActionListener(e -> eliminarSeleccionado());
        btnCerrarSesion.addActionListener(e -> cerrarSesion());
    }

    private void cargarDatos() {
        modelo.setRowCount(0);
        List<Usuario> usuarios = UsuarioRepository.getInstance().obtenerTodos();
        for (Usuario u : usuarios) {
            modelo.addRow(new Object[]{
                    u.getNombre(), u.getApellido(), u.getTelefono(), u.getCorreo(), u.getUsername()
            });
        }
    }

// Opcion de actualizar el usuario ya creado dentro de la lista.
    private void actualizarSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para actualizar");
            return;
        }
        String username = (String) modelo.getValueAt(fila, 4);
        Usuario u = UsuarioRepository.getInstance().buscarPorUsername(username);
        if (u == null) {
            return;
        }

        EditarUsuarioDialog dialog = new EditarUsuarioDialog(this, u);
        dialog.setVisible(true);
        cargarDatos(); 
    }

// Opcion para eliminar el usuario ya creado.
    private void eliminarSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para eliminar");
            return;
        }
        String username = (String) modelo.getValueAt(fila, 4);
        int confirm = JOptionPane.showConfirmDialog(this,
                "Seguro que desea eliminar a " + username + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            UsuarioRepository.getInstance().eliminar(username);
            cargarDatos(); 
        }
    }

// Opcion para cerrar session dentro de la lista de usuarios.
    private void cerrarSesion() {
        dispose();
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
