package DynaminProgramming;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class LISDP{

	public static int N=0;
	public static int[] cache = new int[101];
	public static Vector<Integer> A = new Vector<Integer>();
	
	public static void main(String [] args) throws FileNotFoundException{
		
		System.setIn(new FileInputStream("D:\\workspace\\Algorithms\\src\\DynaminProgramming\\LIS.txt"));
		
		Scanner scan = new Scanner(System.in);
		int T=scan.nextInt();
		
		
		for(int k=0; k<T; k++){
			N=scan.nextInt();
			initcache();
			
		
			for(int i=0; i<N ;i++){
				A.add(scan.nextInt());
			}
		
			System.out.println(lis3(-1)-1);
			
			for(int i=0; i<101 ;i++){
				System.out.print(cache[i] + " ");
				if(i%10==0){
					System.out.println();
				}
			}
//			long start = System.currentTimeMillis();
//		    long end = System.currentTimeMillis();
//		    System.out.println((end - start)/(double)1000);
			
		}
	}

//	private static int lis(Vector<Integer> A) {
//		
//		if(A.isEmpty()){
//			return 0;
//		}
//		int ret=0;
//		for(int i =0; i <A.size(); i++){
//			Vector<Integer> B = new Vector<Integer>();
//			for(int j=i+1; j<A.size(); j++){
//				if(A.get(i) < A.get(j)){
//					B.add(A.get(j));
//				}
//			}
//			ret = max(ret, 1+lis(B));
//		}
//		return ret;
//	}

	private static int lis3(int start) {
		
		int ret = cache[start+1];
		if(ret != -1){
			return ret;
		}
		
		ret = 1;
		for(int next = start+1; next<N; next++){
			if(start == -1 || A.get(start) < A.get(next)){
				ret = cache[start+1] = max(ret, lis3(next) +1);
			}
		}
		return ret;
	}

	private static void initcache() {
		
		for(int i=0; i<101; i++){
			cache[i]=-1;
		}
	}
	private static int max(int a, int b) {
		if(a>b){
			return a;
		}else
			return b;
	}
}

