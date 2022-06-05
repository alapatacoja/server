package es.ieslvareda.server.dto;

public class MotoDTO extends VehiculoDTO {

    private int velocidadmax;
    private int cilindrada;

    public MotoDTO(String matricula, String marca, String color, int preciohora, String descripcion, int bateria, String fecha, int carnettipo, String estado, int velocidadmax, int cilindrada) {
        super(matricula, marca, color, preciohora, descripcion, bateria, fecha, carnettipo, estado, "moto");
        this.velocidadmax = velocidadmax;
        this.cilindrada = cilindrada;
    }

    public int getVelocidadmax() {
        return velocidadmax;
    }

    public void setVelocidadmax(int velocidadmax) {
        this.velocidadmax = velocidadmax;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }
}
