package com.cognixia.jump.jdbc.ems_extension.team2;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/***
 * This is a revised version of the original ConnectionManager that we made.
 * <p>
 * It's whole point is that the original allows for MULTIPLE connections to a singular database,
 * which can cause MANY stability and integrity issues with the data and its structure. So, we
 * reimplemented the ConnectionManager class to be a "Singleton" design--meaning there's only one
 * connection that exists, and all declarations / instantiations of it are now pointers to it.
 * <p>
 * We then removed the hard-coded URL, username, and password from the code and moved it into a 
 * config.properties file in the resources folder.
 * <p>
 * @author Alexandre Northouse
 */
public class ConnectionManager {
	// this is the static, private variable that keeps this a singleton class.
	private static Connection conn = null;
	
	
	private static void makeConnection() {
		//first, we create a properties object to hold the property files.
		Properties props = new Properties();
		
		//this then gets put into a try / catch, incase of the many possible errors.
		try {
			//this loads the properties from the properties file
			props.load(new FileInputStream("./resources/config.properties"));
			
			//we then extract the items into variables (for readability)
			String url = props.getProperty("url");
			String user = props.getProperty("username");
			String pass = props.getProperty("password");
			
			//finally, we create the connection:
			conn = DriverManager.getConnection(url, user, pass);
			
		//then the possible exceptions	
		} catch (IOException e) {
			System.out.println("There was an IOException:");
			System.out.println(e.getCause());
		} catch (SQLException e) {
			System.out.println("There was an SQLException:");
			System.out.println(e.getCause());
		}
	}
	
	
	/***
	 * This is the public facing method that will create or return a connection.
	 * @return conn where it is either a pointer to a static or a new conn.
	 */
	public static Connection getConnection() {
		if (conn == null) {
			System.out.println("Initiating database connection..");
			makeConnection();
		}
		return conn;
	}
	
	
	public static void closeConnection() throws SQLException {
		if (conn != null) {
			System.out.println("Terminating database connection..");
			conn.close();
			conn = null;
		}
	}
}
