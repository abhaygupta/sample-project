package com.intuit.sample.java8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;

import org.junit.Test;

public class AnnotationsExample {

	/**
	 * Annotations in Java 8 are repeatable. Let's dive directly into an example
	 * to figure that out.
	 */

	@interface Hints {
		Hint[]value();
	}

	@Repeatable(Hints.class)
	@interface Hint {
		String value();
	}

	/**
	 * Java 8 enables us to use multiple annotations of the same type by
	 * declaring the annotation @Repeatable.
	 * 
	 */

	@Hints({ @Hint("person is a human"), @Hint("person can be male/female") })
	class Person1 {
	}

	// OR

	@Hint("person is a human")
	@Hint("person can be male/female")
	class Person2 {
	}

	// Java 8 is expanded to two new targets
	@Target({ ElementType.TYPE_PARAMETER, ElementType.TYPE_USE })
	@interface MyAnnotation {
	}

	/**
	 * Read more: Arrays.parallelSort, StampedLock and CompletableFuture
	 */

	@Test
	public void testRepetableAnnotations() {
		for (Hint hint : Person1.class.getAnnotationsByType(Hint.class)) {
			System.out.println("Annotation for person1=" + hint.value());
		}

		for (Hint hint : Person2.class.getAnnotationsByType(Hint.class)) {
			System.out.println("Annotation for person2=" + hint.value());
		}

	}

}
