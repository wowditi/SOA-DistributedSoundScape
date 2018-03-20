
/**
 * ControlServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
package org.example.www.controlservice.server;

import org.example.www.controlserviceelements.ProcessPlaybackCommandRequest;
import org.example.www.soundscapedatatypes.SoundScapeSourceLayout;

/**
 * ControlServiceSkeleton java skeleton for the axisService
 */
public class ControlServiceSkeleton implements ControlServiceSkeletonInterface {

	private SoundScapeSourceLayout layout;

	/**
	 * Auto generated method signature
	 * 
	 * @param setSoundScapeSourceLayoutRequest0
	 * @return setSoundScapeSourceLayoutResponse1
	 * @throws ErrorMessage
	 */

	public org.example.www.controlserviceelements.SetSoundScapeSourceLayoutResponse setSoundScapeSourceLayout(
			org.example.www.controlserviceelements.SetSoundScapeSourceLayoutRequestE setSoundScapeSourceLayoutRequest0)
			throws ErrorMessage {
		this.layout = setSoundScapeSourceLayoutRequest0.getSetSoundScapeSourceLayoutRequest()
				.getSoundScapeSourceLayout();
		org.example.www.controlserviceelements.SetSoundScapeSourceLayoutResponse response = new org.example.www.controlserviceelements.SetSoundScapeSourceLayoutResponse();
		response.setSetSoundScapeSourceLayoutResponse(true);
		return response;
		// TODO : fill this with the necessary business logic
		// throw new java.lang.UnsupportedOperationException("Please implement " +
		// this.getClass().getName() + "#setSoundScapeSourceLayout");
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param processPlaybackCommandRequest2
	 * @return processPlaybackCommandResponse3
	 * @throws ErrorMessage
	 */

	public org.example.www.controlserviceelements.ProcessPlaybackCommandResponse processPlaybackCommand(
			org.example.www.controlserviceelements.ProcessPlaybackCommandRequest processPlaybackCommandRequest2)
			throws ErrorMessage {
		//SpeakerDeviceArray speakers = processPlaybackCommandRequest2.getProcessPlaybackCommandRequest()
		
		
		
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException(
				"Please implement " + this.getClass().getName() + "#processPlaybackCommand");
	}

}
