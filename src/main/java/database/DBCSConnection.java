
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

	private String dbUser 			= null;
	private String dbPass			= null;
	private String dbEdition		= null;

	//DB Connection objects
	private Connection _conn;
	private OracleDataSource _ords;
	private ServletContext _context;

	static String dbIP				= null;
	static String dbName			= null;

	public DBCSConnection(ServletContext context) throws SQLException{

		_context = context;


		try {

			String dbPropsRelpath = "/WEB-INF/classes/db.properties";
			System.out.println("[DB DEBUG] Servlet Context: "+context);

			InputStream propStream = context.getResourceAsStream(dbPropsRelpath);
			Properties dbProps = new Properties();
			dbProps.load(propStream);


			dbUser = dbProps.getProperty("databaseUser");
			dbPass = dbProps.getProperty("databasePassword");
			dbIP = dbProps.getProperty("databaseIP");
			dbName = dbProps.getProperty("databaseName");
			dbEdition = dbProps.getProperty("databaseEdition");

		} catch (IOException e) {

			System.out.println("[DB DEBUG] Error loading database connection info");
			e.printStackTrace();
		}

		System.out.println("[DB DEBUG] Database IP:"+dbIP);
		System.out.println("[DB DEBUG] Database Name:"+dbName);
		System.out.println("[DB DEBUG] Database User:"+dbUser);
		System.out.println("[DB DEBUG] Database User:"+dbPass);
		System.out.println("[DB DEBUG] Database Edition: "+dbEdition);

		String connURL = "jdbc:oracle:thin:@//"+dbIP+":1521/"+dbName;
		//Create Data Source
		_ords = new OracleDataSource();
		_ords.setURL(connURL);
		_ords.setUser(dbUser);
		_ords.setPassword(dbPass);
		_conn = _ords.getConnection();
		_conn = DriverManager.getConnection(connURL,dbUser,dbPass);

		Statement editionStmt = _conn.createStatement();
		editionStmt.execute("alter session set edition="+dbEdition);


	}

	public Connection getConnection() throws SQLException{
		//Lazy Initialization
		if( _conn == null){
			_conn = _ords.getConnection();
		}


		if( _conn.isClosed()){
			_conn = _ords.getConnection();
		}

		System.out.println("Database connection completed successfully");
		return _conn;
	}

	public boolean isActive() throws SQLException{
		return !_conn.isClosed();
	}


}
