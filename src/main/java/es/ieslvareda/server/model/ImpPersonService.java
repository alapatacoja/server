package es.ieslvareda.server.model;

import es.ieslvareda.model.MyDataSource;
import es.ieslvareda.model.Person;
import es.ieslvareda.model.Result;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpPersonService implements IPersonService {

 

    @Override
    public List<Person> getAll() {

        List<Person> personList = new ArrayList<>();
        DataSource dataSource = MyDataSource.getMyMariaDBDataSource();

        try(Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from person")){

            String dni;
            String nombre;
            String apellidos;
            int edad;

            while(resultSet.next()){
                dni = resultSet.getString("dni");
                nombre = resultSet.getString("nombre");
                apellidos = resultSet.getString("apellidos");
                edad = resultSet.getInt("edad");

                personList.add(new Person(dni,nombre,apellidos,edad));

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return personList;
    }

    @Override
    public Result<Person> get(String dni) {


        DataSource dataSource = MyDataSource.getMyMariaDBDataSource();

        try(Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from person where dni='"+dni+"'")){

            String nombre;
            String apellidos;
            int edad;

            if(resultSet.next()){

                nombre = resultSet.getString("nombre");
                apellidos = resultSet.getString("apellidos");
                edad = resultSet.getInt("edad");

                Person person = new Person(dni,nombre,apellidos,edad);

                return new Result.Success<>(person);

            } else  {
                return new Result.Error(404,"No existe ninguna persona con el dni " + dni);
            }


        } catch (SQLSyntaxErrorException sqlee) {
            return new Result.Error(sqlee.getErrorCode(),"Error de acceso a la BD. Consulte con el administrador.");
        }catch (SQLException throwables) {
            return new Result.Error(throwables.getErrorCode(),throwables.getMessage());
        }catch (Exception e) {
            return new Result.Error(1,e.getMessage());
        }
    }

    @Override
    public boolean update(Person person) {
        return false;
    }

    @Override
    public boolean delete(String dni) {
        return false;
    }

    @Override
    public Result<Person> add(Person person) {
        DataSource ds = MyDataSource.getMyMariaDBDataSource();

        try(Connection con = ds.getConnection();
            Statement statement = con.createStatement();) {
            String sql = "INSERT INTO " +
                    "person VALUES ('"+person.getDni()+"','"+person.getNombre()+"','"+person.getApellidos()+"',"+person.getEdad()+")";

            int count = statement.executeUpdate(sql);
            if(count==1)
                return new Result.Success<>(person);
            else
                return new Result.Error(404,"No se ha podido añadir la persona");

        } catch (SQLException throwables) {
            return new Result.Error(404,throwables.getMessage());
        }
    }
}
