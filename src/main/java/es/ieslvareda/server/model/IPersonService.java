package es.ieslvareda.server.model;

import es.ieslvareda.model.Person;
import es.ieslvareda.model.Result;

import java.util.List;

public interface IPersonService {

    List<Person> getAll();
    Result<Person> get(String dni);
    boolean update(Person person);
    boolean delete(String dni);
    Result<Person> add(Person person);

}
