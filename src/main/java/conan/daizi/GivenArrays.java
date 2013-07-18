package conan.daizi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GivenArrays {

	private ArrayList<int[]> arrayList;
	
	public GivenArrays add(final int[] input) {
		if(arrayList == null){
			arrayList = new ArrayList<int[]>();
		}
		
		arrayList.add(Arrays.copyOf(input, input.length));
		
		return this;
	}

	public final int[] get(int i) {
		return arrayList.get(i);
	}

	public int getSize() {
		return arrayList.size();
	}
	
	public String toString(){
		
		StringBuffer sb = new StringBuffer("");
		
		for(int i = 0; i < arrayList.size(); i++){
			sb.append(Arrays.toString(arrayList.get(i))).append(",");
		}
		return sb.toString();
	}

	public int getMatchedOccurenceBitmap() {

		int matchedOccurenceBitmap = 0;
		
		for(int i = 0; i < arrayList.size(); i++){
			matchedOccurenceBitmap = matchedOccurenceBitmap | 1 << i;
		}
		
		return matchedOccurenceBitmap;
	}

	public Element[] getSortedElementArray() {
		List<Element> aggregatedElements = aggregateArrays();
		
		return sort(aggregatedElements);
	}
	

	private Element[] sort(List<Element> aggregatedElements) {
		
		Element[] elementArray = new Element[aggregatedElements.size()];
		
		elementArray = aggregatedElements.toArray(elementArray);
		
		Arrays.sort(elementArray, new Comparator<Element>(){
			public int compare(Element o1, Element o2) {
				return o1.getValue() - o2.getValue();
			}
			
		});
		
		return elementArray;
	}

	private List<Element> aggregateArrays() {
		Map<Integer, Element> resultMap = new HashMap<Integer, Element>();
		
		for(int i = 0; i < arrayList.size(); i++){
			int[] oneArray = arrayList.get(i);
			for(int j = 0; j < oneArray.length; j++){
				Element element = resultMap.get(oneArray[j]);
				
				if(element == null){
					element = new Element(oneArray[j]);
					
					resultMap.put(oneArray[j], element);
				}
				
				element.occurAtArray(i);				
			}
		}
		
		List<Element> result = new ArrayList<Element>(resultMap.size());
		for(Map.Entry<Integer, Element> entry : resultMap.entrySet()){
			result.add(entry.getValue());
		}
		
		return result;
	}
	
	class Element {
		private int value;
		
		private int occurenceOfArraysInbitmap = 0;
		
		Element(int value){
			this.value = value;
		}
		
		public void occurAtArray(int i) {
			occurenceOfArraysInbitmap = occurenceOfArraysInbitmap | 1 << i;
		}

		public int getValue() {
			return value;
		}

		int getOccurenceBitmap(){
			return occurenceOfArraysInbitmap;
		}
	}
}
