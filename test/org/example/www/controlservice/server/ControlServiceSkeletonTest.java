package org.example.www.registrationservice.server;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;
import java.util.ArrayList;

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

public class RegistrationServiceSkeletonTest {
	ControlServiceSkeleton skelleton;

	@BeforeEach
	public void setup() {
		skelleton = new ControlServiceSkeleton();
	}

	@Test
	public void addSoundScapeSourceLayoutTest() throws RuntimeException {

		SetSoundScapeSourceLayoutRequest request = new SetSoundScapeSourceLayoutRequest();
		request.setSoundScapeSourceLayout(createSoundScapeSource(100, 0, 0, 0));
		request.setSpeakers(createSpeakerDeviceArray(0, 0, 0));
		skelleton.setSoundScapeSourceLayout(request);

		RegistrationServiceSkeleton skeleton = new RegistrationServiceSkeleton("DistributedSoundScapeTest");
		GetSpeakersResponseE speakerResponse = skeleton.getSpeakers(new GetSpeakersRequestE("1.1.1.1", 1, 1));
		assertEquals(false, speakerResponse.getGetSpeakersResponse().getSpeakers().isSpeakerDeviceSpecified());
		AddSpeakerRequestE addSpeakerRequest1 = new AddSpeakerRequestE("1.1.1.1", 1, 1, (short) 1, (short) 1,
				(short) 1);
		skeleton.addSpeaker(addSpeakerRequest1);
		SpeakerDevice speaker1 = addSpeakerRequest1.getAddSpeakerRequest().getSpeaker();
		speakerResponse = skeleton.getSpeakers(new GetSpeakersRequestE("1.1.1.1", 1, 1));
		assertEquals(true, speakerResponse.getGetSpeakersResponse().getSpeakers().isSpeakerDeviceSpecified());
		assertEquals(1, speakerResponse.getGetSpeakersResponse().getSpeakers().getSpeakerDevice().length);
		assertEquals(speaker1, speakerResponse.getGetSpeakersResponse().getSpeakers().getSpeakerDevice()[0]);
		AddSpeakerRequestE addSpeakerRequest2 = new AddSpeakerRequestE("255.255.255.255", 65535, 1, (short) 50,
				(short) 30, (short) 23);
		skeleton.addSpeaker(addSpeakerRequest2);
		SpeakerDevice speaker2 = addSpeakerRequest2.getAddSpeakerRequest().getSpeaker();
		speakerResponse = skeleton.getSpeakers(new GetSpeakersRequestE("1.1.1.1", 1, 1));
		assertEquals(true, speakerResponse.getGetSpeakersResponse().getSpeakers().isSpeakerDeviceSpecified());
		assertEquals(2, speakerResponse.getGetSpeakersResponse().getSpeakers().getSpeakerDevice().length);
		assertEquals(speaker1, speakerResponse.getGetSpeakersResponse().getSpeakers().getSpeakerDevice()[0]);
		assertEquals(speaker2, speakerResponse.getGetSpeakersResponse().getSpeakers().getSpeakerDevice()[1]);
		assertNotEquals(speaker1, speakerResponse.getGetSpeakersResponse().getSpeakers().getSpeakerDevice()[1]);
		assertNotEquals(speaker2, speakerResponse.getGetSpeakersResponse().getSpeakers().getSpeakerDevice()[0]);
		speakerResponse = skeleton.getSpeakers(new GetSpeakersRequestE("1.1.1.1", 1, 50));
		assertEquals(false, speakerResponse.getGetSpeakersResponse().getSpeakers().isSpeakerDeviceSpecified());
		AddSpeakerRequestE addSpeakerRequest3 = new AddSpeakerRequestE("0.0.0.0", 65535, 50, (short) 51, (short) 23,
				(short) 23);
		skeleton.addSpeaker(addSpeakerRequest3);
		SpeakerDevice speaker3 = addSpeakerRequest3.getAddSpeakerRequest().getSpeaker();
		speakerResponse = skeleton.getSpeakers(new GetSpeakersRequestE("1.1.1.1", 1, 50));
		assertEquals(true, speakerResponse.getGetSpeakersResponse().getSpeakers().isSpeakerDeviceSpecified());
		assertEquals(1, speakerResponse.getGetSpeakersResponse().getSpeakers().getSpeakerDevice().length);
		assertEquals(speaker3, speakerResponse.getGetSpeakersResponse().getSpeakers().getSpeakerDevice()[0]);
		AddSpeakerRequestE addSpeakerRequest4 = new AddSpeakerRequestE("0.0.0.0", 65535, 230, (short) 213, (short) 213,
				(short) 234);
		SQLException e = assertThrows(SQLException.class, () -> skeleton.addSpeaker(addSpeakerRequest4));
		e.getMessage().contains("Duplicate entry");
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

	private SpeakerDeviceArray createSpeakerDeviceArraySpecific(short[] x, short[] y, short[] z) {
		assert (x.length == y.length) && (y.length == z.length)
		SpeakerDeviceArray speakers = new SpeakerDeviceArray();
		String ipBase = "1.1.1.";
		for (int i = 0, i < x.length; i++) {
			speakers.addSpeakerDevice(createSpeaker(ipBase + i,8081, x[i], y[i], z[i]));
		}
		return speakers
	}

	private SpeakerDeviceArray createSpeakerDeviceArray(short[] x, short[] y, short[] z) {
		SpeakerDeviceArray result = new SpeakerDeviceArray();
		SpeakerDeviceArray speakers = new SpeakerDeviceArray();
		String ipBase = "1.1.1.";
		int i = 0;
		for (int x = 0, x < x.length; x++) {
			for (int y = 0; y < y.length; y++) {
				for (int z = 0; z < z.length; z++) {
					speakers.addSpeakerDevice(createSpeaker(ipBase + i,8081, x[i], y[i], z[i]));
					i++;
				}
			}
		}
		return speakers
	}

	private SpeakerDevice createSpeaker(string ip, int port, Long soundScapeID, short x, short y, short z) {
		return new SpeakerDevice(ip, port, soundScapeID, x, y, z)
	}

	private ChannelLayout createChannel(short chanelNumber, double volume, short x, short y, short z) {
		ChannelNumber number = new ChannelNumber();
		number.setChannelNumber(chanelNumber);
		VolumeLevel volume = new VolumeLevel();
		volume.setVolumeLevel(volume);
		Location location = new Location();
		location.setX(x);
		location.setY(y);
		location.setZ(z);
		ChannelLayout channel = new ChannelLayout();
		channel.setLocation(location);
		channel.setChannelNumber(chanelNumber);
		channel.getVolumeLevel(volume);
	}

	private SoundScapeSourceLayout createSoundScapeSource(double[] volumes, short[] x, short[] y, short[] z) {
		int i = 0;
		SoundScapeSourceLayout layout = new SoundScapeSourceLayout();
		for (int v = 0, v < volumes.length; v++) {
			for(int x = 0, x < x.length; x++) {
				for (int y = 0, y < y.length; y++) {
					for (int z = 0, v < z.length; z++) {
						layout.addChannelLayouts(createChannel(i, volumes[i], x[i], y[i], z[i]));
						i++;
					}
				}
			}
		}
		return layout
	}

	private SoundScapeSourceLayout createSoundScapeSourceSpecific(double[] volumes, short[] x, short[] y, short[] z) {
		assert (volumes.length == x.length) && (x.length == y.length) && (y.length == z.length)
		SoundScapeSourceLayout layout = new SoundScapeSourceLayout();
		for (int i = 0, i < x.length; i++) {
			layout.addChannelLayouts(createChannel(i, volumes[i], x[i], y[i], z[i]));
		}
		return layout
	}

}
