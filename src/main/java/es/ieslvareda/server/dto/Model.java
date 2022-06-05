package es.ieslvareda.server.dto;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private static Model model;
    List<VehiculoDTO> vehiculos;
    List<LoginDTO> users;

    public Model() {
        vehiculos = new ArrayList<>();
        vehiculos.add(new CocheDTO("0000AAA", "BMW i3", "verde", 5, null, 45, null, 3, "preparado", 4, 4));
        vehiculos.add(new CocheDTO("0000AAB", "Fiat 500", "blanco", 5, null, 45, null, 3, "preparado", 4, 4));
        vehiculos.add(new CocheDTO("1111AAA", "BMW i3", "rojo", 5, null, 45, null, 3, "preparado", 4, 4));
        vehiculos.add(new CocheDTO("2222AAA", "Ligier JS50", "azul", 5, null, 45, null, 3, "preparado", 4, 4));
        vehiculos.add(new CocheDTO("2222AAB", "BMW i3", "verde", 5, null, 45, null, 3, "preparado", 4, 4));
        vehiculos.add(new CocheDTO("4444AAA", "BMW i3", "negro", 5, null, 45, null, 3, "preparado", 4, 4));
        vehiculos.add(new CocheDTO("9999AAA", "BMW i3", "amarillo", 5, null, 45, null, 3, "preparado", 4, 4));
        vehiculos.add(new MotoDTO("1234BBB", "Seat MO", "verde", 5, null, 45, null, 3, "preparado", 50, 65));
        vehiculos.add(new MotoDTO("9999BBB", "Seat MO", "verde", 5, null, 45, null, 3, "preparado", 50, 65));
        vehiculos.add(new MotoDTO("3991BBA", "Seat MO", "verde", 5, null, 45, null, 3, "preparado", 50, 65));
        vehiculos.add(new MotoDTO("3486BBB", "Seat MO", "verde", 5, null, 45, null, 3, "preparado", 50, 65));
        vehiculos.add(new BiciDTO("0000CCC", "RiverSide", "verde", 5, null, 45, null, 3, "preparado", "hibrida"));
        vehiculos.add(new BiciDTO("1111CCD", "NCM", "verde", 5, null, 45, null, 3, "preparado", "hibrida"));
        vehiculos.add(new BiciDTO("2222CCC", "RockRider", "verde", 5, null, 45, null, 3, "preparado", "montaña"));
        vehiculos.add(new PatineteDTO("4444DDD", "SSCYHT", "negro", 5, null, 45, null, 3, "preparado", 3, 10));
        vehiculos.add(new PatineteDTO("6666DDD", "SSCYHT", "negro", 5, null, 45, null, 3, "preparado", 3, 10));

        users = new ArrayList<>();
        users.add(new LoginDTO("alex", "uwu"));
        users.add(new LoginDTO("empleado2", "abc"));
        users.add(new LoginDTO("empleado3", "abc"));
        users.add(new LoginDTO("empleado4", "abc"));
    }

    public static Model getInstance(){
        if (model==null)
            model= new Model();
        return model;
    }

    public List<VehiculoDTO> getVehiculos() {
        return vehiculos;
    }

    public List<LoginDTO> getUsers() {
        return users;
    }
}
