package es.ieslvareda.server.dto;

public class PatineteDTO extends VehiculoDTO {

    private int tamanio;
    private int ruedas;

    public PatineteDTO(String matricula, String marca, String color, int preciohora, String descripcion, int bateria, String fecha, int carnettipo, String estado, int tamanio, int ruedas) {
        super(matricula, marca, color, preciohora, descripcion, bateria, fecha, carnettipo, estado, "patinete");
        this.tamanio = tamanio;
        this.ruedas = ruedas;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public int getRuedas() {
        return ruedas;
    }

    public void setRuedas(int ruedas) {
        this.ruedas = ruedas;
    }
}
