package org.rpc.http;

import org.rpc.invoke.Woker;

public class HttpServerimp implements HttpServer {

	public void doGet(Request request, Response response) {
		request.getParamter("");
	}

	public void doPost(Request request, Response response) {
		byte[] s = response.getBytes();
		String result = Woker.work(s);
		System.out.println(result);
		response.print(result);
		response.flush();
	}
}
