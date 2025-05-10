package biblioteca;

public class Usuario implements Observador {
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // Implementación del método Observer
    @Override
    public void notificar(String mensaje) {
        System.out.println("Notificación para " + nombre + ": " + mensaje);
    }

    public void recibirNotificaciones() {
        System.out.println(nombre + " revisó sus notificaciones.");
    }
}
