
/**
 * RegistrationServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
package org.example.www.registrationservice.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.www.database.MariaDB;
import org.example.www.registrationserviceelements.AddSpeakerResponse;
import org.example.www.registrationserviceelements.GetSpeakersResponse;
import org.example.www.registrationserviceelements.GetSpeakersResponseE;
import org.example.www.soundscapedatatypes.GeneralDevice;
import org.example.www.soundscapedatatypes.IPv4Address;
import org.example.www.soundscapedatatypes.Location;
import org.example.www.soundscapedatatypes.SpeakerDevice;
import org.example.www.soundscapedatatypes.SpeakerDeviceArray;

/**
 * RegistrationServiceSkeleton java skeleton for the axisService
 */
public class RegistrationServiceSkeleton implements RegistrationServiceSkeletonInterface {

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
			db = new MariaDB();
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
			PreparedStatement createSoundScape = db .prepareStatement("INSERT IGNORE INTO soundScapes (soundScapeId) VALUES(?)");
			createSoundScape.setLong(1, speaker.getGeneralDevice().getSoundScapeId().getSoundscapeId().longValue());
			createSoundScape.executeUpdate();
			PreparedStatement createLink = db.prepareStatement("insert into soundScapeToSpeakers (soundScapeId, ipAddress, port) VALUES (?, ?, ?)");
			createLink.setLong(1, speaker.getGeneralDevice().getSoundScapeId().getSoundscapeId().longValue());
			createLink.setString(2, ipv4);
			createLink.setInt(3, speaker.getGeneralDevice().getPort().getPort().intValue());	
			createLink.executeUpdate();
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
			throws ErrorMessage {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException(
				"Please implement " + this.getClass().getName() + "#setSpeakerLocation");
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
			db = new MariaDB();
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
			ResultSet speakers = getSpeakersStatement.executeQuery();
			SpeakerDeviceArray speakerArray = new SpeakerDeviceArray();
			while (speakers.next()) {
				String ipAddress = speakers.getString(1);
				int port = speakers.getInt(2);
				short x = speakers.getShort(3);
				short y = speakers.getShort(4);
				short z = speakers.getShort(5);
				speakerArray.addSpeakerDevice(new SpeakerDevice(ipAddress, port, soundScapeId, x, y, z));
			}
			System.out.println(speakerArray.getSpeakerDevice().length);
			GetSpeakersResponseE response = new GetSpeakersResponseE();
			GetSpeakersResponse innerResponse = new GetSpeakersResponse();
			innerResponse.setSpeakers(speakerArray);
			response.setGetSpeakersResponse(innerResponse);
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
			org.example.www.registrationserviceelements.RegisterUserRequestE registerUserRequest6) throws ErrorMessage {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException(
				"Please implement " + this.getClass().getName() + "#registerUser");
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param removeUserRequest8
	 * @return
	 */

	public void removeUser(org.example.www.registrationserviceelements.RemoveUserRequestE removeUserRequest8) {
		// TODO : fill this with the necessary business logic

	}

	/**
	 * Auto generated method signature
	 * 
	 * @param removeSpeakerRequest9
	 * @return removeSpeakerResponse10
	 * @throws ErrorMessage
	 */

	public org.example.www.registrationserviceelements.RemoveSpeakerResponse removeSpeaker(
			org.example.www.registrationserviceelements.RemoveSpeakerRequestE removeSpeakerRequest9)
			throws ErrorMessage {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException(
				"Please implement " + this.getClass().getName() + "#removeSpeaker");
	}

}
