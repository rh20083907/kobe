package org.rpc.http;

public class HttpServerimp implements HttpServer {

	public void doGet(Request request, Response response) {
		response.print("hello");
		response.print("world ");
		response.flush();
	}

	public void doPost(Request request, Response response) {

	}
}
