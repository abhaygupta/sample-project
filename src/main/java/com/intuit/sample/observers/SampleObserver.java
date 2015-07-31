package com.intuit.sample.observers;

import java.util.Observable;
import java.util.Observer;

public class SampleObserver implements Observer {

	public void update(Observable o, Object arg) {
		System.out.println("Got an update on observed object=" + o + ". Args=" + arg);
	}

}
