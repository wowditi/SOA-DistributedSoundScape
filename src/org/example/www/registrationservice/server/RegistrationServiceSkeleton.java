
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
import java.sql.SQLException;

import org.example.www.database.MariaDB;
import org.example.www.registrationserviceelements.AddSpeakerResponse;
import org.example.www.soundscapedatatypes.SpeakerDevice;

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
		SpeakerDevice speaker = addSpeakerRequest0.getAddSpeakerRequest().getSpeaker();
		long ipv4;
		try {
			InetAddress i= InetAddress.getByName(speaker.getGeneralDevice().getIpAddress().getIPv4Address());
			ipv4 = ByteBuffer.wrap(i.getAddress()).getInt() & 0x00000000ffffffffL;
		} catch (UnknownHostException e) {
			throw new RuntimeException("Unable to process ip-address: "+ e);
		}
		String statement = "INSERT INTO Speakers (ipAddress, port, x, y, z)";
		statement += "VALUES (?, ?, ?, ?, ?)";
		PreparedStatement addSpeakerStatement = db.prepareStatement(statement);
		addSpeakerStatement.setLong(1, ipv4);
		addSpeakerStatement.setInt(2, speaker.getGeneralDevice().getPort().getPort().intValue());
		addSpeakerStatement.setShort(3, speaker.getLocation().getX());
		addSpeakerStatement.setShort(4, speaker.getLocation().getY());
		addSpeakerStatement.setShort(5, speaker.getLocation().getZ());
		AddSpeakerResponse response = new AddSpeakerResponse();
		addSpeakerStatement.execute(); 
		response.setAddSpeakerResponse(true);
		return response;
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
	 */

	public org.example.www.registrationserviceelements.GetSpeakersResponseE getSpeakers(
			org.example.www.registrationserviceelements.GetSpeakersRequestE getSpeakersRequest4) throws ErrorMessage {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException(
				"Please implement " + this.getClass().getName() + "#getSpeakers");
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
