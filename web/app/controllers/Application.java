package controllers;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.inject.Singleton;

import actors.HelloActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import play.mvc.Controller;
import play.mvc.Result;
import scala.compat.java8.FutureConverters;

import static akka.pattern.Patterns.ask;
import actors.HelloActorProtocol.SayHello;



@Singleton
public class Application extends Controller {
	
	final ActorRef helloActor;
	
	@Inject
	public Application(ActorSystem system) {
		helloActor = system.actorOf(HelloActor.props);
	}
	
	public CompletionStage<Result> sayHello(String name) {
		return FutureConverters.toJava(ask(helloActor, new SayHello(name), 1000))
				.thenApply(response -> ok ((String) response));
	}

}
