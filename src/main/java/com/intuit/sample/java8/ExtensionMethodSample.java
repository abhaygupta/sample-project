package com.intuit.sample.java8;

public class ExtensionMethodSample implements ExtensionMethodInteface {

	@Override
	public double calculate(int a) {
		return sqrt(a * 100);
	}

	public static void main(String... args) {
		ExtensionMethodSample obj = new ExtensionMethodSample();
		System.out.println("SQRT 100 =" + obj.sqrt(100));
		System.out.println("Calculate 100 =" + obj.calculate(100));
	}
}
