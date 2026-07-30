package com.tarea4.repository;

import com.tarea4.model.Usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

// Repositorio de usuarios para la crecion de un archivo binario donde se alamacenaran los usuarios ya agregados sin necesidad de una base de datos con el uso de "Singleton"

public class UsuarioRepository {

    private static UsuarioRepository instancia;
    private List<Usuario> usuarios;
    private static final String ARCHIVO = "usuarios.dat";

    private UsuarioRepository() {
        usuarios = new ArrayList<>();
        cargarDesdeArchivo();
    }

    public static synchronized UsuarioRepository getInstance() {
        if (instancia == null) {
            instancia = new UsuarioRepository();
        }
        return instancia;
    }

    public List<Usuario> obtenerTodos() {
        return new ArrayList<>(usuarios);
    }

    public boolean existeUsername(String username) {
        return buscarPorUsername(username) != null;
    }

    public Usuario buscarPorUsername(String username) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null;
    }

    public void agregar(Usuario u) {
        usuarios.add(u);
        guardarEnArchivo();
    }

    public boolean actualizar(String usernameOriginal, Usuario datosNuevos) {
        Usuario existente = buscarPorUsername(usernameOriginal);
        if (existente == null) {
            return false;
        }
        existente.setUsername(datosNuevos.getUsername());
        existente.setNombre(datosNuevos.getNombre());
        existente.setApellido(datosNuevos.getApellido());
        existente.setTelefono(datosNuevos.getTelefono());
        existente.setCorreo(datosNuevos.getCorreo());
        if (datosNuevos.getPassword() != null && !datosNuevos.getPassword().isEmpty()) {
            existente.setPassword(datosNuevos.getPassword());
        }
        guardarEnArchivo();
        return true;
    }

    public boolean eliminar(String username) {
        Usuario u = buscarPorUsername(username);
        if (u == null) {
            return false;
        }
        usuarios.remove(u);
        guardarEnArchivo();
        return true;
    }

    @SuppressWarnings("unchecked")
    private void cargarDesdeArchivo() {
        File f = new File(ARCHIVO);
        if (!f.exists()) {
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            usuarios = (List<Usuario>) ois.readObject();
        } catch (Exception e) {
            usuarios = new ArrayList<>();
        }
    }

    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
