package com.intuit.sample.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class ObjectPool<T> {

	private BlockingQueue<T> pool;

	// expiry time
	// locked thread

	public ObjectPool(int maxCapacity) {
		pool = new LinkedBlockingQueue<T>(maxCapacity);
	}

	protected T checkout() throws InterruptedException {
		return pool.take();
	}

	protected void checkin(T object) throws InterruptedException {
		pool.put(object);
	}
}
