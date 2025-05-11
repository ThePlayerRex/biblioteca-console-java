# Biblioteca Console Java 📚

Aplicación de consola en Java para la gestión de una biblioteca. Permite realizar préstamos, devoluciones y recibir notificaciones cuando cambia el estado de los libros.

---

## 🚀 Funcionalidades

- 📖 Visualización del catálogo de libros
- 📥 Préstamo de libros a usuarios
- 📤 Devolución de libros
- 🔔 Notificaciones automáticas para usuarios observadores

---

## 🛠️ Tecnologías utilizadas

- Java 21 (OpenJDK 21.0.7 LTS)
- Patrones de diseño: Singleton, Composite y Observer

---

## 📁 Estructura del Proyecto
## 📂 src/ → Código fuente en Java
## 📂 bin/ → Archivos compilados (.class)

---

## ▶️ Cómo ejecutar

Asegúrate de tener Java instalado.

javac -d bin src/BibliotecaApp.java
java -cp bin BibliotecaApp

## Patrones de diseño usados
Singleton: En CatalogoLibros, para asegurar una única instancia del catálogo.

Composite: En ColeccionDeLibros y CatalogoLibros, para manejar jerarquías de libros.

Observer: Entre Libro y Usuario, para notificar cambios en el estado de los libros.

##  Autor

- [ThePlayerRex](https://github.com/ThePlayerRex)

##  Estado del proyecto

✅ Finalizado — versión estable para consola.

