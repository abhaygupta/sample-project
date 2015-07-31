package com.intuit.sample.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class FutureScheduledTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
		FactorialTask task = new FactorialTask(10);
		ScheduledFuture<Long> future = scheduledExecutor.schedule(task, 5, TimeUnit.SECONDS);
		while (!future.isDone()) {
			System.out.println("Factorial task is still running ......");
			Thread.sleep(500);
		}
		System.out.println("Factorial task completed ..... ");
		Long factorialvalue = future.get();
		System.out.println("10! = " + factorialvalue);
		scheduledExecutor.shutdown();
	}
}
