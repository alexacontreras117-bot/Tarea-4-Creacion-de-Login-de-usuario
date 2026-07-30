package com.tarea4;

import com.tarea4.ui.LoginFrame;

import javax.swing.SwingUtilities;

// Ejecucion de la aplicacion.
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
