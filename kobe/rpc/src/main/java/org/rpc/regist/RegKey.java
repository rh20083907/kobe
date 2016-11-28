package org.rpc.regist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.rpc.transfer.RequestWapper;
import org.rpc.util.Json;
import org.rpc.util.ObjectUtil;

public class RegKey {
	public String packagaName;
	public String className;
	public String method;
	public List<String> parameterType;

	public String getPackagaName() {
		return packagaName;
	}

	public void setPackagaName(String packagaName) {
		this.packagaName = packagaName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<String> getParameterType() {
		return parameterType;
	}

	public void setParameterType(List<String> parameterType) {
		this.parameterType = parameterType;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof RegKey)) {
			return false;
		}
		RegKey o = (RegKey) obj;
		if (ObjectUtil.equals(o.className, this.className) && ObjectUtil.equals(o.className, this.className)
				&& ObjectUtil.equals(o.method, this.method) && ObjectUtil.equals(o.packagaName, this.packagaName)
				&& ObjectUtil.equals(o.parameterType, this.parameterType)) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return ObjectUtil.hashCode(className, method, packagaName, parameterType);
	}

	public static void main(String[] args) {
		Map<RegKey, Object> map=new HashMap<>();
		RegKey r1=new RegKey();
		r1.className="111";
		r1.method="333";
		r1.packagaName="12";
		r1.parameterType=Arrays.asList("123");
		RegKey r2= Json.getObj( Json.toJson(r1),RegKey.class);
		System.out.println(r1==r2);
		map.put(r1, new Object());
		System.out.println(map.get(r2));
	}

	public static RegKey getRegKey(RequestWapper requestWapper) {
		RegKey regKey = new RegKey();
		regKey.setClassName(requestWapper.getClassName());
		regKey.setMethod(requestWapper.getMethod());
		regKey.setPackagaName(requestWapper.getPackagaName());
		List<String> list = new ArrayList<>();
		for (Class<?> c : requestWapper.getParameterClass()) {
			list.add(c.getName());
		}
		regKey.setParameterType(list);
		return regKey;
	}
}
