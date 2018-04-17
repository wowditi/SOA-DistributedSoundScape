package org.example.www.uploadservice.server;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.sql.SQLException;

import org.example.www.database.MariaDB;
import org.example.www.registrationservice.server.RegistrationServiceSkeleton;
import org.example.www.registrationserviceelements.AddSpeakerRequestE;
import org.example.www.registrationserviceelements.GetSpeakersRequestE;
import org.example.www.soundscapedatatypes.Link;
import org.example.www.soundscapedatatypes.SpeakerDeviceArray;
import org.example.www.uploadserviceelements.DeleteSongRequestE;
import org.example.www.uploadserviceelements.IsSongLoadedRequestE;
import org.example.www.uploadserviceelements.UploadSongRequestE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UploadServiceSkeletonTest {
	private final RegistrationServiceSkeleton skeleton = new RegistrationServiceSkeleton("DistributedSoundScapeTest");

	@BeforeEach
	public void setup() throws RuntimeException, SQLException {
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
		skeleton.addSpeaker(new AddSpeakerRequestE("1.1.1.1", 1, (long) 1, (short) 1, (short) 1, (short) 1)); 
		skeleton.addSpeaker(new AddSpeakerRequestE("1.1.1.2", 1, (long) 1, (short) 1, (short) 1, (short) 1));
	}

	@Test
	public void uploadServiceTest() throws SQLException {
		UploadServiceSkeleton skel = new UploadServiceSkeleton("DistributedSoundScapeTest");
		Link url1 = new Link("tegos.kz/new/mp3_full/Kygo_feat._Justin_Jesso_-_Stargazing.mp3", "http");
		Link url2 = new Link("a.tumblr.com/tumblr_lh4wdoJdgt1qa5xk1o1.mp3", "https");
		SpeakerDeviceArray speakers = skeleton.getSpeakers(new GetSpeakersRequestE("1.1.1.1", 1, 1))
				.getGetSpeakersResponse().getSpeakers();
		assertFalse(skel.isSongLoaded(new IsSongLoadedRequestE(url1, speakers)).getIsSongLoadedResponse());
		assertFalse(skel.isSongLoaded(new IsSongLoadedRequestE(url2, speakers)).getIsSongLoadedResponse());
		skel.uploadSong(new UploadSongRequestE(url1, speakers));
		assertTrue(skel.isSongLoaded(new IsSongLoadedRequestE(url1, speakers)).getIsSongLoadedResponse());
		assertFalse(skel.isSongLoaded(new IsSongLoadedRequestE(url2, speakers)).getIsSongLoadedResponse());
		skel.uploadSong(new UploadSongRequestE(url2, speakers));
		assertTrue(skel.isSongLoaded(new IsSongLoadedRequestE(url1, speakers)).getIsSongLoadedResponse());
		assertTrue(skel.isSongLoaded(new IsSongLoadedRequestE(url2, speakers)).getIsSongLoadedResponse());
		skeleton.addSpeaker(new AddSpeakerRequestE("1.1.1.3", 1, (long) 1, (short) 1, (short) 1, (short) 1));
		speakers = skeleton.getSpeakers(new GetSpeakersRequestE("1.1.1.1", 1, 1)).getGetSpeakersResponse()
				.getSpeakers();
		assertFalse(skel.isSongLoaded(new IsSongLoadedRequestE(url1, speakers)).getIsSongLoadedResponse());
		assertFalse(skel.isSongLoaded(new IsSongLoadedRequestE(url2, speakers)).getIsSongLoadedResponse());
		skel.uploadSong(new UploadSongRequestE(url2, speakers));
		assertTrue(skel.isSongLoaded(new IsSongLoadedRequestE(url2, speakers)).getIsSongLoadedResponse());
		assertFalse(skel.isSongLoaded(new IsSongLoadedRequestE(url1, speakers)).getIsSongLoadedResponse());
		skel.uploadSong(new UploadSongRequestE(url1, speakers));
		assertTrue(skel.isSongLoaded(new IsSongLoadedRequestE(url2, speakers)).getIsSongLoadedResponse());
		assertTrue(skel.isSongLoaded(new IsSongLoadedRequestE(url1, speakers)).getIsSongLoadedResponse());
		skel.deleteSong(new DeleteSongRequestE(url1, speakers));
		assertFalse(skel.isSongLoaded(new IsSongLoadedRequestE(url1, speakers)).getIsSongLoadedResponse());
		assertTrue(skel.isSongLoaded(new IsSongLoadedRequestE(url2, speakers)).getIsSongLoadedResponse());
		skel.deleteSong(new DeleteSongRequestE(url2, speakers));
		assertFalse(skel.isSongLoaded(new IsSongLoadedRequestE(url1, speakers)).getIsSongLoadedResponse());
		assertFalse(skel.isSongLoaded(new IsSongLoadedRequestE(url2, speakers)).getIsSongLoadedResponse());
	}

}
