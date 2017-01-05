package actors;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import akka.testkit.TestActorRef;
import akka.testkit.TestProbe;
import play.Configuration;
import play.Environment;

public class UserActorTest {
	
	static TestProbe out;
	static TestProbe stocksActor;
	static ActorSystem system;
	static Configuration configuration;
	static TestActorRef<UserActor> userActor;
	
	@BeforeClass
	public static void setup() {
		system = ActorSystem.create("test");
		out = new TestProbe(system, "out");
		stocksActor = new TestProbe(system, "stocksActor");
		configuration = Configuration.load(Environment.simple());
		Props props = Props.create(UserActor.class, (Creator<UserActor>) () -> 
		    new UserActor(out.ref(), stocksActor.ref(), configuration)
		);
		userActor = TestActorRef.create(system, props, "userActor");
	}
	
	@AfterClass
	public static void testdown() {
		JavaTestKit.shutdownActorSystem(system);
		system = null;
		userActor = null;
		stocksActor = null;
		out = null;
		configuration = null;
	}
	
	@Test
	public void userActorShouldSendStockUpdate() {
		running(fakeApplication(), () -> {
			String symbol = "ABC";
			double price = 123;
			
			// send off the stock update ...
			userActor.receive(new Stock.Update(symbol, price));
			
			final FiniteDuration duration = JavaTestKit.duration("1 second");
			JsonNode output = (JsonNode) out.receiveOne(duration);
			
			// ... and expect it to be a JSON node
			assertThat(output.get("type").asText()).isEqualTo("stockupdate");
			assertThat(output.get("symbol").asText()).isEqualTo(symbol);
			assertThat(output.get("price").asDouble()).isEqualTo(price);
		});
	}
	
	@Test
	public void userActorShouldSendStockHistory() {
		running(fakeApplication(), () -> {
			String symbol = "ABC";
			Double[] history = { 0.1, 1.0};
			
			// send off the stock history
			userActor.receive(new Stock.History(symbol, history));
			
			final FiniteDuration duration = JavaTestKit.duration("1 second");
			JsonNode output = (JsonNode) out.receiveOne(duration);
			
			// and expect it to be a json node
			assertThat(output.get("type").asText()).isEqualTo("stockhistory");
			assertThat(output.get("symbol").asText()).isEqualTo(symbol);
			assertThat(output.get("history").get(0).asDouble()).isEqualTo(history[0]);
			assertThat(output.get("history").get(1).asDouble()).isEqualTo(history[1]);
		});
	}
}