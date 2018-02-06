package simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ArraySortTest {

	public static void main(String [] args){
		
		
		int[] jumsu = {90,89,54,35,45,45,76,98,23,12};

		
		System.out.println("1 : " + jumsu);
		System.out.println("2 : " + Arrays.toString(jumsu));
		
		Arrays.sort(jumsu);;
		System.out.println("3 : " + Arrays.toString(jumsu));
		
		ArrayList<Integer> jumsuList = new ArrayList<Integer>();
		jumsuList.add(4);
		jumsuList.add(1);
		jumsuList.add(2);
		jumsuList.add(7);
		System.out.println("4 : " + jumsuList);
		
		Collections.sort(jumsuList);
		System.out.println("5 : " + jumsuList);
		
	}
}
