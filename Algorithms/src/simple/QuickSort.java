package simple;

import java.io.IOException;
import java.util.Scanner;

public class QuickSort {

	 public static int partition(int[] a, int low, int high)
	  {
	   int left=low-1;
	   int temp;
	   int pivot=a[high];
	   for(int right=low;right<high;right++){
	    if(a[right] <= pivot)     //   <- if(a[right] <= pivot)
	    {
	     left ++;
	     temp = a[left];
	     a[left]=a[right];
	     a[right]=temp;
	    }
	   }
	   temp = a[left+1];
	   a[left+1]=a[high];
	   a[high]=temp;
	   return left+1;

	}

	  public static void quickSort(int[] a, int low, int high)
	   {
	    if(low < high)
	    {
	     int pivotPlace = partition(a, low, high);
	    quickSort(a, low, pivotPlace-1);
	    quickSort(a, pivotPlace+1, high);
	    }
	  }

	  public static void main(String[] args)
	   {
	   int a[] = {7, 5, 16, 1, 13, 9, 9, 0};
	   
	   for(int i=0; i<a.length;i++)
		    System.out.print(a[i]+" ");
		System.out.println();   
	   quickSort(a, 0, 7);
	   
	   System.out.print("The sorted data : ");
	   for(int i=0; i<a.length;i++)
	    System.out.print(a[i]+" ");
	   }


}
