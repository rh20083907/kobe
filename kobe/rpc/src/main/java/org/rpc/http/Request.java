package org.rpc.http;

import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;

@SuppressWarnings("restriction")
public class Request {

	private HttpExchange httpExchange;

	public Map<String, Object> map = new HashMap<>();

	public Object put(String key, Object value) {
		return map.put(key, value);
	}

	public Object getParamter(String key) {
		return map.get(key);
	}

	public void setPara(String s1) {
		// TODO Auto-generated method stub
	}

	public HttpExchange getHttpExchange() {
		return httpExchange;
	}

	public void setHttpExchange(HttpExchange httpExchange) {
		this.httpExchange = httpExchange;
	}
}
