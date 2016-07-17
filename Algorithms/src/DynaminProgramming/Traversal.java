package DynaminProgramming;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Traversal{

	public static int N;
	
	public static void main(String [] args) throws FileNotFoundException{
		
		System.setIn(new FileInputStream("D:\\workspace\\Algorithms\\src\\DynaminProgramming\\Traversal.txt"));
		
		Scanner scan = new Scanner(System.in);
		int T=scan.nextInt();
		
		
		for(int k=0; k<T; k++){
			N=scan.nextInt();
			int[] A = new int[N];
			int[] B = new int[N];
		
			for(int i=0; i<N ;i++){
				A[i]=scan.nextInt();
			}
			for(int i=0; i<N ;i++){
				B[i]=scan.nextInt();
			}
			
//			long start = System.currentTimeMillis();
			
			printPostOrder(A,B);
			
			
//		    long end = System.currentTimeMillis();
//		    System.out.println((end - start)/(double)1000);
			
		}
	}

	private static void printPostOrder(int[] a, int[] b) {
		
		
		int size = a.length;
		if(size == 0){
			return;
		}
		int root = a[0];
//		int findrootb = 0;
		int cnt=0;
			
//		27 16 9 12 54 36 72
//		9 12 16 27 36 54 72
		
		for(int i=0; i<size ;i++){
			if(b[i] == root){
				//findrootb= i;
				break;
			}
			cnt++;
		}
//		int left[] = slice(a,1,3);
		
		int left = cnt;
		int right = size-left-1;
		
		System.out.println("left: "+left+"  right: "+right);
		printPostOrder(slice(a,1,left), slice(b,0,left));
		printPostOrder(slice(a,left+1,right), slice(b,left+1,right));
		System.out.println(root);
	}

	private static int[] slice(int[] a, int i, int k) {
		
		int[] ret = new int[k];
		
		for(int j=0; j<k ;j++){
			ret[j] = a[j+i];
		}
		
		return ret;
	}
}

