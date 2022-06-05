package es.ieslvareda.server.model;

import es.ieslvareda.model.AuthenticateData;
import es.ieslvareda.model.Empleado;
import es.ieslvareda.model.db.MyDataSource;
import es.ieslvareda.server.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImpEmpleadoService implements IEmpleadoService{
    @Override
    public Result<Empleado> authenticate(AuthenticateData authenticateData) {

        String email = authenticateData.getEmail();
        String passwd = authenticateData.getPasswd();
        String sql = "SELECT * FROM EMPLEADO WHERE " +
                "EMAIL=? AND PASSWORD=ENCRYPT_PASWD.encrypt_val(?)";

        try(Connection con = MyDataSource.getmyOracleDataSource().getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)){

            int pos = 0;
            pstmt.setString(++pos,email);
            pstmt.setString(++pos,passwd);

            ResultSet rs = pstmt.executeQuery();

            String nombre;
            String apellidos;
            String dni;

            if(rs.next()){
                nombre = rs.getString("NOMBRE");
                apellidos = rs.getString("APELLIDOS");
                dni = rs.getString("DNI");

                Empleado e = new Empleado(nombre, apellidos, dni, email);
                return new Result.Success<>(e);

            } else
                return new Result.Error(404,"Email y/o password incorrectos.");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result.Error(404, throwables.getMessage());
        }


    }
}
