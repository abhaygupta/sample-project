package com.intuit.sample.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveArrayRepeatedValuesInPlace {

	public static Integer[] removeRepeatedValuesInPlace(Integer[] array) {
		Map<Integer, Boolean> numberExists = new HashMap<Integer, Boolean>();
		Map<Integer, Boolean> indexesToBeRemoved = new HashMap<Integer, Boolean>();
		for (int i = 0; i < array.length; i++) {
			if (numberExists.get(array[i]) == null) {
				numberExists.put(array[i], Boolean.TRUE);
			} else { // number is duplicate
				indexesToBeRemoved.put(i, true);
			}
		}
		array = removeElementsFromArray(array, indexesToBeRemoved);
		return array;
	}

	private static Integer[] removeElementsFromArray(Integer[] array, Map<Integer, Boolean> indexesToBeRemoved) {
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < array.length; i++) {
			if (indexesToBeRemoved.get(i) == null) {
				result.add(array[i]);
			}
		}

		Integer[] new_array = new Integer[result.size()];

		new_array = result.toArray(new_array);
		return new_array;
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 4, 7, 5, 6, 3, 2, 5, 6, 7, 9, 12, 1, 6, 9, -1, 12, 12, 13, 343, 111, 4 };
		Integer[] inputArray = new Integer[array.length];
		for (int i = 0; i < array.length; i++) {
			inputArray[i] = Integer.valueOf(array[i]);
		}

		inputArray = removeRepeatedValuesInPlace(inputArray);
		System.out.println("Result size=" + inputArray.length);
		for (Integer data : inputArray) {
			System.out.print(data + " ");
		}
	}

}
