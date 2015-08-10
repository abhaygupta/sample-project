package com.intuit.sample.java8;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicExample extends BaseExample {

	public static void main(String... args) {
		// e.g.1 Atomic uses compare-and-swap (CAS), an atomic instruction
		// directly
		// supported by most modern CPUs
		AtomicInteger atomicInt = new AtomicInteger(0);
		ExecutorService executor = Executors.newFixedThreadPool(2);
		IntStream.range(0, 1000).forEach(i -> executor.submit(atomicInt::incrementAndGet));
		stop(executor);
		System.out.println(atomicInt.get()); // => 1000

		// e.g. 2 updateAndGet() accepts a lambda expression in order to perform
		// arbitrary arithmetic operations upon the integer
		AtomicInteger atomicInt1 = new AtomicInteger(0);
		ExecutorService executor1 = Executors.newFixedThreadPool(2);
		IntStream.range(0, 1000).forEach(i -> {
			Runnable task = () -> atomicInt1.updateAndGet(n -> n + 2);
			executor1.submit(task);
		});
		stop(executor1);
		System.out.println(atomicInt1.get()); // => 2000

		// Also check, AtomicBoolean, AtomicLong and AtomicReference.

		// concurrent hash map
		ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
		map.put("foo", "bar");
		map.put("han", "solo");
		map.put("r2", "d2");
		map.put("c3", "p0");
		// for each
		map.forEach((key, value) -> System.out.printf("%s = %s\n", key, value));
		// putIfAbsent
		System.out.println("Put if absent=" + map.putIfAbsent("c3", "p1"));
		// get or default
		System.out.println("Get or default=" + map.getOrDefault("hi", "default"));
		//replaceAll
		map.replaceAll((key, value) -> key.length()>0 ? "non-zero-len-key" : "zero-len-key");
		System.out.println("replaceAll =" + map);
		//compute
		map.compute("foo", (key, value) -> value +"-" + value);
		System.out.println("compute =" + map);
		//merge
		map.merge("foo", "boo", (oldVal, newVal) -> newVal + " was " + oldVal);
		System.out.println("merge =" + map);

		//fork and join pool
		System.out.println("ForkNJoin pool size=" + ForkJoinPool.getCommonPoolParallelism());  // 3




	}
}
