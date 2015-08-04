package com.intuit.sample.practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provided a set of characters and a list of words, how many of those words can
 * we create from the input characters ? input: chars =
 * 'atuslirabocynkebbterasiuaracnbawp' words = output: car =&gt; 2 boat =&gt; 1
 * plane =&gt; 1 bus =&gt; 2 shuttle =&gt; 0
 * 
 * @author agupta13
 *
 */
public class PossibleWords {

	public static Map<String, Integer> possibleWordCount(String chars, List<String> words) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		Map<String, Integer> charCounterMap = new HashMap<String, Integer>();
		chars = chars.replaceAll("\\s+", "");
		for (String ch : chars.split("")) {
			if (charCounterMap.get(ch) == null) {
				charCounterMap.put(ch, 1);
			} else {
				int counter = charCounterMap.get(ch);
				charCounterMap.put(ch, ++counter);
			}
		}

		for (String word : words) {
			word = word.replaceAll("\\s+", "");
			String[] charArray = word.split("");
			int min = Integer.MAX_VALUE;
			for (String ch : charArray) {
				if (!ch.isEmpty()) {
					Integer charOccurenceCount = charCounterMap.get(ch);
					if (charOccurenceCount == null) {
						min = 0;
						break;
					}
					min = Math.min(min, Integer.valueOf(charOccurenceCount));
				}
			}
			System.out.println(word + " = " + min);
			result.put(word, min);
		}
		return result;
	}

	public static void main(String[] args) {
		List<String> words = Arrays.asList("car", "boat", "plane", "bus", "shuttle");
		String chars = "atuslirabocynkebbterasiuaracnbawp";
		possibleWordCount(chars, words);
	}
}
