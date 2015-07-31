package com.intuit.sample.observers;

import java.util.Observable;

public class SampleSubject extends Observable {

	public static void main(String[] args) {
		SampleSubject subject = new SampleSubject();
		System.out.println("Observers: " + subject.countObservers());
		SampleObserver observer = new SampleObserver();
		subject.addObserver(observer);
		System.out.println("Observers: " + subject.countObservers());
		subject.setChanged();
		subject.notifyObservers("You have been notified");
	}

}
