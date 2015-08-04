package com.intuit.sample.practice;

import java.util.Arrays;

public class BSearch {

	/**
	 * return index of data element if found else -1
	 * 
	 * @param array
	 * @param data
	 * @return
	 */
	public static int bsearch(Integer[] array, int data) {
		return search(array, data, 0, array.length - 1);
	}

	private static int search(Integer[] array, int data, int start, int end) {
		if (start < end) {
			int mid = (start + end) / 2;
			if (mid == start || mid == end) {
				return -1;
			} else if (array[mid] == data) {
				return mid;
			} else if (data > array[mid]) {
				return search(array, data, mid, end);
			} else {
				return search(array, data, start, mid - 1);
			}
		} else {
			return -1;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		int[] array = { 1, 2, 4, 7, 5, 6, 3, 2, 5, 6, 7, 9, 12, 1, 6, 9, -1, 12, 12, 13, 343, 111, 4 };
		Integer[] inputArray = new Integer[array.length];
		for (int i = 0; i < array.length; i++) {
			inputArray[i] = Integer.valueOf(array[i]);
		}
		Arrays.sort(inputArray);
		System.out.println(inputArray);
		for (Integer data : inputArray) {
			System.out.print(data + " ");
		}
		System.out.println("");
		System.out.println(" SEARCHING = 111 ...... ");
		System.out.println(bsearch(inputArray, 111));
		Thread.sleep(1000);
		System.out.println(" SEARCHING = 9......... ");
		System.out.println(bsearch(inputArray, 9));
		Thread.sleep(1000);
		System.out.println(" SEARCHING =70000............ ");
		System.out.println(bsearch(inputArray, 70000));
		Thread.sleep(1000);
	}
}
