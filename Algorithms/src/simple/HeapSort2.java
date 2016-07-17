package simple;

import java.io.IOException;
import java.util.Scanner;

public class HeapSort2 {

	public static void heapSort(int[] arr){
		int len = arr.length;
		for(int i=len/2; i>0; i--){
			downHeap(arr,i,len);
		}
		do{
			int temp = arr[0];
			arr[0] = arr[len-1];
			arr[len-1] = temp;
			len--;
			downHeap(arr,1,len);
		}while(len > 1);
	}
	
	private static void downHeap(int arr[], int k, int len){
		
		int temp = arr[k-1];
		
		while(k <= len/2){
			int j = 2 * k;
			if(j<len && arr[j-1] < arr[j]){
				j++;
			}
			if(temp > arr[j-1]){
				break;
			}else{
				arr[k-1] = arr[j-1];
				k=j;
			}
		}
		arr[k-1] = temp;
	}
	
	static void printArray(int[] array){
		for(int i =0; i <array.length; i++){
			System.out.print(array[i] + " ");
		}
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
