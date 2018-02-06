package Strings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Naming{
	
	public static String Htemp;
	public static String Ntemp;
	public static ArrayList<Integer> res = new ArrayList<Integer>();
	public static int Hlen;
	public static int Nlen;
	public static int[] pi;
	
	public static void main(String [] args) throws FileNotFoundException{
		
		System.setIn(new FileInputStream("D:\\workspace\\Algorithms\\src\\Strings\\Naming.txt"));
		
		Scanner scan = new Scanner(System.in);
		int T=scan.nextInt();
		scan.nextLine();
		
		
		
		for(int k=0; k<T; k++){
			
			res.clear();
			
			Ntemp = scan.nextLine();
			
			System.out.println(Ntemp);
			Nlen = Ntemp.length();
			char[] N = new char[Nlen];
			N = Ntemp.toCharArray();
			
			pi = new int[Nlen];
			
			Initpi(N);
			System.out.println("pi");
			
			for(int i=0; i<Nlen; i++){
				System.out.println("pi["+i+"] = " +pi[i]);
			}
			System.out.println();
			
			long start = System.currentTimeMillis();
			
			System.out.println("result : ");
			System.out.println(res);
						
		    long end = System.currentTimeMillis();
		    //System.out.println((end - start)/(double)1000);
			//System.out.println("result : " + result);
		}
	}

	private static void Initpi(char[] N) {
		int start = 1;
		int matched = 0;
		
		while(start + matched < Nlen){
			if(N[start + matched] == N[matched]){
				matched++;
				pi[start+matched-1] = matched;
			}else{
				if(matched == 0){
					start++;
				}else{
					start += matched - pi[matched-1];
					matched = pi[matched-1];
				}
			}
		}
		
	}

}
