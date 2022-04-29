package es.ieslvareda.server;

import es.ieslvareda.server.controllers.PersonController;
import es.ieslvareda.server.model.JsonTransformer;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        get(API.ALL_PERSON, PersonController::getPersons, new JsonTransformer<>());
        get(API.PERSON, PersonController::getPerson,new JsonTransformer<>());
        post(API.PERSON, PersonController::addPerson, new JsonTransformer<>());
    }
}


