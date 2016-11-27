package org.rpc.regist;

import java.util.List;

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
		return super.equals(obj);
	}
}
