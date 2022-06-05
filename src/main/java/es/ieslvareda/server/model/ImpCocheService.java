package es.ieslvareda.server.model;


import es.ieslvareda.model.db.MyDataSource;
import es.ieslvareda.server.dto.CocheDTO;
import es.ieslvareda.server.Extras;
import es.ieslvareda.server.Result;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpCocheService implements IVehiculoService<CocheDTO> {


    @Override
    public List<CocheDTO> getAll() {
        List<CocheDTO> vehiculos = new ArrayList<>();
        DataSource ds = MyDataSource.getmyOracleDataSource();
        String sql = "select * from coche c join vehiculo v on c.matricula=v.matricula";
        try (Connection cn = ds.getConnection(); Statement st = cn.createStatement();
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
            int plazas, puertas;

            while (rs.next()) {
                matricula = rs.getString("matricula");
                marca = rs.getString("marca");
                color = rs.getString("color");
                preciohora = rs.getInt("preciohora");
                descripcion = rs.getString("descripcion");
                bateria = rs.getInt("bateria");
                fecha = rs.getDate("fechaadq");
                estado = rs.getString("estado");
                carnettipo = rs.getInt("idcarnet");
                plazas = rs.getInt("numplazas");
                puertas = rs.getInt("numpuertas");

                vehiculos.add(new CocheDTO(matricula, marca, color, preciohora, descripcion, bateria, String.valueOf(fecha), carnettipo, estado, plazas, puertas));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehiculos;
    }

    @Override
    public Result<CocheDTO> get(String matricula) {
        DataSource ds = MyDataSource.getmyOracleDataSource();

        try (Connection con = ds.getConnection();
             CallableStatement cs = con.prepareCall("{call seleccionar_coche(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
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
                int puertas, plazas;

                preciohora = cs.getInt(2);
                marca = cs.getString(3);
                descripcion = cs.getString(4);
                color = cs.getString(5);
                bateria = cs.getInt(6);
                fecha = cs.getDate(7);
                estado = cs.getString(8);
                carnettipo = cs.getInt(9);
                plazas = cs.getInt(10);
                puertas = cs.getInt(11);

                    CocheDTO vehiculo = new CocheDTO(matricula, marca, color, preciohora, descripcion, bateria, String.valueOf(fecha), carnettipo, estado, puertas, plazas);
                    return new Result.Success<>(vehiculo);
        } catch (SQLException e) {
            e.printStackTrace();
            return new Result.Error(123, "no existe");
        }

    }

    //ARREGLAR CARNET UWUWUWUWU
    @Override
    public boolean update(CocheDTO vehicle) {
        String sql = "{call update_coche(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        DataSource ds = MyDataSource.getmyOracleDataSource();

        try (Connection con = ds.getConnection();
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
            cs.setInt(++pos, vehicle.getPlazas());
            cs.setInt(++pos, vehicle.getPuertas());

            cs.executeUpdate();
            System.out.println(get(vehicle.getMatricula()));
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    //si que vaaaaaaaaaaaaaaaidfjweiufhsiluahflia
    @Override
    public boolean delete(String matricula) {
        String sql = "{call delete_coche(?)}";

        try (Connection con = MyDataSource.getmyOracleDataSource().getConnection();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, matricula);
            cs.executeUpdate();
            System.out.println(get(matricula));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    //ARREGLAR CARNET, LO DEMÁS GUCCI UHWDJKSGL

    @Override
    public Result<CocheDTO> add(CocheDTO vehicle) {
        String sql = "{call insertar_coche(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

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
            cs.setInt(++pos, vehicle.getPlazas());
            cs.setInt(++pos, vehicle.getPuertas());

            cs.executeUpdate();
                return new Result.Success<>(vehicle);
        } catch (SQLException e) {
            return new Result.Error(e.getErrorCode(), e.getMessage());
        }
    }

}
