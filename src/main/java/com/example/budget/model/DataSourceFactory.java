package com.example.budget.model;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSourceFactory {

    private final DataSource ds;
    private static final Logger LOGGER = Logger.getLogger(DataSourceFactory.class.getName());

    DataSourceFactory() {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().
                getResource("database.properties")).getPath();
        InputStream input = null;

        try {
            input = new FileInputStream(rootPath);
            Properties properties = new Properties();
            properties.load(input);
            ds.setDatabaseName(properties.getProperty("database"));
            ds.setServerName(properties.getProperty("serverName"));
            ds.setPortNumber(Integer.parseInt(properties.getProperty("port")));
            ds.setUser(properties.getProperty("user"));
            ds.setPassword(properties.getProperty("password"));
        }
        catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "File database.properties not found", e);
        }
        catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IO Error", e);
        }
        finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE,"Filed to close streams", e);
                }
            }
        }
        this.ds = ds;
    }

    public static Connection getConnection() throws SQLException {
        return SingletonHelper.INSTANCE.ds.getConnection();
    }

    public static class SingletonHelper {
        public static final DataSourceFactory INSTANCE = new DataSourceFactory();
    }

}
