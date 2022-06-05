package es.ieslvareda.server.api;

import es.ieslvareda.model.db.DatabaseAccess;
import es.ieslvareda.server.dto.BiciDTO;
import es.ieslvareda.server.model.ImpBiciService;

import java.util.List;
import java.util.Map;

public class Bici extends DatabaseAccess {
    private List<BiciDTO> coches;
    private ImpBiciService service;
    public Bici() {
        service = new ImpBiciService();
        coches = service.getAll();
    }

    @Override
    public Object select(Map params) {
        if (params.containsKey("matricula"))
            return service.get(String.valueOf(params.get("matricula")));
        else return coches;
    }

    @Override
    public Object insert(Object data) {
        return service.add((BiciDTO) data);
    }

    @Override
    public Object delete(Map params) {
        return service.delete(params.get("matricula").toString());
    }

    @Override
    public Object update(Map params, Object data) {
        return service.update((BiciDTO) data);
    }
}
