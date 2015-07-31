package com.intuit.sample.threads;

import java.util.Random;

public class ThreadLocalExample {

	private static ThreadLocal<String> threadLocalVar = new ThreadLocal<String>();
	private static Random random = new Random();

	public static class ThreadDemo implements Runnable {

		public void run() {
			System.out.println("Started thread =" + Thread.currentThread().getName());
			String threadLocalStr = random.nextInt(10000) + "";
			System.out.println("Thread local val set to =" + threadLocalStr);
			threadLocalVar.set(threadLocalStr);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " - Thread local value=" + threadLocalVar.get());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ThreadDemo t1 = new ThreadDemo();
		ThreadDemo t2 = new ThreadDemo();
		Thread thread1 = new Thread(t1, "Thread 1");
		Thread thread2 = new Thread(t2, "Thread 2");
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
	}
}
