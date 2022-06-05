package es.ieslvareda.server.api;

import es.ieslvareda.model.db.DatabaseAccess;
import es.ieslvareda.server.dto.CocheDTO;
import es.ieslvareda.server.model.ImpCocheService;

import java.util.List;
import java.util.Map;

public class Coche extends DatabaseAccess {

    private List<CocheDTO> coches;
    private ImpCocheService service;
    public Coche() {
        service = new ImpCocheService();
        coches = service.getAll();
    }

    @Override
    public Object select(Map params) {
        if (params.containsKey("matricula"))
            return service.get(params.get("matricula").toString());
        else return coches;
    }

    @Override
    public Object insert(Object data) {
        return service.add((CocheDTO) data);
    }

    @Override
    public Object delete(Map params) {
        return service.delete(params.get("matricula").toString());
    }

    @Override
    public Object update(Map params, Object data) {
        return service.update((CocheDTO) data);
    }
}
