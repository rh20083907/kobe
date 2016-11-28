package org.rpc.regist;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.rpc.annotation.Waiter;
import org.rpc.scan.PackageScan;
import org.rpc.scan.PackageScanImpl;
import org.rpc.transfer.RequestWapper;
import org.rpc.util.AnntationUtil;
import org.rpc.util.ExceptionUtil;

public class ServiceRegister {

	private ServiceRegister() {
	}

	private static ServiceRegister sing = new ServiceRegister();

	public static Map<RegKey, Object> map = new ConcurrentHashMap<>();
	public PackageScan packageScan = new PackageScanImpl();

	public void reg(String packageName) {
		Set<Class<?>> set = packageScan.scan(packageName);
		for (Class<?> clazz : set) {
			if (AnntationUtil.findAnnotation(clazz, Waiter.class) != null) {
				regService(clazz);
			}
		}
	}

	private void regService(Class<?> clazz) {
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			regMethod(clazz, method);
		}
	}

	private void regMethod(Class<?> clazz, Method method) {
		Class<?>[] clazzs = method.getParameterTypes();
		String pack = clazz.getInterfaces()[0].getPackage().getName();
		List<String> list = Arrays.asList(clazzs).stream().map(s -> s.getName()).collect(Collectors.toList());
		RegKey requestWapper = new RegKey();
		requestWapper.setClassName(clazz.getInterfaces()[0].getName());
		requestWapper.setMethod(method.getName());
		requestWapper.setPackagaName(pack);
		requestWapper.setParameterType(list);
		try {
			Object obj = clazz.newInstance();
			map.put(requestWapper, obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.newRuntimeException(e);
		}
	}

	public Object get(RegKey RegKey) {
		return map.get(RegKey);
	}

	public static ServiceRegister getSing() {
		return sing;
	}

}
