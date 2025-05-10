package biblioteca;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Libro {
    private String titulo;
    private boolean disponible = true;
    private List<Observador> observadores = new ArrayList<>();
    private Date fechaDevolucion; // ← NUEVO: Fecha límite de devolución

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
            notificar("El libro \"" + titulo + "\" ha sido prestado a " + usuario.getNombre());
        } else {
            System.out.println("El libro no está disponible.");
        }
    }

    public void devolver() {
        disponible = true;
        notificar("El libro \"" + titulo + "\" ha sido devuelto.");
    }

    public void suscribir(Observador observador) {
        observadores.add(observador);
    }

    private void notificar(String mensaje) {
        for (Observador o : observadores) {
            o.notificar(mensaje);
        }
    }

    // ← NUEVOS MÉTODOS ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    public void setFechaDevolucion(Date fecha) {
        this.fechaDevolucion = fecha;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }
}
