package dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.util.Properties;

public class DatabaseConnector {

    private static final String file = "resources/mysqllogin.properties";

    private SQLServerDataSource dataSource = null;

    public DatabaseConnector(){
        Properties properties = getConnectionDetails();
        this.dataSource = new SQLServerDataSource();
        this.dataSource.setDatabaseName(properties.getProperty("name"));
        this.dataSource.setUser(properties.getProperty("username"));
        this.dataSource.setPassword(properties.getProperty("password"));
        this.dataSource.setServerName(properties.getProperty("server"));
        this.dataSource.setPortNumber(Integer.parseInt(properties.getProperty("port")));
        this.dataSource.setTrustServerCertificate(true);
    }

    private static Properties getConnectionDetails(){
        Properties properties = new Properties();

        try {
            FileInputStream sr = new FileInputStream(file);
            properties.load(sr);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties;
    }

}
