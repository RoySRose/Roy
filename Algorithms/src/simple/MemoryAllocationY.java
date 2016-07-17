package simple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemoryAllocationY {

	public static void main(String[] args) throws IOException{
	
		List<String> input = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);
		String temp = "";
		String array[];
		System.out.println("단어를 입력하세요, x 치면 끝남: ");
		int i =0;
		
		while(true){
			temp = scan.next();
			if(temp.equals("x")){
				break;
			}else{
				input.add(temp);
				i++;
			}
		}
		System.out.println("input finished");
		
		array = new String[input.size()];
		for(i=0; i <input.size(); i++){
			array[i]  = input.get(i);
		}
		
		System.out.println("동적할당된 배열 출력");
		for(i=0; i <array.length; i++){
			System.out.println(array[i]);
		}
	}
}
