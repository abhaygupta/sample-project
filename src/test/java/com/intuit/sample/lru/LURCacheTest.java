package com.intuit.sample.lru;

import org.junit.Test;

import junit.framework.Assert;

public class LURCacheTest {

	@Test
	public void testLRUCache() {
		LRUCache<String, String> cache = new LRUCache<String, String>(5);
		cache.put("ABC", "ABC");
		cache.put("ABCD", "ABCD");
		cache.put("ABCDE", "ABCDE");
		cache.put("ABCDEF", "ABCDEF");
		cache.put("ABCDEFG", "ABCDEFG");
		cache.put("ABCDEFGH", "ABCDEFGH");
		Assert.assertNull(cache.get("ABC"));
		Assert.assertTrue(cache.get("ABCD").equals("ABCD"));
		cache.put("ABCDEFGHI", "ABCDEFGHI");
		Assert.assertNull(cache.get("ABCDE"));
	}
}
