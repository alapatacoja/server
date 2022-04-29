package es.ieslvareda.server.controllers;

import es.ieslvareda.model.Person;
import es.ieslvareda.model.Result;
import es.ieslvareda.server.model.IPersonService;
import es.ieslvareda.server.model.ImpPersonService;
import es.ieslvareda.server.model.JsonTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.util.List;

public class PersonController {

    static Logger logger = LoggerFactory.getLogger(PersonController.class);

    private static IPersonService service = new ImpPersonService();
    private static JsonTransformer<Person> jsonTransformer = new JsonTransformer<>();

    public static List<Person> getPersons(Request req, Response res){
        logger.info("Receiving request for all persons");
        return service.getAll();
    }
    public static Result<Person> getPerson(Request req, Response res){
        // http://localhost:4567/person?dni=1111
        String dni = req.queryParams("dni");
        logger.info("Get person with dni= " + dni);
        Result result = service.get(dni);
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }

    public static Result<Person> addPerson(Request request, Response res) {
        logger.info("Add new person");

        String body = request.body();
        Person p = jsonTransformer.getObjet(body,Person.class);
        Result<Person> result = service.add(p);

        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }

        return result;
    }
}
