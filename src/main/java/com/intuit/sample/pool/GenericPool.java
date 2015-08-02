package com.intuit.sample.pool;

import java.util.HashMap;
import java.util.Map;

public abstract class GenericPool<T> {
	private long expirationTime;
	private int maxSize;
	private Map<T, Long> locked, unlocked;

	public GenericPool(int poolSize, int expiryTime) {
		maxSize = poolSize;
		expirationTime = expiryTime; // 30 seconds
		locked = new HashMap<T, Long>();
		unlocked = new HashMap<T, Long>();
	}

	protected abstract T create();

	public abstract boolean validate(T o);

	public abstract void expire(T o);

	public synchronized T checkOut() {
		long now = System.currentTimeMillis();
		if (unlocked.size() > 0) {
			for (T t : unlocked.keySet()) {
				if ((now - unlocked.get(t)) > expirationTime) {
					// object has expired
					unlocked.remove(t);
					expire(t);
					t = null;
				} else {
					if (validate(t)) {
						unlocked.remove(t);
						locked.put(t, now);
						return (t);
					} else {
						// object failed validation
						unlocked.remove(t);
						expire(t);
						t = null;
					}
				}
			}
		}
		// no objects available, create a new one
		T e = create();
		locked.put(e, now);
		return (e);
	}

	public synchronized void checkIn(T t) {
		locked.remove(t);
		unlocked.put(t, System.currentTimeMillis());
	}

	public boolean isEmpty() {
		return locked.size() + unlocked.size() == 0;
	}

	public boolean isFull() {
		return locked.size() + unlocked.size() == maxSize;
	}
}
