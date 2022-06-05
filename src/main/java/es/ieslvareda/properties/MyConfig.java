package es.ieslvareda.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class MyConfig {

    private static MyConfig instance;
    private final String DEFAULT_PROPERTIES = "default.properties";
    private final String CUSTOM_PROPERTIES = "custom.properties";
    private Properties myProperties;

    private MyConfig(){

        Properties defaultProperties = new Properties();
        try(FileInputStream fis = new FileInputStream(DEFAULT_PROPERTIES)){

            defaultProperties.load(fis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        myProperties=new Properties(defaultProperties);

        try(FileInputStream fis = new FileInputStream(CUSTOM_PROPERTIES)){

            myProperties.load(fis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static MyConfig getInstance(){
        if (instance==null){
            instance = new MyConfig();
        }
        return instance;
    }

    public String getDBURL() {
        return myProperties.getProperty("MYSQL_DB_URL");
    }

    public String getDBUSer() {
        return myProperties.getProperty("MYSQL_DB_USERNAME");
    }

    public String getDBPassword() {
        return myProperties.getProperty("MYSQL_DB_PASSWORD");
    }


    public String getOracleDBHost(){
        return myProperties.getProperty("ORACLE_DB_HOST");
    }

    public String getORACLEUSer() {
        return myProperties.getProperty("ORACLE_DB_USERNAME");
    }

    public String getORACLEPassword() {
        return myProperties.getProperty("ORACLE_DB_PASSWORD");
    }

    public String getOracleDBPort(){
        return myProperties.getProperty("ORACLE_DB_PORT");
    }



}
