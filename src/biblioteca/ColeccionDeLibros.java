package biblioteca;

import java.util.*;

public class ColeccionDeLibros {
    private String nombre;
    private List<Libro> libros = new ArrayList<>();

    public ColeccionDeLibros(String nombre) {
        this.nombre = nombre;
    }

    public void agregar(Libro libro) {
        libros.add(libro);
    }

    // Composite: colección actúa como contenedor de objetos Libro
    public List<Libro> obtenerTodosLosLibros() {
        return libros;
    }
}
