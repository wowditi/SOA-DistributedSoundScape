package org.example.www.registrationservice.server;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;

import org.example.www.database.MariaDB;
import org.example.www.registrationserviceelements.AddSpeakerRequestE;
import org.example.www.registrationserviceelements.GetSpeakersRequestE;
import org.example.www.registrationserviceelements.GetSpeakersResponseE;
import org.example.www.registrationserviceelements.RemoveSpeakerRequestE;
import org.example.www.registrationserviceelements.SetSpeakerLocationRequestE;
import org.example.www.soundscapedatatypes.GeneralDevice;
import org.example.www.soundscapedatatypes.Location;
import org.example.www.soundscapedatatypes.SpeakerDevice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistrationServiceSkeletonTest {

	@BeforeEach
	public void setup() {
		try {
			MariaDB mariadb = new MariaDB("DistributedSoundScapeTest");
			try {
				mariadb.restoreDatabase();
			} finally {
				mariadb.cleanUp();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addSpeakerTest() throws RuntimeException, SQLException {
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
}
