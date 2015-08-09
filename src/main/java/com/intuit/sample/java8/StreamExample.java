package com.intuit.sample.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamExample {

	/**
	 * Theory: A java.util.Stream represents a sequence of elements on which one
	 * or more operations can be performed.
	 * 
	 * Stream operations are either intermediate or terminal. While terminal
	 * operations return a result of a certain type, intermediate operations
	 * return the stream itself so you can chain multiple method calls in a row.
	 * Streams are created on a source, e.g. a java.util.Collection like lists
	 * or sets (maps are not supported). Stream operations can either be
	 * executed sequential or parallel.
	 */

	public static List<String> stringCollection = new ArrayList<>();
	// to test paralel stream
	public static List<String> bigCollection = new ArrayList<>();
	public final static int bigCollectionSize = 1000000;

	static {
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");
		for (int i = 0; i < bigCollectionSize; i++) {
			bigCollection.add(UUID.randomUUID().toString());
		}
	}

	/**
	 * Collections in Java 8 are extended so you can simply create streams
	 * either by calling Collection.stream() or Collection.parallelStream()
	 */

	/**
	 * Filter accepts a predicate to filter all elements of the stream. This
	 * operation is intermediate which enables us to call another stream
	 * operation (forEach) on the result. ForEach accepts a consumer to be
	 * executed for each element in the filtered stream. ForEach is a terminal
	 * operation. It's void, so we cannot call another stream operation.
	 * 
	 */
	@Test
	public void testStreamFilter() {
		System.out.println("testStreamFilter: filtering string starting with 'c'");
		stringCollection.stream().filter((str) -> str.startsWith("c")).forEach(System.out::println);
	}

	/**
	 * Sorted is an intermediate operation which returns a sorted view of the
	 * stream. The elements are sorted in natural order unless you pass a custom
	 * Comparator.
	 * 
	 * Note that: this doesn't affect order of collection
	 * 
	 */
	@Test
	public void testStreamSorted() {
		System.out.println("testStreamSorted");
		stringCollection.stream().sorted().forEach(System.out::println);
	}

	/**
	 * The intermediate operation map converts each element into another object
	 * via the given function. The following example converts each string into
	 * an upper-cased string. But you can also use map to transform each object
	 * into another type. The generic type of the resulting stream depends on
	 * the generic type of the function you pass to map.
	 * 
	 */
	@Test
	public void testStreamMap() {
		System.out.println("testStreamMap - str to uppercase");
		stringCollection.stream().map(String::toUpperCase).forEach(System.out::println);
		System.out.println("testStreamMap - str to strstrstr");
		stringCollection.stream().map((a) -> (a + a + a)).forEach(System.out::println);
	}

	/**
	 * Various matching operations can be used to check whether a certain
	 * predicate matches the stream. All of those operations are terminal and
	 * return a boolean result.
	 * 
	 */
	@Test
	public void testStreamMatch() {
		System.out.println("testStreamMap - str containing a");
		System.out.println("Any string contains a?:" + stringCollection.stream().anyMatch((str) -> str.contains("a")));
		System.out.println("No string start with a?:" + stringCollection.stream().noneMatch((a) -> a.startsWith("a")));

	}

	/**
	 * Count is a terminal operation returning the number of elements in the
	 * stream as a long.
	 * 
	 */
	@Test
	public void testStreamCount() {
		System.out.println("testStreamCount - count str starting with a, count="
				+ stringCollection.stream().filter((str) -> str.startsWith("a")).count());
	}

	/**
	 * This terminal operation performs a reduction on the elements of the
	 * stream with the given function. The result is an Optional holding the
	 * reduced value.
	 * 
	 */
	@Test
	public void testStreamReduce() {
		System.out.println("testStreamReduce appending all string with #="
				+ stringCollection.stream().reduce((s1, s2) -> s1 + "#" + s2).get());
	}

	/**
	 * Parallel Streams
	 * 
	 * As mentioned above streams can be either sequential or parallel.
	 * Operations on sequential streams are performed on a single thread while
	 * operations on parallel streams are performed concurrent on multiple
	 * threads.
	 */
	@Test
	public void testParallelStreamSorting() {
		long start = System.nanoTime();
		System.out.println("Starting sequential sorting on element, size=" + bigCollectionSize);
		bigCollection.stream().sorted((a, b) -> a.compareTo(b)).findFirst().ifPresent(System.out::println);
		System.out.println("Completed sequential sorting time taken=" + (System.nanoTime() - start) + " millis");

		start = System.nanoTime();
		System.out.println("Starting parallel sorting on element, size=" + bigCollectionSize);
		bigCollection.parallelStream().sorted((a, b) -> a.compareTo(b)).findFirst().ifPresent(System.out::println);
		System.out.println("Completed parallel sorting time taken=" + (System.nanoTime() - start) + " millis");
	}
}
