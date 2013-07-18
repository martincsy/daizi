package conan.daizi;

import java.text.MessageFormat;

import conan.daizi.GivenArrays.Element;

/**
 * 一个都不能少(须弥之镜)。
 * 有k个有序的数组，请找到一个最小的数字范围。使得这K个有序数组中，每个数组都至少有一个数字在该范围中。
 * 例如
 * A: 4, 10, 15, 24, 26
 * B: 0, 9, 12, 20
 * C: 5, 18, 22, 30
 * 
 * 所得最小范围为[20, 21, 22, 23, 24],其中，20在B中，22在C中，24在A中。
 * 
 * @author caosha
 *
 */
public class EachArrayHasElementInMinimumSet {

	private GivenArrays givenArrays;
	private int[] minimumSet;
	
	public EachArrayHasElementInMinimumSet(GivenArrays givenArrays) {
		this.givenArrays = givenArrays;
	}

	/**
	 * 思路是将所有数组里的数字排个序，然后用一个变量来标记该数字在各个数组中是否出现，
	 *   | 0 | 4 | 5 | 9 | 10| 12| 15| 18| 20| 22| 24| 26| 30|
	 * A   0   1   0   0   1   0   1   0   0   0   1   1   0  
	 * B   1   0   0   1   0   1   0   0   1   0   0   0   0
	 * C   0   0   1   0   0   0   0   1   0   1   0   0   1
	 * 横轴是排好序的数字，纵轴是该数字在每个数组出现的标记，
	 * 考虑用bitmap来计算
	 * 用二进制(bin)表示则为 f(0)=010, f(1)=100, f(2)=001 ... 
	 * f(0)) + f(1) + f(2) = 111, 则[0,4,5]符合条件,以0为起始坐标是最小的集合
	 * 以此类推，[4,5,9], [5,9,10]也符合
	 * 然后用动态规划，从左到右走一遍，就能得到最下的集合Len[20,22,24]=5
	 * 
	 */
	public void process() {
		System.out.println(givenArrays.toString());
		
		Element[] sortedElements = givenArrays.getSortedElementArray();
		
		int matchedBitmap = givenArrays.getMatchedOccurenceBitmap();
		
		Element minStartElement = null;
		Element minEndElement = null;
		
		int minLength = Integer.MAX_VALUE;
		
		for(int i = 0; i < sortedElements.length; i++){
			int elemntOccurrence = 0;
			for(int j = i; j < sortedElements.length; j++){
				elemntOccurrence = elemntOccurrence | sortedElements[j].getOccurenceBitmap();
				
				if(elemntOccurrence == matchedBitmap){
					//Find a range
					int tempLength = sortedElements[j].getValue() - sortedElements[i].getValue() + 1;
					
					System.out.println(MessageFormat.format("[{0}, {1}] length is {2}", sortedElements[i].getValue(), sortedElements[j].getValue(), tempLength));
					
					if(tempLength < minLength){
						minStartElement = sortedElements[i];
						minEndElement = sortedElements[j];
						minLength = tempLength;

						System.out.println(MessageFormat.format("Minimum [{0}, {1}] length is {2}", minStartElement.getValue(), minEndElement.getValue(), minLength));
					}
					
					break;//break anyway, because this is the minimal set within the start index
				}
			}
		}
		
		minimumSet = generateMinimumSet(minStartElement, minEndElement);
	}

	private int[] generateMinimumSet(Element minStartElement,
			Element minEndElement) {
		
		if(minStartElement.getValue() == minEndElement.getValue()){
			return new int[]{minStartElement.getValue()};
		}
		
		int[] result = new int[minEndElement.getValue() - minStartElement.getValue() + 1];
		
		for(int i = 0; i < result.length; i++){
			result[i] = minStartElement.getValue() + i;
		}
		
		return result;
	}

	public int[] getMinimumSetOfOverlaps() {
		return minimumSet;
	}

}
