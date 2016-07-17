package DynaminProgramming;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class isNerd2{
	
	static TreeMap<Integer, Integer> A;

	public static Boolean isDominated(int x, int y){
			
		if(A.containsKey(x)){
			if(A.get(x) < y){
				return false;
			}
			else return true;
		}
		else if(A.higherEntry(x) == null){
			return false;
		}else{		
			System.out.println("x: " + A.higherKey(x) + "y: " + A.get(A.higherKey(x)));
			return y <= A.get(A.higherKey(x));
		}
	}
	
	public static void ClearDominated(int x, int y){
		
		for(int i=0; i<=x; i++){
			if(A.get(i) <= y){
				A.remove(i);
			}
		}
	}
		
	
	public static void main(String [] args) throws FileNotFoundException{
		
		System.setIn(new FileInputStream("D:\\workspace\\Algorithms\\src\\DynaminProgramming\\isNerd2.txt"));
		
		Scanner scan = new Scanner(System.in);
		int T=scan.nextInt();
		
		
		for(int k=0; k<T; k++){
			int N=scan.nextInt();
			
			A = new TreeMap<Integer, Integer>();
			
			for(int i=0; i<N ;i++){
				A.put(scan.nextInt(), scan.nextInt());
			}
		
			System.out.println(A.toString());
			System.out.println(isDominated(5,2));
			
			
//			long start = System.currentTimeMillis();
//		    long end = System.currentTimeMillis();
//		    System.out.println((end - start)/(double)1000);
			
		}
	}
}

