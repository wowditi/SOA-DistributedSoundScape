package org.example.www.database;

import java.sql.*;

public class MariaDB {
	static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://localhost:3306/";
    static final String USER = "root";
    static final String PASS = "";
    
    private final Connection connObj;

	public MariaDB(String database) throws ClassNotFoundException, SQLException {
    	Class.forName(JDBC_DRIVER);  
    	connObj = DriverManager.getConnection(DB_URL+database+"?allowMultiQueries=true", USER, PASS); 
    }
	
	public void cleanUp() throws SQLException {
		connObj.close();
	}
	
	public PreparedStatement prepareStatement(String statement) throws SQLException {
		return connObj.prepareStatement(statement);
	}
	
	public void restoreDatabase() {
		try {
			Statement stmt = connObj.createStatement();
			stmt.execute(createTables);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private final String createTables = "DROP TABLE `speakerToSongs`;\n" + 
			"\n" + 
			"\n" + 
			"DROP TABLE `soundScapeToSpeakers`;\n" + 
			"\n" + 
			"\n" + 
			"DROP TABLE `userToSoundScapes`;\n" + 
			"\n" + 
			"\n" + 
			"DROP TABLE `Users`;\n" + 
			"\n" + 
			"\n" + 
			"DROP TABLE `soundScapes`;\n" + 
			"\n" + 
			"\n" + 
			"DROP TABLE `Speakers`;\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"-- ************************************** `Users`\n" + 
			"\n" + 
			"CREATE TABLE `Users`\n" + 
			"(\n" + 
			" `ipAddress` VARCHAR(15) NOT NULL ,\n" + 
			" `port`      SMALLINT  UNSIGNED NOT NULL ,\n" + 
			"\n" + 
			"PRIMARY KEY (`ipAddress`, `port`)\n" + 
			");\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"-- ************************************** `soundScapes`\n" + 
			"\n" + 
			"CREATE TABLE `soundScapes`\n" + 
			"(\n" + 
			" `soundScapeId` INT UNSIGNED NOT NULL AUTO_INCREMENT ,\n" + 
			"\n" + 
			"PRIMARY KEY (`soundScapeId`)\n" + 
			");\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"-- ************************************** `Speakers`\n" + 
			"\n" + 
			"CREATE TABLE `Speakers`\n" + 
			"(\n" + 
			" `ipAddress` VARCHAR(15) NOT NULL ,\n" + 
			" `port`      SMALLINT UNSIGNED NOT NULL ,\n" + 
			" `x`         SMALLINT NOT NULL ,\n" + 
			" `y`         SMALLINT NOT NULL ,\n" + 
			" `z`         SMALLINT NOT NULL ,\n" + 
			"\n" + 
			"PRIMARY KEY (`ipAddress`, `port`)\n" + 
			");\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"-- ************************************** `speakerToSongs`\n" + 
			"\n" + 
			"CREATE TABLE `speakerToSongs`\n" + 
			"(\n" + 
			" `ipAddress` VARCHAR(15) NOT NULL ,\n" + 
			" `port`      SMALLINT UNSIGNED NOT NULL ,\n" + 
			" `song`      VARCHAR(2083) NOT NULL ,\n" + 
			"\n" + 
			"PRIMARY KEY (`ipAddress`, `port`, `song`),\n" + 
			"KEY `fkIdx_100` (`ipAddress`, `port`),\n" + 
			"CONSTRAINT `FK_100` FOREIGN KEY `fkIdx_100` (`ipAddress`, `port`) REFERENCES `Speakers` (`ipAddress`, `port`) ON DELETE CASCADE\n" + 
			");\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"-- ************************************** `soundScapeToSpeakers`\n" + 
			"\n" + 
			"CREATE TABLE `soundScapeToSpeakers`\n" + 
			"(\n" + 
			" `soundScapeId` INT UNSIGNED NOT NULL ,\n" + 
			" `ipAddress`    VARCHAR(15) NOT NULL ,\n" + 
			" `port`         SMALLINT UNSIGNED NOT NULL ,\n" + 
			"\n" + 
			"PRIMARY KEY (`soundScapeId`, `ipAddress`, `port`),\n" + 
			"KEY `fkIdx_74` (`soundScapeId`),\n" + 
			"CONSTRAINT `FK_74` FOREIGN KEY `fkIdx_74` (`soundScapeId`) REFERENCES `soundScapes` (`soundScapeId`) ON DELETE CASCADE,\n" + 
			"KEY `fkIdx_78` (`ipAddress`, `port`),\n" + 
			"CONSTRAINT `FK_78` FOREIGN KEY `fkIdx_78` (`ipAddress`, `port`) REFERENCES `Speakers` (`ipAddress`, `port`) ON DELETE CASCADE\n" + 
			");\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"-- ************************************** `userToSoundScapes`\n" + 
			"\n" + 
			"CREATE TABLE `userToSoundScapes`\n" + 
			"(\n" + 
			" `soundScapeId` INT UNSIGNED NOT NULL ,\n" + 
			" `ipAddress`    VARCHAR(15) NOT NULL ,\n" + 
			" `port`         SMALLINT  UNSIGNED NOT NULL ,\n" + 
			"\n" + 
			"PRIMARY KEY (`soundScapeId`, `ipAddress`, `port`),\n" + 
			"KEY `fkIdx_59` (`soundScapeId`),\n" + 
			"CONSTRAINT `FK_59` FOREIGN KEY `fkIdx_59` (`soundScapeId`) REFERENCES `soundScapes` (`soundScapeId`) ON DELETE CASCADE,\n" + 
			"KEY `fkIdx_63` (`ipAddress`, `port`),\n" + 
			"CONSTRAINT `FK_63` FOREIGN KEY `fkIdx_63` (`ipAddress`, `port`) REFERENCES `Users` (`ipAddress`, `port`) ON DELETE CASCADE\n" + 
			");\n" + 
			"\n" + 
			"";
}
