package es.ieslvareda.server.dto;

public class VehiculoDTO {

    private String matricula;
    private String marca;
    private String color;
    private int preciohora;
    private String descripcion;
    private int bateria;
    private String fecha;
    private int carnettipo;
    private String estado;
    private String tipovehiculo;

    public VehiculoDTO(String matricula, String marca, String color, int preciohora, String descripcion, int bateria, String fecha, int carnettipo, String estado, String tipovehiculo) {
        this.matricula = matricula;
        this.marca = marca;
        this.color = color;
        this.preciohora = preciohora;
        this.descripcion = descripcion;
        this.bateria = bateria;
        this.fecha = fecha;
        this.carnettipo = carnettipo;
        this.estado = estado;
        this.tipovehiculo = tipovehiculo;
    }

    public String getTipovehiculo() {
        return tipovehiculo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPreciohora() {
        return preciohora;
    }

    public void setPreciohora(int preciohora) {
        this.preciohora = preciohora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCarnettipo() {
        return carnettipo;
    }

    public void setCarnettipo(int carnettipo) {
        this.carnettipo = carnettipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "VehiculoDTO{" +
                "matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", color='" + color + '\'' +
                ", preciohora=" + preciohora +
                ", descripcion='" + descripcion + '\'' +
                ", bateria=" + bateria +
                ", fecha=" + fecha +
                ", carnettipo=" + carnettipo +
                ", estado='" + estado + '\'' +
                '}';
    }
}
