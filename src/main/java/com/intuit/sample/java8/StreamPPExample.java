package com.intuit.sample.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPPExample {

	public static void main(String... args) {
		// collection - stream
		List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
		myList.stream().filter(s -> s.startsWith("c")).map(String::toUpperCase).sorted().forEach(System.out::println);

		/**
		 * Just use Stream.of() to create a stream from a bunch of object
		 * references.
		 **/
		Stream.of("a1", "a2", "a3").findFirst().ifPresent(System.out::println); // a1

		/**
		 * primitive data types int, long and double has IntStream, LongStream
		 * and DoubleStream.
		 * 
		 * has function like. range, max, avg, min etc.
		 */
		IntStream.range(1, 4).forEach(System.out::println);

		/**
		 * transform a regular object stream to a primitive stream or vice versa
		 * mapToInt(), mapToLong() and mapToDouble
		 */
		Stream.of("a1", "a2", "a3").map(s -> s.substring(1)).mapToInt(Integer::parseInt).max()
				.ifPresent(System.out::println);
		/**
		 * Primitive streams can be transformed to object streams via
		 * mapToObj():
		 */
		IntStream.range(1, 4).mapToObj(i -> "a" + i).forEach(System.out::println);

		/**
		 * When executing this code snippet, nothing is printed to the console.
		 * That is because intermediate operations will only be executed when a
		 * terminal operation is present.
		 */
		Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
			System.out.println("filter: " + s);
			return true;
		});

		/**
		 * Java 8 streams cannot be reused. As soon as you call any terminal
		 * operation the stream is closed.
		 * 
		 * Calling any other terminal operation on closed stream causes:
		 * 
		 * java.lang.IllegalStateException: stream has already been operated
		 * upon or closed
		 * 
		 */

		/**
		 * To overcome this limitation we have to to create a new stream chain
		 * for every terminal operation we want to execute, e.g. we could create
		 * a stream supplier to construct a new stream with all intermediate
		 * operations already set up:
		 * 
		 */

		Supplier<Stream<String>> streamSupplier = () -> Stream.of("d2", "a2", "b1", "b3", "c")
				.filter(s -> s.startsWith("a"));

		streamSupplier.get().anyMatch(s -> true); // ok
		streamSupplier.get().noneMatch(s -> true); // ok

		/**
		 * Advanced operation:
		 * 
		 * complex operations collect, flatMap and reduce.
		 */

		Supplier<Person> personSupplier = Person::new;
		List<Person> persons = new ArrayList<>();
		while (persons.size() != 10) {
			persons.add(personSupplier.get());
		}

		/**
		 * Collector: terminal operation. Transform the elements of the stream
		 * into a different kind of result, e.g. a List, Set or Map
		 * 
		 * Collect accepts a Collector which consists of four different
		 * operations: a supplier, an accumulator, a combiner and a finisher
		 */

		// toList, toSet
		List<Person> filtered = persons.stream().filter(p -> p.fName.contains("f")).collect(Collectors.toList());
		System.out.println(filtered);

		// group by clause
		Map<Integer, List<Person>> mapOfPerson = persons.stream().collect(Collectors.groupingBy(p -> p.age));
		System.out.println(mapOfPerson);

		// avg
		Double averageAge = persons.stream().collect(Collectors.averagingInt(p -> p.age));
		System.out.println("person avg:" + averageAge); // 19.0

		// stats
		IntSummaryStatistics ageSummary = persons.stream().collect(Collectors.summarizingInt(p -> p.age));
		System.out.println(ageSummary);

		// join all person in single string
		String phrase = persons.stream().filter(p -> p.age >= 18).map(p -> p.fName.substring(0, 4))
				.collect(Collectors.joining(" and ", "In India ", " are of legal age."));
		System.out.println(phrase);

		// mapped keys must be unique, otherwise an IllegalStateException is
		// thrown
		Map<String, String> personMap = persons.stream()
				.collect(Collectors.toMap((p) -> p.fName, p -> "Name=" + p.fName + " age=" + p.age));
		for (String key : personMap.keySet()) {
			System.out.println("key=" + key + " value=" + personMap.get(key));
		}

		/**
		 * We want to transform all persons of the stream into a single string
		 * consisting of all names in upper letters separated by the | pipe
		 * character.
		 */
		String names = persons.stream()
				.collect(Collector.of(() -> new StringJoiner("|"), // supplier
						(j, p) -> j.add(p.fName.toUpperCase()), // accumulator
						(j1, j2) -> j1.merge(j2), // combiner
						StringJoiner::toString)); // finisher
		System.out.println("Names=" + names);

		/**
		 * FlatMap accepts a function which has to return a stream of objects.
		 */
		List<Foo> foos = new ArrayList<>();
		// create foos
		IntStream.range(1, 4).forEach(i -> foos.add(new Foo("Foo" + i)));
		// create bars
		foos.forEach(f -> IntStream.range(1, 4).forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));
		foos.stream().flatMap(f -> f.bars.stream()).forEach(b -> System.out.println(b.name));

		// OR can be done as
		System.out.println("OR can be done as");
		IntStream.range(1, 4)
				.mapToObj(i -> new Foo("Foo" + i)).peek(f -> IntStream.range(1, 4)
						.mapToObj(i -> new Bar("Bar" + i + " <- " + f.name)).forEach(f.bars::add))
				.flatMap(f -> f.bars.stream()).forEach(b -> System.out.println(b.name));
	}

	public static class Person {
		String fName;
		String lName;
		int age;

		static Random random = new Random(100);

		public Person() {
			this.fName = UUID.randomUUID().toString();
			this.lName = UUID.randomUUID().toString();
			age = random.nextInt(100);
		}

		@Override
		public String toString() {
			return fName;
		}
	}

	public static class Foo {
		String name;
		List<Bar> bars = new ArrayList<>();

		Foo(String name) {
			this.name = name;
		}
	}

	public static class Bar {
		String name;

		Bar(String name) {
			this.name = name;
		}
	}

}
