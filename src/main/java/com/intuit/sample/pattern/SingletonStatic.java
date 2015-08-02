package com.intuit.sample.pattern;

public class SingletonStatic {

	private static final SingletonStatic instance = new SingletonStatic();

	private SingletonStatic() {

	}

	public SingletonStatic getInstance() {
		return instance;
	}
}
