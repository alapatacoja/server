package es.ieslvareda.model.db;

import java.util.Map;

public abstract class DatabaseAccess <T>{

    public abstract T select(Map<String, String[]> params);

    public abstract T insert(T data);

    public abstract T update(Map<String, String[]> params, T data);

    public abstract T delete(Map<String, String[]> params);

}
