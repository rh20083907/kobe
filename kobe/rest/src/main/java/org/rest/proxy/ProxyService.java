package org.rest.proxy;

public interface ProxyService {
	public <T> T getObj(Class<T> t);
}
