package es.ieslvareda.server.controllers;

import es.ieslvareda.model.AuthenticateData;
import es.ieslvareda.model.Empleado;
import es.ieslvareda.server.Result;
import es.ieslvareda.server.model.IEmpleadoService;
import es.ieslvareda.server.model.ImpEmpleadoService;
import es.ieslvareda.server.JsonTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

public class EmpleadoController {

    static Logger logger = LoggerFactory.getLogger(EmpleadoController.class);
    static JsonTransformer<Empleado> jse = new JsonTransformer<>();
    static IEmpleadoService service = new ImpEmpleadoService();


    public static Result<Empleado> authenticate(Request request, Response response) {
        logger.info("Autenticando ");
        String body = request.body();

        JsonTransformer<AuthenticateData> jst = new JsonTransformer<>();
        AuthenticateData ad = jst.getObjet(body,AuthenticateData.class);

        Result<Empleado> result = service.authenticate(ad);

        if(result instanceof Result.Success){
            response.status(200);
        } else {
            response.status(404);
        }
        return result;
    }
}
