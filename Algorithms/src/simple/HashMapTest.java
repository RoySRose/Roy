package simple;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class HashMapTest {
	
	public static void main(String[] args) throws IOException{
		
		HashMap<Integer, String> test = new HashMap<Integer, String>();
		
		test.put(1, "PJH");
		test.put(9, "LSH");
		test.put(5, "JHJ");
		test.put(4, "SCY");
		test.put(7, "SKT");
		
		System.out.println("3? : " + test.containsKey(1));
		System.out.println("4? : " + test.containsKey(3));
		
		Iterator<Integer> it = test.keySet().iterator();
		while(it.hasNext()){
			int key = it.next();
			System.out.println(key + "=");
			System.out.println(test.get(key));
		}
	}
}
