package com.intuit.sample.sort;

import java.util.Arrays;

public class QuickSort {

	public static void sort(int[] array) {
		if (array == null || array.length == 0 || array.length == 1) {
			return;
		}
		quickSort(array, 0, array.length - 1);
	}

	private static void quickSort(int[] array, int start, int end) {
		if (start < end) {
			int mid = start + (end - start) / 2;
			partition(array, mid, start, end);
			quickSort(array, start, mid);
			quickSort(array, mid + 1, end);
		}
	}

	private static void partition(int[] array, int mid, int start, int end) {
		int pivot = array[mid];
		while (start < end) {
			if (array[end] < pivot) {
				end--;
			}

			if (array[start] > pivot) {
				start++;
			}

			// swap two elements
			swap(array, start, end);
		}

	}

	private static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	public static void main(String[] args) {
		int[] array = { 9, 2, 1, 0, 1, 4, 5, 7, 6, 8, 9, 34, 56, 1, -1, -2, 45, 2, 1, 43534, 12121 };
		sort(array);
		for (int data : array) {
			System.out.print(data + " ");
		}
	}
}
