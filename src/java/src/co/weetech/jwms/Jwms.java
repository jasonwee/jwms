/*
 *  Copyright 2016 Jason Wee
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package co.weetech.jwms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.logging.Logger;

import co.weetech.util.StringUtil;

public class Jwms {
	
	private static Logger logger = null; 
	
	private static String USER_AGENT = "jwms-lib";
	private String urlString;
	private String crumb;
	private String username;
	private String password;
	
	public Jwms(String url, String crumb, String username, String password, Logger logger) {
		this.urlString = url;
		this.crumb = crumb;
		this.username = username;
		this.password = password;
	}
	
	public boolean send(String message, String result, String duration, String displayName, String description) 
			throws IOException {
		
		URL url = new URL(urlString);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		
		String userpass = username + ":" + password;
		String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
		
		// all the request header required.
		httpCon.setRequestMethod("POST");
		httpCon.setRequestProperty("User-Agent", USER_AGENT);
		httpCon.setRequestProperty(".crumb", crumb);
		httpCon.setRequestProperty("Authorization", basicAuth);
		httpCon.setDoOutput(true);
		
		String jenkinsString= "<run><log encoding=\"hexBinary\">%s</log><result>%s</result><duration>%s</duration>"
				+ "<displayName>%s</displayName><description>%s</description></run>";
		byte[] outputBytes = String.format(jenkinsString, StringUtil.toHex(message), result, duration, displayName, description).getBytes();
		OutputStream os = httpCon.getOutputStream();
		os.write(outputBytes);
		os.flush();
		os.close();

		int responseCode= httpCon.getResponseCode();
		if (getLogger() != null) {
			getLogger().info(String.format("url '%s' response code '%s'", urlString, responseCode));
		}
		
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();
			
			if (getLogger() != null) {
				getLogger().info(String.format("response '%s'", response.toString()));
			}
			return true;
		} else {
			return false;
		}
	}
	
	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		Jwms.logger = logger;
	}
	
}
