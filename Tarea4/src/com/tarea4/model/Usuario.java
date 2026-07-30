package com.tarea4.model;

// Usuario del sistema, se aplica herencia de la clase "Persona" y polimorfismo con el override de la informacion a mostrar.

public class Usuario extends Persona {

    private static final long serialVersionUID = 1L;

    private String username;
    private String telefono;
    private String correo;
    private String password; // Cifrado de la contraseña

    public Usuario(String username, String nombre, String apellido,
                    String telefono, String correo, String password) {
        super(nombre, apellido);
        this.username = username;
        this.telefono = telefono;
        this.correo = correo;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validarPassword(String intento) {
        return password != null && password.equals(intento);
    }

    @Override
    public String mostrarInfo() {
        return String.format("Usuario: %s | Nombre: %s %s | Tel: %s | Correo: %s",
                username, getNombre(), getApellido(), telefono, correo);
    }

    @Override
    public String toString() {
        return mostrarInfo();
    }
}
