package org.rpc.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;

import org.rpc.util.ExceptionUtil;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;

@SuppressWarnings("restriction")
public class HttpProviderImpl implements httpProvider {

	public static void main(String[] args) throws IOException {
		HttpServerProvider provider = HttpServerProvider.provider();
		HttpServer httpserver = provider.createHttpServer(new InetSocketAddress(8080), 100);
		httpserver.createContext("/rpc", new HttpHandler() {
			public void handle(HttpExchange arg0) throws IOException {
				System.out.println("accept an exchange from internet.....");
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
		Request request = new Request();
		Response response = new Response();
		response.setHttpExchange(httpExchange);
		request.setHttpExchange(httpExchange);
		HttpServerimp httpServer = new HttpServerimp();
		httpServer.doGet(request, response);
	}

	@Override
	public void start(String path, int port) {
		HttpServerProvider provider = HttpServerProvider.provider();
		HttpServer httpserver = null;
		try {
			httpserver = provider.createHttpServer(new InetSocketAddress(port), 100);
		} catch (IOException e) {
			e.printStackTrace();
			throw ExceptionUtil.newRuntimeException(e);
		}
		httpserver.createContext("/rpc", new HttpHandler() {
			public void handle(HttpExchange arg0) throws IOException {
				System.out.println("accept an exchange from internet.....");
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
}
