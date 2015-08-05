package com.intuit.sample.java8;

public class LambdaScope {
	final static int num = 1;

	public static void main(String... args) {
		Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
		System.out.println("Static final int=" + stringConverter.convert(2));

		int num2 = 2;
		Converter<Integer, String> stringConverter1 = (from) -> String.valueOf(from + num2);
		System.out.println("Local int=" + stringConverter1.convert(2));

		/**
		 * compile time error: Local variable num3 defined in an enclosing scope
		 * must be final or effectively final
		 */
		//		int num3 = 3;
		//		Converter<Integer, String> stringConverter2 = (from) -> String.valueOf(from + num3);
		//		System.out.println("Local int later updated =" + stringConverter1.convert(2));
		//		num3 = 4;
	}

	// functional interface
	interface Converter<T, E> {
		E convert(T num);
	}
}
