package es.ieslvareda.server.api;

import es.ieslvareda.model.db.DatabaseAccess;
import es.ieslvareda.server.dto.VehiculoDTO;
import es.ieslvareda.server.model.ImplVehiculoService;

import java.util.List;
import java.util.Map;

public class Vehiculo extends DatabaseAccess {

    ImplVehiculoService service = new ImplVehiculoService();
    List lista;

    public Vehiculo() {
        lista = service.getAll();
    }

    @Override
    public Object select(Map params) {
        if (params.containsKey("matricula"))
            return service.get(String.valueOf(params.get("matricula")));
        else return lista;
    }

    @Override
    public Object insert(Object data) {
        return service.add((VehiculoDTO) data);
    }

    @Override
    public Object delete(Map params) {
        return service.delete(params.get("matricula").toString());
    }

    @Override
    public Object update(Map params, Object data) {
        return service.update((VehiculoDTO) data);
    }
}
