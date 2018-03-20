package org.example.www.utils;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import org.example.www.database.MariaDB;

public class SpeakerUtils {

	public static void sendFileToSpeaker(File file, String ipAddress, int port, MariaDB db) throws SQLException {
		//Send file
		try {
			Thread.sleep(new Random().nextInt(50));
		} catch (InterruptedException e) {
			//Do nothing, does not matter
		}
		String statement = "INSERT INTO speakerToSongs (ipAddress, port, song) VALUES (?, ?, ?)";
		PreparedStatement insertStatement = db.prepareStatement(statement);
		insertStatement.setString(1, ipAddress);
		insertStatement.setInt(2, port);
		insertStatement.setString(3, file.getName());
		insertStatement.executeUpdate();
	}
}
