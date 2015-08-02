package com.intuit.sample.sort;

public class MergeSort {
	static public void merge(int[] numbers, int left, int mid, int right) {
		int[] temp = new int[25];
		int i, left_end, num_elements, tmp_pos;

		left_end = (mid - 1);
		tmp_pos = left;
		num_elements = (right - left + 1);

		while ((left <= left_end) && (mid <= right)) {
			if (numbers[left] <= numbers[mid])
				temp[tmp_pos++] = numbers[left++];
			else
				temp[tmp_pos++] = numbers[mid++];
		}

		while (left <= left_end)
			temp[tmp_pos++] = numbers[left++];

		while (mid <= right)
			temp[tmp_pos++] = numbers[mid++];

		for (i = 0; i < num_elements; i++) {
			numbers[right] = temp[right];
			right--;
		}
	}

	static public void sort(int[] numbers, int left, int right) {
		int mid;
		if (right > left) {
			mid = (right + left) / 2;
			sort(numbers, left, mid);
			sort(numbers, (mid + 1), right);
			merge(numbers, left, (mid + 1), right);
		}
	}

	static public void sort(int[] numbers) {
		sort(numbers, 0, numbers.length - 1);
	}

	public static void main(String[] args) {
		int[] array = { 9, 2, 1, 0, 1, 4, 5, 7, 6, 8, 9, 34, 56, 1, -1, -2, 45, 2, 1, 43534, 12121 };
		sort(array);
		for (int data : array) {
			System.out.print(data + " ");
		}
	}
}
