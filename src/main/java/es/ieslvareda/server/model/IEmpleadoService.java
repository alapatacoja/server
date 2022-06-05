package es.ieslvareda.server.model;

import es.ieslvareda.model.AuthenticateData;
import es.ieslvareda.model.Empleado;
import es.ieslvareda.server.Result;

public interface IEmpleadoService {

    Result<Empleado> authenticate(AuthenticateData authenticateData);

}
