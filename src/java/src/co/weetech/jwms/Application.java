package co.weetech.jwms;

import java.io.IOException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

	
/**
 * java -cp 
 * /home/jason/.gradle/caches/modules-2/files-2.1/org.slf4j/slf4j-api/1.7.21/139535a69a4239db087de9bab0bee568bf8e0b70/slf4j-api-1.7.21.jar:
 * /home/jason/.gradle/caches/modules-2/files-2.1/org.slf4j/slf4j-log4j12/1.7.21/7238b064d1aba20da2ac03217d700d91e02460fa/slf4j-log4j12-1.7.21.jar:
 * /home/jason/.gradle/caches/modules-2/files-2.1/log4j/log4j/1.2.17/5af35056b4d257e4b64b9e8069c0746e8b08629f/log4j-1.2.17.jar:
 * jwms-java-lib-0.1.0.jar:. co.weetech.jwms.Application
 * 
 * @author jason
 *
 */
public class Application {
	
	private static Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner(System.in);
		// jenkins url string
		logger.info("What is jenkins url?");
		String urlString = scanner.nextLine();
		if (urlString == null || urlString.isEmpty()) {
			logger.error("jenkins url is required");
		}
		
		// jenkins job name
		logger.info("What is jenkins jobName?");
		String jobName = scanner.nextLine();
		if (jobName == null || jobName.isEmpty()) {
			logger.error("jenkins url is required");
		}
		
		// jenkins crumb 
		logger.info("What is jenkins crumb?");
		String crumb = scanner.nextLine();
		if (crumb == null || crumb.isEmpty()) {
			logger.error("jenkins crumb is required");
		}
		
		// jenkins username
		logger.info("What is jenkins username?");
		String username = scanner.nextLine();
		if (username == null || username.isEmpty()) {
			logger.error("jenkins username is required");
		}
		
		// jenkins api associated with the user
		logger.info("What is jenkins api?");
		String password = scanner.nextLine();
		if (password == null || password.isEmpty()) {
			logger.error("jenkins api is required");
		}
		
		// the message in hex
		logger.info("What is message to send to jenkins? [DONE]");
		String message = scanner.nextLine();
		if (message == null || message.isEmpty()) {
			logger.error("jenkins message is required");
		}
		
		// the result in numeric, 0 is success , others fail
		logger.info("What is the result of this? [0 for success / 1 for fail] ");
		String result = scanner.nextLine();
		if (result == null || result.isEmpty()) {
			logger.error("jenkins result is required");
		}
		
		// duration in milli seconds
		logger.info("What is the duration of this job? [2000]");
		String duration = scanner.nextLine();
		if (duration == null || duration.isEmpty()) {
			logger.error("jenkins duration is required");
		}
		
		// the name to display in jenkins for this run
		logger.info("What is this job name? [local test]");
		String displayName = scanner.nextLine();
		if (displayName == null || displayName.isEmpty()) {
			logger.error("jenkins displayName is required");
		}
		
		// the more elaborative description for this run
		logger.info("What is this job description? [hello world]");
		String description=scanner.nextLine();
		if (description == null || description.isEmpty()) {
			logger.error("jenkins description is required");
		}
		
		// how to actually initalize and send it.
		logger.info(String.format("urlString='%s' jobName='%s' crumb='%s' username='%s' password='%s'", urlString, 
				jobName, crumb, username, password));
		logger.info(String.format("message='%s' result='%s' duration='%s' displayName='%s'", message, result, duration, 
				displayName));
		logger.info(String.format("description='%s'", description));
		
		Jwms.setLogger(logger);
		Jwms agent = new Jwms(urlString, jobName, crumb, username, password);
		agent.send(message, result, duration, displayName, description);

	}

}
