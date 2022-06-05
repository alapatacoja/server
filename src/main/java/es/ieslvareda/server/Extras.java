package es.ieslvareda.server;

import es.ieslvareda.model.db.MyDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Extras {

    public static String gettipocarnet(int id) {
        DataSource ds = MyDataSource.getmyOracleDataSource();

        try (Connection con = ds.getConnection(); PreparedStatement st = con.prepareStatement("select tipo from CARNET where idcarnet = ?");
        ) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next())
                return rs.getString("tipo");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
