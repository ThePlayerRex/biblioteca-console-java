package biblioteca;

import java.util.*;

public class Libro {
    private String titulo;
    private boolean disponible = true;
    private List<Observador> observadores = new ArrayList<>();
    private Date fechaDevolucion;

    public Libro(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public void prestarA(Usuario usuario) {
        if (disponible) {
            disponible = false;
            // Observer: notificación de préstamo
            notificar("El libro \"" + titulo + "\" ha sido prestado a " + usuario.getNombre());
        } else {
            System.out.println("El libro no está disponible.");
        }
    }

    public void devolver() {
        disponible = true;
        // Observer: notificación de devolución
        notificar("El libro \"" + titulo + "\" ha sido devuelto.");
    }

    // Método para suscribirse como observador
    public void suscribir(Observador observador) {
        observadores.add(observador);
    }

    // Método privado para notificar a todos los observadores
    private void notificar(String mensaje) {
        for (Observador o : observadores) {
            o.notificar(mensaje);
        }
    }

    public void setFechaDevolucion(Date fecha) {
        this.fechaDevolucion = fecha;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }
}
