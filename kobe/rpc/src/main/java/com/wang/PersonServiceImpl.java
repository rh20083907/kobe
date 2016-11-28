package com.wang;

import org.rpc.annotation.Waiter;

@Waiter // 标记waiter 就可以发布服务了。
public class PersonServiceImpl implements PersonService {
	public String say(String name) {
		return " hello to " + name;
	}
}
