package es.ieslvareda.server;

import com.google.gson.Gson;
import es.ieslvareda.model.db.DatabaseAccess;
import spark.Request;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import static spark.Spark.*;

public class Spark {

    private Gson gson = new Gson();

    public void methods(){
        get("/api/:class", (request, response) -> {
            String path = getPath(request);
            DatabaseAccess databaseAccess = getObject(path);
            Map<String, String[]> params = request.queryMap().toMap();
            return gson.toJson(databaseAccess.select(params));
        });

        post("api/:class", (request, response) -> {
            String path = getPath(request);
            DatabaseAccess databaseAccess = getObject(path);
            Class<?> clazz = getDtoClass(path);
            Object data = gson.fromJson(request.body(), clazz);
            return gson.toJson(databaseAccess.insert(data));
        });

        put("/api/:class", (request, response) -> {
            String path = getPath(request);
            DatabaseAccess databaseAccess = getObject(path);
            Class<?> clazz = getDtoClass(path);
            Object data = gson.fromJson(request.body(), clazz);
            Map<String, String[]> params = request.queryMap().toMap();
            return gson.toJson(databaseAccess.update(params, data));
        });

        delete("/api/:class", (request, response) -> {
            DatabaseAccess databaseAccess = getObject(request.params(":class"));
            Map<String, String[]> params = request.queryMap().toMap();
            return gson.toJson(databaseAccess.delete(params));
        });
    }

    private String getPath(Request request){
        String input = request.params(":class");
        String path = input.substring(0, 1).toUpperCase() + input.substring(1);
        return path;
    }

    private DatabaseAccess getObject(String className){
        Class<?> clazz = null;
        DatabaseAccess object = null;
        try {
            clazz = Class.forName("es.ieslvareda.server.api." + className);
            object = (DatabaseAccess) clazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return object;
    }

    private Class<?> getDtoClass(String path){
        Class<?> clazz = null;
        try {
            clazz = Class.forName("es.ieslvareda.server.dto."+path + "DTO");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

}
