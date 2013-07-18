package conan.daizi;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class MinimumSetOfArraysOverlapsTest {

	/**
	 * Given [1] and [1]
	 * Expect [1]
	 */
	@Test
	public void testForSameArrays(){
		GivenArrays givenArrays = new GivenArrays();
		givenArrays.add(new int[]{1}).add(new int[]{1});
		
		EachArrayHasElementInMinimumSet instance = new EachArrayHasElementInMinimumSet(givenArrays);
		
		instance.process();
		
		int[] result = instance.getMinimumSetOfOverlaps();
		
		assertEquals("[1]", Arrays.toString(result));
	}
	
	/**
	 * Given [1] and [2]
	 * Expect [1, 2]
	 */
	@Test
	public void testForDistinctArrays(){
		GivenArrays givenArrays = new GivenArrays();
		givenArrays.add(new int[]{1}).add(new int[]{2});
		
		EachArrayHasElementInMinimumSet instance = new EachArrayHasElementInMinimumSet(givenArrays);
		
		instance.process();
		
		int[] result = instance.getMinimumSetOfOverlaps();
		
		assertEquals("[1, 2]", Arrays.toString(result));
	}
	
	/**
	 * Given [1, 1, 2, 3], [1, 2] and [2, 2]
	 * Expect [2]
	 */
	@Test
	public void testForThreeArraysAndHaveDuplicates(){
		GivenArrays givenArrays = new GivenArrays();
		givenArrays.add(new int[]{1, 1, 2, 3}).add(new int[]{1, 2}).add(new int[]{2, 2});
		
		EachArrayHasElementInMinimumSet instance = new EachArrayHasElementInMinimumSet(givenArrays);
		
		instance.process();
		
		int[] result = instance.getMinimumSetOfOverlaps();
		
		assertEquals("[2]", Arrays.toString(result));
	}
	
	/**
	 * Given A [4, 10, 15, 24, 26], B [0, 9, 12, 20] and C [5, 18, 22, 30]
	 * Expect [20, 21, 22, 23, 24], 24 in A, 20 in B and 22 in C
	 */
	@Test
	public void testForComplexArrays(){
		GivenArrays givenArrays = new GivenArrays();
		givenArrays.add(new int[]{4, 10, 15, 24, 26}).add(new int[]{0, 9, 12, 20}).add(new int[]{5, 18, 22, 30});
		
		EachArrayHasElementInMinimumSet instance = new EachArrayHasElementInMinimumSet(givenArrays);
		
		instance.process();
		
		int[] result = instance.getMinimumSetOfOverlaps();
		
		assertEquals("[20, 21, 22, 23, 24]", Arrays.toString(result));
	}
	
	/**
	 * Given A [-1, 0, 1], B [0] and C [-1, 2]
	 * Expect [-1, 0]
	 */
	@Test
	public void testForNegitiveValue(){
		GivenArrays givenArrays = new GivenArrays();
		givenArrays.add(new int[]{-1, 0, 1}).add(new int[]{0}).add(new int[]{-1, 2});
		
		EachArrayHasElementInMinimumSet instance = new EachArrayHasElementInMinimumSet(givenArrays);
		
		instance.process();
		
		int[] result = instance.getMinimumSetOfOverlaps();
		
		assertEquals("[-1, 0]", Arrays.toString(result));
		
	}
}
