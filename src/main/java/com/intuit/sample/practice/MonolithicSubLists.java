package com.intuit.sample.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create monolithic sublists from an array of integers ,in increasing order for
 * example [1,2,4,7,5,6,3,2] will give [[1,2,4,7][5,6],[3],[2]]
 * 
 * @author agupta13
 *
 */
public class MonolithicSubLists {

	public static List<List<Integer>> sortedSubList(Integer[] array) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (array == null || array.length == 0) {
			return result;
		} else if (array.length == 1) {
			result.add(Arrays.asList(array));
			return result;
		}
		int startIndex = -1;
		int oldVal = 0;
		for (int i = 0; i < array.length; i++) {
			if (startIndex == -1) {
				startIndex = i;
				oldVal = array[i];
			} else if (array[i] > oldVal) {
				oldVal = array[i];
			} else {
				result.add(Arrays.asList(Arrays.copyOfRange(array, startIndex, i)));
				startIndex = i;
				oldVal = array[i];
			}
			if (i == array.length - 1) {
				result.add(Arrays.asList(Arrays.copyOfRange(array, startIndex, i + 1)));
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 4, 7, 5, 6, 3, 2, 5, 6, 7, 9, 12, 1, 6, 9 };
		Integer[] inputArray = new Integer[array.length];
		for (int i = 0; i < array.length; i++) {
			inputArray[i] = Integer.valueOf(array[i]);
		}

		List<List<Integer>> result = sortedSubList(inputArray);
		System.out.println("Result size=" + result.size());
		for (List<Integer> list : result) {
			for (Integer i : list) {
				System.out.print(i + " ");
			}
			System.out.println("");
		}
	}

}
