package es.ieslvareda.model;

import com.mysql.cj.jdbc.MysqlDataSource;
import es.ieslvareda.properties.MyConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Model {

    public List<Person> getPersons(){

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

    public List<Empleado> getEmpleados(){

        List<Empleado> empleados = new ArrayList<>();
        try(Connection con = MyDataSource.getMyOracleDataSource().getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT NOMBRE FROM EMPLEADO")) {

            int pos;
            while (rs.next()){
                empleados.add(new Empleado(rs.getString(1)));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return empleados;
    }
}
