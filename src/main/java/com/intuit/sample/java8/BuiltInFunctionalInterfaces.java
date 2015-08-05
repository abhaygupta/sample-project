package com.intuit.sample.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Assert;
import org.junit.Test;

/**
 * The JDK 1.8 API contains many built-in functional interfaces. Some of them
 * are well known from older versions of Java like Comparator or Runnable. Those
 * existing interfaces are extended to enable Lambda support via
 * the @FunctionalInterface annotation.
 * 
 * But the Java 8 API is also full of new functional interfaces to make your
 * life easier. Some of those new interfaces are well known from the Google
 * Guava library. Even if you're familiar with this library you should keep a
 * close eye on how those interfaces are extended by some useful method
 * extensions.
 * 
 * @author agupta13
 *
 */
public class BuiltInFunctionalInterfaces {

	@Test
	public void testRunnableFunctionalInterface() {
		Runnable runnable1 = () -> {
			System.out.println("Thread is running");
			System.out.println("Thread doing more operations");
		};
		Thread thread2 = new Thread(runnable1);
		thread2.start();
	}

	@Test
	public void testComparatorFunctionalInterface() {
		List<String> list = Arrays.asList("peter", "anna", "mike", "xenia");
		System.out.println("List before sorting = " + list);
		list.sort((a, b) -> a.compareTo(b));
		System.out.println("List post sorting = " + list);
	}

	/**
	 * Predicates are boolean-valued functions of one argument. The interface
	 * contains various default methods for composing predicates to complex
	 * logical terms (and, or, negate)
	 * 
	 */

	@Test
	public void testStringPresentPredicate() {
		Predicate<String> predicate = (str) -> str != null && str.length() > 0;
		Assert.assertTrue(predicate.test("ABC"));
		Assert.assertFalse(predicate.test(""));
		Assert.assertFalse(predicate.test(null));
		Assert.assertTrue(predicate.negate().test(null));
	}

	@Test
	public void testNullCheckPredicate() {
		Predicate<String> nonNull = Objects::nonNull;
		Assert.assertTrue(nonNull.test(""));
		Assert.assertFalse(nonNull.test(null));

		Predicate<String> isNull = Objects::isNull;
		Assert.assertFalse(isNull.test(""));
		Assert.assertTrue(isNull.test(null));

	}

	/**
	 * Functions accept one argument and produce a result. Default methods can
	 * be used to chain multiple functions together (compose, andThen).
	 */

	@Test
	public void testFunction() {
		Function<String, Integer> toInteger = Integer::parseInt;
		Function<String, String> backToString = toInteger.andThen(String::valueOf);

		System.out.println("Function<String, Integer> toInteger = Integer::valueOf;");
		System.out.println("1131 = " + toInteger.apply("1131"));

		System.out.println("Function<String,String> backToString = toInteger.andThen(String::valueOf);");
		System.out.println("1131 = " + backToString.apply("1131"));
	}

	/**
	 * Suppliers produce a result of a given generic type. Unlike Functions,
	 * Suppliers don't accept arguments.
	 */

	public class Person {
		String firstName;
		String lastName;

		public Person(String fName, String lName) {
			this.firstName = fName;
			this.lastName = lName;
		}

		public Person() {
		}
	}

	@Test
	public void testSupplier() {
		Supplier<Person> personSupplier = Person::new;
		// new person
		System.out.println("Create person instance=" + personSupplier.get());
	}

	/**
	 * Consumers represents operations to be performed on a single input
	 * argument.
	 */

	@Test
	public void testConsumer() {
		Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
		greeter.accept(new Person("Luke", "Skywalker"));
	}

	/**
	 * Comparators are well known from older versions of Java. Java 8 adds
	 * various default methods to the interface.
	 */

	@Test
	public void testComparators() {
		Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
		Person p1 = new Person("John", "Doe");
		Person p2 = new Person("Alice", "Wonderland");
		Assert.assertTrue(comparator.compare(p1, p2) > 0);
		Assert.assertTrue(comparator.reversed().compare(p1, p2) < 0);
	}

	/**
	 * Optionals are not functional interfaces, instead it's a nifty utility to
	 * prevent NullPointerException. It's an important concept for the next
	 * section, so let's have a quick look at how Optionals work.
	 * 
	 * Optional is a simple container for a value which may be null or non-null.
	 * Think of a method which may return a non-null result but sometimes return
	 * nothing. Instead of returning null you return an Optional in Java 8.
	 */

	@Test
	public void testOptionals() {
		Optional<String> optional = Optional.of("bam");
		Assert.assertTrue(optional.isPresent()); // true
		Assert.assertTrue("bam".equals(optional.get())); // "bam"
		Assert.assertTrue("bam".equals(optional.orElse("fallback"))); // "bam"
		optional.ifPresent((s) -> System.out.println(s.charAt(0))); // "b"
	}

}
