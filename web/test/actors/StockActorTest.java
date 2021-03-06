package actors;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import utils.StockQuote;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

import static org.assertj.core.api.Assertions.*;

public class StockActorTest {
	
	static ActorSystem system;
	
	@BeforeClass
	public static void setup() {
		system = ActorSystem.create("test");
	}
	
	@AfterClass
	public static void teardown() {
		JavaTestKit.shutdownActorSystem(system);
		system = null;
	}
	
	public static class FixedStockQuote implements StockQuote {
		
		Double price;
		
		public FixedStockQuote(Double price) {
			this.price = price;
		}

		@Override
		public Double newPrice(Double lastPrice) {
			return price;
		}
		
	}
	
	@Test
	public void stockActorShouldNotifyWatchers() {
		running(fakeApplication(), () -> new JavaTestKit(system) {{
			String symbol = "ABC";
			double price = 1234;
			
			Props props = Props.create(StockActor.class, symbol, new FixedStockQuote(price), /* tick */ false);
			ActorRef stockActor = system.actorOf(props, "stockActor");
			
			// receive stock.history when adding a watcher with stock.watch
			stockActor.tell(new Stock.Watch(symbol), getRef());
			Stock.History history = expectMsgClass(Stock.History.class);
			
			// receive stock.update on stock.latest tick
			stockActor.tell(Stock.latest, getRef());
			Stock.Update update = expectMsgClass(Stock.Update.class);
			assertThat(update.symbol).isEqualTo(symbol);
			assertThat(update.price).isEqualTo(price);
		}});
	}

}
