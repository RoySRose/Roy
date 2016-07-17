package simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListTest {

	public static void main(String [] args){
		ArrayList<Integer> list = new ArrayList<Integer>();
	
		list.add(12);
		list.add(24);
		list.add(31);
		System.out.println(list);
		
		list.add(2,100);
		System.out.println(list);
		
		for(int i=0; i<list.size(); i++){
			System.out.println(list.get(i) + ",");
		}
		System.out.println();
		
		Integer[] temp = new Integer[list.size()];
		list.toArray(temp);
		
		System.out.println(Arrays.toString(temp));
		
		List<Integer> list2 = Arrays.asList(1,3,5,7); 
		System.out.println(list2);
		
	}
}
