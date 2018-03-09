package simple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemoryAllocationN {

	public static void main(String[] args) throws IOException{
		

		ArrayList<Integer[]> input = new ArrayList<Integer[]>();
		input.add(1);
//		input.add(2);
//		input.add(3);
//		input.add(4);
		
		
		System.out.println(input.get(0));
//		System.out.println(input.get(1));
//		System.out.println(input.get(2));
//		System.out.println(input.get(3));
		
		
		/*
		ArrayList<String> input = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);
		String temp = "";
		System.out.println("�ܾ �Է��ϼ���, x ġ�� ����: ");
		//int i =0;
		
		while(true){
			temp = scan.next();
			if(temp.equals("x")){
				break;
			}else{
				input.add(temp);
				//i++;
			}
		}
		System.out.println("input finished");
		for(int i=0; i <input.size(); i++){
			System.out.println(input.get(i)+" ");
		}
		for(int i=0; i <input.size(); i++){
			System.out.println(input.get(i).length());
		}
		String s = "awefjkahwef;hawehfklajwfejhalwkhf";
		System.out.println(s.length());
		s= "wrwerw";
		
		System.out.println(s);
		System.out.println(s.sizeof());
		
		*/
//		int intmax = Integer.MAX_VALUE;
//		Integer i = 2147483647;
//		System.out.println(i.SIZE);
//		Integer i2 = 1;
//		System.out.println(i2.SIZE);
//		
//		
//		String s = "2147483648";
//		System.out.println(s);
	
//		Integer k[] = new Integer[20000000];
//		Integer a[] = new Integer[20000000];
//		//a[] = null;
//		for(int i = 0; i < 20000000 ; i++)		{
//		//	a[i] = 2147483647;
//			a[i] = null;
//		}
//		Integer b[] = new Integer[20000000];
		
		/*
		for(int i = 0; i < 20000000 ; i++)
		{
			k[i] = 2147483647;
			k[i] = null;
		}
		
		for(int i = 0; i < 20000000 ; i++)
		{
			b[i] = 2147483647;
			b[i] = null;
		}
		*/
	}
}

