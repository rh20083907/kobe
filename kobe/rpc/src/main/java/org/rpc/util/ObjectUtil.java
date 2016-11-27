package org.rpc.util;

public class ObjectUtil {
	public static int hashCode(Object obj) {
		if (obj == null) {
			return 0;
		}
		return obj.hashCode();
	}

	public static int hashCode(Object... obj) {
		if (obj == null || obj.length == 0) {
			return 0;
		}
		int i = 0;
		for (Object o : obj) {
			i += hashCode(o);
		}
		return i;
	}
}
