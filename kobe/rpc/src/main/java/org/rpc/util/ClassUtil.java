package org.rpc.util;

public class ClassUtil {
	public static String toString(Class<?>... cs) {
		if (cs == null || cs.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Class<?> c : cs) {
			sb.append(c.getName());
		}
		return sb.toString();
	}
}
