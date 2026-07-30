package com.tarea4.model;

import java.io.Serializable;

// Clase abstracta que representa a una persona, y encapsula los atributos protegidos con getters y setters.

public abstract class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String nombre;
    protected String apellido;

    public Persona(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

// Abstraccion de cada subclase y override de la informacion que se muestra

    public abstract String mostrarInfo();

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
