package org.example.www.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadUtils {
	private static final int BUFFER_SIZE = 4096;
	private static final List<String> allowedExtensions = new ArrayList<String>();

	public static String downloadSong(String fileURL, String saveDir) throws MalformedURLException, IOException, RuntimeException {
		String fileName = "";
		URL url = new URL(fileURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		int responseCode = httpConn.getResponseCode();

		// always check HTTP response code first
		if (responseCode == HttpURLConnection.HTTP_OK) {
			String disposition = httpConn.getHeaderField("Content-Disposition");

			if (disposition != null) {
				// extracts file name from header field
				int index = disposition.indexOf("filename=");
				if (index > 0) {
					fileName = disposition.substring(index + 10, disposition.length() - 1);
				}
			} else {
				// extracts file name from URL
				fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
			}

			String[] splitFilename = fileName.split(".");
			if (allowedExtensions.contains(splitFilename[splitFilename.length])) {
				// opens input stream from the HTTP connection
				InputStream inputStream = httpConn.getInputStream();
				String saveFilePath = saveDir + File.separator + fileName;

				// opens an output stream to save into file
				FileOutputStream outputStream = new FileOutputStream(saveFilePath);

				int bytesRead = -1;
				byte[] buffer = new byte[BUFFER_SIZE];
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				outputStream.close();
				inputStream.close();

			} else {
				throw new RuntimeException("Illegal file extension: \"" + splitFilename[splitFilename.length] + "\"");
			}
		} else {
			throw new RuntimeException("No file to download. Server replied HTTP code: " + responseCode);
		}
		httpConn.disconnect();
		return fileName;
	}

}
