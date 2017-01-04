package controllers;

import java.util.concurrent.CompletableFuture;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.ws.WebSocket;
import org.asynchttpclient.ws.WebSocketListener;
import org.asynchttpclient.ws.WebSocketUpgradeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketClient {
	
	private AsyncHttpClient client;
	
	public WebSocketClient(AsyncHttpClient c) {
		this.client = c;
	}
	
	public CompletableFuture<WebSocket> call(String url, WebSocketListener listener) {
		BoundRequestBuilder requestBuilder = client.prepareGet(url);
		
		final WebSocketUpgradeHandler handler = new WebSocketUpgradeHandler.Builder().addWebSocketListener(listener).build();
		final ListenableFuture<WebSocket> future = requestBuilder.execute(handler);
		final CompletableFuture<WebSocket> completableFuture = future.toCompletableFuture();
		return completableFuture;
	}
	
	static class LoggingListener implements WebSocketListener {
		
		private Logger logger = LoggerFactory.getLogger(LoggingListener.class);
		
		private Throwable throwableFound = null;
		
		public Throwable getThrowable() {
			return throwableFound;
		}

		@Override
		public void onOpen(WebSocket websocket) {
			logger.info("On open: ");
			websocket.sendMessage("hello");
		}

		@Override
		public void onClose(WebSocket websocket) {
			logger.info("onClose: ");
		}

		@Override
		public void onError(Throwable t) {
			logger.error("onError: ", t);
			throwableFound = t;
		}
		
	}

}
