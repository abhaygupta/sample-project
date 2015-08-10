package com.intuit.sample.java8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.Test;

public class ConcurrencyExample {

	static int count = 0;

	static void increment() {
		count = count + 1;
	}

	public static void main(String... args) throws InterruptedException {
		// thread eg.
		Runnable r1 = () -> System.out.println("Thread: " + Thread.currentThread().getName() + " is running");
		Thread t1 = new Thread(r1);
		t1.start();
		t1.join();

		System.out.println("Sleeping for 2 sec....");
		// sleep for 2 sec :)
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Awake after sleeping 2 sec....");

		// e.g. 3
		/**
		 * The below example uses yet another type of executor created via
		 * newWorkStealingPool(). This factory method is part of Java 8 and
		 * returns an executor of type ForkJoinPool which works slightly
		 * different than normal executors. Instead of using a fixed size
		 * thread-pool ForkJoinPools are created for a given parallelism size
		 * which per default is the number of available cores of the hosts CPU.
		 */
		ExecutorService executor = Executors.newWorkStealingPool();
		List<Callable<String>> callables = Arrays.asList(() -> "task1", () -> "task2", () -> "task3");
		executor.invokeAll(callables).stream().map(future -> {
			try {
				return future.get();
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}).forEach(System.out::println);
		
	}

	@Test
	public void testRaceCondition() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		IntStream.range(0, 10000).forEach(i -> executor.submit(ConcurrencyExample::increment));
		executor.shutdown();
		System.out.println(count); // should be 20000
	}

}
