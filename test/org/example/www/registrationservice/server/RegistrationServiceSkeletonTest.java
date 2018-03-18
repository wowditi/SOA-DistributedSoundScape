package org.example.www.registrationservice.server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.example.www.database.MariaDB;
import org.example.www.registrationserviceelements.AddSpeakerRequestE;
import org.example.www.registrationserviceelements.GetSpeakersRequestE;
import org.example.www.registrationserviceelements.GetSpeakersResponseE;
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
		skeleton.addSpeaker(new AddSpeakerRequestE("1.1.1.1", 1, 1, (short)1, (short)1, (short)1));
		speakerResponse = skeleton.getSpeakers(new GetSpeakersRequestE("1.1.1.1", 1, 1));
		assertEquals(true, speakerResponse.getGetSpeakersResponse().getSpeakers().isSpeakerDeviceSpecified());
		assertEquals(1, speakerResponse.getGetSpeakersResponse().getSpeakers().getSpeakerDevice().length);
	}
}
