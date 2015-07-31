package com.intuit.sample.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService poolExecutor = Executors.newCachedThreadPool();
		// big number
		FactorialTask task = new FactorialTask(10);
		System.out.println("Submitting task for 10! ......");
		Future<Long> future = poolExecutor.submit(task);
		while (!future.isDone()) {
			System.out.println("Factorial task is still running ......");
			Thread.sleep(5);
		}
		System.out.println("Factorial task completed ..... ");
		Long factorialvalue = future.get();
		System.out.println("10! = " + factorialvalue);
		poolExecutor.shutdown();
	}
}
