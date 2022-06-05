package es.ieslvareda.model.db;


import com.mysql.cj.jdbc.MysqlDataSource;
import es.ieslvareda.properties.MyConfig;
import oracle.jdbc.datasource.impl.OracleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class MyDataSource {

    public static DataSource getMariaDBDataSource(){

        MysqlDataSource ds = new MysqlDataSource();
        ds.setUrl(MyConfig.getInstance().getDBURL());
        ds.setUser(MyConfig.getInstance().getDBUSer());
        ds.setPassword(MyConfig.getInstance().getDBPassword());

        return ds;

    }


    public static DataSource getmyOracleDataSource(){
        OracleDataSource mysqlDataSource = null;

        try {
            mysqlDataSource = new OracleDataSource();

            String host = MyConfig.getInstance().getOracleDBHost();
            String port = MyConfig.getInstance().getOracleDBPort();
            String user = MyConfig.getInstance().getORACLEUSer();
            String password = MyConfig.getInstance().getORACLEPassword();


            mysqlDataSource.setURL("jdbc:oracle:thin:@"+host+":"+port+":xe");
            mysqlDataSource.setUser(user);
            mysqlDataSource.setPassword(password);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mysqlDataSource;

    }

}
