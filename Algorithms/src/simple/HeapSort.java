package simple;

import java.io.IOException;
import java.util.Scanner;

public class HeapSort {

	public static void heapSort(int[] arr){
		int len = arr.length;
		for(int k = len/2; k>0; k--){
			downHeap(arr, k,len);
		}
		System.out.println("###Frist Heap Building Finished###\n");
		do{
			int temp = arr[0];
			arr[0] = arr[len-1];
			arr[len-1] = temp;
			len = len-1;
			downHeap(arr,1,len);
		}while(len>1);
		//return arr;
	}
	
	private static void downHeap(int arr[], int k, int len){
		int temp = arr[k-1];
		System.out.println("==downHeap Start : k=" + k + " len=" + len + " temp"+temp+" ==");
		while(k<= len/2){
			int j=2*k; //Left Child Node
			//System.out.println("--k = " + k + " j=" + j);
			if((j<len) && (arr[j-1] < arr[j])){
				//System.out.println("Right value BIGGER: " + arr[j-1] + " < "+arr[j]);
				j++;
				//System.out.println("so Now j i " + j);
			}
			if(temp >= arr[j-1]){
				//System.out.println("I'm BIGGER than Child. so break here!!!"+ temp + " >= " + arr[j-1] + "\n------------");
				break;
			}else{
				//System.out.println("Under valueBIGGER : "+ temp  "<" +arr[j-1]);
				arr[k-1] = arr[j-1];
				//System.out.println("So arr[" + (k-1) + "] : " + arr[k-1] + "\n------------");
				k=j;
			}
		}
		arr[k-1] = temp;
		//System.out.println("arr[" + (k-1) + "]:" + temp + "\n-----------------"););//printArray(arr)
		//printArray(arr);
	}
	
	static void printArray(int[] array){
		for(int i =0; i <array.length; i++){
			System.out.println(array[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws IOException{
		
		Scanner sc = new Scanner(System.in);

		int boycnt = 12;	
		
		
		int boy[] = new int[boycnt];
		int boyh[] = new int[boycnt];
		
		boy[0] = 0;
		boy[1] = 2;
		boy[2] = 3;
		boy[3] = 4;
		boy[4] = 5;
		boy[5] = 7;
		boy[6] = 8;
		boy[7] = 4;
		boy[8] = 1;
		boy[9] = 2;
		boy[10] = 4;
		boy[11] = 5;
		
		heapSort(boy);
		printArray(boy);
	}
}
