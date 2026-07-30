package com.tarea4.factory;

import com.tarea4.model.Usuario;


// Uso de "Fabrica" para centraciozar la creacion de los objetos (el orden y pocision que van a tener en la aplicacion) y para que el resto de la aplicacion no dependa directamente del constructor de la clase.

public class UsuarioFactory {

    public static Usuario crearUsuario(String username, String nombre, String apellido,
                                        String telefono, String correo, String password) {
        return new Usuario(username, nombre, apellido, telefono, correo, password);
    }
}
