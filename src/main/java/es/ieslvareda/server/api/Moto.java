package es.ieslvareda.server.api;

import es.ieslvareda.model.db.DatabaseAccess;
import es.ieslvareda.server.dto.MotoDTO;
import es.ieslvareda.server.model.ImpMotoService;

import java.util.List;
import java.util.Map;

public class Moto extends DatabaseAccess {

    private List<MotoDTO> motos;
    private ImpMotoService service;

    public Moto() {
        service = new ImpMotoService();
        motos = service.getAll();
    }

    @Override
    public Object select(Map params) {
        if (params.containsKey("matricula"))
            return service.get(String.valueOf(params.get("matricula")));
        else return motos;
    }

    @Override
    public Object insert(Object data) {
        return service.add((MotoDTO) data);
    }

    @Override
    public Object delete(Map params) {
        return service.delete(params.get("matricula").toString());
    }

    @Override
    public Object update(Map params, Object data) {
        return service.update((MotoDTO) data);
    }
}