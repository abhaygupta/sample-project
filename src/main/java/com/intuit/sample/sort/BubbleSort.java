package com.intuit.sample.sort;

public class BubbleSort {

	public static int[] sort(int[] array) {
		if (array == null || array.length == 0 || array.length == 1) {
			return array;
		}
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[i]) {
					swap(array, j, i);
				}
			}
		}
		return array;
	}

	private static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	public static void main(String[] args) {
		int[] array = { 9, 2, 1, 0, 1, 4, 5, 7, 6, 8, 9, 34, 56, 1, -1, -2, 45, 2, 1, 43534, 12121 };
		for (int data : sort(array)) {
			System.out.print(data + " ");
		}
	}
}
