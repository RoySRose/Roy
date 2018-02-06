package basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Template{

	public static void main(String [] args) throws FileNotFoundException{
		
		System.setIn(new FileInputStream("D:\\workspace\\Algorithms\\src\\DynaminProgramming\\LIS.txt"));
		
		Scanner scan = new Scanner(System.in);
		int T=scan.nextInt();
		
		
		for(int k=0; k<T; k++){
			int N=scan.nextInt();
			int[] A = new int[N];
		
			for(int i=0; i<N ;i++){
				A[i]=scan.nextInt();
			}
		
//			long start = System.currentTimeMillis();
//		    long end = System.currentTimeMillis();
//		    System.out.println((end - start)/(double)1000);
			
		}
	}
}

