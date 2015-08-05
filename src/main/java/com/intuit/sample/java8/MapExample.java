package com.intuit.sample.java8;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MapExample {
	public static void main(String... args) {

		Map<String, String> map = new HashMap<>();
		int i = 10;
		while (i > 0) {
			String uuid = UUID.randomUUID().toString();
			// putIfAbsent prevents us from writing additional if null checks;
			map.putIfAbsent(uuid, "present");
			i--;
		}

		// map for each
		map.forEach((key, val) -> System.out.println("key=" + key + " val=" + val));

		// compute if present
		String key = (String) map.keySet().toArray()[0];
		System.out.println("key value before=" + map.get(key));
		map.computeIfPresent(key, (k, v) -> k + "-" + v);
		System.out.println("key value after=" + map.get(key));

		// this removes it
		System.out.println("key value before computing it to null=" + map.get(key));
		map.computeIfPresent(key, (k, v) -> null);
		System.out.println("key value after computing it to null=" + map.get(key));

		// remove entries for a a given key, only if it's currently mapped to a
		// given value
		key = (String) map.keySet().toArray()[0];
		System.out.println("Remove if key=" + key + " value=" + key + "-" + "present" + " matches="
				+ map.remove(key, key + "-" + "present")); // not found

		// get or default value
		key = (String) map.keySet().toArray()[0];
		System.out.println("Get or default for key " + key + " =" + map.getOrDefault(key, "not found"));
		System.out.println("Get or default for key TEST123 =" + map.getOrDefault("TEST123", "not found"));

		map.merge(key, "--newValue", (value, newValue) -> value.concat(newValue));
		System.out.println("new val for key=" + key + " post merging=" + map.get(key));
	}
}
