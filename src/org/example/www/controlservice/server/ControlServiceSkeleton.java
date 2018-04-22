
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

import javax.management.RuntimeErrorException;

import org.example.www.controlserviceelements.ProcessPlaybackCommandRequest;
import org.example.www.controlserviceelements.ProcessPlaybackCommandRequestE;
import org.example.www.controlserviceelements.ProcessPlaybackCommandResponse;
import org.example.www.controlserviceelements.SetSoundScapeSourceLayoutRequestE;
import org.example.www.controlserviceelements.SetSoundScapeSourceLayoutResponse;
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
		SetSoundScapeSourceLayoutResponse response = new SetSoundScapeSourceLayoutResponse();
		response.setSetSoundScapeSourceLayoutResponse(false);
		try {
			SoundScapeSourceLayout newLayout = setSoundScapeSourceLayoutRequest0.getSetSoundScapeSourceLayoutRequest()
					.getSoundScapeSourceLayout();

			if (!this.layout.equals(newLayout)) {
				this.layout = newLayout;
				volumeMap = new HashMap<Location, ChannelLayout[]>();
			}
			// for every speaker
			SpeakerDeviceArray speakers = setSoundScapeSourceLayoutRequest0.getSetSoundScapeSourceLayoutRequest()
					.getSpeakers();
			if (speakers.isSpeakerDeviceSpecified()) {
				for (SpeakerDevice speaker : speakers.getSpeakerDevice()) {

					if (!volumeMap.containsKey(speaker.getLocation())) {
						addLocChanels(speaker.getLocation());
					}
				}
			}

			response.setSetSoundScapeSourceLayoutResponse(true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("An error occured: " + e);
		}
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
			org.example.www.controlserviceelements.ProcessPlaybackCommandRequestE processPlaybackCommandRequest)
			throws ErrorMessage, RuntimeException {
		//fault test
		if (processPlaybackCommandRequest.getProcessPlaybackCommandRequest().getCommand().getPlaybackCommand().equals("faulty"))
			throw new RuntimeException("An intended error has occured");
		// make a queue for simultanious sending
		ProcessPlaybackCommandResponse response = new ProcessPlaybackCommandResponse();
		response.setProcessPlaybackCommandResponse(false);
		try {
			ProcessPlaybackCommandRequest request = processPlaybackCommandRequest.getProcessPlaybackCommandRequest();
			PlaybackCommandQueue queue = new PlaybackCommandQueue();

			// get the command
			PlaybackCommand command = request.getCommand();

			// for every speaker
			SpeakerDeviceArray speakers = request.getSpeakers();
			for (SpeakerDevice speaker : speakers.getSpeakerDevice()) {

				// Get or calculate set of volume levels for every channel
				if (!volumeMap.containsKey(speaker.getLocation())) {
					addLocChanels(speaker.getLocation());
				}
				ChannelLayout[] channels = volumeMap.get(speaker.getLocation());

				queue.addInstruction(new PlaybackCommandInstruction(speaker, channels, command));

			}

			queue.send();
			response = new ProcessPlaybackCommandResponse();
			response.setProcessPlaybackCommandResponse(true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("An error occured: " + e);
		}
		return response;
		// TODO : fill this with the necessary business logic
		// throw new java.lang.UnsupportedOperationException(
		// "Please implement " + this.getClass().getName() + "#processPlaybackCommand");

	}

	private void addLocChanels(Location speakerLoc) {
		// Calculate new set of volume levels for every channel
		ChannelLayout[] channels;
		if (layout != null && layout.getChannelLayouts() != null)
			channels = new ChannelLayout[layout.getChannelLayouts().length];
		else
			channels = new ChannelLayout[0];
		for (int i = 0; i < channels.length; i++) {
			ChannelLayout sourceChannel = layout.getChannelLayouts()[i];

			channels[i] = new ChannelLayout();
			channels[i].setChannelNumber(sourceChannel.getChannelNumber());
			channels[i].setLocation(sourceChannel.getLocation());
			channels[i].setVolumeLevel(calcVolumeLevel(sourceChannel, speakerLoc));

		}
		volumeMap.put(speakerLoc, channels);
	}

	private VolumeLevel calcVolumeLevel(ChannelLayout sourceChannel, Location speakerLoc) {
		Location sourceLoc = sourceChannel.getLocation();

		// Claculate volume multiplier based on vector projection scalar projecting
		// speaker on source vector
		double volumeMultiplier;

		if (dotProduct(sourceLoc, sourceLoc) != 0) {
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
			System.out.println("==========Playback Queue Test Output==========");
			for (PlaybackCommandInstruction instruction : queue) {
				StringBuilder output = new StringBuilder();
				output.append("S@ ");
				output.append(instruction.getSpeakerDevice().getLocation());
				output.append(" ");
				output.append(instruction.getPlaybackCommand());
				for (ChannelLayout channel : instruction.getChanelLayout()) {
					output.append(" ");
					output.append(channel.getLocation());
					output.append(" ");
					output.append(channel.getVolumeLevel().getVolumeLevel());

				}
				System.out.println(output);
			}
			System.out.println("==========END OF Playback Queue Test Output==========");
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

		public SpeakerDevice getSpeakerDevice() {
			return this.speaker;
		}

		public ChannelLayout[] getChanelLayout() {
			return this.chanelVolumes;
		}

		public PlaybackCommand getPlaybackCommand() {
			return this.command;
		}

	}
}