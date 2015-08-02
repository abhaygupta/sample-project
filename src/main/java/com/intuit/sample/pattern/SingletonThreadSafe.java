package com.intuit.sample.pattern;

public class SingletonThreadSafe {

	private volatile static SingletonThreadSafe instance;

	private SingletonThreadSafe() {

	}

	public static SingletonThreadSafe getInstance() {
		if (instance == null) {
			synchronized (SingletonThreadSafe.class) {
				if (instance == null) {
					instance = new SingletonThreadSafe();
				}
			}
		}
		return instance;
	}

	public static void main(String[] args) {
		System.out.println(SingletonThreadSafe.getInstance());
		System.out.println(SingletonThreadSafe.getInstance());
		System.out.println(SingletonThreadSafe.getInstance());
		System.out.println(SingletonThreadSafe.getInstance());
	}
}
