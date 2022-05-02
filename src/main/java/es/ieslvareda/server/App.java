package es.ieslvareda.server;

import es.ieslvareda.server.controllers.EmpleadoController;
import es.ieslvareda.server.controllers.PersonController;
import es.ieslvareda.server.model.JsonTransformer;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        get(API.Routes.ALL_PERSON, PersonController::getPersons, new JsonTransformer<>());
        get(API.Routes.PERSON, PersonController::getPerson,new JsonTransformer<>());
        post(API.Routes.PERSON, PersonController::addPerson, new JsonTransformer<>());


        // Oracle
        post(API.Routes.AUTHENTICATE, EmpleadoController::authenticate, new JsonTransformer<>());
    }
}


