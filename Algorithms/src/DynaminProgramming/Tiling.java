package DynaminProgramming;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Tiling{
	
	public static int N;
	public static int cache[];
	public static int MOD = 1000000007;
	
	public static void main(String [] args) throws FileNotFoundException{
		
		System.setIn(new FileInputStream("D:\\workspace\\Algorithms\\src\\DynaminProgramming\\Tiling.txt"));
		
		Scanner scan = new Scanner(System.in);
		int T=scan.nextInt();
		
		cache = new int[101];		
		for(int k=0; k<T; k++){
			
			N=scan.nextInt();
			
			//System.out.println("N : "+N);
			cache = new int[N+1];
			
			for(int i=0; i <= N; i++){
				cache[i] = -1;
			}
			
			long start = System.currentTimeMillis();
			
			int result = tileupasymmectric(N);
			
		    long end = System.currentTimeMillis();
		    System.out.println("result : " + result);
		    
		    System.out.println((end - start)/(double)1000);
		    System.out.println();
		    
			
		}
	}

	public static int tileup(int n){
		
		if(n <= 1){
			return 1;
		}
		
		//System.out.println("n : " + n);
		int ret = cache[n];
		if(ret != -1){
			//System.out.println("meet cache");
			return ret;
		}
		
		cache[n] = (tileup(n-1)% MOD + tileup(n-2)% MOD) % MOD;
		return cache[n];
	}

	public static int tileupasymmectric(int n){
		

		if(n%2 == 1){
			return (tileup(n) - tileup(n/2) + MOD) % MOD;
		}else{
			return (tileup(n) - tileup(n/2) - tileup(n/2-1) + 2 *MOD) % MOD;
		}
	}
	
}


