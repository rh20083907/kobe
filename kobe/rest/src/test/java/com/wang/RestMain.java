package com.wang;

import org.rest.proxy.ProxyService;
import org.rest.proxy.ProxyServiceImpl;

public class RestMain {
	public static void main(String[] args) {
		ProxyService proxyService = new ProxyServiceImpl();
		PersonService p = proxyService.getObj(PersonService.class);
		String str = p.say(" nihao ");
		System.out.println(str);
		System.out.println(p.add(1, 2));
	}
}
