package basic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Template{

	public static void main(String [] args) throws NumberFormatException, IOException{
		
		System.setIn(new FileInputStream("D:\\workspace\\hello-world\\Algorithms\\src\\basic\\Template.txt"));
		//Scanner scan = new Scanner(System.in);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		//T = scan.nextInt();
		String[] split;
		
		for(int k=0; k<T; k++){
			//int N=scan.nextInt();
			split = br.readLine().split(" ");
			
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);
			
			System.out.println(a + "  " + b);
//			int[] A = new int[N];
		
//			for(int i=0; i<N ;i++){
//				A[i]=scan.nextInt();
//			}
		
//			long start = System.currentTimeMillis();
//		    long end = System.currentTimeMillis();
//		    System.out.println((end - start)/(double)1000);
			
		}
	}
}

