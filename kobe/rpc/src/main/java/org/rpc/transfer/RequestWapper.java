package org.rpc.transfer;

import java.io.Serializable;

public class RequestWapper implements Serializable {
	private static final long serialVersionUID = 1L;
	public String packagaName;
	public String className;
	public String method;
	public Object[] obj;
	public Class<?>[] parameterClass;

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

	public Object[] getObj() {
		return obj;
	}

	public void setObj(Object[] obj) {
		this.obj = obj;
	}

	public Class<?>[] getParameterClass() {
		return parameterClass;
	}

	public void setParameterClass(Class<?>[] parameterClass) {
		this.parameterClass = parameterClass;
	}

}
