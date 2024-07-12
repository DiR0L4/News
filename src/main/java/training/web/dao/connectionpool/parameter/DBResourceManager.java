package training.web.dao.connectionpool.parameter;

import java.util.ResourceBundle;

public class DBResourceManager {
    private static final DBResourceManager instance = new DBResourceManager();
    ResourceBundle jdbcProperties = ResourceBundle.getBundle("db");
    public static DBResourceManager getInstance(){
        return instance;
    }
    public String getValue(String key){
        return jdbcProperties.getString(key);
    }
}
