package Strings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class KMP{
	
	public static String Htemp;
	public static String Ntemp;
	public static ArrayList<Integer> res = new ArrayList<Integer>();
	public static int Hlen;
	public static int Nlen;
	public static int[] pi;
	
	public static void main(String [] args) throws FileNotFoundException{
		
		System.setIn(new FileInputStream("D:\\workspace\\Algorithms\\src\\Strings\\KMP.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T=sc.nextInt();
		sc.nextLine();
		
		for(int k=0; k<T; k++){
			
			res.clear();
			
			Htemp = sc.nextLine();
			Ntemp = sc.nextLine();
			
			System.out.println(Htemp);
			System.out.println(Ntemp);
			Hlen = Htemp.length();
			Nlen = Ntemp.length();
			char[] H = new char[Hlen];
			H = Htemp.toCharArray();
			char[] N = new char[Nlen];
			N = Ntemp.toCharArray();
			
			pi = new int[Nlen];
			
//			for(int i=0; i<Nlen; i++){
//				System.out.print(N[i]);
//			}
//			System.out.println();
//			
//			System.out.println("start");
			Initpi(N);
			
			
			
			System.out.println("pi");
//			
//			for(int i=0; i<Nlen; i++){
//				System.out.println("pi["+i+"] = " +pi[i]);
//			}
//			System.out.println();
			
			long start = System.currentTimeMillis();
			
			Search(H,N);
			
			System.out.println("result : ");
			System.out.println(res);
						
		    long end = System.currentTimeMillis();
		    //System.out.println((end - start)/(double)1000);
			//System.out.println("result : " + result);
		}
	}

	private static void Initpi(char[] N) {
		int start = 0;
		int matched = 0;
		
		while(start + matched < Nlen){
			System.out.println("start: "+start +"  "
					+ "matched: " +matched);
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

	private static void Search(char[] H, char[] N) {

		int start = 0;
		int matched = 0;
		
		while(start < Hlen - Nlen){
			if(matched < Nlen && H[start + matched] == N[matched]){
				matched++;
				if(matched == Nlen){
					res.add(start);
				}
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
