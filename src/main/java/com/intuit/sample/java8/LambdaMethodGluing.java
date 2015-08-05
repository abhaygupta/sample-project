package com.intuit.sample.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaMethodGluing {

	/**
	 * http://winterbe.com/posts/2014/03/16/java-8-tutorial/
	 * 
	 * Theory: Each lambda corresponds to a given type, specified by an
	 * interface. A so called functional interface must contain exactly one
	 * abstract method declaration. Each lambda expression of that type will be
	 * matched to this abstract method. Since default methods are not abstract
	 * you're free to add default methods to your functional interface.
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		// old way
		List<String> list = Arrays.asList("peter", "anna", "mike", "xenia");
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				return a.compareTo(b);
			}
		});
		System.out.println("List=" + list);

		// using lamda expression
		list = Arrays.asList("peter", "anna", "mike", "xenia");
		Collections.sort(list, (String a, String b) -> {
			return a.compareTo(b);
		});
		System.out.println("Lamda : 1 List=" + list);

		// OR lamda - even better as type of variable is know to java
		list = Arrays.asList("peter", "anna", "mike", "xenia");
		Collections.sort(list, (a, b) -> {
			return a.compareTo(b);
		});
		System.out.println("Lamda : 2 List=" + list);

		// OR lamda - even better
		list = Arrays.asList("peter", "anna", "mike", "xenia");
		Collections.sort(list, (a, b) -> a.compareTo(b));
		System.out.println("Lamda : 3 List=" + list);
	}
}
