package DivideConquer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class QuadTree{
	
	static int pos = 0;
	
	public static void main(String [] args) throws FileNotFoundException{
		
		System.setIn(new FileInputStream("D:\\workspace\\Algorithms\\src\\DivideConquer\\QuadTree.txt"));
		
		Scanner scan = new Scanner(System.in);
		int T=scan.nextInt();
		scan.nextLine();
		
		for(int k=0; k<T; k++){
			pos =0;
			String line = scan.nextLine();
			
			Queue q = new LinkedList();
			
			System.out.println("line :" + line);

			
			long start = System.currentTimeMillis();
			
			String result = reverse(line);
			
		    long end = System.currentTimeMillis();
		    //System.out.println((end - start)/(double)1000);
			System.out.println("result : " + result);
		}
	}
	private static String reverse(String line) {

		
		char first = line.charAt(pos);
		pos++;
		if(first == 'b' || first == 'w'){
			return String.valueOf(first);
		}
		
		String upperleft = reverse(line);
		String upperright = reverse(line);
		String lowerleft = reverse(line);
		String lowerright = reverse(line);
		
		
		return "x" + lowerleft + lowerright + upperleft + upperright;
	}
}
