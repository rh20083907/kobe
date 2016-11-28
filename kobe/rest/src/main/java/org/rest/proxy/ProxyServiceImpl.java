package org.rest.proxy;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

import org.rest.util.Json;
import org.rest.util.SerialiUtil;
import org.rpc.transfer.RequestWapper;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ProxyServiceImpl implements ProxyService {
	@Override
	public <T> T getObj(Class<T> t) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(t);
		enhancer.setCallback(new MethodInterceptor() {
			public Object intercept(Object obj, Method method, Object[] paras, MethodProxy methodProxy)
					throws Throwable {
				RequestWapper requestWapper = new RequestWapper();
				requestWapper.setClassName(t.getName());
				requestWapper.setMethod(method.getName());
				requestWapper.setObj(paras);
				requestWapper.setPackagaName(t.getPackage().getName());
				requestWapper.setParameterClass(method.getParameterTypes());
				byte[] bytes = SerialiUtil.ObjectToByte(requestWapper);
				URL url = new URL("http://127.0.0.1:8083/rpc");
				HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
				urlConn.setRequestProperty("Content-Length", bytes.length + "");
				urlConn.setDoOutput(true);
				urlConn.setDoInput(true);
				urlConn.setRequestMethod("POST");
				OutputStream out = urlConn.getOutputStream();
				out.write(bytes);
				out.flush();
				InputStream in = urlConn.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				String temp = null;
				StringBuilder sb = new StringBuilder();
				while ((temp = reader.readLine()) != null) {
					sb.append(temp);
				}
				reader.close();
				in.close();
				urlConn.disconnect();
				Object returObj = Json.getObj(sb.toString(), method.getReturnType());
				return returObj;
			}
		});
		return (T) enhancer.create();
	}
}
