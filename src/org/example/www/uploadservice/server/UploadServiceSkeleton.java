
/**
 * UploadServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
package org.example.www.uploadservice.server;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.example.www.database.MariaDB;
import org.example.www.soundscapedatatypes.SpeakerDevice;
import org.example.www.uploadservice.server.ErrorMessage;
import org.example.www.uploadservice.server.UploadServiceSkeletonInterface;
import org.example.www.utils.DownloadUtils;
import org.example.www.utils.SpeakerUtils;

/**
 * UploadServiceSkeleton java skeleton for the axisService
 */
public class UploadServiceSkeleton implements UploadServiceSkeletonInterface {

	private final String database;

	public UploadServiceSkeleton() {
		database = "DistributedSoundScape";
	}

	public UploadServiceSkeleton(String database) {
		this.database = database;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param uploadSongRequest0
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 * @throws MalformedURLException
	 */

	public void uploadSong(org.example.www.uploadserviceelements.UploadSongRequestE uploadSongRequest0)
			throws SQLException {
		System.out.println("test");
		try {
		MariaDB db;
		try {
			db = new MariaDB(database);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("Unable to create a connection to the database: " + e);
		}
		try {
			SpeakerDevice[] speakers = uploadSongRequest0.getUploadSongRequest().getSpeakers().getSpeakerDevice();
			String type = uploadSongRequest0.getUploadSongRequest().getLink().getType().getValue();
			String url = uploadSongRequest0.getUploadSongRequest().getLink().getAddress();
			String fileName;
			switch (type) {
				case "http":
					try {
						fileName = DownloadUtils.downloadSong("http://" + url, "");
					} catch (MalformedURLException e) {
						throw new RuntimeException("Malformed url: \"http://" + url + "\": "+ e);
					} catch (IOException e) {
						throw new RuntimeException("Unable to download file: "+e);
					}
					break;
				case "ftp":
					throw new RuntimeException("Sorry ftp is not (yet) supported");
				default:
					throw new RuntimeException("Only the ftp and http types are allowed");
			}
			// Send file to speakers
			ExecutorService executor = Executors.newFixedThreadPool(speakers.length);
			for (SpeakerDevice speaker : speakers) {
				executor.submit(() -> {
				    try {
						SpeakerUtils.sendFileToSpeaker(new File(fileName), speaker.getGeneralDevice().getIpAddress().getIPv4Address(),
								speaker.getGeneralDevice().getPort().getPort().intValue(), db);
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException("The database could not be updated with the new song"+e);
					}
				});
			}
			executor.shutdown();
			try {
				executor.awaitTermination(5, TimeUnit.MINUTES);
			} catch (InterruptedException e) {
				//Callback send failure
				return;
			}
			//Callback send success
			return;
			
		} finally {
			db.cleanUp();
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param isSongLoadedRequest1
	 * @return isSongLoadedResponse2
	 * @throws ErrorMessage
	 */

	public org.example.www.uploadserviceelements.IsSongLoadedResponse isSongLoaded(
			org.example.www.uploadserviceelements.IsSongLoadedRequestE isSongLoadedRequest1) throws ErrorMessage {
		// TODO : fill this with the necessary business logic
		System.out.println("test2");
		throw new java.lang.UnsupportedOperationException(
				"Do Please implement " + this.getClass().getName() + "#isSongLoaded2");
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param deleteSongRequest3
	 * @return
	 */

	public void deleteSong(org.example.www.uploadserviceelements.DeleteSongRequestE deleteSongRequest3) {
		// TODO : fill this with the necessary business logic

	}

}
