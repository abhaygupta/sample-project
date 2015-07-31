package com.intuit.sample.future;

import java.util.concurrent.Callable;

public class FactorialTask implements Callable<Long> {

	private final int number;

	public FactorialTask(int number) {
		this.number = number;
	}

	public Long call() throws Exception {
		return factorial(number);
	}

	private Long factorial(Integer i) throws InterruptedException {
		if (i < 0) {
			throw new RuntimeException("Number can't be negative for factorial");
		}
		Long result = 1L;
		while (i > 0) {
			Thread.sleep(5);
			result = result * i;
			i--;
		}
		return result;
	}
}
