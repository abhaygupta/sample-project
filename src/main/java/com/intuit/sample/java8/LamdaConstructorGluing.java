package com.intuit.sample.java8;

import org.junit.Test;

public class LamdaConstructorGluing {

	/**
	 * Let's see how the :: keyword works for constructors. First we define an
	 * example bean with different constructors
	 * 
	 * @author agupta13
	 *
	 */
	public class Person {
		String firstName;
		String lastName;

		Person() {
		}

		Person(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}
	}

	/**
	 * Factory interface to create person object
	 * 
	 * @author agupta13
	 *
	 * @param
	 * 			<P>
	 */
	interface PersonFactory<P extends Person> {
		P create(String firstName, String lastName);
	}

	@Test
	public void testGlueConstructorUsingLamda() {
		PersonFactory<Person> factory = Person::new;
		Person p = factory.create("Abhay", "Gupta");
		System.out.println("Person firstName=" + p.firstName + " lastName=" + p.lastName);
	}
}
