package org.rpc.util;

import java.lang.annotation.Annotation;

import org.rpc.annotation.Waiter;

@Waiter
public class AnntationUtil {
	public static <A extends Annotation> A findAnnotation(Class<?> clazz, Class<A> clazzAnn) {
		Annotation[] annotations = clazz.getAnnotations();
		for (Annotation a : annotations) {
			if (a.annotationType() == clazzAnn) {
				return (A) a;
			}
		}
		return null;
	}
}
