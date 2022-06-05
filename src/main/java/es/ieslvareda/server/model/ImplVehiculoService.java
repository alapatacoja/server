package es.ieslvareda.server.model;



import es.ieslvareda.model.db.MyDataSource;
import es.ieslvareda.server.dto.VehiculoDTO;
import es.ieslvareda.server.Result;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImplVehiculoService implements IVehiculoService<VehiculoDTO> {

    @Override
    public List<VehiculoDTO> getAll() {
        List<VehiculoDTO> vehiculos = new ArrayList<>();
        DataSource ds = MyDataSource.getmyOracleDataSource();
        String sql = "select * from vehiculo";
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

                vehiculos.add(new VehiculoDTO( matricula,  marca,  color,  preciohora,  descripcion,  bateria, String.valueOf(fecha),  carnettipo,  estado, null));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehiculos;
    }

    @Override
    public Result<VehiculoDTO> get(String matricula) {
        DataSource ds = MyDataSource.getmyOracleDataSource();

        try(Connection con = ds.getConnection(); PreparedStatement st = con.prepareStatement("select * from vehiculo where matricula=?");
        ) {

            int pos = 0;
            st.setString(++pos, matricula);
            ResultSet rs = st.executeQuery();

            String marca;
            String color;
            int preciohora;
            String descripcion;
            int bateria;
            Date fecha;
            int carnettipo;
            String estado;

            if(rs.next()) {
                marca = rs.getString("marca");
                color = rs.getString("color");
                preciohora = rs.getInt("preciohora");
                descripcion = rs.getString("descripcion");
                bateria = rs.getInt("bateria");
                fecha = rs.getDate("fechaadq");
                estado = rs.getString("estado");
                carnettipo = rs.getInt("idcarnet");

                VehiculoDTO vehiculo = new VehiculoDTO(matricula,marca,color,preciohora,  descripcion,  bateria,  String.valueOf(fecha),  carnettipo,  estado, null);
                return new Result.Success<>(vehiculo);
            } else {
                return new Result.Error(404, "No existe");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return new Result.Error(e.getErrorCode(), e.getMessage());
        }
    }

    @Override
    public boolean delete(String matricula) {

        DataSource ds = MyDataSource.getmyOracleDataSource();

        try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement("delete from vehiculo where matricula=?")) {

            int pos = 0;
            ps.setString(++pos, matricula);
            int rs = ps.executeUpdate();

            if (rs==1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            new Result.Error(e.getErrorCode(), e.getMessage());
        }
        return false;
    }

    @Override
    public Result<VehiculoDTO> add(VehiculoDTO vehicle) {
        DataSource ds = MyDataSource.getmyOracleDataSource();

        try (Connection con = ds.getConnection(); PreparedStatement st = con.prepareStatement("insert into vehiculo(matricula, preciohora, marca, descripcion, color, bateria, fechaadq, estado, idcarnet) " +
                        "values (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {


            int pos =0;
            st.setString(++pos, vehicle.getMatricula());
            st.setInt(++pos, vehicle.getPreciohora());
            st.setString(++pos, vehicle.getMarca());
            st.setString(++pos, vehicle.getDescripcion());
            st.setString(++pos, vehicle.getColor());
            st.setInt(++pos, vehicle.getBateria());
            st.setDate(++pos, Date.valueOf(vehicle.getFecha()));
            st.setString(++pos, vehicle.getEstado());
            st.setInt(++pos, vehicle.getCarnettipo());

            st.executeQuery();
            return new Result.Success<>(vehicle);

        } catch (SQLException e) {
            e.printStackTrace();
            return new Result.Error(123, "no se puede");
        }
    }

    @Override
    public boolean update(VehiculoDTO vehicle) {
        DataSource ds = MyDataSource.getmyOracleDataSource();

        try(Connection con = ds.getConnection(); PreparedStatement st = con.prepareStatement("update vehiculo set marca=?, preciohora=?, descripcion=?, color=?, bateria=?, " +
                "fechaadq=?, estado=?, idcarnet=? where matricula=?")) {

            int pos =0;
            st.setString(++pos, vehicle.getMarca());
            st.setInt(++pos, vehicle.getPreciohora());
            st.setString(++pos, vehicle.getDescripcion());
            st.setString(++pos, vehicle.getColor());
            st.setInt(++pos, vehicle.getBateria());
            st.setDate(++pos, Date.valueOf(vehicle.getFecha()));
           st.setString(++pos, vehicle.getEstado());
            st.setInt(++pos, vehicle.getCarnettipo());
            st.setString(++pos, vehicle.getMatricula());

            st.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
