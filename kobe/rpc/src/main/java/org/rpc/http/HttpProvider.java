package org.rpc.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;

@SuppressWarnings("restriction")
public class HttpProvider {
	public static void main(String[] args) throws IOException {
		HttpServerProvider provider = HttpServerProvider.provider();
		HttpServer httpserver = provider.createHttpServer(new InetSocketAddress(8080), 100);
		httpserver.createContext("/rpc", new HttpHandler() {
			public void handle(HttpExchange arg0) throws IOException {
				System.out.println("accept an exchange from internet.....");
				String resp = "your request message i get it!";
//				arg0.sendResponseHeaders(200, resp.getBytes().length);
//				OutputStream out = arg0.getResponseBody();
//				out.write(resp.getBytes());
//				out.flush();
				if (arg0.getRequestMethod().equals("GET")) {
					doGet(arg0);
				} else if (arg0.getRequestMethod().equals("POST")) {
					doPost(arg0);
				} else {
				}
				arg0.close();
			}
		});
		httpserver.setExecutor(null);
		httpserver.start();
		System.out.println("server started");
	}

	public static void doGet(HttpExchange httpExchange) {
		URI uri = httpExchange.getRequestURI();
		String strUri = uri.toString();
		Request request = new Request();
		Response response = new Response();
		request.setPara(strUri);
		response.setHttpExchange(httpExchange);
		HttpServerimp httpServer = new HttpServerimp();
		httpServer.doGet(request, response);
	}

	public static void doPost(HttpExchange httpExchange) {

	}
}
