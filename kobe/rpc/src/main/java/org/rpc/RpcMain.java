package org.rpc;

import org.rpc.http.HttpProviderImpl;
import org.rpc.http.httpProvider;
import org.rpc.regist.ServiceRegister;
import org.rpc.regist.ServiceRegister;

public class RpcMain {
	public static void main(String[] args) {
		ServiceRegister serviceRegister =  ServiceRegister.getSing();
		serviceRegister.reg("com.wang");
		httpProvider http = new HttpProviderImpl();
		http.start("wang", 8080);
	}
}
