package com.kmecpp.nmm;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;

public class Files {

	/**
	 * High performance read from an {@link InputStream} into a String
	 * 
	 * @param inputStream
	 *            the input stream from which to read
	 * @return the string read from the reader
	 * @throws IOException
	 *             if an IOException occurs
	 */
	public static String read(URL url) throws IOException {
		InputStreamReader reader = new InputStreamReader(url.openStream());
		StringWriter sw = new StringWriter();
		char[] buffer = new char[4096];
		int pos = 0;
		while ((pos = reader.read(buffer)) != -1) {
			sw.write(buffer, 0, pos);
		}
		return sw.toString();
	}

}
