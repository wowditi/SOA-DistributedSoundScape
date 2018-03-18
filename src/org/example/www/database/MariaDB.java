package org.example.www.database;

import java.sql.*;

public class MariaDB {
	static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://mariadb:3306/DistributedSoundScape";
    static final String USER = "root";
    static final String PASS = "";
    
    private final Connection connObj;

	public MariaDB() throws ClassNotFoundException, SQLException {
    	Class.forName(JDBC_DRIVER);  
    	connObj = DriverManager.getConnection(DB_URL, USER, PASS); 
    }
	
	public void cleanUp() throws SQLException {
		connObj.close();
	}
	
	public PreparedStatement prepareStatement(String statement) throws SQLException {
		return connObj.prepareStatement(statement);
	}
}
