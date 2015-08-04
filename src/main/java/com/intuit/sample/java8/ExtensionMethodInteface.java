package com.intuit.sample.java8;

public interface ExtensionMethodInteface {
	double calculate(int a);

	default double sqrt(int a) {
		return Math.sqrt(a);
	}
}
