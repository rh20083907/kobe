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

	public static boolean equals(Object obj, Object obj1) {
		if (obj == null && obj1 == null) {
			return true;
		}
		if (obj != null && obj1 == null) {
			return false;
		}
		return obj.equals(obj1);
	}
}
