package com.intuit.sample.threads;

import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Just to summarize we call wait (), notify () or notifyAll method in Java from
 * synchronized method or synchronized block in Java to avoid: 
 * 1) IllegalMonitorStateException in Java which will occur if we don't call wait
 * (), notify () or notifyAll () method from synchronized context. 
 * 2) Any potential race condition between wait and notify method in Java.
 * 
 * 
 * Read more:
 * http://javarevisited.blogspot.com/2011/05/wait-notify-and-notifyall-in-java.
 * html#ixzz3hgZ27US9
 * 
 * @author agupta13
 *
 */
public class ProducerConsumer {

	private static int maxSize = 10;
	public static Queue<String> queue;
	private static Condition readCondition, writeCondition;
	private static ReentrantLock lock;

	static {
		queue = new ConcurrentLinkedQueue<String>();
		lock = new ReentrantLock();
		readCondition = lock.newCondition();
		writeCondition = lock.newCondition();
	}

	public static class Consumer implements Runnable {
		public void run() {
			lock.lock();
			try {
				System.out.println("Running consumer thread=" + Thread.currentThread().getName());
				while (queue.isEmpty()) {
					System.out.println("Queue is empty consumer sleeping ... ");
					try {
						readCondition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("Got data from Q:" + queue.poll());
				writeCondition.signalAll();
			} finally {
				lock.unlock();
			}
		}
	}

	public static class Producer implements Runnable {
		public void run() {
			lock.lock();
			try {
				System.out.println("Running producer thread=" + Thread.currentThread().getName());
				while (queue.size() == maxSize) {
					System.out.println("Queue is full producer sleeping ... ");
					try {
						writeCondition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				String data = UUID.randomUUID().toString();
				System.out.println("Pubished data to Q:" + data + " . success=" + queue.offer(data));
				readCondition.signalAll();
			} finally {
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// producer
		while (true) {
			Thread producer = new Thread(new Producer());
			Thread consumer = new Thread(new Consumer());
			producer.start();
			consumer.start();
			producer.join();
			consumer.join();
		}
	}
}
