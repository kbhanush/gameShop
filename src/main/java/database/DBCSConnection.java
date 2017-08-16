package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import oracle.jdbc.pool.*;

import javax.servlet.ServletContext;

/*
 *
 * @author Arindam Bose
 */
public class DBCSConnection {
//Database Connection properties

    //Database Connection properties
    private String dbUser = null;
    private String dbPass = null;
    private String dbEdition = null;
    static String dbIP = null;
    static String dbName = null;

    //DB Connection objects
    private Connection _conn;
    private final OracleDataSource _ords;
    //private ServletContext _context;

    public DBCSConnection(ServletContext context) throws SQLException {

        // Get database connection information from the system properties...
        dbUser = System.getProperty("dbUser");
        dbPass = System.getProperty("dbPass");
        dbIP = System.getProperty("dbIP");
        dbName = System.getProperty("dbName");
        dbEdition = System.getProperty("dbEdition");
        String source = "system properties";

        // If the system properties are not set, look to the OS environment...        
        if (dbUser == null || dbPass == null || dbIP == null || dbName == null || dbEdition == null) {
            System.out.println("[DB DEBUG] The database connection information wasn't provided via system properties - checking the environment.");
            // This will allow an individual system property to override the properties file...
            dbUser = (dbUser != null) ? dbUser : System.getenv("dbUser");
            dbPass = (dbPass != null) ? dbPass : System.getenv("dbPass");
            dbIP = (dbIP != null) ? dbIP : System.getenv("dbIP");
            dbName = (dbName != null) ? dbName : System.getenv("dbName");
            dbEdition = (dbEdition != null) ? dbEdition : System.getenv("dbEdition");
            source = "OS environment";
        }

        // If the environment it not set, pull from a Maven generated properties file...
        if (dbUser == null || dbPass == null || dbIP == null || dbName == null || dbEdition == null) {
            System.out.println("[DB DEBUG] The database connection information wasn't provided via system properties or the OS environment - reading from Maven generarated db.proerties file.");

            try {
                String dbPropsRelpath = "db.properties";
                System.out.println("[DB DEBUG] Servlet Context: " + context);
                InputStream propStream = context.getResourceAsStream(dbPropsRelpath);
                Properties dbProps = new Properties();
                dbProps.load(propStream);
                // This will allow an individual system property or environment variable to override the properties file...
                dbUser = (dbUser != null) ? dbUser : dbProps.getProperty("databaseUser");
                dbPass = (dbPass != null) ? dbPass : dbProps.getProperty("databasePassword");
                dbIP = (dbIP != null) ? dbIP : dbProps.getProperty("databaseIP");
                dbName = (dbName != null) ? dbName : dbProps.getProperty("databaseName");
                dbEdition = (dbEdition != null) ? dbEdition : dbProps.getProperty("databaseEdition");
                source = "Maven generated db.properties file";
            } catch (IOException e) {
                System.out.println("[DB DEBUG] Error loading database connection info");
            }
        }
        // If the environment it not set, pull from a application properties file...
        if (dbUser == null || dbPass == null || dbIP == null || dbName == null || dbEdition == null) {
            System.out.println("[DB DEBUG] The database connection information wasn't provided via system properties, the OS environment or the Maven generated properties file - reading from the application db.proerties file.");

            try {
                String dbPropsRelpath = "/WEB-INF/classes/db.properties";
                System.out.println("[DB DEBUG] Servlet Context: " + context);
                InputStream propStream = context.getResourceAsStream(dbPropsRelpath);
                Properties dbProps = new Properties();
                dbProps.load(propStream);
                // This will allow an individual system property or environment variable to override the properties file...
                dbUser = (dbUser != null) ? dbUser : dbProps.getProperty("databaseUser");
                dbPass = (dbPass != null) ? dbPass : dbProps.getProperty("databasePassword");
                dbIP = (dbIP != null) ? dbIP : dbProps.getProperty("databaseIP");
                dbName = (dbName != null) ? dbName : dbProps.getProperty("databaseName");
                dbEdition = (dbEdition != null) ? dbEdition : dbProps.getProperty("databaseEdition");
                source = "Application provided db.properties file";
            } catch (IOException e) {
                System.out.println("[DB DEBUG] Error loading database connection info");
            }
        }

        System.out.println("[DB DEBUG] The database connection properties were read from the " + source);
        System.out.println("[DB DEBUG] Database User:" + dbUser);
        System.out.println("[DB DEBUG] Database Password:" + dbPass);
        System.out.println("[DB DEBUG] Database IP:" + dbIP);
        System.out.println("[DB DEBUG] Database Name:" + dbName);
        System.out.println("[DB DEBUG] Database Edition: " + dbEdition);

        String connURL = "jdbc:oracle:thin:@//" + dbIP + ":1521/" + dbName;
        //Create Data Source
        _ords = new OracleDataSource();
        _ords.setURL(connURL);
        _ords.setUser(dbUser);
        _ords.setPassword(dbPass);
        _conn = _ords.getConnection();
        _conn = DriverManager.getConnection(connURL, dbUser, dbPass);

        Statement editionStmt = _conn.createStatement();
        editionStmt.execute("alter session set edition=" + dbEdition);

    }

    public Connection getConnection() throws SQLException {
        //Lazy Initialization
        if (_conn == null) {
            _conn = _ords.getConnection();
        }

        if (_conn.isClosed()) {
            _conn = _ords.getConnection();
        }

        System.out.println("Database connection completed successfully");
        return _conn;
    }

    public boolean isActive() throws SQLException {
        return !_conn.isClosed();
    }

}
