
/**
 * ControlServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
package org.example.www.controlservice.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.example.www.controlserviceelements.ProcessPlaybackCommandRequest;
import org.example.www.controlserviceelements.ProcessPlaybackCommandRequestE;
import org.example.www.controlserviceelements.ProcessPlaybackCommandResponse;
import org.example.www.soundscapedatatypes.ChannelLayout;
import org.example.www.soundscapedatatypes.Location;
import org.example.www.soundscapedatatypes.PlaybackCommand;
import org.example.www.soundscapedatatypes.SoundScapeSourceLayout;
import org.example.www.soundscapedatatypes.SpeakerDevice;
import org.example.www.soundscapedatatypes.SpeakerDeviceArray;
import org.example.www.soundscapedatatypes.VolumeLevel;

/**
 * ControlServiceSkeleton java skeleton for the axisService
 */
public class ControlServiceSkeleton implements ControlServiceSkeletonInterface {

	protected SoundScapeSourceLayout layout = new SoundScapeSourceLayout();
	protected Map<Location, ChannelLayout[]> volumeMap = new HashMap<Location, ChannelLayout[]>();

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
		SoundScapeSourceLayout newLayout = setSoundScapeSourceLayoutRequest0.getSetSoundScapeSourceLayoutRequest()
				.getSoundScapeSourceLayout();

		if (!this.layout.equals(newLayout)) {
			this.layout = newLayout;
			volumeMap = new HashMap<Location, ChannelLayout[]>();
		}
		// for every speaker
		SpeakerDeviceArray speakers = setSoundScapeSourceLayoutRequest0.getSetSoundScapeSourceLayoutRequest()
				.getSpeakers();
		for (SpeakerDevice speaker : speakers.getSpeakerDevice()) {

			if (!volumeMap.containsKey(speaker.getLocation())) {
				addLocChanels(speaker.getLocation());
			}
		}

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
		// make a queue for simultanious sending
		PlaybackCommandQueue queue = new PlaybackCommandQueue();

		// get the command
		PlaybackCommand command = processPlaybackCommandRequest2.getCommand();

		// for every speaker
		SpeakerDeviceArray speakers = processPlaybackCommandRequest2.getSpeakers();
		for (SpeakerDevice speaker : speakers.getSpeakerDevice()) {

			// Get or calculate set of volume levels for every channel
			if (!volumeMap.containsKey(speaker.getLocation())) {
				addLocChanels(speaker.getLocation());
			}
			ChannelLayout[] channels = volumeMap.get(speaker.getLocation());

			queue.addInstruction(new PlaybackCommandInstruction(speaker, channels, command));

		}

		queue.send();
		org.example.www.controlserviceelements.ProcessPlaybackCommandResponse response = new org.example.www.controlserviceelements.ProcessPlaybackCommandResponse();
		response.setProcessPlaybackCommandResponse(true);
		return response;
		// TODO : fill this with the necessary business logic
		// throw new java.lang.UnsupportedOperationException(
		// "Please implement " + this.getClass().getName() + "#processPlaybackCommand");

	}

	private void addLocChanels(Location speakerLoc) {
		// Calculate new set of volume levels for every channel
		ChannelLayout[] channels = new ChannelLayout[layout.getChannelLayouts().length];
		for (int i = 0; i < channels.length; i++) {
			ChannelLayout sourceChannel = layout.getChannelLayouts()[i];

			channels[i].setChannelNumber(sourceChannel.getChannelNumber());
			channels[i].setVolumeLevel(calcVolumeLevel(sourceChannel, speakerLoc));

		}
		volumeMap.put(speakerLoc, channels);
	}

	private VolumeLevel calcVolumeLevel(ChannelLayout sourceChannel, Location speakerLoc) {
		Location sourceLoc = sourceChannel.getLocation();

		// Claculate volume multiplier based on vector projection scalar projecting
		// speaker on source vector
		double volumeMultiplier;

		if (dotProduct(sourceLoc, sourceLoc) !=0) { 
			volumeMultiplier = dotProduct(speakerLoc, sourceLoc) / dotProduct(sourceLoc, sourceLoc);
		} else {
			volumeMultiplier = 1;
		}
		volumeMultiplier = Math.max(volumeMultiplier, 0);

		VolumeLevel newVolume = new VolumeLevel();
		newVolume.setVolumeLevel(sourceChannel.getVolumeLevel().getVolumeLevel() * volumeMultiplier);
		return newVolume;

	}

	private double dotProduct(Location a, Location b) {
		double result = 0;
		result += (a.getX() * b.getX());
		result += (a.getY() * b.getY());
		result += (a.getZ() * b.getZ());
		return result;
	}

	private class PlaybackCommandQueue {

		ArrayList<PlaybackCommandInstruction> queue = new ArrayList<PlaybackCommandInstruction>();

		public void addInstruction(PlaybackCommandInstruction instruction) {
			this.queue.add(instruction);
		}

		public void clear() {
			this.queue = new ArrayList<PlaybackCommandInstruction>();
		}

		public void send() {
			// placeholder, no actual speakers to send to
			this.clear();
		}
	}

	private class PlaybackCommandInstruction {

		final SpeakerDevice speaker;
		final ChannelLayout[] chanelVolumes;
		final PlaybackCommand command;

		PlaybackCommandInstruction(SpeakerDevice speaker, ChannelLayout[] chanelVolumes, PlaybackCommand command) {
			this.speaker = speaker;
			this.chanelVolumes = chanelVolumes;
			this.command = command;
		}

	}

	@Override
	public ProcessPlaybackCommandResponse processPlaybackCommand(
			ProcessPlaybackCommandRequestE processPlaybackCommandRequest) throws ErrorMessage {
		// TODO Auto-generated method stub
		return null;
	}
}
