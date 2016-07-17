package BruteForce;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Picnic{

	public static int N;
	public static int M;
	public static Boolean areFriends[][];
	public static void main(String [] args) throws FileNotFoundException{
		
		System.setIn(new FileInputStream("D:\\workspace\\Algorithms\\src\\BruteForce\\Picnic.txt"));
		
		Scanner scan = new Scanner(System.in);
		int T=scan.nextInt();
		
		for(int k=0; k<T; k++){
			N=scan.nextInt();
			M=scan.nextInt(); 
			
			areFriends = new Boolean[N][N];
			Boolean[] taken = new Boolean[N]; 
			
			for(int i=0; i<N; i++){
				taken[i] = false;
				for(int j=0; j<N; j++){
					//System.out.println("i: " + i+" j: " + j);
					areFriends[i][j] = false;
				}
			}
			
			for(int i=0; i<M; i++){
				int m=scan.nextInt();
				int n=scan.nextInt();
				
				areFriends[m][n] = true;
				areFriends[n][m] = true;
			}
			
//			for(int i=0; i<N; i++){
//				for(int j=0; j<N; j++){
//					//System.out.println("i: " + i+" j: " + j);
//					System.out.println("areF["+i+"]["+j+"]= " + areFriends[i][j]);
//				}
//			}
			
			
			long start = System.currentTimeMillis();
			
			int result = countingPair(taken);
			
		    long end = System.currentTimeMillis();
		    //System.out.println((end - start)/(double)1000);
			System.out.println("result : " + result);
			System.out.println();
		}
	}
	private static int countingPair(Boolean[] taken) {
	
		int cnt = 0;
//		int finished = 1;
		int free = -1;
		
		for(int i=0; i<N; i++){
			if(taken[i] == false){
				free = i;
				break;
			}
		}
		if(free == -1){
			return 1;
		}
		
		for(int j=free+1; j<N; j++){
			if(taken[j]==false && areFriends[free][j]==true){
				
				//System.out.println("i: " + free+" j: " + j);
				//cnt =1;
				taken[free] = taken[j] =true;
				cnt = cnt + countingPair(taken);
				taken[free] = taken[j] =false;
				//System.out.println("cnt: " + cnt);
				
			}
		}
		
		return cnt;
	}
}

