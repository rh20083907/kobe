package org.rpc.scan;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import org.rpc.util.ExceptionUtil;

public class PackageScanImpl implements PackageScan {
	@Override
	public Set<Class<?>> scan(String packagePath) {
		String packageDirName = packagePath.replace('.', '/');
		Enumeration<URL> dirs = null;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		Set<Class<?>> set = new HashSet<>();
		doScan(set, dirs, packagePath);
		return set;
	}

	private Set<Class<?>> doScan(Set<Class<?>> set, Enumeration<URL> dirs, String packagePath) {
		while (dirs.hasMoreElements()) {
			URL url = dirs.nextElement();
			String protocol = url.getProtocol();
			File f = new File(url.getFile());
			if (f.isDirectory()) {
				doScanDir(set, packagePath, f);
			}
		}
		return null;
	}

	private void doScanDir(Set<Class<?>> set, String packageDirName, File f) {
		if (!f.isDirectory()) {
			return;
		}
		File[] fs = f.listFiles();
		for (File o : fs) {
			if (o.isDirectory()) {
				this.doScanDir(set, packageDirName + "." + o.getName(), o);
			} else {
				try {
					set.add(Class.forName(packageDirName + "." + o.getName().replace(".class", "")));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					throw ExceptionUtil.newRuntimeException(e);
				}
			}
		}
	}
}
