package org.example.www.controlservice.server;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.axis2.databinding.types.UnsignedShort;
import org.example.www.controlservice.server.ControlServiceSkeleton;
import org.example.www.database.MariaDB;
import org.example.www.registrationservice.server.RegistrationServiceSkeleton;
import org.example.www.registrationserviceelements.AddSpeakerRequestE;
import org.example.www.registrationserviceelements.GetSpeakersRequestE;
import org.example.www.registrationserviceelements.GetSpeakersResponseE;
import org.example.www.registrationserviceelements.RemoveSpeakerRequestE;
import org.example.www.registrationserviceelements.SetSpeakerLocationRequestE;
import org.example.www.controlserviceelements.*;
import org.example.www.soundscapedatatypes.ChannelLayout;
import org.example.www.soundscapedatatypes.ChannelNumber;
import org.example.www.soundscapedatatypes.GeneralDevice;
import org.example.www.soundscapedatatypes.Location;
import org.example.www.soundscapedatatypes.SoundScapeSourceLayout;
import org.example.www.soundscapedatatypes.SpeakerDevice;
import org.example.www.soundscapedatatypes.SpeakerDeviceArray;
import org.example.www.soundscapedatatypes.VolumeLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ControlServiceSkeletonTest {
	private ControlServiceSkeleton skeleton;

	@BeforeEach
	public void setup() {
		skeleton = new ControlServiceSkeleton();
	}

	@Test
	public void addSoundScapeSourceLayoutTest() throws RuntimeException, ErrorMessage, SQLException {
		SetSoundScapeSourceLayoutRequestE request = new SetSoundScapeSourceLayoutRequestE();
		SetSoundScapeSourceLayoutRequest innerRequest = new SetSoundScapeSourceLayoutRequest();
		innerRequest.setSoundScapeSourceLayout(createSoundScapeSource(new double[]{100}, new short[]{0}, new short[]{0}, new short[]{0}));
		innerRequest.setSpeakers(createSpeakerDeviceArray(new short[]{0}, new short[]{0}, new short[]{0}));
		request.setSetSoundScapeSourceLayoutRequest(innerRequest);
		skeleton.setSoundScapeSourceLayout(request);
	}

	private SpeakerDeviceArray createSpeakerDeviceArraySpecific(short[] x, short[] y, short[] z) {
		assert (x.length == y.length) && (y.length == z.length);
		SpeakerDeviceArray speakers = new SpeakerDeviceArray();
		String ipBase = "1.1.1.";
		for (int i = 0; i < x.length; i++) {
			speakers.addSpeakerDevice(createSpeaker(ipBase + i,8081, (long)1, x[i], y[i], z[i]));
		}
		return speakers;
	}

	private SpeakerDeviceArray createSpeakerDeviceArray(short[] x, short[] y, short[] z) {
		SpeakerDeviceArray result = new SpeakerDeviceArray();
		SpeakerDeviceArray speakers = new SpeakerDeviceArray();
		String ipBase = "1.1.1.";
		int i = 0;
		for (int a = 0; a < x.length; a++) {
			for (int b = 0; b < y.length; b++) {
				for (int c = 0; c < z.length; c++) {
					speakers.addSpeakerDevice(createSpeaker(ipBase + i,8081, (long)1, x[i], y[i], z[i]));
					i++;
				}
			}
		}
		return speakers;
	}

	private SpeakerDevice createSpeaker(String ip, int port, Long soundScapeID, short x, short y, short z) {
		return new SpeakerDevice(ip, port, soundScapeID, x, y, z);
	}

	private ChannelLayout createChannel(short chanelNumber, double volume, short x, short y, short z) {
		ChannelNumber number = new ChannelNumber();
		number.setChannelNumber(new UnsignedShort(chanelNumber));
		VolumeLevel volumeLevel = new VolumeLevel();
		volumeLevel.setVolumeLevel(volume);
		Location location = new Location();
		location.setX(x);
		location.setY(y);
		location.setZ(z);
		ChannelLayout channel = new ChannelLayout();
		channel.setLocation(location);
		channel.setChannelNumber(number);
		channel.setVolumeLevel(volumeLevel);
		return channel;
	}

	private SoundScapeSourceLayout createSoundScapeSource(double[] volumes, short[] x, short[] y, short[] z) {
		int i = 0;
		SoundScapeSourceLayout layout = new SoundScapeSourceLayout();
		for (int v = 0; v < volumes.length; v++) {
			for(int a = 0; a < x.length; a++) {
				for (int b = 0; b < y.length; b++) {
					for (int c = 0; c < z.length; c++) {
						layout.addChannelLayouts(createChannel((short)i, volumes[i], x[i], y[i], z[i]));
						i++;
					}
				}
			}
		}
		return layout;
	}

	private SoundScapeSourceLayout createSoundScapeSourceSpecific(double[] volumes, short[] x, short[] y, short[] z) {
		assert (volumes.length == x.length) && (x.length == y.length) && (y.length == z.length);
		SoundScapeSourceLayout layout = new SoundScapeSourceLayout();
		for (int i = 0; i < x.length; i++) {
			layout.addChannelLayouts(createChannel((short)i, volumes[i], x[i], y[i], z[i]));
		}
		return layout;
	}

}
