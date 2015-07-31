package com.intuit.sample.pool;

public class StringPool extends ObjectPool<String> {

	public StringPool(int maxCapacity) {
		super(maxCapacity);
	}
}
