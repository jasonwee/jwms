package controllers;

import java.util.concurrent.CompletableFuture;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.ws.WebSocket;
import org.asynchttpclient.ws.WebSocketListener;
import org.asynchttpclient.ws.WebSocketUpgradeHandler;

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

}
