# Tarea 4 – Login de Usuarios (Java + Swing)

Aplicacion de escritorio en Java que implementa un sistema de Login y
Registro de usuarios, según el mandato de la tarea.

## Cómo ejecutar
Requiere **JDK 8 o superior** instalado.

### Opcion A: desde la terminal
```bash
cd src
javac -d ../bin com/tarea4/*.java com/tarea4/model/*.java com/tarea4/repository/*.java com/tarea4/factory/*.java com/tarea4/ui/*.java
cd ../bin
java com.tarea4.Main
```

### Opcion B: desde Eclipse / IntelliJ
1. Crear un proyecto Java nuevo.
2. Copiar la carpeta `src` completa dentro del proyecto (mantiene los paquetes `com.tarea4.*`).
3. Ejecutar la clase `com.tarea4.Main`.

La aplicacion guarda los usuarios en un archivo `usuarios.dat` que se crea
automáticamente en la carpeta desde donde se ejecuta, así los datos
persisten entre ejecuciones sin necesidad de instalar MySQL.

## Estructura del proyecto

```
src/com/tarea4/
├── Main.java                     Punto de entrada
├── model/
│   ├── Persona.java              Clase abstracta (Abstraccion)
│   └── Usuario.java               Hereda de Persona (Herencia/Polimorfismo)
├── repository/
│   └── UsuarioRepository.java     Patron Singleton + persistencia en archivo
├── factory/
│   └── UsuarioFactory.java        Patron Fabrica (Factory Method)
└── ui/
    ├── LoginFrame.java             Pantalla de Login
    ├── RegistroFrame.java          Pantalla de Registro
    ├── PrincipalFrame.java         Listado de usuarios + Nuevo/Actualizar/Eliminar/Cerrar sesion
    └── EditarUsuarioDialog.java    Dialogo para actualizar un usuario
```

## Pilares de POO aplicados

- **Abstraccion**: `Persona` es una clase abstracta con el metodo
  `mostrarInfo()` que obliga a cada subclase a definir como presenta su
  informacion.
- **Encapsulamiento**: todos los atributos son `private`/`protected` con
  getters y setters; la contraseña nunca se expone directamente fuera de
  `Usuario`.
- **Herencia**: `Usuario extends Persona`.
- **Polimorfismo**: `Usuario` sobrescribe `mostrarInfo()` y `toString()`.

## Patrones de diseño aplicados

- **Singleton**: `UsuarioRepository.getInstance()` garantiza una única
  instancia que administra la lista de usuarios y su persistencia.
- **Fábrica (Factory Method)**: `UsuarioFactory.crearUsuario(...)`
  centraliza la creación de objetos `Usuario`.






