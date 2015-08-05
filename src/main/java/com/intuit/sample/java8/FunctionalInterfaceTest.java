package com.intuit.sample.java8;

public class FunctionalInterfaceTest {

	/**
	 * Theory: We can use arbitrary interfaces as lambda expressions as long as
	 * the interface only contains one abstract method. To ensure that your
	 * interface meet the requirements, you should add the @FunctionalInterface
	 * annotation. The compiler is aware of this annotation and throws a
	 * compiler error as soon as you try to add a second abstract method
	 * declaration to the interface.
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		System.out.println("Lambda example ....");
		System.out.println("FunctionalInterfaceSample<Integer> ift = (a, b) -> a * b;");

		FunctionalInterfaceSample<Integer> ift = (a, b) -> a * b;
		System.out.println("A * B =" + ift.multiply(10, 20));
		System.out.println("A + B =" + ift.add(10, 20));
		System.out.println("A - B =" + ift.sub(10, 20));

		System.out.println("");

		// OR using static methods
		System.out.println("Lambda using static method ....");
		System.out.println("FunctionalInterfaceSample<Integer> ift2 = Math::multiplyExact;");
		FunctionalInterfaceSample<Integer> ift2 = Math::multiplyExact;

		System.out.println("A * B =" + ift2.multiply(10, 20));
		System.out.println("A + B =" + ift2.add(10, 20));
		System.out.println("A - B =" + ift2.sub(10, 20));

		// OR using class methods
		// OR using static methods
		System.out.println("Lambda using sample class static/non-static method ....");
		System.out.println("FunctionalInterfaceSample<Integer> ift3 = SampleClass::sampleMethod;");
		FunctionalInterfaceSample<Integer> ift3 = SampleClass::sampleMethod;
		System.out.println("A * B =" + ift3.multiply(10, 20));
		System.out.println("A + B =" + ift3.add(10, 20));
		System.out.println("A - B =" + ift3.sub(10, 20));
	}

	/**
	 * static as it is referenced from main :(
	 * 
	 * @author agupta13
	 *
	 */
	public static class SampleClass {
		public static int sampleMethod(int a, int b) {
			return a * b;
		}
	}

	@FunctionalInterface
	public interface FunctionalInterfaceSample<T> {

		T multiply(T a, T b);

		default int add(int a, int b) {
			return a + b;
		}

		default int sub(int a, int b) {
			return a - b;
		}

	}

}
