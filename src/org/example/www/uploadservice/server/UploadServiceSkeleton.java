
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
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.impl.llom.OMElementImpl;
import org.apache.axiom.soap.SOAPHeader;
import org.apache.axiom.soap.SOAPHeaderBlock;
import org.apache.axiom.soap.impl.llom.SOAPHeaderBlockImpl;
import org.apache.axiom.soap.impl.llom.soap11.SOAP11Factory;
import org.apache.axiom.soap.impl.llom.soap11.SOAP11HeaderBlockImpl;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.databinding.ADBException;
import org.example.www.database.MariaDB;
import org.example.www.soundscapedatatypes.SpeakerDevice;
import org.example.www.uploadservice.client.UploadCallbackServiceStub;
import org.example.www.uploadservice.server.ErrorMessage;
import org.example.www.uploadservice.server.UploadServiceSkeletonInterface;
import org.example.www.uploadserviceelements.IsSongLoadedResponse;
import org.example.www.uploadservice.client.UploadCallbackServiceStub.UploadSongResponse;
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
		MariaDB db;
		try {
			MessageContext msgCtx = MessageContext.getCurrentMessageContext();
			System.out.println("test2");
			/*String callbackEndPoint = ((OMElement) ((OMElement) msgCtx.getEnvelope().getHeader()
					.getChildrenWithName(new QName("http://www.apache.org/ode/type/session", "callback")).next())
							.getChildrenWithName(new QName("http://www.w3.org/2005/08/addressing", "Address")).next())
									.getText();*/
			UploadCallbackServiceStub stub = new UploadCallbackServiceStub();//callbackEndPoint
			ServiceClient callbackClient = stub._getServiceClient(); 
			/*SOAP11Factory factory = new SOAP11Factory();
			OMNamespace addrNamespace = factory.createOMNamespace("http://www.w3.org/2005/08/addressing", "addr");
			SOAPHeaderBlock replyToHeader = factory.createSOAPHeaderBlock("ReplyTo", addrNamespace);
			OMElement replyToAddress = factory.createOMElement(new QName("http://www.w3.org/2005/08/addressing", "Address"));
			//replyToAddress.setText(callbackEndPoint); //msgCtx.getMessageID()
			replyToHeader.addChild(replyToAddress);
			
			callbackClient.addHeader(replyToHeader);
			SOAPHeaderBlock callbackOdeHeader = (SOAPHeaderBlock) msgCtx.getEnvelope().getHeader().getHeaderBlocksWithNSURI("http://www.apache.org/ode/type/session").get(0);
			callbackClient.addHeader(callbackOdeHeader);
			SOAPHeaderBlock callbackIntalioHeader = (SOAPHeaderBlock) msgCtx.getEnvelope().getHeader().getHeaderBlocksWithNSURI("http://www.intalio.com/type/session").get(0);
			callbackClient.addHeader(callbackIntalioHeader);
			
			System.out.println("Sending");
			//callbackClient.sendReceive(response.getOMElement(response.MY_QNAME, factory));
			*/
			Thread.sleep(2000);
			UploadSongResponse response = new UploadSongResponse(true);
			stub.uploadSongCallback(response); 
			System.out.println("Send");
			// client.addHeader(header);
			// client.addHeader(null);
			// stub.uploadSongCallback(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
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
			System.out.println(url);
			switch (type) {
			case "http":
				try {
					fileName = DownloadUtils.downloadSong("http://" + url, "");
				} catch (MalformedURLException e) {
					throw new RuntimeException("Malformed url: \"http://" + url + "\": " + e);
				} catch (IOException e) {
					throw new RuntimeException("Unable to download file: " + e);
				}
				break;
			case "https":
				try {
					fileName = DownloadUtils.downloadSong("http://" + url, "");
				} catch (MalformedURLException e) {
					throw new RuntimeException("Malformed url: \"http://" + url + "\": " + e);
				} catch (IOException e) {
					throw new RuntimeException("Unable to download file: " + e);
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
						SpeakerUtils.sendFileToSpeaker(new File(fileName), type + "://" + url,
								speaker.getGeneralDevice().getIpAddress().getIPv4Address(),
								speaker.getGeneralDevice().getPort().getPort().intValue(), db);
					} catch (SQLIntegrityConstraintViolationException e) {
						// Song was already loaded
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException("The database could not be updated with the new song" + e);
					}
				});
			}
			executor.shutdown();
			try {
				executor.awaitTermination(5, TimeUnit.MINUTES);
			} catch (InterruptedException e) {
				// Callback send failure
				return;
			}
			// Callback send success
			return;

		} finally {
			db.cleanUp();
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param isSongLoadedRequest1
	 * @return isSongLoadedResponse2
	 * @throws ErrorMessage
	 * @throws SQLException
	 */

	public org.example.www.uploadserviceelements.IsSongLoadedResponse isSongLoaded(
			org.example.www.uploadserviceelements.IsSongLoadedRequestE isSongLoadedRequest1) throws SQLException {
		MariaDB db;
		try {
			db = new MariaDB(database);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("Unable to create a connection to the database: " + e);
		}
		try {
			SpeakerDevice[] speakers = isSongLoadedRequest1.getIsSongLoadedRequest().getSpeakers().getSpeakerDevice();
			String type = isSongLoadedRequest1.getIsSongLoadedRequest().getLink().getType().getValue();
			String url = isSongLoadedRequest1.getIsSongLoadedRequest().getLink().getAddress();
			// Send file to speakers
			ArrayList<Future<Boolean>> futureList = new ArrayList<Future<Boolean>>();
			ExecutorService executor = Executors.newFixedThreadPool(speakers.length);
			for (SpeakerDevice speaker : speakers) {
				Future<Boolean> future = executor.submit(() -> {
					try {
						return SpeakerUtils.isSongLoadedOnSpeaker(type + "://" + url,
								speaker.getGeneralDevice().getIpAddress().getIPv4Address(),
								speaker.getGeneralDevice().getPort().getPort().intValue(), db);
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException("The database could not be updated with the new song" + e);
					}
				});
				futureList.add(future);
			}
			executor.shutdown();
			IsSongLoadedResponse response = new IsSongLoadedResponse();
			response.setIsSongLoadedResponse(true);
			for (Future<Boolean> future : futureList) {
				try {
					if (!future.get(1, TimeUnit.MINUTES)) {
						response.setIsSongLoadedResponse(false);
						break;
					}
				} catch (ExecutionException e) {
					throw new RuntimeException("Unable to get retrieve the needed information: " + e);
				} catch (TimeoutException e) {
					throw new RuntimeException("Unable to get a timely response from the database: " + e);
				} catch (InterruptedException e) {
					throw new RuntimeException("Unable to get a timely response from the database: " + e);
				}
			}
			// Callback send success
			return response;

		} finally {
			db.cleanUp();
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param deleteSongRequest3
	 * @return
	 * @throws SQLException
	 */

	public void deleteSong(org.example.www.uploadserviceelements.DeleteSongRequestE deleteSongRequest3)
			throws SQLException {
		MariaDB db;
		try {
			db = new MariaDB(database);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("Unable to create a connection to the database: " + e);
		}
		try {
			SpeakerDevice[] speakers = deleteSongRequest3.getDeleteSongRequest().getSpeakers().getSpeakerDevice();
			String type = deleteSongRequest3.getDeleteSongRequest().getLink().getType().getValue();
			String url = deleteSongRequest3.getDeleteSongRequest().getLink().getAddress();
			// Send file to speakers
			ExecutorService executor = Executors.newFixedThreadPool(speakers.length);
			for (SpeakerDevice speaker : speakers) {
				executor.submit(() -> {
					try {
						SpeakerUtils.deleteFileFromSpeaker(type + "://" + url,
								speaker.getGeneralDevice().getIpAddress().getIPv4Address(),
								speaker.getGeneralDevice().getPort().getPort().intValue(), db);
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException("The database could not be updated with the new song" + e);
					}
				});
			}
			executor.shutdown();
			try {
				executor.awaitTermination(5, TimeUnit.MINUTES);
			} catch (InterruptedException e) {
				// Callback send failure
				return;
			}
			// Callback send success
			return;

		} finally {
			db.cleanUp();
		}
	}

}
