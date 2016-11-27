package org.rpc.http;

public interface HttpServer {
	public void doGet(Request request, Response response);

	public void doPost(Request request, Response response);
}
