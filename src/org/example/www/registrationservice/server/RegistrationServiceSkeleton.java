
/**
 * RegistrationServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
package org.example.www.registrationservice.server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.www.database.MariaDB;
import org.example.www.registrationserviceelements.AddSpeakerResponse;
import org.example.www.registrationserviceelements.GetSpeakersResponse;
import org.example.www.registrationserviceelements.GetSpeakersResponseE;
import org.example.www.registrationserviceelements.RegisterUserResponse;
import org.example.www.registrationserviceelements.RemoveSpeakerResponse;
import org.example.www.registrationserviceelements.SetSpeakerLocationResponse;
import org.example.www.soundscapedatatypes.GeneralDevice;
import org.example.www.soundscapedatatypes.Location;
import org.example.www.soundscapedatatypes.SpeakerDevice;
import org.example.www.soundscapedatatypes.SpeakerDeviceArray;

/**
 * RegistrationServiceSkeleton java skeleton for the axisService
 */
public class RegistrationServiceSkeleton implements RegistrationServiceSkeletonInterface {
	
	private final String database;
	
	public RegistrationServiceSkeleton() {
		database = "DistributedSoundScape";
	}
	
	public RegistrationServiceSkeleton(String database) {
		this.database = database;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param addSpeakerRequest0
	 * @return addSpeakerResponse1
	 * @throws SQLException 
	 * @throws ErrorMessage
	 */

	public org.example.www.registrationserviceelements.AddSpeakerResponse addSpeaker(
			org.example.www.registrationserviceelements.AddSpeakerRequestE addSpeakerRequest0) throws RuntimeException, SQLException {
		MariaDB db;
		try {
			db = new MariaDB(database);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("Unable to create a connection to the database: " + e);
		}
		try {
			SpeakerDevice speaker = addSpeakerRequest0.getAddSpeakerRequest().getSpeaker();
			String ipv4 = speaker.getGeneralDevice().getIpAddress().getIPv4Address();
			String statement = "INSERT INTO Speakers (ipAddress, port, x, y, z)";
			statement += "VALUES (?, ?, ?, ?, ?)";
			PreparedStatement addSpeakerStatement = db.prepareStatement(statement);
			addSpeakerStatement.setString(1, ipv4);
			addSpeakerStatement.setInt(2, speaker.getGeneralDevice().getPort().getPort().intValue());
			addSpeakerStatement.setShort(3, speaker.getLocation().getX());
			addSpeakerStatement.setShort(4, speaker.getLocation().getY());
			addSpeakerStatement.setShort(5, speaker.getLocation().getZ());
			addSpeakerStatement.executeUpdate(); 
			addSpeakerStatement.close();
			PreparedStatement createSoundScape = db .prepareStatement("INSERT IGNORE INTO soundScapes (soundScapeId) VALUES(?)");
			createSoundScape.setLong(1, speaker.getGeneralDevice().getSoundScapeId().getSoundscapeId().longValue());
			createSoundScape.executeUpdate();
			createSoundScape.close();
			PreparedStatement createLink = db.prepareStatement("insert into soundScapeToSpeakers (soundScapeId, ipAddress, port) VALUES (?, ?, ?)");
			createLink.setLong(1, speaker.getGeneralDevice().getSoundScapeId().getSoundscapeId().longValue());
			createLink.setString(2, ipv4);
			createLink.setInt(3, speaker.getGeneralDevice().getPort().getPort().intValue());	
			createLink.executeUpdate();
			createLink.close();
			AddSpeakerResponse response = new AddSpeakerResponse();
			response.setAddSpeakerResponse(true);
			return response;
		} finally {
			db.cleanUp();
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param setSpeakerLocationRequest2
	 * @return setSpeakerLocationResponse3
	 * @throws ErrorMessage
	 */

	public org.example.www.registrationserviceelements.SetSpeakerLocationResponse setSpeakerLocation(
			org.example.www.registrationserviceelements.SetSpeakerLocationRequestE setSpeakerLocationRequest2)
			throws RuntimeException, SQLException {
		MariaDB db;
		try {
			db = new MariaDB(database);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("Unable to create a connection to the database: " + e);
		}
		try {
			GeneralDevice user = setSpeakerLocationRequest2.getSetSpeakerLocationRequest().getUser();
			SpeakerDevice speaker = setSpeakerLocationRequest2.getSetSpeakerLocationRequest().getSpeakerDevice();
			if (!user.getSoundScapeId().getSoundscapeId().equals(speaker.getGeneralDevice().getSoundScapeId().getSoundscapeId())) {
				throw new RuntimeException("User soundScape is not equal to speaker soundScape");
			}
			String statement = "update Speakers set x = ?, y = ?, z = ? where ipAddress = ? and port = ?";
			PreparedStatement setLocationStatement = db.prepareStatement(statement);
			Location location = speaker.getLocation();
			setLocationStatement.setShort(1, location.getX());
			setLocationStatement.setShort(2, location.getY());
			setLocationStatement.setShort(3, location.getZ());
			setLocationStatement.setString(4, speaker.getGeneralDevice().getIpAddress().getIPv4Address());
			setLocationStatement.setInt(5, speaker.getGeneralDevice().getPort().getPort().intValue());
			SetSpeakerLocationResponse response = new SetSpeakerLocationResponse();
			if (setLocationStatement.executeUpdate() > 0)
				response.setSetSpeakerLocationResponse(true);
			else {
				setLocationStatement.close();
				throw new RuntimeException("The given speaker does not exist.");
			}
			setLocationStatement.close();
			return response;
		} finally {
			db.cleanUp();
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param getSpeakersRequest4
	 * @return getSpeakersResponse5
	 * @throws ErrorMessage
	 * @throws SQLException 
	 */

	public org.example.www.registrationserviceelements.GetSpeakersResponseE getSpeakers(
			org.example.www.registrationserviceelements.GetSpeakersRequestE getSpeakersRequest4) throws RuntimeException, SQLException {
		MariaDB db;
		try {
			db = new MariaDB(database);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("Unable to create a connection to the database: " + e);
		}
		try {
			String statement = "select scape.ipAddress, scape.port, speaker.x, speaker.y, "
					+ "speaker.z from soundScapeToSpeakers scape join (Speakers speaker) ON scape.port = "
					+ "speaker.port and scape.ipAddress = speaker.ipAddress where soundScapeId = ?";
			PreparedStatement getSpeakersStatement = db.prepareStatement(statement);
			Long soundScapeId = getSpeakersRequest4.getGetSpeakersRequest().getUser()
					.getSoundScapeId().getSoundscapeId().longValue();
			getSpeakersStatement.setLong(1, soundScapeId);
			GetSpeakersResponseE response = new GetSpeakersResponseE();
			GetSpeakersResponse innerResponse = new GetSpeakersResponse();
			SpeakerDeviceArray speakerArray = new SpeakerDeviceArray();
			innerResponse.setSpeakers(speakerArray);
			response.setGetSpeakersResponse(innerResponse);
			if (!getSpeakersStatement.execute()) {
				getSpeakersStatement.close();
				return response;
			}
			ResultSet speakers = getSpeakersStatement.getResultSet();
			while (speakers.next()) {
				String ipAddress = speakers.getString(1);
				int port = speakers.getInt(2);
				short x = speakers.getShort(3);
				short y = speakers.getShort(4);
				short z = speakers.getShort(5);
				speakerArray.addSpeakerDevice(new SpeakerDevice(ipAddress, port, soundScapeId, x, y, z));
			}			
			getSpeakersStatement.close();
			speakers.close();
			return response;
		} finally {
			db.cleanUp();
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param registerUserRequest6
	 * @return registerUserResponse7
	 * @throws ErrorMessage
	 */

	public org.example.www.registrationserviceelements.RegisterUserResponse registerUser(
			org.example.www.registrationserviceelements.RegisterUserRequestE registerUserRequest6) throws RuntimeException, SQLException {
		MariaDB db;
		System.out.println("test");
		try {
			db = new MariaDB(database);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("Unable to create a connection to the database: " + e);
		}
		try {
			GeneralDevice user = registerUserRequest6.getRegisterUserRequest().getUser();
			String ipv4 = user.getIpAddress().getIPv4Address();
			String statement = "INSERT IGNORE INTO Users (ipAddress, port)";
			statement += "VALUES (?, ?)";
			PreparedStatement addSpeakerStatement = db.prepareStatement(statement);
			addSpeakerStatement.setString(1, ipv4);
			addSpeakerStatement.setInt(2, user.getPort().getPort().intValue());
			addSpeakerStatement.executeUpdate(); 
			addSpeakerStatement.close();
			PreparedStatement createSoundScape = db .prepareStatement("INSERT IGNORE INTO soundScapes (soundScapeId) VALUES(?)");
			createSoundScape.setLong(1, user.getSoundScapeId().getSoundscapeId().longValue());
			createSoundScape.executeUpdate();
			createSoundScape.close();
			PreparedStatement createLink = db.prepareStatement("insert ignore into userToSoundScapes (soundScapeId, ipAddress, port) VALUES (?, ?, ?)");
			createLink.setLong(1, user.getSoundScapeId().getSoundscapeId().longValue());
			createLink.setString(2, ipv4);
			createLink.setInt(3, user.getPort().getPort().intValue());	
			createLink.executeUpdate();
			createLink.close();
			RegisterUserResponse response = new RegisterUserResponse();
			response.setRegisterUserResponse(true);
			return response;
		} catch (Exception e) {
			//for bpel debugging purposes
			e.printStackTrace();
			throw new RuntimeException("Something went horribly wrong");
		} finally {
			db.cleanUp();
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param removeUserRequest8
	 * @return
	 * @throws SQLException 
	 */

	public void removeUser(org.example.www.registrationserviceelements.RemoveUserRequestE removeUserRequest8) throws SQLException {
		MariaDB db;
		try {
			db = new MariaDB(database);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("Unable to create a connection to the database: " + e);
		}
		try {
			GeneralDevice user = removeUserRequest8.getRemoveUserRequest().getUser();
			String ipv4 = user.getIpAddress().getIPv4Address();
			String statement = "DELETE FROM Users where ipAddress = ? and port = ?";
			PreparedStatement removeUserStatement = db.prepareStatement(statement);
			removeUserStatement.setString(1, ipv4);
			removeUserStatement.setInt(2, user.getPort().getPort().intValue());
			if (removeUserStatement.executeUpdate() == 0) {
				removeUserStatement.close();
				throw new RuntimeException("User does not exist.");
			}
			removeUserStatement.close();
		} finally {
			db.cleanUp();
		}

	}

	/**
	 * Auto generated method signature
	 * 
	 * @param removeSpeakerRequest9
	 * @return removeSpeakerResponse10
	 * @throws ErrorMessage
	 * @throws SQLException 
	 */

	public org.example.www.registrationserviceelements.RemoveSpeakerResponse removeSpeaker(
			org.example.www.registrationserviceelements.RemoveSpeakerRequestE removeSpeakerRequest9)
			throws ErrorMessage, SQLException {
		MariaDB db;
		try {
			db = new MariaDB(database);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("Unable to create a connection to the database: " + e);
		}
		try {
			GeneralDevice speaker = removeSpeakerRequest9.getRemoveSpeakerRequest().getSpeaker();
			String ipv4 = speaker.getIpAddress().getIPv4Address();
			String statement = "DELETE FROM Speakers where ipAddress = ? and port = ?";
			PreparedStatement removeSpeakerStatement = db.prepareStatement(statement);
			removeSpeakerStatement.setString(1, ipv4);
			removeSpeakerStatement.setInt(2, speaker.getPort().getPort().intValue());
			if (removeSpeakerStatement.executeUpdate() == 0) {
				removeSpeakerStatement.close();
				throw new RuntimeException("Speaker does not exist.");
			}
			removeSpeakerStatement.close();
			RemoveSpeakerResponse response = new RemoveSpeakerResponse();
			response.setRemoveSpeakerResponse(true);
			return response;
		} finally {
			db.cleanUp();
		}
	}

}
