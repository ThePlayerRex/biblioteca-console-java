import biblioteca.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class BibliotecaApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Uso del patrón Singleton para obtener el catálogo único
        CatalogoLibros catalogo = CatalogoLibros.getInstancia();

        // Crear libros y colección (Composite)
        Libro l1 = new Libro("El Principito");
        Libro l2 = new Libro("1984");

        ColeccionDeLibros coleccion = new ColeccionDeLibros("Colección Ciencia Ficción");
        coleccion.agregar(l1);
        coleccion.agregar(l2);
        catalogo.agregarLibro(coleccion); // Composite: agregando colección

        Map<Libro, Usuario> prestamosActivos = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- Menú Biblioteca ---");
            System.out.println("1. Ver catálogo");
            System.out.println("2. Prestar un libro");
            System.out.println("3. Recibir libro prestado");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("\n--- Catálogo actual ---");
                    catalogo.mostrarCatalogo();
                    break;

                case "2":
                    List<Libro> disponibles = catalogo.obtenerLibrosDisponibles();
                    for (int i = 0; i < disponibles.size(); i++) {
                        System.out.println((i + 1) + ". " + disponibles.get(i).getTitulo());
                    }

                    if (disponibles.isEmpty()) {
                        System.out.println("No hay libros disponibles.");
                        break;
                    }

                    System.out.print("Seleccione un libro por número: ");
                    try {
                        int seleccion = Integer.parseInt(scanner.nextLine()) - 1;
                        if (seleccion < 0 || seleccion >= disponibles.size()) {
                            System.out.println("Selección inválida.");
                            break;
                        }

                        System.out.print("Ingrese el nombre del usuario: ");
                        String nombreUsuario = scanner.nextLine();
                        Usuario nuevoUsuario = new Usuario(nombreUsuario);

                        Libro libroSeleccionado = disponibles.get(seleccion);
                        libroSeleccionado.suscribir(nuevoUsuario); // Observer

                        Date fechaPrestamo = new Date();
                        Calendar calendario = Calendar.getInstance();
                        calendario.setTime(fechaPrestamo);
                        calendario.add(Calendar.DAY_OF_YEAR, 3);
                        Date fechaDevolucion = calendario.getTime();

                        System.out.println("\nLibro prestado a " + nuevoUsuario.getNombre());
                        System.out.println("Fecha de préstamo: " + sdf.format(fechaPrestamo));
                        System.out.println("Fecha estimada de devolución: " + sdf.format(fechaDevolucion));

                        libroSeleccionado.setFechaDevolucion(fechaDevolucion);
                        libroSeleccionado.prestarA(nuevoUsuario);
                        catalogo.eliminarLibro(libroSeleccionado);
                        prestamosActivos.put(libroSeleccionado, nuevoUsuario);

                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida.");
                    }
                    break;

                case "3":
                    if (prestamosActivos.isEmpty()) {
                        System.out.println("No hay libros prestados.");
                        break;
                    }

                    List<Libro> librosPrestados = new ArrayList<>(prestamosActivos.keySet());
                    for (int i = 0; i < librosPrestados.size(); i++) {
                        System.out.println((i + 1) + ". " + librosPrestados.get(i).getTitulo() +
                                " (prestado a " + prestamosActivos.get(librosPrestados.get(i)).getNombre() + ")");
                    }

                    System.out.print("Seleccione el libro a recibir: ");
                    try {
                        int seleccion = Integer.parseInt(scanner.nextLine()) - 1;
                        if (seleccion < 0 || seleccion >= librosPrestados.size()) {
                            System.out.println("Selección inválida.");
                            break;
                        }

                        Libro libroDevuelto = librosPrestados.get(seleccion);
                        Usuario usuario = prestamosActivos.get(libroDevuelto);

                        Date fechaActual = new Date();
                        Date vencimiento = libroDevuelto.getFechaDevolucion();

                        System.out.println("Recibiendo el libro \"" + libroDevuelto.getTitulo() + "\" de " + usuario.getNombre());
                        System.out.println("Fecha actual: " + sdf.format(fechaActual));
                        System.out.println("Fecha de vencimiento: " + sdf.format(vencimiento));

                        if (fechaActual.after(vencimiento)) {
                            System.out.println("📌 El libro fue entregado FUERA de plazo. Se aplicará una multa.");
                        } else {
                            System.out.println("✅ El libro fue entregado a tiempo.");
                        }

                        libroDevuelto.setFechaDevolucion(null);
                        libroDevuelto.devolver(); // Observer
                        catalogo.agregarLibro(libroDevuelto);
                        prestamosActivos.remove(libroDevuelto);

                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida.");
                    }
                    break;

                case "4":
                    salir = true;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }

        scanner.close();
    }
}
