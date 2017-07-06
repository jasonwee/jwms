package models;

import javax.inject.Inject;

import akka.actor.ActorSystem;
import scala.concurrent.ExecutionContext;
import scala.concurrent.ExecutionContextExecutor;

/**
 * custom execution context wired to "database.dispatcher" thread pool
 * @author jason
 *
 */
public class DatabaseExecutionContext implements ExecutionContextExecutor {
	
	private final ExecutionContext executionContext;
	
	private static final String name = "database.dispatcher";
	
	@Inject
	public DatabaseExecutionContext(ActorSystem actorSystem) {
		this.executionContext = actorSystem.dispatchers().lookup(name);
	}
	
	@Override
	public ExecutionContext prepare() {
		return executionContext.prepare();
	}

	@Override
	public void execute(Runnable command) {
		executionContext.execute(command);
	}

	@Override
	public void reportFailure(Throwable cause) {
		executionContext.reportFailure(cause);
	}

}
