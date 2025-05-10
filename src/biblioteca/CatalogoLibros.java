package biblioteca;

import java.util.*;

public class CatalogoLibros {
    // Aplicación del patrón Singleton: instancia única del catálogo
    private static CatalogoLibros instancia;

    private List<Libro> libros = new ArrayList<>();

    // Constructor privado evita instanciación directa
    private CatalogoLibros() {}

    // Método para obtener la única instancia (Singleton)
    public static CatalogoLibros getInstancia() {
        if (instancia == null) {
            instancia = new CatalogoLibros();
        }
        return instancia;
    }

    public void agregarLibro(ColeccionDeLibros coleccion) {
        // Composite: se agregan varios libros de una colección
        libros.addAll(coleccion.obtenerTodosLosLibros());
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public void eliminarLibro(Libro libro) {
        libros.remove(libro);
    }

    public List<Libro> obtenerLibrosDisponibles() {
        List<Libro> disponibles = new ArrayList<>();
        for (Libro l : libros) {
            if (l.estaDisponible()) {
                disponibles.add(l);
            }
        }
        return disponibles;
    }

    public List<Libro> obtenerTodosLosLibros() {
        return libros;
    }

    public void mostrarCatalogo() {
        for (Libro l : libros) {
            System.out.println("- " + l.getTitulo() + (l.estaDisponible() ? "" : " (prestado)"));
        }
    }
}
