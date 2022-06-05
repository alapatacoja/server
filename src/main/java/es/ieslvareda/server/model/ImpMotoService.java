package es.ieslvareda.server.model;


import es.ieslvareda.model.db.MyDataSource;
import es.ieslvareda.server.dto.MotoDTO;
import es.ieslvareda.server.Extras;
import es.ieslvareda.server.Result;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpMotoService implements IVehiculoService<MotoDTO>{
    @Override
    public List<MotoDTO> getAll() {
        List<MotoDTO> vehiculos = new ArrayList<>();
        DataSource ds = MyDataSource.getmyOracleDataSource();
        String sql = "select * from moto c join vehiculo v on c.matricula=v.matricula";
        try(Connection cn = ds.getConnection(); Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql)) {

            String matricula;
            String marca;
            String color;
            int preciohora;
            String descripcion;
            int bateria;
            Date fecha;
            int carnettipo;
            String estado;
            int cilindrada, velocidadmax;

            while(rs.next()){
                matricula = rs.getString("matricula");
                marca = rs.getString("marca");
                color = rs.getString("color");
                preciohora = rs.getInt("preciohora");
                descripcion = rs.getString("descripcion");
                bateria = rs.getInt("bateria");
                fecha = rs.getDate("fechaadq");
                estado = rs.getString("estado");
                carnettipo = rs.getInt("idcarnet");
                cilindrada = rs.getInt("cilindrada");
                velocidadmax = rs.getInt("velocidadmax");

                vehiculos.add(new MotoDTO(matricula, marca, color, preciohora, descripcion, bateria, String.valueOf(fecha), carnettipo, estado, cilindrada, velocidadmax));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehiculos;
    }

    @Override
    public Result<MotoDTO> get(String matricula) {
        DataSource ds = MyDataSource.getmyOracleDataSource();

        try (Connection con = ds.getConnection();
             CallableStatement cs = con.prepareCall("{call seleccionar_moto(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        ) {

            int pos = 0;
            cs.setString(++pos, matricula);
            cs.registerOutParameter(++pos, Types.INTEGER);
            cs.registerOutParameter(++pos, Types.VARCHAR);
            cs.registerOutParameter(++pos, Types.VARCHAR);
            cs.registerOutParameter(++pos, Types.VARCHAR);
            cs.registerOutParameter(++pos, Types.INTEGER);
            cs.registerOutParameter(++pos, Types.DATE);
            cs.registerOutParameter(++pos, Types.VARCHAR);
            cs.registerOutParameter(++pos, Types.VARCHAR);
            cs.registerOutParameter(++pos, Types.INTEGER);
            cs.registerOutParameter(++pos, Types.INTEGER);

            cs.execute();

            String marca;
            String color;
            int preciohora;
            String descripcion;
            int bateria;
            Date fecha;
            int carnettipo;
            String estado;
            int velocidadmax, cilindrada;

            preciohora = cs.getInt(2);
            marca = cs.getString(3);
            descripcion = cs.getString(4);
            color = cs.getString(5);
            bateria = cs.getInt(6);
            fecha = cs.getDate(7);
            estado = cs.getString(8);
            carnettipo = cs.getInt(9);
            velocidadmax = cs.getInt(10);
            cilindrada = cs.getInt(11);

                MotoDTO vehiculo = new MotoDTO(matricula, marca, color, preciohora, descripcion, bateria, String.valueOf(fecha), carnettipo, estado, velocidadmax, cilindrada);
                return new Result.Success<>(vehiculo);
        } catch (SQLException e) {
            e.printStackTrace();
            return new Result.Error(123, "no existe");
        }

    }

    @Override
    public boolean update(MotoDTO vehicle) {
        String sql = "{call update_moto(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        DataSource ds = MyDataSource.getmyOracleDataSource();

        try(Connection con = ds.getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            int pos= 0;
            cs.setString(++pos, vehicle.getMatricula());
            cs.setInt(++pos, vehicle.getPreciohora());
            cs.setString(++pos, vehicle.getMarca());
            cs.setString(++pos, vehicle.getDescripcion());
            cs.setString(++pos, vehicle.getColor());
            cs.setInt(++pos, vehicle.getBateria());
            cs.setDate(++pos, Date.valueOf(vehicle.getFecha()));
            cs.setString(++pos, vehicle.getEstado());
            cs.setString(++pos, Extras.gettipocarnet(vehicle.getCarnettipo()));
            cs.setInt(++pos, vehicle.getVelocidadmax());
            cs.setInt(++pos, vehicle.getCilindrada());

            cs.executeUpdate();
            if (cs.execute())
                return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String matricula) {
        String sql = "{call delete_moto(?)}";

        try(Connection con = MyDataSource.getmyOracleDataSource().getConnection();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, matricula);
            cs.executeUpdate();

            if (cs.execute())
                return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Result<MotoDTO> add(MotoDTO vehicle) {
        String sql = "{call insertar_moto(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection con = MyDataSource.getmyOracleDataSource().getConnection();
             CallableStatement cs = con.prepareCall(sql)) {

            int pos = 0;
            cs.setString(++pos, vehicle.getMatricula());
            cs.setInt(++pos, vehicle.getPreciohora());
            cs.setString(++pos, vehicle.getMarca());
            cs.setString(++pos, vehicle.getDescripcion());
            cs.setString(++pos, vehicle.getColor());
            cs.setInt(++pos, vehicle.getBateria());
            cs.setDate(++pos, Date.valueOf(vehicle.getFecha()));
            cs.setString(++pos, vehicle.getEstado());
            cs.setString(++pos, Extras.gettipocarnet(vehicle.getCarnettipo()));
            cs.setInt(++pos, vehicle.getVelocidadmax());
            cs.setInt(++pos, vehicle.getCilindrada());

            cs.executeUpdate();
            return new Result.Success<>(vehicle);
        } catch (SQLException e) {
            return new Result.Error(e.getErrorCode(), e.getMessage());
        }
    }
}
