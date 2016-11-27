package org.rpc.scan;

import java.util.Set;

public interface PackageScan {
	public Set<Class<?>> scan(String packagePath);
}
