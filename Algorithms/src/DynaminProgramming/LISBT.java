package DynaminProgramming;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class LISBT{

	public static void main(String [] args) throws FileNotFoundException{
		
		System.setIn(new FileInputStream("D:\\workspace\\Algorithms\\src\\DynaminProgramming\\LIS.txt"));
		
		Scanner scan = new Scanner(System.in);
		int T=scan.nextInt();
		
		
		for(int k=0; k<T; k++){
			int N=scan.nextInt();
			Vector<Integer> A = new Vector<Integer>();
		
			for(int i=0; i<N ;i++){
				A.add(scan.nextInt());
			}
		
			System.out.println(lis(A));
			
//			long start = System.currentTimeMillis();
//		    long end = System.currentTimeMillis();
//		    System.out.println((end - start)/(double)1000);
			
		}
	}

	private static int lis(Vector<Integer> A) {
		
		if(A.isEmpty()){
			return 0;
		}
		int ret=0;
		for(int i =0; i <A.size(); i++){
			Vector<Integer> B = new Vector<Integer>();
			for(int j=i+1; j<A.size(); j++){
				if(A.get(i) < A.get(j)){
					B.add(A.get(j));
				}
			}
			ret = max(ret, 1+lis(B));
		}
		return ret;
	}

	private static int max(int a, int b) {
		if(a>b){
			return a;
		}else
			return b;
	}
}

