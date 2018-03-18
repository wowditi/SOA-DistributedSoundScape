
/**
 * RegistrationServiceSkeletonInterface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
package org.example.www.registrationservice.server;

import java.sql.SQLException;

/**
 * RegistrationServiceSkeletonInterface java skeleton interface for the
 * axisService
 */
public interface RegistrationServiceSkeletonInterface {

	/**
	 * Auto generated method signature
	 * 
	 * @param addSpeakerRequest
	 * @throws ErrorMessage
	 *             :
	 * @throws SQLException
	 * @throws RuntimeException
	 */

	public org.example.www.registrationserviceelements.AddSpeakerResponse addSpeaker(
			org.example.www.registrationserviceelements.AddSpeakerRequestE addSpeakerRequest)
			throws ErrorMessage, RuntimeException, SQLException;

	/**
	 * Auto generated method signature
	 * 
	 * @param setSpeakerLocationRequest
	 * @throws ErrorMessage
	 *             :
	 * @throws SQLException 
	 * @throws RuntimeException 
	 */

	public org.example.www.registrationserviceelements.SetSpeakerLocationResponse setSpeakerLocation(
			org.example.www.registrationserviceelements.SetSpeakerLocationRequestE setSpeakerLocationRequest)
			throws ErrorMessage, RuntimeException, SQLException;

	/**
	 * Auto generated method signature
	 * 
	 * @param getSpeakersRequest
	 * @throws ErrorMessage
	 *             :
	 * @throws SQLException 
	 * @throws RuntimeException 
	 */

	public org.example.www.registrationserviceelements.GetSpeakersResponseE getSpeakers(
			org.example.www.registrationserviceelements.GetSpeakersRequestE getSpeakersRequest) throws ErrorMessage, RuntimeException, SQLException;

	/**
	 * Auto generated method signature
	 * 
	 * @param registerUserRequest
	 * @throws ErrorMessage
	 *             :
	 * @throws SQLException 
	 * @throws RuntimeException 
	 */

	public org.example.www.registrationserviceelements.RegisterUserResponse registerUser(
			org.example.www.registrationserviceelements.RegisterUserRequestE registerUserRequest) throws ErrorMessage, RuntimeException, SQLException;

	/**
	 * Auto generated method signature
	 * 
	 * @param removeUserRequest
	 * @throws SQLException 
	 */

	public void removeUser(org.example.www.registrationserviceelements.RemoveUserRequestE removeUserRequest) throws SQLException;

	/**
	 * Auto generated method signature
	 * 
	 * @param removeSpeakerRequest
	 * @throws ErrorMessage
	 *             :
	 */

	public org.example.www.registrationserviceelements.RemoveSpeakerResponse removeSpeaker(
			org.example.www.registrationserviceelements.RemoveSpeakerRequestE removeSpeakerRequest) throws ErrorMessage;

}
