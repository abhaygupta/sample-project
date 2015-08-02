package com.intuit.sample.sort;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class FindSmallest {

	static ForkJoinPool fjPool = new ForkJoinPool();

	public static int smallest(int[] array) throws InterruptedException, ExecutionException {
		SmallestNumberTask task = new SmallestNumberTask(array);
		ForkJoinTask<Integer> result = fjPool.submit(task);
		if (!result.isDone()) {
			System.out.println("Task running ..... ");
		}
		int smallestNum = result.get();
		System.out.println("Found smallest number ..... " + smallestNum);
		return smallestNum;
	}

	public static class SmallestNumberTask extends RecursiveTask<Integer> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int[] array;

		public SmallestNumberTask(int[] array) {
			this.array = array;
		}

		@Override
		protected Integer compute() {
			int min = -1;
			if (array == null || array.length == 0) {
				return min;
			}
			min = array[0];
			for (int data : array) {
				min = Math.min(min, data);
			}
			return min;
		}
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int[] array = { 9, 2, 1, 0, 1, 4, 5, 7, 6, 8, 9, 34, 56, 1, -1, -2, 45, 2, 1, 43534, 12121 };
		System.out.println("Smallest number=" + FindSmallest.smallest(array));
	}
}
