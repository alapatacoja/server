package es.ieslvareda.server.api;

import es.ieslvareda.model.db.DatabaseAccess;
import es.ieslvareda.server.dto.PatineteDTO;
import es.ieslvareda.server.model.ImpPatineteService;

import java.util.List;
import java.util.Map;

public class Patinete extends DatabaseAccess {
    private List<PatineteDTO> coches;
    private ImpPatineteService service;
    public Patinete() {
        service = new ImpPatineteService();
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
        return service.add((PatineteDTO) data);
    }

    @Override
    public Object delete(Map params) {
        return service.delete(params.get("matricula").toString());
    }

    @Override
    public Object update(Map params, Object data) {
        return service.update((PatineteDTO) data);
    }
}
