package com.intuit.sample.practice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * "Symmetric Difference of Arrays" Input: two arrays of integers Output: one
 * array of integers which occur in only one (not both) arrays Test case: Input:
 * [ 1, 7, 8, 2, 4, 5 ] [ 3, 5, 1, 7, 6, 9 ] Output: [ 8, 2, 4, 3, 6, 9 ]
 * 
 * @author agupta13
 *
 */
public class SymmetricDifferenceOfArray {

	public static Integer[] symmetricDiff(Integer[] array1, Integer[] array2) {
		Set<Integer> set1 = new HashSet<Integer>(Arrays.asList(array1));
		Set<Integer> set2 = new HashSet<Integer>(Arrays.asList(array2));
		// SET A - SET B
		set1.removeAll(set2);
		// SET B - SET A
		set2.removeAll(new HashSet<Integer>(Arrays.asList(array1)));
		// SET A U SET B
		set1.addAll(set2);
		Integer[] result = new Integer[set1.size()];
		set1.toArray(result);
		return result;
	}

	public static void main(String[] args) {
		Integer[] array1 = { 1, 7, 8, 2, 4, 5, 11, 12, 34, 33, -1, 0 };
		Integer[] array2 = { 3, 5, 1, 7, 6, 9 };

		Integer[] output = symmetricDiff(array1, array2);
		System.out.println("Result size=" + output.length);
		for (Integer data : output) {
			System.out.print(data + " ");
		}
	}
}
