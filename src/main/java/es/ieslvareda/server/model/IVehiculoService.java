package es.ieslvareda.server.model;

import es.ieslvareda.server.Result;

import java.util.List;

public interface IVehiculoService<T>{

    List<T> getAll();
    Result<T> get(String matricula);
    boolean update(T vehicle);
    boolean delete(String matricula);
    Result<T> add(T vehicle);

}
