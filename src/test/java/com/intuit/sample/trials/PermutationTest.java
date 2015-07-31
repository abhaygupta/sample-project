package com.intuit.sample.trials;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PermutationTest {

	@Test
	public void testStringPermutationsNullStringNegative() {
		List<String> result = Permutation.generatePermutations(null);
		Assert.assertNotNull(result);
		Assert.assertTrue(result.isEmpty());
	}

	@Test
	public void testStringPermutationsEmptyStringPositive() {
		List<String> result = Permutation.generatePermutations("");
		Assert.assertNotNull(result);
		Assert.assertTrue(result.isEmpty());
	}

	@Test
	public void testStringPermutationsSampleStringOfLenghtOnePositive() {
		List<String> result = Permutation.generatePermutations("a");
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertEquals(result.size(), 1);
	}

	@Test
	public void testStringPermutationsSampleStringPositive() {
		List<String> result = Permutation.generatePermutations("abc");
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertEquals(result.size(), 6);
	}

	@Test
	public void testStringPermutationsSampleStringAllRepeatedCharsPositive() {
		List<String> result = Permutation.generatePermutations("aaa");
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertEquals(result.size(), 6);
	}

}