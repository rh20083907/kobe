package org.rpc.util;

public class ExceptionUtil {
	public static RuntimeException newRuntimeException(Exception e) {
		return new RuntimeException(e);
	}
}
