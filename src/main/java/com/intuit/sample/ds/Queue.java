package com.intuit.sample.ds;

import java.util.LinkedList;

public class Queue<T> {
	private int maxSize;
	private LinkedList<T> list;

	public Queue(int maxSize) {
		this.maxSize = maxSize;
		list = new LinkedList<T>();
	}

	public T dequeue() {
		if (isEmpty()) {
			return null;
		}
		return list.remove(0);
	}

	public void enqueue(T data) {
		if (isFull()) {
			throw new RuntimeException("Queue is full");
		}
		list.addLast(data);
	}

	public T peek() {
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public boolean isFull() {
		return list.size() == maxSize;
	}

	public static void main(String[] args) {
		Queue<Integer> queue = new Queue<Integer>(5);
		queue.enqueue(1);
		queue.enqueue(11);
		queue.enqueue(111);
		queue.enqueue(1111);
		queue.enqueue(11111);
		queue.dequeue();
		queue.enqueue(22222);
		queue.dequeue();
		queue.enqueue(33333);
		queue.dequeue();
		queue.enqueue(44444);
		queue.dequeue();
		queue.enqueue(55555);
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
	}
}
