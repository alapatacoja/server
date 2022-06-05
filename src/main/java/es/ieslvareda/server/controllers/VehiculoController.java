package es.ieslvareda.server.controllers;

import es.ieslvareda.model.Person;
import es.ieslvareda.server.dto.CocheDTO;
import es.ieslvareda.server.Result;
import es.ieslvareda.server.model.ImpCocheService;
import spark.Request;
import spark.Response;

import java.util.List;

public class VehiculoController {

    private static ImpCocheService service = new ImpCocheService();
    public static List<CocheDTO> getCoches(Request req, Response res){
        return service.getAll();
    }
    public static Result<Person> getCoche(Request req, Response res){
        // http://localhost:4567/person?dni=1111
        String dni = req.queryParams("dni");
        Result result = service.get(dni);
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }

}