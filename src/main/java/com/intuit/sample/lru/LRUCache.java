package com.intuit.sample.lru;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LRUCache<Key, Value> {

	private int maxSize;
	private Map<Key, Value> cacheMap;
	private Queue<Key> cacheQueue;

	public LRUCache(final int maxSize) {
		this.maxSize = maxSize;
		cacheMap = new ConcurrentHashMap<Key, Value>(maxSize);
		cacheQueue = new ConcurrentLinkedQueue<Key>();
	}

	public Value get(Key key) {
		// remove add add key back so it is in Last
		if (cacheMap.containsKey(key)) {
			cacheQueue.remove(key);
			cacheQueue.add(key);
		}
		return cacheMap.get(key);
	}

	public void put(Key key, Value value) {
		if (cacheMap.containsKey(key)) {
			cacheQueue.remove(key);
		}

		if (cacheQueue.size() == maxSize) {
			Key removedKey = cacheQueue.poll();
			cacheMap.remove(removedKey);
		}

		cacheMap.put(key, value);
		cacheQueue.add(key);
	}

	public void remove(Key key) {
		if (cacheMap.containsKey(key)) {
			cacheQueue.remove(key);
		}
	}
}
