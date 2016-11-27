package org.rpc.http;

import com.sun.net.httpserver.HttpExchange;

public interface httpProvider {
	public void start(String path, int port);
}
