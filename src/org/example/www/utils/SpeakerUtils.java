package org.example.www.utils;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import org.example.www.database.MariaDB;

public class SpeakerUtils {

	public static void sendFileToSpeaker(File file, String url, String ipAddress, int port, MariaDB db) throws SQLException {
		//Send file to speaker
		System.out.println("sending");
		try {
			Thread.sleep(new Random().nextInt(1000));
		} catch (InterruptedException e) {
			//Do nothing, its for mock-up purposes anyways
		}
		String statement = "INSERT IGNORE INTO speakerToSongs (ipAddress, port, song) VALUES (?, ?, ?)";
		PreparedStatement insertStatement = db.prepareStatement(statement);
		insertStatement.setString(1, ipAddress);
		insertStatement.setInt(2, port);
		insertStatement.setString(3, url);
		insertStatement.executeUpdate();
	}
	
	public static void deleteFileFromSpeaker(String url, String ipAddress, int port, MariaDB db) throws SQLException {
		//Delete files from speaker
		System.out.println("Deleting");
		try {
			Thread.sleep(new Random().nextInt(1000));
		} catch (InterruptedException e) {
			//Do nothing, its for mock-up purposes anyways
		}
		String statement = "DELETE FROM speakerToSongs where ipAddress = ? and port = ? and song = ?";
		PreparedStatement deleteStatement = db.prepareStatement(statement);
		deleteStatement.setString(1, ipAddress);
		deleteStatement.setInt(2, port);
		deleteStatement.setString(3, url);
		deleteStatement.executeUpdate();
	}
	
	public static boolean isSongLoadedOnSpeaker(String url, String ipAddress, int port, MariaDB db) throws SQLException {
		String statement = "SELECT 1 FROM speakerToSongs where ipAddress = ? and port = ? and song = ?";
		PreparedStatement searchStatement = db.prepareStatement(statement);
		searchStatement.setString(1, ipAddress);
		searchStatement.setInt(2, port);
		searchStatement.setString(3, url);
		if (searchStatement.execute() && searchStatement.getResultSet().next())
			return true;
		return false;
	}
}
