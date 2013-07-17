package conan.daizi;

import java.text.MessageFormat;
import java.util.Arrays;


/**
 * Given an array, find two sub arrays and meet the following conditions.
 * 1. Array A and array B have no overlaps.
 * 2. Array A and array B are continues.
 * 3. |Sum(A) - Sum(B)| is the largest.
 */
public class MaximumValueBetweenTwoSubArray {

	private int[] givenArray;
	private int maximumValue;
	private int[] subArrayB;
	private int[] subArrayA;

	MaximumValueBetweenTwoSubArray(final int[] givenArray) {
		if(givenArray == null || givenArray.length <= 1){
			throw new IllegalArgumentException("Invalid given array");
		}
		this.givenArray = givenArray;
	}

	/**
	 * Dynamic Processing
	 * 
	 * E.g.
	 * Given   Index:| 0| 1| 2| 3| 4| 5| 6| 
	 *         Value:| 2|-1|-2| 1| 4| 2| 8|
	 *         
	 * Suppose the given array length is n, and index i divide the given array to two sub arrays within index as [0, i-1] and [i, n-1]
	 * 
	 * 1. Find max_left(0, i-1) and min_left(0, i-1), from left to right
	 * 2. Find max_right(i, n-1) and min_righ(i, n-1), from right to left
	 * 
	 */
	public void process() {
		
		System.out.println(MessageFormat.format("Begin process array {0}", Arrays.toString(givenArray)));
		
		CalculateResult max = null;
		CalculateResult min = null;
		
		int maxValue = Integer.MIN_VALUE;
		
		for(int i = 0; i < givenArray.length - 1; i++){
			CalculateResult maxLeft = calculateMax(0, i);
			System.out.println(MessageFormat.format("[{0}] MaxLeft[{1}, {2}].sum()={3}", i, maxLeft.getStart(), maxLeft.getEnd(), maxLeft.getSum()));
			
			CalculateResult minLeft = calculateMin(0, i);
			System.out.println(MessageFormat.format("[{0}] MinLeft[{1}, {2}].sum()={3}", i, minLeft.getStart(), minLeft.getEnd(), minLeft.getSum()));
			
			CalculateResult maxRight = calculateMax(i + 1, givenArray.length - 1);
			System.out.println(MessageFormat.format("[{0}] MaxRight[{1}, {2}].sum()={3}", i, maxRight.getStart(), maxRight.getEnd(), maxRight.getSum()));
			
			CalculateResult minRight = calculateMin(i + 1, givenArray.length - 1);
			System.out.println(MessageFormat.format("[{0}] MinRight[{1}, {2}].sum()={3}", i, minRight.getStart(), minRight.getEnd(), minRight.getSum()));
			
			
			int leftMinusRight = abs(maxLeft, minRight);
			int rightMinusLeft = abs(maxRight, minLeft);
			
			if(leftMinusRight > maxValue){
				maxValue = leftMinusRight;
				max = maxLeft;
				min = minRight;
			}
			
			if(rightMinusLeft > maxValue){
				maxValue = rightMinusLeft;
				max = maxRight;
				min = minLeft;
			}

			System.out.println(MessageFormat.format("[{0}] Max[{1}, {2}].sum()={3} Min[{4}, {5}].sum()={6}", i, max.getStart(), max.getEnd(), max.getSum(), 
					min.getStart(), min.getEnd(), min.getSum()));
		}
		
		
		
		subArrayA = Arrays.copyOfRange(givenArray, max.getStart(), max.getEnd() + 1);
		subArrayB = Arrays.copyOfRange(givenArray, min.getStart(), min.getEnd() + 1);
		
		System.out.println(MessageFormat.format("Maximum Array[{0}, {1}] Sum={2} : {3}", max.getStart(), max.getEnd(), max.getSum(), Arrays.toString(subArrayA)));
		System.out.println(MessageFormat.format("Minimum Array[{0}, {1}] Sum={2} : {3}", min.getStart(), min.getEnd(), min.getSum(), Arrays.toString(subArrayB)));
		
		maximumValue = maxValue;
	}

	private int abs(CalculateResult maxLeft, CalculateResult minRight) {
		return maxLeft.getSum() - minRight.getSum();
	}

	private CalculateResult calculateMin(int begin, int end) {
		int sum = givenArray[begin];
		
		int minSumBegin = begin;
		int minSumEnd = begin;
		
		for(int i = begin; i < end + 1; i++){
			int tempSum = 0;
			for(int j = i; j < end + 1; j++){
				tempSum = tempSum + givenArray[j];
				
				if(tempSum < sum){
					sum = tempSum;
					minSumBegin = i;
					minSumEnd = j;
				}
			}
		}
		
		CalculateResult result = new CalculateResult(minSumBegin, minSumEnd, sum);
		
		return result;
	}

	private CalculateResult calculateMax(int begin, int end) {
		int sum = givenArray[begin];
		
		int maxSumBegin = begin;
		int maxSumEnd = begin;
		
		for(int i = begin; i < end + 1; i++){
			int tempSum = 0;
			for(int j = i; j < end + 1; j++){
				tempSum = tempSum + givenArray[j];
				
				if(tempSum > sum){
					sum = tempSum;
					maxSumBegin = i;
					maxSumEnd = j;
				}
			}
		}
		
		CalculateResult result = new CalculateResult(maxSumBegin, maxSumEnd, sum);
		
		return result;
	}

	public int getMaximumValueBetweenTwoSubArrays() {
		return this.maximumValue;
	}

	public int[] getSubArrayA() {
		return this.subArrayA;
	}

	public int[] getSubArrayB() {
		return this.subArrayB;
	}

	private class CalculateResult{
		private int start;
		
		private int end;
		
		private int sum;
		
		CalculateResult(int start, int end, int sum){
			this.start = start;
			this.end = end;
			this.sum = sum;
		}
		
		int getStart(){
			return start;
		}
		
		int getEnd(){
			return end;
		}
		
		int getSum(){
			return sum;
		}
	}
}
