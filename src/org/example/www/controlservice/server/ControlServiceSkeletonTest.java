package org.example.www.controlservice.server;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.axis2.databinding.types.UnsignedShort;
import org.apache.axis2.databinding.utils.ConverterUtil;
import org.example.www.controlservice.server.ControlServiceSkeleton;
import org.example.www.database.MariaDB;
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
	ControlServiceSkeleton skelleton;

	@BeforeEach
	public void setup() {
		skelleton = new ControlServiceSkeleton();
	}

	@Test
	public void addSoundScapeSourceLayoutTest() throws RuntimeException {

		SetSoundScapeSourceLayoutRequest request = new SetSoundScapeSourceLayoutRequest();
		SoundScapeSourceLayout sourceLayout = createSoundScapeSource(new double[100], new short[1], new short[1], new short[1]);
		request.setSoundScapeSourceLayout(createSoundScapeSource(new double[100], new short[1], new short[1], new short[1]));
		SpeakerDeviceArray sourceSpeakers = createSpeakerDeviceArray((long) 1, new short[1], new short[1], new short[1]);
		request.setSpeakers(sourceSpeakers);
		SetSoundScapeSourceLayoutRequestE E = new SetSoundScapeSourceLayoutRequestE();
		E.setSetSoundScapeSourceLayoutRequest(request);
		try {
			skelleton.setSoundScapeSourceLayout(E);
		} catch (ErrorMessage e) {
			assertEquals(true, false);
			e.printStackTrace();
		}
		assertEquals(skelleton.layout, sourceLayout);
		for (SpeakerDevice speaker : sourceSpeakers.getSpeakerDevice()) {
			Location loc = speaker.getLocation();
			assertTrue(skelleton.volumeMap.containsKey(loc));
		}
		
	}

	@Test
	public void removeSpeakerTest() throws RuntimeException, SQLException, ErrorMessage {
		RegistrationServiceSkeleton skeleton = new RegistrationServiceSkeleton("DistributedSoundScapeTest");
		AddSpeakerRequestE addSpeakerRequest1 = new AddSpeakerRequestE("1.1.1.1", 1, 1, (short) 1, (short) 1,
				(short) 1);
		skeleton.addSpeaker(addSpeakerRequest1);
		SpeakerDevice speaker1 = addSpeakerRequest1.getAddSpeakerRequest().getSpeaker();
		AddSpeakerRequestE addSpeakerRequest2 = new AddSpeakerRequestE("1.34.32.5", 45, 1, (short) 34, (short) 11,
				(short) 23);
		skeleton.addSpeaker(addSpeakerRequest2);
		SpeakerDevice speaker2 = addSpeakerRequest2.getAddSpeakerRequest().getSpeaker();
		AddSpeakerRequestE addSpeakerRequest3 = new AddSpeakerRequestE("1.1.1.1", 2, 1, (short) 132, (short) 15,
				(short) 1557);
		skeleton.addSpeaker(addSpeakerRequest3);
		SpeakerDevice speaker3 = addSpeakerRequest3.getAddSpeakerRequest().getSpeaker();
		GetSpeakersResponseE speakerResponse = skeleton.getSpeakers(new GetSpeakersRequestE("1.1.1.1", 1, 1));
		assertEquals(3, speakerResponse.getGetSpeakersResponse().getSpeakers().getSpeakerDevice().length);
		skeleton.removeSpeaker(new RemoveSpeakerRequestE(speaker2.getGeneralDevice()));
		speakerResponse = skeleton.getSpeakers(new GetSpeakersRequestE("1.1.1.1", 1, 1));
		assertEquals(speaker1, speakerResponse.getGetSpeakersResponse().getSpeakers().getSpeakerDevice()[0]);
		assertEquals(speaker3, speakerResponse.getGetSpeakersResponse().getSpeakers().getSpeakerDevice()[1]);
		skeleton.removeSpeaker(new RemoveSpeakerRequestE(speaker3.getGeneralDevice()));
		speakerResponse = skeleton.getSpeakers(new GetSpeakersRequestE("1.1.1.1", 1, 1));
		assertEquals(speaker1, speakerResponse.getGetSpeakersResponse().getSpeakers().getSpeakerDevice()[0]);
		skeleton.removeSpeaker(new RemoveSpeakerRequestE(speaker1.getGeneralDevice()));
		speakerResponse = skeleton.getSpeakers(new GetSpeakersRequestE("1.1.1.1", 1, 1));
		assertEquals(false, speakerResponse.getGetSpeakersResponse().getSpeakers().isSpeakerDeviceSpecified());
	}

	@Test
	public void setSpeakerLocationTest() throws RuntimeException, SQLException {
		RegistrationServiceSkeleton skeleton = new RegistrationServiceSkeleton("DistributedSoundScapeTest");
		AddSpeakerRequestE addSpeakerRequest1 = new AddSpeakerRequestE("1.1.1.1", 1, 1, (short) 1, (short) 1,
				(short) 1);
		skeleton.addSpeaker(addSpeakerRequest1);
		GetSpeakersResponseE speakerResponse = skeleton.getSpeakers(new GetSpeakersRequestE("1.1.1.1", 1, 1));
		assertEquals(new Location((short) 1, (short) 1, (short) 1),
				speakerResponse.getGetSpeakersResponse().getSpeakers().getSpeakerDevice()[0].getLocation());
		skeleton.setSpeakerLocation(new SetSpeakerLocationRequestE(new GeneralDevice("1.1.1.1", 1, (long) 1),
				new SpeakerDevice("1.1.1.1", 1, (long) 1, (short) 5, (short) 10, (short) 15)));
		speakerResponse = skeleton.getSpeakers(new GetSpeakersRequestE("1.1.1.1", 1, 1));
		assertEquals(new Location((short) 5, (short) 10, (short) 15),
				speakerResponse.getGetSpeakersResponse().getSpeakers().getSpeakerDevice()[0].getLocation());
	}

	private SpeakerDeviceArray createSpeakerDeviceArraySpecific(long soundScapeID, short[] xArray, short[] yArray, short[] zArray) {
		assert (xArray.length == yArray.length) && (yArray.length == zArray.length);
		SpeakerDeviceArray speakers = new SpeakerDeviceArray();
		String ipBase = "1.1.1.";
		for (int i = 0; i < xArray.length; i++) {
			speakers.addSpeakerDevice(createSpeaker(ipBase + i,8081, soundScapeID, xArray[i], yArray[i], zArray[i]));
		}
		return speakers;
	}

	private SpeakerDeviceArray createSpeakerDeviceArray(long soundScapeID, short[] xArray, short[] yArray, short[] zArray) {
		SpeakerDeviceArray result = new SpeakerDeviceArray();
		SpeakerDeviceArray speakers = new SpeakerDeviceArray();
		String ipBase = "1.1.1.";
		int i = 0;
		for (int x = 0; x < xArray.length; x++) {
			for (int y = 0; y < yArray.length; y++) {
				for (int z = 0; z < zArray.length; z++) {
					speakers.addSpeakerDevice(createSpeaker(ipBase + i,8081, soundScapeID, xArray[x], yArray[y], zArray[z]));
					i++;
				}
			}
		}
		return speakers;
	}

	private SpeakerDevice createSpeaker(String ip, int port, Long soundScapeID, short x, short y, short z) {
		return new SpeakerDevice(ip, port, soundScapeID, x, y, z);
	}

	private ChannelLayout createChannel(int i, double volume, short x, short y, short z) {
		ChannelNumber number = new ChannelNumber();
		number.setChannelNumber(ConverterUtil.convertToUnsignedShort(((Integer) i).toString()));
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

	private SoundScapeSourceLayout createSoundScapeSource(double[] volumes, short[] xArray, short[] yArray, short[] zArray) {
		int i = 0;
		SoundScapeSourceLayout layout = new SoundScapeSourceLayout();
		for (int v = 0; v < volumes.length; v++) {
			for(int x = 0; x < xArray.length; x++) {
				for (int y = 0; y < yArray.length; y++) {
					for (int z = 0; z < zArray.length; z++) {
						layout.addChannelLayouts(createChannel(i, volumes[v], xArray[x], yArray[y], zArray[z]));
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
			layout.addChannelLayouts(createChannel(i, volumes[i], x[i], y[i], z[i]));
		}
		return layout;
	}

}
