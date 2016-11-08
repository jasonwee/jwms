package co.weetech.jwms;

import java.io.IOException;

public class Application {
	
	public static void main(String[] args) throws IOException {
		// jenkins url string
		String urlString = "";
		
		// jenkins crumb 
		String crumb = "";
		
		// jenkins username
		String username = "";
		
		// jenkins api associated with the user
		String password = "";
		
		// the message in hex
		String message = "DONE";
		
		// the result in numeric, 0 is success , others fail
		String result = "0";
		
		// duration in milli seconds
		String duration = "2000";
		
		// the name to display in jenkins for this run
		String displayName = "local test";
		
		// the more elaborative description for this run
		String description="";
		
		// how to actually initalize and send it.
		Jwms agent = new Jwms(urlString, crumb, username, password, null);
		agent.send(message, result, duration, displayName, description);
		
		// TODO
		// we need rpm spec
	}

}
