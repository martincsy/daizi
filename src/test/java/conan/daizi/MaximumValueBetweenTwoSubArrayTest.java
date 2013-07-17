package conan.daizi;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class MaximumValueBetweenTwoSubArrayTest {

	/**
	 * E.g.
	 * [2] -> A=[2] and B[Null]
	 * Sub Array should not be empty
	 * |Sum(A) - Sum(B)| = 1 the largest
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testBoundaryValue(){

		int[] givenArray = {2};
		
		MaximumValueBetweenTwoSubArray instance = new MaximumValueBetweenTwoSubArray(givenArray);
	
		instance.process();
	}
	
	/**
	 * E.g.
	 * [2, -1] -> A[2] and B[-1]
	 * |Sum(A) - Sum(B)| = 3 the largest
	 */
	@Test
	public void testGivenArrayContainsTwoElements(){

		int[] givenArray = {2, -1};
		
		MaximumValueBetweenTwoSubArray instance = new MaximumValueBetweenTwoSubArray(givenArray);
	
		instance.process();
		
		int maximumValue = instance.getMaximumValueBetweenTwoSubArrays();
		
		int[] subArrayA = instance.getSubArrayA();
		
		int[] subArrayB = instance.getSubArrayB();
		
		assertEquals(3, maximumValue);
		assertEquals("[2]", Arrays.toString(subArrayA));
		assertEquals("[-1]", Arrays.toString(subArrayB));
	}
	
	/**
	 * E.g.
	 * [2, -1, -2] -> A[2] and B[-1, -2]
	 * |Sum(A) - Sum(B)| = 5 the largest
	 */
	@Test
	public void testGivenArrayContainsThreeElements(){

		int[] givenArray = {2, -1, -2};
		
		MaximumValueBetweenTwoSubArray instance = new MaximumValueBetweenTwoSubArray(givenArray);
	
		instance.process();
		
		int maximumValue = instance.getMaximumValueBetweenTwoSubArrays();
		
		int[] subArrayA = instance.getSubArrayA();
		
		int[] subArrayB = instance.getSubArrayB();
		
		assertEquals(5, maximumValue);
		assertEquals("[2]", Arrays.toString(subArrayA));
		assertEquals("[-1, -2]", Arrays.toString(subArrayB));
	}

	/**
	 * E.g.
	 * [2, -1, -2, 1, -4, 2, 8] -> A=[-1, -2, 1, -4] and B[2, 8]
	 * |Sum(A) - Sum(B)| = 16 the largest
	 */
	@Test
	public void testGiveArrayContainsManyElements(){
		int[] givenArray = {2, -1, -2, 1, -4, 2, 8};
		
		MaximumValueBetweenTwoSubArray instance = new MaximumValueBetweenTwoSubArray(givenArray);
	
		instance.process();
		
		int maximumValue = instance.getMaximumValueBetweenTwoSubArrays();
		
		int[] subArrayA = instance.getSubArrayA();
		
		int[] subArrayB = instance.getSubArrayB();
		
		assertEquals(16, maximumValue);
		assertEquals("[2, 8]", Arrays.toString(subArrayA));
		assertEquals("[-1, -2, 1, -4]", Arrays.toString(subArrayB));
	}
}