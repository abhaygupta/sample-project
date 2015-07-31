package com.intuit.sample.threads;

public class DeadLockExample {

	private static String resource1 = "resource1";
	private static String resource2 = "resource2";

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {

			public void run() {
				System.out.println("Running thread =" + Thread.currentThread().getName());
				synchronized (resource1) {
					System.out.println("Thread =" + Thread.currentThread().getName() + " locked resource1. "
							+ "Sleeping for 1 sec");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					synchronized (resource2) {
						System.out.println("Thread =" + Thread.currentThread().getName() + " locked resource2. "
								+ "Sleeping for 1 sec");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}
		}, "Thread 1");

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				System.out.println("Running thread =" + Thread.currentThread().getName());
				synchronized (resource2) {
					System.out.println("Thread =" + Thread.currentThread().getName() + " locked resource2. "
							+ "Sleeping for 1 sec");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					synchronized (resource1) {
						System.out.println("Thread =" + Thread.currentThread().getName() + " locked resource1. "
								+ "Sleeping for 1 sec");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}
		}, "Thread 2");

		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}
}
