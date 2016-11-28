package org.rpc.http;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.spi.*;

import org.rpc.util.ExceptionUtil;

public class Response {

	private HttpExchange httpExchange;
	private String data = null;

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

	public byte[] getBytes() {
		InputStream in = httpExchange.getRequestBody();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int i = 0;
		try {
			while ((i = in.read()) > -1) {
				out.write(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw ExceptionUtil.newRuntimeException(e);
		}
		return out.toByteArray();
	}

	public String getDate() {
		if (data != null) {
			return data;
		}
		InputStream in = httpExchange.getRequestBody();
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader bf = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder();
		String s = "";
		try {
			while ((s = bf.readLine()) != null) {
				sb.append(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
			ExceptionUtil.newRuntimeException(e);
		}
		data = sb.toString();
		return data;
	}

}
