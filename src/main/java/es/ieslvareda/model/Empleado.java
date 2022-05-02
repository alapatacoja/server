package es.ieslvareda.model;

public class Empleado {

    private String nombre;
    private String apellidos;
    private String dni;
    private String email;

    public Empleado(String nombre) {
        this.nombre = nombre;
    }

    public Empleado(String nombre, String apellidos, String dni, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
