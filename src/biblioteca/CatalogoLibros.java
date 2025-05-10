package biblioteca;

import java.util.*;

public class CatalogoLibros {
    private static CatalogoLibros instancia;
    private List<Libro> libros = new ArrayList<>();

    private CatalogoLibros() {}

    public static CatalogoLibros getInstancia() {
        if (instancia == null) {
            instancia = new CatalogoLibros();
        }
        return instancia;
    }

    public void agregarLibro(ColeccionDeLibros coleccion) {
        libros.addAll(coleccion.obtenerTodosLosLibros());
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
    
    public void mostrarCatalogo() {
        for (Libro l : libros) {
            System.out.println("- " + l.getTitulo() + (l.estaDisponible() ? "" : " (prestado)"));
        }
    }    

    public List<Libro> obtenerTodosLosLibros() {
        return libros;
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }
    
}
