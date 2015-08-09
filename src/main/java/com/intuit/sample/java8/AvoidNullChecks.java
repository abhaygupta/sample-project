package com.intuit.sample.java8;

import java.util.Optional;
import java.util.function.Supplier;

public class AvoidNullChecks {

	public static class Outer {
		Nested nested;

		Nested getNested() {
			return nested;
		}
	}

	class Nested {
		Inner inner;

		Inner getInner() {
			return inner;
		}
	}

	class Inner {
		String foo;

		String getFoo() {
			return foo;
		}
	}

	public static void main(String... args) {
		Outer outer = new Outer();
		// old way null check
		if (outer != null && outer.nested != null && outer.nested.inner != null) {
			System.out.println(outer.nested.inner.foo);
		}

		// new way
		Optional.of(outer).map(Outer::getNested).map(Nested::getInner).map(Inner::getFoo)
				.ifPresent(System.out::println);

		// using supplier method
		resolve(() -> outer.getNested().getInner().getFoo()).ifPresent(System.out::println);
	}

	public static <T> Optional<T> resolve(Supplier<T> resolver) {
		try {
			T result = resolver.get();
			return Optional.ofNullable(result);
		} catch (NullPointerException e) {
			return Optional.empty();
		}
	}
}
