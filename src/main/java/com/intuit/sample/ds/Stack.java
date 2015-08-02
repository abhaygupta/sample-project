package com.intuit.sample.ds;

import java.util.LinkedList;
import java.util.List;

/*
 * LIFO
 */
public class Stack<T> {

	private int maxSize;
	private List<T> list;
	private int top;

	public Stack(int maxSize) {
		this.maxSize = maxSize;
		list = new LinkedList<T>();
		for (int i = 0; i < maxSize; i++) {
			list.add(null);
		}
		top = -1;
	}

	public T pop() {
		if (top == -1)
			return null;
		return list.get(top--);
	}

	public void push(T data) {
		if (this.isFull()) {
			throw new RuntimeException("Stack is full");
		}
		list.set(++top, data);
	}

	public T peek() {
		if (top == -1)
			return null;
		return list.get(top);
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public boolean isFull() {
		return top == (maxSize - 1);
	}

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>(5);
		stack.push(1);
		stack.push(11);
		stack.push(111);
		stack.push(1111);
		stack.push(11111);
		stack.pop();
		stack.push(11111);
		stack.pop();
		stack.push(22222);
		stack.pop();
		stack.push(33333);
		stack.pop();
		stack.push(44444);
		System.out.println(stack.peek());
	}

}
