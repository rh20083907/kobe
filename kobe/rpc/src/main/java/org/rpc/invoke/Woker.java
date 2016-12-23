package org.rpc.invoke;

import java.lang.reflect.Method;

import org.rpc.regist.RegKey;
import org.rpc.regist.ServiceRegister;
import org.rpc.transfer.RequestWapper;
import org.rpc.util.ExceptionUtil;
import org.rpc.util.Json;
import org.rpc.util.SerialiUtil;

public class Woker {

	public static String work(byte[] bytes) {
		RequestWapper requestWapper = SerialiUtil.ByteToObject(bytes);
		RegKey regKey = RegKey.getRegKey(requestWapper);
		ServiceRegister register = ServiceRegister.getSing();
		Object obj = register.get(regKey);
		Method method = null;
		Object retult = null;
		try {
			method = obj.getClass().getMethod(requestWapper.getMethod(), requestWapper.getParameterClass());
			retult = method.invoke(obj, requestWapper.getObj());
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.newRuntimeException(e);
		}
		return Json.toJson(retult);
	}

}
