package com.intuit.sample.trials;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class Permutation {

	//O(N!)
	public static List<String> generatePermutations(String input) {
		List<String> result = new ArrayList<String>();
		if (input == null || input.length() == 0) {
			return result;
		} else if (input.length() == 1) {
			result.add(input);
			return result;
		} else {
			for (int i = 0; i < input.length(); i++) {
				char startChar = input.charAt(i);
				String subString = null;
				// high memory imprint!
				subString = (i + 1 < input.length()) ? input.substring(0, i) + input.substring(i + 1)
						: input.substring(0, i);
				List<String> subResult = generatePermutations(subString);
				for (String str : subResult) {
					result.add(startChar + str);
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		for (String result : generatePermutations("abc")) {
			System.out.println(result);
		}
	}
}