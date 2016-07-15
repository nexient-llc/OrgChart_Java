package com.nexient.validation;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by kskronek on 6/9/2016.
 */
public class Validator {

	public Validator() {

	}

	public void validate(Object obj) throws RuntimeException {
		validateFields(obj);
	}

	private void validateFields(Object obj) {
		for (Field field : obj.getClass().getDeclaredFields()) {
			validateFieldAnnotations(obj, field.getDeclaredAnnotations());
		}
	}

	private void validateFieldAnnotations(Object obj, Annotation[] annotations) {
		for (Annotation a : annotations) {
			validateFieldAnnotation(obj, a);
		}
	}

	private void validateFieldAnnotation(Object obj, Annotation a) {
		final String name = a.annotationType().getSimpleName();

		try {
			final Method method = this.getClass().getDeclaredMethod("validate" + name, Annotation.class);
			method.invoke(this, a);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}

		System.out.println(a.getClass().getCanonicalName());
	}

	private static void validateColumn(Object obj, Annotation a) {
		final Map<String, Object> attributes = AnnotationUtils.getAnnotationAttributes(a);
		final Integer length = (Integer) attributes.get("length");
		validateLength(obj, length);
		//		((AnnotationDescription.AnnotationInvocationHandler) a.h).memberValues.get("nullable");
		System.out.println(a.getClass().getCanonicalName());
	}

	private static void validateLength(Object obj, Integer length) {
		if (null != obj) {
			if (obj instanceof String) {
				String str = (String) obj;
				Assert.isTrue(str.length() < length);
			}
		}
	}
}
