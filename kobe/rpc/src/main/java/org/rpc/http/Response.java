package org.rpc.http;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

import org.rpc.util.ExceptionUtil;

public class Response {

	private HttpExchange httpExchange;

	private StringBuilder sb = new StringBuilder();

	public void print(String str) {
		sb.append(str);
	}

	public void flush() {
		String resp = sb.toString();
		try {
			httpExchange.sendResponseHeaders(200, resp.getBytes().length);
			OutputStream out = httpExchange.getResponseBody();
			out.write(resp.getBytes());
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HttpExchange getHttpExchange() {
		return httpExchange;
	}

	public void setHttpExchange(HttpExchange httpExchange) {
		this.httpExchange = httpExchange;
	}

}
