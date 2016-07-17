package DynaminProgramming;

import java.util.Scanner;

public class Main{
	
	public static int N;
	public static int cache[];
	public static int MOD = 1000000007;
	
	public static void main(String [] args){

		Scanner scan = new Scanner(System.in);
		int T=scan.nextInt();
		
		cache = new int[101];		
		for(int k=0; k<T; k++){
			
			N=scan.nextInt();
			
			cache = new int[N+1];
			init();
			
			int result = tileup(N);
			System.out.println(result);
		}
	}
	
	private static void init() {
		for(int i=0; i <= N; i ++){
			cache[i] = -1;
		}
	}

	public static int tileup(int n){
		
		if(n <= 1){
			return 1;
		}
		
		int ret = cache[n];
		if(ret != -1){
			return ret;
		}
		
		cache[n] = (tileup(n-1) + tileup(n-2)) % MOD;
		return cache[n];
	}
}